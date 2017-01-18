package com.koakh.androidmapzenstartup;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mapzen.android.graphics.MapFragment;
import com.mapzen.android.graphics.MapzenMap;
import com.mapzen.android.graphics.OnMapReadyCallback;
import com.mapzen.android.graphics.model.CameraType;
import com.mapzen.tangram.LngLat;
import com.mapzen.tangram.MapView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapzenFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapzenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapzenFragment extends Fragment {
  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  private OnFragmentInteractionListener mListener;

  public MapzenFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment MapzenFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static MapzenFragment newInstance(String param1, String param2) {
    MapzenFragment fragment = new MapzenFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    //return inflater.inflate(R.layout.fragment_map, container, false);

    View rootView = inflater.inflate(R.layout.fragment_map, container, false);

    //getSupportFragmentManager().findFragmentById returns null for google maps in fragment in android?
    //MapFragment mapFragment = (MapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map_fragment);
    MapFragment mapFragment = (MapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment);
    mapFragment.getMapAsync(new OnMapReadyCallback() {
      @Override
      public void onMapReady(MapzenMap map) {
        map.setPosition(new LngLat(-73.9903, 40.74433));
        map.setRotation(0f);
        map.setZoom(17f);
        map.setTilt(0f);
        map.setCameraType(CameraType.ISOMETRIC);
        map.setMyLocationEnabled(true);
      }
    });

    return rootView;
  }

  // TODO: Rename method, update argument and hook method into UI event
  public void onButtonPressed(Uri uri) {
    if (mListener != null) {
      mListener.onFragmentInteraction(uri);
    }
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnFragmentInteractionListener) {
      mListener = (OnFragmentInteractionListener) context;
    } else {
      throw new RuntimeException(context.toString()
          + " must implement OnFragmentInteractionListener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  /**
   * This interface must be implemented by activities that contain this
   * fragment to allow an interaction in this fragment to be communicated
   * to the activity and potentially other fragments contained in that
   * activity.
   * <p>
   * See the Android Training lesson <a href=
   * "http://developer.android.com/training/basics/fragments/communicating.html"
   * >Communicating with Other Fragments</a> for more information.
   */
  public interface OnFragmentInteractionListener {
    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
  }
}
