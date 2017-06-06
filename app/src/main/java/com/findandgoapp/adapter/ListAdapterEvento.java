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
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.findandgoapp.activity.R;
import com.findandgoapp.custom.CustomFontTextView;
import com.findandgoapp.pojo.EventoPOJO;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Juan F Mateos Redondo on 17/03/15.
 */
public class ListAdapterEvento extends BaseAdapter {

    private static final String TAG = "ListAdapterEvento";
    private final Context _c;
    private final ArrayList<EventoPOJO> _aTEP;
    private final LinkedList<Integer> selectedIndeces = new LinkedList<>();

    public ListAdapterEvento(ArrayList<EventoPOJO> aTEP, Context c) {
        _aTEP = aTEP;
        _c = c;
        //Log.e(TAG, _aTEP.get(0).getS_nombreEvento());

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
            convertView = vi.inflate(R.layout.fragment_evento_menu, null);
            viewHolder = new ViewHolder();

            viewHolder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.rlEventoMenu);
            viewHolder.tvEventoNombre = (CustomFontTextView) convertView.findViewById(R.id.idTvEventoMenuNombre);
            viewHolder.tvEventoCategoria = (CustomFontTextView) convertView.findViewById(R.id.idTvEventoMenuCategoria);
            viewHolder.tvEventoClasificacion = (CustomFontTextView) convertView.findViewById(R.id.idTvEventoMenuClasificacion);
            viewHolder.tvEventoTipo = (CustomFontTextView) convertView.findViewById(R.id.idTvEventoMenuTipo);
            viewHolder.tvEventoCiudad = (CustomFontTextView) convertView.findViewById(R.id.idTvEventoMenuCiudad);
            viewHolder.tvEventoFecha = (CustomFontTextView) convertView.findViewById(R.id.idTvEventoMenuFecha);
            viewHolder.ivFotoEvento = (ImageView) convertView.findViewById(R.id.idIvEventoMenuImagen);
            viewHolder.tvNumConfirma = (TextView) convertView.findViewById(R.id.TvNumValidados);
            viewHolder.tvNumDenuncia = (TextView) convertView.findViewById(R.id.TvNumDenunciados);
            viewHolder.tvNumComenta = (TextView) convertView.findViewById(R.id.TvNumComment);
            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();


        }

        //Log.e(TAG, _aTEP.get(position).getS_nombreEvento() + " pos: " + String.valueOf(position));


        viewHolder.tvEventoNombre.setText(_aTEP.get(position).getS_nombreEvento());
        viewHolder.tvEventoNombre.setTextColor(Color.BLACK);
        viewHolder.tvEventoCategoria.setText(_aTEP.get(position).getS_categoria());
        viewHolder.tvEventoCategoria.setTextColor(Color.BLACK);
        viewHolder.tvEventoClasificacion.setText(_aTEP.get(position).getS_clasificacion());
        viewHolder.tvEventoClasificacion.setTextColor(Color.BLACK);
        viewHolder.tvEventoTipo.setText(_aTEP.get(position).getS_tipo());
        viewHolder.tvEventoTipo.setTextColor(Color.BLACK);
        viewHolder.tvEventoCiudad.setText(_aTEP.get(position).getS_localidad());
        viewHolder.tvEventoCiudad.setTextColor(Color.BLACK);
        viewHolder.tvEventoFecha.setText(_aTEP.get(position).getS_fecha());
        viewHolder.tvEventoFecha.setTextColor(Color.BLACK);
        viewHolder.ivFotoEvento.setImageDrawable(setBorder(_aTEP.get(position).getI_categoria()));

        viewHolder.idEvento = _aTEP.get(position).getI_idEvento();
        viewHolder.tvNumConfirma.setText(String.valueOf(_aTEP.get(position).getI_numConfirma()));
        viewHolder.tvNumDenuncia.setText(String.valueOf(_aTEP.get(position).getI_numDenuncia()));
        viewHolder.tvNumComenta.setText(String.valueOf(_aTEP.get(position).getI_numComenta()));

        if (position % 2 == 0)
            viewHolder.relativeLayout.setBackgroundColor(convertView.getResources().getColor(R.color.lista));
        else
            viewHolder.relativeLayout.setBackgroundColor(convertView.getResources().getColor(R.color.transparente));

        return convertView;
    }

    private Drawable setBorder(int position) {


        if (position == 1) {
            return _c.getResources().getDrawable(R.drawable.borde_musica);
        } else if (position == 2) {
            return _c.getResources().getDrawable(R.drawable.borde_audiovisual);
        } else if (position == 3) {
            return _c.getResources().getDrawable(R.drawable.borde_escena);
        } else if (position == 4) {
            return _c.getResources().getDrawable(R.drawable.borde_literatura);
        } else if (position == 5) {
            return _c.getResources().getDrawable(R.drawable.borde_arte);
        } else if (position == 6) {
            return _c.getResources().getDrawable(R.drawable.borde_deporte);
        } else if (position == 7) {
            return _c.getResources().getDrawable(R.drawable.borde_juegos);
        } else if (position == 8) {
            return _c.getResources().getDrawable(R.drawable.borde_social);
        } else {
            return _c.getResources().getDrawable(R.drawable.borde_otro);
        }
    }

    static class ViewHolder {

        private CustomFontTextView tvEventoNombre;
        private CustomFontTextView tvEventoCategoria;
        private CustomFontTextView tvEventoClasificacion;
        private CustomFontTextView tvEventoTipo;
        private CustomFontTextView tvEventoFecha;
        private CustomFontTextView tvEventoCiudad;

        private ImageView ivFotoEvento;
        private TextView tvNumConfirma;
        private TextView tvNumDenuncia;
        private TextView tvNumComenta;
        public RelativeLayout relativeLayout;


        public int idEvento;
    }


}