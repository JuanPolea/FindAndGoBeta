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
import com.findandgoapp.activity.EventoSeleccionado;
import com.findandgoapp.activity.R;
import com.findandgoapp.adapter.ListAdapterValoraciones;
import com.findandgoapp.custom.CustomFontTextView;
import com.findandgoapp.pojo.NotificacionPOJO;
import com.findandgoapp.tools.Utilidades;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;


/**
 * A placeholder fragment containing a simple view.
 */
public class ValoracionFragment extends Fragment {


    private static Activity activity;
    private static int idSesion;
    private final NotificacionPOJO notificacionPOJO = new NotificacionPOJO();

    public ValoracionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i(getClass().getName(), "onCreateView");

        View rootView = inflater.inflate(R.layout.fragment_valoracion_item_list, container, false);

        activity = getActivity();
        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences("DATOS", Context.MODE_PRIVATE);

        notificacionPOJO.setListView((ListView) rootView.findViewById(R.id.valoracionItemList));
        notificacionPOJO.setIdUsuario(sharedPreferences.getInt(getString(R.string.sIdSesion), 0));

        notificacionPOJO.setTvTitulo((CustomFontTextView) rootView.findViewById(R.id.tvTituloNotificaciones));

        idSesion = sharedPreferences.getInt(getString(R.string.sIdSesion), 0);

        int tipo = sharedPreferences.getInt(getString(R.string.tipoValoracion), 0);

        Log.e("tipo", String.valueOf(tipo));

        if (tipo == 1) {
            //Obtenemos las confirmaciones realizadas Accion = 0 Motivo =4
            notificacionPOJO.setiAccion(0);
            notificacionPOJO.setiMotivo(4);
            selectConfirmacion();
            Log.e("EXECUTE", "UNO");
        } else if (tipo == 2) {
            //Obtenemos las confirmaciones recibidas Accion = 1 Motivo =4
            notificacionPOJO.setiAccion(1);
            notificacionPOJO.setiMotivo(4);
            selectConfirmacion();
            Log.e("EXECUTE", "2");
        } else if (tipo == 3) {
            //Obtenemos las denuncias realizadas Accion = 0 Motivo != 4
            notificacionPOJO.setiAccion(0);
            notificacionPOJO.setiMotivo(0);
            //notificacionPOJO.setiMotivo(4);
            selectDenuncias();
            Log.e("EXECUTE", "3");
        } else if (tipo == 4) {
            //Obtenemos las denuncias recibidas Accion = 1 Motivo != 4
            notificacionPOJO.setiAccion(1);
            //notificacionPOJO.setiMotivo(4);
            notificacionPOJO.setiMotivo(0);
            selectDenuncias();
            Log.e("EXECUTE", "4");
        } else if (tipo == 5) {

            selectNotificaciones();
        }
        return rootView;

    }

    private void selectDenuncias() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getString(R.string.sUrl_selectDenuncia),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        Log.e(getClass().getName(), "selectDenuncias" + s);

                        JSONObject json;
                        try {
                            json = new JSONObject(s);


                            final ArrayList<NotificacionPOJO> elementos = new ArrayList<>();
                            JSONArray array;

                            LinkedList<JSONObject> linkedList = new LinkedList<>();
                            int success;

                            Utilidades utilidades = new Utilidades();


                            success = json.getInt(getString(R.string.success));


                            if (success == 1) {

                                array = new JSONArray(json.getString(getString(R.string.result)));


                                for (int i = 0; i < array.length(); i++) {

                                    NotificacionPOJO notificacionPOJO = new NotificacionPOJO();
                                    Log.e(getClass().getName(), "ARRAY " + i + array.toString());
                                    notificacionPOJO.JSONPuntuacionToObject(array.getJSONObject(i));
                                    linkedList.add(array.getJSONObject(i));
                                    elementos.add(notificacionPOJO);
                                    Log.e(getClass().getName(), "Ele " + i + " " + elementos.get(i).getPuntuacionPOJO().getsNombreEvento());

                                }
                            }
                            if (!elementos.isEmpty()) {

                                ArrayList<NotificacionPOJO> arrayList;
                                arrayList = notificacionPOJO.formateoListNotificaciones(getActivity(), elementos);
                                for (int i = 0; i < arrayList.size(); i++) {
                                    Log.e(getClass().getName(), i + " " + arrayList.get(i).getPuntuacionPOJO().getIdEvento());
                                }
                                ListAdapterValoraciones listAdapterValoracion = new ListAdapterValoraciones(arrayList, getActivity());
                                notificacionPOJO.getListView().setAdapter(listAdapterValoracion);
                                listAdapterValoracion.notifyDataSetChanged();
                                //notificacionPOJO.getTvTitulo().setText(getActivity().getResources().getString(R.string.sNotificacionDenuncia));

                                addListenerToListViewDenuncia(notificacionPOJO.getListView(), linkedList);
                                Log.e(getClass().getName(), "SelectDenuncias->linked\n" + linkedList.toString());
                            } else {
                                utilidades.errorConsultaBBDD(getActivity(), getString(R.string.sNotieneNotificaciones));
                                //activity.finish();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(getClass().getName(), volleyError.getMessage());
            }
        }

        )

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return notificacionPOJO.formatoValoracion();
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    /**
     * @param listView
     * @param linkedList
     */
    private void addListenerToListViewDenuncia(ListView listView, final LinkedList linkedList) {

        //final JSONArray _jsonArray = jsonArray;


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent i = new Intent(getActivity().getApplicationContext(), EventoSeleccionado.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                JSONObject jsonObject;

                jsonObject = (JSONObject) linkedList.get(position);
                Log.e(getClass().getName(), "Position" + String.valueOf(position) + "\n" + jsonObject.toString());


                try {
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DATOS", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putInt("idEvento", jsonObject.getInt("idEvento"));
                    editor.putInt("idSesion", idSesion);
                    editor.putInt("idUsuarioPenalizado", jsonObject.getInt("idUsuarioPenalizado"));
                    editor.putInt("idUsuarioDenuncia", jsonObject.getInt("idUsuarioDenuncia"));
                    editor.putInt("accion", notificacionPOJO.getiAccion());
                    editor.putString(getString(R.string.fromFragment), getString(R.string.valoracion));
                    editor.putInt("idUsuario", jsonObject.getInt("idUsuarioPenalizado"));

                    Log.e(getClass().getName(), "idUsuarioPenalizado" + String.valueOf(jsonObject.getInt("idUsuarioPenalizado")));
                    Log.e(getClass().getName(), "denuncia " + String.valueOf(jsonObject.getInt("idUsuarioDenuncia")));
                    Log.e(getClass().getName(), "sess " + String.valueOf(idSesion));
                    //Log.e(getClass().getName(), "idUsuario" + String.valueOf(jsonObject.getInt("idUsuario")));
                    Log.e(getClass().getName(), "Accion" + String.valueOf(notificacionPOJO.getiAccion()));

                    editor.putString("activity", this.getClass().getSimpleName());
                    editor.apply();
                    getActivity().startActivity(i);


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(getClass().getName(), e.getMessage());
                }


            }
        });


    }


    /**
     * @params String, String, JSONObject
     * @desc Obtiene los eventos creados by date o mis eventos en función del parámetro iseleccion
     */

    private void selectConfirmacion() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getString(R.string.sUrl_selectConfirmacion),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        Log.e(getClass().getName(), "selectConfirmacion" + s);

                        JSONObject json;
                        try {
                            json = new JSONObject(s);


                            final ArrayList<NotificacionPOJO> elementos = new ArrayList<>();
                            JSONArray array;

                            LinkedList<JSONObject> linkedList = new LinkedList<>();
                            int success;

                            Utilidades utilidades = new Utilidades();

                            success = json.getInt("success");


                            if (success == 1) {


                                array = new JSONArray(json.getString("result"));


                                for (int i = 0; i < array.length(); i++) {

                                    NotificacionPOJO notificacionPOJO = new NotificacionPOJO();


                                    notificacionPOJO.JSONPuntuacionToObject(array.getJSONObject(i));


                                    linkedList.add(array.getJSONObject(i));

                                    Log.e(getClass().getName(), "LinkedList Confirma" + linkedList);


                                    elementos.add(notificacionPOJO);

                                }
                            }
                            if (!elementos.isEmpty()) {

                                ArrayList<NotificacionPOJO> arrayList;
                                arrayList = notificacionPOJO.formateoListNotificaciones(getActivity(), elementos);
                                ListAdapterValoraciones listAdapterValoracion = new ListAdapterValoraciones(arrayList, getActivity().getApplicationContext());
                                notificacionPOJO.getListView().setAdapter(listAdapterValoracion);
                                // notificacionPOJO.getTvTitulo().setText(getActivity().getResources().getString(R.string.sNotificacionConfirmacion));
                                listAdapterValoracion.notifyDataSetChanged();


                                addListenerToListViewConfirma(notificacionPOJO.getListView(), linkedList);


                            } else {
                                utilidades.errorConsultaBBDD(getActivity(), "No tiene notificaciones");
                                //activity.finish();

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
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

                return notificacionPOJO.formatoValoracion();
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    /**
     * @param listView
     * @param linkedList
     */
    private void addListenerToListViewConfirma(ListView listView, final LinkedList linkedList) {

        //final JSONArray _jsonArray = jsonArray;


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent i = new Intent(getActivity().getApplicationContext(), EventoSeleccionado.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                JSONObject jsonObject;

                jsonObject = (JSONObject) linkedList.get(position);
                Log.e(getClass().getName(), "Position" + String.valueOf(position) + "\n" + jsonObject.toString());


                try {

                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DATOS", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putInt(getActivity().getResources().getString(R.string.sIdEvento), jsonObject.getInt("idEvento"));

                    if (notificacionPOJO.getiAccion() == 0) {
                        Log.e(getClass().getName(), "if");
                        editor.putInt(getActivity().getResources().getString(R.string.sIdUsuario), jsonObject.getInt("idUsuarioPenalizado"));
                    } else {
                        Log.e(getClass().getName(), "else");
                        editor.putInt(getActivity().getResources().getString(R.string.sIdUsuario), jsonObject.getInt("idUsuarioPenalizado"));
                    }
                    editor.putInt("idSesion", idSesion);
                    editor.putString("activity", this.getClass().getSimpleName());
                    editor.putString(getString(R.string.fromFragment), getString(R.string.valoracion));
                    editor.apply();
                    try {
                        getActivity().startActivity(i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }

            }
        });
    }


    private void selectNotificaciones() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getString(R.string.sUrl_selectAllNotificacion),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        Log.e(getClass().getName(), "selectNotificaciones" + s);

                        JSONObject json;
                        try {
                            json = new JSONObject(s);


                            int successComentario = json.getInt("successComentario");
                            int successPenalizacion = json.getInt("successPenalizacion");
                            int successPuntuacion = json.getInt("successPuntuacion");


                            LinkedList linkedList = new LinkedList();
                            ArrayList<NotificacionPOJO> elementos = new ArrayList<>();


                            if (successComentario == 1) {
                                JSONArray array = new JSONArray(json.getString("comentario"));


                                for (int i = 0; i < array.length(); i++) {

                                    NotificacionPOJO notificacionPOJO = new NotificacionPOJO();


                                    notificacionPOJO.JSONComentarioToObject(array.getJSONObject(i));
                                    linkedList.add(array.getJSONObject(i));


                                    elementos.add(notificacionPOJO);

                                }
                            }
                            if (successPenalizacion == 1) {


                                JSONArray array = new JSONArray(json.getString("penalizacion"));


                                for (int i = 0; i < array.length(); i++) {

                                    NotificacionPOJO notificacionPOJO = new NotificacionPOJO();


                                    notificacionPOJO.JSONPenalizacionToObject(array.getJSONObject(i));
                                    linkedList.add(array.getJSONObject(i));


                                    elementos.add(notificacionPOJO);

                                }
                            }
                            if (successPuntuacion == 1) {


                                JSONArray array = new JSONArray(json.getString("puntuacion"));


                                for (int i = 0; i < array.length(); i++) {

                                    NotificacionPOJO notificacionPOJO = new NotificacionPOJO();


                                    notificacionPOJO.JSONPuntuacionToObject(array.getJSONObject(i));
                                    linkedList.add(array.getJSONObject(i));


                                    elementos.add(notificacionPOJO);

                                }
                            }
                            if (!elementos.isEmpty()) {

                                ListAdapterValoraciones listAdapterValoracion = new ListAdapterValoraciones(elementos, getActivity());
                                notificacionPOJO.getListView().setAdapter(listAdapterValoracion);
                                listAdapterValoracion.notifyDataSetChanged();

                                addListenerToListViewNotificaciones(notificacionPOJO.getListView(), linkedList);
                                Log.e(getClass().getName(), "SelectNotificaciones->linked\n" + linkedList.toString());
                            } else {
                                Utilidades utilidades = new Utilidades();
                                utilidades.errorConsultaBBDD(getActivity(), getActivity().getResources().getString(R.string.sNotieneNotificaciones));
                                activity.finish();

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
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


                return notificacionPOJO.formatoId();
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    /**
     * @param listView
     * @param linkedList
     */
    private void addListenerToListViewNotificaciones(ListView listView, final LinkedList linkedList) {

        //final JSONArray _jsonArray = jsonArray;


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent i = new Intent(getActivity().getApplicationContext(), EventoSeleccionado.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                JSONObject jsonObject;

                jsonObject = (JSONObject) linkedList.get(position);
                Log.e(getClass().getName(), "Position" + String.valueOf(position) + "\n" + jsonObject.toString());


                try {

                    if (jsonObject.getString("tipo").equals("comentario")) {
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DATOS", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putInt(getActivity().getResources().getString(R.string.sIdEvento), jsonObject.getInt("idEvento"));
                        editor.putInt(getActivity().getResources().getString(R.string.sIdUsuario), jsonObject.getInt("idUsuario"));
                        editor.putInt(getActivity().getResources().getString(R.string.sIdSesion), idSesion);
                        editor.putString(getString(R.string.fromFragment), getString(R.string.valoracion));

                        editor.putString("activity", this.getClass().getSimpleName());

                        Log.e(getClass().getName(), "ValoracionFragment->EventoSeleccionado");
                        editor.apply();


                        try {
                            getActivity().startActivity(i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (jsonObject.getString("tipo").equals("puntuacion")) {
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getResources().getString(R.string.DATOS), Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putInt(getActivity().getResources().getString(R.string.sIdEvento), jsonObject.getInt("idEvento"));
                        editor.putInt(getActivity().getResources().getString(R.string.sIdUsuario), jsonObject.getInt("idUsuarioDenuncia"));
                        editor.putInt(getActivity().getResources().getString(R.string.sIdSesion), idSesion);
                        editor.putString(getString(R.string.fromFragment), getString(R.string.valoracion));

                        editor.putString(getActivity().getResources().getString(R.string.sActivity), this.getClass().getSimpleName());

                        editor.apply();
                        try {
                            getActivity().startActivity(i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (jsonObject.getString("tipo").equals("penalizacion")) {

                        Utilidades utilidades = new Utilidades();
                        utilidades.mensajeAlertDialog(getActivity(), "Su usuario ha sido bloqueado. Por favor contacte con el administrador para más información:\n" + "admin@goout.com");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


    }


}

