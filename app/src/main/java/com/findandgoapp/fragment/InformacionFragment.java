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
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import com.findandgoapp.activity.Alusiones;
import com.findandgoapp.activity.Notificaciones;
import com.findandgoapp.activity.R;
import com.findandgoapp.activity.SancionRecibida;
import com.findandgoapp.adapter.ListAdapterInformacion;
import com.findandgoapp.pojo.NotificacionPOJO;
import com.findandgoapp.tools.Utilidades;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.Map;

/**
 * A placeholder fragment containing a simple view.
 */
public class InformacionFragment extends Fragment {

    private static NotificacionPOJO notificacionPOJO;
    private static int idSesion;
    private final String TAG = "InformacionFrg";

    public InformacionFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_informacion_item_list, container, false);

        notificacionPOJO = new NotificacionPOJO();
        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(getResources().getString(R.string.sDATOS), Context.MODE_PRIVATE);
        notificacionPOJO.setListView((ListView) rootView.findViewById(R.id.listaInformacion));
        notificacionPOJO.setIdUsuario(sharedPreferences.getInt(getActivity().getResources().getString(R.string.sIdUsuario), 0));
        notificacionPOJO.setTipo(sharedPreferences.getInt(getActivity().getResources().getString(R.string.tipoUsuario), 0));
        idSesion = sharedPreferences.getInt(getString(R.string.sIdSesion), 0);

        selectInformacionUsuario();


        FragmentManager fragmentManager = getFragmentManager();

        return rootView;

    }


    /**
     *
     *
     */
    private void selectInformacionUsuario() {


        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_selectInformacion),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        LinkedList<Integer> linkedList = new LinkedList<>();

                        try {

                            Log.e(getClass().getName(), "selectInformacionUsuario " + response);
                            JSONObject json = new JSONObject(response);

                            linkedList.add(json.getInt(getResources().getString(R.string.sevento)));
                            linkedList.add(json.getInt(getResources().getString(R.string.spenalizacion)));

                            if (notificacionPOJO.getTipo() != getResources().getInteger(R.integer.iAsistente))
                                linkedList.add(json.getInt(getResources().getString(R.string.sreferencia)));

                            Log.e(TAG, "LINK " + linkedList.toString());

                            ListAdapterInformacion listAdapterInformacion = new ListAdapterInformacion(getActivity(), linkedList, notificacionPOJO.getTipo());
                            notificacionPOJO.getListView().setAdapter(listAdapterInformacion);
                            listAdapterInformacion.notifyDataSetChanged();


                            addListenerToListView(notificacionPOJO.getListView(), linkedList);


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


                return notificacionPOJO.formatoInfo();
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);


    }

    private void addListenerToListView(final ListView listView, final LinkedList<Integer> linkedList) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Utilidades utilidades = new Utilidades();
                Intent i;

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getResources().getString(R.string.sDATOS), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putInt(getActivity().getResources().getString(R.string.sIdUsuario), notificacionPOJO.getIdUsuario());
                editor.putInt(getActivity().getResources().getString(R.string.sIdSesion), idSesion);
                editor.putString("activity", this.getClass().getSimpleName());

                Log.e(TAG, "Position " + position + " Valor: " + listView.getItemAtPosition(position));

                int valor;
                valor = linkedList.get(position);

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.addToBackStack("fragment_informacion");
                fragmentTransaction.commit();

                if (position == 0) {
                    if (valor == 0) {
                        utilidades.errorConsultaBBDD(getActivity(), getActivity().getResources().getString(R.string.sNoHayEventos));
                    } else {

                        editor.putInt(getActivity().getResources().getString(R.string.sTipoPenalizacion), 5);
                        editor.putInt(getActivity().getResources().getString(R.string.tipo), notificacionPOJO.getTipo());
                        i = new Intent(getActivity().getApplicationContext(), Notificaciones.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);


                        startActivity(i);


                    }

                }
                if (position == 1) {
                    if (valor == 0) {
                        utilidades.errorConsultaBBDD(getActivity(), getActivity().getResources().getString(R.string.sNoHaySancion));
                    } else {

                        i = new Intent(getActivity().getApplicationContext(), SancionRecibida.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);

                        startActivity(i);


                    }
                }

                if (notificacionPOJO.getTipo() != getActivity().getResources().getInteger(R.integer.iAsistente)) {


                    if (position == 2) {

                        if (valor == 0) {
                            utilidades.errorConsultaBBDD(getActivity(), getActivity().getResources().getString(R.string.sNoHayAlusiones));
                        } else {
                            i = new Intent(getActivity().getApplicationContext(), Alusiones.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);

                            startActivity(i);


                        }
                    }
                }

                editor.apply();


            }
        });

    }


}
