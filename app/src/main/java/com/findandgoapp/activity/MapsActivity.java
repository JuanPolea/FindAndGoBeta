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

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {


    private String direccion;

    private GoogleMap mapa;


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences("DATOS", Context.MODE_PRIVATE);
        String nombre = sharedPreferences.getString("nombreEvento", getString(R.string.title_activity_maps));
        direccion = sharedPreferences.getString("direccion", null);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {


            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayShowHomeEnabled(true);
            }


        } catch (NullPointerException e) {
            Log.e(getClass().getName(), e.getMessage());
        }

        try {
            if (toolbar != null) {
                toolbar.setTitleTextColor(getApplicationContext().getResources().getColor(R.color.verdeAgua));

                toolbar.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.transparente));
                toolbar.setTitle(nombre);
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


    }

    /**
     * @param strAddress
     */
    private void getLocationFromAddress(String strAddress) {
        //Create coder with Activity context - this
        Geocoder coder = new Geocoder(this);
        List<Address> address;

        try {
            //Get latLng from String
            address = coder.getFromLocationName(strAddress, 5);

            //check for null
            if (address == null || address.size() == 0) {
                Toast.makeText(getApplicationContext(), getString(R.string.noLocation) + direccion, Toast.LENGTH_LONG).show();
                this.finish();
                return;
            }

            //Lets take first possibility from the all possibilities.
            Address location = address.get(0);
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

            //Put marker on map on that LatLng
            Marker srchMarker = mapa.addMarker(new MarkerOptions().position(latLng).title(direccion).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_place)));

            //Animate and Zoon on that map location
            mapa.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            //mapa.animateCamera(CameraUpdateFactory.zoomTo(50));


            CameraPosition camPos = new CameraPosition.Builder()
                    .target(latLng)   //Centramos el mapa en Madrid
                    .zoom(20)         //Establecemos el zoom en 19
                    .bearing(0)      //Establecemos la orientaci?n con el noreste arriba
                    .tilt(60)         //Bajamos el punto de vista de la c?mara 70 grados
                    .build();

            CameraUpdate camUpd3 =
                    CameraUpdateFactory.newCameraPosition(camPos);

            mapa.animateCamera(camUpd3);


        } catch (IOException e) {
            e.printStackTrace();

        }
    }


    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    public void onMapReady(GoogleMap map) {


        try {
            mapa = map;
            mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            mapa.getUiSettings().setZoomControlsEnabled(true);
            getLocationFromAddress(direccion);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), getString(R.string.noLocation), Toast.LENGTH_LONG).show();
            finish();
        }


    }


}