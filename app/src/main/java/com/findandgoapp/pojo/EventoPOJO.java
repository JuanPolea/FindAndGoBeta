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

import android.app.Application;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.ListView;

import com.findandgoapp.activity.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by juanpolea on 20/07/16.
 */
public class EventoPOJO extends Application implements Parcelable {


    private String[] items = {"Evento no existente",
            "Contiene datos erróneos",
            "Contenido ofensivo",
    };

    private int i_idEvento;
    private int i_idUsuario;
    private int i_ano;
    private int i_mes;
    private int i_dia;
    private int i_hora;

    private int i_minutos;
    private final DireccionPOJO direccionPOJO = new DireccionPOJO();
    private int i_numComenta;


    private String s_comentario;

    private String s_nombreUsuarioComenta;

    private final LinkedList<ComentarioPOJO> ll_comentario = new LinkedList<>();
    private final LinkedList<PenalizacionPOJO> ll_penalizacion = new LinkedList<>();


    private boolean b_isImage;


    /**
     * Tab Evento
     */

    private int i_seleccion;
    private ListView listView;


    private String s_nombreEvento;
    private String s_categoria;
    private String s_clasificacion;


    private String s_nombreSala;
    private String s_tipo;
    private String s_ciudad;
    private String s_localidad;
    private int i_categoria;
    private int i_cp;
    private String s_fecha;
    private int i_estado;
    private int i_numConfirma;
    private int i_numDenuncia;
    private String s_artista;
    private int i_precio;
    private String s_descripcion;
    private String s_creador;
    private String emailUsuarioSesion;
    private String s_hora;

    public Map<String, String> getLlNvp() {
        return llNvp;
    }

    private final Map<String, String> llNvp = new HashMap<>();

    public EventoPOJO(Context context) {
    }

    public EventoPOJO() {
    }

    /**
     * @param s_nombreEvento
     * @param s_nombreSala
     * @param s_categoria
     * @param s_clasificacion
     * @param s_tipo
     * @param s_fecha
     * @param i_hora
     * @param s_localidad
     * @param s_ciudad
     * @param s_artista
     * @param i_precio
     */
    public EventoPOJO(String s_nombreEvento, String s_nombreSala, String s_categoria, String s_clasificacion, String s_tipo, String s_fecha, int i_hora, String s_localidad, String s_ciudad, String s_artista, int i_precio, String s_descripcion) {


        this.s_nombreEvento = s_nombreEvento;
        this.s_nombreSala = s_nombreSala;
        this.s_categoria = s_categoria;
        this.s_clasificacion = s_clasificacion;
        this.s_tipo = s_tipo;
        this.s_fecha = s_fecha;
        this.i_hora = i_hora;
        this.s_localidad = s_localidad;
        this.s_ciudad = s_ciudad;
        this.s_artista = s_artista;
        this.i_precio = i_precio;
        this.s_descripcion = s_descripcion;
    }

    /**
     * @param in
     */
    private EventoPOJO(Parcel in) {
        items = in.createStringArray();

        s_comentario = in.readString();
        s_nombreUsuarioComenta = in.readString();
        i_idEvento = in.readInt();
        i_idUsuario = in.readInt();
        i_estado = in.readInt();
        b_isImage = in.readByte() != 0;

        s_nombreEvento = in.readString();
        s_categoria = in.readString();
        s_clasificacion = in.readString();
        s_tipo = in.readString();
        s_nombreSala = in.readString();
        s_ciudad = in.readString();
        s_localidad = in.readString();
        s_fecha = in.readString();
        s_hora = in.readString();


    }

    /**
     *
     */
    public static final Parcelable.Creator<EventoPOJO> CREATOR = new Parcelable.Creator<EventoPOJO>() {
        @Override
        public EventoPOJO createFromParcel(Parcel in) {
            return new EventoPOJO(in);
        }

        @Override
        public EventoPOJO[] newArray(int size) {
            return new EventoPOJO[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(items);
        dest.writeStringArray(items);
        dest.writeString(s_comentario);
        dest.writeString(s_nombreUsuarioComenta);
        dest.writeInt(i_idEvento);
        dest.writeInt(i_idUsuario);
        dest.writeInt(i_estado);
        dest.writeByte((byte) (b_isImage ? 1 : 0));

        dest.writeString(s_nombreEvento);
        dest.writeString(s_categoria);
        dest.writeString(s_clasificacion);
        dest.writeString(s_tipo);
        dest.writeString(s_nombreSala);
        dest.writeString(s_ciudad);
        dest.writeString(s_localidad);
        dest.writeString(s_fecha);
        dest.writeString(s_hora);

    }


    /**
     * @return
     */
    public Map<String, String> formatoIdEvento(Context c) {

        Map<String, String> params = new HashMap<>();

        params.put(c.getString(R.string.sIdEvento), String.valueOf(getI_idEvento()));
        Log.i(getClass().getName(), "formatoIdEvento");
        return params;
    }


    /**
     * @return
     */
    public Map<String, String> formatoIdEventoidUsuario(Context c) {

        Map<String, String> params = new HashMap<>();

        params.put(c.getString(R.string.sIdEvento), String.valueOf(getI_idEvento()));
        params.put(c.getString(R.string.sIdUsuario), String.valueOf(getI_idUsuario()));

        Log.i(getClass().getName(), "formatoIdEvento");
        return params;
    }

    /**
     * @param c
     * @param idUsuario
     * @return
     */
    public Map<String, String> formatoIdEventoByidUsuario(Context c, int idUsuario) {

        Map<String, String> params = new HashMap<>();

        params.put(c.getString(R.string.sIdEvento), String.valueOf(getI_idEvento()));
        params.put(c.getString(R.string.sIdUsuario), String.valueOf(idUsuario));

        Log.i(getClass().getName(), "formatoIdEvento");
        return params;
    }


    /**
     * @param jsonObject
     */
    public void JSONtoObject(Context c, JSONObject jsonObject) {

        String[] categoria = c.getResources().getStringArray(R.array.categoria);
        String[] clasificacion = c.getResources().getStringArray(R.array.clasificacion);
        Log.e(getClass().getName(), jsonObject.toString());
        try {
            this.setI_idEvento(jsonObject.getInt("idEvento"));
            this.setS_nombreEvento(jsonObject.getString("nombreEvento"));


            this.setS_clasificacion(clasificacion[jsonObject.getInt("idClasificacion")]);
            this.setS_categoria(categoria[jsonObject.getInt("idCategoria")]);
            this.setI_categoria(jsonObject.getInt("idCategoria"));


            String[] tipo = c.getResources().getStringArray(R.array.tipo);
            this.setS_tipo(tipo[jsonObject.getInt("idTipo")]);
            this.setS_ciudad(jsonObject.getString("idProvincia"));
            this.setS_localidad(jsonObject.getString("localidad"));
            this.setS_fecha(jsonObject.getString("fechaEvento"));
            this.setI_numConfirma(jsonObject.getInt("NumConfirmaciones"));
            this.setI_numDenuncia(jsonObject.getInt("NumDenuncias"));
            this.setI_numComenta(jsonObject.getInt("NumComentarios"));
            //Log.e(getClass().getName(), String.valueOf(jsonObject));
        } catch (JSONException e1) {
            // TODO Auto-generated catch block
            Log.e(getClass().getName(), e1.getMessage());
        }


    }

    /**
     * @param c
     * @param jArray
     * @return
     */
    public void JSONArraytoObject(Context c, JSONArray jArray) {


        for (int i = 0; i < jArray.length(); i++) {
            org.json.JSONObject row = null;

            try {
                row = jArray.getJSONObject(i);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            this.JSONtoObject(c, row);
        }
        //Log.e(getClass().getName(), "JSONArraytoObject-> " + jArray.toString());

    }


    public void inicializarDenunciados() {
        // TODO Auto-generated method stub

        getLlNvp().put("tipo1", "1");
        getLlNvp().put("tipo2", "2");
        getLlNvp().put("tipo3", "3");
        Log.i(getClass().getName(), "inicializarDenunciados");

    }


    /**
     *
     */
    public void inicializarConfirmados() {

        getLlNvp().put("confirmado", "4");
        Log.i(getClass().getName(), "inicializarConfirmados");
    }


    /**
     * @param jsonObject
     */
    public void JSONtoEvento(JSONObject jsonObject) {

        try {
            this.setS_nombreEvento(jsonObject.getString("nombreEvento"));

            this.setI_idEvento(jsonObject.getInt("idEvento"));

            this.setI_idUsuario(jsonObject.getInt("idUsuario"));
        } catch (JSONException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


    }

    /*
    * *********************************************
     * Getters and setters
     * ********************************************
     *
     */

    public int getI_idEvento() {
        return i_idEvento;
    }

    public void setI_idEvento(int i_idEvento) {
        this.i_idEvento = i_idEvento;
    }

    public int getI_idUsuario() {
        return i_idUsuario;
    }

    public void setI_idUsuario(int i_idUsuario) {
        this.i_idUsuario = i_idUsuario;
    }


    public String getS_comentario() {
        return s_comentario;
    }

    public void setS_comentario(String s_comentario) {
        this.s_comentario = s_comentario;
    }


    public String getS_nombreUsuarioComenta() {
        return s_nombreUsuarioComenta;
    }


    public LinkedList<ComentarioPOJO> getLl_comentario() {
        return ll_comentario;
    }

    public LinkedList<PenalizacionPOJO> getLl_penalizacion() {
        return ll_penalizacion;
    }

    public int getI_estado() {
        return i_estado;
    }

    public void setI_estado(int i_estado) {
        this.i_estado = i_estado;
    }

    public void setB_isImage(boolean b_isImage) {
        this.b_isImage = b_isImage;
    }


    public int getI_ano() {
        return i_ano;
    }

    public void setI_ano(int i_ano) {
        this.i_ano = i_ano;
    }

    public int getI_mes() {
        return i_mes;
    }

    public void setI_mes(int i_mes) {
        this.i_mes = i_mes;
    }

    public int getI_dia() {
        return i_dia;
    }

    public void setI_dia(int i_dia) {
        this.i_dia = i_dia;
    }

    public int getI_hora() {
        return i_hora;
    }

    public void setI_hora(int i_hora) {
        this.i_hora = i_hora;
    }

    public int getI_minutos() {
        return i_minutos;
    }

    public void setI_minutos(int i_minutos) {
        this.i_minutos = i_minutos;
    }

    public DireccionPOJO getDireccionPOJO() {
        return direccionPOJO;
    }


    public String getsCiudad() {
        return s_ciudad;
    }


    public int getI_seleccion() {
        return i_seleccion;
    }

    public void setI_seleccion(int i_seleccion) {
        this.i_seleccion = i_seleccion;
    }

    public String getS_ciudad() {
        return s_ciudad;
    }

    public void setS_ciudad(String s_ciudad) {
        this.s_ciudad = s_ciudad;
    }

    public String getS_localidad() {
        return s_localidad;
    }

    public void setS_localidad(String s_localidad) {
        this.s_localidad = s_localidad;
    }

    public int getI_categoria() {
        return i_categoria;
    }

    private void setI_categoria(int i_categoria) {
        this.i_categoria = i_categoria;
    }

    public int getI_cp() {
        return i_cp;
    }

    public void setI_cp(int i_cp) {
        this.i_cp = i_cp;
    }


    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    public String getS_nombreEvento() {
        return s_nombreEvento;
    }

    public void setS_nombreEvento(String s_nombreEvento) {
        this.s_nombreEvento = s_nombreEvento;
    }

    public String getS_categoria() {
        return s_categoria;
    }

    public void setS_categoria(String s_categoria) {
        this.s_categoria = s_categoria;
    }

    public String getS_clasificacion() {
        return s_clasificacion;
    }

    public void setS_clasificacion(String s_clasificacion) {
        this.s_clasificacion = s_clasificacion;
    }

    public String getS_tipo() {
        return s_tipo;
    }

    public void setS_tipo(String s_tipo) {
        this.s_tipo = s_tipo;
    }

    public String getS_fecha() {
        return s_fecha;
    }

    public void setS_fecha(String s_fecha) {
        this.s_fecha = s_fecha;
    }

    public int getI_numConfirma() {
        return i_numConfirma;
    }

    private void setI_numConfirma(int i_numConfirma) {
        this.i_numConfirma = i_numConfirma;
    }

    public int getI_numDenuncia() {
        return i_numDenuncia;
    }

    private void setI_numDenuncia(int i_numDenuncia) {
        this.i_numDenuncia = i_numDenuncia;
    }

    public int getI_numComenta() {
        return i_numComenta;
    }

    private void setI_numComenta(int i_numComenta) {
        this.i_numComenta = i_numComenta;
    }


    public String getS_nombreSala() {
        return s_nombreSala;
    }

    public void setS_nombreSala(String s_nombreSala) {
        this.s_nombreSala = s_nombreSala;
    }


    public String getS_artista() {
        return s_artista;
    }

    public void setS_artista(String s_artista) {
        this.s_artista = s_artista;
    }

    public int getI_precio() {
        return i_precio;
    }

    public void setI_precio(int i_precio) {
        this.i_precio = i_precio;
    }


    public String getS_descripcion() {
        return s_descripcion;
    }

    public void setS_descripcion(String s_descripcion) {
        this.s_descripcion = s_descripcion;
    }


    public String getS_creador() {
        return s_creador;
    }

    public void setS_creador(String s_creador) {
        this.s_creador = s_creador;
    }

    public void setEmailUsuarioSesion(String emailUsuarioSesion) {
        this.emailUsuarioSesion = emailUsuarioSesion;
    }

    public String getS_hora() {
        return s_hora;
    }

    public void setS_hora(String s_hora) {
        this.s_hora = s_hora;
    }
}
