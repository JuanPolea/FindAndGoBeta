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

package com.findandgoapp.fragment;

/**
 * Created by juanpolea on 10/08/16.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.findandgoapp.activity.EventoListado;
import com.findandgoapp.activity.MenuPrincipal;
import com.findandgoapp.activity.R;
import com.findandgoapp.adapter.MyArrayAdapter;
import com.findandgoapp.custom.CustomFontEditText;
import com.findandgoapp.custom.CustomFontTextView;
import com.findandgoapp.custom.CustomTypeFaceSpan;
import com.findandgoapp.pojo.AlertaPOJO;
import com.findandgoapp.pojo.SugerenciaPOJO;
import com.findandgoapp.tools.Utilidades;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.findandgoapp.tools.Utilidades.pad;

public class AlertaFragment extends Fragment implements DialogSemana.NoticeDialogListener {


    private static Activity activity;
    private static AlertaPOJO alertaPOJO;
    private static FragmentManager fragmentManager;

    private CustomFontEditText et_Titulo;
    private CustomFontEditText et_EventoTitulo;
    private Spinner sp_Categoria;
    private Spinner sp_Clasificacion;
    private Spinner sp_Tipo;
    private CustomFontEditText et_NombreSala;
    private Spinner sp_Ciudad;
    private ImageView iv_Fecha;
    private CustomFontTextView tv_Fecha;
    private CheckBox cbFecha;
    private CheckBox cbHora;
    private DatePickerDialog.OnDateSetListener dp_Fecha;
    private CustomFontTextView tv_Hora;

    private TimePickerDialog.OnTimeSetListener tdp_Hora;
    private ImageView iv_Hora;
    private ImageView iv_Repetir;
    private CustomFontTextView tv_Repetir;
    private CustomFontEditText et_Artista;
    private ImageView iv_Registrar;
    private AutoCompleteTextView et_Localidad;


    private TextInputLayout tilTituloAlerta;
    private TextInputLayout tilCiudad;


    private String modo;
    private ProgressDialog progressDialog;
    private boolean userSelect = false;
    private SugerenciaPOJO sugerenciaPOJO;


    /**
     * Interfaz
     */


    public AlertaFragment() {

    }

    private CheckBox getCbFecha() {
        return cbFecha;
    }

    private void setCbFecha(CheckBox cbFecha) {
        this.cbFecha = cbFecha;
    }

    private CheckBox getCbHora() {
        return cbHora;
    }

    private void setCbHora(CheckBox cbHora) {
        this.cbHora = cbHora;
    }


    @Override
    public void setTextInTextView(boolean[] linkedlist) {
        Log.e(getClass().getName(), "SetText");

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e(getClass().getName(), "OnCreateView");
        setHasOptionsMenu(true);
        sugerenciaPOJO = new SugerenciaPOJO();

        alertaPOJO = new AlertaPOJO(getActivity());
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getResources().getString(R.string.DATOS), Context.MODE_PRIVATE);
        alertaPOJO.setI_idUsuario(sharedPreferences.getInt(getActivity().getResources().getString(R.string.sIdUsuario), 0));
        alertaPOJO.setI_idSesion(sharedPreferences.getInt(getActivity().getResources().getString(R.string.sIdSesion), 0));
        alertaPOJO.setI_idalerta(sharedPreferences.getInt(getString(R.string.sIdAlerta), 0));
        modo = sharedPreferences.getString(getString(R.string.modo), getString(R.string.svacio));
        Log.e(getClass().getName(), modo);

        getActivity().setTitle(getString(R.string.sAlertaMenu));


        fragmentManager = getActivity().getSupportFragmentManager();


        activity = getActivity();
        View rootView = inflater.inflate(R.layout.fragment_alerta, container, false);


        final List<Spinner> spinnerList = new LinkedList<>();
        final Utilidades utilidades = new Utilidades();
//Variables para el calendario
        final Calendar CALENDAR = Calendar.getInstance();


        alertaPOJO.setI_ano(CALENDAR.get(Calendar.YEAR));
        alertaPOJO.setI_mes(CALENDAR.get(Calendar.MONTH));
        alertaPOJO.setI_dia(CALENDAR.get(Calendar.DAY_OF_MONTH));


        RelativeLayout relative = (RelativeLayout) rootView.findViewById(R.id.rlfragment_alerta);


        setEt_Titulo((CustomFontEditText) relative.findViewById(R.id.idEtAlertaTitulo));
        setTilTituloAlerta((TextInputLayout) relative.findViewById(R.id.tilAlertaTitulo));

        Log.e(getClass().getName(), getEt_Titulo().getText().toString());
        setEt_EventoTitulo((CustomFontEditText) rootView.findViewById(R.id.idEtAlertaTituloEvento));

        Log.e(getClass().getName(), getEt_EventoTitulo().getText().toString());
        setEt_NombreSala((CustomFontEditText) rootView.findViewById(R.id.idEtAlertaNombreSala));

        setIvAlertaFecha((ImageView) rootView.findViewById(R.id.idIvAlertaFecha));
        setIvAlertaHora((ImageView) rootView.findViewById(R.id.idIvAlertaHora));
        setIvAlertaRepetir((ImageView) rootView.findViewById(R.id.idIvAlertaRepetir));

        setTvAlertaFecha((CustomFontTextView) rootView.findViewById(R.id.idTvAlertaFecha));
        setTvAlertaHora((CustomFontTextView) rootView.findViewById(R.id.idTvAlertaHora));
        setTvAlertaRepetir((CustomFontTextView) rootView.findViewById(R.id.idTvAlertaRepetir));

        setCbFecha((CheckBox) rootView.findViewById(R.id.cbFecha));
        setCbHora((CheckBox) rootView.findViewById(R.id.cbHora));
        getCbFecha().setChecked(true);
        getCbHora().setChecked(true);

        if (getCbFecha().isChecked()) {
            getTvAlertaFecha().setTextColor(getActivity().getResources().getColor(R.color.verdeAgua));
            getCbFecha().setTextColor(getActivity().getResources().getColor(R.color.verdeAgua));

        }

        if (getCbHora().isChecked()) {
            getTvAlertaHora().setTextColor(getActivity().getResources().getColor(R.color.verdeAgua));
            getCbHora().setTextColor(getActivity().getResources().getColor(R.color.verdeAgua));
        }

        setEt_Artista((CustomFontEditText) rootView.findViewById(R.id.idEtAlertaArtista));

        setSp_Categoria((Spinner) rootView.findViewById(R.id.idSpAlertaCategoria));
        setSp_Clasificacion((Spinner) rootView.findViewById(R.id.idSpAlertaClasificacion));
        setSp_Tipo((Spinner) rootView.findViewById(R.id.idSpAlertaTipo));
        setSp_Ciudad((Spinner) rootView.findViewById(R.id.idSpAlertaCiudad));
        setEt_Localidad((AutoCompleteTextView) rootView.findViewById(R.id.idEtAlertaLocalidad));
        setTilCiudad((TextInputLayout) rootView.findViewById(R.id.tilAlertaLocalidad));

        setIvAlertaRegistrar((ImageView) rootView.findViewById(R.id.idBAlertaRegistrar));

        final MyArrayAdapter adapterCategoria = new MyArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, getActivity().getResources().getStringArray(R.array.categoriaConsulta));
        // Aplicamos el adaptador al spinner
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getSp_eventoCategoria().setAdapter(adapterCategoria);

        MyArrayAdapter adapterClasificacion = new MyArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, getActivity().getResources().getStringArray(R.array.clasificacion));
        // Aplicamos el adaptador al spinner
        adapterClasificacion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getSp_Clasificacion().setAdapter(adapterClasificacion);


        MyArrayAdapter adapterCiudad = new MyArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, getActivity().getResources().getStringArray(R.array.ciudades));
        adapterCiudad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        getSp_Ciudad().setAdapter(adapterCiudad);

        MyArrayAdapter adapterTipo = new MyArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, getActivity().getResources().getStringArray(R.array.tipo));
        adapterTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getSp_Tipo().setAdapter(adapterTipo);


        getSp_Ciudad().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                onTouch();
                if (userSelect)
                    selectLocalidad(getSp_Ciudad().getSelectedItemPosition());

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub


            }


            public void onTouch() {
                userSelect = true;
            }

        });


        /**
         * iniciar los spinners
         */


        getSp_eventoCategoria().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                if (getSp_eventoCategoria().getSelectedItem().toString().equalsIgnoreCase(getResources().getString(R.string.cualquiera))) {

                    getSp_Clasificacion().setClickable(false);

                } else {
                    getSp_Clasificacion().setClickable(true);
                    if (getSp_eventoCategoria().getSelectedItem().toString().equalsIgnoreCase(getResources().getString(R.string.sOtro))) {

                        sugerenciaPOJO.setIdUsuario(alertaPOJO.getI_idUsuario());
                        sugerenciaPOJO.setTipo(Integer.parseInt(getString(R.string.sugerenciaCategoria)));
                        sugerenciaPOJO.setIdSugerencia(getSp_eventoCategoria().getSelectedItemPosition());
                        sugerenciaPOJO.setTexto(getActivity().getResources().getString(R.string.sDeseaAddCategoria));
                        sugerenciaPOJO.addSugerencia(getActivity());
                        getSp_Clasificacion().setSelection(0);

                    }

                }
                Utilidades.rellenarSpinnerAlerta(getActivity(), getSp_eventoCategoria(), getSp_Clasificacion());
                adapterCategoria.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }

        });


        spinnerList.add(getSp_eventoCategoria());
        spinnerList.add(getSp_Ciudad());
        /**
         * Instanciamos DatePickerDialog para Fecha
         */


        /**
         * Iniciamos la fecha
         */
        dp_Fecha = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int ano, int mes, int dia) {

                alertaPOJO.setI_ano(ano);
                alertaPOJO.setI_mes(mes);
                alertaPOJO.setI_dia(dia);

                getTvAlertaFecha().setText(new StringBuilder()
                        .append(alertaPOJO.getI_dia()).append("-")
                        .append(alertaPOJO.getI_mes() + 1).append("-")
                        .append(alertaPOJO.getI_ano()));

                updateFecha();

            }
        };

        /**
         * Iniciar la hora
         */
        tdp_Hora = new TimePickerDialog.OnTimeSetListener() {

            public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                alertaPOJO.setI_hora(selectedHour);
                alertaPOJO.setI_minutos(selectedMinute);

                // set current time into textview
                getTvAlertaHora().setText(new StringBuilder().append(pad(alertaPOJO.getI_hora()))
                        .append(":").append(pad(alertaPOJO.getI_minutos())));
                getTvAlertaHora().setTextColor(getActivity().getResources().getColor(R.color.verdeAgua));
            }
        };


        getIvAlertaFecha().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DatePickerDialog DPD = new DatePickerDialog(getActivity(), dp_Fecha, alertaPOJO.getI_ano(), alertaPOJO.getI_mes(), alertaPOJO.getI_dia());
                DPD.getDatePicker().setMinDate(CALENDAR.getTimeInMillis());
                DPD.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.sAceptar), DPD);
                getCbFecha().setChecked(false);
                getCbFecha().setTextColor(getActivity().getResources().getColor(R.color.link));
                DPD.show();
            }
        });

        getCbFecha().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getCbFecha().isChecked()) {

                    getCbFecha().setTextColor(getActivity().getResources().getColor(R.color.verdeAgua));
                    getTvAlertaFecha().setTextColor(getActivity().getResources().getColor(R.color.verdeAgua));
                    getTvAlertaFecha().setText(getString(R.string.sAAMMDD));

                } else {

                    if (getTvAlertaFecha().getText().toString().equalsIgnoreCase(getString(R.string.sAAMMDD))) {
                        getTvAlertaFecha().setText(getString(R.string.sAAMMDD));
                        getCbFecha().setTextColor(getActivity().getResources().getColor(R.color.link));
                        getTvAlertaFecha().setTextColor(getActivity().getResources().getColor(R.color.grisMuyOscuro));
                    } else {
                        updateFecha();
                        getCbFecha().setTextColor(getActivity().getResources().getColor(R.color.link));
                        getTvAlertaFecha().setTextColor(getActivity().getResources().getColor(R.color.verdeAgua));
                    }


                }
            }
        });


        /**
         * Instanciar TimePickerDialog para el recordatorio
         */
        getIvAlertaHora().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                TimePickerDialog TPD = new TimePickerDialog(getActivity(), tdp_Hora, alertaPOJO.getI_hora(), alertaPOJO.getI_minutos(), true);
                TPD.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.sAceptar), TPD);
                TPD.show();
                getCbHora().setChecked(false);
                getCbHora().setTextColor(getActivity().getResources().getColor(R.color.link));


            }

        });
        getCbHora().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getCbHora().isChecked()) {

                    getTvAlertaHora().setTextColor(getActivity().getResources().getColor(R.color.verdeAgua));
                    getTvAlertaHora().setText(getString(R.string.sHHMM));
                    getCbHora().setTextColor(getActivity().getResources().getColor(R.color.verdeAgua));
                } else {
                    if (getTvAlertaHora().getText().toString().equalsIgnoreCase(getString(R.string.sHHMM))) {
                        getTvAlertaHora().setText(getString(R.string.sHHMM));
                        getCbHora().setTextColor(getActivity().getResources().getColor(R.color.link));
                        getTvAlertaHora().setTextColor(getActivity().getResources().getColor(R.color.grisMuyOscuro));
                    } else {
                        getTvAlertaHora().setText(new StringBuilder().append(pad(alertaPOJO.getI_hora()))
                                .append(":").append(pad(alertaPOJO.getI_minutos())));
                        getTvAlertaHora().setTextColor(getActivity().getResources().getColor(R.color.verdeAgua));
                    }
                    getCbHora().setTextColor(getActivity().getResources().getColor(R.color.link));
                    getTvAlertaHora().setTextColor(getActivity().getResources().getColor(R.color.grisMuyOscuro));
                }
            }
        });
        getIvAlertaRepetir().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                showNoticeDialogSemana(getActivity(), alertaPOJO, alertaPOJO.getI_idSesion());

                alertaPOJO.setDiaByTextView(getActivity(), getTvAlertaRepetir());


            }


        });

        /**
         *
         */
        getIvAlertaRegistrar().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(getClass().getName(), "Registra " + modo);

                boolean titulo = true;
                boolean localidad = true;

                if (getEt_Titulo().getText().toString().isEmpty()) {
                    getTilTituloAlerta().setError(getString(R.string.sCampoObligatorio));
                    titulo = false;
                }
                if (getEt_Localidad().getText().toString().isEmpty()) {
                    getTilCiudad().setError(getString(R.string.sCampoObligatorio));
                    localidad = false;
                }

                if (titulo && localidad) {
                    if (!(utilidades.compruebaSpinner(getActivity().getApplicationContext(), spinnerList))) {

                        alertaPOJO.setDiaByTextView(getActivity(), getTvAlertaRepetir());

                        if (modo.equalsIgnoreCase(getString(R.string.update)))
                            updateAlerta();
                        else if (modo.equalsIgnoreCase(getString(R.string.crear)))
                            insertAlerta();
                        else if (modo.equalsIgnoreCase(getString(R.string.consultar))) {

                            consultarEvento();
                        }


                    }

                } else
                    Toast.makeText(getActivity(), getString(R.string.compruebeCampos), Toast.LENGTH_SHORT).show();
            }
        });


        if (modo.equalsIgnoreCase(getString(R.string.update))) {
            selectAlerta();
            getActivity().setTitle(R.string.title_activity_alerta_update);
        }


        addTextCheckListener(getEt_Titulo(), getTilTituloAlerta());
        // addTextCheckListener(getEt_Localidad(), getTilCiudad());

        return rootView;
    }

    /**
     * A partir de la provincia, devuelve las localidades de ésta.
     *
     * @param s
     */
    private void selectLocalidad(final int s) {


        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_selectLocalidad),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Log.e(getClass().getName(), "selectLocalidad " + response);
                        try {
                            JSONArray jsonArray = new JSONArray();
                            JSONObject json = new JSONObject(response);
                            ArrayList<String> array = new ArrayList<>();
                            jsonArray = json.getJSONArray(getString(R.string.result));

                            int success = json.getInt(getString(R.string.success));
                            if (success == 1) {
                                if (jsonArray.length() > 0) {

                                    for (int i = 0; i < jsonArray.length(); i++) {

                                        JSONObject jsonObject = new JSONObject();
                                        jsonObject = jsonArray.getJSONObject(i);

                                        array.add(jsonObject.getString(getString(R.string.localidad)));

                                    }

                                    if (array.size() > 0) {

                                        String[] string = new String[array.size()];
                                        string = array.toArray(string);

                                        ArrayAdapter<String> adapter = new ArrayAdapter<>
                                                (getActivity(), android.R.layout.simple_list_item_1, string);
                                        getEt_Localidad().setAdapter(adapter);


                                    }
                                }
                            }


                            Log.e(getClass().getName(), array.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Adding parameters to request

                return formatoIdProvincia(s);
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);


    }

    private Map<String, String> formatoIdProvincia(int idProvincia) {
        Map<String, String> params = new HashMap<>();


        params.put(getString(R.string.sidProvincia), String.valueOf(idProvincia));

        return params;

    }

    /**
     * @param editText
     * @param textInputLayout
     */
    private void addTextCheckListener(CustomFontEditText editText, final TextInputLayout textInputLayout) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                textInputLayout.setError(getString(R.string.sCampoObligatorio));

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                textInputLayout.setError(getString(R.string.sCampoObligatorio));
            }

            @Override
            public void afterTextChanged(Editable s) {


                textInputLayout.setError(null);
            }

        });
    }


    /**
     *
     */
    private void updateAlerta() {

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_updateDatosAlerta),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Utilidades utilidades = new Utilidades();
                            Log.e(getClass().getName(), "updateAlerta " + response);
                            int success;
                            JSONObject json = new JSONObject(response);

                            success = json.getInt(getString(R.string.success));

                            if (success == 1) {

                                rellenarFichaAlerta(json.getJSONObject(getString(R.string.result)));

                                utilidades.errorConsultaBBDD(getActivity(), getString(R.string.alertaModificada));
                            } else {
                                utilidades.errorConsultaBBDD(getActivity(), getString(R.string.alertaNoModificada));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        Log.e(getClass().getName(), error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Adding parameters to request

                return toArray(alertaPOJO);
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    /**
     *
     */
    private void insertAlerta() {

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.sCargando));
        progressDialog.show();

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_insertAlerta),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            Log.e(getClass().getName(), "insertAlerta " + response);
                            int success;
                            JSONObject json = new JSONObject(response);

                            success = json.getInt(getString(R.string.success));

                            if (success == 1) {
                                Intent i = new Intent(getActivity().getApplicationContext(), MenuPrincipal.class);

                                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getResources().getString(R.string.sDATOS), Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putInt(getActivity().getResources().getString(R.string.tab), 2);
                                editor.putInt(getActivity().getResources().getString(R.string.sIdSesion), alertaPOJO.getI_idSesion());
                                editor.putInt(getActivity().getResources().getString(R.string.sIdUsuario), alertaPOJO.getI_idUsuario());

                                editor.apply();
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                getActivity().getApplicationContext().startActivity(i);
                                getActivity().finish();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        Log.e(getClass().getName(), error.getMessage());
                        progressDialog.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //Adding parameters to request

                return toArray(alertaPOJO);
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    /**
     * @params String, String, JSONArray
     * @desc Obtener los eventos en función de los parámetros seleccionado por el usuario
     */

    private void consultarEvento() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.sCargando));
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getString(R.string.sUrl_selectEventos), new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                Log.e(getClass().getName(), "consultarEvento " + s);


                int success;

                progressDialog.dismiss();
                try {
                    JSONObject json = new JSONObject(s);
                    JSONArray array = json.getJSONArray(getString(R.string.result));
                    success = json.getInt(getResources().getString(R.string.success));


                    Utilidades utilidades = new Utilidades();


                    if (success == 1) {
                        Log.e(getClass().getName(), "consultarEvento " + array.get(0));
                        utilidades.errorConsultaBBDD(getActivity().getApplicationContext(), getActivity().getResources().getString(R.string.sMostrandoEventos));
                        Intent i = new Intent().setClass(getActivity().getApplicationContext(), EventoListado.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getResources().getString(R.string.sDATOS), Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();


                        editor.putInt(getActivity().getResources().getString(R.string.sIdUsuario), alertaPOJO.getI_idUsuario());

                        editor.putString(getActivity().getResources().getString(R.string.sresultado), String.valueOf(array));
                        editor.putInt(getActivity().getResources().getString(R.string.sSeleccion), 2);
                        editor.putString(getString(R.string.fromFragment), getString(R.string.fragment_alerta));
                        editor.putInt(getActivity().getResources().getString(R.string.sIdSesion), alertaPOJO.getI_idSesion());


                        editor.apply();

                        startActivity(i);
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_alerta_nueva, new EventoListadoFragment(), getString(R.string.fragment_evento_listado));
                        //fragmentTransaction.commit();

                    } else
                        utilidades.errorConsultaBBDD(getActivity().getApplicationContext(), getActivity().getResources().getString(R.string.sNoSeHanEncontradoDatos));


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(getClass().getName(), e.getMessage());
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                progressDialog.dismiss();
                Log.e(getClass().getName(), volleyError.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //Adding parameters to request

                Log.e(getClass().getName(), alertaPOJO.getll_diaSeleccionado().toString() + "|-| " + Arrays.toString(alertaPOJO.getI_idiaSeleccionado()) + "|-| " + Arrays.toString(alertaPOJO.getIsSelectedArray()));
                alertaPOJO.setS_categoria(getSp_eventoCategoria().getSelectedItem().toString());
                alertaPOJO.setS_clasificacion(getSp_Clasificacion().getSelectedItem().toString());
                alertaPOJO.setS_tipo(getSp_Tipo().getSelectedItem().toString());
                alertaPOJO.setS_ciudad(getSp_Ciudad().getSelectedItem().toString());
                alertaPOJO.setS_fecha(getTvAlertaFecha().getText().toString());
                alertaPOJO.setS_hora(getTvAlertaHora().getText().toString());
                return toArray(alertaPOJO);
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    private Map<String, String> toArray(AlertaPOJO alertaPOJO) {


        Map<String, String> params = new HashMap<>();

        JSONArray jsonArray = new JSONArray();


        int i = 0;
        for (boolean valor : alertaPOJO.getIsSelectedArray()) {

            if (valor)
                jsonArray.put(i);
            i++;
        }
        //Formatear los valores para evitar valores null y reacer el updateAlerta a la base de datos.
        params.put(getString(R.string.sIdAlerta), String.valueOf(alertaPOJO.getI_idalerta()));
        params.put(getString(R.string.titulo), getEt_Titulo().getText().toString());
        params.put(getString(R.string.nombreEvento), getEt_EventoTitulo().getText().toString());
        if (getSp_eventoCategoria().getSelectedItem().toString().equalsIgnoreCase(getString(R.string.cualquiera))) {
            params.put(getString(R.string.categoria), getString(R.string.categoria_dummy));
            params.put(getString(R.string.clasificacion), getString(R.string.categoria_dummy));
        } else {
            params.put(getString(R.string.categoria), String.valueOf(getSp_eventoCategoria().getSelectedItemPosition()));
            params.put(getString(R.string.clasificacion), String.valueOf(getSp_Clasificacion().getSelectedItemPosition()));

        }

        params.put(getString(R.string.tipo), String.valueOf(getSp_Tipo().getSelectedItemPosition()));
        params.put(getString(R.string.ciudad), String.valueOf(getSp_Ciudad().getSelectedItem()));
        params.put(getString(R.string.localidad), getEt_Localidad().getText().toString());
        params.put(getString(R.string.nombreSala), getEt_NombreSala().getText().toString());

        if (getTvAlertaFecha().getText().toString().equalsIgnoreCase(getString(R.string.sAAMMDD)))
            params.put(getString(R.string.fecha), getString(R.string.sFechaDummy));
        else
            params.put(getString(R.string.fecha), getTvAlertaFecha().getText().toString());

        if (getTvAlertaHora().getText().toString().equalsIgnoreCase(getString(R.string.sHoraDummy)) || getTvAlertaHora().getText().toString().equalsIgnoreCase(getString(R.string.sHHMM)))
            params.put(getString(R.string.hora), getString(R.string.sHHMM));
        else params.put(getString(R.string.hora), getTvAlertaHora().getText().toString());

        params.put(getString(R.string.repetir), jsonArray.toString());
        params.put(getString(R.string.artista), getEt_Artista().getText().toString());

        params.put(getString(R.string.sIdUsuario), String.valueOf(alertaPOJO.getI_idSesion()));
        params.put(getString(R.string.diaSeleccionado), Arrays.toString(alertaPOJO.getIsSelectedArray()));

        Log.e(getClass().getName(), "toArray:\n" + params.toString());

        return params;
    }


    /**
     * @params String, String, JSONArray
     * @desc Obtener los eventos en función de los parámetros seleccionado por el usuario
     */

    private void selectAlerta() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.sCargando));
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getString(R.string.sUrl_selectAllAlerta), new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                Log.e(getClass().getName(), "selectAlerta " + s);


                int success = 0;

                progressDialog.dismiss();
                try {


                    JSONObject jsonObject = new JSONObject(s);
                    success = jsonObject.getInt(getString(R.string.success));

                    alertaPOJO.repetir(jsonObject.getJSONArray(getString(R.string.repetir)));
                    rellenarFichaAlerta(jsonObject.getJSONObject(getString(R.string.result)));

                    setDia();


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(getClass().getName(), e.getMessage());
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(getClass().getName(), volleyError.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //Adding parameters to request

                return alertaPOJO.formatoId(getActivity());
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    /**
     *
     */
    private void setDia() {

        if (alertaPOJO.getll_diaSeleccionado().isEmpty()) {
            Log.e(getClass().getName(), "setdia empty");
            getTvAlertaRepetir().setText(getString(R.string.nunca));
            getTvAlertaRepetir().setTextColor(getActivity().getResources().getColor(R.color.grisMuyOscuro));
        } else {
            boolean cont = true;
            getTvAlertaRepetir().setText("");
            Log.e(getClass().getName(), "setdia NO empty " + alertaPOJO.getll_diaSeleccionado().toString());

            for (int i = 0; i < alertaPOJO.getll_diaSeleccionado().size(); i++) {

                if (!alertaPOJO.getll_diaSeleccionado().contains(i)) {
                    cont = false;
                }
            }

            if (cont) {
                getTvAlertaRepetir().setText(getString(R.string.sRepetir));
                getTvAlertaRepetir().setTextColor(getActivity().getResources().getColor(R.color.verdeAgua));
            } else {
                String dia = getTvAlertaRepetir().getText().toString();

                if (alertaPOJO.getll_diaSeleccionado().contains(0)) {
                    dia = dia.concat(getString(R.string.sInicialLunes));
                    getTvAlertaRepetir().setText(dia);

                }
                if (alertaPOJO.getll_diaSeleccionado().contains(1)) {
                    dia = dia.concat(getString(R.string.sInicialMartes));
                    getTvAlertaRepetir().setText(dia);

                }
                if (alertaPOJO.getll_diaSeleccionado().contains(2)) {
                    dia = dia.concat(getString(R.string.sInicialMiercoles));
                    getTvAlertaRepetir().setText(dia);


                }
                if (alertaPOJO.getll_diaSeleccionado().contains(3)) {
                    dia = dia.concat(getString(R.string.sInicialJueves));
                    getTvAlertaRepetir().setText(dia);


                }
                if (alertaPOJO.getll_diaSeleccionado().contains(4)) {
                    dia = dia.concat(getString(R.string.sInicialViernes));
                    getTvAlertaRepetir().setText(dia);


                }
                if (alertaPOJO.getll_diaSeleccionado().contains(5)) {
                    dia = dia.concat(getString(R.string.sInicialSabado));
                    getTvAlertaRepetir().setText(dia);


                }
                if (alertaPOJO.getll_diaSeleccionado().contains(6)) {
                    dia = dia.concat(getString(R.string.sInicialDomingo));
                    getTvAlertaRepetir().setText(dia);

                }
                getTvAlertaRepetir().setTextColor(getActivity().getResources().getColor(R.color.verdeAgua));
            }

        }
    }

    /**
     * @param jsonObject
     * @throws JSONException
     */
    private void rellenarFichaAlerta(JSONObject jsonObject) throws JSONException {


        String nombreEvento = jsonObject.getString("nombreEvento");
        String nombreSala = jsonObject.getString("nombreSala");
        String artista = jsonObject.getString("artistaEvento");
        String localidad = jsonObject.getString("localidad");

        getEt_Titulo().setText(jsonObject.getString("tituloAlerta"));
        getEt_EventoTitulo().setText(nombreEvento);
        getEt_NombreSala().setText(nombreSala);
        getEt_Artista().setText(artista);

        String cat = jsonObject.getString("idCategoria");

        int posicion = jsonObject.getInt("posicion");

        if (cat.equalsIgnoreCase(getString(R.string.categoria_dummy))) {

            String[] arrayList = getActivity().getResources().getStringArray(R.array.categoriaConsulta);
            for (int i = 0; i < arrayList.length; i++) {

                if (arrayList[i].equalsIgnoreCase(getString(R.string.cualquiera))) {
                    posicion = i;
                }

            }

            this.getSp_eventoCategoria().setSelection(posicion);
            Utilidades.rellenarSpinnerAlerta(getActivity(), getSp_eventoCategoria(), getSp_Clasificacion());


        } else {
            this.getSp_eventoCategoria().setSelection(Integer.parseInt(jsonObject.getString("idCategoria")));
            Utilidades.rellenarSpinnerAlerta(getActivity(), getSp_eventoCategoria(), getSp_Clasificacion());
            getSp_Clasificacion().setSelection(posicion);
        }


        this.getSp_Tipo().setSelection(Integer.parseInt(jsonObject.getString("idTipo")));

        this.getSp_Ciudad().setSelection(Integer.parseInt(jsonObject.getString("idProvincia")));


        getEt_Localidad().setText(localidad);
        String fecha;
        fecha = jsonObject.getString("fechaEvento");

        if (fecha.equalsIgnoreCase(getString(R.string.sFechaDummy)) || fecha.equalsIgnoreCase(getString(R.string.sFecha00))) {
            getTvAlertaFecha().setText(getString(R.string.sAAMMDD));
        } else {
            getTvAlertaFecha().setText(jsonObject.getString("fechaEvento"));
        }
        String hora = jsonObject.getString("horaEvento");
        if (hora.equalsIgnoreCase(getString(R.string.sHoraDummy)) || hora.equalsIgnoreCase(getString(R.string.sHoraDummyPHP))) {
            getTvAlertaHora().setText(getString(R.string.sHHMM));
        } else {
            getTvAlertaHora().setText(jsonObject.getString("horaEvento"));
        }
        alertaPOJO.setI_idSesion(jsonObject.getInt("idUsuario"));


        Log.e(getClass().getName(), "rellenarFichaAlerta " + jsonObject.toString());
    }


    private void updateFecha() {
        getTvAlertaFecha().setText(new StringBuilder().append(alertaPOJO.getI_ano()).append("-").append(alertaPOJO.getI_mes() + 1).append("-").append(alertaPOJO.getI_dia()));
    }

    /**
     * @param c
     * @param alertaPOJO
     * @param idSesion
     */
    private static void showNoticeDialogSemana(Context c, AlertaPOJO alertaPOJO, int idSesion) {
        // Create an instance of the dialog fragment and show it
        activity.getIntent().putExtra("diasSemana", alertaPOJO);
        activity.getIntent().putExtra("idSesion", idSesion);

        DialogSemana dialog = DialogSemana.newInstance(alertaPOJO, idSesion);
        dialog.show(fragmentManager, c.getString(R.string.fragment_dialog_semana));
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    Log.e(getClass().getSimpleName(), "onMenuOpened...unable to set icons for overflow menu", e);
                }
            }
        }
        assert menu != null;
        for (int i = 0; i < menu.size(); i++) {
            MenuItem mi = menu.getItem(i);
            String title = mi.getTitle().toString();
            Spannable newTitle = new SpannableString(title);
            Typeface font = Typeface.createFromAsset(getActivity().getAssets(), getResources().getString(R.string.fontAmaticRegular));
            newTitle.setSpan(new CustomTypeFaceSpan("", font), 0, newTitle.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            mi.setTitle(newTitle);

        }


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {


        if (modo.equalsIgnoreCase(getString(R.string.update))) {
            inflater.inflate(R.menu.menu_alerta_update, menu);

        } else
            Log.e(getClass().getName(), "onCreateOptionsMenu1\n" + modo);


        super.onCreateOptionsMenu(menu, inflater);
    }


    /**
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        switch (item.getItemId()) {

            case R.id.miDeleteAlerta:

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                TextView textView = new TextView(getActivity().getApplicationContext());
                textView.setText(getActivity().getResources().getString(R.string.sAlerta));
                textView.setTypeface(Typeface.createFromAsset(getActivity().getApplicationContext().getAssets(), getActivity().getApplicationContext().getResources().getString(R.string.fontAmaticRegular)));
                textView.setPadding(15, 10, 0, 0);
                textView.setTextSize(getActivity().getApplicationContext().getResources().getDimension(R.dimen.size_15));
                textView.setTextColor(getActivity().getApplicationContext().getResources().getColor(R.color.rojo));

                builder.setCustomTitle(textView);
                builder.setIcon(R.drawable.ic_warning);

                builder.setMessage(getActivity().getResources().getString(R.string.sEliminarAlerta));

                builder.setPositiveButton(getActivity().getResources().getString(R.string.sAceptar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {

                        deleteAlerta();


                    }
                });
                builder.setNegativeButton(getActivity().getResources().getString(R.string.sCancelar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {

                        dialogo1.cancel();


                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                alert.getWindow().getAttributes();

                Button button = new Button(getActivity().getApplicationContext());
                button = alert.getButton(DialogInterface.BUTTON_POSITIVE);
                button.setTextColor(getActivity().getApplicationContext().getResources().getColor(R.color.link));
                button.setBackgroundColor(getActivity().getApplicationContext().getResources().getColor(R.color.blanco));
                Button buttonCancel = new Button(getActivity().getApplicationContext());
                buttonCancel = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
                buttonCancel.setTextColor(getActivity().getApplicationContext().getResources().getColor(R.color.blanco));
                buttonCancel.setBackgroundColor(getActivity().getApplicationContext().getResources().getColor(R.color.link));

                //Preparamos las fuentes personalizadas
                Typeface fontTextoBoton = Typeface.createFromAsset(getActivity().getApplicationContext().getAssets(), getActivity().getApplicationContext().getResources().getString(R.string.fontAmaticRegularBold));
                button.setTypeface(fontTextoBoton);
                buttonCancel.setTypeface(fontTextoBoton);

                TextView textView1 = (TextView) alert.findViewById(android.R.id.message);
                textView1.setTypeface(Typeface.createFromAsset(getActivity().getApplicationContext().getAssets(), getActivity().getApplicationContext().getResources().getString(R.string.fontAmaticRegular)));
                textView1.setTextSize(getActivity().getApplicationContext().getResources().getDimension(R.dimen.size_10));


                return true;

            case R.id.miBuscarAlerta:


                consultarEvento();

                return true;


        }

        return super.onOptionsItemSelected(item);
    }


    private void deleteAlerta() {

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.sCargando));
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getString(R.string.sUrl_deleteAlerta), new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                Log.e(getClass().getName(), "deleteAlerta " + s);


                int success;

                progressDialog.dismiss();
                try {
                    JSONObject json = new JSONObject(s);
                    success = json.getInt(getResources().getString(R.string.success));


                    Utilidades utilidades = new Utilidades();


                    if (success == 1) {
                        Intent i = new Intent(activity.getApplicationContext(), MenuPrincipal.class);


                        SharedPreferences sharedPreferences = activity.getSharedPreferences(getString(R.string.DATOS), Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putInt(activity.getResources().getString(R.string.sIdSesion), alertaPOJO.getI_idUsuario());
                        editor.putInt(activity.getResources().getString(R.string.tab), 2);


                        editor.apply();
                        activity.startActivity(i);
                        activity.finish();


                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        activity.getApplicationContext().startActivity(i);
                        activity.finish();
                    } else {
                        Log.e(getClass().getName(), "deleteAlerta " + json.getString(getString(R.string.result)));
                        utilidades.errorConsultaBBDD(getActivity().getApplicationContext(), getActivity().getResources().getString(R.string.NoseEliminaAlerta));
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(getClass().getName(), e.getMessage());
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Log.e(getClass().getName(), volleyError.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //Adding parameters to request

                Log.e(getClass().getName(), alertaPOJO.getll_diaSeleccionado().toString() + "|-| " + Arrays.toString(alertaPOJO.getI_idiaSeleccionado()) + "|-| " + Arrays.toString(alertaPOJO.getIsSelectedArray()));
                return toArray(alertaPOJO);
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    /**
     * getters and setters
     */

    private CustomFontEditText getEt_Artista() {
        return et_Artista;
    }

    private void setEt_Artista(CustomFontEditText et_Artista) {
        this.et_Artista = et_Artista;
    }

    private CustomFontEditText getEt_EventoTitulo() {
        return et_EventoTitulo;
    }

    private void setEt_EventoTitulo(CustomFontEditText et_EventoTitulo) {
        this.et_EventoTitulo = et_EventoTitulo;
    }

    private CustomFontEditText getEt_NombreSala() {
        return et_NombreSala;
    }

    private void setEt_NombreSala(CustomFontEditText et_NombreSala) {
        this.et_NombreSala = et_NombreSala;
    }

    private CustomFontEditText getEt_Titulo() {
        return et_Titulo;
    }

    private void setEt_Titulo(CustomFontEditText et_Titulo) {
        this.et_Titulo = et_Titulo;
    }

    private AutoCompleteTextView getEt_Localidad() {
        return et_Localidad;
    }

    private void setEt_Localidad(AutoCompleteTextView etAlertaLocalidad) {
        et_Localidad = etAlertaLocalidad;
    }

    private ImageView getIvAlertaFecha() {
        return iv_Fecha;
    }

    private void setIvAlertaFecha(ImageView iv_Fecha) {
        this.iv_Fecha = iv_Fecha;
    }

    private ImageView getIvAlertaHora() {
        return iv_Hora;
    }

    private void setIvAlertaHora(ImageView iv_Hora) {
        this.iv_Hora = iv_Hora;
    }

    private ImageView getIvAlertaRegistrar() {
        return iv_Registrar;
    }

    private void setIvAlertaRegistrar(ImageView iv_Registrar) {
        this.iv_Registrar = iv_Registrar;
    }

    private ImageView getIvAlertaRepetir() {
        return iv_Repetir;
    }

    private void setIvAlertaRepetir(ImageView iv_Repetir) {
        this.iv_Repetir = iv_Repetir;
    }

    private Spinner getSp_eventoCategoria() {
        return sp_Categoria;
    }

    private void setSp_Categoria(Spinner sp_Categoria) {
        this.sp_Categoria = sp_Categoria;
    }

    private Spinner getSp_Ciudad() {
        return sp_Ciudad;
    }

    private void setSp_Ciudad(Spinner sp_Ciudad) {
        this.sp_Ciudad = sp_Ciudad;
    }

    private Spinner getSp_Clasificacion() {
        return sp_Clasificacion;
    }

    private void setSp_Clasificacion(Spinner sp_Clasificacion) {
        this.sp_Clasificacion = sp_Clasificacion;
    }

    private Spinner getSp_Tipo() {
        return sp_Tipo;
    }

    private void setSp_Tipo(Spinner sp_Tipo) {
        this.sp_Tipo = sp_Tipo;
    }

    private CustomFontTextView getTvAlertaFecha() {
        return tv_Fecha;
    }

    private void setTvAlertaFecha(CustomFontTextView tv_Fecha) {
        this.tv_Fecha = tv_Fecha;
    }

    private CustomFontTextView getTvAlertaHora() {
        return tv_Hora;
    }

    private void setTvAlertaHora(CustomFontTextView tv_Hora) {
        this.tv_Hora = tv_Hora;
    }

    private CustomFontTextView getTvAlertaRepetir() {
        return tv_Repetir;
    }

    private void setTvAlertaRepetir(CustomFontTextView tv_Repetir) {
        this.tv_Repetir = tv_Repetir;
    }

    private TextInputLayout getTilTituloAlerta() {
        return tilTituloAlerta;
    }

    private void setTilTituloAlerta(TextInputLayout tilTituloAlerta) {
        this.tilTituloAlerta = tilTituloAlerta;
    }

    private TextInputLayout getTilCiudad() {
        return tilCiudad;
    }

    private void setTilCiudad(TextInputLayout tilCiudad) {
        this.tilCiudad = tilCiudad;
    }
}
