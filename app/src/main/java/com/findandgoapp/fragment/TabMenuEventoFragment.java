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

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
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
import com.findandgoapp.activity.EventoSeleccionado;
import com.findandgoapp.activity.MenuPrincipal;
import com.findandgoapp.activity.R;
import com.findandgoapp.adapter.ListAdapterEvento;
import com.findandgoapp.custom.CustomTypeFaceSpan;
import com.findandgoapp.pojo.EventoPOJO;
import com.findandgoapp.tools.Utilidades;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class TabMenuEventoFragment extends Fragment {


    private static final String TAG = "TabMenuEventoFragment";
    private static int iIdSesion;
    private static SharedPreferences sharedPreferences;
    private final EventoPOJO tabEventoPOJO = new EventoPOJO();

    private FragmentActivity activity;
    private boolean flag;
    private ProgressDialog progressDialog;
    private Map<String, String> toUbicacion;


    public TabMenuEventoFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        activity = getActivity();

        Log.e(TAG, "OnCreateView");
        getActivity().setTitle(getString(R.string.title_activity_menu_ppal));


        View view = inflater.inflate(R.layout.fragment_evento_item_list, container, false);


        sharedPreferences = getActivity().getApplicationContext().getSharedPreferences("DATOS", Context.MODE_PRIVATE);

        sharedPreferences = getActivity().getSharedPreferences(getResources().getString(R.string.sDATOS), Context.MODE_PRIVATE);
        tabEventoPOJO.setI_idUsuario(sharedPreferences.getInt(getResources().getString(R.string.sIdSesion), 0));
        iIdSesion = sharedPreferences.getInt(getResources().getString(R.string.sIdSesion), 0);
        tabEventoPOJO.setS_localidad(sharedPreferences.getString(getActivity().getResources().getString(R.string.locality), getActivity().getResources().getString(R.string.svacio)));
        tabEventoPOJO.setI_cp(sharedPreferences.getInt(getActivity().getResources().getString(R.string.cp), 0));
        tabEventoPOJO.setS_ciudad(sharedPreferences.getString(getActivity().getResources().getString(R.string.subAdmin), getActivity().getResources().getString(R.string.svacio)));
        flag = sharedPreferences.getBoolean(getActivity().getResources().getString(R.string.login), false);
        int seleccion = sharedPreferences.getInt(getString(R.string.seleccion), 0);


        Log.e(TAG, "localidad " + tabEventoPOJO.getS_localidad() + " Cp " + tabEventoPOJO.getI_cp());

        tabEventoPOJO.setI_estado(sharedPreferences.getInt(getResources().getString(R.string.estado), 0));


        tabEventoPOJO.setListView((ListView) view.findViewById(R.id.eventoItemList));


        toUbicacion = toUbicacion(tabEventoPOJO.getS_ciudad(), tabEventoPOJO.getS_localidad(), tabEventoPOJO.getI_cp(), seleccion);
        selectEventosCreados(tabEventoPOJO, seleccion);


        Log.e(TAG, "Estado -> " + tabEventoPOJO.getI_estado());
        if (tabEventoPOJO.getI_estado() != 0) {
            Utilidades utilidades = new Utilidades();
            utilidades.mensajeAlertDialog(getActivity(), getActivity().getResources().getString(R.string.sAvisoBloqueado));
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(getActivity().getResources().getString(R.string.bloqueado), true);
            Log.i(TAG, "cambia variable bloqueado");
            editor.apply();

        }

        return view;
    }

    /**
     * @param ciudad
     * @param localidad
     * @param cp
     * @param seleccion
     * @return
     */
    private Map<String, String> toUbicacion(String ciudad, String localidad, int cp, int seleccion) {


        Map<String, String> map = new HashMap<>();

        if (cp != 0) {

            map.put(getActivity().getResources().getString(R.string.locality), localidad);
            map.put(getActivity().getResources().getString(R.string.cp), String.valueOf(cp));
            map.put(getActivity().getResources().getString(R.string.subAdmin), ciudad);
            map.put(getResources().getString(R.string.seleccion), String.valueOf(seleccion));

        } else {
            map.put(getActivity().getResources().getString(R.string.locality), getActivity().getResources().getString(R.string.svacio));
            map.put(getActivity().getResources().getString(R.string.cp), getActivity().getResources().getString(R.string.iCero));
            map.put(getActivity().getResources().getString(R.string.subAdmin), getActivity().getResources().getString(R.string.svacio));
            map.put(getResources().getString(R.string.seleccion), String.valueOf(seleccion));

        }

        map.put(getActivity().getResources().getString(R.string.seleccion), String.valueOf(seleccion));
        map.put(getActivity().getResources().getString(R.string.sIdUsuario), String.valueOf(tabEventoPOJO.getI_idUsuario()));


        Log.e(TAG, "toUbicacion\n" + map.toString());
        return map;

    }

    /**
     * Selecciona una lista de eventos en función de la selección que se realice.
     *
     * @param tabEventoPOJO
     * @param seleccion
     */
    private void selectEventosCreados(final EventoPOJO tabEventoPOJO, int seleccion) {


        if (flag) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage(getString(R.string.sCargandoEventos));
            progressDialog.show();

        }
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_selectAllEvento),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        if (flag)
                            progressDialog.dismiss();

                        Log.e(getClass().getName(), "selectEventosCreados" + response);
                        try {
                            int success;

                            Utilidades utilidades = new Utilidades();
                            final ArrayList<EventoPOJO> elementos = new ArrayList<>();
                            JSONObject json = new JSONObject(response);


                            success = json.getInt(getResources().getString(R.string.success));

                            if (success == 1) {
                                JSONArray jsonArray = json.getJSONArray(getResources().getString(R.string.result));
                                Log.e(getClass().getName(), "success 1");


                                for (int i = 0; i < jsonArray.length(); i++) {

                                    EventoPOJO tabEventoPOJO = new EventoPOJO();
                                    tabEventoPOJO.JSONtoObject(getActivity(), jsonArray.getJSONObject(i));

                                    elementos.add(tabEventoPOJO);


                                }


                                ListAdapterEvento listAdapterEvento = new ListAdapterEvento(elementos, getActivity());
                                tabEventoPOJO.getListView().setAdapter(listAdapterEvento);
                                listAdapterEvento.notifyDataSetChanged();

                                addListenerToListView(tabEventoPOJO.getListView(), jsonArray);


                            } else if (success == 2 && flag) {
                                {
                                    Toast.makeText(getActivity(), (getString(R.string.sNoEventosCercanos)), Toast.LENGTH_LONG).show();
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putBoolean(getString(R.string.login), false);
                                    editor.apply();

                                }

                            } else if (success == 0 && sharedPreferences.getInt(getString(R.string.seleccion), 0) == 1) {

                                Log.e(getClass().getName(), "success 0 seleccion 1");
                                JSONObject jsonObject = new JSONObject();
                                Intent intent = new Intent(getActivity(), MenuPrincipal.class);

                                intent.putExtra(getResources().getString(R.string.sIdUsuario), tabEventoPOJO.getI_idUsuario());
                                intent.putExtra(getResources().getString(R.string.sIdSesion), iIdSesion);
                                intent.putExtra(getResources().getString(R.string.estado), tabEventoPOJO.getI_estado());

                                jsonObject = json.getJSONObject(getResources().getString(R.string.sResultado));
                                intent.putExtra(getResources().getString(R.string.tipoUsuario), jsonObject.getInt(getResources().getString(R.string.tipoUsuario)));
                                intent.putExtra(getResources().getString(R.string.password), jsonObject.getString(getResources().getString(R.string.password)));


                                utilidades.errorConsultaBBDD(getActivity(), getString(R.string.sNoEventos));


                            } else {

                                if (flag) {
                                    utilidades.errorConsultaBBDD(getActivity(), getString(R.string.sNoEventosCercanos));
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putBoolean(getString(R.string.login), false);
                                    editor.apply();
                                } else
                                    utilidades.errorConsultaBBDD(getActivity(), getString(R.string.sNoEventos));
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean(getString(R.string.login), false);
                        editor.apply();
                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        Toast.makeText(getActivity(), getString(R.string.errorConexion), Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        if (flag) {
                            progressDialog.dismiss();

                            editor.putBoolean(getString(R.string.login), false);
                            editor.apply();
                        }
                        /*
                        if (error instanceof TimeoutError) {

                            // note : may cause recursive invoke if always timeout.
                            selectEventosCreados(tabEventoPOJO,seleccion);
                        } */
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                return toUbicacion;
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


        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    /**
     * @param listView
     * @param jsonArray
     */
    private void addListenerToListView(ListView listView, JSONArray jsonArray) {
        final JSONArray _jsonArray = jsonArray;


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent i = new Intent(getActivity().getApplicationContext(), EventoSeleccionado.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                JSONObject jsonObject;

                try {
                    jsonObject = _jsonArray.getJSONObject(position);
                    tabEventoPOJO.JSONArraytoObject(getActivity(), _jsonArray);
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getResources().getString(R.string.sDATOS), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    assert jsonObject != null;
                    editor.putInt(getActivity().getResources().getString(R.string.sIdUsuario), jsonObject.getInt(getActivity().getResources().getString(R.string.sIdUsuario)));
                    editor.putInt(getActivity().getResources().getString(R.string.sIdEvento), jsonObject.getInt(getActivity().getResources().getString(R.string.sIdEvento)));
                    editor.putInt(getActivity().getResources().getString(R.string.sIdSesion), iIdSesion);
                    editor.putString(getString(R.string.fromFragment), getString(R.string.fragment_tab_menu_evento));
                    editor.putInt(getActivity().getResources().getString(R.string.estado), tabEventoPOJO.getI_estado());

                    editor.apply();


                    FragmentManager fragmentManager = activity.getSupportFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    fragmentManager.popBackStack();
                    ft.addToBackStack(getString(R.string.fragment_evento_listado));

                    ft.commit();

                    activity.startActivity(i);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

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

        switch (item.getItemId()) {
            case R.id.miIdSalir:

                Utilidades utilidades = new Utilidades();
                utilidades.salir(getActivity());
                return true;
            default:

                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


}
