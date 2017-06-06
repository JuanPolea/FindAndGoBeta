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
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.findandgoapp.activity.Configuracion;
import com.findandgoapp.activity.EventoSeleccionado;
import com.findandgoapp.activity.R;
import com.findandgoapp.adapter.ListAdapterNotificaciones;
import com.findandgoapp.custom.CustomFontTextView;
import com.findandgoapp.custom.CustomTypeFaceSpan;
import com.findandgoapp.pojo.NotificacionPOJO;
import com.findandgoapp.tools.Utilidades;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;


/**
 * A placeholder fragment containing a simple view.
 */
public class NotificacionesFragment extends Fragment {


    private static int idSesion;
    private final NotificacionPOJO notificacionPOJO = new NotificacionPOJO();


    public NotificacionesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(getClass().getName(), "onCreateView");


        View rootView = inflater.inflate(R.layout.fragment_notificaciones_item_list, container, false);

        setHasOptionsMenu(true);
        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(getActivity().getResources().getString(R.string.sDATOS), Context.MODE_PRIVATE);
        notificacionPOJO.setListView((ListView) rootView.findViewById(R.id.listNotificaciones));
        notificacionPOJO.setIdUsuario(sharedPreferences.getInt(getActivity().getResources().getString(R.string.sIdUsuario), 0));
        notificacionPOJO.setTvTitulo((CustomFontTextView) rootView.findViewById(R.id.tvTituloNotificaciones));

        idSesion = sharedPreferences.getInt(getActivity().getResources().getString(R.string.sIdUsuario), 0);


        selectNotificaciones();
        return rootView;

    }

    private void selectNotificaciones() {
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_selectAllEstadoNotificacion),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray();
                            Utilidades utilidades = new Utilidades();
                            Log.e(getClass().getName(), "selectNotificaciones " + response);
                            JSONObject json = new JSONObject(response);

                            int success;
                            success = json.getInt(getString(R.string.success));


                            LinkedList<JSONObject> link = new LinkedList<>();
                            ArrayList<NotificacionPOJO> elementos = new ArrayList<>();

                            if (success == 1) {


                                jsonArray = (json.getJSONArray(getString(R.string.result)));


                                for (int i = 0; i < jsonArray.length(); i++) {

                                    NotificacionPOJO notificacion = new NotificacionPOJO();
                                    notificacion.JSONToEstadoNotificacion(jsonArray.getJSONObject(i));
                                    link.add(jsonArray.getJSONObject(i));
                                    elementos.add(notificacion);


                                }


                                JSONArray mencion = (json.getJSONArray(getString(R.string.menciones)));
                                Log.e(getClass().getName(), String.valueOf(jsonArray));

                                for (int i = 0; i < mencion.length(); i++) {

                                    Log.e(getClass().getName(), String.valueOf(jsonArray.get(i)));

                                    NotificacionPOJO notificacion = new NotificacionPOJO();
                                    notificacion.JSONToMencion(mencion.getJSONObject(i));
                                    link.add(jsonArray.getJSONObject(i));
                                    elementos.add(notificacion);


                                }


                            } else if (success == 2) {


                                jsonArray = (json.getJSONArray(getString(R.string.result)));


                                for (int i = 0; i < jsonArray.length(); i++) {

                                    NotificacionPOJO notificacion = new NotificacionPOJO();
                                    notificacion.JSONToEstadoNotificacion(jsonArray.getJSONObject(i));
                                    link.add(jsonArray.getJSONObject(i));
                                    elementos.add(notificacion);


                                }


                            } else if (success == 3) {


                                jsonArray = (json.getJSONArray(getString(R.string.menciones)));


                                for (int i = 0; i < jsonArray.length(); i++) {

                                    NotificacionPOJO notificacion = new NotificacionPOJO();
                                    notificacion.JSONToMencion(jsonArray.getJSONObject(i));
                                    link.add(jsonArray.getJSONObject(i));
                                    elementos.add(notificacion);


                                }


                            }


                            if (!elementos.isEmpty()) {


                                ListAdapterNotificaciones listAdapterNotificacion = new ListAdapterNotificaciones(elementos, getActivity().getApplicationContext());
                                notificacionPOJO.getListView().setAdapter(listAdapterNotificacion);
                                listAdapterNotificacion.notifyDataSetChanged();

                                LinkedList<JSONObject> linked = new LinkedList<>();
                                for (JSONObject l : link) {
                                    linked.add(l);
                                    try {
                                        Log.e(getClass().getName(), "LIST " + l.get("nombreEvento"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                addListenerToListView(notificacionPOJO.getListView(), linked);
                                //Log.e(getClass().getName(), "linked\n" + linkedList.toString());
                            } else {
                                utilidades.errorConsultaBBDD(getActivity(), getActivity().getResources().getString(R.string.sNotieneNotificaciones));
                                // activity.finish();

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
                        Toast.makeText(getActivity(), getString(R.string.errorConexion), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                return notificacionPOJO.formatoId();
            }
        };
        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent i;


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Log.d(getClass().getName(), "Configuracion" + String.valueOf(item.getItemId()));
            i = new Intent(getActivity(), Configuracion.class);
            startActivity(i);
            return true;


        }
        return super.onOptionsItemSelected(item);
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
     * @param listView
     * @param linkedList
     */
    private void addListenerToListView(ListView listView, final LinkedList linkedList) {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent i = new Intent(getActivity().getApplicationContext(), EventoSeleccionado.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                JSONObject jsonObject;

                jsonObject = (JSONObject) linkedList.get(position);
                Log.e(getClass().getName(), "Position" + String.valueOf(position) + "\n" + jsonObject.toString());


                try {


                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getResources().getString(R.string.sDATOS), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putInt(getActivity().getResources().getString(R.string.sIdEvento), jsonObject.getInt(getActivity().getResources().getString(R.string.sIdEvento)));
                    editor.putInt(getActivity().getResources().getString(R.string.sIdUsuario), jsonObject.getInt(getActivity().getResources().getString(R.string.sIdUsuario)));
                    editor.putInt(getActivity().getResources().getString(R.string.sIdSesion), idSesion);
                    editor.putString(getString(R.string.fromFragment), getString(R.string.fragment_notificaciones));

                    editor.putString(getActivity().getResources().getString(R.string.sactivity), this.getClass().getSimpleName());

                    editor.apply();


                    getActivity().startActivity(i);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


    }
}

