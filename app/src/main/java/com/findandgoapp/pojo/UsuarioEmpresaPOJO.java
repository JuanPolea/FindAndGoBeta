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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Juan F. Mateos
 */
public class UsuarioEmpresaPOJO extends UsuarioArtistaPOJO {

    private static final String TAG = "UsuarioEmpresaPOJO";
    private static final int tipo = 2;
    private String s_cp;
    private String s_localidad;
    private String s_calle;
    private String s_numero;
    private String s_tipo;
    private String s_via;


    public static final Parcelable.Creator<UsuarioEmpresaPOJO> CREATOR = new Parcelable.Creator<UsuarioEmpresaPOJO>() {
        @Override
        public UsuarioEmpresaPOJO createFromParcel(Parcel in) {
            return new UsuarioEmpresaPOJO(in);
        }

        @Override
        public UsuarioEmpresaPOJO[] newArray(int size) {
            return new UsuarioEmpresaPOJO[size];
        }
    };

    public UsuarioEmpresaPOJO(Parcel in) {
        super.setItems(in.createStringArray());
        super.setI_motivo(in.readInt());
        super.setI_idUsuario(in.readInt());
        super.setI_tipo(tipo);
    }

    public UsuarioEmpresaPOJO() {
        setI_tipo(tipo);
    }


    /**
     * @return
     */
    @Override
    public Map<String, String> toArray() {
        // TODO Auto-generated method stub
        Map<String, String> params = new HashMap<>();

        params.put("sEmpresaNombre", getS_nombre());
        params.put("sEmpresaEmail", getS_email());
        params.put("sEmpresaPassword", getS_password());
        params.put("sEmpresaCategoria", getS_categoria());
        params.put("sEmpresaTipo", getS_tipo());
        params.put("sEmpresaCiudad", getS_ciudad());
        params.put("sEmpresaLocalidad", getS_localidad());
        params.put("sEmpresaVia", getS_via());
        params.put("sEmpresaCalle", getS_calle());
        params.put("sEmpresaNumero", getS_numero());
        params.put("sEmpresaCP", getS_cp());
        params.put("sEmpresaDescripcion", getS_descripcion());
        params.put("sEmpresaWeb", getS_web());
        params.put("idEmpresa", String.valueOf(getI_idUsuario()));
        return params;
    }

    /**
     * @return
     */
    public Map<String, String> formatoId() {
        // TODO Auto-generated method stub
        Map<String, String> params = new HashMap<>();

        params.put("idUsuario", String.valueOf(this.getI_idUsuario()));
        params.put("password", String.valueOf(this.getS_password()));
        return params;
    }


    public String getS_calle() {
        return s_calle;
    }

    public void setS_calle(String s_calle) {
        this.s_calle = s_calle;
    }

    public String getS_cp() {
        return s_cp;
    }

    public void setS_cp(String s_cp) {
        this.s_cp = s_cp;
    }

    public String getS_localidad() {
        return s_localidad;
    }

    public void setS_localidad(String s_localidad) {
        this.s_localidad = s_localidad;
    }

    private String getS_numero() {
        return s_numero;
    }

    public void setS_numero(String s_numero) {
        this.s_numero = s_numero;
    }

    public String getS_tipo() {
        return s_tipo;
    }

    public void setS_tipo(String s_tipo) {
        this.s_tipo = s_tipo;
    }

    public String getS_via() {
        return s_via;
    }

    public void setS_via(String s_via) {
        this.s_via = s_via;
    }


}
