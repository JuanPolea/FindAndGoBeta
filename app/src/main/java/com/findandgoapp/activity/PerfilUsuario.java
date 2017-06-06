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
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.findandgoapp.pojo.UsuarioPOJO;


public class PerfilUsuario extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        UsuarioPOJO usuarioPOJO = new UsuarioPOJO();

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.DATOS), Context.MODE_PRIVATE);
        usuarioPOJO.setI_idUsuario(sharedPreferences.getInt(getString(R.string.sIdUsuario), 0));
        usuarioPOJO.setI_idSesion(sharedPreferences.getInt(getString(R.string.sIdSesion), 0));
        usuarioPOJO.setI_tipo(sharedPreferences.getInt(getString(R.string.tipo), 0));
        usuarioPOJO.setS_password(sharedPreferences.getString(getString(R.string.password), getString(R.string.svacio)));
        usuarioPOJO.setI_estado(sharedPreferences.getInt(getResources().getString(R.string.estado), 0));
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(getString(R.string.sIdUsuario), usuarioPOJO.getI_idUsuario());
        editor.putInt(getString(R.string.sIdSesion), usuarioPOJO.getI_idSesion());
        editor.putString(getString(R.string.password), usuarioPOJO.getS_password());
        editor.putInt(getString(R.string.estado), usuarioPOJO.getI_estado());


        if (usuarioPOJO.getI_tipo() == 2) {
            setContentView(R.layout.activity_perfil_empresa);


        } else if (usuarioPOJO.getI_tipo() == 3) {
            setContentView(R.layout.activity_perfil_artista);


        } else {

            setContentView(R.layout.activity_perfil_asistente);

        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            toolbar.setTitleTextColor(getApplicationContext().getResources().getColor(R.color.verdeAgua));
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayShowHomeEnabled(true);
            }
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //do something you want

                    onBackPressed();

                }
            });
        } catch (NullPointerException e) {
            Log.e(getClass().getName(), e.getMessage());
        }
        Log.i(getClass().getName(), "onCreate");


        // mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        editor.apply();


    }

    @Override
    public void onBackPressed() {


        Log.e(getClass().getName(), "back");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.DATOS), Context.MODE_PRIVATE);
        String modo = sharedPreferences.getString(getString(R.string.modo), getString(R.string.svacio));
        int count = fragmentManager.getBackStackEntryCount();

        if (count == 0) {

            if (modo.equalsIgnoreCase(getString(R.string.crear))) {
                super.onBackPressed();
                Intent i = new Intent(PerfilUsuario.this, UsuarioNuevo.class);

                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);

                Log.e(getClass().getName(), "back");
                startActivity(i);
            } else if (modo.equalsIgnoreCase(getString(R.string.consultar))) {
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStack();
                } else {
                    /*Intent i = new Intent(PerfilUsuario.this, MenuPrincipal.class);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString(getResources().getString(R.string.fromFragment), getResources().getString(R.string.title_activity_menu_ppal));
                    editor.putInt(getString(R.string.tab), 1);
                    editor.apply();
                    //getActivity().finish();
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);*/
                    super.onBackPressed();

                }
            }


            //additional code
        } else {
            getSupportFragmentManager().popBackStack();
            fragmentTransaction.commit();
            Log.e(getClass().getName(), "pop");
        }

    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {

        // The action bar home/up action should open or close the drawer.

        switch (item.getItemId()) {

            case android.R.id.home:

                //mDrawer.openDrawer(GravityCompat.START);

                return true;

        }


        return super.onOptionsItemSelected(item);

    }

}
