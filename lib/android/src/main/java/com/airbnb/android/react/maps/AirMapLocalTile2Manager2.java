package com.airbnb.android.react.maps;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

/**
 * Created by zavadpe on 30/11/2017.
 */
public class AirMapLocalTile2Manager2 extends ViewGroupManager<AirMapLocalTile2> {
    private DisplayMetrics metrics;

    public AirMapLocalTile2Manager2(ReactApplicationContext reactContext) {
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
        return "AirMapLocalTile2";
    }

    @Override
    public AirMapLocalTile2 createViewInstance(ThemedReactContext context) {
        return new AirMapLocalTile2(context);
    }

    @ReactProp(name = "pathTemplate")
    public void setPathTemplate(AirMapLocalTile2 view, String pathTemplate) {
        view.setPathTemplate(pathTemplate);
    }

    @ReactProp(name = "tileSize", defaultFloat = 256f)
    public void setTileSize(AirMapLocalTile2 view, float tileSize) {
        view.setTileSize(tileSize);
    }

    @ReactProp(name = "zIndex", defaultFloat = -1.0f)
    public void setZIndex(AirMapLocalTile2 view, float zIndex) {
        view.setZIndex(zIndex);
    }

}