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
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.findandgoapp.activity.EventoSeleccionado;
import com.findandgoapp.activity.R;
import com.findandgoapp.adapter.ListAdapterEvento;
import com.findandgoapp.pojo.EventoPOJO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juanpolea on 4/08/16.
 */
public class EventoListadoFragment extends Fragment {


    private static final EventoPOJO tabEventoPOJO = new EventoPOJO();
    private static int i_idSesion;
    private String fromFragment;
    private SharedPreferences sharedPreferences;
    private Toolbar toolbar;
    private FragmentManager fragmentManager;
    private SharedPreferences.Editor editor;

    public EventoListadoFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

    }

    @Override
    public void onResume() {
        super.onResume();
        sharedPreferences = getActivity().getApplicationContext().getSharedPreferences("DATOS", Context.MODE_PRIVATE);
        String fromFragment = sharedPreferences.getString("fromFragment", "vacio");
        if (!fromFragment.equalsIgnoreCase("fragment_notificaciones") && toolbar != null)
            toolbar.setTitle(getString(R.string.title_activity_evento_listado));
        editor = sharedPreferences.edit();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_evento_item_list, container, false);

        fragmentManager = getActivity().getSupportFragmentManager();

        ListView listView = ((ListView) rootView.findViewById(R.id.eventoItemList));

        sharedPreferences = getActivity().getApplicationContext().getSharedPreferences("DATOS", Context.MODE_PRIVATE);
        tabEventoPOJO.setI_idUsuario(sharedPreferences.getInt(getActivity().getResources().getString(R.string.sIdUsuario), 0));
        tabEventoPOJO.setI_seleccion(sharedPreferences.getInt(getActivity().getResources().getString(R.string.seleccion), 0));
        fromFragment = sharedPreferences.getString(getString(R.string.fromFragment), getString(R.string.svacio));


        // Log.e(getClass().getName(), "SHARE" + sharedPreferences.getString(getActivity().getResources().getString(R.string.resultado), null));
        i_idSesion = sharedPreferences.getInt(getActivity().getResources().getString(R.string.sIdSesion), 0);
        getActivity().setTitle(R.string.title_activity_menu_ppal);
        JSONArray array;

        if (tabEventoPOJO.getI_seleccion() == 2) {

            ArrayList<EventoPOJO> elementos = new ArrayList<>();
            try {

                array = new JSONArray(sharedPreferences.getString(getActivity().getResources().getString(R.string.resultado), null));

                JSONArray jsonArray = new JSONArray();


                for (int i = 0; i < array.length(); i++) {

                    for (int j = 0; j < array.getJSONArray(i).length(); j++) {


                        EventoPOJO tabEventoPOJO = new EventoPOJO();
                        tabEventoPOJO.JSONtoObject(getActivity(), array.getJSONArray(i).getJSONObject(j));
                        elementos.add(tabEventoPOJO);
                        jsonArray.put(array.getJSONArray(i).getJSONObject(j));

                    }

                }

                ListAdapterEvento listAdapterEvento = new ListAdapterEvento(elementos, getActivity());
                listView.setAdapter(listAdapterEvento);
                listAdapterEvento.notifyDataSetChanged();

                addListenerToListView(listView, jsonArray);


            } catch (JSONException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();


            }


        } else if (tabEventoPOJO.getI_seleccion() == 1) {


            getActivity().setTitle(R.string.listadoEvento);
            ArrayList<EventoPOJO> elementos = new ArrayList<>();
            try {

                array = new JSONArray(sharedPreferences.getString(getActivity().getResources().getString(R.string.resultado), null));


                if (array == null) {
                    Toast.makeText(getActivity(), getString(R.string.noExistenDatos), Toast.LENGTH_SHORT).show();
                    getActivity().finish();

                } else {


                    for (int i = 0; i < array.length(); i++) {

                        EventoPOJO tabEventoPOJO = new EventoPOJO();
                        tabEventoPOJO.JSONtoObject(getActivity(), array.getJSONObject(i));
                        Log.w(getClass().getName(), "Inserta en elementos: " + array.getJSONObject(i).toString());
                        elementos.add(tabEventoPOJO);
                    }

                    ListAdapterEvento listAdapterEvento = new ListAdapterEvento(elementos, getActivity());
                    listView.setAdapter(listAdapterEvento);
                    listAdapterEvento.notifyDataSetChanged();

                    addListenerToListView(listView, array);

                }

            } catch (JSONException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        try {


            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            if (toolbar != null) {

                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                fromFragment = sharedPreferences.getString(getString(R.string.fromFragment), getString(R.string.svacio));
                toolbar.setVisibility(View.VISIBLE);
                toolbar.setEnabled(true);


                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //do something you want

                        //int count = getActivity().getSupportFragmentManager().getBackStackEntryCount();

                        fromFragment = sharedPreferences.getString(getString(R.string.fromFragment), getString(R.string.svacio));
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        if (isAdded()) {


                            if (fromFragment.equalsIgnoreCase(getString(R.string.fragment_evento_consulta))) {


                                toolbar.setTitle(R.string.consultar);

                                fragmentTransaction.replace(R.id.fragment_evento_item_list, new EventoFragment(), getString(R.string.fragment_evento_item_list));


                                //Log.e(getClass().getName(), "sesion " + i_idSesion + " idUS " + jsonObject.getInt("idUsuario") + " idEv " + jsonObject.getInt("idEvento"));
                                SharedPreferences sharedPreferences1 = getActivity().getApplicationContext().getSharedPreferences("DATOS", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences1.edit();

                                editor.putString(getString(R.string.fromFragment), getString(R.string.fragment_evento_listado));
                                editor.apply();
                                fromFragment = sharedPreferences1.getString(getString(R.string.fromFragment), getString(R.string.svacio));
                                Log.e(getClass().getName(), "pop" + fragmentManager.getBackStackEntryCount());
                                // fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                                Log.e(getClass().getName(), "pop2" + fragmentManager.getBackStackEntryCount());
                                fragmentTransaction.addToBackStack(getString(R.string.fragment_evento_listado));
                                fragmentTransaction.commit();


                            } else {

                                Log.e(getClass().getName(), "click");
                                fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                                toolbar.setTitle(R.string.title_activity_menu_ppal);
                                editor.putString("fromFragment",
                                        "MenuPpal");
                                editor.apply();


                            }

                        } else {
                            Log.e(getClass().getName(), "isAdded" + fragmentManager.getBackStackEntryCount());
                            //Intent intent = new Intent(getActivity(),MenuPrincipal.class);
                            //startActivity(intent);

                            toolbar.setTitle(R.string.title_activity_menu_ppal);
                            //fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                            //fragmentTransaction.commit();
                            Log.e(getClass().getName(), "isAdded-" + fragmentManager.getBackStackEntryCount());

                            List<Fragment> list = fragmentManager.getFragments();

                            boolean flag = true;
                            for (final Fragment f : list) {
                                if (f != null) {
                                    if (flag) {
                                        f.getActivity().onBackPressed();
                                        flag = false;
                                    }
                                    Log.e(getClass().getName(), f.getTag());
                                } else
                                    Log.e(getClass().getName(), "null");
                            }


                        }


                    }
                });
            }
        } catch (ExceptionInInitializerError e) {
            e.printStackTrace();
            Log.e(getClass().getName(), e.getMessage());
        }
        return rootView;
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


                Log.i(getClass().getName(), _jsonArray.toString());
                Intent i = new Intent(getActivity().getApplicationContext(), EventoSeleccionado.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                JSONObject jsonObject;

                try {

                    jsonObject = _jsonArray.getJSONObject(position);
                    Log.e(getClass().getName(), "JSON-->\n " + jsonObject.toString());
                    tabEventoPOJO.JSONtoObject(getActivity(), _jsonArray.getJSONObject(position));
                    Log.e(getClass().getName(), "JSON-1->\n " + tabEventoPOJO.getI_idEvento());
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getApplicationContext().getResources().getString(R.string.sDATOS),
                            Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    //Log.e(getClass().getName(), "sesion " + i_idSesion + " idUS " + jsonObject.getInt("idUsuario") + " idEv " + jsonObject.getInt("idEvento"));
                    editor.putInt(getActivity().getApplicationContext().getResources().getString(R.string.sIdSesion), i_idSesion);
                    editor.putInt(getActivity().getApplicationContext().getResources().getString(R.string.sIdEvento), jsonObject.getInt(getActivity().getApplicationContext().getResources().getString(R.string.sIdEvento)));
                    editor.putInt(getActivity().getApplicationContext().getResources().getString(R.string.sIdUsuario), jsonObject.getInt(getActivity().getApplicationContext().getResources().getString(R.string.sIdUsuario)));
                    editor.putInt(getActivity().getApplicationContext().getResources().getString(R.string.estado), i_estado);
                    editor.putString(getString(R.string.fromFragment), getString(R.string.fragment_evento_listado));

                    editor.apply();
                    getActivity().startActivity(i);


                } catch (JSONException e) {
                    Log.e(getClass().getName(), e.getMessage());
                }


                Log.e(getClass().getName(), "addListenerToListView" + _jsonArray.toString());

            }
        });


    }


}