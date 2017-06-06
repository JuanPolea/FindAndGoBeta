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
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.findandgoapp.activity.AdminUsuarioPenalizar;
import com.findandgoapp.activity.R;
import com.findandgoapp.custom.CustomFontTextView;
import com.findandgoapp.pojo.PenalizacionPOJO;
import com.findandgoapp.tools.Utilidades;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Juan F. Mateos
 */
public class ListAdapterPenalizacion extends BaseAdapter {

    private static final String TAG = "ListAdapterPenalizacion";
    private final Context _c;
    private final ArrayList<PenalizacionPOJO> _alPenalizaPOJO;
    private final LinkedList<Integer> selectedIndeces = new LinkedList<>();


    public ListAdapterPenalizacion(ArrayList<PenalizacionPOJO> aTEP, Context c) {
        _alPenalizaPOJO = aTEP;
        _c = c;
        Log.i(TAG, "Constructor");

    }


    public int getCount() {
        // TODO Auto-generated method stub

        return _alPenalizaPOJO.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub

        return _alPenalizaPOJO.get(position);
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

        Log.i(TAG, "getView");

        final ViewHolder viewHolder;


        if (convertView == null) {


            LayoutInflater vi = (LayoutInflater) _c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.fragment_penalizacion_listado, null);
            viewHolder = new ViewHolder();
            viewHolder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.rlPenalizaciones);
            viewHolder.tvNombreUsuario = (CustomFontTextView) convertView.findViewById(R.id.idTvNombreUsuarioPenalizado);
            viewHolder.tvPenalizacion = (CustomFontTextView) convertView.findViewById(R.id.idTvDescripcionPenalizacion);
            viewHolder.tvMotivo = (CustomFontTextView) convertView.findViewById(R.id.idTvMotivo);
            viewHolder.tvFecha = (CustomFontTextView) convertView.findViewById(R.id.idTvFecha);
            viewHolder.ivBloqueo = (ImageView) convertView.findViewById(R.id.idIvUsuarioPenalizado);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvNombreUsuario.setText(_alPenalizaPOJO.get(position).getUsuario());
        viewHolder.tvNombreUsuario.setTextColor(Color.BLUE);
        viewHolder.tvPenalizacion.setText(_alPenalizaPOJO.get(position).getPenalizacion());
        viewHolder.tvPenalizacion.setTextColor(Color.BLACK);
        viewHolder.tvMotivo.setText(_alPenalizaPOJO.get(position).getDescripcion());
        viewHolder.tvMotivo.setTextColor(Color.BLACK);
        viewHolder.tvFecha.setText(_alPenalizaPOJO.get(position).getFecha());
        viewHolder.tvFecha.setTextColor(Color.RED);

        viewHolder.idUsuario = _alPenalizaPOJO.get(position).getIdUsuario();

        viewHolder.ivBloqueo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.e(TAG, "onClick");
                deletePenalizacion(v.getContext(), _alPenalizaPOJO.get(position));

                _alPenalizaPOJO.remove(position);
                notifyDataSetChanged();


            }
        });

        if (position % 2 == 0)
            viewHolder.relativeLayout.setBackgroundColor(convertView.getResources().getColor(R.color.lista));
        else
            viewHolder.relativeLayout.setBackgroundColor(convertView.getResources().getColor(R.color.transparente));

        return convertView;
    }

    static class ViewHolder {

        private CustomFontTextView tvNombreUsuario;
        private CustomFontTextView tvFecha;
        private CustomFontTextView tvPenalizacion;
        private CustomFontTextView tvMotivo;
        private ImageView ivBloqueo;

        public RelativeLayout relativeLayout;
        public int idUsuario;
    }

    /**
     *
     */
    private void deletePenalizacion(Context context, final PenalizacionPOJO penalizacionPOJO) {

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, context.getString(R.string.sUrl_deletePenalizacionUsuario),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Utilidades utilidades = new Utilidades();
                        Log.e(getClass().getName(), "selectDatosUsuarioPenalizados " + response);


                        try {


                            JSONObject json = new JSONObject(response);

                            utilidades.errorConsultaBBDD(_c.getApplicationContext(), json.getString("mensaje"));

                            if (getCount() == 0) {

                                SharedPreferences sharedPreferences = _c.getSharedPreferences("DATOS", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();


                                Intent i = new Intent(_c.getApplicationContext(), AdminUsuarioPenalizar.class);

                                editor.putInt("idUsuario", penalizacionPOJO.getIdUsuario());
                                editor.putInt("idSesion", 1);

                                editor.apply();
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                _c.startActivity(i);


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();


                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                return penalizacionPOJO.formatoIdUsuarioIdPenalizacion();
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }


}