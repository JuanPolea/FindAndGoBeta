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
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class RealizarFotoActivity extends AppCompatActivity {


    private SharedPreferences sharedPreferences;
    private int idEvento;
    private int idSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_foto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        TextView toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar);
        toolbarTitle.setText(getResources().getString(R.string.title_activity_realizar_foto));
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), getResources().getString(R.string.fontAmaticRegularBold));
        toolbarTitle.setTypeface(myTypeface);
        toolbar.setTitleTextColor(getApplicationContext().getResources().getColor(R.color.verdeAgua));

        sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.DATOS), Context.MODE_PRIVATE);
        idEvento = sharedPreferences.getInt(getResources().getString(R.string.sIdEvento), 0);

        idSesion = sharedPreferences.getInt(getString(R.string.sIdSesion), 0);

    }

    private void salir() {


        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(getString(R.string.sIdSesion), idSesion);
        editor.putInt(getString(R.string.sIdEvento), idEvento);
        Intent intent = new Intent(RealizarFotoActivity.this, EventoSeleccionado.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        editor.apply();
        finish();
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        salir();

    }
}
