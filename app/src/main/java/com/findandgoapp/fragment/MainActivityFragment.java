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


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.findandgoapp.activity.EnviarEmail;
import com.findandgoapp.activity.MenuPrincipal;
import com.findandgoapp.activity.R;
import com.findandgoapp.activity.TabAdmin;
import com.findandgoapp.activity.UsuarioNuevo;
import com.findandgoapp.custom.CustomFontButton;
import com.findandgoapp.custom.CustomFontEditText;
import com.findandgoapp.library.JSONParser;
import com.findandgoapp.pojo.UsuarioPOJO;
import com.findandgoapp.tools.Utilidades;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


/**
 * Actividad que lanza la aplicación.
 */
public class MainActivityFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {


    private static final int RC_SIGN_IN = 1;

    private UsuarioPOJO usuarioPOJO;
    private CallbackManager mCallbackManager;
    private AccessTokenTracker mTokenTracker;
    private ProfileTracker mProfileTracker;
    private static Activity activity;

    //elementos de fragment_main
    private CustomFontEditText etEmail;
    private EditText etPassword;
    private CustomFontButton bEnviar;
    private CustomFontButton bUsuarioNuevo;
    private CustomFontButton bOlvidoPassword;
    private TextInputLayout tilNombre;
    private TextInputLayout tilPassword;
    private CheckBox cbRecordad;


    private ImageView ivSalir;
    private int recordar;
    private ProgressDialog progressDialog;
    private boolean checked;
    private GoogleApiClient mGoogleApiClient;


    private SignInButton signInButton;

    public MainActivityFragment() {
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(getString(R.string.checked), checked);


    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            checked = savedInstanceState.getBoolean(getString(R.string.checked));
            getCbRecordad().setChecked(checked);

        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        Log.i(getClass().getName(), getString(R.string.sOnCreate));

        setActivity(getActivity());

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.CHECKED), Context.MODE_PRIVATE);

        checked = sharedPreferences.getBoolean(getString(R.string.checked), false);

        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        setmCallbackManager(CallbackManager.Factory.create());
        setupTokenTracker();
        setupProfileTracker();
        getmTokenTracker().startTracking();

        mProfileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile profile, Profile profile2) {
                updateUI();
            }
        };
        getmProfileTracker().startTracking();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage(getActivity(), this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }

    private void updateUI() {

        boolean enableButtons = AccessToken.getCurrentAccessToken() != null;

        Profile profile = Profile.getCurrentProfile();
        if (profile == null) {
            Log.e(getClass().getName(), getActivity().getResources().getString(R.string.sErrorProfileNull));
        } else if (enableButtons && profile != null) {
            Log.e(getClass().getName(), "Access Token " + AccessToken.getCurrentAccessToken().toString());
            Log.e(getClass().getName(), "TabSocial " + profile.getName());
        } else {
            Log.e(getClass().getName(), "profile.getFirstName " + profile.getFirstName());
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.DATOS), Context.MODE_PRIVATE);

        recordar = sharedPreferences.getInt(getString(R.string.recordar), 0);


        final View[] rootView = {inflater.inflate(R.layout.fragment_main, container, false)};
        usuarioPOJO = new UsuarioPOJO();
        final View fragmentContainer = rootView[0].findViewById(R.id.view_pager);
        signInButton = (SignInButton) rootView[0].findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_WIDE);


        getSignInButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupSignIn();
            }
        });


        setupLoginButton(rootView[0]);
        setupTextDetails(rootView[0]);


        try {
            @SuppressLint("PackageManagerGetSignatures") PackageInfo info = getActivity().getPackageManager().getPackageInfo(
                    "com.findandgoapp.activity",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());

            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {

        }


        getbUsuarioNuevo().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                Intent i = new Intent(getActivity(), UsuarioNuevo.class);

                startActivity(i);
            }

        });

        /**
         * Pulsa en el botón enviar
         */
        getbEnviar().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Utilidades ut = new Utilidades();

                if ((ut.compruebaEmail(getActivity(), getEtEmail().getText().toString()))) {

                    if (!getEtPassword().getText().toString().isEmpty()) {

                        usuarioPOJO.setS_email(getEtEmail().getText().toString());
                        usuarioPOJO.setS_password(getEtPassword().getText().toString());


                        if (checkConnection()) {
                            login();
                        } else {
                            wifiSettings();
                        }


                    } else {
                        getTilPassword().setError(getString(R.string.sCampoObligatorio));
                    }

                } else if (getEtEmail().getText().toString().isEmpty())
                    getTilNombre().setError(getString(R.string.sCampoObligatorio));
                else

                {
                    getTilNombre().setError(getString(R.string.sErrorFormatoEmail));
                    getTilPassword().setError(getString(R.string.sCampoObligatorio));

                }
            }

        });

        /**
         * Pulsa en el botón olvido password
         */
        getbOlvidoPassword().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), EnviarEmail.class);

                startActivity(i);
            }
        });


        /**
         * CheckBox recordar sesion
         */
        getCbRecordad().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                SharedPreferences sharedPreferences1 = getActivity().getSharedPreferences(getString(R.string.CHECKED), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences1.edit();

                if (getCbRecordad().isChecked()) {
                    checked = true;
                    editor.putString(getString(R.string.email), getEtEmail().getText().toString());
                    editor.putString(getString(R.string.password), getEtPassword().getText().toString());


                } else {
                    checked = false;


                }

                editor.putBoolean(getString(R.string.checked), checked);
                editor.apply();

            }
        });

        /*
        Salir
         */
        getIvSalir().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salir(getActivity());
            }
        });


        return rootView[0];

    }

    private boolean checkConnection() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;

    }

    /**
     *
     */
    private void wifiSettings() {

        TextView textView = new TextView(getActivity());
        textView.setText(getActivity().getResources().getString(R.string.sWarning));
        textView.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), getActivity().getResources().getString(R.string.fontAmaticRegular)));
        textView.setPadding(15, 10, 0, 0);
        textView.setTextSize(getActivity().getResources().getDimension(R.dimen.size_15));
        textView.setTextColor(ContextCompat.getColor(getActivity(), R.color.rojo));

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getActivity().getString(R.string.sAviso));

        builder.setMessage(getActivity().getString(R.string.activarWifi));

        builder.setPositiveButton(getActivity().getString(R.string.sSi), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                getActivity().startActivity(intent);
            }
        });


        builder.setNegativeButton(getActivity().getString(R.string.No), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });


        AlertDialog alert = builder.create();
        alert.show();
        alert.getWindow().getAttributes();

        Button button = new Button(getActivity());
        button = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        button.setTextColor(ContextCompat.getColor(getActivity(), R.color.blanco));
        button.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.link));

        //Preparamos las fuentes personalizadas
        Typeface fontTextoBoton = Typeface.createFromAsset(getActivity().getAssets(), getActivity().getResources().getString(R.string.fontAmaticRegularBold));
        button.setTypeface(fontTextoBoton);


        Button buttonCancel = new Button(getActivity());
        buttonCancel = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        buttonCancel.setTextColor(ContextCompat.getColor(getActivity(), R.color.link));
        buttonCancel.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.blanco));
        //Preparamos las fuentes personalizadas
        Typeface fontTextoCancel = Typeface.createFromAsset(getActivity().getAssets(), getActivity().getResources().getString(R.string.fontAmaticRegularBold));
        button.setTypeface(fontTextoCancel);

        TextView textView1 = (TextView) alert.findViewById(android.R.id.message);
        textView1.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), getActivity().getResources().getString(R.string.fontAmaticRegular)));
        textView1.setTextSize(getActivity().getResources().getDimension(R.dimen.size_10));


    }

    private void salir(final Activity activity) {


        TextView textView = new TextView(activity);
        textView.setText(activity.getResources().getString(R.string.sWarning));
        textView.setTypeface(Typeface.createFromAsset(activity.getAssets(), activity.getResources().getString(R.string.fontAmaticRegular)));
        textView.setPadding(15, 10, 0, 0);

        textView.setTextColor(ContextCompat.getColor(activity, R.color.rojo));

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setCustomTitle(textView)
                .setMessage(activity.getResources().getString(R.string.sDeseaSalir))
                .setCancelable(false)
                .setIcon(ContextCompat.getDrawable(activity, R.drawable.ic_action_alarms))
                .setNegativeButton(activity.getResources().getString(R.string.sNO), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton(activity.getResources().getString(R.string.sSi), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        SharedPreferences sharedDatos = activity.getSharedPreferences(activity.getResources().getString(R.string.DATOS), Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedDatos.edit();

                        if (!checked) {


                            editor.remove(getString(R.string.DATOS));

                            editor.apply();

                            SharedPreferences shared = activity.getSharedPreferences(activity.getResources().getString(R.string.CHECKED), Context.MODE_PRIVATE);
                            SharedPreferences.Editor editorCHecked = shared.edit();
                            editorCHecked.remove(getString(R.string.CHECKED));
                            editorCHecked.apply();


                        }


                        TabMenuAlertaFragment.setIsAlerta(true);
                        activity.finish();

                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
        alert.getWindow().getAttributes();

        Button button = new Button(activity);
        button = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        button.setTextColor(ContextCompat.getColor(activity, R.color.link));
        button.setBackgroundColor(ContextCompat.getColor(activity, R.color.blanco));

        //Preparamos las fuentes personalizadas
        Typeface fontTextoBoton = Typeface.createFromAsset(activity.getAssets(), activity.getResources().getString(R.string.fontAmaticRegularBold));
        button.setTypeface(fontTextoBoton);


        Button buttonCancel = new Button(activity);
        buttonCancel = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        buttonCancel.setTextColor(ContextCompat.getColor(activity, R.color.blanco));
        buttonCancel.setBackgroundColor(ContextCompat.getColor(activity, R.color.link));
        //Preparamos las fuentes personalizadas
        Typeface fontTextoCancel = Typeface.createFromAsset(activity.getAssets(), activity.getResources().getString(R.string.fontAmaticRegularBold));
        button.setTypeface(fontTextoCancel);

        TextView textView1 = (TextView) alert.findViewById(android.R.id.message);
        textView1.setTypeface(Typeface.createFromAsset(activity.getAssets(), activity.getResources().getString(R.string.fontAmaticRegular)));

    }

    /**
     * @param ctx
     */

    private static void cancelAllNotification(Context ctx) {
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager nMgr = (NotificationManager) ctx.getSystemService(ns);
        nMgr.cancelAll();
    }


    /**
     * Comprueba el email y la password en la url de login, permitiendo el acceso al menú principal en caso de
     * ser correctos.
     */
    private void login() {
        //Getting values from edit texts
        final String email = getEtEmail().getText().toString().trim();
        final String password = getEtPassword().getText().toString().trim();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.sCargando));
        progressDialog.show();

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrlLogin),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //If we are getting success from server
                        progressDialog.dismiss();
                        try {

                            Log.e(getClass().getName(), response);

                            JSONObject jsonObject = new JSONObject(response);

                            Utilidades utilidades = new Utilidades();

                            Log.e(getClass().getName(), jsonObject.toString());
                            if (jsonObject == null) {
                                utilidades.errorConsultaBBDD(getActivity().getApplicationContext(), getActivity().getResources().getString(R.string.sErrorBBDD));

                            } else {


                                int success;

                                success = jsonObject.getInt(getActivity().getResources().getString(R.string.success));

                                if (success == 1) {


                                    JSONObject jsonObject1;

                                    jsonObject1 = jsonObject.getJSONObject(getActivity().getResources().getString(R.string.result));

                                    assert jsonObject1 != null;
                                    usuarioPOJO.setI_idUsuario(jsonObject1.getInt(getActivity().getResources().getString(R.string.sIdUsuario)));


                                    usuarioPOJO.setS_nombre(jsonObject1.getString(getActivity().getResources().getString(R.string.nombreUsuario)));

                                    usuarioPOJO.setI_tipo(jsonObject1.getInt(getActivity().getResources().getString(R.string.tipoUsuario)));

                                    usuarioPOJO.setI_estado(jsonObject1.getInt(getString(R.string.estado)));


                                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.DATOS), Context.MODE_PRIVATE);
                                    SharedPreferences.Editor sesion = sharedPreferences.edit();


                                    sesion.putString(getString(R.string.email), usuarioPOJO.getS_email());
                                    sesion.putString(getString(R.string.password), usuarioPOJO.getS_password());


                                    sesion.putInt(getString(R.string.recordar), recordar);
                                    sesion.apply();
                                    Log.e(getClass().getName(), usuarioPOJO.getS_email());


                                    if (usuarioPOJO.getI_idUsuario() == 1) {


                                        Intent i = new Intent(getActivity().getApplicationContext(), TabAdmin.class);

                                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                                        sharedPreferences = getActivity().getSharedPreferences(getActivity().getResources().getString(R.string.DATOS), Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putInt(getActivity().getResources().getString(R.string.sIdSesion), usuarioPOJO.getI_idUsuario());
                                        editor.putInt(getActivity().getResources().getString(R.string.sIdUsuario), usuarioPOJO.getI_idUsuario());
                                        editor.putInt(getActivity().getResources().getString(R.string.estadoUsuario), 0);
                                        editor.putInt(getActivity().getResources().getString(R.string.tab), 0);
                                        getActivity().getApplicationContext().startActivity(i);
                                        editor.apply();
                                        activity.finish();

                                    } else {


                                        sharedPreferences = getActivity().getSharedPreferences(getActivity().getResources().getString(R.string.DATOS), Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();


                                        editor.putInt(getActivity().getResources().getString(R.string.sIdSesion), usuarioPOJO.getI_idUsuario());
                                        editor.putInt(getActivity().getResources().getString(R.string.tipoUsuario), usuarioPOJO.getI_tipo());
                                        editor.putString(getActivity().getResources().getString(R.string.password), usuarioPOJO.getS_password());
                                        editor.putInt(getActivity().getResources().getString(R.string.estado), usuarioPOJO.getI_estado());
                                        editor.putString(getString(R.string.nombreUsuario), usuarioPOJO.getS_nombre());
                                        editor.putInt(getActivity().getResources().getString(R.string.tab), 0);
                                        editor.putBoolean(getString(R.string.login), true);

                                        if (usuarioPOJO.getI_estado() != 0)
                                            editor.putBoolean(getActivity().getResources().getString(R.string.bloqueado), true);
                                        else
                                            editor.putBoolean(getActivity().getResources().getString(R.string.bloqueado), false);
                                        editor.apply();


                                        Intent i = new Intent(getActivity().getApplicationContext(), MenuPrincipal.class);
                                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        getActivity().getApplicationContext().startActivity(i);

                                        activity.finish();

                                    }

                                } else if (success == 2)
                                    utilidades.mensajeAlertDialog(getActivity(), getActivity().getResources().getString(R.string.sUsuarioEliminado));

                                else
                                    utilidades.errorConsultaBBDD(getActivity(), getActivity().getResources().getString(R.string.sErrorUsuarioNoEncontrado));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        error.printStackTrace();
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), getString(R.string.errorConexion), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put(getString(R.string.email), email);
                params.put("password", password);

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.i(getClass().getName(), getString(R.string.sOnViewCreated));

    }


    /**
     * Variable para el login de facebook
     */
    private final FacebookCallback<LoginResult> mFacebookCallback = new FacebookCallback<LoginResult>() {

        /**
         *
         * @param loginResult
         */
        @Override
        public void onSuccess(LoginResult loginResult) {

            final AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();

            mTokenTracker.stopTracking();
            mProfileTracker.stopTracking();


            GraphRequest request = GraphRequest.newMeRequest(
                    loginResult.getAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(final JSONObject object, GraphResponse response) {
                            // Application code

                            UsuarioPOJO usuarioPOJO = new UsuarioPOJO();

                            final EditText input = new EditText(getActivity());


                            input.setTransformationMethod(PasswordTransformationMethod.getInstance());


                            // Write your code here to execute after dialog

                            getEtEmail().setText(object.optString(getActivity().getResources().getString(R.string.email)));


                            usuarioPOJO.setS_password(input.getText().toString());
                            usuarioPOJO.setS_email(object.optString(getActivity().getResources().getString(R.string.email)));
                            String nombre;
                            nombre = object.optString(getActivity().getResources().getString(R.string.sfirst_name));
                            String apellidos;
                            apellidos = object.optString(getActivity().getResources().getString(R.string.last_name));
                            if (!apellidos.isEmpty()) {
                                nombre = nombre.concat(getString(R.string.espacio));
                                nombre = nombre.concat(apellidos);
                            }

                            usuarioPOJO.setS_nombre(nombre);
                            usuarioPOJO.setI_idFcbk(object.optLong(getActivity().getResources().getString(R.string.sId)));

                            LoginFacebook loginFacebook = new LoginFacebook(getActivity(), usuarioPOJO);
                            loginFacebook.execute();


                            Log.e(getClass().getName(), response.toString());


                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id, first_name, last_name, email");
            request.setParameters(parameters);
            request.executeAsync();


        }

        public void onCancel() {
            Log.e(getClass().getName(), getString(R.string.sOnCancel));
        }

        @Override
        public void onError(FacebookException e) {
            Log.e(getClass().getName(), getActivity().getResources().getString(R.string.sErrorFacebook) + e);
        }

    };


    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();


    }

    @Override
    public void onResume() {


        super.onResume();
        Profile profile = Profile.getCurrentProfile();


        Log.i(getClass().getName(), getString(R.string.sOnResume) + constructWelcomeMessage(profile));

    }


    @Override
    public void onStop() {
        super.onStop();
        Log.i(getClass().getName(), getString(R.string.sOnStop));
        mTokenTracker.stopTracking();
        mProfileTracker.stopTracking();
        cancelAllNotification(getActivity());
        mGoogleApiClient.disconnect();

    }

    /**
     *
     */
    private void setupSignIn() {
        Log.i(getClass().getName(), "setupLoginButton");

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(getClass().getName(), getString(R.string.sOnActivityResult));

        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }

    }

    /**
     * @param result
     */
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(getClass().getName(), "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            getEtEmail().setText(acct.getEmail());
            usuarioPOJO.setS_email(acct.getEmail());
            String nombre;
            nombre = acct.getDisplayName();

            usuarioPOJO.setS_nombre(nombre);

            loginApiExterna(usuarioPOJO);


        } else {
            // Signed out, show unauthenticated UI.
            Log.e(getClass().getName(), result.toString());

        }
    }

    /**
     * Comprueba el email y la password en la url de login, permitiendo el acceso al menú principal en caso de
     * ser correctos.
     */
    private void loginApiExterna(final UsuarioPOJO u) {
        //Getting values from edit texts

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.sCargando));
        progressDialog.show();

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrlLoginApiExterna),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //If we are getting success from server
                        progressDialog.dismiss();


                        try {
                            JSONObject jsonObject1 = new JSONObject(response);
                            int success = jsonObject1.getInt(getString(R.string.success));

                            if (success == 1) {

                                JSONObject jsonObject = new JSONObject();
                                jsonObject = jsonObject1.getJSONObject(getString(R.string.result));


                                u.setI_idUsuario(jsonObject.getInt(getString(R.string.sIdUsuario)));

                                u.setS_nombre(jsonObject.getString(getString(R.string.nombreUsuario)));

                                u.setI_tipo(jsonObject.getInt(getString(R.string.tipoUsuario)));


                                u.setI_estado(jsonObject.getInt(getString(R.string.estado)));


                                if (u.getI_idUsuario() == 1) {

                                    Log.e(getClass().getName(), "Inicializa Menú de administrador");

                                    Intent i = new Intent(getActivity(), TabAdmin.class);
                                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.DATOS), Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();

                                    editor.putInt(getString(R.string.sIdSesion), 1);
                                    editor.putInt(getString(R.string.sIdUsuario), 1);
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    editor.apply();
                                    //_c.getApplicationContext().startActivity(i);

                                } else {

                                    Log.e(getClass().getName(), "Inicializa Menú de usuarios");

                                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.DATOS), Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();

                                    editor.putInt(getString(R.string.sIdSesion), u.getI_idUsuario());
                                    editor.putInt(getString(R.string.tipoUsuario), u.getI_tipo());
                                    editor.putString(getString(R.string.password), u.getS_password());
                                    editor.putInt(getString(R.string.estado), u.getI_estado());
                                    editor.putInt(getString(R.string.tab), 0);
                                    editor.putBoolean(getString(R.string.sfacebook), true);
                                    editor.apply();

                                    Intent i = new Intent(getActivity(), MenuPrincipal.class);

                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    getActivity().startActivity(i);


                                }

                            } else if (success == 0) {

                                Intent intent = new Intent(getActivity(), UsuarioNuevo.class);
                                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.DATOS), Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putString(getString(R.string.nombre), u.getS_nombre());
                                editor.putString(getString(R.string.email), u.getS_email());
                                editor.putBoolean(getString(R.string.sfacebook), true);

                                editor.apply();


                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                activity.finish();

                            } else {
                                Utilidades utilidades = new Utilidades();
                                try {
                                    utilidades.errorConsultaBBDD(getActivity(), jsonObject1.getString(getString(R.string.mensaje)));
                                } catch (JSONException e) {
                                    e.printStackTrace();
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
                        error.printStackTrace();
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), getString(R.string.errorConexion), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put(getString(R.string.email), usuarioPOJO.getS_email());
                Log.e(getClass().getName(), "getParams " + usuarioPOJO.getS_email());

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(getClass().getName(), getString(R.string.sOnDestroy));
        mTokenTracker.stopTracking();
        mProfileTracker.stopTracking();
        LoginManager.getInstance().logOut();


    }

    /**
     * @param rootView
     * @description Inicializar los elementos del fragment
     */
    private void setupTextDetails(View rootView) {

        Log.i(getClass().getName(), "setupTextDetails");
        setEtEmail((CustomFontEditText) rootView.findViewById(R.id.idEtEmail));
        setEtPassword((EditText) rootView.findViewById(R.id.idEtPassword));
        setbEnviar((CustomFontButton) rootView.findViewById(R.id.idBEnviar));
        setbOlvidoPassword((CustomFontButton) rootView.findViewById(R.id.idBOlvidoPassword));
        setbUsuarioNuevo((CustomFontButton) rootView.findViewById(R.id.idBUsuarioNuevo));

        setIvSalir((ImageView) rootView.findViewById(R.id.idIvSalir));
        setTilNombre((TextInputLayout) rootView.findViewById(R.id.til_nombre));
        setTilPassword((TextInputLayout) rootView.findViewById(R.id.til_password));
        setCbRecordad((CheckBox) rootView.findViewById(R.id.cbRecordar));

        getCbRecordad().setChecked(checked);

        if (checked) {

            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.CHECKED), Context.MODE_PRIVATE);
            getEtEmail().setText(sharedPreferences.getString(getString(R.string.email), ""));
            getEtPassword().setText(sharedPreferences.getString(getString(R.string.password), ""));


        }


    }

    /**
     *
     */
    private void setupTokenTracker() {
        Log.i(getClass().getName(), "setupTokenTracker");
        mTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                try {
                    Log.d(getClass().getName(), "onCurrentProfileChanged->" + currentAccessToken);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Log.e(getClass().getName(), connectionResult.getErrorMessage());
        Toast.makeText(getActivity(), getString(R.string.apiNoConnect), Toast.LENGTH_SHORT).show();
    }


    /**
     * @params String, String, JSONObject
     * @desc Validación del usuario en el sistema.
     */
    public static class LoginFacebook extends AsyncTask<String, String, JSONObject> {

        final ProgressDialog _pDialog;
        private final Context _c;
        private final UsuarioPOJO _usuarioPOJO;
        private int success = 0;


        LoginFacebook(Context c, UsuarioPOJO up) {
            _c = c;
            _usuarioPOJO = new UsuarioPOJO(up.getS_email(), up.getS_password(), up.getS_nombre());
            _pDialog = new ProgressDialog(_c);
        }

        //Showing progress dialog before starting background thread
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            _pDialog.setMessage(_c.getString(R.string.sValidar));
            _pDialog.setIndeterminate(false);
            _pDialog.setCancelable(true);
            //_pDialog.show();
        }

        //El método doinBackground se encarga de realizar las acciones de parseo y activar el script de php que
        //realizará las acciones en BBDD
        @Override
        protected JSONObject doInBackground(String... args) {

            //Parseador para pasar los datos entre java y php
            JSONParser jsonParser = new JSONParser();


            JSONObject json = new JSONObject();
            try {
                json = jsonParser.makeHttpRequest(_c.getString(R.string.sUrlLoginFacebook), _c.getString(R.string.sGET), _usuarioPOJO.toArrayFacebook());
            } catch (Exception e) {
                e.printStackTrace();
            }


            return json;


        }

        // Cuando se termina la ejecución del hilo, eliminamos el progress dialog

        protected void onPostExecute(JSONObject jsonObject) {
            // dismiss the dialog once done
            //   _pDialog.dismiss();
            JSONObject jsonObject1 = new JSONObject();

            if (jsonObject == null) {

                try {
                    assert jsonObject != null;
                    jsonObject.put(_c.getResources().getString(R.string.mensaje), _c.getResources().getString(R.string.sErrorBBDD));
                    jsonObject.put(_c.getResources().getString(R.string.success), _c.getResources().getInteger(R.integer.cero));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            // observamos el resultado que nos devuelve php
            else {
                Log.e("LoginFacebook->DoinBack", jsonObject.toString());

            }


            Log.e("LoginFAcebook ", jsonObject.toString());
            try {
                success = jsonObject.getInt(_c.getResources().getString(R.string.success));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (success == 1) {

                try {
                    jsonObject1 = jsonObject.getJSONObject(_c.getResources().getString(R.string.result));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    _usuarioPOJO.setI_idUsuario(jsonObject1.getInt(_c.getResources().getString(R.string.sIdUsuario)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    _usuarioPOJO.setS_nombre(jsonObject1.getString(_c.getResources().getString(R.string.nombreUsuario)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    _usuarioPOJO.setI_tipo(jsonObject1.getInt(_c.getResources().getString(R.string.tipoUsuario)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    _usuarioPOJO.setI_estado(jsonObject1.getInt("estado"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (_usuarioPOJO.getI_idUsuario() == 1) {

                    Log.e(getClass().getName(), "Inicializa Menú de administrador");

                    Intent i = new Intent(_c.getApplicationContext(), TabAdmin.class);
                    SharedPreferences sharedPreferences = _c.getSharedPreferences(_c.getResources().getString(R.string.DATOS), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putInt(_c.getResources().getString(R.string.sIdSesion), 1);
                    editor.putInt(_c.getResources().getString(R.string.sIdUsuario), 1);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    editor.apply();
                    //_c.getApplicationContext().startActivity(i);

                } else {

                    Log.e(getClass().getName(), "Inicializa Menú de usuarios");

                    SharedPreferences sharedPreferences = _c.getSharedPreferences(_c.getResources().getString(R.string.DATOS), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putInt(_c.getResources().getString(R.string.sIdSesion), _usuarioPOJO.getI_idUsuario());
                    editor.putInt(_c.getResources().getString(R.string.tipoUsuario), _usuarioPOJO.getI_tipo());
                    editor.putString(_c.getResources().getString(R.string.password), _usuarioPOJO.getS_password());
                    editor.putInt(_c.getResources().getString(R.string.estado), _usuarioPOJO.getI_estado());
                    editor.putInt(_c.getResources().getString(R.string.tab), 0);
                    editor.putBoolean(_c.getResources().getString(R.string.sfacebook), true);
                    editor.apply();

                    Intent i = new Intent(_c.getApplicationContext(), MenuPrincipal.class);

                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    _c.getApplicationContext().startActivity(i);


                }

            } else if (success == 0) {

                Intent intent = new Intent(_c.getApplicationContext(), UsuarioNuevo.class);
                SharedPreferences sharedPreferences = _c.getSharedPreferences(_c.getResources().getString(R.string.DATOS), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(_c.getResources().getString(R.string.nombre), _usuarioPOJO.getS_nombre());
                editor.putString(_c.getResources().getString(R.string.email), _usuarioPOJO.getS_email());
                editor.putBoolean(_c.getResources().getString(R.string.sfacebook), true);

                editor.apply();


                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                _c.getApplicationContext().startActivity(intent);
                activity.finish();

            } else {
                Utilidades utilidades = new Utilidades();
                try {
                    utilidades.errorConsultaBBDD(_c.getApplicationContext(), jsonObject.getString(_c.getResources().getString(R.string.mensaje)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }

    }

    /**
     *
     */
    private void setupProfileTracker() {
        Log.i(getClass().getName(), "setupProfileTracker");
        mProfileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                try {
                    Log.d(getClass().getName(), "onCurrentProfileChanged->" + currentProfile);
                } catch (Exception e) {
                    e.printStackTrace();

                }
                //mTextDetails.setText(constructWelcomeMessage(currentProfile));
            }
        };
    }

    /**
     * @param view
     * @description Inicializar los elementos del facebook
     */
    private void setupLoginButton(View view) {
        Log.i(getClass().getName(), "setupLoginButton");
        LoginButton mButtonLogin = (LoginButton) view.findViewById(R.id.login_button);
        mButtonLogin.setText("");
        mButtonLogin.setFragment(this);
        mButtonLogin.setReadPermissions("public_profile, email,user_friends");
        mButtonLogin.registerCallback(mCallbackManager, mFacebookCallback);
    }


    private String constructWelcomeMessage(Profile profile) {

        StringBuilder stringBuffer = new StringBuilder();
        if (profile != null) {
            stringBuffer.append("Welcome ").append(profile.getName());
        }
        return stringBuffer.toString();
    }

    private TextInputLayout getTilPassword() {
        return tilPassword;
    }

    private void setTilPassword(TextInputLayout tilPassword) {
        this.tilPassword = tilPassword;
    }

    private TextInputLayout getTilNombre() {
        return tilNombre;
    }

    private void setTilNombre(TextInputLayout tilNombre) {
        this.tilNombre = tilNombre;
    }

    private ImageView getIvSalir() {
        return ivSalir;
    }

    private void setIvSalir(ImageView ivSalir) {
        this.ivSalir = ivSalir;
    }

    private CheckBox getCbRecordad() {
        return cbRecordad;
    }

    private void setCbRecordad(CheckBox cbRecordad) {
        this.cbRecordad = cbRecordad;
    }


    private void setmCallbackManager(CallbackManager mCallbackManager) {
        this.mCallbackManager = mCallbackManager;
    }

    private AccessTokenTracker getmTokenTracker() {
        return mTokenTracker;
    }


    private ProfileTracker getmProfileTracker() {
        return mProfileTracker;
    }


    private static void setActivity(Activity activity) {
        MainActivityFragment.activity = activity;
    }

    private CustomFontEditText getEtEmail() {
        return etEmail;
    }

    private void setEtEmail(CustomFontEditText etEmail) {
        this.etEmail = etEmail;
    }

    private EditText getEtPassword() {
        return etPassword;
    }

    private void setEtPassword(EditText etPassword) {
        this.etPassword = etPassword;
    }

    private CustomFontButton getbEnviar() {
        return bEnviar;
    }

    private void setbEnviar(CustomFontButton bEnviar) {
        this.bEnviar = bEnviar;
    }

    private CustomFontButton getbUsuarioNuevo() {
        return bUsuarioNuevo;
    }

    private void setbUsuarioNuevo(CustomFontButton bUsuarioNuevo) {
        this.bUsuarioNuevo = bUsuarioNuevo;
    }

    private CustomFontButton getbOlvidoPassword() {
        return bOlvidoPassword;
    }

    private void setbOlvidoPassword(CustomFontButton bOlvidoPassword) {
        this.bOlvidoPassword = bOlvidoPassword;
    }

    private SignInButton getSignInButton() {
        return signInButton;
    }

}
