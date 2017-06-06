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
import android.graphics.Typeface;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Spinner;

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
import com.findandgoapp.pojo.SugerenciaPOJO;
import com.findandgoapp.pojo.UsuarioEmpresaPOJO;
import com.findandgoapp.tools.Utilidades;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static com.findandgoapp.fragment.UsuarioListadoFragment.usuario;


/**
 * @desc Permite registrar un usuario del tipo empresa
 */
public class UsuarioEmpresaFragment extends Fragment {

    private static final String TAG = "UsuarioEmpresaFragment";
    private static final int RESULT_OK = 0;
    private UsuarioEmpresaPOJO usuarioEmpresaPOJO;
    private File destination;


    private ImageView im_fotoPerfil;

    private CustomFontEditText et_nombre;
    private CustomFontEditText et_password;
    private CustomFontEditText et_email;


    private AutoCompleteTextView et_localidad;
    private CustomFontEditText et_calle;
    private CustomFontEditText et_numero;
    private CustomFontEditText et_codigoPostal;
    private CustomFontEditText et_descripcion;
    private CustomFontEditText et_web;

    private ImageView iv_registrar;

    private Spinner sp_categoria;
    private Spinner sp_tipo;
    private Spinner sp_via;
    private Spinner sp_ciudad;
    private ProgressDialog progressDialog;
    private SugerenciaPOJO sugerenciaPOJO;


    public UsuarioEmpresaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("UsuarioEMpresa", "OnCreateView");

        sugerenciaPOJO = new SugerenciaPOJO();
        View rootView = inflater.inflate(R.layout.fragment_usuario_empresa, container, false);
        usuarioEmpresaPOJO = new UsuarioEmpresaPOJO();
        //Array para controlar que los campos obligatorios estén completos
        final LinkedList<CustomFontEditText> aEditText = new LinkedList<>();
        final LinkedList<Spinner> aSpinner = new LinkedList<>();

        final int idSesion = -1;
        setElements(rootView);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getResources().getString(R.string.sDATOS), Context.MODE_PRIVATE);
        usuarioEmpresaPOJO.setS_email(sharedPreferences.getString(getResources().getString(R.string.sEmail), null));
        usuarioEmpresaPOJO.setS_nombre(sharedPreferences.getString(getResources().getString(R.string.sNombre), null));

        if (usuarioEmpresaPOJO.getS_email() != null)
            getEt_email().setText(usuarioEmpresaPOJO.getS_email());
        if (usuarioEmpresaPOJO.getS_nombre() != null)
            getEt_nombre().setText(usuarioEmpresaPOJO.getS_nombre());


        aEditText.add(getEt_nombre());
        aEditText.add(getEt_password());
        aEditText.add(getEt_email());
        aEditText.add(getEt_codigoPostal());

        aEditText.add(getEt_calle());
        aEditText.add(getEt_numero());

        aSpinner.add(getSp_categoria());
        aSpinner.add(getSp_ciudad());
        aSpinner.add(getSp_tipo());
        aSpinner.add(getSp_via());


        getSp_categoria().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (getSp_categoria().getSelectedItem().toString().equalsIgnoreCase(getResources().getString(R.string.sOtro))) {

                    sugerenciaPOJO.setIdUsuario(idSesion);
                    sugerenciaPOJO.setTipo(Integer.parseInt(getString(R.string.sugerenciaCategoria)));
                    sugerenciaPOJO.setIdSugerencia(getSp_categoria().getSelectedItemPosition());
                    sugerenciaPOJO.setTexto(getActivity().getResources().getString(R.string.sDeseaAddCategoria));
                    sugerenciaPOJO.addSugerencia(getActivity());
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        getSp_tipo().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (getSp_tipo().getSelectedItem().toString().equalsIgnoreCase(getResources().getString(R.string.sOtro))) {
                    sugerenciaPOJO.setIdUsuario(idSesion);
                    sugerenciaPOJO.setTipo(Integer.parseInt(getString(R.string.sugerenciaTipo)));
                    sugerenciaPOJO.setIdSugerencia(getSp_tipo().getSelectedItemPosition());
                    sugerenciaPOJO.setTexto(getActivity().getResources().getString(R.string.sDeseaAddTipo));
                    sugerenciaPOJO.addSugerencia(getActivity());
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getSp_via().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                if (getSp_via().getSelectedItem().toString().equalsIgnoreCase(getResources().getString(R.string.sOtro))) {

                    sugerenciaPOJO.setIdUsuario(idSesion);
                    sugerenciaPOJO.setTipo(Integer.parseInt(getString(R.string.sugerenciaVia)));
                    sugerenciaPOJO.setIdSugerencia(getSp_via().getSelectedItemPosition());
                    sugerenciaPOJO.setTexto(getActivity().getResources().getString(R.string.sDeseaAddVia));
                    sugerenciaPOJO.addSugerencia(getActivity());

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });

        getSp_ciudad().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                selectLocalidad(getSp_ciudad().getSelectedItemPosition());

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });

        getIv_registrar().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // creamos nuevo Empresa en segundo plano
                Utilidades ut = new Utilidades();

                if (!(ut.estaVacio(getActivity(), aEditText))) {
                    if ((ut.compruebaEmail(getActivity().getApplicationContext(), getEt_email().getText().toString()))) {
                        if (!(ut.compruebaSpinner(getActivity().getApplicationContext(), aSpinner))) {

                            usuarioEmpresaPOJO.setS_nombre(getEt_nombre().getText().toString());
                            usuarioEmpresaPOJO.setS_email(getEt_email().getText().toString());
                            usuarioEmpresaPOJO.setS_password(getEt_password().getText().toString());
                            usuarioEmpresaPOJO.setS_ciudad(getSp_ciudad().getSelectedItem().toString());
                            usuarioEmpresaPOJO.setS_categoria(getSp_categoria().getSelectedItem().toString());
                            usuarioEmpresaPOJO.setS_tipo(getSp_tipo().getSelectedItem().toString());
                            usuarioEmpresaPOJO.setS_descripcion(getEt_descripcion().getText().toString());
                            usuarioEmpresaPOJO.setS_web(getEt_web().getText().toString());
                            usuarioEmpresaPOJO.setS_localidad(getEt_localidad().getText().toString());
                            usuarioEmpresaPOJO.setS_via(getSp_via().getSelectedItem().toString());
                            usuarioEmpresaPOJO.setS_calle(getEt_calle().getText().toString());
                            usuarioEmpresaPOJO.setS_numero(getEt_numero().getText().toString());


                            crearEmpresaNueva();
                        }

                    }
                }
            }
        });


        return rootView;
    }

    /**
     * A partir de la provincia, devuelve las localidades de ésta.
     *
     * @param s
     */
    private void selectLocalidad(final int s) {

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.sCargando));


        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_selectLocalidad),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Log.e(getClass().getName(), "selectLocalidad " + response);
                        try {
                            JSONArray jsonArray = new JSONArray();
                            JSONObject json = new JSONObject(response);
                            ArrayList<String> array = new ArrayList<>();
                            jsonArray = json.getJSONArray(getString(R.string.result));

                            int success = json.getInt(getString(R.string.success));
                            if (success == 1) {
                                if (jsonArray.length() > 0) {

                                    for (int i = 0; i < jsonArray.length(); i++) {

                                        JSONObject jsonObject = new JSONObject();
                                        jsonObject = jsonArray.getJSONObject(i);

                                        array.add(jsonObject.getString(getString(R.string.localidad)));

                                    }

                                    if (array.size() > 0) {

                                        String[] string = new String[array.size()];
                                        string = array.toArray(string);

                                        ArrayAdapter<String> adapter = new ArrayAdapter<>
                                                (getActivity(), android.R.layout.simple_list_item_1, string);
                                        getEt_localidad().setAdapter(adapter);


                                    }
                                }
                            }


                            Log.e(getClass().getName(), array.toString());
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


                return formatoIdProvincia(s);
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);


    }

    private Map<String, String> formatoIdProvincia(int idProvincia) {
        Map<String, String> params = new HashMap<>();


        params.put(getString(R.string.sidProvincia), String.valueOf(idProvincia));

        return params;

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


    private void setElements(View rootView) {
        Log.i(TAG, "setElements");

        setIm_fotoPerfil((ImageView) rootView.findViewById(R.id.ivEmpresaFoto));
        setEt_nombre((CustomFontEditText) rootView.findViewById(R.id.idEtEmpresaNombre));
        setEt_password((CustomFontEditText) rootView.findViewById(R.id.idEtEmpresaPassword));
        setEt_email((CustomFontEditText) rootView.findViewById(R.id.idEtEmail));
        setEt_codigoPostal((CustomFontEditText) rootView.findViewById(R.id.idEtEmpresaCp));
        setEt_localidad((AutoCompleteTextView) rootView.findViewById(R.id.idEtEmpresaLocalidad));
        setEt_calle((CustomFontEditText) rootView.findViewById(R.id.idEtEmpresaCalle));
        setEt_numero((CustomFontEditText) rootView.findViewById(R.id.idEtEmpresaNumero));

        setEt_descripcion((CustomFontEditText) rootView.findViewById(R.id.idEtEmpresaDescripcion));
        setEt_web((CustomFontEditText) rootView.findViewById(R.id.idEtEmpresaWeb));
        setIv_registrar((ImageView) rootView.findViewById(R.id.idIvEmpresaRegistrar));

        //Inicializamos los spinners
        setSp_categoria((Spinner) rootView.findViewById(R.id.idSpEmpresaCategoria));
        MyArrayAdapter arrAdapter = new MyArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, getActivity().getResources().getStringArray(R.array.categoria));
        // Especificamos la etiqueta de los elementos a mostrar
        arrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Aplicamos el adapter al Spinner
        getSp_categoria().setAdapter(arrAdapter);

        setSp_tipo((Spinner) rootView.findViewById(R.id.idSpEmpresaTipo));
        MyArrayAdapter arrAdapter1 = new MyArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, getActivity().getResources().getStringArray(R.array.tipo));
        arrAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getSp_tipo().setAdapter(arrAdapter1);

        setSp_ciudad((Spinner) rootView.findViewById(R.id.idSpEmpresaCiudad));
        MyArrayAdapter arrAdapterCiudad = new MyArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, getActivity().getResources().getStringArray(R.array.ciudades));
        arrAdapterCiudad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getSp_ciudad().setAdapter(arrAdapterCiudad);

        setSp_via((Spinner) rootView.findViewById(R.id.idSpEmpresaVia));
        MyArrayAdapter arrAdapterVia = new MyArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, getActivity().getResources().getStringArray(R.array.tipoVia));
        arrAdapterVia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getSp_via().setAdapter(arrAdapterVia);
    }


    /**
     *
     */
    private void crearEmpresaNueva() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.sCargando));
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrlEmpresaNueva),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        progressDialog.dismiss();
                        try {
                            Intent i = new Intent(getActivity(), MenuPrincipal.class);

                            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getResources().getString(R.string.DATOS), Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();


                            JSONObject jsonObject = new JSONObject();

                            editor.putInt(getActivity().getResources().getString(R.string.sIdSesion), Integer.parseInt(jsonObject.getString(getString(R.string.sIdUsuario))));


                            editor.putInt(getActivity().getResources().getString(R.string.tipoUsuario), getActivity().getResources().getInteger(R.integer.iEmpresa));
                            editor.putString(getActivity().getResources().getString(R.string.password), usuarioEmpresaPOJO.getS_password());
                            editor.putBoolean(getActivity().getResources().getString(R.string.estado), true);
                            editor.apply();


                            startActivity(i);

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


                return usuario.toArray();
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    private CustomFontEditText getEt_calle() {
        return et_calle;
    }

    private void setEt_calle(CustomFontEditText et_calle) {
        this.et_calle = et_calle;
    }

    private CustomFontEditText getEt_codigoPostal() {
        return et_codigoPostal;
    }

    private void setEt_codigoPostal(CustomFontEditText et_codigoPostal) {
        this.et_codigoPostal = et_codigoPostal;
    }

    private CustomFontEditText getEt_descripcion() {
        return et_descripcion;
    }

    private void setEt_descripcion(CustomFontEditText et_descripcion) {
        this.et_descripcion = et_descripcion;
    }

    private CustomFontEditText getEt_email() {
        return et_email;
    }

    private void setEt_email(CustomFontEditText et_email) {
        this.et_email = et_email;
    }

    private AutoCompleteTextView getEt_localidad() {
        return et_localidad;
    }

    private void setEt_localidad(AutoCompleteTextView et_localidad) {
        this.et_localidad = et_localidad;
    }

    private CustomFontEditText getEt_nombre() {
        return et_nombre;
    }

    private void setEt_nombre(CustomFontEditText et_nombre) {
        this.et_nombre = et_nombre;
    }

    private CustomFontEditText getEt_numero() {
        return et_numero;
    }

    private void setEt_numero(CustomFontEditText et_numero) {
        this.et_numero = et_numero;
    }

    private CustomFontEditText getEt_password() {
        return et_password;
    }

    private void setEt_password(CustomFontEditText et_password) {
        this.et_password = et_password;
    }

    private CustomFontEditText getEt_web() {
        return et_web;
    }

    private void setEt_web(CustomFontEditText et_web) {
        this.et_web = et_web;
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

    private Spinner getSp_tipo() {
        return sp_tipo;
    }

    private void setSp_tipo(Spinner sp_tipo) {
        this.sp_tipo = sp_tipo;
    }

    private Spinner getSp_via() {
        return sp_via;
    }

    private void setSp_via(Spinner sp_via) {
        this.sp_via = sp_via;
    }

}

