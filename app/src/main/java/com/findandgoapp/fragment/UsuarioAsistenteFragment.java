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
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.findandgoapp.activity.MenuPrincipal;
import com.findandgoapp.activity.R;
import com.findandgoapp.adapter.MyArrayAdapter;
import com.findandgoapp.custom.CustomFontEditText;
import com.findandgoapp.pojo.UsuarioPOJO;
import com.findandgoapp.tools.Utilidades;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import static com.findandgoapp.fragment.UsuarioListadoFragment.usuario;


/**
 * @desc Permite registrar un usuario del tipo asistente
 */
public class UsuarioAsistenteFragment extends Fragment {

    private final static String TAG = "AsistenteFragment";

    private UsuarioPOJO asistentePOJO;

    /**
     * Atributos de fragment usuario asistente
     */

    private ImageView im_fotoPerfil;

    private EditText et_nombre;
    private EditText et_password;
    private EditText et_email;
    private Spinner sp_ciudad;
    private ImageView iv_registrar;
    private Uri imageUri;
    private Bitmap bitMap;
    private File destination;


    private TextInputLayout tilNombre, tilEmail, tilPassword, tilDescripcion, tilWeb;
    private ProgressDialog progressDialog;

    public UsuarioAsistenteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "OnCreateView");

        View rootView = inflater.inflate(R.layout.fragment_usuario_asistente, container, false);
        asistentePOJO = new UsuarioPOJO();
        //array para comprobar si los elementos EditText están vacíos
        final ArrayList<String> aEditText = new ArrayList<>();
        final ArrayList<Spinner> aSpinner = new ArrayList<>();

        setElementos(rootView);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getResources().getString(R.string.sDATOS), Context.MODE_PRIVATE);
        asistentePOJO.setS_email(sharedPreferences.getString(getResources().getString(R.string.sEmail), null));
        asistentePOJO.setS_nombre(sharedPreferences.getString(getResources().getString(R.string.sNombre), null));


        if (asistentePOJO.getS_email() != null)
            getEt_email().setText(asistentePOJO.getS_email());
        if (asistentePOJO.getS_nombre() != null)
            getEt_nombre().setText(asistentePOJO.getS_nombre());

        aEditText.add(this.getEt_nombre().getText().toString());
        aEditText.add(getEt_password().getText().toString());
        aEditText.add(getEt_email().getText().toString());
        aSpinner.add(getSp_ciudad());


        getIv_registrar().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Utilidades ut = new Utilidades();
                // creamos nuevo asistente en segundo plano

                if (!(ut.estaVacioEditText(aEditText))) {
                    if ((ut.compruebaEmail(view.getContext(), getEt_email().getText().toString()))) {
                        if ((!(ut.compruebaSpinner(getActivity(), aSpinner)))) {


                            asistentePOJO.setS_nombre(getEt_nombre().getText().toString());
                            asistentePOJO.setS_password(getEt_password().getText().toString());
                            asistentePOJO.setS_email(getEt_email().getText().toString());
                            asistentePOJO.setS_ciudad(getSp_ciudad().getSelectedItem().toString());

                            crearNuevoAsistente();
                        }

                    }
                }
            }
        });


        return rootView;
    }


    /**
     * @param rootView
     * @desc Instanciamos los elementos del fragment
     */
    private void setElementos(View rootView) {

        Log.i(TAG, "setElementos");

        setIm_fotoPerfil((ImageView) rootView.findViewById(R.id.ivAsistenteFoto));
        getIm_fotoPerfil().setVisibility(View.INVISIBLE);


        setEt_nombre((CustomFontEditText) rootView.findViewById(R.id.idEtAsistenteNombre));
        setEt_password((EditText) rootView.findViewById(R.id.idEtAsistentePassword));
        setEt_email((CustomFontEditText) rootView.findViewById(R.id.idEtAsistenteEmail));
        //Inicializar Spinner
        setSp_ciudad((Spinner) rootView.findViewById(R.id.idSpAsistenteCiudad));
        MyArrayAdapter arrAdapterCiudad = new MyArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, getActivity().getResources().getStringArray(R.array.ciudades));
        // Especificamos como Layout una lista de items simple
        arrAdapterCiudad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Aplicar Adapter. Ver com.go_out.pfc.funciones ListAdapter
        getSp_ciudad().setAdapter(arrAdapterCiudad);
        getSp_ciudad().setSelection(0);
        setIv_registrar((ImageView) rootView.findViewById(R.id.idIvAsistenteRegistrar));


    }

    private void crearNuevoAsistente() {

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.sCargando));
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_insertAsistente),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        int success;

                        String msj;
                        Utilidades utilidades = new Utilidades();

                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject();
                            msj = jsonObject.getString("mensaje");

                            success = jsonObject.getInt("success");


                            if (success == 1) {

                                Intent intent = new Intent(getActivity(), MenuPrincipal.class);

                                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getResources().getString(R.string.DATOS), Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();


                                editor.putInt(getResources().getString(R.string.sIdSesion), Integer.parseInt(jsonObject.getString(getResources().getString(R.string.sIdUsuario))));
                                Log.e(TAG, "Creata USer:" + jsonObject.getString(getResources().getString(R.string.sIdUsuario)));


                                editor.putInt(getResources().getString(R.string.tipoUsuario), usuario.getI_tipo());
                                editor.putString(getResources().getString(R.string.password), usuario.getS_password());
                                editor.apply();

                                startActivity(intent);

                            } else if (success == 6)
                                utilidades.errorConsultaBBDD(getActivity(), getActivity().getString(R.string.usuarioYaExiste));
                            else
                                utilidades.errorConsultaBBDD(getActivity(), msj);

                        } catch (JSONException e) {
                            Log.e(getClass().getName(), e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        progressDialog.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                return usuario.toNombrePasswordEmailCiudad();
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }


    private EditText getEt_email() {
        return et_email;
    }

    private void setEt_email(EditText et_email) {
        this.et_email = et_email;
    }

    private EditText getEt_nombre() {
        return et_nombre;
    }

    private void setEt_nombre(EditText et_nombre) {
        this.et_nombre = et_nombre;
    }

    private EditText getEt_password() {
        return et_password;
    }

    private void setEt_password(EditText et_password) {
        this.et_password = et_password;
    }

    private ImageView getIm_fotoPerfil() {
        return im_fotoPerfil;
    }

    private void setIm_fotoPerfil(ImageView im_fotoPerfil) {
        this.im_fotoPerfil = im_fotoPerfil;
    }

    private ImageView getIv_registrar() {
        return iv_registrar;
    }

    private void setIv_registrar(ImageView iv_registrar) {
        this.iv_registrar = iv_registrar;
    }

    private Spinner getSp_ciudad() {
        return sp_ciudad;
    }

    private void setSp_ciudad(Spinner sp_ciudad) {
        this.sp_ciudad = sp_ciudad;
    }


}




