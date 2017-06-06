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
import com.findandgoapp.pojo.EventoPOJO;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Juan F. Mateos
 */
public class ListAdapterAdminEventoListado extends BaseAdapter {

    private final Context _c;
    private final ArrayList<EventoPOJO> _aTEP;
    private final LinkedList<Integer> selectedIndeces = new LinkedList<>();

    public ListAdapterAdminEventoListado(ArrayList<EventoPOJO> aTEP, Context c) {
        _aTEP = aTEP;
        _c = c;

    }


    public int getCount() {
        // TODO Auto-generated method stub
        Log.i("getCount", String.valueOf(_aTEP.size()));
        return _aTEP.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        // Log.i("getItem", String.valueOf(_aTEP.get(position)));
        return _aTEP.get(position);
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        Log.i("getItemId", String.valueOf(position));
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
            Log.d("ConverView", "NULL");

            LayoutInflater vi = (LayoutInflater) _c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.fragment_tab_admin_evento_listado, null);

            viewHolder = new ViewHolder();
            viewHolder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.fragment_tab_admin_evento_listado);
            viewHolder.tvEventoNombre = (TextView) convertView.findViewById(R.id.tvTabAdminEventoListadoNombre);
            viewHolder.tvEventoNombre.setTextColor(_c.getResources().getColor(R.color.negro));

            viewHolder.imprime();
            convertView.setTag(viewHolder);
        } else {
            Log.d("ConverView", "NOT NULL" + "-> Position ->" + String.valueOf(position));
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.imprime();


        }

        viewHolder.tvEventoNombre.setText(_aTEP.get(position).getS_nombreEvento());
        viewHolder.imprime();
        if (position % 2 == 0)
            viewHolder.relativeLayout.setBackgroundColor(convertView.getResources().getColor(R.color.lista));
        else
            viewHolder.relativeLayout.setBackgroundColor(convertView.getResources().getColor(R.color.transparente));

        return convertView;
    }

    static class ViewHolder {

        private TextView tvEventoNombre;
        public RelativeLayout relativeLayout;

        public void imprime() {
            Log.d("VIIEWHOLDER", "nombre" + tvEventoNombre.getText().toString());
        }


    }


}
