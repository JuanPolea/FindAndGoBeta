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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.findandgoapp.activity.AdminUsuarioPenalizar;
import com.findandgoapp.activity.R;
import com.findandgoapp.adapter.ListAdapterListadoPorNombre;
import com.findandgoapp.pojo.UsuarioPOJO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class UsuarioPorNombreFragment extends Fragment {

    private final UsuarioPOJO usuario = new UsuarioPOJO();

    public UsuarioPorNombreFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_usuario_por_nombre, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DATOS", Context.MODE_PRIVATE);


        usuario.setListView((ListView) view.findViewById(R.id.listaPorNombre));

        String json = sharedPreferences.getString(getString(R.string.sIdUsuario), getString(R.string.svacio));
        try {
            JSONArray jsonArray = new JSONArray(json);

            Log.e(getClass().getName(), json);
            ArrayList<UsuarioPOJO> usuarioPOJOs = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {

                UsuarioPOJO usuarioPOJO = new UsuarioPOJO();
                usuarioPOJO.JSONToNombre(jsonArray.getJSONObject(i));

                usuarioPOJOs.add(usuarioPOJO);

                Log.e(getClass().getName(), usuarioPOJOs.get(i).getS_nombre());

            }

            ListAdapterListadoPorNombre listAdapter = new ListAdapterListadoPorNombre(usuarioPOJOs, getActivity());
            usuario.getListView().setAdapter(listAdapter);
            listAdapter.notifyDataSetChanged();
            addListenerToListView(usuario.getListView(), jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return view;


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
                Intent i = new Intent(getActivity().getApplicationContext(), AdminUsuarioPenalizar.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                JSONObject jsonObject;

                try {

                    jsonObject = _jsonArray.getJSONObject(position);
                    Log.e(getClass().getName(), "JSON-->\n " + jsonObject.toString());

                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getApplicationContext().getResources().getString(R.string.sDATOS),
                            Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putInt(getActivity().getApplicationContext().getResources().getString(R.string.sIdUsuario), jsonObject.getInt(getActivity().getApplicationContext().getResources().getString(R.string.sIdUsuario)));

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
