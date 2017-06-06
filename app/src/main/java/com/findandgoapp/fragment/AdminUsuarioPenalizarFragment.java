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

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.findandgoapp.activity.EventoListado;
import com.findandgoapp.activity.PenalizacionListado;
import com.findandgoapp.activity.R;
import com.findandgoapp.custom.CustomFontTextView;
import com.findandgoapp.custom.CustomTypeFaceSpan;
import com.findandgoapp.pojo.UsuarioPOJO;
import com.findandgoapp.tools.Utilidades;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * A placeholder fragment containing a simple view.
 */
public class AdminUsuarioPenalizarFragment extends Fragment {

    private static final String TAG = "AdminUsPenalizarFrg";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static Activity activity;
    private static android.support.v4.app.FragmentManager fragmentManager;
    private static int idSesion;
    private static UsuarioPOJO usuario;
    private CustomFontTextView nombreUsuario;
    private CustomFontTextView emailUsuairo;
    private CustomFontTextView estado;
    private CustomFontTextView tipoUsuario;
    private CustomFontTextView tvTipo1;
    private CustomFontTextView tvEventosCreados;
    private CustomFontTextView tvDenunciasIn;
    private CustomFontTextView tvDenunciasOut;
    private CustomFontTextView tvConfirmaIn;
    private CustomFontTextView tvConfirmaOut;
    private CustomFontTextView tvComentaIn;
    private CustomFontTextView tvComentaOut;
    private ImageView ivBloqueado;
    private Button tipo1Add;
    private Button tipo1Quit;

    private static void showNoticeDialogPenalizaciones(Context c) {
        // Create an instance of the dialog fragment and show it
        activity.getIntent().putExtra("penalizacion", usuario);
        activity.getIntent().putExtra("idSesion", idSesion);
        FragmentDialogAdminPenalizacion dialog = FragmentDialogAdminPenalizacion.newInstance(idSesion);
        dialog.show(fragmentManager, "DialogAdminFragmentPenalizacion");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        usuario = new UsuarioPOJO();
        Log.i(TAG, "onCreateView");
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DATOS", Context.MODE_PRIVATE);
        setHasOptionsMenu(true);
        activity = getActivity();
        View rootView = inflater.inflate(R.layout.fragment_admin_usuario_penalizar, container, false);

        fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(getString(R.string.fragment_admin_usuario_penalizar));

        fragmentTransaction.commit();
        usuario.setI_idUsuario(sharedPreferences.getInt("idUsuario", 0));
        usuario.setI_estado(sharedPreferences.getInt(getResources().getString(R.string.estado), 0));

        Log.e(TAG, "ID " + String.valueOf(usuario.getI_idUsuario()));
        idSesion = sharedPreferences.getInt("idUsuario", 0);

        setNombreUsuario((CustomFontTextView) rootView.findViewById(R.id.tvAdminNombreUsuarioPenalizar));
        setEmailUsuairo((CustomFontTextView) rootView.findViewById(R.id.tvAdminEmailUsuarioPenalizar));
        setTipoUsuario((CustomFontTextView) rootView.findViewById(R.id.tvAdminTipoUsuarioPenalizar));
        setEstado((CustomFontTextView) rootView.findViewById(R.id.tvAdminEstadoUsuarioPenalizar));
        setTipo1Add((Button) rootView.findViewById(R.id.idBNumTipo1Add));
        setTipo1Quit((Button) rootView.findViewById(R.id.idBNumTipo1Quit));
        setTvTipo1((CustomFontTextView) rootView.findViewById(R.id.idTvTipo1UsuarioPenalizarRes));
        setTvEventosCreados((CustomFontTextView) rootView.findViewById(R.id.idTvNumEventosCreadosUsuarioPenalizar));
        setTvDenunciasIn((CustomFontTextView) rootView.findViewById(R.id.idTvNumDenunciasIn));
        setTvDenunciasOut((CustomFontTextView) rootView.findViewById(R.id.idTvNumDenunciasOut));
        setTvConfirmaIn((CustomFontTextView) rootView.findViewById(R.id.idTvNumConfirmacionesIn));
        setTvConfirmaOut((CustomFontTextView) rootView.findViewById(R.id.idTvNumConfirmacionesOut));
        setTvComentaIn((CustomFontTextView) rootView.findViewById(R.id.idTvNumComentariosIn));
        setTvComentaOut((CustomFontTextView) rootView.findViewById(R.id.idTvNumComentariosOut));
        setIvBloqueado((ImageView) rootView.findViewById(R.id.idIvBloqueado));
        getIvBloqueado().setVisibility(View.INVISIBLE);

        addListenerAdd(getTipo1Add(), rootView, container);

        adListenerEliminarPenalizacion(getTipo1Quit(), getTvTipo1(), rootView, container);
        selectDatosUsuarioPenalizados();


        getIvBloqueado().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activarUsuario();
            }
        });
        return rootView;
    }

    private void activarUsuario() {
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_updateActivarUsuario),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONObject json = new JSONObject(response);
                            int success;
                            success = json.getInt(getString(R.string.success));
                            if (success == 1) {
                                getIvBloqueado().setVisibility(View.INVISIBLE);
                                getEstado().setText(getString(R.string.sActivado));
                                getEstado().setTextColor(getActivity().getResources().getColor(R.color.verdeClaro));
                                Toast.makeText(getActivity(), getString(R.string.sActivado), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), getString(R.string.sNoAccion), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getActivity(), getString(R.string.errorConexion), Toast.LENGTH_SHORT).show();
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

    private void selectDatosUsuarioPenalizados() {

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_selectPuntuacionesById),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Log.e(getClass().getName(), "selectDatosUsuarioPenalizados " + response);


                        try {

                            JSONObject json = new JSONObject(response);

                            getNombreUsuario().setText(json.getString("nombreUsuario"));

                            getEmailUsuairo().setText(json.getString("email"));

                            getTipoUsuario().setText(json.getString("tipoUsuario"));

                            int penalizaciones = 0;
                            penalizaciones = penalizaciones + Integer.parseInt(json.getString("tipo1")) +
                                    Integer.parseInt(json.getString("tipo2")) +
                                    Integer.parseInt(json.getString("tipo3"));
                            getTvTipo1().setText(String.valueOf(penalizaciones));


                            getTvEventosCreados().setText(json.getString("eventosCreados"));

                            getTvDenunciasIn().setText(json.getString("denunciasRecibidas"));

                            getTvDenunciasOut().setText(json.getString("denunciasRealizadas"));

                            getTvConfirmaIn().setText(json.getString("confirmacionesRecibidas"));

                            getTvConfirmaOut().setText(json.getString("confirmacionesRealizadas"));

                            getTvComentaOut().setText(json.getString("comentariosRealizados"));

                            getTvComentaIn().setText(json.getString("comentariosRecibidos"));


                            int bloqueado;

                            bloqueado = json.getInt("estado");

                            if (bloqueado == 1) {

                                getEstado().setText(getString(R.string.sBloqueado));
                                getEstado().setTextColor(getActivity().getResources().getColor(R.color.amarillo));


                            } else if (bloqueado == 0) {

                                getEstado().setText(getActivity().getResources().getString(R.string.sActivo));
                                getEstado().setTextColor(getActivity().getResources().getColor(R.color.verdeClaro));

                            } else {

                                getEstado().setText(getActivity().getResources().getString(R.string.Eliminado));
                                getEstado().setTextColor(getActivity().getResources().getColor(R.color.rojo));
                                getIvBloqueado().setVisibility(View.VISIBLE);

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
                        Toast.makeText(getActivity(), getString(R.string.errorConexion), Toast.LENGTH_SHORT).show();
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

    /**
     * @param button
     * @param textView
     * @param rootView
     * @param container
     */
    private void adListenerEliminarPenalizacion(Button button, final CustomFontTextView textView, final View rootView, ViewGroup container) {


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (textView.getText().equals("0")) {
                    Toast toast = Toast.makeText(rootView.getContext(), "No existen penalizaciones que eliminar", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DATOS", Context.MODE_PRIVATE);

                    Intent intent = new Intent(getActivity(), PenalizacionListado.class);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putInt("idUsuario", usuario.getI_idUsuario());
                    editor.putInt("tipoPenalizacion", 1);

                    editor.apply();
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_admin_usuario_penalizar, menu);
        super.onCreateOptionsMenu(menu, inflater);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        Log.e(TAG, String.valueOf(usuario.getI_idUsuario()));

        switch (item.getItemId()) {
            case R.id.miIdEliminarUsuario:


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(getString(R.string.sEliminarUsuario));

                builder.setPositiveButton(getString(R.string.sSi), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        deleteUsuario();


                    }
                });

                builder.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                return true;

            case R.id.miBuscarEventos:

                selectEventosCreados();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     *
     */
    private void deleteUsuario() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_deleteUsuario),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        int success;

                        try {
                            JSONObject json = new JSONObject(response);

                            int estado;


                            success = json.getInt(getString(R.string.success));


                            if (success == 1) {

                                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DATOS", Context.MODE_PRIVATE);
                                estado = json.getInt(getString(R.string.estado));
                                Log.e(getClass().getName(), response);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putInt(getString(R.string.sIdSesion), idSesion);
                                editor.putInt(getString(R.string.sIdUsuario), idSesion);
                                editor.putInt(getString(R.string.estado), estado);

                                getIvBloqueado().setVisibility(View.VISIBLE);
                                getEstado().setText(getString(R.string.Eliminado));
                                getEstado().setTextColor(getActivity().getResources().getColor(R.color.rojo));
                                Toast.makeText(getActivity(), getString(R.string.sUsuarioEliminado), Toast.LENGTH_SHORT).show();

                                editor.apply();
                            } else {
                                Toast.makeText(getActivity(), getString(R.string.sNoAccion), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getActivity(), getString(R.string.errorConexion), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                return formatoBloqueo(usuario);
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }

    /**
     * x
     *
     * @param usuario
     * @return
     */
    private Map<String, String> formatoBloqueo(UsuarioPOJO usuario) {


        Map<String, String> linkedList = new HashMap<>();
        linkedList.put(getString(R.string.sIdUsuario), String.valueOf(usuario.getI_idUsuario()));

        linkedList.put(getString(R.string.Admin), getString(R.string.tru));
        linkedList.put(getString(R.string.estado), getString(R.string.eliminado));

        return linkedList;

    }

    /**
     *
     */
    private void selectEventosCreados() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_selectAllEvento),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        JSONArray jsonArray = new JSONArray();
                        Utilidades utilidades = new Utilidades();
                        Log.e(getClass().getName(), "selectEventosCreados " + response);
                        int success;

                        try {
                            JSONObject json = new JSONObject(response);
                            jsonArray = json.getJSONArray("result");

                            success = json.getInt("success");
                            Log.e(TAG, "\nARR " + jsonArray.toString());

                            if (success == 1) {

                                Intent i = new Intent().setClass(getActivity().getApplicationContext(), EventoListado.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

                                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getResources().getString(R.string.sDATOS), Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putInt(getActivity().getResources().getString(R.string.sIdUsuario), usuario.getI_idUsuario());
                                editor.putInt(getActivity().getResources().getString(R.string.sIdSesion), 1);
                                editor.putString(getActivity().getResources().getString(R.string.sresultado), String.valueOf(jsonArray));
                                editor.putInt(getActivity().getResources().getString(R.string.sSeleccion), 1);
                                editor.putInt(getActivity().getResources().getString(R.string.estado), json.getInt(getActivity().getResources().getString(R.string.estado)));
                                editor.putString(getString(R.string.fromFragment), getString(R.string.fragment_admin_usuario_penalizar));
                                editor.apply();
                                getActivity().getApplicationContext().startActivity(i);


                                /*EventoListadoFragment fragment1 = new EventoListadoFragment();
                                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.add(fragment1,getString(R.string.fragment_evento_listado));
                                fragmentTransaction.addToBackStack(getString(R.string.fragment_evento_listado));
                                fragmentManager.popBackStack(getString(R.string.fragment_admin_usuario_penalizar), FragmentManager.POP_BACK_STACK_INCLUSIVE);
                                fragmentTransaction.commit();*/
                                // editor.apply();

                            } else {
                                utilidades.errorConsultaBBDD(getActivity(), getActivity().getResources().getString(R.string.sNoHayEventos));
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

                return usuario.formatoIdUsuario(1);
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);


    }


    /**
     * @param button
     */
    private void addListenerAdd(final Button button, final View rootView, final ViewGroup container) {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showNoticeDialogPenalizaciones(getActivity());
            }
        });

    }


    private CustomFontTextView getEmailUsuairo() {
        return emailUsuairo;
    }

    private void setEmailUsuairo(CustomFontTextView emailUsuairo) {
        this.emailUsuairo = emailUsuairo;
    }

    private CustomFontTextView getEstado() {
        return estado;
    }

    private void setEstado(CustomFontTextView estado) {
        this.estado = estado;
    }

    private CustomFontTextView getNombreUsuario() {
        return nombreUsuario;
    }

    private void setNombreUsuario(CustomFontTextView nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    private Button getTipo1Add() {
        return tipo1Add;
    }

    private void setTipo1Add(Button tipo1Add) {
        this.tipo1Add = tipo1Add;
    }

    private Button getTipo1Quit() {
        return tipo1Quit;
    }

    private void setTipo1Quit(Button tipo1Quit) {
        this.tipo1Quit = tipo1Quit;
    }


    private CustomFontTextView getTipoUsuario() {
        return tipoUsuario;
    }

    private void setTipoUsuario(CustomFontTextView tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    private CustomFontTextView getTvComentaIn() {
        return tvComentaIn;
    }

    private void setTvComentaIn(CustomFontTextView tvComentaIn) {
        this.tvComentaIn = tvComentaIn;
    }

    private CustomFontTextView getTvComentaOut() {
        return tvComentaOut;
    }

    private void setTvComentaOut(CustomFontTextView tvComentaOut) {
        this.tvComentaOut = tvComentaOut;
    }

    private CustomFontTextView getTvConfirmaIn() {
        return tvConfirmaIn;
    }

    private void setTvConfirmaIn(CustomFontTextView tvConfirmaIn) {
        this.tvConfirmaIn = tvConfirmaIn;
    }

    private CustomFontTextView getTvConfirmaOut() {
        return tvConfirmaOut;
    }

    private void setTvConfirmaOut(CustomFontTextView tvConfirmaOut) {
        this.tvConfirmaOut = tvConfirmaOut;
    }

    private CustomFontTextView getTvDenunciasIn() {
        return tvDenunciasIn;
    }

    private void setTvDenunciasIn(CustomFontTextView tvDenunciasIn) {
        this.tvDenunciasIn = tvDenunciasIn;
    }

    private CustomFontTextView getTvDenunciasOut() {
        return tvDenunciasOut;
    }

    private void setTvDenunciasOut(CustomFontTextView tvDenunciasOut) {
        this.tvDenunciasOut = tvDenunciasOut;
    }

    private CustomFontTextView getTvEventosCreados() {
        return tvEventosCreados;
    }

    private void setTvEventosCreados(CustomFontTextView tvEventosCreados) {
        this.tvEventosCreados = tvEventosCreados;
    }

    private CustomFontTextView getTvTipo1() {
        return tvTipo1;
    }

    private void setTvTipo1(CustomFontTextView tvTipo1) {
        this.tvTipo1 = tvTipo1;
    }


    private ImageView getIvBloqueado() {
        return ivBloqueado;
    }

    private void setIvBloqueado(ImageView ivBloqueado) {
        this.ivBloqueado = ivBloqueado;
    }

}
