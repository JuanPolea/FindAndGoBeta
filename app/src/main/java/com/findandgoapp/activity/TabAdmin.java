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

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.findandgoapp.fragment.AdminUsuarioConsultaFragment;
import com.findandgoapp.fragment.EventoFragment;
import com.findandgoapp.fragment.InformacionFragment;
import com.findandgoapp.fragment.TabAdminEventoFragment;
import com.findandgoapp.fragment.TabAdminUsuarioFragment;
import com.findandgoapp.fragment.TabMenuEventoFragment;
import com.findandgoapp.pojo.UsuarioPOJO;
import com.findandgoapp.tools.Utilidades;

import java.util.List;


public class TabAdmin extends AppCompatActivity implements AdapterView.OnItemClickListener, NavigationView.OnNavigationItemSelectedListener {

    private int tab;

    private TabLayout tabs;

    private static final String[] values = {"Drawer 1", "Drawer 2", "Drawer 3"};
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private SharedPreferences sharedPreferences;
    private UsuarioPOJO usuario = new UsuarioPOJO();
    private DrawerLayout mDrawer;

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        sharedPreferences = getSharedPreferences(getResources().getString(R.string.DATOS), Context.MODE_PRIVATE);
        usuario.setI_idSesion(sharedPreferences.getInt(getString(R.string.sIdUsuario), 0));
        usuario.setI_idUsuario(sharedPreferences.getInt(getString(R.string.sIdUsuario), 0));

    }

    @Override

    protected void onPostCreate(Bundle savedInstanceState) {

        super.onPostCreate(savedInstanceState);

        // Sync the toggle state after onRestoreInstanceState has occurred.


        toggle.syncState();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedState) {
        super.onRestoreInstanceState(savedState);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_drawer_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.DATOS), Context.MODE_PRIVATE);

        usuario.setI_idSesion(sharedPreferences.getInt(getString(R.string.sIdUsuario), 0));
        usuario.setI_idUsuario(sharedPreferences.getInt(getString(R.string.sIdUsuario), 0));
        tab = sharedPreferences.getInt(getResources().getString(R.string.tab), 0);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_admin);

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);


        navigationView = (NavigationView) findViewById(R.id.nav_view_admin);
        navigationView.setNavigationItemSelectedListener(this);


        try {

            toolbar.setTitleTextColor(getApplicationContext().getResources().getColor(R.color.verdeAgua));
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayShowHomeEnabled(true);
            }
            if (toolbar != null) {
                toolbar.setTitle(getApplicationContext().getString(R.string.title_activity_tab_admin));
            }
        } catch (ExceptionInInitializerError e) {
            e.printStackTrace();
        }

        tabs = (TabLayout) findViewById(R.id.tabs);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        /*
      The {@link android.support.v4.view.PagerAdapter} that will provide
      fragments for each of the sections. We use a
      {@link FragmentPagerAdapter} derivative, which will keep every
      loaded fragment in memory. If this becomes too memory intensive, it
      may be best to switch to a
      {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        /*
      The {@link ViewPager} that will host the section contents.
     */
        ViewPager mViewPager = (ViewPager) findViewById(R.id.view_pager);

        assert mViewPager != null;
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabs.setupWithViewPager(mViewPager);
        tabs.setTabTextColors(getApplicationContext().getResources().getColor(R.color.link), getApplicationContext().getResources().getColor(R.color.verdeAgua));

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                Log.e(getClass().getName(), "PAGE " + position);
                tab = position;


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setCurrentItem(tab);

        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        tabs.getTabAt(tab).select();
                    }
                }, 100);


    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.

        Intent i;


        SharedPreferences sharedPreferences = this.getSharedPreferences(getResources().getString(R.string.DATOS), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        int id = item.getItemId();
        FragmentManager frManager = getSupportFragmentManager();
        FragmentTransaction frTransaction = frManager.beginTransaction();

        UsuarioPOJO usuarioPOJO = new UsuarioPOJO();
        usuarioPOJO.setI_idSesion(sharedPreferences.getInt(getResources().getString(R.string.sIdUsuario), 0));
        usuarioPOJO.setI_idUsuario(sharedPreferences.getInt(getResources().getString(R.string.sIdUsuario), 0));

        usuarioPOJO.setI_estado(sharedPreferences.getInt(getResources().getString(R.string.estadoUsuario), 0));
        usuarioPOJO.setS_password(sharedPreferences.getString(getResources().getString(R.string.password), null));
        usuarioPOJO.setI_tipo(sharedPreferences.getInt(getResources().getString(R.string.tipoUsuario), 0));


        if (id == R.id.miIdBuscarPorDatos) {

            EventoFragment fragment1 = new EventoFragment();


            frTransaction.replace(R.id.fragment_tab_admin_evento, fragment1, getString(R.string.fragment_evento_consulta));
            frTransaction.addToBackStack(getString(R.string.fragment_evento_consulta));
            List<Fragment> list = frManager.getFragments();

            editor.putInt(getString(R.string.sIdUsuario), usuarioPOJO.getI_idUsuario());
            editor.putString(getString(R.string.modo), getString(R.string.consultar));
            editor.putInt(getString(R.string.sIdSesion), usuarioPOJO.getI_idSesion());

            editor.apply();

            for (Fragment f : list) {
                if (f != null) Log.e(getClass().getName(), f.getTag());
            }

            frTransaction.commit();


        } else if (id == R.id.miIdCrearEvento) {

            if (usuarioPOJO.getI_estado() == 0) {
                EventoFragment fragment = new EventoFragment();
                frTransaction = frManager.beginTransaction();

                frTransaction.replace(R.id.fragment_tab_admin_evento, fragment, getString(R.string.fragment_evento_crear));
                frTransaction.addToBackStack(getString(R.string.fragment_evento_crear));

                editor.putInt(getString(R.string.sIdUsuario), usuarioPOJO.getI_idUsuario());
                editor.putString(getString(R.string.modo), getString(R.string.crear));
                editor.putInt(getString(R.string.sIdSesion), usuarioPOJO.getI_idSesion());
                editor.apply();

                frTransaction.commit();


            } else {
                Utilidades utilidades = new Utilidades();
                utilidades.mensajeAlertDialog(getApplicationContext(), getString(R.string.sUsuarioBloqueado));
            }


        } else if (id == R.id.miIdMisEventos) {
            frManager = getSupportFragmentManager();
            frTransaction = frManager.beginTransaction();


            sharedPreferences = this.getSharedPreferences(getResources().getString(R.string.DATOS), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor1 = sharedPreferences.edit();

            editor1.putInt(getString(R.string.sIdUsuario), usuarioPOJO.getI_idUsuario());
            editor1.putString(getString(R.string.modo), getString(R.string.consultar));
            editor1.putInt(getString(R.string.sIdSesion), usuarioPOJO.getI_idUsuario());
            editor1.putString(getString(R.string.fromFragment), getString(R.string.fragment_tab_menu_evento));
            editor1.putInt(getString(R.string.seleccion), 2);

            editor1.apply();

            Toast.makeText(getApplicationContext(), getString(R.string.sCargando), Toast.LENGTH_SHORT).show();
            TabMenuEventoFragment fragment1 = new TabMenuEventoFragment();
            frTransaction.replace(R.id.fragment_tab_admin_evento, fragment1, getString(R.string.fragment_evento_consulta));
            frTransaction.addToBackStack(getString(R.string.fragment_evento_consulta));


            frTransaction.commit();


        } else if (id == R.id.miIdActualizarEventos) {

            int seleccion = 0;
            Toast.makeText(getApplicationContext(), getString(R.string.sCargando), Toast.LENGTH_LONG).show();
            TabAdminEventoFragment fragment1 = new TabAdminEventoFragment();
            frTransaction.replace(R.id.fragment_tab_admin_evento, fragment1, getString(R.string.fragment_tab_admin_evento));
            frTransaction.addToBackStack(getString(R.string.fragment_tab_admin_evento));

            editor.putInt(getString(R.string.estadoUsuario), usuarioPOJO.getI_idSesion());
            editor.putInt(getString(R.string.sIdUsuario), usuarioPOJO.getI_idSesion());
            editor.putString(getString(R.string.modo), getString(R.string.consultar));
            editor.putInt(getString(R.string.sIdUsuario), usuarioPOJO.getI_idSesion());
            editor.putString(getString(R.string.fromFragment), getString(R.string.fragment_tab_admin_evento));
            editor.putInt(getString(R.string.seleccion), seleccion);
            editor.apply();

            frTransaction.commit();


        } else if (id == R.id.action_settings) {

            i = new Intent(getApplicationContext(), Configuracion.class);
            startActivity(i);


        } else if (id == R.id.miIdNotificaciones) {
            i = new Intent(getApplicationContext(), Informacion.class);

            // int tipoPenalizacion = 5;

            editor.putInt(getResources().getString(R.string.sIdUsuario), usuarioPOJO.getI_idUsuario());
            //editor.putInt(getResources().getString(R.string.sTipoPenalizacion), tipoPenalizacion);
            editor.putInt(getResources().getString(R.string.tipoUsuario), usuarioPOJO.getI_tipo());
            editor.apply();
            frTransaction.add(new InformacionFragment(), getString(R.string.fragment_informacion));
            frTransaction.addToBackStack("fragment_informacion");
            frTransaction.commit();

            startActivity(i);
        } else if (id == R.id.miIdBuscarUsuario) {
            i = new Intent(this, AdminUsuarioConsulta.class);
            // int tipoPenalizacion = 5;


            frTransaction.add(new AdminUsuarioConsultaFragment(), getString(R.string.fragment_admin_usuario_consulta));
            frTransaction.addToBackStack("fragment_admin_usuario_consulta");
            frTransaction.commit();

            startActivity(i);
        } else {
            Utilidades utilidades = new Utilidades();
            utilidades.mensajeAlertDialog(getApplicationContext(), getString(R.string.sUsuarioBloqueado));
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_admin);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        View hView = navigationView.getHeaderView(0);

        ImageView iv = (ImageView) hView.findViewById(R.id.ivLeyenda);
        iv.setBackground(getResources().getDrawable(R.drawable.ic_logo_tab));


        if (tab == 0) {
            navigationView.getMenu().clear();
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.menu_drawer_admin);

            LinearLayout linearLayout = (LinearLayout) hView.findViewById(R.id.llHeaderEvento);
            linearLayout.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.icono_transparente, null));


        } else if (tab == 1) {

            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.menu_drawer_admin_usuarios);

            LinearLayout linearLayout = (LinearLayout) hView.findViewById(R.id.llHeaderEvento);
            linearLayout.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.icono_transparente, null));
        }

        return true;

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(this, "Pulsado " + values[position], Toast.LENGTH_SHORT).show();

        mDrawer.closeDrawers();
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {


            Log.i(getClass().getName(), "onCreate");


            return inflater.inflate(R.layout.fragment_menu_principal, container, false);
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            FragmentManager fragmentManager = getSupportFragmentManager();

            switch (position) {
                case 0:

                    return new TabAdminEventoFragment();
                case 1:
                    return new TabAdminUsuarioFragment();

            }


            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {

                case 0:

                    return getResources().getString(R.string.sEventos);
                case 1:
                    return getResources().getString(R.string.sUsuarios);

            }
            return null;
        }
    }

    @Override
    public void onBackPressed() {

        int count = getSupportFragmentManager().getBackStackEntryCount();

        Log.e(getClass().getName(), String.valueOf(getFragmentManager().getBackStackEntryCount()));

        FragmentManager fragmentManager = getSupportFragmentManager();
        List<Fragment> list = fragmentManager.getFragments();
        for (Fragment f : list) {
            if (f != null)
                Log.e(getClass().getName(), "onBackPressed " + f.getTag());
        }

        if (count == 0) {
            salir();

        } else {
            Log.e(getClass().getName(), "else");
            super.onBackPressed();

        }
    }


    private void salir() {
        TextView textView = new TextView(getApplicationContext());
        textView.setText(getApplicationContext().getResources().getString(R.string.sWarning));
        textView.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), getApplicationContext().getResources().getString(R.string.fontAmaticRegular)));
        textView.setPadding(15, 10, 0, 0);
        textView.setTextSize(getApplicationContext().getResources().getDimension(R.dimen.size_15));
        textView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.rojo));

        AlertDialog.Builder builder = new AlertDialog.Builder(TabAdmin.this);
        builder.setCustomTitle(textView)
                .setMessage(getApplicationContext().getResources().getString(R.string.sCerrarSesion))
                .setCancelable(false)
                .setIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_action_alarms))
                .setNegativeButton(getApplicationContext().getResources().getString(R.string.sNO), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton(getApplicationContext().getResources().getString(R.string.sSi), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        TabAdmin.this.finish();
                        Intent intent = new Intent(TabAdmin.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
        alert.getWindow().getAttributes();

        Button button = new Button(getApplicationContext());
        button = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        button.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.link));
        button.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.blanco));

        //Preparamos las fuentes personalizadas
        Typeface fontTextoBoton = Typeface.createFromAsset(getApplicationContext().getAssets(), getApplicationContext().getResources().getString(R.string.fontAmaticRegularBold));
        button.setTypeface(fontTextoBoton);


        Button buttonCancel = new Button(getApplicationContext());
        buttonCancel = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        buttonCancel.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.blanco));
        buttonCancel.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.link));
        //Preparamos las fuentes personalizadas
        Typeface fontTextoCancel = Typeface.createFromAsset(getApplicationContext().getAssets(), getApplicationContext().getResources().getString(R.string.fontAmaticRegularBold));
        button.setTypeface(fontTextoCancel);

        TextView textView1 = (TextView) alert.findViewById(android.R.id.message);
        textView1.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), getApplicationContext().getResources().getString(R.string.fontAmaticRegular)));
        textView1.setTextSize(getApplicationContext().getResources().getDimension(R.dimen.size_10));
    }


}

