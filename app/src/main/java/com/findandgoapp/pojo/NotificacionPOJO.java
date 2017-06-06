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
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.findandgoapp.custom.CustomFontTextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Juan F. Mateos
 */
public class NotificacionPOJO {

    private static final String TAG = "NotificacionPOJO";
    private int idUsuario;
    private int idEvento;
    private String nombreEvento;
    private String accion;
    private int iAccion;
    private int iMotivo;
    private String nombreUsuario;
    private String nombreUsuarioPenalizado;

    private ListView listView;
    private int contador = 0;

    private PenalizacionPOJO penalizacionPOJO = new PenalizacionPOJO();
    private final ComentarioPOJO comentarioPOJO = new ComentarioPOJO();
    private PuntuacionPOJO puntuacionPOJO = new PuntuacionPOJO();
    private TextView titulo;
    private CustomFontTextView tvTitulo;
    private int tipo;


    private int NumComentarios, NumDenuncias, NumConfirma;


    public NotificacionPOJO() {

    }


    /**
     * @param jsonObject
     */
    public void JSONComentarioToObject(JSONObject jsonObject) throws JSONException {

        getComentarioPOJO().setIdUsuario(jsonObject.getInt("idUsuario"));
        getComentarioPOJO().setNombreUsuario(jsonObject.getString("nombreUsuario"));
        getComentarioPOJO().setIdEvento(jsonObject.getInt("idEvento"));
        getComentarioPOJO().setNombreEvento(jsonObject.getString("nombreEvento"));
        getComentarioPOJO().setComentario(jsonObject.getString("comentario"));
        getComentarioPOJO().setsTipo(jsonObject.getString("tipo"));
        Log.e(TAG, "JSONComentarioToObject\n" + jsonObject.toString());

    }


    /**
     * @param jsonObject
     * @throws JSONException
     */
    public void JSONPenalizacionToObject(JSONObject jsonObject) throws JSONException {

        getPenalizacionPOJO().setIdPenalizacion(jsonObject.getInt("idPenalizacion"));
        getPenalizacionPOJO().setIdUsuario(jsonObject.getInt("idUsuario"));
        getPenalizacionPOJO().setPenalizacion(jsonObject.getString("tipoPenalizacion"));
        getPenalizacionPOJO().setFecha(jsonObject.getString("fechaCreacion"));
        getPenalizacionPOJO().setDescripcion(jsonObject.getString("idMotivo"));
        getPenalizacionPOJO().setsTipo(jsonObject.getString("tipo"));
        Log.i(TAG, "Penalizacion\n" + jsonObject.toString());
    }

    /**
     * @param jsonObject
     * @throws JSONException
     */
    public void JSONPuntuacionToObject(JSONObject jsonObject) throws JSONException {

        getPuntuacionPOJO().setsNombreUsuario(jsonObject.getString("nombreUsuarioDenuncia"));
        getPuntuacionPOJO().setIdUsuario(jsonObject.getInt("idUsuarioDenuncia"));
        getPuntuacionPOJO().setIdEvento(jsonObject.getInt("idEvento"));
        getPuntuacionPOJO().setsNombreEvento(jsonObject.getString("nombreEvento"));
        getPuntuacionPOJO().setsMotivo(jsonObject.getString("idMotivo"));
        getPuntuacionPOJO().setFecha(jsonObject.getString("fecha"));
        getPuntuacionPOJO().setsTipo(jsonObject.getString("tipo"));
        Log.i(TAG, "Puntuacion\n" + jsonObject.toString());
    }


    /**
     * @param jsonObject
     * @throws JSONException
     */
    public void JSONToEstadoNotificacion(JSONObject jsonObject) {


        try {
            setNombreEvento(jsonObject.getString("nombreEvento"));
            setNumComentarios(jsonObject.getInt("numComentarios"));
            setNumConfirma(jsonObject.getInt("numConfirmaciones"));
            setNumDenuncias(jsonObject.getInt("numDenuncias"));
            setTipo(0);
            Log.e(TAG, "JSONToEstadoNotificacion\n" + jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    /**
     * @param jsonObject
     * @throws JSONException
     */
    public void JSONToMencion(JSONObject jsonObject) {


        try {
            setNombreEvento(jsonObject.getString("nombreEvento"));
            setNombreUsuario(jsonObject.getString("nombreUsuario"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        setTipo(1);


        Log.e(TAG, "JSONToMencion\n" + jsonObject);

    }


    /**
     * @return linkedList
     */
    public Map<String, String> formatoId() {

        Map<String, String> linkedList = new HashMap<>();
        linkedList.put("idUsuario", String.valueOf(getIdUsuario()));

        Log.e(TAG, linkedList.toString());
        return linkedList;
    }

    /**
     * @return linkedList
     */
    public Map<String, String> formatoInfo() {

        Map<String, String> linkedList = new HashMap<>();
        linkedList.put("idUsuario", String.valueOf(getIdUsuario()));
        linkedList.put("tipo", String.valueOf(getTipo()));
        Log.e(TAG, linkedList.toString());
        return linkedList;
    }


    /**
     * @return
     */
    public Map<String, String> formatoValoracion() {

        Map<String, String> linkedList = new HashMap<>();
        linkedList.put("idUsuario", String.valueOf(getIdUsuario()));
        linkedList.put("motivo", String.valueOf(getiMotivo()));
        linkedList.put("accion", String.valueOf(getiAccion()));
        Log.d(TAG, linkedList.toString());
        return linkedList;
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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    private void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }


    public PenalizacionPOJO getPenalizacionPOJO() {
        return penalizacionPOJO;
    }

    public ComentarioPOJO getComentarioPOJO() {
        return comentarioPOJO;
    }


    public PuntuacionPOJO getPuntuacionPOJO() {
        return puntuacionPOJO;
    }

    public int getiAccion() {
        return iAccion;
    }

    public void setiAccion(int iAccion) {
        this.iAccion = iAccion;
    }

    public int getiMotivo() {
        return iMotivo;
    }

    public void setiMotivo(int iMotivo) {
        this.iMotivo = iMotivo;
    }


    public void setTvTitulo(CustomFontTextView tvTitulo) {
        this.tvTitulo = tvTitulo;
    }

    public int getContador() {
        return contador;
    }

    private void setContador(int contador) {
        this.contador = contador;
    }

    /**
     * @param c
     * @param elementos
     * @return
     */
    public ArrayList<NotificacionPOJO> formateoListNotificaciones(Context c, ArrayList<NotificacionPOJO> elementos) {
        final ArrayList<NotificacionPOJO> arrayList = new ArrayList<>();
        Log.e(TAG, "LinkedList Elementos" + elementos.get(0).getPuntuacionPOJO().getIdEvento());

        for (int i = 0; i < elementos.size(); i++) {
            Log.e(TAG, "ANTEs I " + i + ": " + elementos.get(i).getPuntuacionPOJO().getIdEvento() + " " + elementos.get(i).getContador());
            for (int j = 0; j < elementos.size(); j++) {

                Log.e(TAG, "ANTEs j " + j + elementos.get(j).getPuntuacionPOJO().getIdEvento() + " " + elementos.get(i).getPuntuacionPOJO().getIdEvento());
                if (elementos.get(j).getPuntuacionPOJO().getIdEvento() == elementos.get(i).getPuntuacionPOJO().getIdEvento()) {
                    int cont = elementos.get(j).getContador();
                    elementos.get(j).setContador(cont + 1);
                    Log.e(TAG, "if " + j + +elementos.get(j).getContador());

                    boolean esta = false;
                    for (int k = 0; k < arrayList.size(); k++) {
                        esta = false;
                        Log.e(TAG, "FOR ARRAY " + arrayList.get(k).getPuntuacionPOJO().getIdEvento() + " | " + elementos.get(j).getPuntuacionPOJO().getIdEvento());
                        if ((arrayList.get(k).getPuntuacionPOJO().getIdEvento() == elementos.get(j).getPuntuacionPOJO().getIdEvento())) {
                            esta = true;
                        } else {
                            Log.e(TAG, "No está en arraylist " + elementos.get(j).getPuntuacionPOJO().getIdEvento() + " ");
                        }
                    }
                    if (!esta) {
                        arrayList.add(elementos.get(j));
                        Log.e(TAG, "idEvento " + elementos.get(j).getPuntuacionPOJO().getIdEvento() + " " + elementos.get(j).getContador());
                    }


                } else {
                    //arrayList.add(elementos.get(j));
                    Log.e(TAG, "ELSE ");
                }

            }
        }
        return arrayList;
    }

    public int getNumComentarios() {
        return NumComentarios;
    }

    private void setNumComentarios(int numComentarios) {
        NumComentarios = numComentarios;
    }

    public int getNumDenuncias() {
        return NumDenuncias;
    }

    private void setNumDenuncias(int numDenuncias) {
        NumDenuncias = numDenuncias;
    }

    public int getNumConfirma() {
        return NumConfirma;
    }

    private void setNumConfirma(int numConfirma) {
        NumConfirma = numConfirma;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
