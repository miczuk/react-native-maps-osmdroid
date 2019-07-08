package com.airbnb.android.react.maps;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.Map;

import javax.annotation.Nullable;

public class AirMapOverlay2Manager extends ViewGroupManager<AirMapOverlay2> {
  private final DisplayMetrics metrics;

  public AirMapOverlay2Manager(ReactApplicationContext reactContext) {
    super();
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      metrics = new DisplayMetrics();
      ((WindowManager) reactContext.getSystemService(Context.WINDOW_SERVICE))
          .getDefaultDisplay()
          .getRealMetrics(metrics);
    } else {
      metrics = reactContext.getResources().getDisplayMetrics();
    }
  }

  @Override
  public String getName() {
    return "AirMapOverlay2";
  }

  @Override
  public AirMapOverlay2 createViewInstance(ThemedReactContext context) {
    return new AirMapOverlay2(context);
  }

  @ReactProp(name = "bounds")
  public void setBounds(AirMapOverlay2 view, ReadableArray bounds) {
    view.setBounds(bounds);
  }

  @ReactProp(name = "zIndex", defaultFloat = 1.0f)
  public void setZIndex(AirMapOverlay2 view, float zIndex) {
    view.setZIndex(zIndex);
  }

  // @ReactProp(name = "transparency", defaultFloat = 1.0f)
  // public void setTransparency(AirMapOverlay2 view, float transparency) {
  //   view.setTransparency(transparency);
  // }

  @ReactProp(name = "image")
  public void setImage(AirMapOverlay2 view, @Nullable String source) {
    view.setImage(source);
  }


  @Override
  @Nullable
  public Map getExportedCustomDirectEventTypeConstants() {
    return MapBuilder.of(
        "onPress", MapBuilder.of("registrationName", "onPress")
    );
  }
}
