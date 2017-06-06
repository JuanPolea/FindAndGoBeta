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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.findandgoapp.activity.R;
import com.findandgoapp.custom.CustomFontTextView;
import com.findandgoapp.pojo.ComentarioPOJO;
import com.findandgoapp.pojo.EventoPOJO;
import com.findandgoapp.tools.BordesRedondos;
import com.findandgoapp.tools.Utilidades;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.Map;


public class FragmentDialog extends DialogFragment {


    private static final java.lang.String ARG_PARAM1 = "comentario";
    private static final java.lang.String ARG_PARAM2 = "idSesion";

    private static int idSesion = 0;

    private Button buttonOK, buttonCancel;
    private ListView list;
    private EventoPOJO evento;
    private NoticeDialogListener mListener;


    public static FragmentDialog newInstance(EventoPOJO eventoSeleccionadoPOJO, int idSesion) {
        FragmentDialog fragmentDialog = new FragmentDialog();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, eventoSeleccionadoPOJO);
        args.putSerializable(ARG_PARAM2, idSesion);
        fragmentDialog.setArguments(args);
        return fragmentDialog;
    }


    public interface NoticeDialogListener {
        void onDialogPositiveClick(int res);

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
        View v = inflater.inflate(R.layout.fragment_dialog_2_botones, null);
        builder.setView(v);

        setButtonOK((Button) v.findViewById(R.id.bAddComent));
        setButtonCancel((Button) v.findViewById(R.id.bCancela));
        list = ((ListView) v.findViewById(R.id.listaMenuComentarios));
        //builder.setMessage(R.string.sMenuComentarios);

        evento = i.getParcelableExtra(ARG_PARAM1);
        idSesion = i.getIntExtra(ARG_PARAM2, 0);


        ListAdapterDialogo listAdapter = new ListAdapterDialogo(getActivity(), evento.getLl_comentario());
        getList().setAdapter(listAdapter);
        addListenerToListView(getActivity(), list, evento);
        listAdapter.notifyDataSetChanged();


        getButtonCancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        getButtonOK().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (evento.getI_estado() == 0) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                    LayoutInflater inflater = getActivity().getLayoutInflater();
                    final View dialogView = inflater.inflate(R.layout.fragment_comentario, null);

                    builder.setView(dialogView);

                    Button bEnviar = (Button) dialogView.findViewById(R.id.idBAddComentario);
                    Button bCancelar = (Button) dialogView.findViewById(R.id.idBCancelaAddComentario);
                    final EditText editText = (EditText) dialogView.findViewById(R.id.idEtAddComentario);


                    final AlertDialog dialogo = builder.create();

                    bCancelar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialogo.dismiss();


                        }
                    });

                    bEnviar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String sComentario = editText.getText().toString();


                            ComentarioPOJO comentarioPOJO = new ComentarioPOJO();

                            comentarioPOJO.setIdEvento(evento.getI_idEvento());
                            comentarioPOJO.setIdUsuario(idSesion);
                            comentarioPOJO.setComentario(sComentario);
                            comentarioPOJO.setModo(0);

                            Log.e(getClass().getName(), "bEnviar.setOnClickListener: " + comentarioPOJO.getIdUsuario() + "|" + comentarioPOJO.getIdEvento() + "|" + comentarioPOJO.getComentario());


                            insertComentarios(evento, comentarioPOJO);


                            CustomFontTextView customFontTextView = (CustomFontTextView) getActivity().findViewById(R.id.idTvNumDeComentario);
                            Log.e(getClass().getName(), "NumComentarios " + customFontTextView.getText().toString());

                            int res = Integer.parseInt(customFontTextView.getText().toString());

                            mListener.onDialogPositiveClick(res + 1);

                            dialogo.dismiss();


                        }
                    });


                    dialogo.show();
                } else {
                    Utilidades utilidades = new Utilidades();
                    utilidades.mensajeAlertDialog(getActivity(), getResources().getString(R.string.sUsuarioBloqueado));
                }
            }
        });
        return builder.create();

    }

    /**
     *
     */
    private void insertComentarios(final EventoPOJO evento, final ComentarioPOJO comentarioPOJO) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_insertComentario),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Log.e(getClass().getName(), "Insert Comentarios " + response);

                        int success;
                        try {
                            JSONObject json = new JSONObject(response);
                            success = json.getInt(getString(R.string.success));
                            if (success == 1) {


                                ComentarioPOJO comentarioPOJO1 = new ComentarioPOJO();
                                comentarioPOJO1.setNombreUsuario(json.getString(getString(R.string.nombre)));
                                comentarioPOJO1.setComentario(json.getString(getString(R.string.comentarioInicial)));
                                comentarioPOJO1.setIdEvento(evento.getI_idEvento());
                                comentarioPOJO1.setIdUsuario(idSesion);
                                comentarioPOJO1.setModo(0);
                                comentarioPOJO1.setIdComentario(json.getInt(getString(R.string.sIdComentario)));


                                evento.getLl_comentario().addFirst(comentarioPOJO1);

                                ListAdapterDialogo listAdapter = new ListAdapterDialogo(getActivity(), evento.getLl_comentario());
                                getList().setAdapter(listAdapter);
                                addListenerToListView(getActivity(), list, evento);


                            } else {
                                Utilidades utilidades = new Utilidades();
                                utilidades.errorConsultaBBDD(getActivity(), getString(R.string.comentarioNoInsertado));
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
                        Log.e(getClass().getName(), error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //Adding parameters to request


                return comentarioPOJO.toArray();
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mListener = (NoticeDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement NoticeDialogListener");
        }


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

                    if (eventoSeleccionadoPOJO.getLl_comentario().get(position).getIdUsuario() == idSesion || idSesion == 1) {

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
                                    mListener.onDialogPositiveClick(eventoSeleccionadoPOJO.getLl_comentario().size());
                                    dialog.dismiss();
                                }
                            });

                            bEnviar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


                                    eventoSeleccionadoPOJO.getLl_comentario().get(position).setComentario(editText.getText().toString());

                                    ComentarioPOJO comentarioPOJO = new ComentarioPOJO();

                                    comentarioPOJO.setIdEvento(eventoSeleccionadoPOJO.getI_idEvento());
                                    comentarioPOJO.setIdUsuario(idSesion);

                                    comentarioPOJO.setIdComentario(eventoSeleccionadoPOJO.getLl_comentario().get(position).getIdComentario());
                                    comentarioPOJO.setNombreUsuario(eventoSeleccionadoPOJO.getLl_comentario().get(position).getNombreUsuario());

                                    comentarioPOJO.setComentario(editText.getText().toString());

                                    Log.e(getClass().getName(), "AddListener->setOnClickListener: " + comentarioPOJO.getIdUsuario() + "|" + comentarioPOJO.getIdEvento() + "|" + comentarioPOJO.getComentario());
                                    ComentarioFragment updateComentario = new ComentarioFragment();

                                    updateComentario.updateComentario(getActivity(), comentarioPOJO);

                                    eventoSeleccionadoPOJO.getLl_comentario().remove(position);
                                    eventoSeleccionadoPOJO.getLl_comentario().add(position, comentarioPOJO);
                                    dialog.dismiss();
                                    onDestroy();
                                    ListAdapterDialogo listAdapter = new ListAdapterDialogo(getActivity(), eventoSeleccionadoPOJO.getLl_comentario());
                                    getList().setAdapter(listAdapter);
                                    addListenerToListView(getActivity(), list, evento);

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


                                    eventoSeleccionadoPOJO.getLl_comentario().remove(position);
                                    deleteComentario(eventoSeleccionadoPOJO, comentarioPOJO);
                                    Log.e(getClass().getName(), "bEliminar.setOnClickListener: " + comentarioPOJO.getIdUsuario() + "|" + comentarioPOJO.getIdEvento() + "|" + comentarioPOJO.getComentario());


                                    mListener.onDialogPositiveClick(eventoSeleccionadoPOJO.getLl_comentario().size());

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

                Log.e(getClass().getName(), listView.getItemAtPosition(position).toString());
                Log.e(getClass().getName(), eventoSeleccionadoPOJO.getI_idUsuario() + eventoSeleccionadoPOJO.getS_nombreUsuarioComenta() + eventoSeleccionadoPOJO);

            }

        });
    }


    /**
     *
     */
    private class ListAdapterDialogo extends BaseAdapter {

        final Context _c;
        final LinkedList<ComentarioPOJO> linkedList;

        public ListAdapterDialogo(Context c, LinkedList<ComentarioPOJO> list) {

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

            final int pos = position;
            if (convertView == null) {


                viewHolder = new ViewHolder();
                LayoutInflater vi = (LayoutInflater) _c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.fragment_comentario_evento_seleccionado, null);

                convertView.setTag(viewHolder);
            } else {

                viewHolder = (ViewHolder) convertView.getTag();

            }
            viewHolder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.LayoutComentarios);
            viewHolder.tvNombre = (TextView) convertView.findViewById(R.id.idTvComenta);
            viewHolder.tvDesc = (TextView) convertView.findViewById(R.id.idTvComentario);
            viewHolder.ivFotoComentario = (ImageView) convertView.findViewById(R.id.ivFotoComentario);
            String url = getString(R.string.sRutaImagenes) + String.valueOf(linkedList.get(position).getIdUsuario()) + getString(R.string.sFormatoPerfil);

            if (position % 2 == 0)
                viewHolder.relativeLayout.setBackgroundColor(convertView.getResources().getColor(R.color.gris));


            launchPicasso(getActivity(), viewHolder.ivFotoComentario, url);

            viewHolder.tvNombre.setText(String.valueOf(linkedList.get(position).getNombreUsuario()));
            viewHolder.tvDesc.setBackgroundColor(_c.getResources().getColor(R.color.transparente));

            viewHolder.tvDesc.setText(linkedList.get(position).getComentario());
            viewHolder.tvDesc.setTextColor(Color.BLACK);

            viewHolder.tvNombre.setBackgroundColor(_c.getResources().getColor(R.color.transparente));
            viewHolder.ivFotoComentario.setClickable(true);
            viewHolder.ivFotoComentario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectCreador(linkedList.get(pos).getIdUsuario(), linkedList.get(pos).getNombreUsuario());
                }
            });
            return convertView;
        }


        private class ViewHolder {
            TextView tvNombre;
            TextView tvDesc;
            ImageView ivFotoComentario;

            RelativeLayout relativeLayout;
        }
    }

    /**
     * @param i_idUsuario
     * @param nombre
     */
    private void selectCreador(final int i_idUsuario, final String nombre) {


        final EventoPOJO eventoPOJO = new EventoPOJO();
        eventoPOJO.setI_idUsuario(i_idUsuario);
        eventoPOJO.setS_creador(nombre);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getString(R.string.sUrl_selectInformeCreador),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e(getClass().getName(), "selectCreador " + response);

                        int success;
                        JSONObject jsonObject;
                        try {

                            jsonObject = new JSONObject(response);
                            success = jsonObject.getInt(getString(R.string.success));

                            if (success == 1) {


                                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());

                                LayoutInflater inflater = getActivity().getLayoutInflater();
                                View dialogView = inflater.inflate(R.layout.fragment_creador_informe, null);
                                builder.setView(dialogView);
                                builder.setTitle(nombre);
                                builder.setCancelable(true);

                                ImageView bCancelar = (ImageView) dialogView.findViewById(R.id.ivCreadorCancela);
                                ImageView ivFoto = (ImageView) dialogView.findViewById(R.id.ivCreador);
                                String url = getString(R.string.sRutaImagenes) + String.valueOf(i_idUsuario) + getString(R.string.sFormatoPerfil);

                                launchPicasso(getActivity(), ivFoto, url);


                                CustomFontTextView numValoraciones = (CustomFontTextView) dialogView.findViewById(R.id.tvCreadorNumValoraciones);
                                CustomFontTextView numPenalizaciones = (CustomFontTextView) dialogView.findViewById(R.id.tvCreadorNumPenalizaciones);
                                CustomFontTextView numEventos = (CustomFontTextView) dialogView.findViewById(R.id.tvCreadorNumEventos);
                                CustomFontTextView descripcion = (CustomFontTextView) dialogView.findViewById(R.id.tvDescripcionTexto);

                                CustomFontTextView categoria = (CustomFontTextView) dialogView.findViewById(R.id.tvCategoria);
                                CustomFontTextView provincia = (CustomFontTextView) dialogView.findViewById(R.id.tvProvincia);
                                CustomFontTextView categoriadesc = (CustomFontTextView) dialogView.findViewById(R.id.tvCategoriaDescripcion);
                                CustomFontTextView provinciadesc = (CustomFontTextView) dialogView.findViewById(R.id.tvProvinciaDescripcion);


                                JSONObject valoraciones = new JSONObject();
                                valoraciones = jsonObject.getJSONObject(getString(R.string.result));
                                int suma = 0;

                                suma += valoraciones.getInt("idMotivo1");
                                suma += valoraciones.getInt("idMotivo2");
                                suma += valoraciones.getInt("idMotivo3");
                                numPenalizaciones.setText(String.valueOf(suma));

                                suma = valoraciones.getInt("idMotivo4");
                                numValoraciones.setText(String.valueOf(suma));

                                int creados;
                                creados = jsonObject.getInt(getString(R.string.creados));
                                numEventos.setText(String.valueOf(creados));

                                int tipoUsuario = jsonObject.getInt(getString(R.string.tipoUsuario));
                                if (tipoUsuario == 4) {
                                    descripcion.setText(getString(R.string.sAsistente));
                                    categoriadesc.setVisibility(View.INVISIBLE);
                                    provinciadesc.setVisibility(View.INVISIBLE);
                                    categoria.setVisibility(View.INVISIBLE);
                                    provincia.setVisibility(View.INVISIBLE);
                                } else {
                                    if (tipoUsuario == 3) {
                                        descripcion.setText(getString(R.string.sArtista));
                                    } else
                                        descripcion.setText(jsonObject.getString(getString(R.string.tipoEmpresa)));

                                    categoriadesc.setText(jsonObject.getString(getString(R.string.idCategoria)));
                                    provinciadesc.setText(jsonObject.getString(getString(R.string.sidProvincia)));
                                }


                                final android.app.AlertDialog dialoge = builder.create();

                                bCancelar.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        dialoge.dismiss();
                                    }
                                });

                                dialoge.show();
                            } else if (success == 2) {
                                Utilidades ut = new Utilidades();
                                ut.mensajeAlertDialog(getActivity(), getString(R.string.noInforme));

                            } else {
                                Utilidades ut = new Utilidades();
                                ut.mensajeAlertDialog(getActivity(), getString(R.string.ErrorInforme));

                            }
                        } catch (JSONException e1) {
                            e1.printStackTrace();
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


                return eventoPOJO.formatoIdEventoidUsuario(getActivity());
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    /**
     * @param evento
     * @param comentarioPOJO
     */
    private void deleteComentario(final EventoPOJO evento, final ComentarioPOJO comentarioPOJO) {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_deleteComentario),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e(getClass().getName(), "Delete Comentarios " + response);


                        ListAdapterDialogo listAdapter = new ListAdapterDialogo(getActivity(), evento.getLl_comentario());
                        getList().setAdapter(listAdapter);
                        addListenerToListView(getActivity(), list, evento);
                       /* Utilidades utilidades = new Utilidades();
                        utilidades.errorConsultaBBDD(getActivity(), getActivity().getResources().getString(R.string.sEliminarComentario));
                        Intent intent = new Intent(getActivity(), EventoSeleccionado.class);

                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.DATOS), Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putInt(getResources().getString(R.string.sIdSesion), idSesion);
                        editor.putInt(getResources().getString(R.string.sEvento), evento.getI_idEvento());
                        editor.putInt(getResources().getString(R.string.sIdUsuario), evento.getI_idUsuario());
                        editor.putBoolean(getResources().getString(R.string.sFotoNueva), true);
                        editor.apply();

                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                       // getActivity().finish();
                        //startActivity(intent);*/


                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        Log.e(getClass().getName(), error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                return comentarioPOJO.toArray();
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }


    /**
     * @param context
     * @param image
     * @param file
     */
    private static void launchPicasso(Context context, ImageView image, String file) {


        Picasso.
                with(context).
                load(file)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .transform(new BordesRedondos(300, 0))
                .error(R.drawable.btn_artista)
                .placeholder(R.drawable.btn_usuario)
                .into(image);


        Utilidades utilidades = new Utilidades();
        utilidades.clearImageDiskCache(context.getApplicationContext());

        int ancho = (int) context.getResources().getDimension(R.dimen.fotoAnchoComentario);
        int alto = (int) context.getResources().getDimension(R.dimen.fotoAltoComentario);
        image.getLayoutParams().height = ancho;
        image.getLayoutParams().width = alto;
    }

    private Button getButtonOK() {
        return buttonOK;
    }

    private void setButtonOK(Button buttonOK) {
        this.buttonOK = buttonOK;
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
    public void onDestroy() {
        super.onDestroy();
    }
}