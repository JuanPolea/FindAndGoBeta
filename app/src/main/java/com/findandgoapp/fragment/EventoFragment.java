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

package com.findandgoapp.fragment;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
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
import com.findandgoapp.activity.TabAdmin;
import com.findandgoapp.adapter.MyArrayAdapter;
import com.findandgoapp.custom.CustomFontEditText;
import com.findandgoapp.custom.CustomFontTextView;
import com.findandgoapp.pojo.EventoPOJO;
import com.findandgoapp.pojo.SugerenciaPOJO;
import com.findandgoapp.tools.Utilidades;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;


/**
 * A placeholder fragment containing a simple view.
 */
public class EventoFragment extends Fragment {

    private final static EventoPOJO eventoPOJO = new EventoPOJO();
    private static final int REQUEST_PERMISSION_SETTING = 101;
    private static final int EXTERNAL_STORAGE_PERMISSION_CONSTANT = 100;
    private static int idSesion;
    private SharedPreferences sharedPreferences;
    private CustomFontEditText et_eventoNombre;
    private CustomFontEditText et_eventoLugar;

    private Spinner sp_eventoCategoria;
    private Spinner sp_eventoClasificacion;
    private Spinner sp_eventoTipo;
    private Spinner sp_eventoCiudad;

    private Spinner sp_eventoVia;

    private AutoCompleteTextView et_eventoLocalidad;
    private CustomFontEditText et_eventoCalle;
    private CustomFontEditText et_eventoNumero;
    private CustomFontEditText et_eventoCp;
    private CustomFontEditText et_eventoArtista;
    private CustomFontEditText et_eventoPrecio;
    private CustomFontEditText et_eventoDescripcion;

    private CustomFontTextView tv_eventoFecha;
    private CustomFontTextView tv_eventoHora;

    private TextInputLayout tilNombre;
    private TextInputLayout tilLugar;
    private TextInputLayout tilLocalidad;
    private TextInputLayout tilCalle;
    private TextInputLayout tilDireccion;
    private TextInputLayout tilNumero;
    private TextInputLayout tilCp;
    private TextInputLayout tilHora;
    private TextInputLayout tilFecha;


    private TextInputLayout tilArtista;
    private TextInputLayout tilPrecio;
    private TextInputLayout tilDescripcion;


    private ImageButton b_eventoFecha;
    private ImageButton b_eventoHora;

    private ImageButton b_eventoConsulta;
    private ImageView iv_efoto;
    private boolean b_isImage;
    private String modo;


    private CheckBox cbFecha;
    private CheckBox cbHora;
    private SugerenciaPOJO sugerenciaPOJO;

    private ProgressDialog progressDialog;
    private SharedPreferences permissionStatus;

    public EventoFragment() {
    }

    /**
     * @param c
     * @return
     */
    private static String pad(int c) {

        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        Log.e(getClass().getName(), "onCreateView");
        setHasOptionsMenu(false);

        final ArrayList<String> aet_consultar = new ArrayList<>();
        final ArrayList<Spinner> asp_consultar = new ArrayList<>();

        final View view;
        final MyArrayAdapter adapterCategoria;

        sugerenciaPOJO = new SugerenciaPOJO();

        sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(getString(R.string.sDATOS), Context.MODE_PRIVATE);
        idSesion = sharedPreferences.getInt(getString(R.string.sIdSesion), 0);
        eventoPOJO.setI_idUsuario(sharedPreferences.getInt(getString(R.string.sIdSesion), 0));
        permissionStatus = getActivity().getSharedPreferences(getString(R.string.permissionStatus), MODE_PRIVATE);
        modo = sharedPreferences.getString(getString(R.string.modo), getString(R.string.svacio));


        if (modo.equalsIgnoreCase(getString(R.string.crear))) {

            view = inflater.inflate(R.layout.fragment_evento_nuevo, container, false);
            getActivity().setTitle(getString(R.string.sCrearEvento));

            setSp_eventoVia((Spinner) view.findViewById(R.id.idSpEventoNuevoVia));
            final MyArrayAdapter adapterVia = new MyArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, getActivity().getResources().getStringArray(R.array.tipoVia));
            // Aplicamos el adaptador al spinner
            getSp_eventoVia().setAdapter(adapterVia);
            asp_consultar.add(getSp_eventoVia());

            getSp_eventoVia().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> arg0, View arg1,
                                           int arg2, long arg3) {
                    // TODO Auto-generated method stub

                    if (getSp_eventoVia().getSelectedItem().toString().equalsIgnoreCase(getResources().getString(R.string.sOtro))) {

                        sugerenciaPOJO.setIdUsuario(idSesion);
                        sugerenciaPOJO.setTipo(Integer.parseInt(getString(R.string.sugerenciaVia)));
                        sugerenciaPOJO.setIdSugerencia(getSp_eventoVia().getSelectedItemPosition());

                        sugerenciaPOJO.setTexto(getActivity().getResources().getString(R.string.sDeseaAddVia));

                        sugerenciaPOJO.addSugerencia(getActivity());

                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub

                }

            });


            setSp_eventoCategoria((Spinner) view.findViewById(R.id.idSpEventoNuevoCategoria));
            asp_consultar.add(getSp_eventoCategoria());
            adapterCategoria = new MyArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, getActivity().getResources().getStringArray(R.array.categoria));
            getSp_eventoCategoria().setAdapter(adapterCategoria);

            setEt_eventoCalle((CustomFontEditText) view.findViewById(R.id.idEtEventoNuevoCalle));

            setEt_eventoNumero((CustomFontEditText) view.findViewById(R.id.idEtEventoNuevoNumero));

            setEt_eventoCp((CustomFontEditText) view.findViewById(R.id.idEtEventoNuevoCp));
            setEt_eventoDescripcion((CustomFontEditText) view.findViewById(R.id.idEtEventoNuevoDescripcion));

            setTilCalle((TextInputLayout) view.findViewById(R.id.tilEventoNuevoCalle));
            setTilNumero((TextInputLayout) view.findViewById(R.id.tilEventoNuevoNumero));
            setTilCp(((TextInputLayout) view.findViewById(R.id.tilEventoNuevoCp)));
            setTilDescripcion((TextInputLayout) view.findViewById(R.id.tilEventoNuevoDescripcion));
            setTilHora((TextInputLayout) view.findViewById(R.id.tilEventoNuevoHora));
            setTilFecha((TextInputLayout) view.findViewById(R.id.tilEventoNuevoFecha));

            addTextCheckListener(getEt_eventoCalle(), getTilCalle());
            addTextCheckListener(getEt_eventoNumero(), getTilNumero());
            addTextCheckListener(getEt_eventoCp(), getTilCp());
            addTextCheckListener(getEt_eventoDescripcion(), getTilDescripcion());


        } else {


            view = inflater.inflate(R.layout.fragment_evento_consulta, container, false);
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.addToBackStack(getString(R.string.fragment_evento_consulta));

            setSp_eventoCategoria((Spinner) view.findViewById(R.id.idSpEventoNuevoCategoria));
            asp_consultar.add(getSp_eventoCategoria());
            adapterCategoria = new MyArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, getActivity().getResources().getStringArray(R.array.categoriaConsulta));

            getSp_eventoCategoria().setAdapter(adapterCategoria);

            setTv_eventoFecha((CustomFontTextView) view.findViewById(R.id.idTvEventoNuevoFecha));
            setTv_eventoHora((CustomFontTextView) view.findViewById(R.id.idTvEventoNuevoHora));

            setCbFecha((CheckBox) view.findViewById(R.id.cbFechaEv));
            setCbHora((CheckBox) view.findViewById(R.id.cbHoraEv));

            fragmentTransaction.commit();
            getActivity().setTitle(getString(R.string.sConsultar));


            getCbFecha().setChecked(true);
            getCbHora().setChecked(true);

            if (getCbFecha().isChecked()) {
                getTv_eventoFecha().setTextColor(getActivity().getResources().getColor(R.color.verdeAgua));
                getCbFecha().setTextColor(getActivity().getResources().getColor(R.color.verdeAgua));
            }
            if (getCbHora().isChecked()) {
                getTv_eventoHora().setTextColor(getActivity().getResources().getColor(R.color.verdeAgua));
                getCbHora().setTextColor(getActivity().getResources().getColor(R.color.verdeAgua));
            }
            getCbHora().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getCbHora().isChecked()) {

                        getTv_eventoHora().setTextColor(getActivity().getResources().getColor(R.color.verdeAgua));
                        getTv_eventoHora().setText(getString(R.string.sHHMM));
                        getCbHora().setTextColor(getActivity().getResources().getColor(R.color.verdeAgua));
                    } else {

                        getCbHora().setTextColor(getActivity().getResources().getColor(R.color.link));
                        getTv_eventoHora().setTextColor(getActivity().getResources().getColor(R.color.grisMuyOscuro));
                    }
                }
            });
            getCbFecha().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getCbFecha().isChecked()) {


                        getCbFecha().setTextColor(getActivity().getResources().getColor(R.color.verdeAgua));
                        getTv_eventoFecha().setTextColor(getActivity().getResources().getColor(R.color.verdeAgua));
                        getTv_eventoFecha().setText(getString(R.string.sAAMMDD));
                    } else {
                        getCbFecha().setTextColor(getActivity().getResources().getColor(R.color.link));
                        getTv_eventoFecha().setTextColor(getActivity().getResources().getColor(R.color.grisMuyOscuro));
                    }
                }
            });


        }


        //Variables para el calendario
        final Calendar CALENDAR = Calendar.getInstance();

        eventoPOJO.setI_ano(CALENDAR.get(Calendar.YEAR));
        eventoPOJO.setI_mes(CALENDAR.get(Calendar.MONTH));
        eventoPOJO.setI_dia(CALENDAR.get(Calendar.DAY_OF_MONTH));

        setEt_eventoNombre((CustomFontEditText) view.findViewById(R.id.idEtEventoNuevoNombre));
        setTilNombre((TextInputLayout) view.findViewById(R.id.tilEventoNuevoNombre));

        setEt_eventoLugar((CustomFontEditText) view.findViewById(R.id.idEtEventoNuevoLugar));
        setTilLugar((TextInputLayout) view.findViewById(R.id.tilEventoNuevoLugar));


        setSp_eventoClasificacion((Spinner) view.findViewById(R.id.idSpEventoNuevoClasificacion));
        setSp_eventoTipo((Spinner) view.findViewById(R.id.idSpEventoNuevoTipo));
        setSp_eventoCiudad((Spinner) view.findViewById(R.id.idSpEventoNuevoCiudad));

        asp_consultar.add(getSp_eventoCiudad());
        setSp_eventoVia((Spinner) view.findViewById(R.id.idSpEventoNuevoVia));

        setEt_eventoLocalidad((AutoCompleteTextView) view.findViewById(R.id.idEtEventoNuevoLocalidad));
        setTilLocalidad((TextInputLayout) view.findViewById(R.id.tilEventoNuevoLocalidad));
        aet_consultar.add(getEt_eventoLocalidad().getText().toString());

        setEt_eventoArtista((CustomFontEditText) view.findViewById(R.id.idEtEventoNuevoArtista));
        setTilArtista((TextInputLayout) view.findViewById(R.id.tilEventoNuevoArtista));
        setEt_eventoPrecio((CustomFontEditText) view.findViewById(R.id.idEtEventoNuevoPrecio));
        setTilPrecio((TextInputLayout) view.findViewById(R.id.tilEventoNuevoPrecio));


        setTv_eventoFecha((CustomFontTextView) view.findViewById(R.id.idTvEventoNuevoFecha));
        setTv_eventoHora((CustomFontTextView) view.findViewById(R.id.idTvEventoNuevoHora));


        setB_eventoConsulta((ImageButton) view.findViewById(R.id.idBEventoNuevoRegistrar));
        setB_eventoFecha((ImageButton) view.findViewById(R.id.idBEventoNuevoFecha));
        setB_eventoHora((ImageButton) view.findViewById(R.id.idBEventoNuevoHora));


        final MyArrayAdapter adapterClasificacion = new MyArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, getActivity().getResources().getStringArray(R.array.clasificacion));
        // Aplicamos el adaptador al spinner

        getSp_eventoClasificacion().setAdapter(adapterClasificacion);


        final MyArrayAdapter adapterTipo = new MyArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, getActivity().getResources().getStringArray(R.array.tipo));
        getSp_eventoTipo().setAdapter(adapterTipo);


        final MyArrayAdapter adapterCiudad = new MyArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, getActivity().getResources().getStringArray(R.array.ciudades));
        getSp_eventoCiudad().setAdapter(adapterCiudad);


        //Añadir evento OnItemSelectedListener a los Spinners


        getSp_eventoCategoria().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                if (getSp_eventoCategoria().getSelectedItem().toString().equalsIgnoreCase(getResources().getString(R.string.cualquiera)) || getSp_eventoCategoria().getSelectedItem().toString().equalsIgnoreCase(getResources().getString(R.string.sElijaCategoria))) {

                    getSp_eventoClasificacion().setClickable(false);

                } else {
                    getSp_eventoClasificacion().setClickable(true);
                    if (getSp_eventoCategoria().getSelectedItem().toString().equalsIgnoreCase(getResources().getString(R.string.sOtro))) {

                        sugerenciaPOJO.setIdUsuario(idSesion);
                        sugerenciaPOJO.setTipo(Integer.parseInt(getString(R.string.sugerenciaCategoria)));
                        sugerenciaPOJO.setIdSugerencia(getSp_eventoCategoria().getSelectedItemPosition());
                        sugerenciaPOJO.setTexto(getActivity().getResources().getString(R.string.sDeseaAddCategoria));
                        sugerenciaPOJO.addSugerencia(getActivity());
                        getSp_eventoClasificacion().setSelection(0);

                    }

                }
                Utilidades.rellenarSpinner(getActivity(), getSp_eventoCategoria(), getSp_eventoClasificacion());
                adapterCategoria.notifyDataSetChanged();


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }

        });

        getSp_eventoClasificacion().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                int selection = getSp_eventoClasificacion().getSelectedItemPosition();
                Log.e(getClass().getName(), getSp_eventoClasificacion().getSelectedItem().toString());

                if (!getSp_eventoCategoria().getSelectedItem().toString().equalsIgnoreCase(getResources().getString(R.string.cualquiera)) && !getSp_eventoCategoria().getSelectedItem().toString().equalsIgnoreCase(getResources().getString(R.string.sOtro)) && getSp_eventoClasificacion().getSelectedItem().toString().equalsIgnoreCase(getResources().getString(R.string.sOtro))) {

                    sugerenciaPOJO.setIdUsuario(idSesion);
                    sugerenciaPOJO.setTipo(Integer.parseInt(getString(R.string.sugerenciaClasificacion)));
                    sugerenciaPOJO.setIdSugerencia(getSp_eventoCategoria().getSelectedItemPosition());
                    sugerenciaPOJO.setTexto(getActivity().getResources().getString(R.string.sDeseaAddClasificacion));
                    sugerenciaPOJO.addSugerencia(getActivity());

                    getSp_eventoClasificacion().setSelection(selection);

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });

        getSp_eventoTipo().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                Log.e(getClass().getName(), getSp_eventoTipo().getSelectedItem().toString());
                if (getSp_eventoTipo().getSelectedItem().toString().equalsIgnoreCase(getResources().getString(R.string.sOtro))) {

                    sugerenciaPOJO.setIdUsuario(idSesion);
                    sugerenciaPOJO.setTipo(Integer.parseInt(getString(R.string.sugerenciaTipo)));
                    sugerenciaPOJO.setIdSugerencia(getSp_eventoTipo().getSelectedItemPosition());
                    sugerenciaPOJO.setTexto(getActivity().getResources().getString(R.string.sDeseaAddTipo));
                    sugerenciaPOJO.addSugerencia(getActivity());
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });

        getSp_eventoCiudad().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                selectLocalidad(getSp_eventoCiudad().getSelectedItemPosition());
                getEt_eventoLocalidad().setText("");

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });
        //Se añade el Date Picker Dialog al botón que representa la fecha


        /**
         * Creamos un DatePickerDialog.OnDateSetListener que usaremos para dar formato al calendario
         * <p/>
         * Métodos públicos
         * abstract void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
         */
        final DatePickerDialog.OnDateSetListener dpEventoFecha = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int ano, int mes, int dia) {


                eventoPOJO.setI_ano(ano);
                eventoPOJO.setI_mes(mes);
                eventoPOJO.setI_dia(dia);

                getTv_eventoFecha().setText(new StringBuilder()
                        .append(eventoPOJO.getI_ano()).append("-")
                        .append(eventoPOJO.getI_mes() + 1).append("-")
                        .append(eventoPOJO.getI_dia()));

                updateDisplay();

            }
        };

        getB_eventoFecha().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                DatePickerDialog DPD = new DatePickerDialog(getActivity(), dpEventoFecha, eventoPOJO.getI_ano(), eventoPOJO.getI_mes(), eventoPOJO.getI_dia());
                DPD.getDatePicker().setMinDate(CALENDAR.getTimeInMillis());
                DPD.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.sAceptar), DPD);
                if (modo.equalsIgnoreCase(getString(R.string.consultar))) {
                    getCbFecha().setChecked(false);
                }
                DPD.show();

            }

        });

        //Se añade el Time Picker Dialog al botón que representa la hora
        final TimePickerDialog.OnTimeSetListener tpdEventoHora = new TimePickerDialog.OnTimeSetListener() {

            public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {


                eventoPOJO.setI_hora(selectedHour);
                eventoPOJO.setI_minutos(selectedMinute);

                // set current time into textv_iew

                tv_eventoHora.setText(new StringBuilder().append(pad(eventoPOJO.getI_hora()))
                        .append(":").append(pad(eventoPOJO.getI_minutos())));
            }
        };
        getB_eventoHora().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                TimePickerDialog TPD = new TimePickerDialog(getActivity(), tpdEventoHora, eventoPOJO.getI_hora(), eventoPOJO.getI_minutos(), true);
                TPD.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.sAceptar), TPD);
                TPD.setTitle(null);
                TPD.show();
                if (modo.equalsIgnoreCase(getString(R.string.consultar))) {
                    getCbHora().setChecked(false);
                }
            }
        });

        //Al pulsar el botón de consulta se realiza la consulta a base de datos si al menos un campo está relleno
        getB_eventoConsulta().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // consultamos el evento en un hilo en segundo plano
                //Obtenemos el idUsuario de la Actividad anterior
                Utilidades ut = new Utilidades();

                boolean nombre = true;
                boolean lugar = true;
                boolean localidad = true;
                boolean calle = true;
                boolean cp = true;

                boolean numero = true;
                boolean fecha = true;
                boolean hora = true;


                if (getEt_eventoLocalidad().getText().toString().isEmpty()) {
                    getTilLocalidad().setError(getString(R.string.sCampoObligatorio));
                    localidad = false;
                }


                if (modo.equalsIgnoreCase(getString(R.string.crear))) {

                    if (getEt_eventoNombre().getText().toString().isEmpty()) {
                        getTilNombre().setError(getString(R.string.sCampoObligatorio));
                        nombre = false;
                    }
                    if (getEt_eventoLugar().getText().toString().isEmpty()) {
                        getTilLugar().setError(getString(R.string.sCampoObligatorio));
                        lugar = false;
                    }

                    if (getEt_eventoCalle().getText().toString().isEmpty()) {
                        getTilCalle().setError(getString(R.string.sCampoObligatorio));
                        calle = false;
                    }

                    if (getEt_eventoNumero().getText().toString().isEmpty()) {
                        getTilNumero().setError(getString(R.string.sCampoObligatorio));
                        numero = false;
                    }
                    if (getEt_eventoCp().getText().toString().isEmpty()) {
                        getTilCp().setError(getString(R.string.sCampoObligatorio));
                        cp = false;
                    }
                    if (getEt_eventoLocalidad().getText().toString().isEmpty()) {
                        getTilLocalidad().setError(getString(R.string.sCampoObligatorio));
                    }
                    if (getTv_eventoFecha().getText().toString().isEmpty() || getTv_eventoFecha().getText().toString().equalsIgnoreCase(getString(R.string.sCampoObligatorio))) {
                        fecha = false;
                        getTilFecha().setError(getString(R.string.sCampoObligatorio));

                    }
                    if (getTv_eventoHora().getText().toString().isEmpty() || getTv_eventoHora().getText().toString().equalsIgnoreCase(getString(R.string.sCampoObligatorio))) {
                        hora = false;
                        getTilHora().setError(getString(R.string.sCampoObligatorio));


                    }

                    addTextCheckListener(getEt_eventoNombre(), getTilNombre());
                    addTextCheckListener(getEt_eventoLugar(), getTilLugar());
                    addTextViewCheckListener(getTv_eventoFecha(), getTilFecha());
                    addTextViewCheckListener(getTv_eventoHora(), getTilHora());


                    if (nombre && lugar && calle && localidad && numero && cp && hora && fecha) {
                        if (!(ut.compruebaSpinner(getActivity(), asp_consultar))) {

                            if (Build.VERSION.SDK_INT >= 23) {

                                crearEvento();
                            }


                        }
                    } else
                        Toast.makeText(getActivity(), getString(R.string.compruebeCampos), Toast.LENGTH_SHORT).show();

                } else if (modo.equalsIgnoreCase(getString(R.string.consultar))) {


                    if (!(ut.estaVacioEditText(aet_consultar))) {
                        if (!(ut.compruebaSpinner(getActivity(), asp_consultar))) {
                            if (getEt_eventoLocalidad().getText().toString().isEmpty()) {
                                getTilLocalidad().setError(getString(R.string.sCampoObligatorio));

                            } else
                                consultarEvento();


                        }
                    } else
                        Toast.makeText(getActivity(), getString(R.string.compruebeCampos), Toast.LENGTH_SHORT).show();

                } else
                    Log.e(getClass().getName(), getString(R.string.errorModoVacio));
            }
        });


        // addTextCheckListener(getEt_eventoLocalidad(), getTilLocalidad());
        //addTextCheckListener(getEt_eventoArtista(),getTilArtista());


        return view;

    }

    /**
     *
     */

    private void requestPermission() {


        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                //Show Information about why you need the permission


                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_CONSTANT);

            } else if (permissionStatus.getBoolean(Manifest.permission.WRITE_EXTERNAL_STORAGE, false)) {
                //Previously Permission Request was cancelled with 'Dont Ask Again',
                // Redirect to Settings after showing Information about why you need the permission
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
                builder.setTitle(getString(R.string.permission_storage));
                builder.setMessage(getString(R.string.almacen));
                builder.setPositiveButton(getString(R.string.sSi), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts(getString(R.string.paquete), getActivity().getPackageName(), null);
                        intent.setData(uri);
                        startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
                        Toast.makeText(getActivity(), getString(R.string.menuPermisos), Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton(getString(R.string.sCancelar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            } else {
                //just request the permission
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_CONSTANT);


            }

            SharedPreferences.Editor editor = permissionStatus.edit();
            editor.putBoolean(Manifest.permission.WRITE_EXTERNAL_STORAGE, true);
            editor.apply();


        } else {
            //You already have the permission, just go ahead.
            insertImage();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        Log.e(getClass().getName(), "request " + requestCode);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case EXTERNAL_STORAGE_PERMISSION_CONSTANT: {
                if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(),
                            getResources().getString(R.string.permission_storage_success),
                            Toast.LENGTH_SHORT).show();

                    selectImage();


                } else {


                    if (idSesion == 1) {

                        Log.i(getClass().getName(), "Inicializa Menú de administrador");

                        Intent i = new Intent(getActivity().getApplicationContext(), TabAdmin.class);

                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                        getActivity().getApplicationContext().startActivity(i);


                    } else {
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentManager.popBackStack();
                        Intent i = new Intent(getActivity(), MenuPrincipal.class);
                        startActivity(i);
                    }

                }

            }
        }
    }

    private void selectImage() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.DATOS), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        editor.putInt(getResources().getString(R.string.sIdEvento), eventoPOJO.getI_idEvento());
        editor.putInt(getResources().getString(R.string.sIdUsuario), eventoPOJO.getI_idUsuario());
        editor.putBoolean(getResources().getString(R.string.sFotoNueva), false);


        editor.putInt(getString(R.string.inserta), 1);
        editor.apply();

        RealizarFotoFragment realizarFotoFragment = new RealizarFotoFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentManager.popBackStack();
        fragmentTransaction.add(realizarFotoFragment, getString(R.string.fragment_evento_crear));
        fragmentTransaction.addToBackStack(getString(R.string.fragment_evento_crear));
        fragmentTransaction.commit();
    }

    /**
     * A partir de la provincia, devuelve las localidades de ésta.
     *
     * @param s
     */
    private void selectLocalidad(final int s) {

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.sCargando));


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
                                        getEt_eventoLocalidad().setAdapter(adapter);


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
     * @param textView
     * @param textInputLayout
     */
    private void addTextViewCheckListener(CustomFontTextView textView, final TextInputLayout textInputLayout) {
        textView.addTextChangedListener(new TextWatcher() {
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
     * public void updateDisplay()
     * <p/>
     * Se actualiza el com.gooutapp.custom.CustomFontTextView correspondiente al DatePickerDialog.onDateSetListener con los valores del dpEventoFecha
     */
    private void updateDisplay() {
        // TODO Auto-generated method stub
        // A i_mes se le suma uno porque comienza en 0
        Log.i(getClass().getName(), "updateDispaly");
        getTv_eventoFecha().setText(new StringBuilder().append(eventoPOJO.getI_ano()).append("-").append(eventoPOJO.getI_mes() + 1).append("-").append(eventoPOJO.getI_dia()));
    }


    /**
     * @return
     */
    private Map<String, String> formatoConsulta() {

        Map<String, String> params = new HashMap<>();

        try {


            params.put(getString(R.string.nombre), this.getEt_eventoNombre().getText().toString());
            params.put(getString(R.string.lugar), this.getEt_eventoLugar().getText().toString());

            if (getSp_eventoCategoria().getSelectedItem().toString().equalsIgnoreCase(getString(R.string.cualquiera))) {

                params.put(getString(R.string.categoria), getString(R.string.cualquiera));
            } else {
                params.put(getString(R.string.categoria),
                        this.getSp_eventoCategoria().getSelectedItem().toString().equalsIgnoreCase(getString(R.string.sElijaCategoria))
                                ? getString(R.string.svacio)
                                : String.valueOf(getSp_eventoCategoria().getSelectedItemPosition()));
            }
            String clasificacion = this.getSp_eventoClasificacion().getSelectedItem().toString();

            if (clasificacion.contains(getString(R.string.sElija))) {

                params.put(getString(R.string.clasificacion), getString(R.string.svacio));
            } else {

                params.put(getString(R.string.clasificacion), String.valueOf(getSp_eventoClasificacion().getSelectedItemPosition()));
            }

            params.put(getString(R.string.ciudad), this.getSp_eventoCiudad().getSelectedItem().toString()
                    .equalsIgnoreCase(getString(R.string.sElijaCiudad))
                    ? getString(R.string.svacio)
                    : this.getSp_eventoCiudad().getSelectedItem().toString());

            params.put(getString(R.string.sidCiudad), String.valueOf(this.getSp_eventoCiudad().getSelectedItemPosition()));

            params.put(getString(R.string.tipo), this.getSp_eventoTipo().getSelectedItem().toString()
                    .equalsIgnoreCase(getString(R.string.sElijaSala))
                    ? getString(R.string.svacio)
                    : String.valueOf(getSp_eventoTipo().getSelectedItemPosition()));

            params.put(getString(R.string.localidad), this.getEt_eventoLocalidad().getText().toString());

            params.put(getString(R.string.fecha), this.getTv_eventoFecha().getText().toString());
            params.put(getString(R.string.hora), this.getTv_eventoHora().getText().toString());
            params.put(getString(R.string.artista), this.getEt_eventoArtista().getText().toString());
            params.put(getString(R.string.precio), this.getEt_eventoPrecio().getText().toString());
            params.put(getString(R.string.sIdUsuario), String.valueOf(idSesion));
            params.put(getString(R.string.sIdEvento), String.valueOf(EventoFragment.eventoPOJO.getI_idEvento()));

            Log.e(getClass().getName(), String.valueOf(getSp_eventoCiudad().getSelectedItemPosition()));
            Log.e(getClass().getName(), "formatoConsulta\n" + params.toString());
        } catch (ExceptionInInitializerError e) {
            Log.e(getClass().getName(), "formatoConsulta");
            e.printStackTrace();
        }


        return params;
    }

    /**
     * @return
     */
    private Map<String, String> formatoCrear() {

        Map<String, String> params = new HashMap<>();

        try {

            params.put(getString(R.string.nombre), this.getEt_eventoNombre().getText().toString());
            params.put(getString(R.string.lugar), this.getEt_eventoLugar().getText().toString());

            params.put(getString(R.string.categoria), this.getSp_eventoCategoria().getSelectedItem().toString()
                    .equalsIgnoreCase(getString(R.string.sElijaCategoria))
                    ? getString(R.string.svacio)
                    : String.valueOf(getSp_eventoCategoria().getSelectedItemPosition()));

            String clasificacion = this.getSp_eventoClasificacion().getSelectedItem().toString();

            if (clasificacion.contains(getString(R.string.sElija))) {

                params.put(getString(R.string.clasificacion), getString(R.string.svacio));
            } else {

                params.put(getString(R.string.clasificacion), String.valueOf(getSp_eventoClasificacion().getSelectedItemPosition()));
            }

            params.put(getString(R.string.ciudad), this.getSp_eventoCiudad().getSelectedItem().toString()
                    .equalsIgnoreCase(getString(R.string.sElijaCiudad))
                    ? getString(R.string.svacio)
                    : this.getSp_eventoCiudad().getSelectedItem().toString());

            params.put(getString(R.string.sidCiudad), String.valueOf(this.getSp_eventoCiudad().getSelectedItemPosition()));

            params.put(getString(R.string.tipo), this.getSp_eventoTipo().getSelectedItem().toString()
                    .equalsIgnoreCase(getString(R.string.sElijaSala))
                    ? getString(R.string.svacio)
                    : String.valueOf(getSp_eventoTipo().getSelectedItemPosition()));

            params.put(getString(R.string.via), this.getSp_eventoVia().getSelectedItem().toString().equalsIgnoreCase(getString(R.string.sElijaVia))
                    ? getString(R.string.svacio)
                    : String.valueOf(getSp_eventoVia().getSelectedItemPosition()));

            params.put(getString(R.string.localidad), this.getEt_eventoLocalidad().getText().toString());
            params.put(getString(R.string.calle), getEt_eventoCalle().getText().toString());
            params.put(getString(R.string.snumero), getEt_eventoNumero().getText().toString());
            params.put(getString(R.string.cp), getEt_eventoCp().getText().toString());

            params.put(getString(R.string.fecha), this.getTv_eventoFecha().getText().toString());
            params.put(getString(R.string.hora), this.getTv_eventoHora().getText().toString());
            params.put(getString(R.string.artista), this.getEt_eventoArtista().getText().toString());

            if (getEt_eventoPrecio().getText().toString().equalsIgnoreCase(""))
                params.put(getString(R.string.precio), String.valueOf(-1));

            else
                params.put(getString(R.string.precio), this.getEt_eventoPrecio().getText().toString());

            params.put(getString(R.string.s_IdUsuario), String.valueOf(eventoPOJO.getI_idUsuario()));
            params.put(getString(R.string.sIdEvento), String.valueOf(eventoPOJO.getI_idEvento()));
            params.put(getString(R.string.descripcion), getEt_eventoDescripcion().getText().toString());

            Log.e(getClass().getName(), String.valueOf(getSp_eventoCiudad().getSelectedItemPosition()));
            Log.e(getClass().getName(), "formatoCrear\n" + params.toString());
        } catch (ExceptionInInitializerError e) {
            Log.e(getClass().getName(), "formatoCrear");
            e.printStackTrace();
        }


        return params;
    }


    /**
     */
    private void consultarEvento() {

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.sCargando));
        progressDialog.show();

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_selectEventos),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        JSONArray jsonArray = new JSONArray();

                        Log.e(getClass().getName(), "consultarEvento " + response);

                        progressDialog.dismiss();
                        try {
                            JSONObject json = new JSONObject(response);

                            int success;
                            success = json.getInt(getString(R.string.success));
                            jsonArray = json.getJSONArray(getString(R.string.sResultado));
                            Log.e(getClass().getName() + "1-->", String.valueOf(jsonArray.toString()));

                            if (success == 1) {


                                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.DATOS), Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                Log.e(getClass().getName() + "2-->", String.valueOf(jsonArray.get(0)));
                                editor.putInt(getActivity().getString(R.string.sIdUsuario), eventoPOJO.getI_idUsuario());
                                editor.putString(getString(R.string.resultado), String.valueOf(jsonArray.toString()));

                                editor.putInt(getString(R.string.seleccion), 2);

                                editor.putInt(getString(R.string.sIdSesion), idSesion);
                                editor.putString(getString(R.string.fromFragment), getString(R.string.fragment_tab_menu_evento));
                                editor.apply();


                                Intent i = new Intent(getActivity(), EventoListado.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(i);


                            } else {
                                Toast.makeText(getActivity(), getString(R.string.sNoEventos), Toast.LENGTH_LONG).show();
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
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), getString(R.string.errorConexion), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return formatoConsulta();
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);


    }


    /**
     */
    private void crearEvento() {


        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.sCargando));
        progressDialog.show();


        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_insertEvento),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Utilidades utilidades = new Utilidades();

                        Log.e(getClass().getName(), "crearEvento" + response);
                        progressDialog.dismiss();

                        try {

                            JSONObject json = new JSONObject(response);


                            int success;
                            success = json.getInt(getString(R.string.success));


                            if (success == 1) {


                                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.DATOS), Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putInt(getActivity().getString(R.string.sIdUsuario), eventoPOJO.getI_idUsuario());

                                editor.putInt(getString(R.string.seleccion), 2);
                                editor.putInt(getString(R.string.sIdSesion), idSesion);
                                editor.putInt(getString(R.string.tab), 0);


                                editor.apply();

                                insertImage();


                            } else if (success == 10) {
                                Toast.makeText(getActivity(), getString(R.string.eventoExiste), Toast.LENGTH_LONG).show();
                            } else if (success == 8) {
                                Toast.makeText(getActivity(), getString(R.string.ExisteArtista), Toast.LENGTH_LONG).show();
                            } else if (success == 9) {
                                Toast.makeText(getActivity(), getString(R.string.LugarExiste), Toast.LENGTH_LONG).show();
                            } else if (success == 12) {

                                String provincia = json.getString(getString(R.string.noProvincia));
                                String localidad = json.getString(getString(R.string.noLocalidad));
                                int cp = json.getInt(getString(R.string.noCp));
                                HashMap<String, String> arrayList = new HashMap<>();
                                arrayList.put(getString(R.string.sProvincia), provincia);
                                arrayList.put(getString(R.string.localidad), localidad);
                                arrayList.put(getString(R.string.cp), String.valueOf(cp));


                                utilidades.mensajeAlertDialog(getActivity(), arrayList);


                            } else if (success == 13) {
                                String localidad = json.getString(getString(R.string.noLocalidad));
                                String tipoVia = json.getString(getString(R.string.noTipoVia));
                                String calle = json.getString(getString(R.string.noCalle));
                                int cp = json.getInt(getString(R.string.noNumero));

                                HashMap<String, String> arrayList = new HashMap<>();
                                arrayList.put(getString(R.string.localidad), localidad);
                                arrayList.put(getString(R.string.sidTipoVia), tipoVia);
                                arrayList.put(getString(R.string.calle), calle);
                                arrayList.put(getString(R.string.cp), String.valueOf(cp));
                                utilidades.mensajeAlertDialog(getActivity(), arrayList);

                            } else {
                                Toast.makeText(getActivity(), getString(R.string.NoRegistraEvento), Toast.LENGTH_LONG).show();
                            }
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
                        Toast.makeText(getActivity(), getString(R.string.errorConexion), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return formatoCrear();
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }

    private void insertImage() {
        Utilidades utilidades = new Utilidades();

        if (utilidades.isExternalStorageAvailable()) {

            if (Build.VERSION.SDK_INT >= 23) {

                requestPermission();
            } else {

                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setTitle(getResources().getString(R.string.sInsertaFoto));

                alert.setPositiveButton(getResources().getString(R.string.sSi), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        selectImage();

                    }

                });
                alert.setNegativeButton(getResources().getString(R.string.sNO), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (idSesion == 1) {

                            Log.i(getClass().getName(), "Inicializa Menú de administrador");

                            Intent i = new Intent(getActivity().getApplicationContext(), TabAdmin.class);

                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                            getActivity().getApplicationContext().startActivity(i);


                        } else {
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            fragmentManager.popBackStack();
                            Intent i = new Intent(getActivity(), MenuPrincipal.class);
                            startActivity(i);
                        }

                    }
                });

                AlertDialog dialog = alert.create();
                dialog.show();

            }

        } else {
            Toast.makeText(getActivity(), getString(R.string.noRealizaFoto), Toast.LENGTH_LONG).show();
        }
    }


    private ImageButton getB_eventoConsulta() {
        return b_eventoConsulta;
    }

    private void setB_eventoConsulta(ImageButton b_eventoConsulta) {
        this.b_eventoConsulta = b_eventoConsulta;
    }

    private ImageButton getB_eventoFecha() {
        return b_eventoFecha;
    }

    private void setB_eventoFecha(ImageButton b_eventoFecha) {
        this.b_eventoFecha = b_eventoFecha;
    }

    private ImageButton getB_eventoHora() {
        return b_eventoHora;
    }

    private void setB_eventoHora(ImageButton b_eventoHora) {
        this.b_eventoHora = b_eventoHora;
    }

    private CustomFontEditText getEt_eventoArtista() {
        return et_eventoArtista;
    }

    private void setEt_eventoArtista(CustomFontEditText et_eventoArtista) {
        this.et_eventoArtista = et_eventoArtista;
    }

    private CustomFontEditText getEt_eventoCalle() {
        return et_eventoCalle;
    }

    private void setEt_eventoCalle(CustomFontEditText et_eventoCalle) {
        this.et_eventoCalle = et_eventoCalle;
    }

    private CustomFontEditText getEt_eventoCp() {
        return et_eventoCp;
    }

    private void setEt_eventoCp(CustomFontEditText et_eventoCp) {
        this.et_eventoCp = et_eventoCp;
    }

    private CustomFontEditText getEt_eventoDescripcion() {
        return et_eventoDescripcion;
    }

    private void setEt_eventoDescripcion(CustomFontEditText et_eventoDescripcion) {
        this.et_eventoDescripcion = et_eventoDescripcion;
    }

    private AutoCompleteTextView getEt_eventoLocalidad() {
        return et_eventoLocalidad;
    }

    private void setEt_eventoLocalidad(AutoCompleteTextView et_eventoLocalidad) {
        this.et_eventoLocalidad = et_eventoLocalidad;
    }

    private CustomFontEditText getEt_eventoLugar() {
        return et_eventoLugar;
    }

    private void setEt_eventoLugar(CustomFontEditText et_eventoLugar) {
        this.et_eventoLugar = et_eventoLugar;
    }

    private CustomFontEditText getEt_eventoNombre() {
        return et_eventoNombre;
    }

    private void setEt_eventoNombre(CustomFontEditText et_eventoNombre) {
        this.et_eventoNombre = et_eventoNombre;
    }

    private CustomFontEditText getEt_eventoNumero() {
        return et_eventoNumero;
    }

    private void setEt_eventoNumero(CustomFontEditText et_eventoNumero) {
        this.et_eventoNumero = et_eventoNumero;
    }

    private CustomFontEditText getEt_eventoPrecio() {
        return et_eventoPrecio;
    }

    private void setEt_eventoPrecio(CustomFontEditText et_eventoPrecio) {
        this.et_eventoPrecio = et_eventoPrecio;
    }


    private Spinner getSp_eventoCategoria() {
        return sp_eventoCategoria;
    }

    private void setSp_eventoCategoria(Spinner sp_eventoCategoria) {
        this.sp_eventoCategoria = sp_eventoCategoria;
    }

    private Spinner getSp_eventoCiudad() {
        return sp_eventoCiudad;
    }

    private void setSp_eventoCiudad(Spinner sp_eventoCiudad) {
        this.sp_eventoCiudad = sp_eventoCiudad;
    }

    private Spinner getSp_eventoClasificacion() {
        return sp_eventoClasificacion;
    }

    private void setSp_eventoClasificacion(Spinner sp_eventoClasificacion) {
        this.sp_eventoClasificacion = sp_eventoClasificacion;
    }

    private Spinner getSp_eventoTipo() {
        return sp_eventoTipo;
    }

    private void setSp_eventoTipo(Spinner sp_eventoTipo) {
        this.sp_eventoTipo = sp_eventoTipo;
    }

    private Spinner getSp_eventoVia() {
        return sp_eventoVia;
    }

    private void setSp_eventoVia(Spinner sp_eventoVia) {
        this.sp_eventoVia = sp_eventoVia;
    }

    private CustomFontTextView getTv_eventoFecha() {
        return tv_eventoFecha;
    }

    private void setTv_eventoFecha(CustomFontTextView tv_eventoFecha) {
        this.tv_eventoFecha = tv_eventoFecha;
    }

    private CustomFontTextView getTv_eventoHora() {
        return tv_eventoHora;
    }

    private void setTv_eventoHora(CustomFontTextView tv_eventoHora) {
        this.tv_eventoHora = tv_eventoHora;
    }

    private TextInputLayout getTilNombre() {
        return tilNombre;
    }

    private void setTilNombre(TextInputLayout tilNombre) {
        this.tilNombre = tilNombre;
    }

    private TextInputLayout getTilLugar() {
        return tilLugar;
    }

    private void setTilLugar(TextInputLayout tilLugar) {
        this.tilLugar = tilLugar;
    }

    private TextInputLayout getTilCalle() {
        return tilCalle;
    }

    private void setTilCalle(TextInputLayout tilCalle) {
        this.tilCalle = tilCalle;
    }

    private TextInputLayout getTilLocalidad() {
        return tilLocalidad;
    }

    private void setTilLocalidad(TextInputLayout tilLocalidad) {
        this.tilLocalidad = tilLocalidad;
    }

    private TextInputLayout getTilNumero() {
        return tilNumero;
    }

    private void setTilNumero(TextInputLayout tilNumero) {
        this.tilNumero = tilNumero;
    }

    private TextInputLayout getTilCp() {
        return tilCp;
    }

    private void setTilCp(TextInputLayout tilCp) {
        this.tilCp = tilCp;
    }

    private void setTilArtista(TextInputLayout tilArtista) {
        this.tilArtista = tilArtista;
    }

    private void setTilPrecio(TextInputLayout tilPrecio) {
        this.tilPrecio = tilPrecio;
    }

    private TextInputLayout getTilDescripcion() {
        return tilDescripcion;
    }

    private void setTilDescripcion(TextInputLayout tilDescripcion) {
        this.tilDescripcion = tilDescripcion;
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

    private TextInputLayout getTilHora() {
        return tilHora;
    }

    private void setTilHora(TextInputLayout tilHora) {
        this.tilHora = tilHora;
    }

    private TextInputLayout getTilFecha() {
        return tilFecha;
    }

    private void setTilFecha(TextInputLayout tilFecha) {
        this.tilFecha = tilFecha;
    }
}
