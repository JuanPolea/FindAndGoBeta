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
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.findandgoapp.activity.R;
import com.findandgoapp.custom.CustomFontTextView;

import java.util.LinkedList;

/**
 * Created by Juan F. Mateos
 */
public class ListAdapterInformacion extends BaseAdapter {


    private final Context _c;
    private final LinkedList<Integer> _linkedList;
    private final LinkedList<Integer> selectedIndeces = new LinkedList<>();

    public ListAdapterInformacion(Context c, LinkedList<Integer> linkedList, int tipo) {
        _linkedList = linkedList;
        _c = c;


    }

    public int getCount() {
        // TODO Auto-generated method stub

        Log.e("getCount", String.valueOf(_linkedList.size()));
        return _linkedList.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub

        Log.e("getItem", String.valueOf(_linkedList.get(position)));
        return _linkedList.get(position);
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
            convertView = vi.inflate(R.layout.fragment_informacion, null);
            viewHolder = new ViewHolder();
            viewHolder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.fragment_informacion);
            viewHolder.tvTitulo = (CustomFontTextView) convertView.findViewById(R.id.tvInfo);
            viewHolder.tvContador = (CustomFontTextView) convertView.findViewById(R.id.tvContadorInfo);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.ivNextI);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        if (position == 0) {
            viewHolder.tvTitulo.setText(_c.getResources().getString(R.string.sInteracciones));
            viewHolder.tvContador.setText(String.valueOf(_linkedList.get(position)));

        }
        if (position == 1) {
            viewHolder.tvTitulo.setText(_c.getResources().getString(R.string.sSancion));
            viewHolder.tvContador.setText(String.valueOf(_linkedList.get(position)));


        }

        if (position == 2) {

            viewHolder.tvTitulo.setText(_c.getResources().getString(R.string.sReferencias));
            viewHolder.tvContador.setText(String.valueOf(_linkedList.get(position)));

        }

        viewHolder.tvTitulo.setTextColor(ContextCompat.getColor(_c, R.color.link));

        if (position % 2 == 0)
            viewHolder.relativeLayout.setBackgroundColor(convertView.getResources().getColor(R.color.lista));
        else
            viewHolder.relativeLayout.setBackgroundColor(convertView.getResources().getColor(R.color.transparente));


        return convertView;
    }

    static class ViewHolder {


        private RelativeLayout relativeLayout;
        private com.findandgoapp.custom.CustomFontTextView tvTitulo;
        private CustomFontTextView tvContador;
        public ImageView imageView;
    }

}