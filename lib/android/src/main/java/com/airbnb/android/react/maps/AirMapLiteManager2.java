package com.airbnb.android.react.maps;

import com.facebook.react.bridge.ReactApplicationContext;
import com.google.android.gms.maps.GoogleMapOptions;

public class AirMapLiteManager2 extends AirMapManager2 {

  private static final String REACT_CLASS = "AIRMapLite";

  @Override
  public String getName() {
    return REACT_CLASS;
  }

  public AirMapLiteManager2(ReactApplicationContext context) {
    super(context);
    this.googleMapOptions = new GoogleMapOptions().liteMode(true);
  }

}
