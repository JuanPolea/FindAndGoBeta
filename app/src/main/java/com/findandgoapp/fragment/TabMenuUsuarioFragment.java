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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import com.findandgoapp.activity.Valoracion;
import com.findandgoapp.custom.CustomFontTextView;
import com.findandgoapp.custom.CustomTypeFaceSpan;
import com.findandgoapp.pojo.UsuarioPOJO;
import com.findandgoapp.tools.BordesRedondos;
import com.findandgoapp.tools.Utilidades;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;


/**
 * A placeholder fragment containing a simple view.
 */
public class TabMenuUsuarioFragment extends Fragment {


    private static final UsuarioPOJO usuarioPOJO = new UsuarioPOJO();

    private static Activity activity;


    private SharedPreferences sharedPreferences;
    private static final Utilidades utilidades = new Utilidades();

    /**
     * interfaz
     */
    private CustomFontTextView tv_nombreUsuario;
    private CustomFontTextView tv_numConfirmacionesIn;
    private CustomFontTextView tv_numConfirmacionesOut;
    private CustomFontTextView tv_numDenunciasIn;
    private CustomFontTextView tv_numDenunciasOut;
    private ImageView iv_perfil;
    private CustomFontTextView tv_estado;


    public TabMenuUsuarioFragment() {
    }

    @Override
    public void onResume() {

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DATOS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("tab", 1);
        editor.apply();
        Log.e(getClass().getName(), "onResume ");
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_tab_menu_usuario, container, false);


        activity = getActivity();
        setHasOptionsMenu(true);


        sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(getResources().getString(R.string.sDATOS), Context.MODE_PRIVATE);


        usuarioPOJO.setI_idSesion(sharedPreferences.getInt(getResources().getString(R.string.sIdSesion), 0));
        usuarioPOJO.setI_idUsuario(sharedPreferences.getInt(getResources().getString(R.string.sIdSesion), 0));

        usuarioPOJO.setI_estado(sharedPreferences.getInt(getResources().getString(R.string.estado), 0));
        usuarioPOJO.setS_password(sharedPreferences.getString(getResources().getString(R.string.password), null));
        usuarioPOJO.setI_tipo(sharedPreferences.getInt(getResources().getString(R.string.tipoUsuario), 0));


        setTv_numConfirmacionesIn((CustomFontTextView) view.findViewById(R.id.idTvConfRecibidasTotal));
        setTv_numConfirmacionesOut((CustomFontTextView) view.findViewById(R.id.idTvConfRealizadasTotal));
        setTv_numDenunciasIn((CustomFontTextView) view.findViewById(R.id.idTvDenunciasRecibidasTotal));
        setTv_numDenunciasOut((CustomFontTextView) view.findViewById(R.id.idTvDenunciasRealizadasTotal));
        setTv_nombreUsuario((CustomFontTextView) view.findViewById(R.id.idTvNombre));
        getTv_nombreUsuario().setTextColor(getResources().getColor(R.color.verdeAgua));
        //setIv_perfil((ImageView) view.findViewById(R.id.idIbPerfil));
        setTv_estado((CustomFontTextView) view.findViewById(R.id.idTvEstadoResultado));
        selectPuntuaciones(usuarioPOJO);

        addListenerToTextView(getTv_numConfirmacionesOut(), 1);
        addListenerToTextView(getTv_numConfirmacionesIn(), 2);
        addListenerToTextView(getTv_numDenunciasOut(), 3);
        addListenerToTextView(getTv_numDenunciasIn(), 4);


        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(getString(R.string.fragment_tab_menu_usuario));

        fragmentTransaction.commit();

        return view;
    }

    /**
     * @param usuarioPOJO
     */
    private void selectPuntuaciones(final UsuarioPOJO usuarioPOJO) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getString(R.string.sUrl_selectPuntuaciones),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        Log.e(getClass().getName(), "SelectPuntuaciones" + s);

                        JSONObject json;
                        try {
                            json = new JSONObject(s);


                            getTv_numConfirmacionesIn().setText(String.valueOf(json.getInt(getActivity().getResources().getString(R.string.confirmacionRecibida))));

                            getTv_numConfirmacionesOut().setText(String.valueOf(json.getInt(getResources().getString(R.string.confirmacionRealizada))));

                            getTv_numDenunciasIn().setText(String.valueOf(json.getInt(getResources().getString(R.string.denunciaRecibida))));

                            getTv_numDenunciasOut().setText(String.valueOf(json.getInt(getResources().getString(R.string.denunciaRealizada))));

                            getTv_nombreUsuario().setText(json.getString(getResources().getString(R.string.nombreUsuario)));

                            usuarioPOJO.setI_tipo(json.getInt(getResources().getString(R.string.tipoUsuario)));

                            JSONArray estado = new JSONArray();

                            estado = json.getJSONArray(getResources().getString(R.string.estado));

                            for (int i = 0; i < estado.length(); i++) {

                                JSONObject jsonObject = estado.getJSONObject(i);
                                if (jsonObject.getInt("tipoPenalizacion") == getResources().getInteger(R.integer.iBloqueado)) {
                                    getTv_estado().setText(getResources().getString(R.string.sBloqueado));
                                    getTv_estado().setTextColor(ContextCompat.getColor(getActivity(), R.color.rojo));
                                    usuarioPOJO.setI_estado(1);
                                }
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                volleyError.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return usuarioPOJO.formatoIdUsuario(getContext());
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(getClass().getName(), "onDestroy");
        utilidades.clearImageDiskCache(activity);
    }


    /**
     * @param b
     * @param tipo
     */
    private void addListenerToTextView(final CustomFontTextView b, final int tipo) {

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (b.getText().toString().equalsIgnoreCase(String.valueOf(0))) {

                    Toast toast = Toast.makeText(getActivity(), getString(R.string.noExistenDatos), Toast.LENGTH_SHORT);
                    toast.show();
                } else {


                    //usuario bloqueado corresponde al id = 4

                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getResources().getString(R.string.DATOS), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putInt(getActivity().getResources().getString(R.string.sIdSesion), usuarioPOJO.getI_idSesion());
                    editor.putInt(getActivity().getResources().getString(R.string.sIdUsuario), usuarioPOJO.getI_idUsuario());
                    editor.putInt(getActivity().getResources().getString(R.string.tipoUsuario), usuarioPOJO.getI_tipo());
                    editor.putString(getActivity().getResources().getString(R.string.password), usuarioPOJO.getS_password());
                    editor.putInt(getActivity().getResources().getString(R.string.estado), usuarioPOJO.getI_estado());
                    editor.putInt(getActivity().getResources().getString(R.string.tab), 0);
                    editor.putInt(getString(R.string.tipoValoracion), tipo);

                    if (usuarioPOJO.getI_estado() != 0)
                        editor.putBoolean(getActivity().getResources().getString(R.string.bloqueado), true);
                    else
                        editor.putBoolean(getActivity().getResources().getString(R.string.bloqueado), false);
                    editor.apply();


                    editor.putBoolean(getActivity().getResources().getString(R.string.fromFragment), true);

                    Intent i = new Intent(getActivity(), Valoracion.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);

                    startActivity(i);

                    Log.i(getClass().getName(), "Inicia Valoracion.class");

                }

            }
        });


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


                Log.d(getClass().getName(), "CATCH" + e.getMessage());

            } finally {
                assert con != null;
                con.disconnect();
            }

            Log.e(getClass().getName(), "FILE_EXISTS" + estado);

            return estado;
        }


        @Override
        protected void onPostExecute(final Boolean result) {


            if (result)
                launchPicasso(_context, result, this.imageView, newFile);
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


        if (isImage) {

            Picasso.
                    with(activity).
                    load(file)
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .transform(new BordesRedondos(300, 0))
                    .error(R.drawable.btn_artista)
                    .placeholder(R.drawable.btn_usuario)
                    .into(image);


            Utilidades utilidades = new Utilidades();
            utilidades.clearImageDiskCache(activity.getApplicationContext());
        } else {
            if (usuarioPOJO.getI_tipo() == 2) {

                image.setImageDrawable(activity.getResources().getDrawable(R.drawable.btn_usuario));

            } else if (usuarioPOJO.getI_tipo() == 3) {
                image.setImageDrawable(activity.getResources().getDrawable(R.drawable.btn_artista));
            } else {
                image.setImageDrawable(activity.getResources().getDrawable(R.drawable.btn_asistente));
            }
        }

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        menu.clear();
        if (!menu.hasVisibleItems())
            inflater.inflate(R.menu.menu_tab_menu_principal, menu);
        else {
            menu.clear();
            inflater.inflate(R.menu.menu_tab_menu_principal, menu);

        }


        super.onCreateOptionsMenu(menu, inflater);
    }


    /**
     * http://stackoverflow.com/questions/6612316/how-set-spannable-object-font-with-custom-font/10741161#10741161
     *
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

            if (title.equalsIgnoreCase(getString(R.string.sRealizarFoto))) {
                menu.removeItem(R.id.idMICamara);
            } else {
                Spannable newTitle = new SpannableString(title);
                Typeface font = Typeface.createFromAsset(getActivity().getAssets(), getResources().getString(R.string.fontAmaticRegular));
                newTitle.setSpan(new CustomTypeFaceSpan("", font), 0, newTitle.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                mi.setTitle(newTitle);
            }


        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        Log.e(getClass().getName(), "onOptionsItemSelected");
        switch (item.getItemId()) {
            case R.id.miIdSalir:

                Utilidades utilidades = new Utilidades();
                utilidades.salir(getActivity());

                return true;

            default:

                return super.onOptionsItemSelected(item);
        }
    }

    private CustomFontTextView getTv_estado() {
        return tv_estado;
    }

    private void setTv_estado(CustomFontTextView tv_estado) {
        this.tv_estado = tv_estado;
    }

    private CustomFontTextView getTv_nombreUsuario() {
        return tv_nombreUsuario;
    }

    private void setTv_nombreUsuario(CustomFontTextView tv_nombreUsuario) {
        this.tv_nombreUsuario = tv_nombreUsuario;
    }

    private CustomFontTextView getTv_numConfirmacionesIn() {
        return tv_numConfirmacionesIn;
    }

    private void setTv_numConfirmacionesIn(CustomFontTextView tv_numConfirmacionesIn) {
        this.tv_numConfirmacionesIn = tv_numConfirmacionesIn;
    }

    private CustomFontTextView getTv_numConfirmacionesOut() {
        return tv_numConfirmacionesOut;
    }

    private void setTv_numConfirmacionesOut(CustomFontTextView tv_numConfirmacionesOut) {
        this.tv_numConfirmacionesOut = tv_numConfirmacionesOut;
    }

    private CustomFontTextView getTv_numDenunciasIn() {
        return tv_numDenunciasIn;
    }

    private void setTv_numDenunciasIn(CustomFontTextView tv_numDenunciasIn) {
        this.tv_numDenunciasIn = tv_numDenunciasIn;
    }


    private CustomFontTextView getTv_numDenunciasOut() {
        return tv_numDenunciasOut;
    }

    private void setTv_numDenunciasOut(CustomFontTextView tv_numDenunciasOut) {
        this.tv_numDenunciasOut = tv_numDenunciasOut;
    }


}
