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

import android.content.Context;
import android.content.SharedPreferences;
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
import com.findandgoapp.adapter.ListAdapterPenalizacion;
import com.findandgoapp.pojo.PenalizacionPOJO;
import com.findandgoapp.tools.Utilidades;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;


/**
 * A placeholder fragment containing a simple view.
 */
public class PenalizacionListadoFragment extends Fragment {

    private static final String TAG = "PenalizacionListadoFrg";
    private static final PenalizacionPOJO penalizacionPOJO = new PenalizacionPOJO();

    public PenalizacionListadoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_penalizacion_item_list, container, false);

        Log.i(TAG, "onCreateView");

        penalizacionPOJO.setListView((ListView) rootView.findViewById(R.id.listaPenalizacion));
        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences("DATOS", Context.MODE_PRIVATE);

        penalizacionPOJO.setIdUsuario(sharedPreferences.getInt("idUsuario", 0));
        penalizacionPOJO.setiTipoPenalizacion(sharedPreferences.getInt("tipoPenalizacion", 0));

        int idUsuario = sharedPreferences.getInt("idUsuario", 0);

        //int idSeleccion = bundle.getInt("idSeleccion");


        Log.d(TAG, "OnCreate\n" + String.valueOf(idUsuario));
        selectPenalizacion();

        return rootView;
    }

    /**
     * @params String, String, JSONObject
     * @desc Obtiene los eventos creados by date o mis eventos en función del parámetro iseleccion
     */

    /**
     *
     */
    private void selectPenalizacion() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_selectPenalizacionByIdUsuario),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        JSONArray array = new JSONArray();
                        Utilidades utilidades = new Utilidades();
                        Log.e(getClass().getName(), "selectPenalizacion " + response);
                        int success;
                        ArrayList<PenalizacionPOJO> elementos = new ArrayList<>();

                        try {
                            JSONObject json = new JSONObject(response);
                            Log.d("JSON", json.toString());


                            success = json.getInt("success");


                            if (success == 1) {
                                utilidades.errorConsultaBBDD(getActivity(), getString(R.string.siPenalizaciones));


                                array = new JSONArray(json.getString("result"));


                                for (int i = 0; i < array.length(); i++) {

                                    PenalizacionPOJO penalizacionPOJO = new PenalizacionPOJO();


                                    penalizacionPOJO.JSONtoObject(array.getJSONObject(i));
                                    penalizacionPOJO.setPenalizacion(json.getString("penalizacion"));
                                    penalizacionPOJO.setUsuario(json.getString("nombreUsuario"));


                                    elementos.add(penalizacionPOJO);

                                }
                                ListAdapterPenalizacion listAdapterPenalizacion = new ListAdapterPenalizacion(elementos, getActivity().getApplicationContext());
                                penalizacionPOJO.getListView().setAdapter(listAdapterPenalizacion);

                                listAdapterPenalizacion.notifyDataSetChanged();


                            } else {
                                utilidades.errorConsultaBBDD(getActivity(), getString(R.string.noPenalizaciones));
                                getActivity().finish();
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
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                return penalizacionPOJO.formatoId();
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }


}