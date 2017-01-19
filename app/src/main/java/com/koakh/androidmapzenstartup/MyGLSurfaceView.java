package com.koakh.androidmapzenstartup;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by mario on 19/01/2017.
 */

public class MyGLSurfaceView extends GLSurfaceView {

  private final MyGLRenderer mRenderer;

  public MyGLSurfaceView(Context context){
    super(context);

    // Create an OpenGL ES 2.0 context
    setEGLContextClientVersion(2);

    mRenderer = new MyGLRenderer();

    // Set the Renderer for drawing on the GLSurfaceView
    setRenderer(mRenderer);
  }
}