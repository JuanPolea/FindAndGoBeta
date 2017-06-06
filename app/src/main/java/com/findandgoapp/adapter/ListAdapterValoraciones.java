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
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.findandgoapp.activity.R;
import com.findandgoapp.custom.CustomFontTextView;
import com.findandgoapp.pojo.NotificacionPOJO;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Juan F Mateos Redondo on 17/03/15.
 */
public class ListAdapterValoraciones extends BaseAdapter {


    private final Context _c;
    private final ArrayList<NotificacionPOJO> _aTEP;
    private final LinkedList<Integer> selectedIndeces = new LinkedList<>();

    public ListAdapterValoraciones(ArrayList<NotificacionPOJO> aTEP, Context c) {
        _aTEP = aTEP;
        _c = c;
        Log.e(getClass().getName(), _aTEP.get(0).getPuntuacionPOJO().getsNombreEvento());

    }


    public int getCount() {
        // TODO Auto-generated method stub

        return _aTEP.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub

        return _aTEP.get(position);
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


        if (convertView == null) {

            LayoutInflater vi = (LayoutInflater) _c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.fragment_valoracion, null);
            viewHolder = new ViewHolder();

            viewHolder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.rlEventoValor);
            viewHolder.tvNotificacion = (CustomFontTextView) convertView.findViewById(R.id.idTvEventoValorNombre);
            viewHolder.ivFotoEvento = (ImageView) convertView.findViewById(R.id.idIvFlechaEventoValor);
            viewHolder.tvContador = (CustomFontTextView) convertView.findViewById(R.id.tvContadorNotificacion);
            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();


        }

        Log.e(getClass().getName(), _aTEP.get(position).getPuntuacionPOJO().getsNombreEvento() + " pos: " + String.valueOf(position));


        viewHolder.tvNotificacion.setText(_aTEP.get(position).getPuntuacionPOJO().getsNombreEvento());
        viewHolder.tvNotificacion.setTextColor(Color.BLACK);


        if (_aTEP.get(position).getComentarioPOJO().getNombreUsuario() != null) {
            viewHolder.tvNotificacion.setText(_aTEP.get(position).getComentarioPOJO().getNombreUsuario() +
                    _c.getResources().getString(R.string.hacomentado) + _aTEP.get(position).getComentarioPOJO().getNombreEvento());
            viewHolder.tvNotificacion.setTextColor(Color.BLACK);
        }

        if (_aTEP.get(position).getPenalizacionPOJO().getDescripcion() != null) {
            viewHolder.tvNotificacion.setText(_c.getResources().getString(R.string.hasidopenalizado) + _aTEP.get(position).getPenalizacionPOJO().getFecha() +
                    ".\n" + _c.getResources().getString(R.string.Motivo) + _aTEP.get(position).getPenalizacionPOJO().getDescripcion())
            ;
            viewHolder.tvNotificacion.setTextColor(Color.BLACK);
            viewHolder.tvContador.setText(_aTEP.get(position).getContador());
        }

        if (_aTEP.get(position).getPuntuacionPOJO().getsMotivo() != null) {
            Log.e(getClass().getName(), _aTEP.get(position).getPuntuacionPOJO().getsNombreEvento());
            viewHolder.tvNotificacion.setText(_aTEP.get(position).getPuntuacionPOJO().getsNombreEvento());
            viewHolder.tvNotificacion.setTextColor(convertView.getResources().getColor(R.color.link));
            viewHolder.tvContador.setText(String.valueOf(_aTEP.get(position).getContador()));
            viewHolder.tvNotificacion.setTextSize(20);
        }
        if (position % 2 != 0)
            viewHolder.relativeLayout.setBackgroundColor(convertView.getResources().getColor(R.color.lista));
        else
            viewHolder.relativeLayout.setBackgroundColor(convertView.getResources().getColor(R.color.transparente));

        return convertView;
    }


    static class ViewHolder {

        public RelativeLayout relativeLayout;
        public ImageView ivFotoEvento;
        private CustomFontTextView tvNotificacion;
        private CustomFontTextView tvContador;
    }


}