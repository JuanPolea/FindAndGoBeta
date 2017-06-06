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

/**
 * Created by juanpolea on 1/04/17.
 * <p>
 * This file is part of FindAndGoApp.
 * <p>
 * FindAndGoApp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * FindAndGoApp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

import com.findandgoapp.activity.R;
import com.findandgoapp.custom.CustomFontTextView;
import com.findandgoapp.pojo.SugerenciaPOJO;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Juan F. Mateos
 */
public class ListAdapterSugerencia extends BaseAdapter {

    private final Context _c;
    private final ArrayList<SugerenciaPOJO> _aTEP;
    private final LinkedList<Integer> selectedIndeces = new LinkedList<>();

    public ListAdapterSugerencia(ArrayList<SugerenciaPOJO> aTEP, Context c) {
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
            convertView = vi.inflate(R.layout.fragment_sugerencia_item_list, null);

            viewHolder = new ViewHolder();
            viewHolder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.fragment_sugerencia_item);
            viewHolder.tvSancion = (CustomFontTextView) convertView.findViewById(R.id.idTvSugerencia);
            viewHolder.cont = (CustomFontTextView) convertView.findViewById(R.id.idTvSugerenciaCont);

            viewHolder.imprime();
            convertView.setTag(viewHolder);
        } else {
            Log.d("ConverView", "NOT NULL" + "-> Position ->" + String.valueOf(position));
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.imprime();


        }

        if (_aTEP.get(position).getTipo() == 1) {

            viewHolder.tvSancion.setText(_c.getString(R.string.Categoria).concat(_c.getString(R.string.dospuntos)).concat(_aTEP.get(position).getTexto()));

        } else if (_aTEP.get(position).getTipo() == 2) {

            String[] posiciones = _c.getResources().getStringArray(R.array.categoria);
            String categoria = posiciones[_aTEP.get(position).getIdCategoria()];

            viewHolder.tvSancion.setText(categoria.concat(_c.getString(R.string.dospuntos)).concat(_aTEP.get(position).getTexto()));

        } else if (_aTEP.get(position).getTipo() == 3) {

            viewHolder.tvSancion.setText(_c.getString(R.string.sala).concat(_c.getString(R.string.dospuntos)).concat(_aTEP.get(position).getTexto()));

        } else if (_aTEP.get(position).getTipo() == 4) {

            viewHolder.tvSancion.setText(_c.getString(R.string.sElijaVia).concat(_c.getString(R.string.dospuntos)).concat(_aTEP.get(position).getTexto()));

        }

        viewHolder.cont.setText(String.valueOf(_aTEP.get(position).getCount()));
        viewHolder.imprime();

        if (position % 2 == 0)
            viewHolder.relativeLayout.setBackgroundColor(convertView.getResources().getColor(R.color.lista));
        else
            viewHolder.relativeLayout.setBackgroundColor(convertView.getResources().getColor(R.color.transparente));


        return convertView;
    }

    static class ViewHolder {

        public RelativeLayout relativeLayout;
        private CustomFontTextView cont;
        private CustomFontTextView tvSancion;

        public void imprime() {
            Log.d("VIIEWHOLDER", "nombre" + tvSancion.getText().toString());
        }


    }


}