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

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.findandgoapp.activity.AdminEventoListado;
import com.findandgoapp.activity.R;
import com.findandgoapp.custom.CustomFontTextView;
import com.findandgoapp.custom.CustomTypeFaceSpan;
import com.findandgoapp.pojo.EventoPOJO;
import com.findandgoapp.tools.Utilidades;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;


/**
 * A placeholder fragment containing a simple view.
 */
public class TabAdminEventoFragment extends Fragment {

    private CustomFontTextView tvNumConfirmados;
    private CustomFontTextView tvNumDenunciados1;
    private CustomFontTextView tvNumDenunciados2;
    private CustomFontTextView tvNumDenunciados3;
    private CustomFontTextView tvtotal;
    private CustomFontTextView tvNumTotal;
    private CustomFontTextView tvNumActual;
    private CustomFontTextView tvNumSugerencia;


    private final static EventoPOJO tabAdminEventosPOJO = new EventoPOJO();
    private static final String TAG = "TabAdminEventoFragment";

    public TabAdminEventoFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(getString(R.string.sDATOS), Context.MODE_PRIVATE);

        setHasOptionsMenu(true);

        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_tab_admin_evento, container, false);

        setTvNumConfirmados((CustomFontTextView) view.findViewById(R.id.idTvNumEventoConfirmado));
        setTvNumDenunciados1((CustomFontTextView) view.findViewById(R.id.idTvNumEventoDenuciadoTipo1));
        setTvNumDenunciados2((CustomFontTextView) view.findViewById(R.id.idTvNumEventoDenuciadoTipo2));
        setTvNumDenunciados3((CustomFontTextView) view.findViewById(R.id.idTvNumEventoDenuciadoTipo3));
        setTvNumTotal((CustomFontTextView) view.findViewById(R.id.idTvNumTotalEventos));
        setTvtotal((CustomFontTextView) view.findViewById(R.id.idTvTotalEventos));
        setTvNumActual((CustomFontTextView) view.findViewById(R.id.idTvNumTotalActual));
        setTvNumSugerencia((CustomFontTextView) view.findViewById(R.id.idTvNumSugerencias));
        tabAdminEventosPOJO.inicializarDenunciados();
        tabAdminEventosPOJO.inicializarConfirmados();
        actualizarDatosEventos();
        return view;

    }

    /**
     *
     */
    private void actualizarDatosEventos() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_informeEventos),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "actualizarDatosEventos " + response); //If we are getting success from server

                        try {
                            JSONObject json = new JSONObject(response);
                            getTvNumDenunciados1().setText(json.getString(getString(R.string.tipo1)));
                            addListener(getActivity(), getTvNumDenunciados1(), 1);

                            getTvNumDenunciados2().setText(json.getString(getString(R.string.tipo2)));
                            addListener(getActivity(), getTvNumDenunciados2(), 2);

                            getTvNumDenunciados3().setText(json.getString(getString(R.string.tipo3)));
                            addListener(getActivity(), getTvNumDenunciados3(), 3);

                            getTvNumConfirmados().setText(json.getString(getString(R.string.tipo4)));
                            addListener(getActivity(), getTvNumConfirmados(), 4);


                            getTvNumTotal().setText(json.getString(getString(R.string.totalCreados)));
                            addListener(getActivity(), getTvNumTotal(), 5);

                            getTvNumActual().setText(json.getString(getString(R.string.totalActuales)));
                            addListener(getActivity(), getTvNumActual(), 6);

                            getTvNumSugerencia().setText(json.getString(getString(R.string.sugerencia)));
                            addListener(getActivity(), getTvNumSugerencia(), 7);
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

        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

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
        Log.d(TAG, "onOptionsItemSelected2");

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


    /**
     * @param c
     * @param textView
     * @param tipo
     */
    private void addListener(final Context c, final CustomFontTextView textView, final int tipo) {


        int total;
        total = Integer.parseInt(textView.getText().toString());


        final int finalTotal = total;

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (finalTotal == 0) {

                    Toast toast = Toast.makeText(c, c.getResources().getString(R.string.sNoEventos), Toast.LENGTH_SHORT);
                    toast.show();


                } else {

                    Log.e(TAG, String.valueOf(finalTotal) + "|" + String.valueOf(tipo));

                    Intent i = new Intent(c.getApplicationContext(), AdminEventoListado.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.DATOS), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt(getString(R.string.sIdSesion), 1);
                    editor.putInt(getString(R.string.tipo), tipo);
                    editor.putBoolean(getString(R.string.estado), true);

                    editor.apply();
                    startActivity(i);
                }

            }
        });

    }


    private CustomFontTextView getTvNumConfirmados() {
        return tvNumConfirmados;
    }

    private void setTvNumConfirmados(CustomFontTextView tvNumConfirmados) {
        this.tvNumConfirmados = tvNumConfirmados;
    }

    private CustomFontTextView getTvNumDenunciados2() {
        return tvNumDenunciados2;
    }

    private void setTvNumDenunciados2(CustomFontTextView tvNumDenunciados2) {
        this.tvNumDenunciados2 = tvNumDenunciados2;
    }

    private CustomFontTextView getTvNumDenunciados1() {
        return tvNumDenunciados1;
    }

    private void setTvNumDenunciados1(CustomFontTextView tvNumDenunciados1) {
        this.tvNumDenunciados1 = tvNumDenunciados1;
    }

    private CustomFontTextView getTvNumDenunciados3() {
        return tvNumDenunciados3;
    }

    private void setTvNumDenunciados3(CustomFontTextView tvNumDenunciados3) {
        this.tvNumDenunciados3 = tvNumDenunciados3;
    }

    private CustomFontTextView getTvNumTotal() {
        return tvNumTotal;
    }

    private void setTvNumTotal(CustomFontTextView tvNumTotal) {
        this.tvNumTotal = tvNumTotal;
    }

    private void setTvtotal(CustomFontTextView tvtotal) {
        this.tvtotal = tvtotal;
    }

    private CustomFontTextView getTvNumActual() {
        return tvNumActual;
    }

    private void setTvNumActual(CustomFontTextView tvNumActual) {
        this.tvNumActual = tvNumActual;
    }

    private CustomFontTextView getTvNumSugerencia() {
        return tvNumSugerencia;
    }

    private void setTvNumSugerencia(CustomFontTextView tvNumSugerencia) {
        this.tvNumSugerencia = tvNumSugerencia;
    }
}
