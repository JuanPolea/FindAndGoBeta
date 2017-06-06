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
public class ListAdapterNotificaciones extends BaseAdapter {


    private final Context _c;
    private final ArrayList<NotificacionPOJO> _aNotificacionPOJO;
    private final LinkedList<Integer> selectedIndeces = new LinkedList<>();

    public ListAdapterNotificaciones(ArrayList<NotificacionPOJO> aNotificacionPOJO, Context c) {
        _aNotificacionPOJO = aNotificacionPOJO;
        _c = c;
    }

    public int getCount() {
        // TODO Auto-generated method stub


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
        final NotificacionPOJO notificacionPOJO = _aNotificacionPOJO.get(position);


        if (convertView == null) {

            LayoutInflater vi = (LayoutInflater) _c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.fragment_notificaciones, null);
            viewHolder = new ViewHolder();
            viewHolder.tvNombreEvento = (TextView) convertView.findViewById(R.id.tvNotificacion);
            viewHolder.tvNumConfirmaciones = (CustomFontTextView) convertView.findViewById(R.id.tvNumConfirmaciones);
            viewHolder.tvNumPenalizaciones = (CustomFontTextView) convertView.findViewById(R.id.tvNumPenalizaciones);
            viewHolder.tvNumComentarios = (CustomFontTextView) convertView.findViewById(R.id.tvNumComentarios);

            viewHolder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.fragment_notificaciones);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        Log.e(getClass().getName(), notificacionPOJO.getNombreEvento() + " -> " + notificacionPOJO.getTipo());
        if (notificacionPOJO.getTipo() == 0) {
            viewHolder.tvNombreEvento.setText(notificacionPOJO.getNombreEvento());
            viewHolder.tvNombreEvento.setTextColor(convertView.getResources().getColor(R.color.link));
            viewHolder.tvNumComentarios.setText(String.valueOf(notificacionPOJO.getNumComentarios()));
            viewHolder.tvNumComentarios.setTextColor(convertView.getResources().getColor(R.color.link));
            viewHolder.tvNumConfirmaciones.setText(String.valueOf(notificacionPOJO.getNumConfirma()));
            viewHolder.tvNumConfirmaciones.setTextColor(convertView.getResources().getColor(R.color.link));
            viewHolder.tvNumPenalizaciones.setText(String.valueOf(notificacionPOJO.getNumDenuncias()));
            viewHolder.tvNumPenalizaciones.setTextColor(convertView.getResources().getColor(R.color.link));


        } else {
            viewHolder.tvNombreEvento.setText(notificacionPOJO.getNombreEvento());
            viewHolder.tvNombreEvento.setTextColor(convertView.getResources().getColor(R.color.link));

        }

        if (position % 2 != 0)
            viewHolder.relativeLayout.setBackgroundColor(convertView.getResources().getColor(R.color.lista));
        else
            viewHolder.relativeLayout.setBackgroundColor(convertView.getResources().getColor(R.color.transparente));

        return convertView;
    }

    static class ViewHolder {


        CustomFontTextView tvNumPenalizaciones;
        private TextView tvNombreEvento;
        private CustomFontTextView tvNumConfirmaciones;
        private RelativeLayout relativeLayout;
        private CustomFontTextView tvNumComentarios;

    }

}