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

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
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
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.findandgoapp.activity.Alerta;
import com.findandgoapp.activity.EventoListado;
import com.findandgoapp.activity.R;
import com.findandgoapp.adapter.ListAdapterAlerta;
import com.findandgoapp.custom.CustomTypeFaceSpan;
import com.findandgoapp.pojo.AlertaPOJO;
import com.findandgoapp.tools.Utilidades;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;


/**
 * A placeholder fragment containing a simple view.
 */

public class TabMenuAlertaFragment extends Fragment {


    private static JSONArray jsonArray;
    private static boolean isAlerta = true;
    private SharedPreferences sharedPreferences;
    private AlertaPOJO tabAlertaPOJO;

    public TabMenuAlertaFragment() {

    }

    /**
     * @param c
     * @param jsonArray
     * @param tabAlertaPOJO
     */
    private static void Notificacion(Context c, JSONArray jsonArray, AlertaPOJO tabAlertaPOJO) {


        Intent intent = new Intent(c, EventoListado.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(c, 0, intent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_CANCEL_CURRENT);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);


        boolean useWhiteIcon = (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP);

        NotificationManager notificationManager = (NotificationManager) c.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(c)
                .setSmallIcon(useWhiteIcon ? R.drawable.ic_logo_lollipop : R.drawable.ic_logo_user)
                .setContentTitle(c.getResources().getString(R.string.sNotificaciones))
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true)
                .setContentText(c.getResources().getString(R.string.sNotificacionesOk))
                .setContentIntent(pendingIntent)
                .setContentInfo("info");

        notificationManager.notify(0, mBuilder.build());

        SharedPreferences sharedPreferences = c.getSharedPreferences(c.getResources().getString(R.string.DATOS), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(c.getResources().getString(R.string.sIdSesion), tabAlertaPOJO.getI_idSesion());

        editor.putInt(c.getResources().getString(R.string.sIdUsuario), tabAlertaPOJO.getI_idSesion());
        editor.putInt(c.getResources().getString(R.string.seleccion), 1);
        editor.putString(c.getResources().getString(R.string.sRes), jsonArray.toString());
        editor.putString(c.getString(R.string.fromFragment), c.getString(R.string.fragment_notificaciones));
        editor.apply();

        isAlerta = false;


    }

    public static void setIsAlerta(boolean isAlerta) {
        TabMenuAlertaFragment.isAlerta = isAlerta;
    }

    @Override
    public void onResume() {

        super.onResume();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabAlertaPOJO = new AlertaPOJO(getActivity().getApplicationContext());
        sharedPreferences = getActivity().getSharedPreferences(getResources().getString(R.string.sDATOS), Context.MODE_PRIVATE);
        tabAlertaPOJO.setI_idSesion(sharedPreferences.getInt(getResources().getString(R.string.sIdSesion), 0));
        tabAlertaPOJO.setI_idUsuario(sharedPreferences.getInt(getResources().getString(R.string.sIdSesion), 0));

        tabAlertaPOJO.setI_estado(sharedPreferences.getInt(getResources().getString(R.string.estado), 0));

        selectAlertaNotificacion();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragment_alerta_item_list, container, false);

        tabAlertaPOJO = new AlertaPOJO(getActivity().getApplicationContext());
        sharedPreferences = getActivity().getSharedPreferences(getResources().getString(R.string.sDATOS), Context.MODE_PRIVATE);
        tabAlertaPOJO.setI_idSesion(sharedPreferences.getInt(getResources().getString(R.string.sIdSesion), 0));
        tabAlertaPOJO.setI_idUsuario(sharedPreferences.getInt(getResources().getString(R.string.sIdSesion), 0));

        tabAlertaPOJO.setI_estado(sharedPreferences.getInt(getResources().getString(R.string.estado), 0));

        Log.e(getClass().getName(), "onCreateView " + tabAlertaPOJO.getI_idSesion() + tabAlertaPOJO.isB_estado() + "iEstado " + tabAlertaPOJO.getI_estado());


        tabAlertaPOJO.setListView((ListView) view.findViewById(R.id.alertaItemList));

        selectDatosAlertasCreadas();
        selectAlertaNotificacion();

        return view;
    }

    private void selectAlertaNotificacion() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getString(R.string.sUrl_selectAlertaNotificacion), new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                Log.e(getClass().getName(), "selectAlertaNotificacion " + s);

                int success;
                try {

                    JSONObject json = new JSONObject(s);

                    success = json.getInt(getActivity().getResources().getString(R.string.success));

                    if (success == 1) {
                        Log.e(getClass().getName(), "getJSONArray " + json.getJSONArray(getString(R.string.result)));


                        jsonArray = json.getJSONArray(getString(R.string.result));


                        if (isAlerta)
                            Notificacion(getActivity(), jsonArray, tabAlertaPOJO);
                        else
                            Log.e(getClass().getName(), "FALSE");
                    } else {
                        Log.e(getClass().getName(), getString(R.string.sNoSeHanEncontradoDatos));
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(getClass().getName(), e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(), getString(R.string.errorConexion), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                return tabAlertaPOJO.formatoId(getActivity());
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    /**
     *
     */
    private void selectDatosAlertasCreadas() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getString(R.string.sUrlInformeAlerta), new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                Log.e(getClass().getName(), "selectDatosAlertasCreadas " + s);

                final ArrayList<AlertaPOJO> elementos = new ArrayList<>();
                JSONArray array;
                int success;

                try {
                    JSONObject json = new JSONObject(s);
                    success = json.getInt(getResources().getString(R.string.success));

                    if (success == 1) {

                        array = new JSONArray(json.getString(getResources().getString(R.string.sResultado)));


                        for (int i = 0; i < array.length(); i++) {

                            AlertaPOJO _tabAlertaPojo = new AlertaPOJO(getActivity());


                            _tabAlertaPojo.toAlertaListado(getActivity(), array.getJSONObject(i));
                            _tabAlertaPojo.setI_idSesion(tabAlertaPOJO.getI_idSesion());
                            elementos.add(_tabAlertaPojo);

                        }


                        ListAdapterAlerta listAdapterAlerta = new ListAdapterAlerta(elementos, getActivity());
                        listAdapterAlerta.notifyDataSetChanged();

                        tabAlertaPOJO.getListView().setAdapter(listAdapterAlerta);
                        addListenerToListView(tabAlertaPOJO.getListView(), array);

                    } else {
                        Log.e(getClass().getName(), getString(R.string.sNotieneNotificaciones));
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(getClass().getName(), e.getMessage());
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Toast.makeText(getActivity(), getString(R.string.errorConexion), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                return tabAlertaPOJO.formatoId(getActivity());
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    /**
     * @param listView
     * @param array
     */
    private void addListenerToListView(ListView listView, JSONArray array) {


        final JSONArray _jsonArray = array;
        /**
         *
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getActivity(), Alerta.class);

                JSONObject jsonObject;
                try {
                    jsonObject = _jsonArray.getJSONObject(position);


                    tabAlertaPOJO.JSONArraytoObject(getActivity(), _jsonArray);

                    i.putExtra(jsonObject.getString(getResources().getString(R.string.sIdAlerta)), jsonObject.getString(getResources().getString(R.string.sIdAlerta)));
                    i.putExtra(jsonObject.getString(getResources().getString(R.string.sIdUsuario)), tabAlertaPOJO.getI_idUsuario());
                    i.putExtra(jsonObject.getString(getResources().getString(R.string.sIdSesion)), tabAlertaPOJO.getI_idSesion());

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Log.e(getClass().getName(), e.getMessage());
                }
                Log.e(getClass().getName(), "Termina on clik" + _jsonArray.toString());
                startActivity(i);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentAlertaItemListItemList, new EventoListadoFragment(), getString(R.string.fragment_evento_listado));
                //fragmentTransaction.commit();
            }
        });


    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        if (requestCode == 1) {

            Log.e(getClass().getName(), "startActivityForResult");
            Notificacion(getActivity(), jsonArray, tabAlertaPOJO);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            Log.e(getClass().getName(), bundle.toString());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

}
