package com.airbnb.android.react.maps;

import android.app.Activity;

import com.airbnb.android.react.maps.osmdroid.OsmMapCalloutManager;
import com.airbnb.android.react.maps.osmdroid.OsmMapCircleManager;
import com.airbnb.android.react.maps.osmdroid.OsmMapManager;
import com.airbnb.android.react.maps.osmdroid.OsmMapMarkerManager;
import com.airbnb.android.react.maps.osmdroid.OsmMapPolygonManager;
import com.airbnb.android.react.maps.osmdroid.OsmMapPolylineManager;
import com.airbnb.android.react.maps.osmdroid.OsmMapUrlTileManager;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MapsPackage2 implements ReactPackage {
  public MapsPackage2(Activity activity) {
  } // backwards compatibility

  public MapsPackage2() {
  }

  @Override
  public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
    return Arrays.<NativeModule>asList(new AirMapModule2(reactContext));
  }

  // Deprecated RN 0.47
  public List<Class<? extends JavaScriptModule>> createJSModules() {
    return Collections.emptyList();
  }

  @Override
  public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {

    List<ViewManager> managers = new ArrayList<>();

    if (hasGoogleMapsOnClasspath()) {
      AirMapCallout2Manager calloutManager = new AirMapCallout2Manager();
      AirMapMarker2Manager annotationManager = new AirMapMarker2Manager();
      AirMapPolyline2Manager polylineManager = new AirMapPolyline2Manager(reactContext);
      AirMapPolygon2Manager polygonManager = new AirMapPolygon2Manager(reactContext);
      AirMapCircle2Manager2 circleManager = new AirMapCircle2Manager2(reactContext);
      AirMapManager2 mapManager = new AirMapManager2(reactContext);
      AirMapLiteManager2 mapLiteManager = new AirMapLiteManager2(reactContext);
      AirMapUrlTile2Manager urlTileManager = new AirMapUrlTile2Manager(reactContext);
      AirMapLocalTile2Manager2 localTileManager = new AirMapLocalTile2Manager2(reactContext);
      AirMapOverlay2Manager overlayManager = new AirMapOverlay2Manager(reactContext);

      List<ViewManager> AirMapManager2s = Arrays.<ViewManager>asList(
              calloutManager,
              annotationManager,
              polylineManager,
              polygonManager,
              circleManager,
              mapManager,
              mapLiteManager,
              urlTileManager,
              localTileManager,
              overlayManager
      );
      managers.addAll(AirMapManager2s);
    }

    if (hasOsmdroidOnClasspath()) {
      OsmMapCalloutManager osmCalloutManager = new OsmMapCalloutManager();
      OsmMapMarkerManager osmMarkerManager = new OsmMapMarkerManager();
      OsmMapPolylineManager osmPolylineManager = new OsmMapPolylineManager(reactContext);
      OsmMapPolygonManager osmPolygonManager = new OsmMapPolygonManager(reactContext);
      OsmMapCircleManager osmMapCircleManager = new OsmMapCircleManager(reactContext);
      OsmMapManager osmMapManager = new OsmMapManager(reactContext);
      OsmMapUrlTileManager osmUrlTileManager = new OsmMapUrlTileManager();

      managers.addAll(Arrays.<ViewManager>asList(
          osmCalloutManager,
          osmMarkerManager,
          osmPolylineManager,
          osmPolygonManager,
          osmMapCircleManager,
          osmMapManager,
          osmUrlTileManager
      ));
    }

    return managers;
  }

  private boolean hasOsmdroidOnClasspath() {
    try {
      Class.forName("org.osmdroid.views.MapView");
      return true;
    } catch (ClassNotFoundException ex) {
      ex.printStackTrace();
    }
    return false;
  }
  private boolean hasGoogleMapsOnClasspath() {
    try {
      Class.forName("com.google.android.gms.maps.MapView");
      return true;
    } catch (ClassNotFoundException ex) {
      ex.printStackTrace();
    }
    return false;
  }
}
