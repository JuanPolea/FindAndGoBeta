/*
 * This file is part of FindAndGoApp.
 *
 *   FindAndGoApp is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   FindAndGoApp is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * This file is part of FindAndGoApp.
 *
 *   FindAndGoApp is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   FindAndGoApp is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * This file is part of FindAndGoApp.
 *
 *   FindAndGoApp is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   FindAndGoApp is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.findandgoapp.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.findandgoapp.activity.R;
import com.findandgoapp.adapter.ListAdapterSancion;
import com.findandgoapp.pojo.PenalizacionPOJO;
import com.findandgoapp.tools.Utilidades;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SancionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SancionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SancionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private OnFragmentInteractionListener mListener;
    private Activity activity;
    private PenalizacionPOJO penalizacion;

    public SancionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        penalizacion = new PenalizacionPOJO();
        activity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sancion_item_list, container, false);

        setHasOptionsMenu(true);
        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(getResources().getString(R.string.sDATOS), Context.MODE_PRIVATE);
        penalizacion.setIdUsuario(sharedPreferences.getInt(getActivity().getResources().getString(R.string.sIdUsuario), 0));

        penalizacion.setListView((ListView) rootView.findViewById(R.id.listSanciones));


        selectSanciones();

        return rootView;
    }

    /**
     *
     */
    private void selectSanciones() {
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_selectSancionUsuario),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray();
                            Utilidades utilidades = new Utilidades();
                            Log.e(getClass().getName(), "selectSanciones " + response);
                            JSONObject json = new JSONObject(response);

                            int success;

                            success = json.getInt(getString(R.string.success));

                            if (success == 1) {


                                jsonArray = new JSONArray(json.getString("result"));


                                ArrayList<PenalizacionPOJO> elementos = new ArrayList<>();

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    PenalizacionPOJO penalizacionPOJO = new PenalizacionPOJO();
                                    penalizacionPOJO.JSONtoSancion(jsonArray.getJSONObject(i));
                                    elementos.add(penalizacionPOJO);


                                }
                                ListAdapterSancion listAdapterSancion = new ListAdapterSancion(elementos, getActivity().getApplicationContext());
                                penalizacion.getListView().setAdapter(listAdapterSancion);

                                listAdapterSancion.notifyDataSetChanged();


                            } else {

                                utilidades.errorConsultaBBDD(getActivity(), json.getString("mensaje"));

                                activity.finish();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        Log.e(getClass().getName(), error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return penalizacion.formatoId();
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
