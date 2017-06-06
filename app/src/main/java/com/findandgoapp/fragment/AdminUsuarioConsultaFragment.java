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
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.findandgoapp.activity.AdminUsuarioPenalizar;
import com.findandgoapp.activity.R;
import com.findandgoapp.activity.UsuarioPorNombre;
import com.findandgoapp.custom.CustomFontEditText;
import com.findandgoapp.pojo.UsuarioPOJO;
import com.findandgoapp.tools.Utilidades;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A placeholder fragment containing a simple view.
 */
public class AdminUsuarioConsultaFragment extends Fragment {


    private UsuarioPOJO usuario;
    private EditText etNombre;
    private EditText etEmail;
    private ImageView ivEnviar;


    public AdminUsuarioConsultaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_admin_usuario_consulta, container, false);
        usuario = new UsuarioPOJO();

        setHasOptionsMenu(true);

        setEtNombre((CustomFontEditText) rootView.findViewById(R.id.idEtBuscarPorNombre));

        setEtEmail((CustomFontEditText) rootView.findViewById(R.id.idEtBuscarPorEmail));

        setIvEnviar((ImageView) rootView.findViewById(R.id.ivBuscarUsuario));

        getIvEnviar().setOnClickListener(new View.OnClickListener() {
            final Utilidades utilidades = new Utilidades();

            @Override
            public void onClick(View v) {

                Log.i(getClass().getName(), "$usuario.getIvEnviar().setOnClickListener");


                usuario.setS_nombre(getEtNombre().getText().toString());
                usuario.setS_email(getEtEmail().getText().toString());

                if (!(utilidades.editTextVacio(getEtNombre().getText().toString()))) {
                    Log.e(getClass().getName(), "Busca por nombre" + usuario.getS_nombre());
                    consultarUsuario(0, usuario.getS_nombre());
                } else if (!(utilidades.editTextVacio(getEtEmail().getText().toString()))) {
                    consultarUsuario(1, usuario.getS_email());
                    Log.e(getClass().getName(), "Busca por mail");

                }
            }
        });


        return rootView;
    }


    /**
     * @param modo
     * @param string
     */
    private void consultarUsuario(final int modo, final String string) {

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_selectUsuariobyNameOrByEmail),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Log.e(getClass().getName(), "consultarUsuario " + response);
                        int success;

                        try {
                            JSONObject json = new JSONObject(response);


                            String mensaje = null;


                            int estado = 0;


                            success = json.getInt("success");


                            if (success == 1) {
                                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DATOS", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();


                                if (modo == 0) {

                                    Intent i = new Intent(getActivity(), UsuarioPorNombre.class);
                                    editor.putString(getString(R.string.sIdUsuario), String.valueOf(json.getJSONArray(getString(R.string.sIdUsuario))));
                                    editor.putInt(getString(R.string.modo), modo);
                                    editor.apply();
                                    startActivity(i);


                                } else {


                                    Intent i = new Intent(getActivity(), AdminUsuarioPenalizar.class);
                                    editor.putInt(getString(R.string.sIdUsuario), json.getInt(getString(R.string.sIdUsuario)));
                                    editor.apply();

                                    startActivity(i);
                                }


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
                //Adding parameters to request

                return formatoEmailNombre(modo, string);
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }


    private Map<String, String> formatoEmailNombre(int modo, String string) {
        Map<String, String> map = new HashMap<>();
        if (modo == 0) {
            map.put("nombre", string);
        } else {
            map.put("email", string);
        }

        Log.e(getClass().getName(), map.toString());
        return map;
    }


    private EditText getEtEmail() {
        return etEmail;
    }

    private void setEtEmail(EditText etEmail) {
        this.etEmail = etEmail;
    }

    private EditText getEtNombre() {
        return etNombre;
    }

    private void setEtNombre(EditText etNombre) {
        this.etNombre = etNombre;
    }

    private ImageView getIvEnviar() {
        return ivEnviar;
    }

    private void setIvEnviar(ImageView ivEnviar) {
        this.ivEnviar = ivEnviar;
    }


}

