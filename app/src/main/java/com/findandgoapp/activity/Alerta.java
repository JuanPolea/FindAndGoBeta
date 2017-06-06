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

package com.findandgoapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.findandgoapp.custom.CustomFontTextView;
import com.findandgoapp.fragment.DialogSemana;

public class Alerta extends AppCompatActivity implements DialogSemana.NoticeDialogListener {

    private int idSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerta);

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.DATOS), Context.MODE_PRIVATE);
        idSesion = sharedPreferences.getInt(getString(R.string.sIdSesion), 0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            toolbar.setTitleTextColor(getApplicationContext().getResources().getColor(R.color.verdeAgua));
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayShowHomeEnabled(true);
            }
        } catch (NullPointerException e) {
            Log.e(getClass().getName(), e.getMessage());
        }


        try {
            if (toolbar != null) {
                toolbar.setTitle(getApplicationContext().getString(R.string.title_activity_menu_ppal));
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //do something you want

                        onBackPressed();

                    }
                });
            }
        } catch (ExceptionInInitializerError e) {
            e.printStackTrace();
        }


        Log.e(getClass().getName(), "onCreate");


    }

    @Override
    public void onBackPressed() {

        int count = getSupportFragmentManager().getBackStackEntryCount();
        super.onBackPressed();
        if (count == 0) {
            super.onBackPressed();
            Intent i;


            i = new Intent(getApplicationContext(), MenuPrincipal.class);


            SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.sDATOS), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putInt(getResources().getString(R.string.sIdSesion), idSesion);
            editor.putInt(getResources().getString(R.string.tab), 2);
            editor.putInt(getString(R.string.sSeleccion), 0);

            editor.apply();
            startActivity(i);
            finish();

        } else {
            getFragmentManager().popBackStack();
        }
    }


    @Override
    public void setTextInTextView(boolean[] diaSeleccionado) {

        CustomFontTextView textView = (CustomFontTextView) findViewById(R.id.idTvAlertaRepetir);


        assert textView != null;
        textView.setText("");

        String dia = textView.getText().toString();
        if (diaSeleccionado[0]) {
            dia = dia.concat(getResources().getString(R.string.sInicialLunes));
            textView.setText(dia);

        }
        if (diaSeleccionado[1]) {
            dia = dia.concat(getResources().getString(R.string.sInicialMartes));
            textView.setText(dia);

        }
        if (diaSeleccionado[2]) {
            dia = dia.concat(getResources().getString(R.string.sInicialMiercoles));
            textView.setText(dia);

        }
        if (diaSeleccionado[3]) {
            dia = dia.concat(getResources().getString(R.string.sInicialJueves));
            textView.setText(dia);

        }
        if (diaSeleccionado[4]) {
            dia = dia.concat(getResources().getString(R.string.sInicialViernes));
            textView.setText(dia);

        }
        if (diaSeleccionado[5]) {
            dia = dia.concat(getResources().getString(R.string.sInicialSabado));
            textView.setText(dia);

        }
        if (diaSeleccionado[6]) {
            dia = dia.concat(getResources().getString(R.string.sInicialDomingo));
            textView.setText(dia);

        }


    }


}
