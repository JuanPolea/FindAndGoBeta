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
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.findandgoapp.activity.Configuracion;
import com.findandgoapp.activity.MenuPrincipal;
import com.findandgoapp.activity.R;
import com.findandgoapp.adapter.MyArrayAdapter;
import com.findandgoapp.custom.CustomFontEditText;
import com.findandgoapp.custom.CustomTypeFaceSpan;
import com.findandgoapp.pojo.UsuarioArtistaPOJO;
import com.findandgoapp.tools.Utilidades;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.Map;


/**
 * @desc Permite registrar un usuario del tipo artista
 */
public class UsuarioArtistaFragment extends Fragment {

    private static final String TAG = "UsuarioFragment";
    private static final int RESULT_OK = 0;
    private final UsuarioArtistaPOJO artistaPOJO = new UsuarioArtistaPOJO();

    private ImageView im_fotoPerfil;
    private ImageView iv_registrar;

    private EditText et_nombre;
    private EditText et_password;
    private EditText et_email;
    private EditText et_descripcion;
    private EditText et_web;

    private Spinner sp_ciudad;
    private Spinner sp_categoria;

    private Uri imageUri;
    private Bitmap bitMap;
    private ProgressDialog progressDialog;


    public UsuarioArtistaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i(TAG, "OnCreateView");

        View rootView = inflater.inflate(R.layout.fragment_usuario_artista, container, false);
        final LinkedList<String> aEditText = new LinkedList<>();
        final LinkedList<Spinner> aSpinner = new LinkedList<>();
        setElements(rootView);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getResources().getString(R.string.sDATOS), Context.MODE_PRIVATE);

        artistaPOJO.setS_email(sharedPreferences.getString(getResources().getString(R.string.sEmail), null));
        artistaPOJO.setS_nombre(sharedPreferences.getString(getResources().getString(R.string.sNombre), null));

        if (artistaPOJO.getS_email() != null)
            getEt_email().setText(artistaPOJO.getS_email());
        if (artistaPOJO.getS_nombre() != null)
            getEt_nombre().setText(artistaPOJO.getS_email());

        aEditText.add(getEt_nombre().getText().toString());
        aEditText.add(getEt_password().getText().toString());
        aEditText.add(getEt_email().getText().toString());
        aSpinner.add(getSp_ciudad());
        aSpinner.add(getSp_categoria());

        //Añadir evento onclick al botón registrar
        getIv_registrar().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                Utilidades ut = new Utilidades();
                if (!(ut.estaVacioEditText(aEditText))) {
                    if ((ut.compruebaEmail(getActivity().getApplicationContext(), getEt_email().getText().toString()))) {
                        if (!(ut.compruebaSpinner(getActivity().getApplicationContext(), aSpinner))) {

                            artistaPOJO.setS_nombre(getEt_nombre().getText().toString());
                            artistaPOJO.setS_email(getEt_email().getText().toString());
                            artistaPOJO.setS_password(getEt_password().getText().toString());
                            artistaPOJO.setS_ciudad(getSp_ciudad().getSelectedItem().toString());
                            artistaPOJO.setS_categoria(getSp_categoria().getSelectedItem().toString());
                            artistaPOJO.setS_descripcion(getEt_descripcion().getText().toString());
                            artistaPOJO.setS_web(getEt_web().getText().toString());


                            crearNuevoArtista();

                        }
                        // creamos nuevo Artista en segundo plano


                    }
                }
            }
        });

        return rootView;

    }

    /**
     * @param menu
     */
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    Log.e(getClass().getSimpleName(), "onMenuOpened...unable to set icons for overflow menu", e);
                }
            }
        }
        assert menu != null;
        for (int i = 0; i < menu.size(); i++) {
            MenuItem mi = menu.getItem(i);
            String title = mi.getTitle().toString();
            Spannable newTitle = new SpannableString(title);
            Typeface font = Typeface.createFromAsset(getActivity().getAssets(), getResources().getString(R.string.fontAmaticRegular));
            newTitle.setSpan(new CustomTypeFaceSpan("", font), 0, newTitle.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            mi.setTitle(newTitle);

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        Log.e(TAG, "onOptionsItemSelected");

        Intent i;
        switch (item.getItemId()) {
            case R.id.action_settings:
                Log.d(TAG, "Configuracion" + String.valueOf(item.getItemId()));
                i = new Intent(getActivity(), Configuracion.class);
                startActivity(i);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * @param rootView
     */
    private void setElements(View rootView) {
        Log.i(TAG, "setElements");


        setIm_fotoPerfil((ImageView) rootView.findViewById(R.id.ivArtistaFoto));

        setEt_nombre((CustomFontEditText) rootView.findViewById(R.id.idEtArtistaNombre));
        setEt_password((CustomFontEditText) rootView.findViewById(R.id.idEtArtistaPassword));
        setEt_email((CustomFontEditText) rootView.findViewById(R.id.idEtArtistaEmail));
        setEt_descripcion((CustomFontEditText) rootView.findViewById(R.id.idEtArtistaDescripcion));
        setEt_web((CustomFontEditText) rootView.findViewById(R.id.idEtArtistaWeb));
        setIv_registrar((ImageView) rootView.findViewById(R.id.idBArtistaRegistrar));
        setSp_categoria((Spinner) rootView.findViewById(R.id.idSpArtistaCategoria));
        MyArrayAdapter adapter = new MyArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, getActivity().getResources().getStringArray(R.array.categoria));
        // Aplicar el adaptador al spinner
        getSp_categoria().setAdapter(adapter);
        setSp_ciudad((Spinner) rootView.findViewById(R.id.idSpArtistaCiudad));
        MyArrayAdapter adapterCiudad = new MyArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, getActivity().getResources().getStringArray(R.array.ciudades));
        // Especificar el layout a usar cuando la lista de elecciones aparezca
        adapterCiudad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Aplicar el adaptador al spinner
        getSp_ciudad().setAdapter(adapterCiudad);

    }

    private EditText getEt_descripcion() {
        return et_descripcion;
    }

    private void setEt_descripcion(EditText et_descripcion) {
        this.et_descripcion = et_descripcion;
    }

    private EditText getEt_web() {
        return et_web;
    }

    private void setEt_web(EditText et_web) {
        this.et_web = et_web;
    }


    /**
     *
     */
    private void crearNuevoArtista() {

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.sCargando));
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_insertArtista),
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

                                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getResources().getString(R.string.DATOS), Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                try {
                                    editor.putInt(getActivity().getResources().getString(R.string.sIdSesion), Integer.parseInt(jsonObject.getString(getActivity().getResources().getString(R.string.sIdUsuario))));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                editor.putInt(getActivity().getResources().getString(R.string.tipoUsuario), artistaPOJO.getI_tipo());
                                editor.putString(getActivity().getResources().getString(R.string.password), artistaPOJO.getS_password());

                                editor.apply();
                                getActivity().startActivity(intent);

                            }
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
                        Toast.makeText(getActivity(), getString(R.string.errorConexion), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return artistaPOJO.toArray();
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

    private void setIm_fotoPerfil(ImageView im_fotoPerfil) {
        this.im_fotoPerfil = im_fotoPerfil;
    }

    private ImageView getIv_registrar() {
        return iv_registrar;
    }

    private void setIv_registrar(ImageView iv_registrar) {
        this.iv_registrar = iv_registrar;
    }

    private Spinner getSp_categoria() {
        return sp_categoria;
    }

    private void setSp_categoria(Spinner sp_categoria) {
        this.sp_categoria = sp_categoria;
    }

    private Spinner getSp_ciudad() {
        return sp_ciudad;
    }

    private void setSp_ciudad(Spinner sp_ciudad) {
        this.sp_ciudad = sp_ciudad;
    }

}