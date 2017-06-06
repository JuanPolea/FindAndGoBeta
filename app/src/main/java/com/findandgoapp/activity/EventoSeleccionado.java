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

package com.findandgoapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.findandgoapp.custom.CustomFontTextView;
import com.findandgoapp.fragment.EventoSeleccionadoFragment;
import com.findandgoapp.fragment.FragmentDialog;

import java.util.Locale;


public class EventoSeleccionado extends AppCompatActivity implements FragmentDialog.NoticeDialogListener, EventoSeleccionadoFragment.OnFragmentInteractionListener {


    private Toolbar toolbar;
    private CollapsingToolbarLayout colapsing;
    private String fromFragment;
    private SharedPreferences sharedPreferences;
    private Locale locale = null;

    @Override
    protected void onResume() {
        super.onResume();

        sharedPreferences = getSharedPreferences(getString(R.string.DATOS), Context.MODE_PRIVATE);

        fromFragment = sharedPreferences.getString(getString(R.string.fromFragment), getString(R.string.svacio));

        colapsing = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (locale != null) {
            newConfig.locale = locale;
            Locale.setDefault(locale);
            getBaseContext().getResources().updateConfiguration(newConfig, getBaseContext().getResources().getDisplayMetrics());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_evento_selecciona2);
        sharedPreferences = getSharedPreferences(getString(R.string.DATOS), Context.MODE_PRIVATE);

        fromFragment = sharedPreferences.getString(getString(R.string.fromFragment), getString(R.string.svacio));

        setIvFotoEvento((ImageView) findViewById(R.id.toolbarImage));


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            toolbar.setTitleTextColor(getApplicationContext().getResources().getColor(R.color.verdeAgua));
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayShowHomeEnabled(true);
            }
        } catch (NullPointerException e) {
            Log.e(getClass().getName(), e.getMessage());
        }


    }


    @Override
    public void onBackPressed() {


        sharedPreferences = getSharedPreferences(getString(R.string.DATOS), Context.MODE_PRIVATE);

        fromFragment = sharedPreferences.getString(getString(R.string.fromFragment), getString(R.string.svacio));
        int idSesion = sharedPreferences.getInt(getString(R.string.sIdSesion), 0);

        if (idSesion == 1) {
            super.onBackPressed();
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStack();
            }
            if (fromFragment.equalsIgnoreCase(getString(R.string.fragment_evento_update))) {
                Intent i = new Intent(EventoSeleccionado.this, MenuPrincipal.class);
                SharedPreferences sharedPreferences = getApplication().getSharedPreferences(getApplicationContext().getResources().getString(R.string.DATOS), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(getString(R.string.fromFragment), getString(R.string.svacio));
                editor.putInt(getString(R.string.tab), 0);
                editor.putBoolean(getString(R.string.login), true);
                editor.apply();
                //getActivity().finish();
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            } else if (fromFragment.equalsIgnoreCase(getString(R.string.fragment_tab_menu_evento))) {
                Intent i = new Intent(EventoSeleccionado.this, MenuPrincipal.class);
                SharedPreferences sharedPreferences = getApplication().getSharedPreferences(getApplicationContext().getResources().getString(R.string.DATOS), Context.MODE_PRIVATE);


                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(getString(R.string.fromFragment), getString(R.string.svacio));
                editor.putInt(getString(R.string.tab), 0);
                editor.apply();

                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            } else {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                fromFragment = sharedPreferences.getString(getString(R.string.fromFragment), getString(R.string.svacio));

                if (!fromFragment.equalsIgnoreCase(getString(R.string.svacio))) {
                    editor.putString(getString(R.string.fromFragment), getString(R.string.fragment_evento_listado));
                    toolbar.setTitle(getString(R.string.title_activity_listado));
                } else {
                    editor.putString(getString(R.string.fromFragment), getString(R.string.svacio));
                    toolbar.setTitle(getString(R.string.title_activity_menu_ppal));
                }

                editor.apply();

                super.onBackPressed();


            }
        }

    }


    @Override
    public void onDialogPositiveClick(int res) {

        CustomFontTextView customFontTextView = (CustomFontTextView) findViewById(R.id.idTvNumDeComentario);

        assert customFontTextView != null;

        customFontTextView.setText(String.valueOf(res));
        Log.e(getClass().getName(), String.valueOf(res));


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onDialogPositive(int res) {
        CustomFontTextView customFontTextView = (CustomFontTextView) findViewById(R.id.idTvNumDeComentario);

        assert customFontTextView != null;

        customFontTextView.setText(String.valueOf(res));
        Log.e(getClass().getName(), String.valueOf(res));
    }

    @Override
    public void setTitle(String title) {
        colapsing.setTitle(title);

        try {
            if (toolbar != null) {
                toolbar.setTitleTextColor(getApplicationContext().getResources().getColor(R.color.verdeAgua));
                toolbar.setTitle(title);
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

        Log.e(getClass().getName(), "Set title " + title);
    }


    private void setIvFotoEvento(ImageView ivFotoEvento) {
        ImageView ivFotoEvento1 = ivFotoEvento;
    }


}
