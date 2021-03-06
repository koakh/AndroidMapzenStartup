package com.koakh.androidmapzenstartup;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mapzen.android.lost.api.Result;
import com.mapzen.android.search.MapzenSearch;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener,
    MapzenFragment.OnFragmentInteractionListener,
    DummyFragment.OnFragmentInteractionListener{

  //Application UI
  private App mApp;
  private DrawerLayout mDrawer;
  private GLSurfaceView mGLView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Create a GLSurfaceView instance and set it
    // as the ContentView for this Activity.
    mGLView = new MyGLSurfaceView(this);
    setContentView(mGLView);

    setContentView(R.layout.activity_main);

    setupInitialFragment(savedInstanceState);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
      }
    });

    mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    mDrawer.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    // Get Application Singleton
    mApp = (App) getApplication();

    // Call requestAppPermissons
    requestAppPermissons();
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    switch (item.getItemId()) {
      case R.id.action_settings:
        return true;
      case R.id.action_map_search:
        MapzenSearch mapzenSearch = new MapzenSearch(this, mApp.getMapzenApiKey());
        mapzenSearch.search("Figueira da Foz", App.LAT_HOME, App.LNG_HOME, new Callback<com.mapzen.pelias.gson.Result>() {
          @Override
          public void failure(RetrofitError error) {
            Log.d(App.TAG, "failure: ");
          }

          @Override
          public void success(com.mapzen.pelias.gson.Result result, Response response) {
            Log.d(App.TAG, "success: ");
          }
        });
        return true;
      default:
        super.onOptionsItemSelected(item);
    }

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_camera) {
      selectDrawerItem(item);
    } else if (id == R.id.nav_gallery) {
      selectDrawerItem(item);
    } else if (id == R.id.nav_slideshow) {

    } else if (id == R.id.nav_manage) {

    } else if (id == R.id.nav_share) {

    } else if (id == R.id.nav_send) {

    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  // In order to receive event callbacks from the fragments,
  // the activity that hosts it must implement the interface defined in the fragment class.  @Override
  public void onFragmentInteraction(Uri uri) {
    Log.d(App.TAG, String.format("onFragmentInteraction: %s", uri));
  }

  public void setupInitialFragment(Bundle savedInstanceState) {

    // Check that the activity is using the layout version with
    // the fragment_container FrameLayout
    if (findViewById(R.id.fragment_container) != null) {

      // However, if we're being restored from a previous state,
      // then we don't need to do anything and should return or else
      // we could end up with overlapping fragments.
      if (savedInstanceState != null) {
        return;
      }

      // Create a new Fragment to be placed in the activity layout
      MapzenFragment fragmentLocation = new MapzenFragment();

      // In case this activity was started with special instructions from an
      // Intent, pass the Intent's extras to the fragment as arguments
      fragmentLocation.setArguments(getIntent().getExtras());

      // Add the fragment to the 'fragment_container' FrameLayout
      getSupportFragmentManager()
          .beginTransaction()
          .add(R.id.fragment_container, fragmentLocation)
          .commit();
    }
  }

  public void selectDrawerItem(MenuItem menuItem) {
    // Create a new fragment and specify the fragment to show based on nav item clicked
    Fragment fragment = null;
    Class fragmentClass;
    switch(menuItem.getItemId()) {
      case R.id.nav_camera:
        fragmentClass = MapzenFragment.class;
        break;
      case R.id.nav_gallery:
        fragmentClass = DummyFragment.class;
        break;
      default:
        fragmentClass = MapzenFragment.class;
    }

    try {
      fragment = (Fragment) fragmentClass.newInstance();
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Insert the fragment by replacing any existing fragment
    FragmentManager fragmentManager = getSupportFragmentManager();
    fragmentManager
        .beginTransaction()
        .replace(R.id.fragment_container, fragment)
        .commit()
    ;

    // Highlight the selected item has been done by NavigationView
    menuItem.setChecked(true);
    // Set action bar title
    setTitle(menuItem.getTitle());
    // Close the navigation drawer
    mDrawer.closeDrawers();
  }

  /**
   * Request Permissions : Required for Marshmallow
   * else "gps" location provider requires ACCESS_FINE_LOCATION permission
   */
  private void requestAppPermissons() {
    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    switch (requestCode) {
      case 1: {
        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          mApp.setPermissionGrantedAccessFineLocation(true);
        } else {
          // permission denied, boo! Disable the
          // functionality that depends on this permission.
        }
        return;
      }
      // other 'case' lines to check for other
      // permissions this app might request
    }
  }
}
