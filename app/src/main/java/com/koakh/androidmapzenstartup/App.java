package com.koakh.androidmapzenstartup;

import android.app.Application;

import com.mapzen.android.graphics.MapzenMap;

/**
 * Created by mario on 18/01/2017.
 */
public class App extends Application {

  public final static String TAG = "MapZenStartup";

  // MapZen
  private String mMapzenApiKey;
  private MapzenMap mMapzenMap;
  // Permission Requests
  private boolean mPermissionGrantedAccessFineLocation = false;

  public static double LAT_HOME = 40.15214;
  public static double LNG_HOME = -8.8597717;

  @Override
  public void onCreate() {
    super.onCreate();

    mMapzenApiKey = getResources().getString(R.string.api_key);
  }

  public String getMapzenApiKey() {
    return mMapzenApiKey;
  }

  public MapzenMap getMapzenMap() {
    return mMapzenMap;
  }

  public void setMapzenMap(MapzenMap mapzenMap) {
    mMapzenMap = mapzenMap;
  }

  public boolean isPermissionGrantedAccessFineLocation() {
    return mPermissionGrantedAccessFineLocation;
  }

  public void setPermissionGrantedAccessFineLocation(boolean permissionGrantedAccessFineLocation) {
    mPermissionGrantedAccessFineLocation = permissionGrantedAccessFineLocation;
  }
}
