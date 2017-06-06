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

/**
 * Created by juanpolea on 5/04/17.
 * <p>
 * This file is part of FindAndGoApp.
 * <p>
 * FindAndGoApp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * FindAndGoApp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 * <p>
 * Based on http://clover.studio/2016/08/09/getting-current-location-in-android-using-location-manager/
 * <p>
 * Based on http://clover.studio/2016/08/09/getting-current-location-in-android-using-location-manager/
 * <p>
 * Based on http://clover.studio/2016/08/09/getting-current-location-in-android-using-location-manager/
 * <p>
 * Based on http://clover.studio/2016/08/09/getting-current-location-in-android-using-location-manager/
 * <p>
 * Based on http://clover.studio/2016/08/09/getting-current-location-in-android-using-location-manager/
 * <p>
 * Based on http://clover.studio/2016/08/09/getting-current-location-in-android-using-location-manager/
 * <p>
 * Based on http://clover.studio/2016/08/09/getting-current-location-in-android-using-location-manager/
 * <p>
 * Based on http://clover.studio/2016/08/09/getting-current-location-in-android-using-location-manager/
 */

/**
 * Based on http://clover.studio/2016/08/09/getting-current-location-in-android-using-location-manager/
 */

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.widget.TextView;


public class TrackGPS extends Service implements LocationListener {

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60;
    private final Context mContext;
    private boolean checkGPS = false;
    private boolean checkNetwork = false;
    private boolean canGetLocation = false;
    private Location loc;
    private double latitude;
    private double longitude;
    private LocationManager locationManager;

    public TrackGPS(Context mContext) {
        this.mContext = mContext;
        getLocation();
    }

    private void getLocation() {

        try {
            locationManager = (LocationManager) mContext
                    .getSystemService(LOCATION_SERVICE);

            // getting GPS status
            checkGPS = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            checkNetwork = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!checkGPS && !checkNetwork) {

            } else {
                this.canGetLocation = true;
                // First get location from Network Provider
                if (checkNetwork) {


                    try {
                        locationManager.requestLocationUpdates(
                                LocationManager.NETWORK_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                        if (locationManager != null) {
                            loc = locationManager
                                    .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                        }

                        if (loc != null) {
                            latitude = loc.getLatitude();
                            longitude = loc.getLongitude();
                        }
                    } catch (SecurityException e) {

                    }
                }
            }
            // if GPS Enabled get lat/long using GPS Services
            if (checkGPS) {

                if (loc == null) {
                    try {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                        if (locationManager != null) {
                            loc = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (loc != null) {
                                latitude = loc.getLatitude();
                                longitude = loc.getLongitude();
                            }
                        }
                    } catch (SecurityException e) {

                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public double getLongitude() {
        if (loc != null) {
            longitude = loc.getLongitude();
        }
        return longitude;
    }

    public double getLatitude() {
        if (loc != null) {
            latitude = loc.getLatitude();
        }
        return latitude;
    }

    public boolean canGetLocation() {
        return this.canGetLocation;
    }


    /**
     *
     */
    public void gpsSettings() {


        TextView textView = new TextView(mContext);
        textView.setText(mContext.getResources().getString(R.string.sWarning));
        textView.setTypeface(Typeface.createFromAsset(mContext.getAssets(), mContext.getResources().getString(R.string.fontAmaticRegular)));
        textView.setPadding(15, 10, 0, 0);
        textView.setTextSize(mContext.getResources().getDimension(R.dimen.size_15));
        textView.setTextColor(ContextCompat.getColor(mContext, R.color.rojo));

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(mContext.getString(R.string.sAviso));

        builder.setMessage(mContext.getString(R.string.activarGps));

        builder.setPositiveButton(mContext.getString(R.string.sSi), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
            }
        });


        builder.setNegativeButton(mContext.getString(R.string.No), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });


        AlertDialog alert = builder.create();
        alert.show();
        alert.getWindow().getAttributes();

        Button button = new Button(mContext);
        button = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        button.setTextColor(ContextCompat.getColor(mContext, R.color.blanco));
        button.setBackgroundColor(ContextCompat.getColor(mContext, R.color.link));

        //Preparamos las fuentes personalizadas
        Typeface fontTextoBoton = Typeface.createFromAsset(mContext.getAssets(), mContext.getResources().getString(R.string.fontAmaticRegularBold));
        button.setTypeface(fontTextoBoton);


        Button buttonCancel = new Button(mContext);
        buttonCancel = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        buttonCancel.setTextColor(ContextCompat.getColor(mContext, R.color.link));
        buttonCancel.setBackgroundColor(ContextCompat.getColor(mContext, R.color.blanco));
        //Preparamos las fuentes personalizadas
        Typeface fontTextoCancel = Typeface.createFromAsset(mContext.getAssets(), mContext.getResources().getString(R.string.fontAmaticRegularBold));
        button.setTypeface(fontTextoCancel);

        TextView textView1 = (TextView) alert.findViewById(android.R.id.message);
        textView1.setTypeface(Typeface.createFromAsset(mContext.getAssets(), mContext.getResources().getString(R.string.fontAmaticRegular)));
        textView1.setTextSize(mContext.getResources().getDimension(R.dimen.size_10));


    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
