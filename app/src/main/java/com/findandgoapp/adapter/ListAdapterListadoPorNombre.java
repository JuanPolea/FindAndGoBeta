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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.findandgoapp.activity.AdminUsuarioPenalizar;
import com.findandgoapp.activity.R;
import com.findandgoapp.pojo.UsuarioPOJO;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by juanpolea on 20/10/16.
 */

public class ListAdapterListadoPorNombre extends BaseAdapter {


    private ArrayList<UsuarioPOJO> _aTUP = new ArrayList<>();
    private final Context _c;
    private final LinkedList<Integer> selectedIndeces = new LinkedList<>();

    public ListAdapterListadoPorNombre(ArrayList<UsuarioPOJO> aTUP, Context c) {
        _aTUP = aTUP;
        _c = c;

    }


    public int getCount() {
        // TODO Auto-generated method stub
        Log.i("getCount", String.valueOf(_aTUP.size()));
        return _aTUP.size();

    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        Log.i("getItem", String.valueOf(_aTUP.get(position)));
        return _aTUP.get(position);
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        Log.i("getItem", String.valueOf(position));
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

        ViewHolder viewHolder;


        Log.e(getClass().getName(), "estado--> " + _aTUP.get(position).getS_nombre());


        if (convertView == null) {

            LayoutInflater vi = (LayoutInflater) _c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.fragment_usuario_por_nombre_item_list, null);

            viewHolder = new ListAdapterListadoPorNombre.ViewHolder();

            viewHolder.tvNombreUsuario = (TextView) convertView.findViewById(R.id.idTvUsuarioListado);
            viewHolder.relativeLayout = ((RelativeLayout) convertView.findViewById(R.id.fragment_listado_por_nombre));
            viewHolder.ivBloqueo = (ImageView) convertView.findViewById(R.id.idIvNextUsuarioListado);


            convertView.setTag(viewHolder);
        } else {

            viewHolder = (ListAdapterListadoPorNombre.ViewHolder) convertView.getTag();
        }


        viewHolder.tvNombreUsuario.setText(_aTUP.get(position).getS_nombre());


        viewHolder.tvNombreUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = _c.getSharedPreferences(_c.getApplicationContext().getResources().getString(R.string.sDATOS), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();


                Intent i = new Intent(_c.getApplicationContext(), AdminUsuarioPenalizar.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                editor.putInt(_c.getApplicationContext().getResources().getString(R.string.sIdUsuario), _aTUP.get(position).getI_idUsuario());
                editor.apply();

                _c.startActivity(i);
            }
        });

        viewHolder.ivBloqueo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = _c.getSharedPreferences(_c.getApplicationContext().getResources().getString(R.string.sDATOS), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();


                Intent i = new Intent(_c.getApplicationContext(), AdminUsuarioPenalizar.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                editor.putInt(_c.getApplicationContext().getResources().getString(R.string.sIdUsuario), _aTUP.get(position).getI_idUsuario());
                editor.apply();

                _c.startActivity(i);
            }
        });

        if (position % 2 == 0)
            viewHolder.relativeLayout.setBackgroundColor(convertView.getResources().getColor(R.color.lista));
        else
            viewHolder.relativeLayout.setBackgroundColor(convertView.getResources().getColor(R.color.transparente));


        return convertView;
    }

    static class ViewHolder {


        private TextView tvNombreUsuario;
        private ImageView ivBloqueo;


        public RelativeLayout relativeLayout;
    }


}