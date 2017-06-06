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

package com.findandgoapp.pojo;

import android.util.Log;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Juan F. Mateos
 */
public class PenalizacionPOJO {

    private static final String TAG = "PenalizacionPOJO";
    private String nombreEvento;
    private String usuario;
    private String penalizacion;
    private int idUsuario;
    private int idEvento;

    private int iTipoPenalizacion;
    private String fecha;
    private String descripcion;
    private int idPenalizacion;
    private String sTipo;
    private int posicion = -1;
    private String valor;

    private ListView listView;


    /**
     * @return Map<String,String>
     * @desc formatear idUsuario y selección (Mis eventos o todos by date)
     */
    public Map<String, String> formatoId() {

        Map<String, String> linkedList = new HashMap<>();

        linkedList.put("idUsuario", String.valueOf(getIdUsuario()));
        linkedList.put("penalizacion", String.valueOf(getiTipoPenalizacion()));

        Log.d(TAG, "formatoIdUsuario\n" + linkedList.toString());

        return linkedList;

    }

    /**
     * @return List<NameValuePair>
     * @desc formatear idUsuario y penalizacion
     */
    public Map<String, String> formatoIdUsuarioIdPenalizacion() {

        Map<String, String> linkedList = new HashMap<>();


        linkedList.put("sIdPenalizacion", String.valueOf(getIdPenalizacion()));
        linkedList.put("idUsuario", String.valueOf(getIdUsuario()));
        Log.e(TAG, "formatoIdUsuarioIdPenalizacion\n" + linkedList.toString());

        return linkedList;

    }


    /**
     * @param jsonObject
     * @throws JSONException
     */
    public void JSONtoObject(JSONObject jsonObject) throws JSONException {

        setIdUsuario(jsonObject.getInt("idUsuario"));
        setFecha(jsonObject.getString("fechaCreacion"));
        setDescripcion(jsonObject.getString("descripcionMotivo"));
        setIdPenalizacion(jsonObject.getInt("idPenalizacion"));


        Log.d(TAG, jsonObject.toString());
    }

    /**
     * @param jsonObject
     * @throws JSONException
     */
    public void JSONtoSancion(JSONObject jsonObject) throws JSONException {

        setIdUsuario(jsonObject.getInt("idUsuario"));
        setFecha(jsonObject.getString("fechaCreacion"));
        setDescripcion(jsonObject.getString("descripcionMotivo"));
        setPenalizacion(jsonObject.getString("nombrePenalizacion"));


        Log.e(TAG, jsonObject.toString());
    }

    /**
     * @param jsonObject
     * @throws JSONException
     */

    public void JSONtoAlusion(JSONObject jsonObject) throws JSONException {


        setIdUsuario(jsonObject.getInt("idUsuario"));
        setFecha(jsonObject.getString("fechaEvento"));
        setNombreEvento(jsonObject.getString("nombreEvento"));
        setUsuario(jsonObject.getString("nombreUsuario"));
        setIdEvento(jsonObject.getInt("idEvento"));

        Log.e(getClass().getName(), "$JSONToAlusion " + jsonObject.toString());
    }


    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    private void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPenalizacion() {
        return penalizacion;
    }

    public void setPenalizacion(String penalizacion) {
        this.penalizacion = penalizacion;
    }

    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }


    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }


    public int getiTipoPenalizacion() {
        return iTipoPenalizacion;
    }

    public void setiTipoPenalizacion(int iTipoPenalizacion) {
        this.iTipoPenalizacion = iTipoPenalizacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdPenalizacion() {
        return idPenalizacion;
    }

    public void setIdPenalizacion(int idPenalizacion) {
        this.idPenalizacion = idPenalizacion;
    }

    public String getsTipo() {
        return sTipo;
    }

    public void setsTipo(String sTipo) {
        this.sTipo = sTipo;
    }


    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
