package com.airbnb.android.react.maps;

import android.os.Handler;
import android.os.Looper;

import java.util.LinkedList;

public class ViewChangesTracker2 {

  private static ViewChangesTracker2 instance;
  private Handler handler;
  private LinkedList<AirMapMarker2> markers = new LinkedList<>();
  private boolean hasScheduledFrame = false;
  private Runnable updateRunnable;
  private final long fps = 40;

  private ViewChangesTracker2() {
    handler = new Handler(Looper.myLooper());
    updateRunnable = new Runnable() {
      @Override
      public void run() {
        hasScheduledFrame = false;
        update();

        if (markers.size() > 0) {
          handler.postDelayed(updateRunnable, fps);
        }
      }
    };
  }

  static ViewChangesTracker2 getInstance() {
    if (instance == null) {
      synchronized (ViewChangesTracker2.class) {
        instance = new ViewChangesTracker2();
      }
    }

    return instance;
  }

  public void addMarker(AirMapMarker2 marker) {
    markers.add(marker);

    if (!hasScheduledFrame) {
      hasScheduledFrame = true;
      handler.postDelayed(updateRunnable, fps);
    }
  }

  public void removeMarker(AirMapMarker2 marker) {
    markers.remove(marker);
  }

  public boolean containsMarker(AirMapMarker2 marker) {
    return markers.contains(marker);
  }

  private LinkedList<AirMapMarker2> markersToRemove = new LinkedList<>();

  public void update() {
    for (AirMapMarker2 marker : markers) {
      if (!marker.updateCustomForTracking()) {
        markersToRemove.add(marker);
      }
    }

    // Remove markers that are not active anymore
    if (markersToRemove.size() > 0) {
      markers.removeAll(markersToRemove);
      markersToRemove.clear();
    }
  }

}
