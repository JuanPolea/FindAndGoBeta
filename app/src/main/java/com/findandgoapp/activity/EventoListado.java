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

package com.findandgoapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.findandgoapp.fragment.EventoFragment;

public class EventoListado extends AppCompatActivity {


    private int idSesion;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getResources().getString(R.string.sDATOS), Context.MODE_PRIVATE);

        idSesion = sharedPreferences.getInt(getResources().getString(R.string.sIdSesion), 0);
        String fromFragment = sharedPreferences.getString(getString(R.string.fromFragment), getString(R.string.svacio));

        if (!fromFragment.equalsIgnoreCase(getString(R.string.fragment_notificaciones))) {
            setContentView(R.layout.activity_evento_listado);


            toolbar = (Toolbar) findViewById(R.id.toolbar);

            setSupportActionBar(toolbar);
            toolbar.setTitleTextColor(getApplicationContext().getResources().getColor(R.color.verdeAgua));
            try {
                toolbar.setTitle(R.string.title_activity_listado);
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setDisplayShowHomeEnabled(true);
                }
            } catch (NullPointerException e) {
                Log.e(getClass().getName(), e.getMessage());
            }

            if (fromFragment.equalsIgnoreCase(getString(R.string.fragment_alerta))) {

                this.setTitle(R.string.listadoEvento);
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {


                        onBackPressed();
                    }
                });
            } else if (fromFragment.equalsIgnoreCase(getString(R.string.fragment_tab_menu_evento))) {

                this.setTitle(R.string.listadoEvento);
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        onBackPressed();
                    }
                });
            } else if (fromFragment.equalsIgnoreCase(getString(R.string.fragment_admin_usuario_penalizar))) {

                this.setTitle(R.string.listadoEvento);
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        onBackPressed();
                    }
                });
            }

        } else {
            setContentView(R.layout.activity_evento_notificacion);


            toolbar = (Toolbar) findViewById(R.id.toolbar);

            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            try {

                toolbar.setVisibility(View.VISIBLE);
                toolbar.setEnabled(true);
                toolbar.setTitle(R.string.title_activity_listado);
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setDisplayShowHomeEnabled(true);
                }
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.sDATOS), Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(getString(R.string.fromFragment), getString(R.string.title_activity_menu_ppal));
                        editor.apply();
                        onBackPressed();
                    }
                });
            } catch (NullPointerException e) {
                Log.e(getClass().getName(), e.getMessage());
            }


        }


    }


    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();

        SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.sDATOS), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String tag = sharedPreferences.getString(getString(R.string.fromFragment), getString(R.string.svacio));
        Intent i;
        if (count == 0) {


            if (idSesion == 1) {
                super.onBackPressed();

            } else if (tag.equalsIgnoreCase(getString(R.string.fragment_evento_consulta))) {

                EventoFragment fragment1 = new EventoFragment();
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(fragment1, getString(R.string.fragment_evento_consulta));
                fragmentTransaction.addToBackStack(null);
                editor.putString(getString(R.string.modo), getString(R.string.consultar));
                editor.apply();
                toolbar.setTitle(R.string.sConsultar);
                fragmentTransaction.commit();


            } else if (tag.equalsIgnoreCase(getString(R.string.fragment_notificaciones))) {


                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentManager.popBackStack();

                fragmentTransaction.commit();


            } else if (tag.equalsIgnoreCase(getString(R.string.fragment_alerta))) {

                super.onBackPressed();


            } else if (tag.equalsIgnoreCase(getString(R.string.fragment_tab_menu_evento))) {

                finish();


            } else if (tag.equalsIgnoreCase(getString(R.string.fragment_admin_usuario_penalizar))) {

                finish();


            } else {
                i = new Intent(getApplicationContext(), MenuPrincipal.class);
                editor.putInt(getResources().getString(R.string.sIdSesion), idSesion);
                editor.putInt(getResources().getString(R.string.tab), 1);
                editor.apply();
                finish();
                startActivity(i);
            }


        } else {


            if (tag.equalsIgnoreCase(getString(R.string.fragment_evento_consulta))) {

                EventoFragment fragment = new EventoFragment();
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction frTransaction = fragmentManager.beginTransaction();
                fragmentManager.popBackStack();
                frTransaction.add(fragment, getString(R.string.fragment_evento_consulta));
                frTransaction.addToBackStack(getString(R.string.fragment_evento_consulta));
                editor.putString(getString(R.string.modo), getString(R.string.consultar));
                editor.apply();
                frTransaction.commit();
            } else {

                i = new Intent(getApplicationContext(), MenuPrincipal.class);
                editor.putInt(getResources().getString(R.string.sIdSesion), idSesion);
                editor.putInt(getResources().getString(R.string.tab), 1);
                editor.apply();
                startActivity(i);
            }


        }
    }


}
