package com.airbnb.android.react.maps;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.google.android.gms.maps.model.ButtCap;
import com.google.android.gms.maps.model.Cap;
import com.google.android.gms.maps.model.RoundCap;
import com.google.android.gms.maps.model.SquareCap;

import java.util.Map;

import javax.annotation.Nullable;

public class AirMapPolyline2Manager extends ViewGroupManager<AirMapPolyline2> {
  private final DisplayMetrics metrics;

  public AirMapPolyline2Manager(ReactApplicationContext reactContext) {
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
    return "AirMapPolyline2";
  }

  @Override
  public AirMapPolyline2 createViewInstance(ThemedReactContext context) {
    return new AirMapPolyline2(context);
  }

  @ReactProp(name = "coordinates")
  public void setCoordinate(AirMapPolyline2 view, ReadableArray coordinates) {
    view.setCoordinates(coordinates);
  }

  @ReactProp(name = "strokeWidth", defaultFloat = 1f)
  public void setStrokeWidth(AirMapPolyline2 view, float widthInPoints) {
    float widthInScreenPx = metrics.density * widthInPoints; // done for parity with iOS
    view.setWidth(widthInScreenPx);
  }

  @ReactProp(name = "strokeColor", defaultInt = Color.RED, customType = "Color")
  public void setStrokeColor(AirMapPolyline2 view, int color) {
    view.setColor(color);
  }

  @ReactProp(name = "geodesic", defaultBoolean = false)
  public void setGeodesic(AirMapPolyline2 view, boolean geodesic) {
    view.setGeodesic(geodesic);
  }

  @ReactProp(name = "zIndex", defaultFloat = 1.0f)
  public void setZIndex(AirMapPolyline2 view, float zIndex) {
    view.setZIndex(zIndex);
  }

  @ReactProp(name = "lineCap")
  public void setlineCap(AirMapPolyline2 view, String lineCap) {
    Cap cap = null;
    switch (lineCap) {
      case "butt":
        cap = new ButtCap();
        break;
      case "round":
        cap = new RoundCap();
        break;
      case "square":
        cap = new SquareCap();
        break;
      default:
        cap = new RoundCap();
        break;
    }
    view.setLineCap(cap);
  }

  @Override
  @Nullable
  public Map getExportedCustomDirectEventTypeConstants() {
    return MapBuilder.of(
        "onPress", MapBuilder.of("registrationName", "onPress")
    );
  }
}
