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

package com.findandgoapp.tools;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.findandgoapp.activity.MainActivity;
import com.findandgoapp.activity.R;
import com.findandgoapp.adapter.MyArrayAdapter;
import com.findandgoapp.custom.CustomFontEditText;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;


/**
 * @author Juan F. Mateos Redondo
 */

public class Utilidades extends FragmentActivity {

    private static final String TAG = "Utilidades ";


    public Utilidades() {
    }

    public Utilidades(Context c) {

    }


    /**
     * @param aEditText
     * @return verdadero si el editText está vacío, falso si no está vacío.
     */
    public boolean estaVacio(Context c, LinkedList<CustomFontEditText> aEditText) {
        boolean flag = false;

        for (EditText et : aEditText) {

            if (et.getText().toString().length() == 0) {

                int ecolor = Color.RED; // whatever color you want
                String estring = c.getResources().getString(R.string.sCampoObligatorio);
                ForegroundColorSpan fgcspan = new ForegroundColorSpan(ecolor);
                SpannableStringBuilder ssbuilder = new SpannableStringBuilder(estring);
                ssbuilder.setSpan(fgcspan, 0, estring.length(), 0);
                et.setError(ssbuilder);
                flag = true;
            }

        }
        return flag;

    }

    /**
     * @param aEditText
     * @return verdadero si el editText está vacío, falso si no está vacío.
     */
    public boolean estaVacioEditText(List<String> aEditText) {
        boolean flag = false;

        for (String et : aEditText) {

            if (!et.isEmpty()) {

                flag = true;
            }

        }
        return flag;

    }

    /**
     * @param string
     * @return
     */
    public boolean editTextVacio(String string) {

        return string.isEmpty();

    }

    /**
     * @param c
     * @param string
     * @return
     */
    public boolean compruebaEmail(Context c, String string) {

        Pattern patron = Pattern.compile("^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$");
        return patron.matcher(string).matches() || string.length() > 30;


    }

    /**
     * @param context
     * @param aSpinner
     * @return
     */
    public boolean compruebaSpinner(Context context, List<Spinner> aSpinner) {

        boolean flag = false;

        for (Spinner spinner : aSpinner) {

            if (spinner.getSelectedItemPosition() == 0
                    && !spinner.getSelectedItem().toString().equalsIgnoreCase(context.getResources().getString(R.string.sOtro))) {
                Toast toast = Toast.makeText(context, context.getString(R.string.compruebeDesplegable) + " " + spinner.getSelectedItem().toString().toLowerCase(), Toast.LENGTH_SHORT);
                toast.show();
                flag = true;
            }
        }
        return flag;
    }


    /**
     * @param c
     * @param msj
     */

    public void errorConsultaBBDD(Context c, String msj) {

        Toast t = Toast.makeText(c, msj, Toast.LENGTH_SHORT);
        t.show();


    }

    /**
     * @return
     */
    public boolean isExternalStorageAvailable() {

        String state = Environment.getExternalStorageState();
        boolean mExternalStorageAvailable;
        boolean mExternalStorageWriteable;

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // We can read and write the media
            mExternalStorageAvailable = mExternalStorageWriteable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // We can only read the media
            mExternalStorageAvailable = true;
            mExternalStorageWriteable = false;
        } else {
            // Something else is wrong. It may be one of many other states, but
            // all we need
            // to know is we can neither read nor write
            mExternalStorageAvailable = mExternalStorageWriteable = false;
        }

        return mExternalStorageAvailable
                && mExternalStorageWriteable;
    }


    public static boolean esCorreoValido(String correo) {
        return Patterns.EMAIL_ADDRESS.matcher(correo).matches();
    }


    /**
     * @param c
     * @param msj
     */
    public void mensajeAlertDialog(Context c, String msj) {


        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        TextView textView = new TextView(c.getApplicationContext());
        textView.setText(c.getResources().getString(R.string.sWarning));
        textView.setTypeface(Typeface.createFromAsset(c.getApplicationContext().getAssets(), c.getApplicationContext().getResources().getString(R.string.fontAmaticRegular)));
        textView.setPadding(15, 10, 0, 0);
        textView.setTextSize(c.getApplicationContext().getResources().getDimension(R.dimen.size_15));
        textView.setTextColor(c.getApplicationContext().getResources().getColor(R.color.rojo));

        builder.setCustomTitle(textView);
        builder.setIcon(R.drawable.ic_warning);

        builder.setMessage(msj);

        builder.setNeutralButton(c.getResources().getString(R.string.sAceptar), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

                dialogo1.cancel();


            }
        });

        AlertDialog alert = builder.create();
        alert.show();
        alert.getWindow().getAttributes();

        Button button = new Button(c.getApplicationContext());
        button = alert.getButton(DialogInterface.BUTTON_NEUTRAL);
        button.setTextColor(c.getApplicationContext().getResources().getColor(R.color.blanco));
        button.setBackgroundColor(c.getApplicationContext().getResources().getColor(R.color.link));
        //Preparamos las fuentes personalizadas
        Typeface fontTextoBoton = Typeface.createFromAsset(c.getApplicationContext().getAssets(), c.getApplicationContext().getResources().getString(R.string.fontAmaticRegularBold));
        button.setTypeface(fontTextoBoton);

        TextView textView1 = (TextView) alert.findViewById(android.R.id.message);
        textView1.setTypeface(Typeface.createFromAsset(c.getApplicationContext().getAssets(), c.getApplicationContext().getResources().getString(R.string.fontAmaticRegular)));
        textView1.setTextSize(c.getApplicationContext().getResources().getDimension(R.dimen.size_10));

    }

    public void mensajeAlertDialog(Context c, HashMap<String, String> array) {

        String msj = c.getResources().getString(R.string.reviseCampos);
        msj = msj.concat("\n");
        if (array.containsKey(c.getApplicationContext().getString(R.string.sProvincia))) {
            msj = msj.concat(c.getApplicationContext().getString(R.string.sProvincia) + " - " + array.get(c.getApplicationContext().getString(R.string.sProvincia)));
            msj = msj.concat("\n");
            msj = msj.concat(c.getApplicationContext().getString(R.string.localidad) + " - " + array.get(c.getApplicationContext().getString(R.string.localidad)));
            msj = msj.concat("\n");
            msj = msj.concat(c.getApplicationContext().getString(R.string.sCodigoPostal) + " - " + array.get(c.getApplicationContext().getString(R.string.cp)));
            msj = msj.concat("\n");

        } else if (array.containsKey(c.getApplicationContext().getString(R.string.via))) {

            msj = msj.concat(c.getApplicationContext().getString(R.string.localidad)) + " - " + array.get(c.getApplicationContext().getString(R.string.localidad));
            msj = msj.concat("\n");
            msj = msj.concat(c.getApplicationContext().getString(R.string.via)) + " - " + array.get(c.getApplicationContext().getString(R.string.sidTipoVia));
            msj = msj.concat("\n");
            msj = msj.concat(c.getApplicationContext().getString(R.string.calle)) + " - " + array.get(c.getApplicationContext().getString(R.string.calle));
            msj = msj.concat("\n");
            msj = msj.concat(c.getApplicationContext().getString(R.string.Cp)) + " - " + array.get(c.getApplicationContext().getString(R.string.cp));

        }

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        TextView textView = new TextView(c.getApplicationContext());
        textView.setText(c.getResources().getString(R.string.sWarning));
        textView.setTypeface(Typeface.createFromAsset(c.getApplicationContext().getAssets(), c.getApplicationContext().getResources().getString(R.string.fontAmaticRegular)));
        textView.setPadding(15, 10, 0, 0);
        textView.setTextSize(c.getApplicationContext().getResources().getDimension(R.dimen.size_15));
        textView.setTextColor(c.getApplicationContext().getResources().getColor(R.color.rojo));

        builder.setCustomTitle(textView);
        builder.setIcon(R.drawable.ic_warning);


        builder.setMessage(msj);

        builder.setNeutralButton(c.getResources().getString(R.string.sAceptar), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

                dialogo1.cancel();


            }
        });

        AlertDialog alert = builder.create();
        alert.show();
        alert.getWindow().getAttributes();

        Button button = new Button(c.getApplicationContext());
        button = alert.getButton(DialogInterface.BUTTON_NEUTRAL);
        button.setTextColor(c.getApplicationContext().getResources().getColor(R.color.blanco));
        button.setBackgroundColor(c.getApplicationContext().getResources().getColor(R.color.link));
        //Preparamos las fuentes personalizadas
        Typeface fontTextoBoton = Typeface.createFromAsset(c.getApplicationContext().getAssets(), c.getApplicationContext().getResources().getString(R.string.fontAmaticRegularBold));
        button.setTypeface(fontTextoBoton);

        TextView textView1 = (TextView) alert.findViewById(android.R.id.message);
        textView1.setTypeface(Typeface.createFromAsset(c.getApplicationContext().getAssets(), c.getApplicationContext().getResources().getString(R.string.fontAmaticRegular)));
        textView1.setTextSize(c.getApplicationContext().getResources().getDimension(R.dimen.size_10));
    }


    /**
     * @param et
     */
    public void desactivarEditText(EditText et) {

        et.setCursorVisible(false);
        et.setFocusable(false);
        et.setFocusableInTouchMode(false);
        et.setClickable(false);
    }


    /**
     * @param context
     */
    public void clearImageDiskCache(Context context) {
        File cache = new File(context.getCacheDir(), "picasso-cache");
        if (cache.exists() && cache.isDirectory()) {
            Log.e(TAG, "picasso deleted" + deleteDir(cache));

        } else {
            Log.e(TAG, "picasso NO deleted " + cache.getAbsoluteFile().toString());
        }

    }

    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                Log.e(TAG, String.valueOf(children[i]));
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        assert dir != null;
        return dir.delete();
    }


    /**
     * @param c
     * @param categoria
     * @param clasificacion
     */
    public static void rellenarSpinner(Context c, Spinner categoria, Spinner clasificacion) {


        String aux = clasificacion.getSelectedItem().toString();


        if (categoria.getSelectedItem().toString().equalsIgnoreCase(String.valueOf(c.getString(R.string.sMusica)))) {

            if (!(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sElijaMusica))) &&
                    !(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sElijaClasificacion))) &&
                    !(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sOtro)))) {

                final MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.musica));
                clasificacion.setAdapter(adapter);
                int spinnerPosition = adapter.getPosition(aux);
                clasificacion.setSelection(spinnerPosition);


            } else {
                final MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.musica));
                clasificacion.setAdapter(adapter);
            }

        } else if (categoria.getSelectedItem().toString().equalsIgnoreCase(String.valueOf(c.getString(R.string.sAudioVisual)))) {
            if (!(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sElijaAudiovisual))) &&
                    !(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sElijaClasificacion))) &&
                    !(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sOtro)))) {

                final MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.audiovisual));
                clasificacion.setAdapter(adapter);

                int spinnerPosition = adapter.getPosition(aux);
                clasificacion.setSelection(spinnerPosition);


            } else {

                final MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.audiovisual));
                clasificacion.setAdapter(adapter);
            }
        } else if (categoria.getSelectedItem().toString().equalsIgnoreCase(String.valueOf(c.getString(R.string.sEscena)))) {
            if (!(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sElijaEscena))) &&
                    !(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sElijaClasificacion))) &&
                    !(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sOtro)))) {

                final MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.escena));
                clasificacion.setAdapter(adapter);
                int spinnerPosition = adapter.getPosition(aux);
                clasificacion.setSelection(spinnerPosition);

            } else {
                final MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.escena));
                clasificacion.setAdapter(adapter);
            }

        } else if (categoria.getSelectedItem().toString().equalsIgnoreCase(String.valueOf(c.getString(R.string.sLiteratura)))) {
            if (!(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sElijaLiterartura))) &&
                    !(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sElijaClasificacion))) &&
                    !(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sOtro)))) {

                final MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.literatura));
                clasificacion.setAdapter(adapter);
                int spinnerPosition = adapter.getPosition(aux);
                clasificacion.setSelection(spinnerPosition);

            } else {
                final MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.literatura));
                clasificacion.setAdapter(adapter);
            }

        } else if (categoria.getSelectedItem().toString().equalsIgnoreCase(String.valueOf(c.getString(R.string.sArte)))) {
            if (!(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sElijaArte))) &&
                    !(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sElijaClasificacion))) &&
                    !(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sOtro)))) {


                final MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.arte));
                clasificacion.setAdapter(adapter);
                int spinnerPosition = adapter.getPosition(aux);
                clasificacion.setSelection(spinnerPosition);

            } else {
                final MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.arte));
                clasificacion.setAdapter(adapter);
            }


        } else if (categoria.getSelectedItem().toString().equalsIgnoreCase(String.valueOf(c.getString(R.string.sSocial)))) {
            if (!(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sElijaSocial))) &&
                    !(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sElijaClasificacion))) &&
                    !(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sOtro)))) {


                final MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.social));
                clasificacion.setAdapter(adapter);
                int spinnerPosition = adapter.getPosition(aux);
                clasificacion.setSelection(spinnerPosition);

            } else {
                final MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.social));
                clasificacion.setAdapter(adapter);
            }
        } else if (categoria.getSelectedItem().toString().equalsIgnoreCase(String.valueOf(c.getString(R.string.sDeporte)))) {
            if (!(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sElijaDeporte))) &&
                    !(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sElijaClasificacion))) &&
                    !(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sOtro)))) {

                final MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.deporte));
                clasificacion.setAdapter(adapter);
                int spinnerPosition = adapter.getPosition(aux);
                clasificacion.setSelection(spinnerPosition);

            } else {
                final MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.deporte));
                clasificacion.setAdapter(adapter);
            }

        } else if (categoria.getSelectedItem().toString().equalsIgnoreCase(String.valueOf(c.getString(R.string.sJuegos)))) {
            if (!(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sElijaJuegos))) &&
                    !(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sElijaClasificacion))) &&
                    !(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sOtro)))) {

                final MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.juegos));
                clasificacion.setAdapter(adapter);
                int spinnerPosition = adapter.getPosition(aux);
                clasificacion.setSelection(spinnerPosition);

            } else {
                final MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.juegos));
                clasificacion.setAdapter(adapter);
            }

        } else if (categoria.getSelectedItem().toString().equalsIgnoreCase(String.valueOf(c.getString(R.string.sOtro)))) {


            MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.otroSolo));
            clasificacion.setAdapter(adapter);


        } else if (categoria.getSelectedItem().toString().equalsIgnoreCase(String.valueOf(c.getString(R.string.cualquiera)))) {
            MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.otroSolo));
            clasificacion.setAdapter(adapter);
        } else {
            MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.clasificacion));
            clasificacion.setAdapter(adapter);

        }


    }

    /**
     * @param c
     * @param categoria
     * @param clasificacion
     */
    public static void rellenarSpinnerAlerta(Context c, Spinner categoria, Spinner clasificacion) {

        String aux = clasificacion.getSelectedItem().toString();


        if (categoria.getSelectedItem().toString().equalsIgnoreCase(String.valueOf(c.getString(R.string.sMusica)))) {

            if (!(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sElijaMusica))) &&
                    !(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sElijaClasificacion))) &&
                    !(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sOtro)))) {

                final MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.musica_cualquiera));
                clasificacion.setAdapter(adapter);
                int spinnerPosition = adapter.getPosition(aux);
                clasificacion.setSelection(spinnerPosition);


            } else {
                final MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.musica_cualquiera));
                clasificacion.setAdapter(adapter);
            }

        } else if (categoria.getSelectedItem().toString().equalsIgnoreCase(String.valueOf(c.getString(R.string.sAudioVisual)))) {
            if (!(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sElijaAudiovisual))) &&
                    !(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sElijaClasificacion))) &&
                    !(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sOtro)))) {

                final MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.audiovisual_cualquiera));
                clasificacion.setAdapter(adapter);

                int spinnerPosition = adapter.getPosition(aux);
                clasificacion.setSelection(spinnerPosition);


            } else {

                final MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.audiovisual_cualquiera));
                clasificacion.setAdapter(adapter);
            }
        } else if (categoria.getSelectedItem().toString().equalsIgnoreCase(String.valueOf(c.getString(R.string.sEscena)))) {
            if (!(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sElijaEscena))) &&
                    !(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sElijaClasificacion))) &&
                    !(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sOtro)))) {

                final MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.escena_cualquiera));
                clasificacion.setAdapter(adapter);
                int spinnerPosition = adapter.getPosition(aux);
                clasificacion.setSelection(spinnerPosition);

            } else {
                final MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.escena_cualquiera));
                clasificacion.setAdapter(adapter);
            }

        } else if (categoria.getSelectedItem().toString().equalsIgnoreCase(String.valueOf(c.getString(R.string.sLiteratura)))) {
            if (!(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sElijaLiterartura))) &&
                    !(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sElijaClasificacion))) &&
                    !(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sOtro)))) {

                final MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.literatura_cualquiera));
                clasificacion.setAdapter(adapter);
                int spinnerPosition = adapter.getPosition(aux);
                clasificacion.setSelection(spinnerPosition);

            } else {
                final MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.literatura_cualquiera));
                clasificacion.setAdapter(adapter);
            }

        } else if (categoria.getSelectedItem().toString().equalsIgnoreCase(String.valueOf(c.getString(R.string.sArte)))) {
            if (!(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sElijaArte))) &&
                    !(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sElijaClasificacion))) &&
                    !(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sOtro)))) {


                final MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.arte_cualquiera));
                clasificacion.setAdapter(adapter);
                int spinnerPosition = adapter.getPosition(aux);
                clasificacion.setSelection(spinnerPosition);

            } else {
                final MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.arte_cualquiera));
                clasificacion.setAdapter(adapter);
            }


        } else if (categoria.getSelectedItem().toString().equalsIgnoreCase(String.valueOf(c.getString(R.string.sSocial)))) {
            if (!(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sElijaSocial))) &&
                    !(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sElijaClasificacion))) &&
                    !(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sOtro)))) {


                final MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.social_cualquiera));
                clasificacion.setAdapter(adapter);
                int spinnerPosition = adapter.getPosition(aux);
                clasificacion.setSelection(spinnerPosition);

            } else {
                final MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.social));
                clasificacion.setAdapter(adapter);
            }
        } else if (categoria.getSelectedItem().toString().equalsIgnoreCase(String.valueOf(c.getString(R.string.sDeporte)))) {
            if (!(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sElijaDeporte))) &&
                    !(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sElijaClasificacion))) &&
                    !(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sOtro)))) {

                final MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.deporte_cualquiera));
                clasificacion.setAdapter(adapter);
                int spinnerPosition = adapter.getPosition(aux);
                clasificacion.setSelection(spinnerPosition);

            } else {
                final MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.deporte_cualquiera));
                clasificacion.setAdapter(adapter);
            }

        } else if (categoria.getSelectedItem().toString().equalsIgnoreCase(String.valueOf(c.getString(R.string.sJuegos)))) {
            if (!(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sElijaJuegos))) &&
                    !(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sElijaClasificacion))) &&
                    !(clasificacion.getSelectedItem().toString().equalsIgnoreCase(c.getString(R.string.sOtro)))) {

                final MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.juegos_cualquiera));
                clasificacion.setAdapter(adapter);
                int spinnerPosition = adapter.getPosition(aux);
                clasificacion.setSelection(spinnerPosition);

            } else {
                final MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.juegos_cualquiera));
                clasificacion.setAdapter(adapter);
            }

        } else if (categoria.getSelectedItem().toString().equalsIgnoreCase(String.valueOf(c.getString(R.string.sOtro)))) {


            MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.otroSolo));
            clasificacion.setAdapter(adapter);


        } else if (categoria.getSelectedItem().toString().equalsIgnoreCase(String.valueOf(c.getString(R.string.cualquiera)))) {
            MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.otroSolo));
            clasificacion.setAdapter(adapter);
        } else {
            MyArrayAdapter adapter = new MyArrayAdapter(c.getApplicationContext(), android.R.layout.simple_spinner_item, c.getApplicationContext().getResources().getStringArray(R.array.clasificacion));
            clasificacion.setAdapter(adapter);

        }


    }

    /**
     * @param c
     * @return
     */
    public static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }


    public void salir(final Activity activity) {
        final SharedPreferences sharedPreferences = activity.getSharedPreferences(activity.getResources().getString(R.string.DATOS), Context.MODE_PRIVATE);
        TextView textView = new TextView(activity);
        textView.setText(activity.getResources().getString(R.string.sWarning));
        textView.setTypeface(Typeface.createFromAsset(activity.getAssets(), activity.getResources().getString(R.string.fontAmaticRegular)));
        textView.setPadding(15, 10, 0, 0);
        textView.setTextSize(activity.getResources().getDimension(R.dimen.size_15));
        textView.setTextColor(ContextCompat.getColor(activity, R.color.rojo));

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setCustomTitle(textView)
                .setMessage(activity.getResources().getString(R.string.sCerrarSesion))
                .setCancelable(false)
                .setIcon(ContextCompat.getDrawable(activity, R.drawable.ic_action_alarms))
                .setNegativeButton(activity.getResources().getString(R.string.sNO), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton(activity.getResources().getString(R.string.sSi), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Intent intent = new Intent(activity, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        activity.startActivity(intent);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.apply();

                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
        alert.getWindow().getAttributes();

        Button button = new Button(activity);
        button = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        button.setTextColor(ContextCompat.getColor(activity, R.color.link));
        button.setBackgroundColor(ContextCompat.getColor(activity, R.color.blanco));

        //Preparamos las fuentes personalizadas
        Typeface fontTextoBoton = Typeface.createFromAsset(activity.getAssets(), activity.getResources().getString(R.string.fontAmaticRegularBold));
        button.setTypeface(fontTextoBoton);


        Button buttonCancel = new Button(activity);
        buttonCancel = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        buttonCancel.setTextColor(ContextCompat.getColor(activity, R.color.blanco));
        buttonCancel.setBackgroundColor(ContextCompat.getColor(activity, R.color.link));
        //Preparamos las fuentes personalizadas
        Typeface fontTextoCancel = Typeface.createFromAsset(activity.getAssets(), activity.getResources().getString(R.string.fontAmaticRegularBold));
        button.setTypeface(fontTextoCancel);

        TextView textView1 = (TextView) alert.findViewById(android.R.id.message);
        textView1.setTypeface(Typeface.createFromAsset(activity.getAssets(), activity.getResources().getString(R.string.fontAmaticRegular)));
        textView1.setTextSize(activity.getResources().getDimension(R.dimen.size_10));

        //activity.getSharedPreferences(getString(R.string.DATOS),0).edit().clear().apply();

    }


}