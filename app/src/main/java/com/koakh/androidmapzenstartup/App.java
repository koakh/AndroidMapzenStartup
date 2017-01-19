package com.koakh.androidmapzenstartup;

import android.app.Application;

import com.mapzen.android.graphics.MapzenMap;

/**
 * Created by mario on 18/01/2017.
 */
public class App extends Application {

  public final static String TAG = "MapZenStartup";

  // MapZen
  private MapzenMap mMapzenMap;
  // Permission Requests
  private boolean mPermissionGrantedAccessFineLocation = false;

  @Override
  public void onCreate() {
    super.onCreate();
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
