package com.koakh.androidmapzenstartup;

import android.app.Application;

/**
 * Created by mario on 18/01/2017.
 */
public class App extends Application{

  public final static String TAG = "MapZenStartup";
  //Location Request
  public final static int REQUEST_RESULT_OK = 1;
  public final static int REQUEST_RESULT_CANCELED = -1;
  public final static int REQUEST_CHECK_SETTINGS = 1029;

  @Override
  public void onCreate() {
    super.onCreate();
  }
}
