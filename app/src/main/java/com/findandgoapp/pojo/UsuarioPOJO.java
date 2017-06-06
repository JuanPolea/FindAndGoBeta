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

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.ListView;

import com.findandgoapp.activity.R;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by juanpolea on 2/08/16.
 */
public class UsuarioPOJO implements Parcelable {


    public static final Parcelable.Creator<UsuarioPOJO> CREATOR = new Parcelable.Creator<UsuarioPOJO>() {
        @Override
        public UsuarioPOJO createFromParcel(Parcel in) {
            return new UsuarioPOJO(in);
        }

        @Override
        public UsuarioPOJO[] newArray(int size) {
            return new UsuarioPOJO[size];
        }
    };
    private String s_email;
    private String s_password;
    private int i_idUsuario;
    private long i_idFcbk;
    private int i_idSesion;
    private String s_nombre;
    private int i_tipo;
    private int i_estado;
    private String s_ciudad;
    private int i_tipoPenalizacion;
    private String s_nombreUsuarioPenalizado;
    private int i_idPenalizacion;
    private int i_motivo;
    private ListView listView;
    private String s_fechaPenalizacion;
    private String s_fechaAlta;
    private int i_penalizacion;
    private String[] items;
    private int i_confirmaIn;
    private int i_confirmaOut;
    private int i_denunciaIn;
    private int i_denunciaOut;
    private int i_idGoogle;
    private boolean isImage = true;

    public UsuarioPOJO(String s_email, String s_password) {
        this.s_email = s_email;
        this.s_password = s_password;

    }

    public UsuarioPOJO(String s_email, String s_password, String s_nombre) {
        this.s_email = s_email;
        this.s_password = s_password;
        this.s_nombre = s_nombre;

    }

    public UsuarioPOJO(String s_nombre, String s_email, String s_password, String s_ciudad) {
        this.s_nombre = s_nombre;
        this.s_email = s_email;
        this.s_password = s_password;
        this.s_ciudad = s_ciudad;
    }


    public UsuarioPOJO(int i_idUsuario, String s_nombre, String s_email, String s_password, String s_ciudad) {
        this.i_idUsuario = i_idUsuario;
        this.s_nombre = s_nombre;
        this.s_email = s_email;
        this.s_password = s_password;
        this.s_ciudad = s_ciudad;
        this.i_idSesion = i_idUsuario;
    }

    public UsuarioPOJO(UsuarioPOJO usuarioPOJO) {
        this.s_nombre = usuarioPOJO.getS_nombre();
        this.s_email = usuarioPOJO.getS_email();
        this.s_password = usuarioPOJO.getS_password();
        this.s_ciudad = usuarioPOJO.getS_ciudad();

    }

    public UsuarioPOJO() {
    }

    public UsuarioPOJO(Parcel in) {
        items = in.createStringArray();
        i_motivo = in.readInt();
        i_idUsuario = in.readInt();

    }


    /**
     * @return
     */
    public Map<String, String> formatoPenalizacion() {

        Map<String, String> linkedList = new HashMap<>();
        linkedList.put("idUsuario", String.valueOf(getI_idUsuario()));
        linkedList.put("tipoPenalizacion", String.valueOf(getI_tipoPenalizacion()));
        linkedList.put("motivo", String.valueOf(getI_motivo()));

        return linkedList;
    }

    /**
     * Formatear parámetros necesarios para el login de facebook
     *
     * @return
     */

    public List<NameValuePair> toArrayFacebook() {
        // TODO Auto-generated method stub
        LinkedList<NameValuePair> params = new LinkedList<>();

        params.add(new BasicNameValuePair("email", this.getS_email()));
        params.add(new BasicNameValuePair("password", this.getS_password()));
        params.add(new BasicNameValuePair("nombreUsuario", this.getS_nombre()));


        return params;
    }


    /**
     * @return
     */
    public Map<String, String> toArray() {
        // TODO Auto-generated method stub

        Map<String, String> params = new HashMap<>();

        params.put("sNombre", getS_nombre());
        params.put("sEmail", getS_email());
        params.put("sPassword", getS_password());
        params.put("sIdUsuario", String.valueOf(getI_idUsuario()));
        params.put("sidFacebook", String.valueOf(getI_idFcbk()));
        params.put("sCiudad", getS_ciudad());
        params.put("sEstado", String.valueOf(getI_estado()));
        params.put("sTipoPenalizacion", String.valueOf(getI_tipoPenalizacion()));
        params.put("sTipo", String.valueOf(getI_tipo()));
        params.put("sMotivo", String.valueOf(getI_motivo()));


        Log.d("UASP->toArray", params.toString());

        return params;
    }


    /**
     * @return
     */
    public Map<String, String> formatoIdUsuario(int seleccion) {
        Map<String, String> map = new HashMap<>();


        map.put("idUsuario", String.valueOf(getI_idUsuario()));
        map.put("seleccion", String.valueOf(seleccion));
        Log.e(getClass().getName(), map.toString());
        return map;
    }

    /**
     * @return
     */
    public Map<String, String> formatoIdUsuario(Context context) {
        Map<String, String> map = new HashMap<>();


        map.put(context.getString(R.string.sIdUsuario), String.valueOf(getI_idUsuario()));
        Log.e(getClass().getName(), map.toString());
        return map;
    }


    /**
     * @param jsonObject
     */
    public void JSONToNombre(JSONObject jsonObject) throws JSONException {

        setI_idUsuario(jsonObject.getInt("idUsuario"));
        setS_nombre(jsonObject.getString("nombreUsuario"));

    }

    /**
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(items);
        dest.writeInt(i_motivo);
        dest.writeInt(i_idUsuario);


    }

    public void JSONtoObject(JSONObject jsonObject) {

        try {
            this.setI_idUsuario(jsonObject.getInt("idUsuario"));
        } catch (JSONException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            this.setS_nombreUsuarioPenalizado(jsonObject.getString("nombreUsuario"));
        } catch (JSONException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            this.setS_fechaPenalizacion(jsonObject.getString("fechaCreacion"));
        } catch (JSONException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            this.setI_estado(jsonObject.getInt("estado"));
        } catch (JSONException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        /*try {
            this.setIdPenalizacion(jsonObject.getInt("idPenalizacion"));
        } catch (JSONException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }*/


    }

    public Map<String, String> formatoTipoEstado() {
        // TODO Auto-generated method stub
        Map<String, String> params = new HashMap<>();
        params.put("tipo", String.valueOf(getI_tipo()));
        params.put("estado", String.valueOf(getI_estado()));

        Log.d(getClass().getName(), "formatoTipoEstado\n" + params.toString());

        return params;
    }

    /**
     * @return
     */
    public Map<String, String> toNombrePasswordEmailCiudad() {
        // TODO Auto-generated method stub

        Map<String, String> map = new HashMap<>();

        map.put("sAsistenteNombre", getS_nombre());
        map.put("sAsistentePassword", getS_password());
        map.put("sAsistenteEmail", getS_email());
        map.put("sAsistenteCiudad", getS_ciudad());

        Log.d("UASP->toArray", map.toString());

        return map;
    }

    /************************************************
     * Getters and setters
     *
     * @return
     */


    public int getI_idUsuario() {
        return i_idUsuario;
    }

    public void setI_idUsuario(int i_idUsuario) {
        this.i_idUsuario = i_idUsuario;
    }

    public int getI_tipoPenalizacion() {
        return i_tipoPenalizacion;
    }

    public void setI_tipoPenalizacion(int i_tipoPenalizacion) {
        this.i_tipoPenalizacion = i_tipoPenalizacion;
    }

    public int getI_motivo() {
        return i_motivo;
    }

    public void setI_motivo(int i_motivo) {
        this.i_motivo = i_motivo;
    }

    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    public String[] getItems() {
        return items;
    }

    public void setItems(String[] items) {
        this.items = items;
    }


    public String getS_fechaPenalizacion() {
        return s_fechaPenalizacion;
    }

    private void setS_fechaPenalizacion(String s_fechaPenalizacion) {
        this.s_fechaPenalizacion = s_fechaPenalizacion;
    }

    public int getI_estado() {
        return i_estado;
    }

    public void setI_estado(int i_estado) {
        this.i_estado = i_estado;
    }


    public int getI_tipo() {
        return i_tipo;
    }

    public void setI_tipo(int i_tipo) {
        this.i_tipo = i_tipo;
    }

    public String getS_email() {
        return s_email;
    }

    public void setS_email(String s_email) {
        this.s_email = s_email;
    }

    public long getI_idFcbk() {
        return i_idFcbk;
    }

    public void setI_idFcbk(long i_idFcbk) {
        this.i_idFcbk = i_idFcbk;
    }


    public String getS_ciudad() {
        return s_ciudad;
    }

    public void setS_ciudad(String s_ciudad) {
        this.s_ciudad = s_ciudad;
    }

    public int getI_idSesion() {
        return i_idSesion;
    }

    public void setI_idSesion(int i_idSesion) {
        this.i_idSesion = i_idSesion;
    }

    public String getS_password() {
        return s_password;
    }

    public void setS_password(String s_password) {
        this.s_password = s_password;
    }

    public boolean isImage() {
        return isImage;
    }

    public String getS_nombre() {
        return s_nombre;
    }

    public void setS_nombre(String s_nombre) {
        this.s_nombre = s_nombre;
    }


    public String getS_nombreUsuarioPenalizado() {
        return s_nombreUsuarioPenalizado;
    }

    private void setS_nombreUsuarioPenalizado(String s_nombreUsuarioPenalizado) {
        this.s_nombreUsuarioPenalizado = s_nombreUsuarioPenalizado;
    }

    public int getI_idPenalizacion() {
        return i_idPenalizacion;
    }


}
