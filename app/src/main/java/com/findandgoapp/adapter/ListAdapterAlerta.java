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

package com.findandgoapp.adapter;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.findandgoapp.activity.Alerta;
import com.findandgoapp.activity.R;
import com.findandgoapp.custom.CustomFontTextView;
import com.findandgoapp.pojo.AlertaPOJO;
import com.findandgoapp.tools.Utilidades;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Juan F. Mateos
 */
public class ListAdapterAlerta extends BaseAdapter {

    private static final String TAG = "ListAdapterAlerta";
    private final Context _c;
    private final ArrayList<AlertaPOJO> _aTAP;
    private final LinkedList<Integer> selectedIndeces = new LinkedList<>();

    public ListAdapterAlerta(ArrayList<AlertaPOJO> aTAP, Context c) {

        _aTAP = aTAP;
        _c = c;
        Log.e(TAG, _aTAP.get(0).getI_idSesion() + " " + _aTAP.get(0).getI_idUsuario());

    }

    private void changeSelectedPositions(int pos) {
        int index = this.selectedIndeces.indexOf(pos);
        if (index != -1) {
            // image button in this row was selected
            Log.i(TAG, "Indice: " + String.valueOf(index));
            this.selectedIndeces.remove(index);
        } else {
            // mark position of the image button as selected
            this.selectedIndeces.add(pos);
        }

    }


    public int getCount() {
        // TODO Auto-generated method stub
        return _aTAP.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return _aTAP.get(position);
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    /**
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // TODO Auto-generated method stub

        final ViewHolder viewHolder;


        final AlertaPOJO tabAlertaPOJO = _aTAP.get(position);

        Log.i(TAG, "GetView");


        if (convertView == null) {

            LayoutInflater vi = (LayoutInflater) _c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.fragment_alarma_listado, null);

            viewHolder = new ViewHolder();
            viewHolder.relativeLayout = ((RelativeLayout) convertView.findViewById(R.id.rlAlerta));
            viewHolder.tvNombre = (CustomFontTextView) convertView.findViewById(R.id.idTvAlertaNombre);
            viewHolder.toggleButton = (Switch) convertView.findViewById(R.id.idTBAlerta);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.ivFlechaAlerta);
            viewHolder.idAlerta = _aTAP.get(position).getI_idalerta();
            viewHolder.idUsuario = _aTAP.get(position).getI_idUsuario();

            if (tabAlertaPOJO.getI_estado() == 1)
                viewHolder.toggleButton.setChecked(true);
            else
                viewHolder.toggleButton.setChecked(false);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.tvNombre.setText(_aTAP.get(position).getS_Titulo());
        viewHolder.tvNombre.setTextColor(Color.BLUE);
        viewHolder.idUsuario = _aTAP.get(position).getI_idUsuario();

        if (position % 2 != 0)
            viewHolder.relativeLayout.setBackgroundColor(convertView.getResources().getColor(R.color.lista));
        else
            viewHolder.relativeLayout.setBackgroundColor(convertView.getResources().getColor(R.color.transparente));

        viewHolder.toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked) {
                    // The toggle is enabled

                    tabAlertaPOJO.setI_idalerta(viewHolder.idAlerta);
                    tabAlertaPOJO.setI_estado(0);


                    cambiarEstadoAlerta(tabAlertaPOJO);
                    changeSelectedPositions(position);


                } else {
                    // The toggle is disabled

                    tabAlertaPOJO.setI_idalerta(viewHolder.idAlerta);
                    tabAlertaPOJO.setI_estado(1);
                    tabAlertaPOJO.setI_idSesion(viewHolder.idUsuario);


                    cambiarEstadoAlerta(tabAlertaPOJO);


                    changeSelectedPositions(position);
                }
            }
        });


        viewHolder.tvNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = _c.getSharedPreferences("DATOS", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                Log.e(TAG, "setOnClickListener" + String.valueOf(tabAlertaPOJO.getI_idalerta()) + " idu " + tabAlertaPOJO.getI_idUsuario() + " ses" + tabAlertaPOJO.getI_idSesion());

                Intent i = new Intent(_c.getApplicationContext(), Alerta.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                viewHolder.tvNombre.setTextColor(_c.getResources().getColor(R.color.azulVioleta));
                viewHolder.imageView.setImageDrawable(_c.getResources().getDrawable(R.drawable.ic_arrow_pressed));

                editor.putInt("idAlerta", tabAlertaPOJO.getI_idalerta());
                editor.putInt("idSesion", tabAlertaPOJO.getI_idSesion());
                editor.putInt("idUsuario", tabAlertaPOJO.getI_idUsuario());
                editor.putString(_c.getResources().getString(R.string.modo), _c.getString(R.string.update));
                editor.apply();
                viewHolder.imageView.setImageDrawable(_c.getResources().getDrawable(R.drawable.ic_arrow));
                _c.startActivity(i);

            }
        });

        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = _c.getSharedPreferences("DATOS", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                Log.e(TAG, "setOnClickListener" + String.valueOf(tabAlertaPOJO.getI_idalerta()) + " idu " + tabAlertaPOJO.getI_idUsuario() + " ses" + tabAlertaPOJO.getI_idSesion());

                Intent i = new Intent(_c.getApplicationContext(), Alerta.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                viewHolder.tvNombre.setTextColor(_c.getResources().getColor(R.color.azulVioleta));
                viewHolder.imageView.setImageDrawable(_c.getResources().getDrawable(R.drawable.ic_arrow_pressed));


                editor.putInt("idAlerta", tabAlertaPOJO.getI_idalerta());
                editor.putInt("idSesion", tabAlertaPOJO.getI_idSesion());
                editor.putInt("idUsuario", tabAlertaPOJO.getI_idUsuario());
                editor.putString(_c.getResources().getString(R.string.modo), _c.getString(R.string.update));
                editor.apply();
                viewHolder.imageView.setImageDrawable(_c.getResources().getDrawable(R.drawable.ic_arrow));
                _c.startActivity(i);


            }
        });


        return convertView;
    }

    /**
     * @param tabAlertaPOJO
     */
    private void cambiarEstadoAlerta(AlertaPOJO tabAlertaPOJO) {

        final AlertaPOJO alerta = new AlertaPOJO(tabAlertaPOJO.getI_idalerta(), tabAlertaPOJO.getI_idUsuario(), tabAlertaPOJO.getI_estado());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, _c.getString(R.string.sUrl_updateAlerta), new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {


                Log.e(getClass().getName(), "cambiarEstadoAlerta " + s);

                int success;

                try {
                    JSONObject json = new JSONObject(s);
                    success = json.getInt(_c.getResources().getString(R.string.success));
                    Utilidades u = new Utilidades();

                    if (success == 1) {

                        if (alerta.getI_estado() == 0) {
                            u.errorConsultaBBDD(_c, _c.getString(R.string.alertaActivada));
                        } else
                            u.errorConsultaBBDD(_c, _c.getString(R.string.alertaDesactivada));
                    } else {
                        u.errorConsultaBBDD(_c, _c.getString(R.string.noActualizaAlerta));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(getClass().getName(), e.getMessage());
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Log.e(getClass().getName(), volleyError.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                return alerta.formatoIdAlerta();
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(_c);
        requestQueue.add(stringRequest);
    }

    static class ViewHolder {

        public RelativeLayout relativeLayout;
        CustomFontTextView tvNombre;
        Switch toggleButton;
        ImageView imageView;
        int idAlerta;
        int idUsuario;
    }


}
