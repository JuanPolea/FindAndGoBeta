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

import com.findandgoapp.pojo.UsuarioPOJO;


public class Informacion extends AppCompatActivity

{


    private final UsuarioPOJO usuarioPOJO = new UsuarioPOJO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);


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
        this.setTitle(getString(R.string.title_activity_informacion));

        SharedPreferences sharedPreferences = this.getApplicationContext().getSharedPreferences(getResources().getString(R.string.sDATOS), Context.MODE_PRIVATE);


        usuarioPOJO.setI_idSesion(sharedPreferences.getInt(getResources().getString(R.string.sIdSesion), 0));
        usuarioPOJO.setI_idUsuario(sharedPreferences.getInt(getResources().getString(R.string.sIdSesion), 0));

        usuarioPOJO.setI_estado(sharedPreferences.getInt(getResources().getString(R.string.estado), 0));
        usuarioPOJO.setS_password(sharedPreferences.getString(getResources().getString(R.string.password), null));
        usuarioPOJO.setI_tipo(sharedPreferences.getInt(getResources().getString(R.string.tipoUsuario), 0));


    }

    @Override
    public void onBackPressed() {


        int count = getSupportFragmentManager().getBackStackEntryCount();


        SharedPreferences sharedPreferences = this.getSharedPreferences(this.getResources().getString(R.string.DATOS), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (count == 0) {
            if (usuarioPOJO.getI_idSesion() == 1 || usuarioPOJO.getI_idUsuario() == 1) {
                editor.putInt(this.getResources().getString(R.string.sIdSesion), usuarioPOJO.getI_idSesion());
                editor.putInt(this.getResources().getString(R.string.sIdUsuario), usuarioPOJO.getI_idUsuario());
                editor.putInt(this.getResources().getString(R.string.tab), 1);

                Intent i = new Intent(this, TabAdmin.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);

                startActivity(i);
                finish();
                overridePendingTransition(0, 0);
            } else {
                Log.e(getClass().getName(), "backPressed");


                editor.putInt(this.getResources().getString(R.string.sIdSesion), usuarioPOJO.getI_idSesion());
                editor.putInt(this.getResources().getString(R.string.sIdUsuario), usuarioPOJO.getI_idUsuario());
                editor.putInt(this.getResources().getString(R.string.tipoUsuario), usuarioPOJO.getI_tipo());
                editor.putString(this.getResources().getString(R.string.password), usuarioPOJO.getS_password());
                editor.putInt(this.getResources().getString(R.string.estado), usuarioPOJO.getI_estado());
                editor.putInt(this.getResources().getString(R.string.tab), 1);

                if (usuarioPOJO.getI_estado() != 0)
                    editor.putBoolean(this.getResources().getString(R.string.bloqueado), true);
                else
                    editor.putBoolean(this.getResources().getString(R.string.bloqueado), false);


                Intent i = new Intent(this, MenuPrincipal.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);

                startActivity(i);
                finish();
                overridePendingTransition(0, 0);
            }


        } else {
            if (usuarioPOJO.getI_idSesion() == 1 || usuarioPOJO.getI_idUsuario() == 1) {
                super.onBackPressed();
            } else {
                getSupportFragmentManager().popBackStack();
                Log.e(getClass().getName(), "pop");
            }
            editor.apply();

        }

    }


}
