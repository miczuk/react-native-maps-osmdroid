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

import java.util.Map;

import javax.annotation.Nullable;

public class AirMapPolygon2Manager extends ViewGroupManager<AirMapPolygon2> {
  private final DisplayMetrics metrics;

  public AirMapPolygon2Manager(ReactApplicationContext reactContext) {
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
    return "AirMapPolygon2";
  }

  @Override
  public AirMapPolygon2 createViewInstance(ThemedReactContext context) {
    return new AirMapPolygon2(context);
  }

  @ReactProp(name = "coordinates")
  public void setCoordinate(AirMapPolygon2 view, ReadableArray coordinates) {
    view.setCoordinates(coordinates);
  }

  @ReactProp(name = "holes")
  public  void setHoles(AirMapPolygon2 view, ReadableArray holes) {
    view.setHoles(holes);
  }

  @ReactProp(name = "strokeWidth", defaultFloat = 1f)
  public void setStrokeWidth(AirMapPolygon2 view, float widthInPoints) {
    float widthInScreenPx = metrics.density * widthInPoints; // done for parity with iOS
    view.setStrokeWidth(widthInScreenPx);
  }

  @ReactProp(name = "fillColor", defaultInt = Color.RED, customType = "Color")
  public void setFillColor(AirMapPolygon2 view, int color) {
    view.setFillColor(color);
  }

  @ReactProp(name = "strokeColor", defaultInt = Color.RED, customType = "Color")
  public void setStrokeColor(AirMapPolygon2 view, int color) {
    view.setStrokeColor(color);
  }

  @ReactProp(name = "tappable", defaultBoolean = false)
  public void setTappable(AirMapPolygon2 view, boolean tapabble) {
    view.setTappable(tapabble);
  }

  @ReactProp(name = "geodesic", defaultBoolean = false)
  public void setGeodesic(AirMapPolygon2 view, boolean geodesic) {
    view.setGeodesic(geodesic);
  }

  @ReactProp(name = "zIndex", defaultFloat = 1.0f)
  public void setZIndex(AirMapPolygon2 view, float zIndex) {
    view.setZIndex(zIndex);
  }

  @Override
  @Nullable
  public Map getExportedCustomDirectEventTypeConstants() {
    return MapBuilder.of(
        "onPress", MapBuilder.of("registrationName", "onPress")
    );
  }
}
