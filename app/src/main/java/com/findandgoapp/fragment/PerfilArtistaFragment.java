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

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.CursorLoader;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.findandgoapp.activity.Configuracion;
import com.findandgoapp.activity.MainActivity;
import com.findandgoapp.activity.MenuPrincipal;
import com.findandgoapp.activity.R;
import com.findandgoapp.adapter.MyArrayAdapter;
import com.findandgoapp.custom.CustomFontEditText;
import com.findandgoapp.custom.CustomTypeFaceSpan;
import com.findandgoapp.library.MultiPartEntity;
import com.findandgoapp.pojo.UsuarioArtistaPOJO;
import com.findandgoapp.tools.BordesRedondos;
import com.findandgoapp.tools.Utilidades;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by juanpolea on 16/08/16.
 */
public class PerfilArtistaFragment extends Fragment {

    private SharedPreferences permissionStatus;
    private static final int REQUEST_PERMISSION_SETTING = 101;
    private static final int EXTERNAL_STORAGE_PERMISSION_CONSTANT = 100;
    private static final int SIZE_FOTO = 300;
    private ImageView iv_fotoPerfil;

    private CustomFontEditText et_nombre;
    private CustomFontEditText et_password;
    private CustomFontEditText et_email;
    private Spinner sp_ciudad;
    private Spinner sp_categoria;
    private CustomFontEditText et_descripcion;
    private CustomFontEditText et_web;
    private ImageView iv_registrar;
    private int i_idUsuario;
    private String s_password;
    private static boolean isImage = false;
    private static UsuarioArtistaPOJO usuario;
    private SharedPreferences sharedPreferences;
    private Activity activity;
    private String modo;
    private TextInputLayout tilNombre, tilEmail, tilPassword, tilDescripcion, tilWeb;
    private File destination;
    private boolean facebook;
    private static final String TIPO_USUARIO = String.valueOf(3);

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(getString(R.string.title_activity_perfil_artista));
        activity = getActivity();
        usuario = new UsuarioArtistaPOJO();
        setHasOptionsMenu(true);
        sharedPreferences = getActivity().getSharedPreferences(getResources().getString(R.string.sDATOS), Context.MODE_PRIVATE);
        usuario.setI_idUsuario(sharedPreferences.getInt(getResources().getString(R.string.sIdSesion), 0));

        usuario.setI_estado(sharedPreferences.getInt(getResources().getString(R.string.estado), 0));

        modo = sharedPreferences.getString(getString(R.string.modo), getString(R.string.svacio));
        permissionStatus = getActivity().getSharedPreferences(getString(R.string.permissionStatus), MODE_PRIVATE);
        facebook = sharedPreferences.getBoolean(getString(R.string.sfacebook), false);

        final View rootView = inflater.inflate(R.layout.fragment_usuario_artista, container, false);
        final List<Spinner> aSpinner = new LinkedList<>();


        setEt_nombre((CustomFontEditText) rootView.findViewById(R.id.idEtArtistaNombre));
        setEt_email((CustomFontEditText) rootView.findViewById(R.id.idEtArtistaEmail));
        setEt_password((CustomFontEditText) rootView.findViewById(R.id.idEtArtistaPassword));
        setSp_ciudad((Spinner) rootView.findViewById(R.id.idSpArtistaCiudad));
        setSp_categoria((Spinner) rootView.findViewById(R.id.idSpArtistaCategoria));
        setEt_descripcion((CustomFontEditText) rootView.findViewById(R.id.idEtArtistaDescripcion));
        setEt_web((CustomFontEditText) rootView.findViewById(R.id.idEtArtistaWeb));

        setTilNombre((TextInputLayout) rootView.findViewById(R.id.tilArtistaNombre));
        setTilEmail((TextInputLayout) rootView.findViewById(R.id.tilArtistaEmail));
        setTilPassword((TextInputLayout) rootView.findViewById(R.id.tilArtistaPassword));

        if (facebook) {
            Utilidades utilidades = new Utilidades();
            getEt_nombre().setText(sharedPreferences.getString(getString(R.string.nombre), getString(R.string.svacio)));
            getEt_email().setText(sharedPreferences.getString(getString(R.string.email), getString(R.string.svacio)));

            utilidades.desactivarEditText(getEt_email());
            getEt_email().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Utilidades utilidades = new Utilidades();
                    utilidades.mensajeAlertDialog(getActivity(), getActivity().getResources().getString(R.string.sCampoFacebook));
                }
            });


        } else {


            getEt_email().addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    getTilEmail().setError(getString(R.string.sCampoObligatorio));
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    getTilEmail().setError(null);
                }

                @Override
                public void afterTextChanged(Editable s) {

                    Log.e(getClass().getName(), String.valueOf(s) + " - " + Utilidades.esCorreoValido(String.valueOf(s)));
                    if (!Utilidades.esCorreoValido(String.valueOf(s))) {
                        getTilEmail().setError(getString(R.string.sErrorFormatoEmail));

                    } else {

                        getTilEmail().setErrorEnabled(false);
                    }

                }

            });
        }


        if (!modo.equals(getString(R.string.crear))) {

            usuario.setS_password(sharedPreferences.getString(getResources().getString(R.string.password), null));
            setIv_fotoPerfil((ImageView) rootView.findViewById(R.id.ivArtistaFoto));
            getIv_fotoPerfil().setVisibility(View.VISIBLE);
            Utilidades utilidades = new Utilidades(getActivity());

            ImageDownloadTask imageDownloadTask = new ImageDownloadTask(getActivity(), String.valueOf(usuario.getI_idUsuario()), getIv_fotoPerfil());
            imageDownloadTask.execute();

            getIv_fotoPerfil().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                        //RUNTIME PERMISSION Android M
                        Utilidades utilidades = new Utilidades();


                        if (utilidades.isExternalStorageAvailable()) {
                            if (Build.VERSION.SDK_INT >= 23) {
                                requestPermission();
                            } else
                                selectImage();
                        } else {
                            Toast.makeText(getActivity(), getString(R.string.noRealizaFoto), Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Log.e(getClass().getName(), "Error Mounted " + Environment.MEDIA_MOUNTED + Environment.getExternalStorageState());
                    }
                }
            });

            rellenarFichaArtista();
            utilidades.desactivarEditText(getEt_email());

            getEt_email().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Utilidades utilidades = new Utilidades();
                    utilidades.mensajeAlertDialog(getActivity(), getActivity().getResources().getString(R.string.sCampoNoModificable) + "\n" +
                            getActivity().getResources().getString(R.string.sEmailAdminstrador));
                }
            });
        }


        setSp_ciudad((Spinner) rootView.findViewById(R.id.idSpArtistaCategoria));
        final MyArrayAdapter arrAdapterCategoria = new MyArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, getActivity().getResources().getStringArray(R.array.categoria));
        getSp_ciudad().setAdapter(arrAdapterCategoria);

        setSp_ciudad((Spinner) rootView.findViewById(R.id.idSpArtistaCiudad));
        final MyArrayAdapter arrAdapterCiudad = new MyArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, getActivity().getResources().getStringArray(R.array.ciudades));
        getSp_ciudad().setAdapter(arrAdapterCiudad);

        aSpinner.add(getSp_ciudad());
        aSpinner.add(getSp_categoria());

        setIv_registrar((ImageView) rootView.findViewById(R.id.idBArtistaRegistrar));


        getEt_nombre().addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                getEt_nombre().setError(getString(R.string.sCampoObligatorio));

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                getTilNombre().setError(null);
                getEt_nombre().setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

                getEt_nombre().setError(null);

            }

        });


        getEt_password().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                getTilPassword().setError(getString(R.string.sCampoObligatorio));

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                getTilPassword().setError(getString(R.string.sCampoObligatorio));
            }

            @Override
            public void afterTextChanged(Editable s) {


                getTilPassword().setError(null);
            }

        });


        getIv_registrar().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilidades ut = new Utilidades();


                boolean nombre = true;
                boolean email = true;
                boolean password = true;

                if (getEt_nombre().getText().toString().isEmpty()) {
                    getTilNombre().setError(getString(R.string.sCampoObligatorio));
                    nombre = false;
                }
                if (getEt_email().getText().toString().isEmpty()) {
                    getTilEmail().setError(getString(R.string.sCampoObligatorio));
                    email = false;
                } else if (!ut.compruebaEmail(getActivity().getApplicationContext(), getEt_email().getText().toString())) {
                    getTilEmail().setError(getString(R.string.sErrorFormatoEmail));
                    email = false;

                }


                if (getEt_password().getText().toString().isEmpty()) {
                    getTilPassword().setError(getString(R.string.sCampoObligatorio));
                    password = false;
                }


                if (nombre && email && password) {
                    if (!(ut.compruebaSpinner(getActivity().getApplicationContext(), aSpinner))) {

                        usuario.setS_nombre(getEt_nombre().getText().toString());
                        usuario.setS_password(getEt_password().getText().toString());
                        usuario.setS_email(getEt_email().getText().toString());
                        usuario.setS_ciudad(getSp_ciudad().getSelectedItem().toString());
                        usuario.setS_descripcion(getEt_descripcion().getText().toString());
                        usuario.setS_web(getEt_web().getText().toString());
                        usuario.setS_categoria(String.valueOf(getSp_categoria().getSelectedItemPosition()));


                        if (!modo.equalsIgnoreCase(getString(R.string.crear))) {
                            updateArtista(usuario);
                            Log.e(getClass().getName(), "update");
                        } else {
                            crearAsistente(usuario);
                            Log.e(getClass().getName(), "crear");
                        }

                    }
                } else {
                    Toast.makeText(getActivity(), getString(R.string.compruebeCampos), Toast.LENGTH_SHORT).show();
                }

            }


        });

        return rootView;
    }

    /**
     * @param usuario
     */
    private void crearAsistente(final UsuarioArtistaPOJO usuario) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_insertAsistente),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONObject json = new JSONObject(response);


                            Log.e(getClass().getName(), "crearAsistente " + response);

                            if (json.getInt(getString(R.string.success)) == 1) {


                                crearArtista(usuario);

                            } else if (json.getInt(getString(R.string.success)) == 6)
                                Toast.makeText(getActivity(), getString(R.string.usuarioYaExiste), Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(getActivity(), getString(R.string.usuarioNocreado), Toast.LENGTH_SHORT).show();
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


                return formatoAsistente();
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);


    }

    /**
     * @return
     */
    private Map<String, String> formatoAsistente() {

        Map<String, String> params = new HashMap<>();
        params.put(getString(R.string.sIdUsuario), String.valueOf(usuario.getI_idUsuario()));
        params.put(getString(R.string.nombre), getEt_nombre().getText().toString());
        params.put(getString(R.string.password), getEt_password().getText().toString());
        params.put(getString(R.string.email), getEt_email().getText().toString());

        params.put(getString(R.string.tipoUsuario), TIPO_USUARIO);
        params.put(getString(R.string.ciudad), getSp_ciudad().getSelectedItem().toString());

        return params;
    }


    /**
     * @param usuario
     */
    private void crearArtista(final UsuarioArtistaPOJO usuario) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_insertArtista),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Log.e(getClass().getName(), "crearArtista " + response);


                        try {

                            JSONObject json = new JSONObject(response);


                            if (json.getInt(getString(R.string.success)) == 1) {


                                Log.e(getClass().getName(), " Entra  -> " + json.getInt(getString(R.string.sIdUsuario)));
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putInt(activity.getString(R.string.sIdSesion), json.getInt(activity.getString(R.string.sIdUsuario)));
                                editor.putInt(activity.getString(R.string.tipoUsuario), json.getInt(getString(R.string.tipoUsuario)));
                                editor.putString(activity.getString(R.string.password), json.getString(activity.getString(R.string.password)));
                                editor.putInt(activity.getString(R.string.estado), usuario.getI_estado());
                                editor.putBoolean(getString(R.string.sfacebook), facebook);
                                editor.putInt(activity.getString(R.string.tab), 0);

                                editor.putBoolean(activity.getString(R.string.bloqueado), false);

                                editor.apply();

                                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentManager.popBackStack();
                                fragmentTransaction.commit();

                                Intent i = new Intent(activity, MenuPrincipal.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                activity.startActivity(i);
                                activity.finish();


                            } else
                                Toast.makeText(getActivity(), getString(R.string.usuarioNocreado), Toast.LENGTH_SHORT).show();

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

                return formatoArtista(usuario);
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);


    }


    /**
     * @param usuario
     */
    private void updateArtista(final UsuarioArtistaPOJO usuario) {
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_updateArtista),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        int success;


                        Log.e(getClass().getName(), "updateArtista " + response);


                        try {
                            JSONObject json = new JSONObject(response);
                            success = json.getInt(getString(R.string.success));


                            rellenarDatosArtista(json);

                            if (success == 1) {
                                Toast.makeText(getActivity(), getString(R.string.usuarioModificado), Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(getActivity(), getString(R.string.usuarioNoModificado), Toast.LENGTH_SHORT).show();


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

                return formatoArtista(usuario);

            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    /**
     * @return
     */
    private Map<String, String> formatoArtista(UsuarioArtistaPOJO usuario) {

        Map<String, String> params = new HashMap<>();

        params.put(getString(R.string.sIdUsuario), String.valueOf(usuario.getI_idUsuario()));
        params.put(getString(R.string.nombre), usuario.getS_nombre());
        params.put(getString(R.string.password), usuario.getS_password());
        params.put(getString(R.string.email), usuario.getS_email());
        params.put(getString(R.string.categoria), usuario.getS_categoria());
        params.put(getString(R.string.descripcion), usuario.getS_descripcion());
        params.put(getString(R.string.s_web), usuario.getS_web());
        params.put(getString(R.string.ciudad), usuario.getS_ciudad());

        Log.e(getClass().getName(), "formatoArtista-> " + params.toString());
        return params;
    }

    /**
     * @param jsonObject
     * @throws JSONException
     */
    private void rellenarDatosArtista(JSONObject jsonObject) throws JSONException {


        JSONObject jsonResult = jsonObject.getJSONObject(getResources().getString(R.string.sResult));

        Log.e(getClass().getName(), "rellenarDatosArtista " + jsonResult.toString());
        getEt_nombre().setText(jsonResult.getString(getResources().getString(R.string.sNombreUsuario)));
        getEt_password().setText(jsonResult.getString(getString(R.string.password)));
        getEt_email().setText(jsonResult.getString(getResources().getString(R.string.smail)));


        int seleccionado = jsonResult.getInt(getResources().getString(R.string.sidCategoria));
        getSp_categoria().setSelection(seleccionado);


        seleccionado = Integer.parseInt(jsonResult.getString(getString(R.string.sidCiudad)));
        getSp_ciudad().setSelection(seleccionado);

        getEt_descripcion().setText(jsonResult.getString(getResources().getString(R.string.descripcion)));
        getEt_web().setText(jsonResult.getString(getResources().getString(R.string.s_web)));

    }


    /**
     *
     */
    private void rellenarFichaArtista() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_selectAllArtista),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Log.e(getClass().getName(), "rellenarFichaArtista " + response);


                        try {
                            JSONObject json = new JSONObject(response);


                            rellenarDatosArtista(json);
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

                return usuario.formatoIdUsuario(getContext());
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }


    /**
     * @param menu
     * @param inflater
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        if (!modo.equalsIgnoreCase(getString(R.string.crear))) {

            inflater.inflate(R.menu.menu_perfil_asistente, menu);
            Log.e(getClass().getName(), "onCreateOptionsMenu1\n");
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    /**
     * @param item
     * @return
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.e(getClass().getName(), "onOptionsItemSelected");

        Intent i;
        switch (item.getItemId()) {
            case R.id.miIdEliminarAsistente:

                TextView textView = new TextView(getActivity().getApplicationContext());
                textView.setText(getActivity().getApplicationContext().getResources().getString(R.string.sWarning));
                textView.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), getActivity().getResources().getString(R.string.fontAmaticRegular)));
                textView.setPadding(15, 10, 0, 0);
                textView.setTextSize(getActivity().getResources().getDimension(R.dimen.size_15));
                textView.setTextColor(getActivity().getResources().getColor(R.color.rojo));

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setCustomTitle(textView)
                        .setMessage(getActivity().getResources().getString(R.string.sEliminarUsuario))
                        .setCancelable(false)
                        .setIcon(getResources().getDrawable(R.drawable.ic_action_alarms))
                        .setNegativeButton(getActivity().getResources().getString(R.string.sNO), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton(getActivity().getResources().getString(R.string.sAceptar), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                deleteArtista();


                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();
                alert.getWindow().getAttributes();

                Button button = new Button(getActivity());
                button = alert.getButton(DialogInterface.BUTTON_POSITIVE);
                button.setTextColor(getActivity().getResources().getColor(R.color.link));
                button.setBackgroundColor(getActivity().getResources().getColor(R.color.blanco));

                //Preparamos las fuentes personalizadas
                Typeface fontTextoBoton = Typeface.createFromAsset(getActivity().getAssets(), getActivity().getResources().getString(R.string.fontAmaticRegularBold));
                button.setTypeface(fontTextoBoton);


                Button buttonCancel = new Button(getActivity());
                buttonCancel = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
                buttonCancel.setTextColor(getActivity().getResources().getColor(R.color.blanco));
                buttonCancel.setBackgroundColor(getActivity().getResources().getColor(R.color.link));
                //Preparamos las fuentes personalizadas
                Typeface fontTextoCancel = Typeface.createFromAsset(getActivity().getAssets(), getActivity().getResources().getString(R.string.fontAmaticRegularBold));
                button.setTypeface(fontTextoCancel);

                TextView textView1 = (TextView) alert.findViewById(android.R.id.message);
                textView1.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), getActivity().getResources().getString(R.string.fontAmaticRegular)));
                textView1.setTextSize(getActivity().getResources().getDimension(R.dimen.size_10));


                return true;
            case R.id.miIdRealizarFotoAsistente:
                Utilidades utilidades = new Utilidades();

                if (utilidades.isExternalStorageAvailable()) {
                    if (Build.VERSION.SDK_INT >= 23) {
                        requestPermission();
                    } else
                        selectImage();
                } else {
                    Toast.makeText(getActivity(), getString(R.string.noRealizaFoto), Toast.LENGTH_SHORT).show();
                }
                return true;


            case R.id.action_settings:

                Log.d(getClass().getName(), "Configuracion" + String.valueOf(item.getItemId()));
                i = new Intent(getActivity(), Configuracion.class);
                startActivity(i);
                return true;

            default:

                return super.onOptionsItemSelected(item);
        }

    }

    /**
     *
     */

    private void requestPermission() {


        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                //Show Information about why you need the permission


                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_CONSTANT);

            } else if (permissionStatus.getBoolean(Manifest.permission.WRITE_EXTERNAL_STORAGE, false)) {
                //Previously Permission Request was cancelled with 'Dont Ask Again',
                // Redirect to Settings after showing Information about why you need the permission
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
                builder.setTitle(getString(R.string.permission_storage));
                builder.setMessage(getString(R.string.almacen));
                builder.setPositiveButton(getString(R.string.sSi), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts(getString(R.string.paquete), getActivity().getPackageName(), null);
                        intent.setData(uri);
                        startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
                        Toast.makeText(getActivity(), getString(R.string.menuPermisos), Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton(getString(R.string.sCancelar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            } else {
                //just request the permission
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_CONSTANT);


            }

            SharedPreferences.Editor editor = permissionStatus.edit();
            editor.putBoolean(Manifest.permission.WRITE_EXTERNAL_STORAGE, true);
            editor.apply();


        } else {
            //You already have the permission, just go ahead.
            selectImage();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        Log.e(getClass().getName(), "request " + requestCode);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case EXTERNAL_STORAGE_PERMISSION_CONSTANT: {
                if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(),
                            getResources().getString(R.string.permission_storage_success),
                            Toast.LENGTH_SHORT).show();
                    selectImage();

                } else {


                    Toast.makeText(getActivity(), getString(R.string.aceptarPermiso), Toast.LENGTH_SHORT).show();


                }

            }
        }
    }


    private void deleteArtista() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_deleteUsuario),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Log.e(getClass().getName(), "deleteArtista " + response);


                        try {

                            JSONObject json = new JSONObject(response);
                            int success;
                            success = json.getInt(getString(R.string.success));

                            if (success == 1) {
                                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getResources().getString(R.string.sDATOS), Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.clear();
                                editor.apply();
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

                                startActivity(intent);
                            } else
                                Toast.makeText(getActivity(), getString(R.string.NoEliminaUsuario), Toast.LENGTH_SHORT).show();
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

                return usuario.formatoIdUsuario(getActivity());
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }

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

    /**
     *
     */
    private void selectImage() {

        final CharSequence[] items = {getActivity().getResources().getString(R.string.sRealizarFoto),
                getActivity().getResources().getString(R.string.sSeleccionarGaleria),
                getActivity().getResources().getString(R.string.sCancelar)};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getActivity().getResources().getString(R.string.sAddFoto));
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals(getActivity().getResources().getString(R.string.sRealizarFoto))) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, getActivity().getResources().getInteger(R.integer.REQUEST_CAMERA));
                } else if (items[item].equals(getActivity().getResources().getString(R.string.sSeleccionarGaleria))) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, getActivity().getResources().getString(R.string.sSelecionarArchivo)),
                            getActivity().getResources().getInteger(R.integer.SELECT_FILE));
                } else if (items[item].equals(getActivity().getResources().getString(R.string.sCancelar))) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    /*
    * @param requestCode
    * @param resultCode
    * @param data
    */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView imageview = (ImageView) getActivity().findViewById(R.id.ivArtistaFoto);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        Bitmap thumbnail;

        if (resultCode == -1) {
            if (requestCode == 137) {

                thumbnail = (Bitmap) data.getExtras().get("data");
                assert thumbnail != null;
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

                if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                    //RUNTIME PERMISSION Android M

                    destination = new File(getActivity().getApplicationContext().getExternalFilesDir(android.os.Environment.DIRECTORY_DCIM), usuario.getI_idUsuario() + getActivity().getResources().getString(R.string.sFormatoPerfil));
                    try {
                        destination.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }


                FileOutputStream fo;


                try {
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                BitmapFactory.Options options = new BitmapFactory.Options();


                int scale = 1;
                while (options.outWidth / scale / 2 >= SIZE_FOTO
                        && options.outHeight / scale / 2 >= SIZE_FOTO)
                    scale *= 2;
                options.inSampleSize = scale;

                Bitmap thumb = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(destination.getPath(), options), SIZE_FOTO, SIZE_FOTO, false);

                BordesRedondos bordesRedondos = new BordesRedondos(SIZE_FOTO, 0);


                imageview.setImageBitmap(bordesRedondos.transform(thumb));


            } else if (requestCode == getActivity().getResources().getInteger(R.integer.SELECT_FILE)) {


                Uri imageUri = data.getData();
                String[] projection = {MediaStore.Images.Media.DATA};
                String selectedImagePath;

                CursorLoader cursorLoader = new CursorLoader(
                        getActivity(),
                        imageUri, projection, null, null, null);
                Cursor cursor = cursorLoader.loadInBackground();

                if (cursor != null) {
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    selectedImagePath = cursor.getString(column_index);
                    BitmapFactory.Options options = new BitmapFactory.Options();

                    int scale = 1;
                    while (options.outWidth / scale / 2 >= SIZE_FOTO
                            && options.outHeight / scale / 2 >= SIZE_FOTO)
                        scale *= 2;
                    options.inSampleSize = scale;

                    Bitmap thumb = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(selectedImagePath, options), SIZE_FOTO, SIZE_FOTO, false);

                    BordesRedondos bordesRedondos = new BordesRedondos(SIZE_FOTO, 0);
                    imageview.setImageBitmap(bordesRedondos.transform(thumb));
                    destination = new File(selectedImagePath);

                }

            }
            try {
                Utilidades utilidades = new Utilidades();
                if (utilidades.isExternalStorageAvailable())
                    upload();
                else {
                    Log.e(getClass().getName(), "Error al cargar la imagen");
                    utilidades.mensajeAlertDialog(getActivity().getApplicationContext(), getActivity().getResources().getString(R.string.sNoImagenDownload));
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     *
     */
    private void upload() {
        // Image location URL
        Log.e(getClass().getName(), "path " + destination.getPath());

        try {
            InputStream is = new FileInputStream(destination);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bao);

            BitmapFactory.Options options = new BitmapFactory.Options();

            int scale = 1;
            while (options.outWidth / scale / 2 >= 150
                    && options.outHeight / scale / 2 >= 150)
                scale *= 2;
            options.inSampleSize = scale;

            //Bitmap thumb = Bitmap.createScaledBitmap (BitmapFactory.decodeFile(selectedImagePath, options), 96, 96, false);

            UploadImage uploadImage = new UploadImage(Bitmap.createScaledBitmap(BitmapFactory.decodeFile(destination.getPath(), options), 96, 96, false));
            uploadImage.execute();


        } catch (FileNotFoundException e) {
            Log.e(getClass().getName(), e.getMessage());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(getActivity().getResources().getString(R.string.sMessage),
                getActivity().getResources().getString(R.string.sRutaImagenes));

    }


    /**
     *
     */
    public class UploadImage extends AsyncTask<Void, Void, Void> {


        private final Bitmap bitmaps;
        private ProgressDialog progressDialog;

        public UploadImage(Bitmap bitmaps) {
            this.bitmaps = bitmaps;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.show();

        }


        @Override
        protected Void doInBackground(Void... params) {


            Bitmap bitmap = bitmaps;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream); // convertir Bitmap to ByteArrayOutputStream
            InputStream in = new ByteArrayInputStream(stream.toByteArray()); // convertirr ByteArrayOutputStream to ByteArrayInputStream

            DefaultHttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(
                    getActivity().getResources().getString(R.string.sUrl_saveToFile));

            MultiPartEntity reqEntity = new MultiPartEntity();
            reqEntity.addPart(getActivity().getResources().getString(R.string.sImagenes), usuario.getI_idUsuario() + getActivity().getResources().getString(R.string.sFormatoPerfil), in);
            Log.e(getClass().getName(), reqEntity.toString());
            httppost.setEntity(reqEntity);

            Log.e(getClass().getName(), "request " + httppost.getRequestLine());

            HttpResponse response = null;
            try {
                response = httpclient.execute(httppost);
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                if (response != null) {

                    InputStream is = response.getEntity().getContent();
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(is));
                    StringBuilder str = new StringBuilder();

                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        str.append(line).append("\n");
                    }

                    Log.e(getClass().getName(), "uploadFile Path is : " + destination);

                }
                Log.e(getClass().getName(), "response " + (response != null ? response.getStatusLine().toString() : null));
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            Log.e(getClass().getName(), "UploadImage");

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            progressDialog.dismiss();
            Log.e(getClass().getName(), "onPost");
            Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.uploaded), Toast.LENGTH_LONG).show();
        }
    }

    /**
     *
     */
    public static class ImageDownloadTask extends AsyncTask<String, Void, Boolean> {

        private final String fileURL;
        private final ImageView imageView;
        private final Context _context;
        private String newFile;


        /**
         *
         */
        public ImageDownloadTask(final Context context, final String imageURL, final ImageView imageView) {
            this.fileURL = imageURL;
            this.imageView = imageView;
            this._context = context;


        }

        @Override
        protected Boolean doInBackground(final String... args) {

            HttpURLConnection con = null;
            Boolean estado = false;

            URL myFileUrl = null;
            try {
                HttpURLConnection.setFollowRedirects(false);
                newFile = _context.getString(R.string.sRutaImagenes) + fileURL + _context.getString(R.string.sFormatoPerfil);
                con = (HttpURLConnection) new URL(newFile).openConnection();
                con.setRequestMethod("HEAD");

                if ((con.getResponseCode() == HttpURLConnection.HTTP_OK)) {


                    if (con.getURL().getFile().equalsIgnoreCase(_context.getString(R.string.upload) + fileURL + _context.getString(R.string.sFormatoPerfil))) {
                        {


                            estado = true;
                        }
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                assert con != null;
                con.disconnect();
            }


            return estado;
        }


        @Override
        protected void onPostExecute(final Boolean result) {


            if (result)
                launchPicasso(_context, result, this.imageView, newFile);
            else {
                imageView.setImageResource(R.drawable.icono_artista);
            }
            Log.e(getClass().getName(), "\nTermina Image: " + fileURL);


        }

    }

    /**
     * @param context
     * @param isImage
     * @param image
     * @param file
     */
    private static void launchPicasso(Context context, Boolean isImage, ImageView image, String file) {


        image.setVisibility(View.VISIBLE);
        if (isImage) {

            Picasso.
                    with(context).
                    load(file)
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .transform(new BordesRedondos(SIZE_FOTO, 0))
                    .resize((int) context.getResources().getDimension(R.dimen.fotoPerfil), (int) context.getResources().getDimension(R.dimen.fotoPerfil))
                    .centerInside()
                    .error(R.drawable.icono_artista)
                    .into(image);


            Utilidades utilidades = new Utilidades();
            utilidades.clearImageDiskCache(context.getApplicationContext());
        } else {

            image.setImageResource(R.drawable.icono_artista);


        }

        image.setAdjustViewBounds(true);
        image.setScaleType(ImageView.ScaleType.FIT_XY);

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

    private CustomFontEditText getEt_nombre() {
        return et_nombre;
    }

    private void setEt_nombre(CustomFontEditText et_nombre) {
        this.et_nombre = et_nombre;
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

    private ImageView getIv_registrar() {
        return iv_registrar;
    }

    private void setIv_registrar(ImageView iv_registrar) {
        this.iv_registrar = iv_registrar;
    }

    private ImageView getIv_fotoPerfil() {
        return iv_fotoPerfil;
    }

    private void setIv_fotoPerfil(ImageView iv_fotoPerfil) {
        this.iv_fotoPerfil = iv_fotoPerfil;
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


    private TextInputLayout getTilEmail() {
        return tilEmail;
    }

    private void setTilEmail(TextInputLayout tilEmail) {
        this.tilEmail = tilEmail;
    }

    private TextInputLayout getTilNombre() {
        return tilNombre;
    }

    private void setTilNombre(TextInputLayout tilNombre) {
        this.tilNombre = tilNombre;
    }

    private TextInputLayout getTilPassword() {
        return tilPassword;
    }

    private void setTilPassword(TextInputLayout tilPassword) {
        this.tilPassword = tilPassword;
    }

}
