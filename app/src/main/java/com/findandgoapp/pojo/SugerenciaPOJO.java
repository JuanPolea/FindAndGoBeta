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

package com.findandgoapp.pojo;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.findandgoapp.activity.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Juan F. Mateos
 */
public class SugerenciaPOJO {


    private static final String TAG = "Sugerencia";
    private int idUsuario;
    private int tipo;
    private String texto;
    private int count;
    private int idSugerencia;
    private int idCategoria;

    /**
     * @param jsonObject
     */
    public void JSONtoSugerencia(JSONObject jsonObject) {

        try {


            setIdSugerencia(jsonObject.getInt("idSugerencia"));
            setTexto(jsonObject.getString("sugerencia"));
            setTipo(jsonObject.getInt("idTipoSugerencia"));
            if (getTipo() == 2) {
                setIdCategoria(jsonObject.getInt("idCategoria"));
            }
            setCount(jsonObject.getInt("cont"));


        } catch (JSONException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


    }

    /**
     *
     */
    public void addSugerencia(final FragmentActivity activity) {

        final int idSugerencia = getIdSugerencia();

        TextView textView = new TextView(activity);
        textView.setText(getTexto());
        textView.setTypeface(Typeface.createFromAsset(activity.getAssets(), activity.getResources().getString(R.string.fontAmaticRegular)));
        textView.setPadding(15, 10, 0, 0);
        textView.setTextSize(activity.getResources().getDimension(R.dimen.size_10));
        textView.setTextColor(ContextCompat.getColor(activity, R.color.negro));

        final TextInputEditText editText = new TextInputEditText(activity);
        editText.setHint(activity.getResources().getString(R.string.sEscribaSugerencia));

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(activity);
        builder.setCustomTitle(textView)
                .setView(editText)
                .setCancelable(false)
                .setIcon(ContextCompat.getDrawable(activity, R.drawable.ic_action_alarms))
                .setNegativeButton(activity.getResources().getString(R.string.sCancelar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton(activity.getResources().getString(R.string.sEnviar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                        if (!editText.getText().toString().equalsIgnoreCase("")) {
                            Log.e(getClass().getName(), editText.getText().toString());


                            SugerenciaPOJO sugerenciaPOJO = new SugerenciaPOJO();
                            sugerenciaPOJO.insertSugerencia(activity, getIdUsuario(), tipo, idSugerencia, editText.getText().toString());

                        } else {

                            Toast.makeText(activity, activity.getString(R.string.sugerenciaNoInsertada), Toast.LENGTH_SHORT).show();
                            editText.setError(activity.getString(R.string.sCampoObligatorio));
                        }


                    }
                });

        android.support.v7.app.AlertDialog alert = builder.create();
        alert.show();
        alert.getWindow().getAttributes();

        Button button = new Button(activity);
        button = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        button.setTextColor(activity.getResources().getColor(R.color.link));
        button.setBackgroundColor(activity.getResources().getColor(R.color.blanco));

        //Preparamos las fuentes personalizadas
        Typeface fontTextoBoton = Typeface.createFromAsset(activity.getAssets(), activity.getResources().getString(R.string.fontAmaticRegularBold));
        button.setTypeface(fontTextoBoton);


        Button buttonCancel = new Button(activity);
        buttonCancel = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        buttonCancel.setTextColor(activity.getResources().getColor(R.color.blanco));
        buttonCancel.setBackgroundColor(activity.getResources().getColor(R.color.link));
        //Preparamos las fuentes personalizadas
        Typeface fontTextoCancel = Typeface.createFromAsset(activity.getAssets(), activity.getResources().getString(R.string.fontAmaticRegularBold));
        button.setTypeface(fontTextoCancel);


    }


    public int getCount() {
        return count;
    }

    private void setCount(int count) {
        this.count = count;
    }


    private void insertSugerencia(final FragmentActivity activity, final int idUsuario, final int tipo, final int id, final String titulo) {

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage(activity.getString(R.string.sCargando));
        progressDialog.show();


        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, activity.getResources().getString(R.string.sUrl_insertSugerencia),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Log.e(getClass().getName(), "addSugerencia" + response);
                        progressDialog.dismiss();

                        try {

                            JSONObject json = new JSONObject(response);
                            String mensaje = json.getString(activity.getString(R.string.mensaje));

                            int success;
                            success = json.getInt(activity.getString(R.string.success));


                            if (success == 1) {

                                Toast.makeText(activity, activity.getResources().getString(R.string.sSugerenciaExito), Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(activity, mensaje, Toast.LENGTH_SHORT).show();
                        } catch (JSONException j) {
                            j.printStackTrace();
                            Log.e(getClass().getName(), j.getMessage());
                        }
                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        progressDialog.dismiss();
                        Toast.makeText(activity, activity.getString(R.string.errorConexion), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                return formatoInsertSugerencia(idUsuario, tipo, id, titulo);
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(stringRequest);

    }

    private Map<String, String> formatoInsertSugerencia(int idUsuario, int tipo, int id, String titulo) {

        Map<String, String> params = new HashMap<>();

        params.put("idUsuario", String.valueOf(idUsuario));
        params.put("tipo", String.valueOf(tipo));
        params.put("sugerencia", titulo);
        params.put("id", String.valueOf(id));

        return params;
    }

    private int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }


    private int getIdSugerencia() {
        return idSugerencia;
    }

    public void setIdSugerencia(int idSugerencia) {
        this.idSugerencia = idSugerencia;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    private void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
}
