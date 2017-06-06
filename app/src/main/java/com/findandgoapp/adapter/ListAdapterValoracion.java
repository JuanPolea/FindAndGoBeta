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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.findandgoapp.activity.R;
import com.findandgoapp.custom.CustomFontTextView;
import com.findandgoapp.pojo.NotificacionPOJO;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Juan F. Mateos
 */
public class ListAdapterValoracion extends BaseAdapter {


    private final Context _c;
    private final LinkedList<Integer> selectedIndeces = new LinkedList<>();
    private ArrayList<NotificacionPOJO> _aNotificacionPOJO = new ArrayList<>();

    public ListAdapterValoracion(ArrayList<NotificacionPOJO> aNotificacionPOJO, Context c) {
        _aNotificacionPOJO = aNotificacionPOJO;
        _c = c;
    }

    public int getCount() {
        // TODO Auto-generated method stub

        Log.e("getCount", String.valueOf(_aNotificacionPOJO.size()));
        return _aNotificacionPOJO.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub

        Log.e("getItem", String.valueOf(_aNotificacionPOJO.get(position)));
        return _aNotificacionPOJO.get(position);
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


        Log.e(getClass().getName(), position + " " + _aNotificacionPOJO.get(position).getPuntuacionPOJO().getsMotivo() + " " + _aNotificacionPOJO.size());
        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) _c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.fragment_notificacion, null);
            viewHolder = new ViewHolder();
            viewHolder.tvNotificacion = (TextView) convertView.findViewById(R.id.tvNotificacion);
            viewHolder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.rlNotificacion);
            viewHolder.tvContador = (CustomFontTextView) convertView.findViewById(R.id.tvContadorNotificacion);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (_aNotificacionPOJO.get(position).getComentarioPOJO().getNombreUsuario() != null) {
            viewHolder.tvNotificacion.setText(_aNotificacionPOJO.get(position).getComentarioPOJO().getNombreUsuario() +
                    _c.getResources().getString(R.string.hacomentado) + _aNotificacionPOJO.get(position).getComentarioPOJO().getNombreEvento());
            viewHolder.tvNotificacion.setTextColor(Color.BLACK);
        }

        if (_aNotificacionPOJO.get(position).getPenalizacionPOJO().getDescripcion() != null) {
            viewHolder.tvNotificacion.setText(_c.getResources().getString(R.string.hasidopenalizado) + _aNotificacionPOJO.get(position).getPenalizacionPOJO().getFecha() +
                    ".\n" + _c.getResources().getString(R.string.Motivo) + _aNotificacionPOJO.get(position).getPenalizacionPOJO().getDescripcion())
            ;
            viewHolder.tvNotificacion.setTextColor(Color.BLACK);
            viewHolder.tvContador.setText(_aNotificacionPOJO.get(position).getContador());
        }

        if (_aNotificacionPOJO.get(position).getPuntuacionPOJO().getsMotivo() != null) {
            viewHolder.tvNotificacion.setText(_aNotificacionPOJO.get(position).getPuntuacionPOJO().getsNombreEvento());
            viewHolder.tvNotificacion.setTextColor(convertView.getResources().getColor(R.color.link));
            viewHolder.tvContador.setText(String.valueOf(_aNotificacionPOJO.get(position).getContador()));
        }

        if (position % 2 != 0)
            viewHolder.relativeLayout.setBackgroundColor(convertView.getResources().getColor(R.color.lista));
        else
            viewHolder.relativeLayout.setBackgroundColor(convertView.getResources().getColor(R.color.transparente));


        return convertView;
    }

    static class ViewHolder {

        private TextView tvNotificacion;
        private RelativeLayout relativeLayout;
        private CustomFontTextView tvContador;
    }

}