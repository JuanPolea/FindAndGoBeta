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

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.findandgoapp.activity.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Juan F. Mateos
 */
public class AlertaPOJO extends EventoPOJO implements Parcelable {

    private int i_idalerta;
    private int i_idSesion;
    private int i_idUsuario;
    private int i_estado;
    private boolean b_estado = true;
    private String s_Titulo;
    private String s_nombreUsuario;
    private int posicion;
    private ListView listView;

    private int i_ano;
    private int i_mes;
    private int i_dia;
    private int i_hora;
    private int i_minutos;

    private Context context;
    private String[] s_semana;
    private LinkedList<Integer> ll_diaSeleccionado = new LinkedList<>();
    private boolean isAlerta;

    private boolean[] isSelectedArray = {true, true, true, true, true, true, true};

    private int[] i_idiaSeleccionado = {1, 1, 1, 1, 1, 1, 1};

    public AlertaPOJO(Context context) {
        this.context = context;

        s_semana = new String[]{

                context.getString(R.string.sLunes),
                context.getString(R.string.sMartes),
                context.getString(R.string.sMiercoles),
                context.getString(R.string.sJueves),
                context.getString(R.string.sViernes),
                context.getString(R.string.sSabado),
                context.getString(R.string.sDomingo)

        };

    }

    public AlertaPOJO(boolean b_estado, int i_idalerta, int i_idSesion, int i_idUsuario, LinkedList<Integer> ll_diaSeleccionado, String s_nombreUsuario) {
        this.b_estado = b_estado;
        this.i_idalerta = i_idalerta;
        this.i_idSesion = i_idSesion;
        this.i_idUsuario = i_idUsuario;
        this.ll_diaSeleccionado = ll_diaSeleccionado;
        this.s_nombreUsuario = s_nombreUsuario;
    }

    public AlertaPOJO() {

    }


    /**
     * @param in
     */
    protected AlertaPOJO(Parcel in) {

        i_ano = in.readInt();
        i_mes = in.readInt();
        i_dia = in.readInt();
        i_hora = in.readInt();
        i_minutos = in.readInt();
        i_idUsuario = in.readInt();
        s_nombreUsuario = in.readString();
        i_idalerta = in.readInt();
        i_idSesion = in.readInt();
        s_semana = in.createStringArray();
        isSelectedArray = in.createBooleanArray();
        i_idiaSeleccionado = in.createIntArray();

    }

    public AlertaPOJO(int i_idalerta, int i_idUsuario, int estado) {

        setI_idalerta(i_idalerta);
        setI_idUsuario(i_idUsuario);
        setI_estado(estado);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(i_idalerta);
        dest.writeInt(i_idSesion);
        dest.writeInt(i_idUsuario);
        dest.writeBooleanArray(isSelectedArray);
        dest.writeStringArray(s_semana);
        dest.writeList(ll_diaSeleccionado);


    }


    public static final Creator<AlertaPOJO> CREATOR = new Creator<AlertaPOJO>() {
        @Override
        public AlertaPOJO createFromParcel(Parcel in) {
            return new AlertaPOJO(in);
        }

        @Override
        public AlertaPOJO[] newArray(int size) {
            return new AlertaPOJO[size];
        }
    };

    /**
     * métodos de la clase
     */

    public Map<String, String> formatoIdAlerta() {

        Map<String, String> map = new HashMap<>();
        map.put("idAlerta", String.valueOf(this.getI_idalerta()));
        map.put("estado", String.valueOf(getI_estado()));

        Log.e(getClass().getName(), map.toString());
        return map;
    }


    /**
     * @param c
     * @param jsonObject
     * @throws JSONException
     */
    public void toAlertaListado(Context c, JSONObject jsonObject) throws JSONException {

        setI_idUsuario(jsonObject.getInt(c.getString(R.string.sIdUsuario)));
        setI_idalerta(jsonObject.getInt(c.getString(R.string.sIdAlerta)));
        // setI_idSesion(jsonObject.getInt(c.getString(R.string.sIdSesion)));
        setI_estado(jsonObject.getInt(c.getString(R.string.estado)));
        setS_Titulo(jsonObject.getString(c.getString(R.string.tituloAlerta)));
    }


    /**
     * @param context
     * @param textView
     */
    public void setDiaByTextView(Context context, TextView textView) {

        getll_diaSeleccionado().clear();

        if (textView.getText().toString().equalsIgnoreCase(context.getString(R.string.sRepetir))) {

            for (int i = 0; i < getll_diaSeleccionado().size(); i++) {
                getll_diaSeleccionado().add(i);
            }

            for (int i = 0; i < getIsSelectedArray().length; i++) {
                getIsSelectedArray()[i] = true;
            }

            for (int i = 0; i < getll_diaSeleccionado().size(); i++) {
                getI_idiaSeleccionado()[i] = 1;
            }

        } else {
            if (textView.getText().toString().contains(this.context.getResources().getString(R.string.sInicialLunes))) {
                getll_diaSeleccionado().add(0);
                getIsSelectedArray()[0] = true;
                getI_idiaSeleccionado()[0] = 1;

            } else {
                getIsSelectedArray()[0] = false;
                getI_idiaSeleccionado()[0] = 0;
            }

            if (textView.getText().toString().contains(this.context.getResources().getString(R.string.sInicialMartes))) {
                getll_diaSeleccionado().add(1);
                getIsSelectedArray()[1] = true;
                getI_idiaSeleccionado()[1] = 1;

            } else {
                getIsSelectedArray()[1] = false;
                getI_idiaSeleccionado()[1] = 0;
            }

            if (textView.getText().toString().contains(this.context.getResources().getString(R.string.sInicialMiercoles))) {
                getll_diaSeleccionado().add(2);
                getIsSelectedArray()[2] = true;
                getI_idiaSeleccionado()[2] = 1;

            } else {
                getIsSelectedArray()[2] = false;
                getI_idiaSeleccionado()[2] = 0;
            }

            if (textView.getText().toString().contains(this.context.getResources().getString(R.string.sInicialJueves))) {
                getll_diaSeleccionado().add(3);
                getI_idiaSeleccionado()[3] = 1;
                getIsSelectedArray()[3] = true;

            } else {
                getIsSelectedArray()[3] = false;
                getI_idiaSeleccionado()[3] = 0;
            }

            if (textView.getText().toString().contains(this.context.getResources().getString(R.string.sInicialViernes))) {
                getll_diaSeleccionado().add(4);
                getIsSelectedArray()[4] = true;
                getI_idiaSeleccionado()[4] = 1;

            } else {
                getIsSelectedArray()[4] = false;
                getI_idiaSeleccionado()[4] = 0;
            }

            if (textView.getText().toString().contains(this.context.getResources().getString(R.string.sInicialSabado))) {
                getll_diaSeleccionado().add(5);
                getI_idiaSeleccionado()[5] = 1;
                getIsSelectedArray()[5] = true;

            } else {
                getIsSelectedArray()[5] = false;
                getI_idiaSeleccionado()[5] = 0;
            }

            if (textView.getText().toString().contains(this.context.getResources().getString(R.string.sInicialDomingo))) {
                getll_diaSeleccionado().add(6);
                getI_idiaSeleccionado()[6] = 1;
                getIsSelectedArray()[6] = true;

            } else {
                getIsSelectedArray()[6] = false;
                getI_idiaSeleccionado()[6] = 0;
            }
        }


        Log.e(getClass().getName(), "setDiaByTX " + getll_diaSeleccionado().toString() + "\n" + Arrays.toString(getIsSelectedArray())
                + "\n" + Arrays.toString(getI_idiaSeleccionado()));
    }

    /**
     * @return
     */
    public Map<String, String> formatoId(Context c) {

        Map<String, String> map = new HashMap();

        map.put(c.getString(R.string.sIdAlerta), String.valueOf(getI_idalerta()));
        map.put(c.getString(R.string.sIdUsuario), String.valueOf(getI_idUsuario()));
        map.put(c.getString(R.string.sIdSesion), String.valueOf(getI_idSesion()));

        Log.e(getClass().getName(), map.toString());
        return map;
    }


    /**
     * @param repetir
     */
    public void repetir(JSONArray repetir) {

        int tamano = repetir.length();
        int cont = 0;

        while (cont < tamano) {
            try {
                JSONObject jsonObject = repetir.getJSONObject(cont);

                getll_diaSeleccionado().add(jsonObject.getInt("idDia"));


            } catch (JSONException e) {
                e.printStackTrace();
            }
            cont++;
        }
    }


    /**
     * Getteres and setters
     *
     *
     */


    /**
     * @return
     */
    public boolean[] getIsSelectedArray() {
        return isSelectedArray;
    }

    /**
     * @param isSelectedArray
     */


    public void setIsSelectedArray(boolean[] isSelectedArray) {
        this.isSelectedArray = isSelectedArray;
    }

    public boolean isB_estado() {
        return b_estado;
    }


    public LinkedList<Integer> getll_diaSeleccionado() {
        return ll_diaSeleccionado;
    }

    public int getI_estado() {
        return i_estado;
    }

    public void setI_estado(int i_estado) {
        this.i_estado = i_estado;
    }

    public int getI_idalerta() {
        return i_idalerta;
    }

    public void setI_idalerta(int i_idalerta) {
        this.i_idalerta = i_idalerta;
    }

    public int getI_idSesion() {
        return i_idSesion;
    }

    public void setI_idSesion(int i_idSesion) {
        this.i_idSesion = i_idSesion;
    }

    public int getI_idUsuario() {
        return i_idUsuario;
    }

    public void setI_idUsuario(int i_idUsuario) {
        this.i_idUsuario = i_idUsuario;
    }


    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    public String getS_nombreUsuario() {
        return s_nombreUsuario;
    }

    public String getS_Titulo() {
        return s_Titulo;
    }

    public void setS_Titulo(String s_Titulo) {
        this.s_Titulo = s_Titulo;
    }


    @Override
    public int getI_ano() {
        return i_ano;
    }

    @Override
    public void setI_ano(int i_ano) {
        this.i_ano = i_ano;
    }

    @Override
    public int getI_dia() {
        return i_dia;
    }

    @Override
    public void setI_dia(int i_dia) {
        this.i_dia = i_dia;
    }

    @Override
    public int getI_hora() {
        return i_hora;
    }

    @Override
    public void setI_hora(int i_hora) {
        this.i_hora = i_hora;
    }

    @Override
    public int getI_mes() {
        return i_mes;
    }

    @Override
    public void setI_mes(int i_mes) {
        this.i_mes = i_mes;
    }

    @Override
    public int getI_minutos() {
        return i_minutos;
    }

    @Override
    public void setI_minutos(int i_minutos) {
        this.i_minutos = i_minutos;
    }

    public int[] getI_idiaSeleccionado() {
        return i_idiaSeleccionado;
    }


}
