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
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
import com.findandgoapp.activity.EventoSeleccionado;
import com.findandgoapp.activity.R;
import com.findandgoapp.activity.RealizarFotoActivity;
import com.findandgoapp.adapter.MyArrayAdapter;
import com.findandgoapp.custom.CustomFontEditText;
import com.findandgoapp.custom.CustomFontTextView;
import com.findandgoapp.custom.CustomTypeFaceSpan;
import com.findandgoapp.pojo.EventoPOJO;
import com.findandgoapp.pojo.SugerenciaPOJO;
import com.findandgoapp.tools.Utilidades;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventoUpdateFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EventoUpdateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventoUpdateFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int REQUEST_PERMISSION_SETTING = 101;
    private static final int EXTERNAL_STORAGE_PERMISSION_CONSTANT = 100;
    private SharedPreferences permissionStatus;
    /**
     * Interfaz
     */
    private CustomFontEditText et_nombre;
    private CustomFontEditText et_categoria;
    private CustomFontEditText et_clasificacion;
    private CustomFontEditText et_tipo;
    private CustomFontEditText et_lugar;
    private CustomFontEditText et_ciudad;
    private AutoCompleteTextView et_localidad;
    private CustomFontEditText et_cp;
    private CustomFontEditText et_numero;
    private CustomFontEditText et_calle;
    private CustomFontTextView tv_fecha;
    private CustomFontTextView tv_hora;
    private CustomFontEditText et_artista;
    private CustomFontEditText et_precio;
    private CustomFontEditText et_descripcion;


    private Spinner sp_categoria;
    private Spinner sp_clasificacion;
    private Spinner sp_tipo;
    private Spinner sp_ciudad;
    private Spinner sp_via;
    private ImageButton ib_eventoConsulta;
    private ImageButton ib_fecha;
    private ImageButton ib_hora;
    private ImageView iv_foto;


    private EventoPOJO evento;
    private OnFragmentInteractionListener mListener;

    private int iHora;
    private int iMinutos;
    private int iAno;
    private int iMes;
    private int iDia;

    private boolean isImage;
    private int idSesion;
    private ProgressDialog progressDialog;
    private SugerenciaPOJO sugerenciaPOJO;
    /**
     * variable timePickerDialog para inicializar el textview ocn la hora seleccionada
     */

    private TimePickerDialog.OnTimeSetListener tpdEventoHora = new TimePickerDialog.OnTimeSetListener() {

        public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
            iHora = selectedHour;
            iMinutos = selectedMinute;

            // set current time into textview
            tv_hora.setText(new StringBuilder().append(pad(iHora))
                    .append(":").append(pad(iMinutos)));
        }
    };
    /**
     * Creamos un DatePickerDialog.OnDateSetListener que usaremos para dar formato al calendario
     * <p/>
     * Métodos públicos
     * abstract void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
     */
    private DatePickerDialog.OnDateSetListener dpEventoFecha = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int ano, int mes, int dia) {


            iAno = ano;
            iMes = mes;
            iDia = dia;

            getTv_fecha().setText(new StringBuilder()
                    .append(iAno).append("-")
                    .append(iMes + 1).append("-")
                    .append(iDia));

            updateDisplay();

        }
    };


    public EventoUpdateFragment() {
        // Required empty public constructor
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    /**
     * public void updateDisplay()
     * <p/>
     * Se actualiza el com.gooutapp.custom.CustomFontTextView correspondiente al DatePickerDialog.onDateSetListener con los valores del dpEventoFecha
     */
    private void updateDisplay() {
        // TODO Auto-generated method stub
        // A iMes se le suma uno porque comienza en 0

        this.getTv_fecha().setText(new StringBuilder().append(this.getiAno()).append("-").append(this.getiMes() + 1).append("-").append(this.getiDia()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        evento = new EventoPOJO();
        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(getString(R.string.sDATOS), Context.MODE_PRIVATE);

        sugerenciaPOJO = new SugerenciaPOJO();
        evento.setI_idEvento(sharedPreferences.getInt(getString(R.string.sIdEvento), 0));
        evento.setI_idUsuario(sharedPreferences.getInt(getString(R.string.sIdUsuario), 0));
        idSesion = sharedPreferences.getInt(getString(R.string.sIdSesion), 0);
        permissionStatus = getActivity().getSharedPreferences(getString(R.string.permissionStatus), MODE_PRIVATE);
        View view = inflater.inflate(R.layout.fragment_evento_update, container, false);

        Log.e(getClass().getName(), "onCreat");


        //Variables para el calendario
        final Calendar CALENDAR = Calendar.getInstance();

        evento.setI_ano(CALENDAR.get(Calendar.YEAR));
        evento.setI_mes(CALENDAR.get(Calendar.MONTH));
        evento.setI_dia(CALENDAR.get(Calendar.DAY_OF_MONTH));

      /*
        setIv_foto((ImageView) view.findViewById(R.id.toolbarImage));
         */
        setEt_nombre((CustomFontEditText) view.findViewById(R.id.idEtEventoUpdateNombre));
        setEt_lugar((CustomFontEditText) view.findViewById(R.id.idEtEventoUpdateLugar));

        setSp_categoria((Spinner) view.findViewById(R.id.idSpEventoUpdateCategoria));
        setSp_clasificacion((Spinner) view.findViewById(R.id.idSpEventoUpdateClasificacion));
        setSp_tipo((Spinner) view.findViewById(R.id.idSpEventoUpdateTipo));
        setSp_ciudad((Spinner) view.findViewById(R.id.idSpEventoUpdateCiudad));
        setEt_localidad((AutoCompleteTextView) view.findViewById(R.id.idEtEventoUpdateLocalidad));
        setSp_via((Spinner) view.findViewById(R.id.idSpEventoUpdateVia));
        setEt_calle((CustomFontEditText) view.findViewById(R.id.idEtEventoUpdateCalle));
        setEt_numero((CustomFontEditText) view.findViewById(R.id.idEtEventoUpdateNumero));
        setEt_cp((CustomFontEditText) view.findViewById(R.id.idEtEventoUpdateCP));
        setTv_fecha((CustomFontTextView) view.findViewById(R.id.idTvEventoUpdateFecha));
        setTv_hora((CustomFontTextView) view.findViewById(R.id.idTvEventoUpdateHora));
        setEt_artista((CustomFontEditText) view.findViewById(R.id.idEtEventoUpdateArtista));
        setEt_precio((CustomFontEditText) view.findViewById(R.id.idEtEventoUpdatePrecio));
        setEt_descripcion((CustomFontEditText) view.findViewById(R.id.idEtEventoUpdateDescripcion));
        //Botones Fecha, hora y registrar
        setIb_eventoConsulta((ImageButton) view.findViewById(R.id.idBEventoUpdateRegistrar));
        setIb_fecha((ImageButton) view.findViewById(R.id.idBEventoUpdateFecha));
        setIb_hora((ImageButton) view.findViewById(R.id.idBEventoUpdateHora));


       /* ImageDownloadTask imageDownloadTask = new ImageDownloadTask(getActivity().getResources().getString(R.string.sRutaImagenes) +
                String.valueOf(evento.getI_idEvento()) + getActivity().getResources().getString(R.string.sFormatoEvento)
                , getIv_foto());
        imageDownloadTask.execute();*/

        //Creamos adaptadores para los spinners
        final MyArrayAdapter adapterCategoria = new MyArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, getActivity().getResources().getStringArray(R.array.categoria));
        MyArrayAdapter adapterClasificacion = new MyArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, getActivity().getResources().getStringArray(R.array.clasificacion));
        // Aplicamos el adaptador al spinner
        getSp_categoria().setAdapter(adapterCategoria);
        getSp_clasificacion().setAdapter(adapterClasificacion);


        Utilidades.rellenarSpinner(getActivity(), getSp_categoria(), getSp_clasificacion());

        MyArrayAdapter adapterTipo = new MyArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, getActivity().getResources().getStringArray(R.array.tipo));
        getSp_tipo().setAdapter(adapterTipo);

        MyArrayAdapter adapterCiudad = new MyArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, getActivity().getResources().getStringArray(R.array.ciudades));
        getSp_ciudad().setAdapter(adapterCiudad);


        MyArrayAdapter arrAdapterVia = new MyArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, getActivity().getResources().getStringArray(R.array.tipoVia));
        arrAdapterVia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getSp_via().setAdapter(arrAdapterVia);


        //Añadir evento OnItemSelectedListener a los Spinners

        getSp_categoria().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                if (getSp_categoria().getSelectedItem().toString().equalsIgnoreCase(getResources().getString(R.string.sOtro))) {
                    sugerenciaPOJO.setIdUsuario(idSesion);
                    sugerenciaPOJO.setTipo(Integer.parseInt(getString(R.string.sugerenciaCategoria)));
                    sugerenciaPOJO.setIdSugerencia(getSp_categoria().getSelectedItemPosition());
                    sugerenciaPOJO.setTexto(getActivity().getResources().getString(R.string.sDeseaAddCategoria));
                    sugerenciaPOJO.addSugerencia(getActivity());
                }

                adapterCategoria.notifyDataSetChanged();
                Utilidades.rellenarSpinner(getActivity(), getSp_categoria(), getSp_clasificacion());


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

                Log.i(getClass().getName(), "onNothingSelected " + getSp_clasificacion().getSelectedItem().toString() + "\n");
            }

        });


        getSp_clasificacion().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                if (getSp_clasificacion().getSelectedItem().toString().equalsIgnoreCase(getResources().getString(R.string.sSugerirClasificacion))) {
                    sugerenciaPOJO.setIdUsuario(idSesion);
                    sugerenciaPOJO.setTipo(Integer.parseInt(getString(R.string.sugerenciaClasificacion)));
                    sugerenciaPOJO.setIdSugerencia(getSp_clasificacion().getSelectedItemPosition());
                    sugerenciaPOJO.setTexto(getActivity().getResources().getString(R.string.sDeseaAddClasificacion));
                    sugerenciaPOJO.addSugerencia(getActivity());
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });

        getSp_tipo().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                if (getSp_tipo().getSelectedItem().toString().equalsIgnoreCase(getResources().getString(R.string.sOtro))) {
                    sugerenciaPOJO.setIdUsuario(idSesion);
                    sugerenciaPOJO.setTipo(Integer.parseInt(getString(R.string.sugerenciaTipo)));
                    sugerenciaPOJO.setIdSugerencia(getSp_tipo().getSelectedItemPosition());
                    sugerenciaPOJO.setTexto(getActivity().getResources().getString(R.string.sDeseaAddTipo));
                    sugerenciaPOJO.addSugerencia(getActivity());
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });

        getSp_via().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                if (getSp_via().getSelectedItem().toString().equalsIgnoreCase(getResources().getString(R.string.sOtro))) {

                    sugerenciaPOJO.setIdUsuario(idSesion);
                    sugerenciaPOJO.setTipo(Integer.parseInt(getString(R.string.sugerenciaVia)));
                    sugerenciaPOJO.setIdSugerencia(getSp_via().getSelectedItemPosition());
                    sugerenciaPOJO.setTexto(getActivity().getResources().getString(R.string.sDeseaAddVia));
                    sugerenciaPOJO.addSugerencia(getActivity());

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });


        //Se añade el Date Picker Dialog al botón que representa la fecha

        getIb_fecha().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                DatePickerDialog DPD = new DatePickerDialog(getActivity(), getDpEventoFecha(), evento.getI_ano(), evento.getI_mes(), evento.getI_dia());
                DPD.getDatePicker().setMinDate(CALENDAR.getTimeInMillis());
                DPD.show();

            }

        });

        //Se añade el Time Picker Dialog al botón que representa la hora

        getIb_hora().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                TimePickerDialog TPD = new TimePickerDialog(getActivity(), getTpdEventoHora(), evento.getI_hora(), evento.getI_minutos(), true);
                TPD.show();
            }
        });

        //Al pulsar el botón de consulta se realiza la consulta a base de datos si al menos un campo está relleno
        getIb_eventoConsulta().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // consultamos el evento en un hilo en segundo plano
                //Obtenemos el idUsuario de la Actividad anterior


                updateEvento();
            }
        });


        getSp_ciudad().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                selectLocalidad(getSp_ciudad().getSelectedItemPosition());

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });

        selectEventoSeleccionado();


        Log.e(getClass().getName(), String.valueOf(isImage()));


        return view;
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
                                        getEt_localidad().setAdapter(adapter);


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
     *
     */
    private void selectEventoSeleccionado() {

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.sCargando));
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_selectAllEventoSeleccionado),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(getClass().getName(), "selectEventoSeleccionado " + response);

                        JSONObject jsonObject;

                        progressDialog.dismiss();
                        int success = 0;
                        try {

                            jsonObject = new JSONObject(response);

                            rellenarFichaEventoConsulta(jsonObject.getJSONObject("result"));


                        } catch (JSONException e1) {
                            e1.printStackTrace();
                            Log.e(getClass().getCanonicalName(), e1.getMessage());
                        }

                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        progressDialog.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //Adding parameters to request

                return evento.formatoIdEvento(getActivity());
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);


    }

    private void rellenarFichaEventoConsulta(JSONObject jsonObject) {

        try {
            this.getEt_nombre().setText(jsonObject.getString("nombreEvento"));


            this.getEt_lugar().setText(jsonObject.getString("lugarEvento"));

            this.getSp_categoria().setSelection(Integer.parseInt(jsonObject.getString("idCategoria")));

            int position = jsonObject.getInt("idClasificacion");

            this.getSp_clasificacion().setSelection(position);


            this.getSp_tipo().setSelection(Integer.parseInt(jsonObject.getString("idTipo")));

            this.getSp_ciudad().setSelection(Integer.parseInt(jsonObject.getString("idProvincia")));


            this.getEt_localidad().setText(jsonObject.getString("nombreLocalidad"));


            this.getSp_via().setSelection(Integer.parseInt(jsonObject.getString("idVia")));


            this.getEt_calle().setText(jsonObject.getString("direccionEvento"));


            this.getEt_numero().setText(jsonObject.getString("numero"));


            this.getEt_cp().setText(jsonObject.getString("cp"));


            this.getTv_fecha().setText(jsonObject.getString("fechaEvento"));

            this.getTv_hora().setText(jsonObject.getString("horaEvento"));


            this.getEt_artista().setText(jsonObject.getString("artistaEvento"));


            int precio = jsonObject.getInt("precioEvento");
            if (precio == -1) {
                this.getEt_precio().setText("");
            } else
                this.getEt_precio().setText(String.valueOf(jsonObject.getInt("precioEvento")));


            this.getEt_descripcion().setText(jsonObject.getString("descripcionEvento"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
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
        assert menu != null;
        for (int i = 0; i < menu.size(); i++) {
            MenuItem mi = menu.getItem(i);
            String title = mi.getTitle().toString();
            Spannable newTitle = new SpannableString(title);
            Typeface font = Typeface.createFromAsset(getActivity().getAssets(), getResources().getString(R.string.fontAmaticRegular));
            newTitle.setSpan(new CustomTypeFaceSpan("", font), 0, newTitle.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            mi.setTitle(newTitle);

        }
        Log.e(getClass().getName(), "onPrepareOptionsMenu\n");

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
        int id = item.getItemId();


        Log.e(getClass().getName(), "onOptionsItemSelected\n" + String.valueOf(evento.getI_idUsuario() + "--->" + id));
        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {


            case R.id.idMIFotoEvento:

                Utilidades utilidades = new Utilidades();

                if (utilidades.isExternalStorageAvailable()) {
                    if (Build.VERSION.SDK_INT >= 23) {
                        requestPermission();
                    } else
                        selectImage();
                } else {
                    Toast.makeText(getActivity(), getString(R.string.noRealizaFoto), Toast.LENGTH_SHORT).show();
                }

                //getActivity().finish();


        }

        return super.onOptionsItemSelected(item);
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
            selectImage();
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


                    Toast.makeText(getActivity(), getString(R.string.aceptarPermiso), Toast.LENGTH_SHORT).show();


                }

            }
        }
    }

    private void selectImage() {
        Intent i = new Intent(getActivity().getApplicationContext(), RealizarFotoActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        SharedPreferences share = getActivity().getSharedPreferences(getString(R.string.DATOS), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();

        editor.putInt(getActivity().getResources().getString(R.string.sIdEvento), evento.getI_idEvento());
        editor.putInt(getActivity().getResources().getString(R.string.sIdSesion), idSesion);
        editor.apply();

        getActivity().startActivity(i);
    }

    private CustomFontTextView getTv_fecha() {
        return tv_fecha;
    }

    private void setTv_fecha(CustomFontTextView tv_fecha) {
        this.tv_fecha = tv_fecha;
    }

    private CustomFontTextView getTv_hora() {
        return tv_hora;
    }

    private void setTv_hora(CustomFontTextView tv_hora) {
        this.tv_hora = tv_hora;
    }

    private ImageButton getIb_fecha() {
        return ib_fecha;
    }

    private void setIb_fecha(ImageButton ib_fecha) {
        this.ib_fecha = ib_fecha;
    }

    private ImageButton getIb_hora() {
        return ib_hora;
    }

    private void setIb_hora(ImageButton ib_hora) {
        this.ib_hora = ib_hora;
    }

    private DatePickerDialog.OnDateSetListener getDpEventoFecha() {
        return dpEventoFecha;
    }

    private TimePickerDialog.OnTimeSetListener getTpdEventoHora() {
        return tpdEventoHora;
    }

    private boolean isImage() {
        return isImage;
    }


    private void updateEvento() {

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.sCargando));
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_updateEvento),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(getClass().getName(), "updateEvento " + response);

                        JSONObject jsonObject;


                        progressDialog.dismiss();
                        int success;
                        try {

                            jsonObject = new JSONObject(response);

                            success = jsonObject.getInt("success");

                            if (success == 1) {
                                Intent i = new Intent(getActivity(), EventoSeleccionado.class);
                                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getResources().getString(R.string.DATOS), Context.MODE_PRIVATE);


                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putInt(getResources().getString(R.string.sIdSesion), idSesion);
                                editor.putInt(getResources().getString(R.string.sEvento), evento.getI_idEvento());
                                editor.putInt(getResources().getString(R.string.sIdUsuario), evento.getI_idUsuario());
                                editor.putBoolean(getResources().getString(R.string.sFotoNueva), true);
                                editor.putString(getString(R.string.fromFragment), getString(R.string.fragment_evento_update));
                                editor.apply();
                                //getActivity().finish();
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(i);
                            } else if (success == 12) {

                                Utilidades utilidades = new Utilidades();
                                String provincia = jsonObject.getString(getString(R.string.noProvincia));
                                String localidad = jsonObject.getString(getString(R.string.noLocalidad));
                                int cp = jsonObject.getInt(getString(R.string.noCp));
                                HashMap<String, String> arrayList = new HashMap<>();
                                arrayList.put(getString(R.string.sProvincia), provincia);
                                arrayList.put(getString(R.string.localidad), localidad);
                                arrayList.put(getString(R.string.cp), String.valueOf(cp));


                                utilidades.mensajeAlertDialog(getActivity(), arrayList);
                            } else

                            {
                                Utilidades utilidades = new Utilidades();
                                utilidades.errorConsultaBBDD(getActivity(), getString(R.string.EventoNoActualiza));
                            }


                        } catch (JSONException e1) {
                            e1.printStackTrace();
                            Log.e(getClass().getCanonicalName(), e1.getMessage());
                        }

                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        progressDialog.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //Adding parameters to request

                return toArray();
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }

    private Map<String, String> toArray() {
        Map<String, String> params = new HashMap<>();

        try {
            params.put("nombre", this.getEt_nombre().getText().toString());
            params.put("lugar", this.getEt_lugar().getText().toString());
            params.put("categoria", this.getSp_categoria().getSelectedItem().toString()
                    .equalsIgnoreCase(getString(R.string.sElijaCategoria))
                    ? "vacio"
                    : String.valueOf(getSp_categoria().getSelectedItemPosition()));

            String clasificacion = this.getSp_clasificacion().getSelectedItem().toString();

            if (clasificacion.contains(getString(R.string.sElija))) {

                params.put("clasificacion", "vacio");
            } else {


                params.put("clasificacion", String.valueOf(getSp_clasificacion().getSelectedItemPosition()));
            }

            params.put("ciudad", this.getSp_ciudad().getSelectedItem().toString()
                    .equalsIgnoreCase(getString(R.string.sElijaCiudad))
                    ? "vacio"
                    : String.valueOf(this.getSp_ciudad().getSelectedItemPosition()));
            params.put("tipo", this.getSp_tipo().getSelectedItem().toString()
                    .equalsIgnoreCase(getString(R.string.sElijaSala))
                    ? "vacio"
                    : String.valueOf(this.getSp_tipo().getSelectedItemPosition()));

            params.put("via", this.getSp_via().getSelectedItem().toString()
                    .equalsIgnoreCase("Via")
                    ? "vacio"
                    : String.valueOf(this.getSp_via().getSelectedItemPosition()));

            params.put("localidad", this.getEt_localidad().getText().toString());
            params.put("calle", this.getEt_calle().getText().toString());
            params.put("numero", this.getEt_numero().getText().toString());
            params.put("cp", this.getEt_cp().getText().toString());
            params.put("fecha", this.getTv_fecha().getText().toString());
            params.put("hora", this.getTv_hora().getText().toString());
            params.put("artista", this.getEt_artista().getText().toString());
            params.put("precio", this.getEt_precio().getText().toString());
            params.put("descripcion", this.getEt_descripcion().getText().toString());

            params.put("idUsuario", String.valueOf(evento.getI_idUsuario()));
            params.put("idEvento", String.valueOf(evento.getI_idEvento()));

            Log.i(getClass().getName(), "toArray\n" + params.toString());
        } catch (ExceptionInInitializerError e) {
            Log.e(getClass().getName(), "toArray");
            e.printStackTrace();
        }


        return params;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private CustomFontEditText getEt_artista() {
        return et_artista;
    }

    private void setEt_artista(CustomFontEditText et_artista) {
        this.et_artista = et_artista;
    }

    private CustomFontEditText getEt_descripcion() {
        return et_descripcion;
    }

    private void setEt_descripcion(CustomFontEditText et_descripcion) {
        this.et_descripcion = et_descripcion;
    }

    private CustomFontEditText getEt_calle() {
        return et_calle;
    }

    private void setEt_calle(CustomFontEditText et_calle) {
        this.et_calle = et_calle;
    }

    private CustomFontEditText getEt_lugar() {
        return et_lugar;
    }

    private void setEt_lugar(CustomFontEditText et_lugar) {
        this.et_lugar = et_lugar;
    }

    private CustomFontEditText getEt_nombre() {
        return et_nombre;
    }

    private void setEt_nombre(CustomFontEditText et_nombre) {
        this.et_nombre = et_nombre;
    }

    private CustomFontEditText getEt_precio() {
        return et_precio;
    }

    private void setEt_precio(CustomFontEditText et_precio) {
        this.et_precio = et_precio;
    }

    private ImageButton getIb_eventoConsulta() {
        return ib_eventoConsulta;
    }

    private void setIb_eventoConsulta(ImageButton ib_eventoConsulta) {
        this.ib_eventoConsulta = ib_eventoConsulta;
    }

    private Spinner getSp_via() {
        return sp_via;
    }

    private void setSp_via(Spinner sp_via) {
        this.sp_via = sp_via;
    }

    private Spinner getSp_categoria() {
        return sp_categoria;
    }

    private void setSp_categoria(Spinner sp_categoria) {
        this.sp_categoria = sp_categoria;
    }

    private Spinner getSp_ciudad() {
        return sp_ciudad;
    }

    private void setSp_ciudad(Spinner sp_ciudad) {
        this.sp_ciudad = sp_ciudad;
    }

    private Spinner getSp_clasificacion() {
        return sp_clasificacion;
    }

    private void setSp_clasificacion(Spinner sp_clasificacion) {
        this.sp_clasificacion = sp_clasificacion;
    }

    private Spinner getSp_tipo() {
        return sp_tipo;
    }

    private void setSp_tipo(Spinner sp_tipo) {
        this.sp_tipo = sp_tipo;
    }

    private CustomFontEditText getEt_cp() {
        return et_cp;
    }

    private void setEt_cp(CustomFontEditText et_cp) {
        this.et_cp = et_cp;
    }

    private AutoCompleteTextView getEt_localidad() {
        return et_localidad;
    }

    private void setEt_localidad(AutoCompleteTextView et_localidad) {
        this.et_localidad = et_localidad;
    }

    private CustomFontEditText getEt_numero() {
        return et_numero;
    }

    private void setEt_numero(CustomFontEditText et_numero) {
        this.et_numero = et_numero;
    }

    private int getiAno() {
        return iAno;
    }

    private int getiDia() {
        return iDia;
    }

    private int getiMes() {
        return iMes;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

    }

    /**
     *
     */
    public class ImageDownloadTask extends AsyncTask<String, Void, Boolean> {

        private final String fileURL;


        /**
         *
         */
        public ImageDownloadTask(final String imageURL, final ImageView imageView) {
            this.fileURL = imageURL;


        }

        @Override
        protected Boolean doInBackground(final String... args) {

            HttpURLConnection con = null;
            Boolean estado = false;


            try {
                HttpURLConnection.setFollowRedirects(false);

                con = (HttpURLConnection) new URL(fileURL).openConnection();
                con.setRequestMethod("HEAD");

                if ((con.getResponseCode() == HttpURLConnection.HTTP_OK)) {

                    estado = true;

                }

            } catch (Exception e) {
                e.printStackTrace();


                Log.d(getClass().getName(), "CATCH" + e.getMessage());

            } finally {
                assert con != null;
                assert con != null;
                con.disconnect();
            }


            return estado;
        }


        @Override
        protected void onPostExecute(final Boolean result) {
            Log.e(getClass().getName(), "FILE_EXISTS" + result);
            evento.setB_isImage(result);


        }

    }

}
