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
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.findandgoapp.activity.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Juan F. Mateos
 */
public class ComentarioPOJO {

    private TextView tvComenta;
    private EditText etComenta;

    private int idEvento;
    private int idUsuario;
    private int idComentario;
    private Button bComenta;
    private Button bCancela;
    private String nombreUsuario;
    private String nombreEvento;
    private String comentario;
    private int modo;
    private String sTipo;
    private int idPenalizacion;


    public ComentarioPOJO() {

    }

    public ComentarioPOJO(ComentarioPOJO comentario) {
        comentario.setIdEvento(getIdEvento());
        comentario.setIdUsuario(getIdUsuario());
        comentario.setIdComentario(getIdComentario());
        comentario.setNombreUsuario(getNombreUsuario());
        comentario.setNombreEvento(getNombreEvento());
        comentario.setComentario(getComentario());
        comentario.setModo(getModo());
        comentario.setsTipo(getsTipo());
        comentario.setIdPenalizacion(getIdPenalizacion());
    }


    /**
     * @param c
     * @param valor
     * @param campo
     * @return
     */
    public static String insertaComentarioFromEditText(Context c, final String valor, final String campo) {
        return c.getResources().getString(R.string.sCampo) + " " + campo + " " + c.getResources().getString(R.string.sDeberiaContener) + " " + valor;
    }


    /**
     * @return
     */
    public Map<String, String> toArray() {

        Map<String, String> map = new HashMap<>();

        map.put("idUsuario", String.valueOf(getIdUsuario()));
        map.put("idEvento", String.valueOf(getIdEvento()));
        map.put("comentario", getComentario());
        map.put("idComentario", String.valueOf(getIdComentario()));
        map.put("modo", String.valueOf(getModo()));
        map.put("idPenalizacion", String.valueOf(getIdPenalizacion()));
        //map.put("nombreUsuario",getNombreUsuario());


        Log.d(getClass().getName(), "ComentPOJO->toArray() " + map.toString());
        return map;

    }


    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }


    public void setbCancela(Button bCancela) {
        this.bCancela = bCancela;
    }


    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setEtComenta(EditText etComenta) {
        this.etComenta = etComenta;
    }

    public void setbComenta(Button bComenta) {
        this.bComenta = bComenta;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
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

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getsTipo() {
        return sTipo;
    }

    public void setsTipo(String sTipo) {
        this.sTipo = sTipo;
    }

    public int getModo() {
        return modo;
    }

    public void setModo(int modo) {
        this.modo = modo;
    }

    public int getIdPenalizacion() {
        return idPenalizacion;
    }

    public void setIdPenalizacion(int idPenalizacion) {
        this.idPenalizacion = idPenalizacion;
    }
}
