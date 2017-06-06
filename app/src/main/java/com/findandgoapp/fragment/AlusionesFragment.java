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

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.findandgoapp.activity.EventoSeleccionado;
import com.findandgoapp.activity.R;
import com.findandgoapp.adapter.ListAdapterAlusion;
import com.findandgoapp.pojo.PenalizacionPOJO;
import com.findandgoapp.tools.Utilidades;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * A placeholder fragment containing a simple view.
 */
public class AlusionesFragment extends Fragment {

    private static int idSesion;
    private final PenalizacionPOJO penalizacionPOJO = new PenalizacionPOJO();
    private ProgressDialog progressDialog;

    public AlusionesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.e(getClass().getName(), "OnCreate");
        View rootView = inflater.inflate(R.layout.fragment_alusiones_item_list, container, false);
        FragmentActivity activity = getActivity();
        setHasOptionsMenu(true);
        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(getResources().getString(R.string.sDATOS), Context.MODE_PRIVATE);
        penalizacionPOJO.setIdUsuario(sharedPreferences.getInt(getActivity().getResources().getString(R.string.sIdUsuario), 0));
        idSesion = sharedPreferences.getInt(getResources().getString(R.string.sIdSesion), 0);


        penalizacionPOJO.setListView((ListView) rootView.findViewById(R.id.listAlusiones));


        selectAlusiones();

        return rootView;
    }

    private void selectAlusiones() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.sCargando));
        progressDialog.show();
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_selectAlusionUsuario),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        LinkedList<Integer> linkedList = new LinkedList<>();

                        progressDialog.dismiss();
                        try {
                            JSONArray array = new JSONArray();
                            Utilidades utilidades = new Utilidades();
                            Log.e(getClass().getName(), "selectAlusiones " + response);
                            JSONObject json = new JSONObject(response);
                            LinkedList linked = new LinkedList();
                            int success;

                            success = json.getInt(getString(R.string.success));

                            if (success == 1) {


                                ArrayList<PenalizacionPOJO> elementos = new ArrayList<>();


                                array = new JSONArray(json.getString("result"));
                                for (int i = 0; i < array.length(); i++) {

                                    PenalizacionPOJO penalizacionPOJO = new PenalizacionPOJO();


                                    penalizacionPOJO.JSONtoAlusion(array.getJSONObject(i));
                                    Log.e(getClass().getName(), array.getJSONObject(i).toString());
                                    linked.add(penalizacionPOJO);

                                    elementos.add(penalizacionPOJO);

                                }
                                ListAdapterAlusion listAdapterAlusion = new ListAdapterAlusion(getActivity(), elementos, idSesion);
                                penalizacionPOJO.getListView().setAdapter(listAdapterAlusion);

                                addListenertoList(penalizacionPOJO.getListView(), linked);

                                listAdapterAlusion.notifyDataSetChanged();


                            } else {

                                utilidades.errorConsultaBBDD(getActivity(), json.getString("mensaje"));

                                //activity.finish();
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
                        progressDialog.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request

                return penalizacionPOJO.formatoId();
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }


    /**
     * @param listView
     * @param linkedList
     */
    private void addListenertoList(ListView listView, final LinkedList<PenalizacionPOJO> linkedList) {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.e(getClass().getName(), String.valueOf(linkedList.get(position).getIdUsuario() + " " + linkedList.get(position).getIdEvento() + " " + idSesion));

                Intent i = new Intent(getActivity(), EventoSeleccionado.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);


                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getResources().getString(R.string.sDATOS), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putInt(getActivity().getResources().getString(R.string.sIdEvento), linkedList.get(position).getIdEvento());
                editor.putInt(getActivity().getResources().getString(R.string.sIdUsuario), linkedList.get(position).getIdUsuario());
                editor.putInt(getActivity().getResources().getString(R.string.sIdSesion), idSesion);
                editor.putString(getString(R.string.fromFragment), getString(R.string.fragment_alusiones));

                editor.putString(getActivity().getResources().getString(R.string.sactivity), this.getClass().getSimpleName());

                editor.apply();


                startActivity(i);


            }
        });
    }


}

