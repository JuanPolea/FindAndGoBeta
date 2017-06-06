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

package com.findandgoapp.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.findandgoapp.activity.R;
import com.findandgoapp.custom.CustomFontTextView;
import com.findandgoapp.pojo.ComentarioPOJO;
import com.findandgoapp.pojo.EventoPOJO;
import com.findandgoapp.pojo.PenalizacionPOJO;
import com.findandgoapp.tools.BordesRedondos;
import com.findandgoapp.tools.Utilidades;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;

/**
 * Created by Juan F. Mateos
 */


public class FragmentDialogPenalizaciones extends DialogFragment {


    private static final java.lang.String ARG_PARAM1 = "penalizacion";
    private static final java.lang.String ARG_PARAM2 = "idSesion";

    private static int idSesion = 0;
    private Button buttonOK, buttonCancel;
    private ListView list;
    private NoticeDialogListener mListener;


    public static FragmentDialogPenalizaciones newInstance(EventoPOJO eventoSeleccionadoPOJO, int idSesion) {
        FragmentDialogPenalizaciones fragmentDialog = new FragmentDialogPenalizaciones();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, eventoSeleccionadoPOJO);
        args.putSerializable(ARG_PARAM2, idSesion);
        fragmentDialog.setArguments(args);
        return fragmentDialog;
    }

    private Button getButtonCancel() {
        return buttonCancel;
    }

    private void setButtonCancel(Button buttonCancel) {
        this.buttonCancel = buttonCancel;
    }

    private ListView getList() {
        return list;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();


        Intent i = getActivity().getIntent();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View v = inflater.inflate(R.layout.fragment_dialog, null);
        builder.setView(v);
        setButtonCancel((Button) v.findViewById(R.id.bCancela));
        list = ((ListView) v.findViewById(R.id.listaMenuPenalizaciones));
        //builder.setMessage(R.string.sMenuComentarios);

        EventoPOJO evento = i.getParcelableExtra(ARG_PARAM1);
        idSesion = i.getIntExtra(ARG_PARAM2, 0);


        ListAdapterDialogo listAdapter = new ListAdapterDialogo(getActivity(), evento.getLl_penalizacion());
        getList().setAdapter(listAdapter);
        addListenerToListView(v.getContext(), list, evento);
        listAdapter.notifyDataSetChanged();


        getButtonCancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return builder.create();

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);


    }

    /**
     * @param context
     * @param listView
     * @param eventoSeleccionadoPOJO
     */
    private void addListenerToListView(final Context context, final ListView listView, final EventoPOJO eventoSeleccionadoPOJO) {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                if (eventoSeleccionadoPOJO.getI_estado() == 0) {
                    Log.e(getClass().getName(), "idUS " + eventoSeleccionadoPOJO.getLl_comentario().get(position).getIdUsuario() +
                            " ses " + idSesion + " MODO " +
                            eventoSeleccionadoPOJO.getLl_comentario().get(position).getModo());

                    if (eventoSeleccionadoPOJO.getLl_comentario().get(position).getIdUsuario() == idSesion) {

                        if (eventoSeleccionadoPOJO.getLl_comentario().get(position).getModo() == 0) {

                            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);

                            LayoutInflater inflater = getActivity().getLayoutInflater();
                            View dialogView = inflater.inflate(R.layout.fragment_comentario_modifica, null);
                            builder.setView(dialogView);
                            CustomFontTextView tvMensaje = (CustomFontTextView) dialogView.findViewById(R.id.idTvAddComen);

                            tvMensaje.setClickable(true);

                            Button bEnviar = (Button) dialogView.findViewById(R.id.idBAddComen);
                            Button bCancelar = (Button) dialogView.findViewById(R.id.idBCancelaAddComen);
                            Button bEliminar = (Button) dialogView.findViewById(R.id.idBDeleteComen);

                            final EditText editText = (EditText) dialogView.findViewById(R.id.idEtAddComen);

                            editText.setText(eventoSeleccionadoPOJO.getLl_comentario().get(position).getComentario());

                            final android.app.AlertDialog dialog = builder.create();

                            bCancelar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });

                            bEnviar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


                                    ComentarioPOJO comentarioPOJO = new ComentarioPOJO();

                                    comentarioPOJO.setIdEvento(eventoSeleccionadoPOJO.getI_idEvento());
                                    comentarioPOJO.setIdUsuario(idSesion);

                                    comentarioPOJO.setIdComentario(eventoSeleccionadoPOJO.getLl_comentario().get(position).getIdComentario());


                                    comentarioPOJO.setComentario(editText.getText().toString());

                                    Log.e(getClass().getName(), "bEnviar.setOnClickListener: " + comentarioPOJO.getIdUsuario() + "|" + comentarioPOJO.getIdEvento() + "|" + comentarioPOJO.getComentario());


                                    ComentarioFragment updateComentario = new ComentarioFragment();
                                    updateComentario.updateComentario(getActivity(), comentarioPOJO);
                                    dialog.dismiss();


                                }
                            });
                            bEliminar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String sComentario = editText.getText().toString();

                                    ComentarioPOJO comentarioPOJO = new ComentarioPOJO();

                                    comentarioPOJO.setIdEvento(eventoSeleccionadoPOJO.getI_idEvento());
                                    comentarioPOJO.setIdUsuario(idSesion);
                                    comentarioPOJO.setComentario(sComentario);
                                    comentarioPOJO.setIdComentario(eventoSeleccionadoPOJO.getLl_comentario().get(position).getIdComentario());

                                    Log.e(getClass().getName(), "bEliminar.setOnClickListener: " + comentarioPOJO.getIdUsuario() + "|" + comentarioPOJO.getIdEvento() + "|" + comentarioPOJO.getComentario());

                                    /*
                                    ComentarioFragment.DeleteComentario deleteComentario = new ComentarioFragment.DeleteComentario(context, comentarioPOJO);
                                    deleteComentario.execute();
                                     */


                                    dialog.dismiss();
                                }
                            });


                            dialog.show();
                        } else {
                            Utilidades utilidades = new Utilidades();
                            utilidades.mensajeAlertDialog(context, context.getResources().getString(R.string.sComentarioAutogenerado));
                        }
                    } else {
                        Utilidades utilidades = new Utilidades();
                        utilidades.mensajeAlertDialog(context, context.getResources().getString(R.string.sComentarioOtroUsuario));
                    }

                } else {
                    Utilidades utilidades = new Utilidades();
                    utilidades.mensajeAlertDialog(context, context.getResources().getString(R.string.sUsuarioBloqueado));
                }


                Log.e(getClass().getName(), "-------------------");
                Log.e(getClass().getName(), listView.getItemAtPosition(position).toString());
                Log.e(getClass().getName(), eventoSeleccionadoPOJO.getI_idUsuario() + eventoSeleccionadoPOJO.getS_nombreUsuarioComenta() + eventoSeleccionadoPOJO);

            }

        });
    }

    public interface NoticeDialogListener {

    }

    /**
     *
     */
    private class ListAdapterDialogo extends BaseAdapter {

        final Context _c;
        final LinkedList<PenalizacionPOJO> linkedList;

        public ListAdapterDialogo(Context c, LinkedList<PenalizacionPOJO> list) {

            linkedList = list;
            _c = c;
        }

        public int getCount() {
            // TODO Auto-generated method stub
            return linkedList.size();
        }

        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return linkedList.get(position);
        }

        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            final ViewHolder viewHolder;

            if (convertView == null) {

                Log.e(getClass().getName(), "if == null");
                viewHolder = new ViewHolder();
                LayoutInflater vi = (LayoutInflater) _c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.fragment_comentario_evento_seleccionado, null);
                convertView.setTag(viewHolder);
            } else {

                viewHolder = (ViewHolder) convertView.getTag();

            }

            Log.e(getClass().getName(), "ListAdapterDialogo " + linkedList.get(position).getUsuario() + " " + linkedList.get(position).getDescripcion());

            viewHolder.tvNombre = (TextView) convertView.findViewById(R.id.idTvComenta);
            viewHolder.tvDesc = (TextView) convertView.findViewById(R.id.idTvComentario);
            viewHolder.ivFotoComentario = (ImageView) convertView.findViewById(R.id.ivFotoComentario);
            String url = getString(R.string.sRutaImagenes) + String.valueOf(linkedList.get(position).getIdUsuario()) + getString(R.string.sFormatoPerfil);

            launchPicasso(getActivity(), viewHolder.ivFotoComentario, url);


            viewHolder.tvNombre.setText(String.valueOf(linkedList.get(position).getUsuario()));
            viewHolder.tvDesc.setBackgroundColor(_c.getResources().getColor(R.color.transparente));

            viewHolder.tvDesc.setText(linkedList.get(position).getDescripcion());
            viewHolder.tvDesc.setTextColor(Color.BLACK);

            viewHolder.tvNombre.setBackgroundColor(_c.getResources().getColor(R.color.transparente));
            return convertView;
        }

        /**
         * @param context
         * @param image
         * @param file
         */
        public void launchPicasso(Context context, ImageView image, String file) {


            Picasso.
                    with(context).
                    load(file)
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .transform(new BordesRedondos(300, 0))
                    .error(R.drawable.ic_logo_user)

                    .into(image);


            Utilidades utilidades = new Utilidades();
            utilidades.clearImageDiskCache(context.getApplicationContext());

            int ancho = (int) context.getResources().getDimension(R.dimen.fotoAnchoComentario);
            int alto = (int) context.getResources().getDimension(R.dimen.fotoAltoComentario);
            image.getLayoutParams().height = ancho;
            image.getLayoutParams().width = alto;
        }

        private class ViewHolder {
            TextView tvNombre;
            TextView tvDesc;
            ImageView ivFotoComentario;

        }
    }
}