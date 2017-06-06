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

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.findandgoapp.fragment.EventoFragment;
import com.findandgoapp.fragment.EventoSeleccionadoFragment;
import com.findandgoapp.fragment.FullScreenFragment;
import com.findandgoapp.fragment.TabMenuAlertaFragment;
import com.findandgoapp.fragment.TabMenuEventoFragment;
import com.findandgoapp.fragment.TabMenuUsuarioFragment;
import com.findandgoapp.pojo.UsuarioPOJO;
import com.findandgoapp.tools.BordesRedondos;
import com.findandgoapp.tools.Utilidades;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.net.HttpURLConnection;
import java.net.NetworkInterface;
import java.net.URL;
import java.util.Collections;
import java.util.List;


@SuppressWarnings("ALL")
public class MenuPrincipal extends AppCompatActivity implements AdapterView.OnItemClickListener,
        EventoSeleccionadoFragment.OnFragmentInteractionListener, NavigationView.OnNavigationItemSelectedListener {

    private static final int C_FRAGMENTS_TO_KEEP_IN_MEMORY = 2;
    private static final String[] values = {"Drawer 1", "Drawer 2", "Drawer 3"};
    private static int tab;
    private static int tipo;
    private RecyclerView recycler;
    private DrawerLayout mDrawer;
    private ListView mDrawerOptions;
    private Toolbar toolbar;
    private SharedPreferences sharedPreferences;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private int iIdSesion = 0;
    private int estado;
    private boolean facebook;
    private UsuarioPOJO usuarioPOJO;

    /**
     * @param context
     * @param isImage
     * @param image
     * @param file
     */
    private static void launchPicasso(Context context, Boolean isImage, ImageView image, String file) {


        if (isImage) {

            Picasso.
                    with(context).
                    load(file)
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .transform(new BordesRedondos(300, 0))
                    .resize((int) context.getResources().getDimension(R.dimen.fotoAncho), (int) context.getResources().getDimension(R.dimen.fotoAncho))
                    .centerInside()
                    .error(R.drawable.ic_logo_tab)
                    .into(image);


            /**
             *  Utilidades utilidades = new Utilidades();
             utilidades.clearImageDiskCache(context.getApplicationContext());
             */

        } else {

            if (tipo == 2)
                image.setImageResource(R.drawable.icono_usuario);
            else if (tipo == 3)
                image.setImageResource(R.drawable.icono_artista);
            else if (tipo == 4)
                image.setImageResource(R.drawable.icono_asistente);
            else
                image.setImageResource(R.drawable.ic_logo_tab);
        }


    }

    /**
     * @param ctx
     */

    public static void cancelAllNotification(Context ctx) {
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager nMgr = (NotificationManager) ctx.getSystemService(ns);
        nMgr.cancelAll();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        sharedPreferences = getSharedPreferences(getResources().getString(R.string.DATOS), Context.MODE_PRIVATE);


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

        // Pass any configuration change to the drawer toggles

        toggle.onConfigurationChanged(newConfig);
        Configuration configuration = getResources().getConfiguration();
        configuration.fontScale = (float) 1;

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        metrics.scaledDensity = configuration.fontScale * metrics.density;
        Log.e(getClass().getSimpleName(), "EscalaConfig " + Float.toString(getApplicationContext().getResources().getDisplayMetrics().density) + "METRICS " + metrics);


        getBaseContext().getResources().updateConfiguration(configuration, metrics);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        sharedPreferences = this.getSharedPreferences(getResources().getString(R.string.DATOS), Context.MODE_PRIVATE);


        iIdSesion = sharedPreferences.getInt(getResources().getString(R.string.sIdSesion), 0);
        tab = sharedPreferences.getInt(getResources().getString(R.string.tab), 0);
        tipo = sharedPreferences.getInt(getString(R.string.tipoUsuario), 0);
        facebook = sharedPreferences.getBoolean(getString(R.string.sfacebook), false);

        if (facebook) {
            usuarioPOJO = new UsuarioPOJO();
            usuarioPOJO.setS_nombre(sharedPreferences.getString(getString(R.string.nombre), getString(R.string.svacio)));
            usuarioPOJO.setS_email(sharedPreferences.getString(getString(R.string.email), getString(R.string.svacio)));
        }

        toolbar.setTitle(getString(R.string.title_activity_menu_ppal));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);


        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());

        Log.e(getClass().getName(), "WifiManager " + ip);


        try {
            // get all the interfaces
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            //find network interface wlan0


            for (NetworkInterface networkInterface : all) {


                Log.e(getClass().getName(), networkInterface.getDisplayName());
                Log.e(getClass().getName(), networkInterface.getInetAddresses().toString());
                Log.e(getClass().getName(), networkInterface.getInterfaceAddresses().toString());


                if (!networkInterface.getName().equalsIgnoreCase("wlan0")) continue;
                //get the hardware address (MAC) of the interface
                byte[] macBytes = networkInterface.getHardwareAddress();
                if (macBytes == null) {
                    Log.e(getClass().getName(), "Network macbytes null");
                }


                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    //gets the last byte of b
                    res1.append(Integer.toHexString(b & 0xFF) + ":");
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }

                Log.e(getClass().getName(), "Network " + res1.toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        try {
            if (toolbar != null) {
                toolbar.setTitle(getApplicationContext().getString(R.string.title_activity_menu_ppal));
                toolbar.setTitleTextColor(getApplicationContext().getResources().getColor(R.color.verdeAgua));
            }
        } catch (ExceptionInInitializerError e) {
            e.printStackTrace();
        }

        final TabLayout tabs = (TabLayout) findViewById(R.id.tabs);


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
        mViewPager.setOffscreenPageLimit(C_FRAGMENTS_TO_KEEP_IN_MEMORY);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                Log.e(getClass().getName(), "PAGE " + position);
                tab = position;
                toolbar.setTitle(R.string.title_activity_menu_ppal);
                Log.e(getClass().getName(), "TAB " + tab);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setCurrentItem(tab);

    }

    @SuppressLint("CommitPrefEdits")
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        LayoutInflater li;
        Intent i;
        SharedPreferences sharedPreferences1 = getSharedPreferences(getString(R.string.DATOS), Context.MODE_PRIVATE);
        estado = sharedPreferences1.getInt(getResources().getString(R.string.estado), 0);
        int id = item.getItemId();


        UsuarioPOJO usuarioPOJO = new UsuarioPOJO();
        usuarioPOJO.setI_idSesion(sharedPreferences.getInt(getResources().getString(R.string.sIdSesion), 0));
        usuarioPOJO.setI_idUsuario(sharedPreferences.getInt(getResources().getString(R.string.sIdSesion), 0));

        usuarioPOJO.setI_estado(sharedPreferences.getInt(getResources().getString(R.string.estado), 0));
        usuarioPOJO.setS_password(sharedPreferences.getString(getResources().getString(R.string.password), null));
        usuarioPOJO.setI_tipo(sharedPreferences.getInt(getResources().getString(R.string.tipoUsuario), 0));


        if (id == R.id.miIdBuscarPorDatos) {
            SharedPreferences sharedPreferences = this.getSharedPreferences(getResources().getString(R.string.DATOS), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor1 = sharedPreferences.edit();
            FragmentManager frManager = getSupportFragmentManager();
            FragmentTransaction frTransaction = frManager.beginTransaction();
            EventoFragment fragment1 = new EventoFragment();
            frTransaction.replace(R.id.fragment_evento_item_list, fragment1, getString(R.string.fragment_evento_consulta));
            frTransaction.addToBackStack(getString(R.string.fragment_evento_consulta));


            editor1.putInt(getString(R.string.sIdUsuario), iIdSesion);
            editor1.putString(getString(R.string.modo), getString(R.string.consultar));
            editor1.putInt(getString(R.string.sIdSesion), iIdSesion);
            editor1.putString(getString(R.string.fromFragment), getString(R.string.fragment_evento_consulta));
            editor1.apply();
            toolbar.setTitle(getString(R.string.sConsultar));


            frTransaction.commit();

        } else if (id == R.id.miIdCrearEvento) {
            FragmentManager frManager = getSupportFragmentManager();
            FragmentTransaction frTransaction = frManager.beginTransaction();
            if (estado == 0) {
                SharedPreferences sharedPreferences = this.getSharedPreferences(getResources().getString(R.string.DATOS), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor1 = sharedPreferences.edit();
                EventoFragment fragment = new EventoFragment();

                frTransaction.replace(R.id.fragment_evento_item_list, fragment, getString(R.string.fragment_evento_crear));
                frTransaction.addToBackStack(getString(R.string.fragment_evento_crear));

                editor1.putInt(getString(R.string.sIdUsuario), iIdSesion);
                editor1.putString(getString(R.string.modo), getString(R.string.crear));
                editor1.putInt(getString(R.string.sIdSesion), iIdSesion);
                editor1.apply();

                frTransaction.commit();


            } else {

                Toast.makeText(getApplicationContext(), getResources().getString(R.string.sUsuarioBloqueado), Toast.LENGTH_LONG).show();
            }


        } else if (id == R.id.miIdMisEventos) {
            FragmentManager frManager = getSupportFragmentManager();
            FragmentTransaction frTransaction = frManager.beginTransaction();


            SharedPreferences sharedPreferences = this.getSharedPreferences(getResources().getString(R.string.DATOS), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor1 = sharedPreferences.edit();

            editor1.putInt(getString(R.string.sIdUsuario), iIdSesion);
            editor1.putString(getString(R.string.modo), getString(R.string.consultar));
            editor1.putInt(getString(R.string.sIdSesion), iIdSesion);
            editor1.putString(getString(R.string.fromFragment), getString(R.string.fragment_tab_menu_evento));
            editor1.putInt(getString(R.string.seleccion), 2);

            editor1.apply();

            Toast.makeText(getApplicationContext(), getString(R.string.sCargando), Toast.LENGTH_SHORT).show();
            TabMenuEventoFragment fragment1 = new TabMenuEventoFragment();
            frTransaction.replace(R.id.fragment_evento_item_list, fragment1, getString(R.string.fragment_evento_consulta));
            frTransaction.addToBackStack(getString(R.string.fragment_evento_consulta));


            toolbar.setTitle(getString(R.string.sConsultar));


            frTransaction.commit();

        } else if (id == R.id.miIdActualizarEventos) {
            FragmentManager frManager = getSupportFragmentManager();
            FragmentTransaction frTransaction = frManager.beginTransaction();

            Toast.makeText(getApplicationContext(), getString(R.string.sCargando), Toast.LENGTH_LONG).show();


            SharedPreferences sharedPreferences = this.getSharedPreferences(getResources().getString(R.string.DATOS), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor1 = sharedPreferences.edit();

            editor1.putInt(getString(R.string.sIdUsuario), iIdSesion);
            editor1.putString(getString(R.string.modo), getString(R.string.consultar));
            editor1.putInt(getString(R.string.sIdSesion), iIdSesion);
            editor1.putString(getString(R.string.fromFragment), getString(R.string.fragment_evento_consulta));
            editor1.putInt(getString(R.string.seleccion), 1);
            editor1.apply();
            toolbar.setTitle(getString(R.string.sConsultar));

            TabMenuEventoFragment fragment1 = new TabMenuEventoFragment();
            frTransaction.replace(R.id.fragment_evento_item_list, fragment1, getString(R.string.fragment_tab_menu_evento));
            frTransaction.addToBackStack(getString(R.string.fragment_evento_consulta));

            frTransaction.commit();


        } else if (id == R.id.miIdLeyenda) {

            Intent intent = new Intent(getApplicationContext(), FullscreenActivity.class);
            SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.DATOS), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(getString(R.string.sIdUsuario), iIdSesion);
            editor.putInt(getString(R.string.sIdSesion), iIdSesion);
            editor.putBoolean(getString(R.string.leyenda), true);
            editor.putBoolean(getString(R.string.usuario), false);
            editor.apply();

            startActivity(intent);

        } else if (id == R.id.action_settings) {

            i = new Intent(getApplicationContext(), Configuracion.class);
            startActivity(i);

        } else if (id == R.id.miIdPerfilUsuario) {
            //navigationView.setCheckedItem(R.id.miIdPerfilUsuario);

            if (estado == 0) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                SharedPreferences sharedPreferences = this.getSharedPreferences(getResources().getString(R.string.DATOS), Context.MODE_PRIVATE);
                editor.putInt(getString(R.string.sIdSesion), usuarioPOJO.getI_idSesion());
                editor.putInt(getResources().getString(R.string.sIdUsuario), usuarioPOJO.getI_idUsuario());
                editor.putString(getResources().getString(R.string.password), usuarioPOJO.getS_password());
                editor.putInt(getString(R.string.tipo), usuarioPOJO.getI_tipo());
                editor.putString(getString(R.string.modo), getString(R.string.consultar));
                editor.putBoolean(getString(R.string.sfacebook), facebook);

                if (facebook) {
                    editor.putString(getString(R.string.nombre), usuarioPOJO.getS_nombre());
                    editor.putString(getString(R.string.email), usuarioPOJO.getS_email());
                }

                editor.putInt(getString(R.string.estado), usuarioPOJO.getI_estado());

                editor.apply();


                i = new Intent(getApplicationContext(), PerfilUsuario.class);
                startActivity(i);
            }

        } else if (id == R.id.miIdNotificaciones) {
            i = new Intent(getApplicationContext(), Informacion.class);
            FragmentManager frManager = getSupportFragmentManager();
            FragmentTransaction frTransaction = frManager.beginTransaction();
            SharedPreferences sharedPreferences = this.getSharedPreferences(getResources().getString(R.string.DATOS), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor1 = sharedPreferences.edit();

            editor1.putInt(getResources().getString(R.string.sIdUsuario), usuarioPOJO.getI_idUsuario());
            editor1.putInt(getResources().getString(R.string.tipoUsuario), usuarioPOJO.getI_tipo());
            editor1.apply();
            frTransaction.addToBackStack("fragment_menu_usuario");
            frTransaction.commit();

            startActivity(i);
        } else if (id == R.id.miIdActualizarUsuarios) {
            int estado = 0;

            i = new Intent(getApplicationContext(), MenuPrincipal.class);

            SharedPreferences sharedPreferences = this.getSharedPreferences(getResources().getString(R.string.DATOS), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor1 = sharedPreferences.edit();


            iIdSesion = sharedPreferences.getInt(getResources().getString(R.string.sIdSesion), 0);
            tab = sharedPreferences.getInt(getResources().getString(R.string.tab), 0);
            sharedPreferences.getInt(getString(R.string.tipoUsuario), usuarioPOJO.getI_tipo());

            editor1.putString(getString(R.string.fromFragment), getString(R.string.svacio));
            editor1.putInt(getString(R.string.tab), 1);
            editor1.putInt(getString(R.string.sIdUsuario), iIdSesion);
            editor1.putInt(getString(R.string.sIdSesion), iIdSesion);
            editor1.putInt(getString(R.string.estado), estado);
            editor1.putInt(getString(R.string.seleccion), 0);
            editor1.apply();
            editor1.apply();
            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);

        } else if (id == R.id.miIdAddAlerta) {

            i = new Intent(getApplicationContext(), Alerta.class);
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATOS", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(getString(R.string.sIdSesion), usuarioPOJO.getI_idSesion());
            editor.putString(getString(R.string.modo), getString(R.string.crear));
            editor.putInt(getString(R.string.sIdUsuario), usuarioPOJO.getI_idUsuario());

            editor.apply();
            startActivity(i);

        } else {
            Utilidades utilidades = new Utilidades();
            utilidades.mensajeAlertDialog(getApplicationContext(), getString(R.string.sUsuarioBloqueado));
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.clear();
        final View hView = navigationView.getHeaderView(0);

        final ImageView iv = (ImageView) hView.findViewById(R.id.ivLeyenda);

        ImageDownloadTask imageDownloadTask = new ImageDownloadTask(getApplicationContext(), String.valueOf(iIdSesion), iv);
        imageDownloadTask.execute();

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), FullscreenActivity.class);
                SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.DATOS), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(getString(R.string.sIdUsuario), iIdSesion);
                editor.putBoolean(getString(R.string.usuario), true);
                editor.putInt(getString(R.string.tipoUsuario), tipo);
                editor.apply();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FullScreenFragment fullScreenFragment = new FullScreenFragment();
                fragmentTransaction.add(fullScreenFragment, getString(R.string.fragment_full_screen));
                fragmentTransaction.addToBackStack(getString(R.string.fragment_full_screen));
                fragmentTransaction.commit();
                startActivity(intent);


            }
        });

        if (tab == 0) {

            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.menu_drawer_evento);

            LinearLayout linearLayout = (LinearLayout) hView.findViewById(R.id.llHeaderEvento);
            iv.setBackground(getResources().getDrawable(R.drawable.icono_transparente));


        } else if (tab == 1) {

            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.menu_drawer_usuario);

            LinearLayout linearLayout = (LinearLayout) hView.findViewById(R.id.llHeaderEvento);
            iv.setBackground(getResources().getDrawable(R.drawable.icono_transparente));
            int size = (int) getApplicationContext().getResources().getDimension(R.dimen.size_image);
            iv.setMaxWidth(size);
            iv.setMaxHeight(size);


        } else if (tab == 2) {

            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.menu_drawer_alerta);

            LinearLayout linearLayout = (LinearLayout) hView.findViewById(R.id.llHeaderEvento);
            iv.setBackground(getResources().getDrawable(R.drawable.icono_transparente));


        }

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item) || toggle.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(this, "Pulsado " + values[position], Toast.LENGTH_SHORT).show();

        mDrawer.closeDrawers();
    }

    @Override
    public void onDialogPositive(int res) {

    }

    @Override
    public void setTitle(String title) {


    }

    @Override
    public void onBackPressed() {


        String fromFragment = sharedPreferences.getString(getString(R.string.fromFragment), getString(R.string.svacio));
        SharedPreferences.Editor editor = sharedPreferences.edit();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {

            if (fromFragment.equalsIgnoreCase(getString(R.string.fragment_evento_consulta))) {
                fragmentManager.popBackStack();
                toolbar.setTitle(getString(R.string.title_activity_menu_ppal));
                editor.putString(getString(R.string.fromFragment), getString(R.string.title_activity_menu_ppal));
                fragmentTransaction.commit();

            } else if (fromFragment.equalsIgnoreCase(getString(R.string.fragment_evento_listado))) {
                fragmentManager.popBackStack();
                toolbar.setTitle(getString(R.string.sConsultar));
                editor.putString(getString(R.string.fromFragment), getString(R.string.fragment_evento_consulta));
                fragmentTransaction.commit();

            } else if (fromFragment.equalsIgnoreCase(getString(R.string.title_activity_menu_ppal))) {
                toolbar.setTitle(getString(R.string.title_activity_menu_ppal));

                editor.putString(getString(R.string.fromFragment), getString(R.string.title_activity_menu_ppal));

            } else
                salir();


            editor.apply();


        } else {

            if (fromFragment.equalsIgnoreCase(getString(R.string.fragment_evento_consulta))) {
                super.onBackPressed();
                super.onBackPressed();
                toolbar.setTitle(getString(R.string.title_activity_menu_ppal));
                editor.putString(getString(R.string.fromFragment), getString(R.string.title_activity_menu_ppal));
                editor.apply();
            } else if (fromFragment.equalsIgnoreCase(getString(R.string.fragment_evento_listado))) {
                super.onBackPressed();
            } else {
                salir();
            }
        }
    }

    private void salir() {
        TextView textView = new TextView(getApplicationContext());
        textView.setText(getApplicationContext().getResources().getString(R.string.sWarning));
        textView.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), getApplicationContext().getResources().getString(R.string.fontAmaticRegular)));
        textView.setPadding(15, 10, 0, 0);
        textView.setTextSize(getApplicationContext().getResources().getDimension(R.dimen.size_aviso));
        textView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.rojo));

        AlertDialog.Builder builder = new AlertDialog.Builder(MenuPrincipal.this);
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

                        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.CHECKED), Context.MODE_PRIVATE);
                        SharedPreferences.Editor sharedChecked = sharedPreferences.edit();
                        //SharedPreferences sharedPreferencesDatos = getSharedPreferences(getString(R.string.DATOS),Context.MODE_PRIVATE);
                        boolean checked = sharedPreferences.getBoolean(getString(R.string.checked), false);

                        Intent intent = new Intent(MenuPrincipal.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        MenuPrincipal.this.finish();

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        if (!checked) {
                            editor.clear();
                            editor.apply();
                        }

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
        textView1.setTextSize(getApplicationContext().getResources().getDimension(R.dimen.size_aviso));

        //getApplicationContext().getSharedPreferences(getString(R.string.DATOS),0).edit().clear().apply();

    }

    /**
     *
     */
    public static class ImageDownloadTask extends AsyncTask<String, Void, Boolean> {

        private final String fileURL;
        private final ImageView imageView;
        private final Context _context;
        private Boolean estado = false;
        private String newFile;


        /**
         *
         */
        public ImageDownloadTask(final Context context, final String imageURL, final ImageView imageView) {
            this.fileURL = imageURL;
            this.imageView = imageView;
            this._context = context;


        }

        @Override
        protected Boolean doInBackground(final String... args) {
            Bitmap bmImg = null;
            HttpURLConnection con = null;
            Boolean estado = false;

            URL myFileUrl = null;
            try {
                HttpURLConnection.setFollowRedirects(false);
                newFile = _context.getString(R.string.sRutaImagenes) + fileURL + _context.getString(R.string.sFormatoPerfil);
                con = (HttpURLConnection) new URL(newFile).openConnection();
                con.setRequestMethod("HEAD");

                if ((con.getResponseCode() == HttpURLConnection.HTTP_OK)) {


                    if (con.getURL().getFile().equalsIgnoreCase(_context.getString(R.string.upload) + fileURL + _context.getString(R.string.sFormatoPerfil))) {
                        {


                            estado = true;
                        }
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();


                Log.d(getClass().getName(), "CATCH" + e.getMessage());

            } finally {
                assert con != null;
                con.disconnect();
            }

            Log.e(getClass().getName(), "FILE_EXISTS" + estado);

            return estado;
        }


        @Override
        protected void onPostExecute(final Boolean result) {


            if (result)
                launchPicasso(_context, result, this.imageView, newFile);

            else

            {

                if (tipo == 2)
                    imageView.setImageResource(R.drawable.icono_usuario);
                else if (tipo == 3)
                    imageView.setImageResource(R.drawable.icono_artista);
                else if (tipo == 4)
                    imageView.setImageResource(R.drawable.icono_asistente);
                else
                    imageView.setImageResource(R.drawable.ic_logo_tab);
            }

            Log.e(getClass().getName(), "\nTermina Image: " + fileURL + " tab: " + tab);
        }

    }

    /**
     *
     */
    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();

            args.putInt(ARG_SECTION_NUMBER, tab);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            getActivity().setTitle(getString(R.string.title_activity_menu_ppal));
            return inflater.inflate(R.layout.fragment_menu_principal, container, false);

        }
    }

    /**
     *
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    toolbar.setTitle(R.string.title_activity_menu_ppal);
                    return new TabMenuEventoFragment();
                case 1:
                    toolbar.setTitle(R.string.title_activity_menu_ppal);

                    return new TabMenuUsuarioFragment();

                case 2:
                    toolbar.setTitle(R.string.title_activity_menu_ppal);
                    return new TabMenuAlertaFragment();

            }


            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {

                case 0:

                    return getResources().getString(R.string.sEventos);
                case 1:
                    return getResources().getString(R.string.sUsuarios);
                case 2:
                    return getResources().getString(R.string.sAlertas);
            }
            return null;
        }
    }

}

