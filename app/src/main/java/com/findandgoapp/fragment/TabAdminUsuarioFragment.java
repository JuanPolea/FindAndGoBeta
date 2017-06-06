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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.findandgoapp.activity.R;
import com.findandgoapp.activity.UsuarioListado;
import com.findandgoapp.custom.CustomFontTextView;
import com.findandgoapp.custom.CustomTypeFaceSpan;
import com.findandgoapp.pojo.AdminPOJO;
import com.findandgoapp.tools.Utilidades;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.Map;


/**
 * A placeholder fragment containing a simple view.
 */
public class TabAdminUsuarioFragment extends Fragment {

    private static final String TAG = "TabAdminUsuarioFragment";
    private static final AdminPOJO tabAdminUsuariosPOJO = new AdminPOJO();

    public TabAdminUsuarioFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        setHasOptionsMenu(true);


        View view = inflater.inflate(R.layout.fragment_tab_admin_usuario, container, false);

        tabAdminUsuariosPOJO.setIdTvEmpresaActivo((CustomFontTextView) view.findViewById(R.id.idTvEmpresaActivo));
        tabAdminUsuariosPOJO.setIdTvEmpresaPenalizado((CustomFontTextView) view.findViewById(R.id.idTvEmpresaPenalizado));
        tabAdminUsuariosPOJO.setIdTvEmpresaEliminado((CustomFontTextView) view.findViewById(R.id.idTvEmpresaEliminado));
        tabAdminUsuariosPOJO.setIdTvSumatorioVerticalEmpresa((CustomFontTextView) view.findViewById(R.id.idTvSumatorioVerticalEmpresa));

        tabAdminUsuariosPOJO.setIdTvArtistaActivo((CustomFontTextView) view.findViewById(R.id.idTvArtistaActivo));
        tabAdminUsuariosPOJO.setIdTvArtistaPenalizado((CustomFontTextView) view.findViewById(R.id.idTvArtistaPenalizado));
        tabAdminUsuariosPOJO.setIdTvArtistaEliminado((CustomFontTextView) view.findViewById(R.id.idTvArtistaEliminado));
        tabAdminUsuariosPOJO.setIdTvSumatorioVerticalArtista((CustomFontTextView) view.findViewById(R.id.idTvSumatorioVerticalArtista));

        tabAdminUsuariosPOJO.setIdTvAsisteActivo((CustomFontTextView) view.findViewById(R.id.idTvAsisteActivo));
        tabAdminUsuariosPOJO.setIdTvAsistePenalizado((CustomFontTextView) view.findViewById(R.id.idTvAsistePenalizado));
        tabAdminUsuariosPOJO.setIdTvAsisteEliminado((CustomFontTextView) view.findViewById(R.id.idTvAsisteEliminado));
        tabAdminUsuariosPOJO.setIdTvSumatorioVerticalAsistente((CustomFontTextView) view.findViewById(R.id.idTvSumatorioVerticalAsistente));

        tabAdminUsuariosPOJO.setIdTvSumatorioActivo((CustomFontTextView) view.findViewById(R.id.idTvSumatorioActivo));
        tabAdminUsuariosPOJO.setIdTvSumatorioPenalizado((CustomFontTextView) view.findViewById(R.id.idTvSumatorioPenalizado));
        tabAdminUsuariosPOJO.setIdTvSumatorioEliminado((CustomFontTextView) view.findViewById(R.id.idTvSumatorioEliminado));
        tabAdminUsuariosPOJO.setIdTvTotal((CustomFontTextView) view.findViewById(R.id.idTvTotal));

        tabAdminUsuariosPOJO.setIdTvSumatorioVerticalEmpresa((CustomFontTextView) view.findViewById(R.id.idTvSumatorioVerticalEmpresa));
        tabAdminUsuariosPOJO.setIdTvSumatorioVerticalArtista((CustomFontTextView) view.findViewById(R.id.idTvSumatorioVerticalArtista));
        tabAdminUsuariosPOJO.setIdTvSumatorioVerticalAsistente((CustomFontTextView) view.findViewById(R.id.idTvSumatorioVerticalAsistente));


        actualizarDatosUsuario();


        /**
         * Tipo-> Empresa = 2, Artista = 3 Asistente = 4 Todos=5
         * Estado-> Activo=0 Bloqueado=1, Eliminado =2 Todos=3;
         */
        addListenerToTextView(tabAdminUsuariosPOJO.getIdTvEmpresaActivo(), 2, 0);
        addListenerToTextView(tabAdminUsuariosPOJO.getIdTvEmpresaPenalizado(), 2, 1);
        addListenerToTextView(tabAdminUsuariosPOJO.getIdTvEmpresaEliminado(), 2, 2);
        addListenerToTextView(tabAdminUsuariosPOJO.getIdTvSumatorioVerticalEmpresa(), 2, 3);


        addListenerToTextView(tabAdminUsuariosPOJO.getIdTvArtistaActivo(), 3, 0);
        addListenerToTextView(tabAdminUsuariosPOJO.getIdTvArtistaPenalizado(), 3, 1);
        addListenerToTextView(tabAdminUsuariosPOJO.getIdTvArtistaEliminado(), 3, 2);
        addListenerToTextView(tabAdminUsuariosPOJO.getIdTvSumatorioVerticalArtista(), 3, 3);

        addListenerToTextView(tabAdminUsuariosPOJO.getIdTvAsisteActivo(), 4, 0);
        addListenerToTextView(tabAdminUsuariosPOJO.getIdTvAsistePenalizado(), 4, 1);
        addListenerToTextView(tabAdminUsuariosPOJO.getIdTvAsisteEliminado(), 4, 2);
        addListenerToTextView(tabAdminUsuariosPOJO.getIdTvSumatorioVerticalAsistente(), 4, 3);


        addListenerToTextView(tabAdminUsuariosPOJO.getIdTvSumatorioActivo(), 5, 0);
        addListenerToTextView(tabAdminUsuariosPOJO.getIdTvSumatorioPenalizado(), 5, 1);
        addListenerToTextView(tabAdminUsuariosPOJO.getIdTvSumatorioEliminado(), 5, 2);

        addListenerToTextView(tabAdminUsuariosPOJO.getIdTvTotal(), 5, 3);

        return view;

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
     * @param b
     * @param tipo
     * @desc Empresa = 2, Artista = 3 Asistente = 4 | Activo=0 Bloqueado=1, Eliminado =2
     */
    private void addListenerToTextView(final CustomFontTextView b, final int tipo, final int estado) {


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (b.getText().toString().equalsIgnoreCase(String.valueOf(0))) {

                    Toast toast = Toast.makeText(getActivity(), "No existen usuarios para esta penalización", Toast.LENGTH_SHORT);
                    toast.show();
                } else {


                    Intent i = new Intent(getActivity(), UsuarioListado.class);
                    //usuario bloqueado corresponde al id = 4
                    getActivity().finish();
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.DATOS), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt(getString(R.string.tipo), tipo);
                    editor.putInt(getString(R.string.estado), estado);

                    Log.e(TAG, "estado " + String.valueOf(estado) + " tipo " + String.valueOf(tipo));
                    editor.apply();
                    startActivity(i);
                }

            }
        });

    }

    /**
     *
     */
    private void actualizarDatosUsuario() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getString(R.string.sUrlInformePenalizados), new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {


                try {
                    JSONObject jsonObject = new JSONObject(s);
                    tabAdminUsuariosPOJO.rellenarMatrizUsuario(jsonObject);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(getClass().getName(), e.getMessage());
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


                return tabAdminUsuariosPOJO.toArray();
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


}


