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
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.findandgoapp.activity.EventoSeleccionado;
import com.findandgoapp.activity.R;
import com.findandgoapp.adapter.ListAdapterAdminEventoListado;
import com.findandgoapp.adapter.ListAdapterSugerencia;
import com.findandgoapp.library.JSONParser;
import com.findandgoapp.pojo.EventoPOJO;
import com.findandgoapp.pojo.SugerenciaPOJO;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
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
public class TabAdminEventoListado extends Fragment {


    public TabAdminEventoListado() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(getClass().getName(), "onCreateView");


        View view = inflater.inflate(R.layout.fragment_tab_admin_item_list, container, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.DATOS), Context.MODE_PRIVATE);

        ListView listView = (ListView) view.findViewById(R.id.listAdminEvento);


        int tipo = sharedPreferences.getInt("tipo", 0);

        if (tipo == 7) {
            getActivity().setTitle(R.string.Sugerencia);
            selectSugerencias(listView);
        } else {
            ListarEventoByTipo listarEventoByTipo = new ListarEventoByTipo(getActivity(), listView, tipo);
            listarEventoByTipo.execute();
        }

        return view;
    }

    private void selectSugerencias(final ListView listView) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getString(R.string.sUrl_selectSugerencia), new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                Log.e(getClass().getName(), "selectSugerencias " + s);

                final ArrayList<SugerenciaPOJO> elementos = new ArrayList<>();

                JSONArray jsonArray = new JSONArray();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    jsonArray = jsonObject.getJSONArray(getString(R.string.result));

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                assert jsonArray != null;
                for (int i = 0; i < jsonArray.length(); i++) {
                    SugerenciaPOJO sugerencia = new SugerenciaPOJO();
                    try {
                        Log.d("Array" + i, jsonArray.get(i).toString());
                        sugerencia.JSONtoSugerencia(jsonArray.getJSONObject(i));
                        elementos.add(sugerencia);

                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }


                }

                listView.setAdapter(new ListAdapterSugerencia(elementos, getActivity()));


            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Toast.makeText(getActivity(), getString(R.string.errorConexion), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return formatoSugerencia();
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    /**
     * @return
     */
    private Map<String, String> formatoSugerencia() {

        Map<String, String> map = new HashMap<>();
        map.put(getString(R.string.sIdSesion), getString(R.string.idAdmin));
        return map;

    }


    /**
     * @params String, String, JSONObject
     * @desc Obtener los eventos en función de los parámetros seleccionado por el usuario
     */
    public static class ListarEventoByTipo extends AsyncTask<String, String, JSONObject> {


        private final ProgressDialog pDialog;
        private final Context _c;
        private final int _itipo;
        private final ListView _listView;

        ListarEventoByTipo(Context c, ListView listView, int itipo) {
            _c = c;
            _listView = listView;
            _itipo = itipo;
            pDialog = new ProgressDialog(c);
        }

        //Mostramos el process Dialog antes de que comience la ejecución del hilo
        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            pDialog.setMessage(_c.getString(R.string.sValidar));
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);

        }

        @Override
        protected JSONObject doInBackground(String... args) {


            //Objeto JSONParser para enviar la dirección url, el método y el objeto
            JSONParser jsonParser = new JSONParser();
            JSONObject json = jsonParser.makeHttpRequest(_c.getString(R.string.sUrl_selectEventosByPenalizacion), _c.getString(R.string.sGET), toArray(_itipo));

            Log.d(getClass().getName(), "ListarEventoByTipo\n " + json.toString());

            return json;

        }

        /**
         * Tras la ejecución, se eliminan los diálogos
         * *
         */
        protected void onPostExecute(JSONObject jsonObject) {


            ArrayList<EventoPOJO> elementos = new ArrayList<>();


            JSONArray array = null;

            Log.e(getClass().getName(), "POST\n" + jsonObject.toString());

            try {
                array = new JSONArray(jsonObject.getString("resultado"));
            } catch (JSONException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            assert array != null;
            for (int i = 0; i < array.length(); i++) {
                EventoPOJO tabEventoPOJO = new EventoPOJO();
                try {
                    Log.d("Array" + i, array.get(i).toString());
                    tabEventoPOJO.JSONtoEvento(array.getJSONObject(i));
                    elementos.add(tabEventoPOJO);

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


            }

            _listView.setAdapter(new ListAdapterAdminEventoListado(elementos, _c.getApplicationContext()));
            addListenerToListView(_c, _listView, array);
        }

        /**
         * @param _c
         * @param _listView
         * @param array
         */
        private void addListenerToListView(final Context _c, ListView _listView, final JSONArray array) {

            _listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                    Intent i = new Intent(_c.getApplicationContext(), EventoSeleccionado.class);

                    SharedPreferences sharedPreferences = _c.getSharedPreferences("DATOS", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();


                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

                    EventoPOJO tabEventoPOJO = new EventoPOJO();
                    JSONObject jsonObject;

                    try {
                        jsonObject = array.getJSONObject(position);
                        tabEventoPOJO.JSONtoEvento(jsonObject);
                        Log.e(getClass().getName(), tabEventoPOJO.getS_nombreEvento());
                        editor.putInt("idUsuario", tabEventoPOJO.getI_idUsuario());
                        editor.putInt("idEvento", tabEventoPOJO.getI_idEvento());
                        editor.putInt("idSesion", 1);
                        editor.putInt("estado", 0);

                        editor.apply();

                        _c.startActivity(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            });
        }

        /**
         * @return LinkedList<NameValuePair>
         */
        public LinkedList<NameValuePair> toArray(int tipo) {
            // TODO Auto-generated method stub


            LinkedList<NameValuePair> linkedList = new LinkedList<>();

            linkedList.add(new BasicNameValuePair("tipo", String.valueOf(tipo)));

            Log.d(getClass().getName(), linkedList.toString());
            return linkedList;
        }

    }
}

