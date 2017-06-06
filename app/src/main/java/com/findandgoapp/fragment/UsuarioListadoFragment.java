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
import com.findandgoapp.activity.AdminUsuarioPenalizar;
import com.findandgoapp.activity.R;
import com.findandgoapp.adapter.ListAdapterUsuario;
import com.findandgoapp.pojo.UsuarioPOJO;
import com.findandgoapp.tools.Utilidades;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;


/**
 * A placeholder fragment containing a simple view.
 */
public class UsuarioListadoFragment extends Fragment {

    static final UsuarioPOJO usuario = new UsuarioPOJO();
    private static final String TAG = "UsuarioListadoFragment";


    public UsuarioListadoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_usuario_listado, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DATOS", Context.MODE_PRIVATE);

        /*
      modo = 0 -> lista los usuarios buscados por nombre de usuario
      modo != 0 -> Realiza listados en función del estado y el tipo recibido
     */

        usuario.setListView((ListView) view.findViewById(R.id.listaPenalizados));


        usuario.setI_tipo(sharedPreferences.getInt("tipo", 0));

        usuario.setI_estado(sharedPreferences.getInt("estado", 0));
        Log.e(TAG, "estado " + String.valueOf(usuario.getI_estado()) + " tipo " + String.valueOf(usuario.getI_tipo()));


        usuario.setListView((ListView) view.findViewById(R.id.listaPenalizados));


        selectDatosUsuariosPenalizados();


        return view;
    }


    private void selectDatosUsuariosPenalizados() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getString(R.string.sUrl_selectUsuariosPenalizados), new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                Log.e(getClass().getName(), "selectDatosUsuariosPenalizados " + s);

                final ArrayList<UsuarioPOJO> elementos = new ArrayList<>();
                JSONArray array;
                int success;

                try {
                    JSONObject json = new JSONObject(s);
                    success = json.getInt(getResources().getString(R.string.success));
                    Log.e(getClass().getName(), "deleteAlerta " + json.getString(getString(R.string.result)));


                    Utilidades utilidades = new Utilidades();


                    if (success == 1) {


                        array = new JSONArray(json.getString("result"));


                        assert array != null;
                        Log.d(TAG, "UsPenalizado: " + array.toString());


                        for (int i = 0; i < array.length(); i++) {

                            UsuarioPOJO usuario = new UsuarioPOJO();


                            try {


                                usuario.JSONtoObject(array.getJSONObject(i));


                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }


                            elementos.add(usuario);

                        }

                        ListAdapterUsuario listAdapterUsuario = new ListAdapterUsuario(elementos, getActivity(), 1);
                        usuario.getListView().setAdapter(listAdapterUsuario);
                        addListenerToListView(getActivity(), usuario.getListView(), array);
                        listAdapterUsuario.notifyDataSetChanged();
                    } else
                        utilidades.errorConsultaBBDD(getActivity().getApplicationContext(), getActivity().getResources().getString(R.string.noExistenDatos));


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(getClass().getName(), e.getMessage());
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Log.e(getClass().getName(), volleyError.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                return usuario.formatoTipoEstado();
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    /**
     * @param listView
     * @param jsonArray
     */
    private void addListenerToListView(final Context _c, ListView listView, JSONArray jsonArray) {


        final JSONArray _jsonArray = jsonArray;


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Log.i(TAG, "$addListenerToListView.listView.setOnItemClickListener");
                Intent i = new Intent(_c.getApplicationContext(), AdminUsuarioPenalizar.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                JSONObject jsonObject = null;

                try {
                    jsonObject = _jsonArray.getJSONObject(position);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                SharedPreferences sharedPreferences = _c.getSharedPreferences(
                        _c.getApplicationContext().getResources().getString(R.string.sDATOS), Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                try {
                    assert jsonObject != null;
                    editor.putInt(_c.getApplicationContext().getResources().getString(R.string.sIdUsuario),
                            jsonObject.getInt(_c.getApplicationContext().getResources().getString(R.string.sIdUsuario)));
                    editor.putInt(_c.getApplicationContext().getResources().getString(R.string.estado),
                            jsonObject.getInt(_c.getApplicationContext().getResources().getString(R.string.estado)));
                    editor.apply();
                    _c.startActivity(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }
}
