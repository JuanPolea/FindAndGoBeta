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

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pol on 09/05/15.
 */
public class UsuarioArtistaPOJO extends UsuarioPOJO {


    private static final String TAG = "UsuarioArtistaPOJO";
    private static final int tipoUsuario = 3;
    private String s_categoria;
    private String s_descripcion;
    private String s_web;

    public static final Parcelable.Creator<UsuarioArtistaPOJO> CREATOR = new Parcelable.Creator<UsuarioArtistaPOJO>() {
        @Override
        public UsuarioArtistaPOJO createFromParcel(Parcel in) {
            return new UsuarioArtistaPOJO(in);
        }

        @Override
        public UsuarioArtistaPOJO[] newArray(int size) {
            return new UsuarioArtistaPOJO[size];
        }
    };

    public UsuarioArtistaPOJO(Parcel in) {
        super.setItems(in.createStringArray());
        super.setI_motivo(in.readInt());
        super.setI_idUsuario(in.readInt());
        super.setI_tipo(3);
    }

    public UsuarioArtistaPOJO() {
        setI_tipo(3);
    }

    public UsuarioArtistaPOJO(String s_nombre, String s_email, String s_password, String s_ciudad, String s_categoria, String s_descripcion, String s_web, int i_idUsuario) {
        super(i_idUsuario, s_nombre, s_email, s_password, s_ciudad);

        this.s_categoria = s_categoria;
        this.s_descripcion = s_descripcion;
        this.s_web = s_web;
        setI_tipo(3);

    }


    /**
     * @return
     */
    @Override
    public Map<String, String> toArray() {
        // TODO Auto-generated method stub
        Map<String, String> params = new HashMap<>();


        params.put("sArtistaNombre", this.getS_nombre());
        params.put("sArtistaPassword", this.getS_password());
        params.put("sArtistaEmail", getS_email());
        params.put("sArtistaCiudad", getS_ciudad());
        params.put("sArtistaCategoria", getS_categoria());
        params.put("sArtistaDescripcion", getS_descripcion());
        params.put("sArtistaWeb", getS_web());
        params.put("sIdUsuario", String.valueOf(getI_idUsuario()));
        Log.d("ToArray", params.toString());
        return params;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(super.getItems());
        dest.writeInt(super.getI_motivo());
        dest.writeInt(super.getI_idUsuario());
    }


    public String getS_categoria() {
        return s_categoria;
    }

    public void setS_categoria(String s_categoria) {
        this.s_categoria = s_categoria;
    }

    public String getS_descripcion() {
        return s_descripcion;
    }

    public void setS_descripcion(String s_descripcion) {
        this.s_descripcion = s_descripcion;
    }

    public String getS_web() {
        return s_web;
    }

    public void setS_web(String s_web) {
        this.s_web = s_web;
    }

}
