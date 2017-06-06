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

import com.findandgoapp.custom.CustomFontTextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Juan F. Mateos
 */
public class AdminPOJO {


    private static final String TAG = "TabAdminUsuariosPOJO";
    private final static int iIdUsuario = 1;

    private CustomFontTextView idTvEmpresaActivo;
    private CustomFontTextView idTvEmpresaPenalizado;
    private CustomFontTextView idTvEmpresaEliminado;
    private CustomFontTextView idTvSumatorioVerticalEmpresa;

    private CustomFontTextView idTvArtistaActivo;
    private CustomFontTextView idTvArtistaPenalizado;
    private CustomFontTextView idTvArtistaEliminado;
    private CustomFontTextView idTvSumatorioVerticalArtista;

    private CustomFontTextView idTvAsisteActivo;
    private CustomFontTextView idTvAsistePenalizado;
    private CustomFontTextView idTvAsisteEliminado;
    private CustomFontTextView idTvSumatorioVerticalAsistente;

    private CustomFontTextView idTvSumatorioActivo;
    private CustomFontTextView idTvSumatorioPenalizado;
    private CustomFontTextView idTvSumatorioEliminado;

    private CustomFontTextView idTvTotal;


    /**
     * @return Map<String, String>
     */
    public Map<String, String> toArray() {
        // TODO Auto-generated method stub
        Map<String, String> map = new HashMap<>();

        map.put("sIdUsuario", String.valueOf(getiIdUsuario()));

        Log.d("TabAdminUsPOJO->toArray", map.toString());

        return map;
    }

    /**
     * @param json
     * @throws JSONException
     */
    public void rellenarMatrizUsuario(JSONObject json) throws JSONException {


        int totalActivos = 0;
        int totalPenalizados = 0;
        int totalEliminados = 0;
        int totalEmpresas = 0;
        int totalArtistas = 0;
        int totalAsistentes = 0;

        JSONObject jsonObject = json.getJSONObject("empresa");
        Log.e(TAG, String.valueOf(jsonObject));

        getIdTvEmpresaActivo().setText(jsonObject.getString("activos"));
        totalActivos = totalActivos + Integer.parseInt(getIdTvEmpresaActivo().getText().toString());
        totalEmpresas = totalEmpresas + Integer.parseInt(getIdTvEmpresaActivo().getText().toString());

        getIdTvEmpresaPenalizado().setText(jsonObject.getString("bloqueados"));
        totalPenalizados = totalPenalizados + Integer.parseInt(getIdTvEmpresaPenalizado().getText().toString());
        totalEmpresas = totalEmpresas + Integer.parseInt(getIdTvEmpresaPenalizado().getText().toString());

        getIdTvEmpresaEliminado().setText(jsonObject.getString("eliminados"));
        totalEliminados = totalEliminados + Integer.parseInt(getIdTvEmpresaEliminado().getText().toString());
        totalEmpresas = totalEmpresas + Integer.parseInt(getIdTvEmpresaEliminado().getText().toString());

        jsonObject = json.getJSONObject("artista");
        Log.e(TAG, String.valueOf(jsonObject));
        getIdTvArtistaActivo().setText(jsonObject.getString("activos"));
        totalActivos = totalActivos + Integer.parseInt(getIdTvArtistaActivo().getText().toString());
        totalArtistas = totalArtistas + Integer.parseInt(getIdTvArtistaActivo().getText().toString());

        getIdTvArtistaPenalizado().setText(jsonObject.getString("bloqueados"));
        totalPenalizados = totalPenalizados + Integer.parseInt(getIdTvArtistaPenalizado().getText().toString());
        totalArtistas = totalArtistas + Integer.parseInt(getIdTvArtistaPenalizado().getText().toString());

        getIdTvArtistaEliminado().setText(jsonObject.getString("eliminados"));
        totalEliminados = totalEliminados + Integer.parseInt(getIdTvArtistaEliminado().getText().toString());
        totalArtistas = totalArtistas + Integer.parseInt(getIdTvArtistaEliminado().getText().toString());


        jsonObject = json.getJSONObject("asistente");
        Log.e(TAG, String.valueOf(jsonObject));
        getIdTvAsisteActivo().setText(jsonObject.getString("activos"));
        totalActivos = totalActivos + Integer.parseInt(getIdTvAsisteActivo().getText().toString());
        totalAsistentes = totalAsistentes + Integer.parseInt(getIdTvAsisteActivo().getText().toString());

        getIdTvAsistePenalizado().setText(jsonObject.getString("bloqueados"));
        totalPenalizados = totalPenalizados + Integer.parseInt(getIdTvAsistePenalizado().getText().toString());
        totalAsistentes = totalAsistentes + Integer.parseInt(getIdTvAsistePenalizado().getText().toString());

        getIdTvAsisteEliminado().setText(jsonObject.getString("eliminados"));
        totalEliminados = totalEliminados + Integer.parseInt(getIdTvAsisteEliminado().getText().toString());
        totalAsistentes = totalAsistentes + Integer.parseInt(getIdTvAsisteEliminado().getText().toString());

        getIdTvSumatorioActivo().setText(String.valueOf(totalActivos));
        getIdTvSumatorioPenalizado().setText(String.valueOf(totalPenalizados));
        getIdTvSumatorioEliminado().setText(String.valueOf(totalEliminados));
        getIdTvSumatorioVerticalEmpresa().setText(String.valueOf(totalEmpresas));
        getIdTvSumatorioVerticalArtista().setText(String.valueOf(totalArtistas));
        getIdTvSumatorioVerticalAsistente().setText(String.valueOf(totalAsistentes));

        getIdTvTotal().setText(String.valueOf(totalActivos + totalPenalizados + totalEliminados));


    }


    /**
     * @return sIdUsuario
     */
    public int getiIdUsuario() {
        return iIdUsuario;
    }


    public CustomFontTextView getIdTvEmpresaActivo() {
        return idTvEmpresaActivo;
    }

    public void setIdTvEmpresaActivo(CustomFontTextView idTvEmpresaActivo) {
        this.idTvEmpresaActivo = idTvEmpresaActivo;
    }

    public CustomFontTextView getIdTvEmpresaPenalizado() {
        return idTvEmpresaPenalizado;
    }

    public void setIdTvEmpresaPenalizado(CustomFontTextView idTvEmpresaPenalizado) {
        this.idTvEmpresaPenalizado = idTvEmpresaPenalizado;
    }

    public CustomFontTextView getIdTvEmpresaEliminado() {
        return idTvEmpresaEliminado;
    }

    public void setIdTvEmpresaEliminado(CustomFontTextView idTvEmpresaEliminado) {
        this.idTvEmpresaEliminado = idTvEmpresaEliminado;
    }

    public CustomFontTextView getIdTvSumatorioVerticalEmpresa() {
        return idTvSumatorioVerticalEmpresa;
    }

    public void setIdTvSumatorioVerticalEmpresa(CustomFontTextView idTvSumatorioVerticalEmpresa) {
        this.idTvSumatorioVerticalEmpresa = idTvSumatorioVerticalEmpresa;
    }

    public CustomFontTextView getIdTvArtistaActivo() {
        return idTvArtistaActivo;
    }

    public void setIdTvArtistaActivo(CustomFontTextView idTvArtistaActivo) {
        this.idTvArtistaActivo = idTvArtistaActivo;
    }

    public CustomFontTextView getIdTvArtistaPenalizado() {
        return idTvArtistaPenalizado;
    }

    public void setIdTvArtistaPenalizado(CustomFontTextView idTvArtistaPenalizado) {
        this.idTvArtistaPenalizado = idTvArtistaPenalizado;
    }

    public CustomFontTextView getIdTvArtistaEliminado() {
        return idTvArtistaEliminado;
    }

    public void setIdTvArtistaEliminado(CustomFontTextView idTvArtistaEliminado) {
        this.idTvArtistaEliminado = idTvArtistaEliminado;
    }

    public CustomFontTextView getIdTvSumatorioVerticalArtista() {
        return idTvSumatorioVerticalArtista;
    }

    public void setIdTvSumatorioVerticalArtista(CustomFontTextView idTvSumatorioVerticalArtista) {
        this.idTvSumatorioVerticalArtista = idTvSumatorioVerticalArtista;
    }

    public CustomFontTextView getIdTvAsisteActivo() {
        return idTvAsisteActivo;
    }

    public void setIdTvAsisteActivo(CustomFontTextView idTvAsisteActivo) {
        this.idTvAsisteActivo = idTvAsisteActivo;
    }

    public CustomFontTextView getIdTvAsistePenalizado() {
        return idTvAsistePenalizado;
    }

    public void setIdTvAsistePenalizado(CustomFontTextView idTvAsistePenalizado) {
        this.idTvAsistePenalizado = idTvAsistePenalizado;
    }

    public CustomFontTextView getIdTvAsisteEliminado() {
        return idTvAsisteEliminado;
    }

    public void setIdTvAsisteEliminado(CustomFontTextView idTvAsisteEliminado) {
        this.idTvAsisteEliminado = idTvAsisteEliminado;
    }

    public CustomFontTextView getIdTvSumatorioVerticalAsistente() {
        return idTvSumatorioVerticalAsistente;
    }

    public void setIdTvSumatorioVerticalAsistente(CustomFontTextView idTvSumatorioVerticalAsistente) {
        this.idTvSumatorioVerticalAsistente = idTvSumatorioVerticalAsistente;
    }

    public CustomFontTextView getIdTvSumatorioActivo() {
        return idTvSumatorioActivo;
    }

    public void setIdTvSumatorioActivo(CustomFontTextView idTvSumatorioActivo) {
        this.idTvSumatorioActivo = idTvSumatorioActivo;
    }

    public CustomFontTextView getIdTvSumatorioPenalizado() {
        return idTvSumatorioPenalizado;
    }

    public void setIdTvSumatorioPenalizado(CustomFontTextView idTvSumatorioPenalizado) {
        this.idTvSumatorioPenalizado = idTvSumatorioPenalizado;
    }

    public CustomFontTextView getIdTvSumatorioEliminado() {
        return idTvSumatorioEliminado;
    }

    public void setIdTvSumatorioEliminado(CustomFontTextView idTvSumatorioEliminado) {
        this.idTvSumatorioEliminado = idTvSumatorioEliminado;
    }

    public CustomFontTextView getIdTvTotal() {
        return idTvTotal;
    }

    public void setIdTvTotal(CustomFontTextView idTvTotal) {
        this.idTvTotal = idTvTotal;
    }


}
