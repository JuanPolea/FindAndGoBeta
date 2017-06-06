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
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.findandgoapp.activity.R;
import com.findandgoapp.pojo.EnviarEmailPOJO;
import com.findandgoapp.tools.Utilidades;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.Map;

/**
 * A placeholder fragment containing a simple view.
 */
public class EnviarEmailFragment extends Fragment {

    private EnviarEmailPOJO enviarEmailPOJO;

    private EditText et_destinatario;
    private ImageView b_enviar;
    private ProgressDialog progressDialog;

    public EnviarEmailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_enviar_email, container, false);
        final LinkedList<String> linkedList = new LinkedList<>();
        setHasOptionsMenu(true);

        enviarEmailPOJO = new EnviarEmailPOJO();
        setEt_destinatario((EditText) view.findViewById(R.id.etInsertaEmail));
        linkedList.add(getEt_destinatario().getText().toString());
        setB_enviar((ImageView) view.findViewById(R.id.bEnviarEmail));
        enviarEmailPOJO.setS_remitente(getString(R.string.remitente));

        getB_enviar().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilidades utilidades = new Utilidades();

                if (!(utilidades.estaVacioEditText(linkedList))) {
                    enviarEmailPOJO.setS_destinatario(getEt_destinatario().getText().toString());

                    recuperarPassword();

                }
            }

        });

        return view;

    }


    private void recuperarPassword() {

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.sCargando));
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getString(R.string.sUrl_recuperarPassword), new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                progressDialog.dismiss();
                Log.e(getClass().getName(), "recuperar contraseña " + s);

                int success;

                try {
                    JSONObject jsonObject = new JSONObject(s);
                    success = jsonObject.getInt(getResources().getString(R.string.success));
                    Log.e(getClass().getName(), "recuperarPassword " + jsonObject.getString(getString(R.string.result)));


                    Utilidades utilidades = new Utilidades();


                    if (success == 1) {
                        Toast.makeText(getActivity(), getString(R.string.sEmailEnviado) + enviarEmailPOJO.getS_destinatario(), Toast.LENGTH_SHORT).show();
                        getActivity().finish();

                    } else {

                        utilidades.errorConsultaBBDD(getActivity(), getString(R.string.sErrorBBDD));

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(getClass().getName(), e.getMessage());
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                //Log.e(getClass().getName(), volleyError.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return enviarEmailPOJO.toArray();
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    /*********
     *
     */

    private EditText getEt_destinatario() {
        return et_destinatario;
    }

    private void setEt_destinatario(EditText et_destinatario) {
        this.et_destinatario = et_destinatario;
    }

    private ImageView getB_enviar() {
        return b_enviar;
    }

    private void setB_enviar(ImageView b_enviar) {
        this.b_enviar = b_enviar;
    }


}
