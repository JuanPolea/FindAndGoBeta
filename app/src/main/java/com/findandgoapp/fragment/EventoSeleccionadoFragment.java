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

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.CursorLoader;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.findandgoapp.activity.Configuracion;
import com.findandgoapp.activity.EventoSeleccionado;
import com.findandgoapp.activity.EventoUpdate;
import com.findandgoapp.activity.MapsActivity;
import com.findandgoapp.activity.MenuPrincipal;
import com.findandgoapp.activity.R;
import com.findandgoapp.activity.TabAdmin;
import com.findandgoapp.custom.CustomFontTextView;
import com.findandgoapp.custom.CustomTypeFaceSpan;
import com.findandgoapp.library.MultiPartEntity;
import com.findandgoapp.pojo.ComentarioPOJO;
import com.findandgoapp.pojo.DireccionPOJO;
import com.findandgoapp.pojo.EventoPOJO;
import com.findandgoapp.pojo.PenalizacionPOJO;
import com.findandgoapp.pojo.PuntuacionPOJO;
import com.findandgoapp.tools.BordesRedondos;
import com.findandgoapp.tools.Utilidades;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventoSeleccionadoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EventoSeleccionadoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventoSeleccionadoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int TAKE_FOTO = 137;
    private static final int REQUEST_PERMISSION_SETTING = 101;
    private static final int EXTERNAL_STORAGE_PERMISSION_CONSTANT = 100;
    private static Activity activity;
    private static boolean isImage = false;
    private static String formato = "jpeg";
    private static String urlShare;
    private final EventoPOJO evento = new EventoPOJO();
    private final PuntuacionPOJO puntuacion = new PuntuacionPOJO();
    private CustomFontTextView tv_nombre;
    private CustomFontTextView tv_descripcion;
    private CustomFontTextView tv_categoria;
    private CustomFontTextView tv_clasificacion;
    private CustomFontTextView tv_tipo;
    private CustomFontTextView tv_lugar;
    private CustomFontTextView tv_direccion;
    private CustomFontTextView tv_fecha;
    private CustomFontTextView tv_hora;
    private CustomFontTextView tv_artista;
    private CustomFontTextView tv_precio;
    private CustomFontTextView tv_denuncia;
    private CustomFontTextView tv_result;
    private CustomFontTextView tv_confirmacion;
    private CustomFontTextView tv_numComentario;
    private CustomFontTextView tv_creador;
    private CustomFontTextView tv_nombreUsuarioComenta;
    private ImageView Iv_confirmar;
    private ImageView Iv_denunciar;
    private ImageView iv_comentario;
    private EditText et_comenta;
    private int i_idEvento;
    private int i_idUsuario;
    private int i_estado;
    private DireccionPOJO direccionPOJO = new DireccionPOJO();
    private OnFragmentInteractionListener mListener;
    private FragmentManager fragmentManager;
    private SharedPreferences sharedPreferences;
    private int idSesion;
    private RelativeLayout relativeImages;
    private ComentarioPOJO comentarioPOJO;
    private int idUsuario;
    private int posicion = -1;
    private boolean tipo1 = false;
    private boolean tipo2 = false;
    private String fromFragment;
    private Toolbar toolbar;
    private File destination;
    private ImageView imageview;
    private SharedPreferences permissionStatus;
    private int SIZE_FOTO = 300;

    public EventoSeleccionadoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventoSeleccionadoFragment.
     */
    // TODO: Rename and change types and number of parameters
    private static EventoSeleccionadoFragment newInstance(String param1, String param2) {
        EventoSeleccionadoFragment fragment = new EventoSeleccionadoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * @param context
     * @param image
     * @param file
     */
    public static void launchPicasso(Context context, ImageView image, String file) {


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

    /**
     *
     */
    public static void launchPicasso(Context context, Boolean isImage, ImageView image, String file) {


        if (isImage) {

            try {


                Picasso.
                        with(context).
                        load(file)
                        .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).
                        error(context.getResources().getDrawable(R.drawable.ic_imagen_por_insertar))
                        .into(image);
            } catch (Exception e) {

                e.printStackTrace();

            }

            Utilidades utilidades = new Utilidades();
            utilidades.clearImageDiskCache(context.getApplicationContext());
        } else {
            image.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_logo));
        }

    }

    @Override
    public void onResume() {
        super.onResume();

        imageview = (ImageView) getActivity().findViewById(R.id.toolbarImage);


        ImageDownloadTask imageDownloadTask = new ImageDownloadTask(getContext(), String.valueOf(evento.getI_idEvento())
                , imageview);
        imageDownloadTask.execute();


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getActivity().getSharedPreferences(getString(R.string.DATOS), Context.MODE_PRIVATE);
        evento.setI_idEvento(sharedPreferences.getInt(getString(R.string.sIdEvento), 0));
        fromFragment = sharedPreferences.getString(getString(R.string.fromFragment), getString(R.string.svacio));


        urlShare = getString(R.string.svacio);
        activity = getActivity();
        fragmentManager = getActivity().getSupportFragmentManager();

        sharedPreferences = getActivity().getSharedPreferences(getResources().getString(R.string.sDATOS), Context.MODE_PRIVATE);
        permissionStatus = getActivity().getSharedPreferences(getString(R.string.permissionStatus), MODE_PRIVATE);
        evento.setI_idEvento(sharedPreferences.getInt(getResources().getString(R.string.sIdEvento), 0));

        idSesion = sharedPreferences.getInt(getResources().getString(R.string.sIdSesion), 0);
        idUsuario = sharedPreferences.getInt(getResources().getString(R.string.sIdUsuario), 0);
        fromFragment = sharedPreferences.getString(getResources().getString(R.string.fromFragment), getString(R.string.svacio));
        evento.setI_idUsuario(sharedPreferences.getInt(getResources().getString(R.string.sIdUsuario), 0));
        comentarioPOJO = new ComentarioPOJO();


        final View[] rootView = {inflater.inflate(R.layout.fragment_evento_seleccionado, container, false)};


        try {

            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

            if (toolbar != null) {
                toolbar.setTitle(getString(R.string.title_activity_evento_listado));
                toolbar.setVisibility(View.VISIBLE);
                toolbar.setEnabled(true);

                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //do something you want

                        //int count = getActivity().getSupportFragmentManager().getBackStackEntryCount();


                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        if (isAdded()) {

                            if (fromFragment.equalsIgnoreCase(getString(R.string.fragment_evento_consulta))) {


                                fromFragment = sharedPreferences.getString(getString(R.string.fromFragment), getString(R.string.svacio));

                                toolbar.setTitle(R.string.consultar);

                                fragmentTransaction.replace(R.id.fragment_evento_item_list, new EventoFragment(), getString(R.string.fragment_evento_item_list));

                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                //Log.e(getClass().getName(), "sesion " + i_idSesion + " idUS " + jsonObject.getInt("idUsuario") + " idEv " + jsonObject.getInt("idEvento"));

                                editor.putString(getString(R.string.fromFragment), getString(R.string.fragment_evento_listado));
                                editor.apply();
                                fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();


                            } else {

                            }
                        } else {


                            toolbar.setTitle(R.string.title_activity_menu_ppal);
                            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                            fragmentTransaction.commit();

                            List<Fragment> list = fragmentManager.getFragments();
                            for (final Fragment f : list) {
                                if (f != null) {

                                    f.getActivity().onBackPressed();

                                }

                            }


                        }


                    }
                });
            } else {

            }
        } catch (ExceptionInInitializerError e) {
            e.printStackTrace();

        }

        imageview = (ImageView) getActivity().findViewById(R.id.toolbarImage);
        //ImageDownloadTask imageDownloadTask = new ImageDownloadTask(getContext(), String.valueOf(evento.getI_idEvento()), imageview);
        //imageDownloadTask.execute();
        setTv_descripcion((CustomFontTextView) rootView[0].findViewById(R.id.idTvDescripcionEventoSeleccionado));
        setTv_categoria((CustomFontTextView) rootView[0].findViewById(R.id.idTvCategoriaEventoSeleccionado));
        setTv_clasificacion((CustomFontTextView) rootView[0].findViewById(R.id.idTvClasificacionEventoSeleccionado));
        setTv_tipo((CustomFontTextView) rootView[0].findViewById(R.id.idTvTipoEventoSeleccionado));
        setTv_lugar((CustomFontTextView) rootView[0].findViewById(R.id.idTvLugarEventoSeleccionado));
        setTv_hora((CustomFontTextView) rootView[0].findViewById(R.id.idTvHoraEventoSeleccionado));

        setTv_direccion((CustomFontTextView) rootView[0].findViewById(R.id.idTvDireccionEventoSeleccionado));
        setTv_fecha((CustomFontTextView) rootView[0].findViewById(R.id.idTvFechaEventoSeleccionado));
        setTv_artista((CustomFontTextView) rootView[0].findViewById(R.id.idTvArtistaEventoSeleccionado));
        setTv_precio((CustomFontTextView) rootView[0].findViewById(R.id.idTvPrecioEventoSeleccionado));
        setTv_creador((CustomFontTextView) rootView[0].findViewById(R.id.idTvCreadorEventoSeleccionado));
        //setIv_fotoEvento((ImageView) rootView[0].findViewById(R.id.idIvImagenEventoSeleccionado));


        relativeImages = (RelativeLayout) rootView[0].findViewById(R.id.idRelativeEventoSeleccionado);

        setTv_numComentario((CustomFontTextView) relativeImages.findViewById(R.id.idTvNumDeComentario));

        setTv_confirmacion((CustomFontTextView) relativeImages.findViewById(R.id.idTvNumeroDeConfirmados));
        setTv_denuncia((CustomFontTextView) relativeImages.findViewById(R.id.idTvNumeroDeDenunciados));
        //setlVComentarios((ListView) rootView.findViewById(R.id.lista));

        setIv_confirmar((ImageView) relativeImages.findViewById(R.id.idIvMeGusta));
        setIv_denunciar((ImageView) relativeImages.findViewById(R.id.idIvNoMeGusta));
        setIv_comentario((ImageView) relativeImages.findViewById(R.id.idIvComentarios));


        selectEventoSeleccionado();

        getTv_creador().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectCreador(evento.getI_idUsuario());

            }
        });

        getIv_confirmar().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (evento.getI_estado() == 0) {


                    puntuacion.setIdUsuario(idSesion);
                    puntuacion.setIdEvento(evento.getI_idEvento());
                    puntuacion.setPuntuacion(4);
                    puntuacion.imprime();


                    if (idUsuario == idSesion) {
                        Toast.makeText(getActivity(), getString(R.string.sNoPuedeValorarSusEventos), Toast.LENGTH_SHORT).show();
                    } else
                        updatePuntuacionEvento(0);

                } else {
                    Utilidades utilidades = new Utilidades();
                    utilidades.mensajeAlertDialog(getActivity(), getResources().getString(R.string.sUsuarioBloqueado));
                }
            }
        });
        setHasOptionsMenu(true);

        getIv_comentario().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectComentarios();


            }
        });
        getIv_denunciar().setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {

                                                     if (idUsuario == idSesion) {

                                                         Toast.makeText(getActivity(), getString(R.string.sNoPuedeValorarSusEventos), Toast.LENGTH_SHORT).show();

                                                     } else {

                                                         if (evento.getI_estado() == 0) {


                                                             final String[] sComentario = {"vacio"};

                                                             puntuacion.setIdUsuario(idSesion);
                                                             puntuacion.setIdEvento(evento.getI_idEvento());

                                                             LayoutInflater inflater = LayoutInflater.from(getActivity());
                                                             rootView[0] = inflater.inflate(R.layout.fragment_denuncia, null);
                                                             AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                                                             builder.setTitle(getActivity().getResources().getString(R.string.sMenuDenuncias));
                                                             builder.setView(rootView[0]);

                                                             final AlertDialog dialog = builder.create();


                                                             final Button bEnviar = (Button) rootView[0].findViewById(R.id.idBAddDenuncia);
                                                             Button bCancelar = (Button) rootView[0].findViewById(R.id.idBCancelaAddDenuncia);

                                                             RadioGroup radioGroup = (RadioGroup) rootView[0].findViewById(R.id.idRgDenunciaTipo);

                                                             final RadioButton radioButton = (RadioButton) rootView[0].findViewById(R.id.idRbDenuncia1);
                                                             final RadioButton radioButton2 = (RadioButton) rootView[0].findViewById(R.id.idRbDenuncia2);
                                                             final RadioButton radioButton3 = (RadioButton) rootView[0].findViewById(R.id.idRbDenuncia3);


                                                             bCancelar.setOnClickListener(new View.OnClickListener() {
                                                                 @Override
                                                                 public void onClick(View v) {

                                                                     try {
                                                                         dialog.dismiss();
                                                                     } catch (Throwable throwable) {
                                                                         throwable.printStackTrace();
                                                                     }

                                                                 }
                                                             });

                                                             bEnviar.setOnClickListener(new View.OnClickListener() {
                                                                 @Override
                                                                 public void onClick(View v) {

                                                                     //Se comprueba si el radio button ha sido seleccionado y se mostrará un mensaje usando
                                                                     //un objeto del tipo Toast.

                                                                     if (puntuacion.getPuntuacion() == 0) {
                                                                         Toast.makeText(getActivity(), getString(R.string.sSeleccioneCampo), Toast.LENGTH_SHORT).show();

                                                                     } else if (puntuacion.getPuntuacion() != 2) {


                                                                         ComentarioPOJO comentarioPOJO = new ComentarioPOJO();

                                                                         comentarioPOJO.setIdEvento(evento.getI_idEvento());
                                                                         comentarioPOJO.setIdUsuario(idSesion);
                                                                         comentarioPOJO.setComentario(sComentario[0]);
                                                                         comentarioPOJO.setModo(1);
                                                                         evento.setS_comentario(sComentario[0]);


                                                                         evento.getLl_comentario().add(comentarioPOJO);


                                                                         updatePuntuacionEvento(1);
                                                                         dialog.dismiss();
                                                                     }


                                                                 }
                                                             });

                                                             dialog.show();

                                                             //Insatanciamos el radio group y chequeamos la opción seleccionada para mostrar
                                                             //información acerca del tipo de denuncia que se va a insertar

                                                             radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                                                                 @Override
                                                                 public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                                     // TODO Auto-generated method stub


                                                                     if (checkedId == R.id.idRbDenuncia1) {


                                                                         bEnviar.setBackground(getResources().getDrawable(R.drawable.btn_aceptar));


                                                                         sComentario[0] = getActivity().getString(R.string.sDenunciaSeleccionado1);
                                                                         puntuacion.setPuntuacion(1);
                                                                         puntuacion.setValor(getActivity().getString(R.string.sDenunciaSeleccionado1));


                                                                         if (tipo1) {

                                                                             bEnviar.setBackground(getResources().getDrawable(R.drawable.btn_delete));

                                                                         } else {

                                                                             bEnviar.setBackground(getResources().getDrawable(R.drawable.btn_aceptar));
                                                                         }


                                                                     } else if (checkedId == R.id.idRbDenuncia2) {


                                                                         puntuacion.setPuntuacion(2);


                                                                         final CharSequence[] charSequencesCampos = getResources().getStringArray(R.array.campos);
                                                                         //Create sequence of items

                                                                         AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
                                                                         dialogBuilder.setTitle(getActivity().getResources().getString(R.string.sSeleccioneCampo));
                                                                         dialogBuilder.setItems(charSequencesCampos, new DialogInterface.OnClickListener() {
                                                                             public void onClick(DialogInterface dialog, final int item) {


                                                                                 selectCampoPenalizado(item);


                                                                             }
                                                                         });

                                                                         dialog.dismiss();
                                                                         //Create alert dialog object via builder
                                                                         AlertDialog alertDialogObject = dialogBuilder.create();
                                                                         //Show the dialog
                                                                         alertDialogObject.show();


                                                                     } else if (checkedId == R.id.idRbDenuncia3)

                                                                     {

                                                                         bEnviar.setBackground(getResources().getDrawable(R.drawable.btn_aceptar));


                                                                         puntuacion.setPuntuacion(2);

                                                                         if (tipo2) {

                                                                             bEnviar.setBackground(getResources().getDrawable(R.drawable.btn_delete));

                                                                         } else {

                                                                             bEnviar.setBackground(getResources().getDrawable(R.drawable.btn_aceptar));
                                                                         }


                                                                         sComentario[0] = getActivity().getString(R.string.sDenunciaSeleccionado3);
                                                                         puntuacion.setPuntuacion(3);
                                                                         puntuacion.setValor(getActivity().getString(R.string.sDenunciaSeleccionado3));


                                                                         //dialog.dismiss();
                                                                     }
                                                                 }

                                                             });
                                                         } else

                                                         {
                                                             Utilidades utilidades = new Utilidades();
                                                             utilidades.mensajeAlertDialog(getActivity(), getResources().getString(R.string.sUsuarioBloqueado));
                                                         }
                                                     }

                                                 }
                                             }

        );


        //launchPicasso();

        return rootView[0];
    }

    private void selectCreador(int i_idUsuario) {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_selectInformeCreador),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        int success;
                        JSONObject jsonObject;
                        try {

                            jsonObject = new JSONObject(response);
                            success = jsonObject.getInt(getString(R.string.success));

                            if (success == 1) {


                                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());

                                LayoutInflater inflater = getActivity().getLayoutInflater();

                                View dialogView = inflater.inflate(R.layout.fragment_creador_informe, null);
                                builder.setView(dialogView);
                                builder.setTitle(evento.getS_creador());
                                builder.setCancelable(true);

                                ImageView bCancelar = (ImageView) dialogView.findViewById(R.id.ivCreadorCancela);
                                ImageView ivFoto = (ImageView) dialogView.findViewById(R.id.ivCreador);
                                String url = getString(R.string.sRutaImagenes) + String.valueOf(evento.getI_idUsuario()) + getString(R.string.sFormatoPerfil);

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

                //Adding parameters to request

                return evento.formatoIdEventoidUsuario(getActivity());
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void selectCampoPenalizado(final int item) {


        final String[] sComent = {null};
        posicion = item;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_selectCampoPenalizado),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        int success;
                        JSONObject jsonObject;
                        try {

                            if (isAdded()) {


                                final CharSequence[] charSequencesCampos = getResources().getStringArray(R.array.campos);
                                final String selectedText = charSequencesCampos[item].toString();
                                jsonObject = new JSONObject(response);
                                success = jsonObject.getInt(getString(R.string.success));

                                if (success == 1) {
                                    int flag;
                                    flag = jsonObject.getInt("flag");

                                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());

                                    LayoutInflater inflater = getActivity().getLayoutInflater();

                                    View dialogView = inflater.inflate(R.layout.fragment_comentario_modifica, null);
                                    builder.setView(dialogView);
                                    CustomFontTextView tvMensaje = (CustomFontTextView) dialogView.findViewById(R.id.idTvAddComen);

                                    tvMensaje.setClickable(true);

                                    Button bEnviar = (Button) dialogView.findViewById(R.id.idBAddComen);
                                    Button bCancelar = (Button) dialogView.findViewById(R.id.idBCancelaAddComen);
                                    Button bEliminar = (Button) dialogView.findViewById(R.id.idBDeleteComen);

                                    EditText etComenta = new EditText(getActivity());
                                    etComenta = (EditText) dialogView.findViewById(R.id.idEtAddComen);

                                    if (flag == 0)
                                        etComenta.setText(jsonObject.getString(getString(R.string.valor)));

                                    final android.app.AlertDialog dialoge = builder.create();

                                    bCancelar.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            dialoge.dismiss();
                                        }
                                    });

                                    final EditText finalEtComenta = etComenta;

                                    bEnviar.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            String[] comment = {null};

                                            puntuacion.setValor(finalEtComenta.getText().toString());

                                            final String valor = finalEtComenta.getText().toString();
                                            comment[0] = ComentarioPOJO.insertaComentarioFromEditText(getActivity(), valor, selectedText);
                                            ComentarioPOJO comentarioPOJO = new ComentarioPOJO();

                                            comentarioPOJO.setIdEvento(evento.getI_idEvento());
                                            comentarioPOJO.setIdUsuario(idSesion);
                                            comentarioPOJO.setComentario(comment[0]);
                                            comentarioPOJO.setModo(1);
                                            evento.setS_comentario(comment[0]);

                                            evento.getLl_comentario().add(comentarioPOJO);


                                            updateCampo(posicion, comentarioPOJO);
                                            // updatePuntuacionEvento(2);

                                            dialoge.dismiss();
                                        }
                                    });

                                    bEliminar.setOnClickListener(new View.OnClickListener() {

                                        @Override
                                        public void onClick(View v) {


                                            //addListenerToListView(getActivity(),listView,eventoSeleccionadoPOJO);


                                            puntuacion.setValor(finalEtComenta.getText().toString());

                                            final String valor = finalEtComenta.getText().toString();
                                            sComent[0] = ComentarioPOJO.insertaComentarioFromEditText(getActivity(), valor, selectedText);
                                            ComentarioPOJO comentarioPOJO = new ComentarioPOJO();
                                            comentarioPOJO.setIdEvento(evento.getI_idEvento());
                                            comentarioPOJO.setIdUsuario(idSesion);
                                            comentarioPOJO.setComentario(sComent[0]);
                                            comentarioPOJO.setModo(1);
                                            comentarioPOJO.setIdPenalizacion(getResources().getInteger(R.integer.penalizacionCamposErroneos));

                                            deleteCampo(posicion, comentarioPOJO);
                                            dialoge.dismiss();


                                        }
                                    });


                                    dialoge.show();
                                } else if (success == 2) {
                                    Utilidades ut = new Utilidades();
                                    ut.mensajeAlertDialog(getActivity(), getString(R.string.campoActualizadoPorOtroUsuario));

                                } else {
                                    final EditText edittext = new EditText(getActivity());
                                    edittext.setHint(getActivity().getResources().getString(R.string.sValorDelCampo));

                                    AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

                                    alert.setView(edittext);

                                    alert.setPositiveButton(getActivity().getResources().getString(R.string.sAceptar), new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            String[] comenta = {null};


                                            final String valor = edittext.getText().toString();
                                            comenta[0] = ComentarioPOJO.insertaComentarioFromEditText(getActivity(), valor, selectedText);
                                            evento.setS_comentario(comenta[0]);
                                            puntuacion.setValor(edittext.getText().toString());
                                            setPosicion(item);
                                            updatePuntuacionEvento(2);
                                        }
                                    });

                                    alert.setNegativeButton(getActivity().getResources().getString(R.string.sCancelar), new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            // what ever you want to do with No option.
                                        }
                                    });

                                    alert.show();
                                }
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
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                return formatoCampoPenalizado(item);
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    /**
     * @param _posicion
     * @param comentarioPOJO
     */
    private void deleteCampo(int _posicion, final ComentarioPOJO comentarioPOJO) {

        final int posicion = _posicion;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_deleteCampoPenalizado),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int success;

                            success = jsonObject.getInt(getString(R.string.success));

                            if (success == 1) {

                                EventoSeleccionadoFragment eventoSeleccionadoFragment = EventoSeleccionadoFragment.newInstance(String.valueOf(idSesion), String.valueOf(evento.getI_idEvento()));
                                getActivity().getSupportFragmentManager()
                                        .beginTransaction()
                                        .detach(eventoSeleccionadoFragment)
                                        .attach(eventoSeleccionadoFragment)
                                        .commit();

                                String confirmado = String.valueOf(getTv_denuncia().getText());

                                int cont = Integer.parseInt(confirmado) - 1;
                                getTv_denuncia().setText(String.valueOf(cont));

                                confirmado = String.valueOf(getTv_numComentario().getText());

                                cont = Integer.parseInt(confirmado) - 1;
                                getTv_numComentario().setText(String.valueOf(cont));


                            } else
                                Toast.makeText(getActivity(), getString(R.string.noEliminaPenalizacion), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
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

                //Adding parameters to request

                return formatoDeleteCampo(comentarioPOJO, posicion);
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);


    }

    private Map<String, String> formatoDeleteCampo(ComentarioPOJO comentarioPOJO, int posicion) {

        Map<String, String> map = new HashMap<>();

        map.put("idEvento", String.valueOf(comentarioPOJO.getIdEvento()));
        map.put("idUsuario", String.valueOf(idSesion));
        map.put("puntuacion", String.valueOf(comentarioPOJO.getIdPenalizacion()));
        map.put("posicion", String.valueOf(posicion));
        map.put("modo", String.valueOf(comentarioPOJO.getModo()));

        Log.d(getClass().getName(), "formatoDeleteCampo " + map.toString());
        return map;
    }

    /**
     * @param posicion
     * @return
     */
    private Map<String, String> formatoCampoPenalizado(int posicion) {
        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put(getString(R.string.sIdUsuario), String.valueOf(idSesion));
        hashMap.put(getString(R.string.sIdEvento), String.valueOf(evento.getI_idEvento()));
        hashMap.put(getString(R.string.puntuacion), String.valueOf(puntuacion.getPuntuacion()));

        hashMap.put(getString(R.string.modo), String.valueOf(comentarioPOJO.getModo()));
        hashMap.put(getString(R.string.posicion), String.valueOf(posicion));

        Log.d(getClass().getName(), "formatoCampoPenalizado " + hashMap.toString());
        return hashMap;
    }

    /**
     * @param i i = 1 confirma,
     *          i = 2 denuncia
     */
    private void updatePuntuacionEvento(final int i) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_updatePuntuacionEvento),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONObject json;
                        int success;

                        try {

                            json = new JSONObject(response);


                            success = json.getInt(getString(R.string.success));


                            if (success == 1) {

                                if (i == 0) {
                                    String confirmado = String.valueOf(getTv_confirmacion().getText());
                                    int cont = Integer.parseInt(confirmado) + 1;
                                    getTv_confirmacion().setText(String.valueOf(cont));
                                    Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.sValoracionPositiva), Toast.LENGTH_SHORT).show();

                                } else {

                                    if (puntuacion.getPuntuacion() == getResources().getInteger(R.integer.penalizacionEventoNoExiste)) {
                                        tipo1 = true;
                                    }
                                    if (puntuacion.getPuntuacion() == getResources().getInteger(R.integer.penalizacionCamposErroneos))
                                        tipo2 = true;

                                    String confirmado = String.valueOf(getTv_denuncia().getText());
                                    int cont = Integer.parseInt(confirmado) + 1;
                                    getTv_denuncia().setText(String.valueOf(cont));
                                    confirmado = String.valueOf(getTv_numComentario().getText());
                                    cont = Integer.parseInt(confirmado) + 1;
                                    getTv_numComentario().setText(String.valueOf(cont));
                                    ComentarioPOJO comentarioPOJO = new ComentarioPOJO();
                                    comentarioPOJO.setIdEvento(evento.getI_idEvento());
                                    comentarioPOJO.setIdUsuario(idSesion);
                                    comentarioPOJO.setComentario(evento.getS_comentario());
                                    comentarioPOJO.setIdPenalizacion(json.getInt(getString(R.string.idPenalizacion)));


                                    comentarioPOJO.setModo(1);
                                    insertComentarios(comentarioPOJO);
                                    Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.eventoValorado), Toast.LENGTH_SHORT).show();

                                    EventoSeleccionadoFragment eventoSeleccionadoFragment = EventoSeleccionadoFragment.newInstance(String.valueOf(idSesion), String.valueOf(evento.getI_idEvento()));
                                    getActivity().getSupportFragmentManager()
                                            .beginTransaction()
                                            .detach(eventoSeleccionadoFragment)
                                            .attach(eventoSeleccionadoFragment)
                                            .commit();


                                }


                            } else if (success == 2) {

                                Toast.makeText(getActivity(), getString(R.string.sRectificacion), Toast.LENGTH_SHORT).show();


                                if (i == 0) {

                                    String confirmado = String.valueOf(getTv_confirmacion().getText());

                                    int cont = Integer.parseInt(confirmado) - 1;
                                    getTv_confirmacion().setText(String.valueOf(cont));


                                } else {


                                    if (puntuacion.getPuntuacion() == 1) {
                                        tipo1 = false;
                                    }
                                    if (puntuacion.getPuntuacion() == 3)
                                        tipo2 = false;

                                    String confirmado = String.valueOf(getTv_denuncia().getText());

                                    int cont = Integer.parseInt(confirmado) - 1;
                                    getTv_denuncia().setText(String.valueOf(cont));

                                    confirmado = String.valueOf(getTv_numComentario().getText());

                                    cont = Integer.parseInt(confirmado) - 1;
                                    getTv_numComentario().setText(String.valueOf(cont));


                                    comentarioPOJO.setIdEvento(evento.getI_idEvento());
                                    comentarioPOJO.setIdUsuario(idSesion);
                                    comentarioPOJO.setComentario(evento.getS_comentario());

                                    deleteComentario();


                                }
                            } else if (success == 4) {
                                Toast.makeText(getActivity(), getString(R.string.elEventoContieneDenuncias), Toast.LENGTH_SHORT).show();
                            } else if (success == 5) {
                                Toast.makeText(getActivity(), getString(R.string.elEventoContieneDenuncias), Toast.LENGTH_SHORT).show();
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
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //Adding parameters to request

                return formatoPuntuacion();
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }

    /**
     * @return
     */
    private Map<String, String> formatoPuntuacion() {

        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put(getString(R.string.sIdUsuario), String.valueOf(idSesion));
        hashMap.put(getString(R.string.sIdEvento), String.valueOf(evento.getI_idEvento()));
        hashMap.put(getString(R.string.puntuacion), String.valueOf(puntuacion.getPuntuacion()));
        if (puntuacion.getPuntuacion() == 4)
            hashMap.put(getString(R.string.valor), getString(R.string.confirma));
        else
            hashMap.put(getString(R.string.valor), "denuncia" + puntuacion.getPuntuacion());
        hashMap.put(getString(R.string.modo), String.valueOf(comentarioPOJO.getModo()));
        hashMap.put(getString(R.string.posicion), String.valueOf(getPosicion()));

        Log.d("formatoPuntuacion()", hashMap.toString());
        return hashMap;
    }

    /**
     * @param posicion
     * @param comentarioPOJO
     */
    private void updateCampo(final int posicion, final ComentarioPOJO comentarioPOJO) {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_updateCampo),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONObject json;
                        int success;

                        try {
                            json = new JSONObject(response);


                            success = json.getInt(getString(R.string.success));

                            if (success == 1) {


                                boolean inserta;
                                inserta = json.getBoolean(getString(R.string.inserta));

                                if (inserta) {

                                    String confirmado = String.valueOf(getTv_denuncia().getText());

                                    int cont = Integer.parseInt(confirmado) + 1;
                                    getTv_denuncia().setText(String.valueOf(cont));

                                    confirmado = String.valueOf(getTv_numComentario().getText());

                                    cont = Integer.parseInt(confirmado) + 1;
                                    getTv_numComentario().setText(String.valueOf(cont));
                                    Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.sValoracionNegativa), Toast.LENGTH_SHORT).show();
                                    comentarioPOJO.setIdEvento(evento.getI_idEvento());
                                    comentarioPOJO.setIdUsuario(idSesion);
                                    comentarioPOJO.setComentario(evento.getS_comentario());
                                    comentarioPOJO.setIdPenalizacion(json.getInt(getString(R.string.idPenalizacion)));

                                    comentarioPOJO.setModo(1);
                                    insertComentarios(comentarioPOJO);
                                } else
                                    Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.actualizaCampo), Toast.LENGTH_SHORT).show();


                                EventoSeleccionadoFragment eventoSeleccionadoFragment = EventoSeleccionadoFragment.newInstance(String.valueOf(idSesion), String.valueOf(evento.getI_idEvento()));
                                getActivity().getSupportFragmentManager()
                                        .beginTransaction()
                                        .remove(eventoSeleccionadoFragment)
                                        .add(eventoSeleccionadoFragment, "eventoSeleccionadoFragment")
                                        .commit();


                            } else {
                                Toast.makeText(getActivity(), getString(R.string.noActualizaCampo), Toast.LENGTH_SHORT).show();
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
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                return formatoUpdatePuntuacion(posicion, comentarioPOJO);
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }

    /**
     * @param posicion
     * @param comentarioPOJO
     * @return
     */
    private Map<String, String> formatoUpdatePuntuacion(int posicion, ComentarioPOJO comentarioPOJO) {

        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put(getString(R.string.sIdUsuario), String.valueOf(idSesion));
        hashMap.put(getString(R.string.sIdEvento), String.valueOf(evento.getI_idEvento()));
        hashMap.put(getString(R.string.puntuacion), String.valueOf(puntuacion.getPuntuacion()));
        hashMap.put(getString(R.string.valor), puntuacion.getValor());
        hashMap.put(getString(R.string.posicion), String.valueOf(posicion));
        hashMap.put(getString(R.string.modo), String.valueOf(comentarioPOJO.getModo()));
        hashMap.put(getString(R.string.comentario), comentarioPOJO.getComentario());

        Log.d("formatoUpdatePuntuacion", hashMap.toString());
        return hashMap;
    }

    /**
     * @param llcomentario
     * @param idSesion
     */
    private void showNoticeDialog(EventoPOJO llcomentario, int idSesion) {
        // Create an instance of the dialog fragment and show it
        activity.getIntent().putExtra("comentario", llcomentario);
        activity.getIntent().putExtra("idSesion", idSesion);

        FragmentDialog dialog = FragmentDialog.newInstance(evento, idSesion);
        dialog.show(getActivity().getSupportFragmentManager(), "NoticeDialogFragment");
    }

    /**
     *
     */
    private void showNoticeShare() {
        // Create an instance of the dialog fragment and show it
        activity.getIntent().putExtra("url", urlShare);
        activity.getIntent().putExtra("evento", evento);

        FragmentDialogShare dialog = FragmentDialogShare.newInstance(urlShare, evento);
        dialog.show(getActivity().getSupportFragmentManager(), "showNoticeShare");
    }

    private void selectEventoSeleccionado() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_selectAllEventoSeleccionado),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(getClass().getName(), "selectEventoSeleccionado " + response);

                        JSONObject jsonObject;


                        try {

                            jsonObject = new JSONObject(response);


                            JSONArray jsonArrayDenuncias;
                            JSONArray jsonArray = jsonObject.getJSONArray(getString(R.string.comment));


                            /**
                             * Se inicializan los campos del evento
                             */

                            JSONObject json;


                            String via;
                            int numero;
                            int cp;


                            json = jsonObject.getJSONObject(getString(R.string.result));
                            jsonArrayDenuncias = jsonObject.getJSONArray(getString(R.string.confirmados));

                            evento.setI_idUsuario(Integer.parseInt(json.getString(getString(R.string.sIdUsuario))));

                            evento.setS_nombreEvento(json.getString(getString(R.string.nombreEvento)));
                            evento.setEmailUsuarioSesion(jsonObject.getString(getString(R.string.email)));
                            mListener.setTitle(evento.getS_nombreEvento());


                            evento.setS_descripcion(json.getString(getString(R.string.descripcionEvento)));
                            if (evento.getS_descripcion().isEmpty()) {
                                getTv_descripcion().setText(getString(R.string.noInsertado));
                                getTv_descripcion().setTextColor(getActivity().getResources().getColor(R.color.grisOscuro));
                            } else {
                                getTv_descripcion().setText(json.getString(getString(R.string.descripcionEvento)));

                            }

                            String[] categoria = getActivity().getResources().getStringArray(R.array.categoria);


                            String[] clasificacion = getActivity().getResources().getStringArray(R.array.clasificacion);


                            getTv_clasificacion().setText(clasificacion[json.getInt(getString(R.string.idClasificacion))]);
                            evento.setS_clasificacion(getTv_clasificacion().getText().toString());
                            getTv_categoria().setText(categoria[json.getInt(getString(R.string.sidCategoria))]);
                            evento.setS_categoria(getTv_categoria().getText().toString());

                            String[] tipo = getActivity().getResources().getStringArray(R.array.tipo);
                            getTv_tipo().setText(tipo[json.getInt(getString(R.string.idTipo))]);
                            evento.setS_tipo(getTv_tipo().getText().toString());
                            evento.setS_nombreSala(json.getString(getString(R.string.lugarEvento)));
                            getTv_lugar().setText(evento.getS_nombreSala());

                            getTv_hora().setText(json.getString(getString(R.string.horaEvento)));
                            evento.setS_hora(getTv_hora().getText().toString());

                            evento.setS_localidad(json.getString(getString(R.string.nombreLocalidad)));
                            evento.setS_ciudad(json.getString(getString(R.string.sNombreProvincia)));
                            via = json.getString(getString(R.string.nombreVia));
                            numero = json.getInt(getString(R.string.snumero));
                            cp = json.getInt(getString(R.string.cp));

                            getDireccionPOJO().setLugar(json.getString(getString(R.string.lugarEvento)));
                            getDireccionPOJO().setVia(via);
                            getDireccionPOJO().setNumero(numero);
                            getDireccionPOJO().setCp(cp);
                            getDireccionPOJO().setLocalidad(evento.getS_localidad());
                            getDireccionPOJO().setCiudad(evento.getsCiudad());

                            getDireccionPOJO().setDireccion(via +
                                    " " +
                                    json.getString(getString(R.string.direccionEvento)) +
                                    ", " +
                                    String.valueOf(numero) +
                                    ", " +
                                    String.valueOf(cp) +
                                    ", " +
                                    evento.getS_localidad());

                            getTv_direccion().setText(via +
                                    " " +
                                    json.getString(getString(R.string.direccionEvento)) +
                                    ", " +
                                    String.valueOf(numero) +
                                    ", " +
                                    String.valueOf(cp) +
                                    ", " +
                                    evento.getS_localidad() + ", " +
                                    json.getString(getString(R.string.sNombreProvincia)));


                            evento.setS_fecha(json.getString(getString(R.string.fechaEvento)));
                            getTv_fecha().setText(evento.getS_fecha());

                            String artista = json.getString(getString(R.string.artistaEvento));
                            if (artista.isEmpty()) {
                                getTv_artista().setText(getString(R.string.noInsertado));
                                getTv_artista().setTextColor(getActivity().getResources().getColor(R.color.grisOscuro));
                            } else
                                getTv_artista().setText(json.getString(getString(R.string.artistaEvento)));

                            if (json.getInt(getString(R.string.precioEvento)) == -1) {
                                getTv_precio().setText(getString(R.string.noInsertado));
                                getTv_precio().setTextColor(getActivity().getResources().getColor(R.color.grisOscuro));
                            } else if (json.getInt(getString(R.string.precioEvento)) == 0)
                                getTv_precio().setText(getString(R.string.gratis));
                            else
                                getTv_precio().setText(json.getString(getString(R.string.precioEvento)));
                            evento.setI_idEvento(json.getInt(getString(R.string.sIdEvento)));
                            getTv_creador().setText(json.getString(getString(R.string.nombreUsuario)));
                            evento.setS_creador(json.getString(getString(R.string.nombreUsuario)));

                            int numComentarios = json.getInt(getString(R.string.numComentarios));


                            int i = 0;
                            int confirmados = 0;
                            int denunciados = 0;
                            JSONObject jsonObject1;

                            while (i < jsonArrayDenuncias.length()) {


                                jsonObject1 = jsonArrayDenuncias.getJSONObject(i);

                                if (Integer.parseInt(jsonObject1.getString(getString(R.string.idMotivo))) == 4) {
                                    confirmados = confirmados + Integer.parseInt(jsonObject1.getString(getString(R.string.cont)));
                                } else
                                    denunciados = denunciados + Integer.parseInt(jsonObject1.getString(getString(R.string.cont)));


                                i++;
                            }
                            getTv_confirmacion().setText(String.valueOf(confirmados));
                            getTv_numComentario().setText(String.valueOf(numComentarios));
                            getTv_denuncia().setText(String.valueOf(denunciados));

                            /**
                             *
                             */
                            int cont = 0;

                            int tamano = jsonArray.length();

                            evento.getLl_comentario().clear();

                            while (cont < tamano) {


                                ComentarioPOJO comentarioPOJO = new ComentarioPOJO();
                                comentarioPOJO.setNombreUsuario(jsonArray.getJSONObject(cont).getString(getActivity().getResources().getString(R.string.nombreUsuario)));
                                comentarioPOJO.setComentario(jsonArray.getJSONObject(cont).getString(getActivity().getResources().getString(R.string.comentario)));
                                comentarioPOJO.setIdUsuario(jsonArray.getJSONObject(cont).getInt(getActivity().getResources().getString(R.string.sIdUsuario)));
                                comentarioPOJO.setIdComentario(jsonArray.getJSONObject(cont).getInt(getActivity().getResources().getString(R.string.sIdComentario)));
                                evento.getLl_comentario().add(comentarioPOJO);


                                cont++;
                            }


                            evento.getLl_penalizacion().clear();


                            jsonArrayDenuncias = jsonObject.getJSONArray("penalizaciones");
                            int contador = 0;
                            int longitud = jsonArrayDenuncias.length();
                            tipo1 = jsonObject.getBoolean("tipo1");
                            tipo2 = jsonObject.getBoolean("tipo2");
                            while (contador < longitud) {

                                PenalizacionPOJO penalizacionPOJO = new PenalizacionPOJO();
                                penalizacionPOJO.setIdEvento(jsonArrayDenuncias.getJSONObject(contador).getInt(getString(R.string.sIdEvento)));
                                penalizacionPOJO.setIdUsuario(jsonArrayDenuncias.getJSONObject(contador).getInt("idUsuarioDenuncia"));
                                penalizacionPOJO.setUsuario(jsonArrayDenuncias.getJSONObject(contador).getString("nombreUsuario"));
                                penalizacionPOJO.setDescripcion(jsonArrayDenuncias.getJSONObject(contador).getString("descripcionMotivo"));

                                penalizacionPOJO.setPosicion(jsonArrayDenuncias.getJSONObject(contador).getInt("idPosicion"));
                                penalizacionPOJO.setValor(jsonArrayDenuncias.getJSONObject(contador).getString("valor"));
                                evento.getLl_penalizacion().add(penalizacionPOJO);
                                contador++;
                            }


                            if (denunciados != 0) {

                                if (idUsuario == idSesion) {
                                    showNoticeDialogPenalizaciones(getActivity(), evento, idSesion);


                                } else {
                                    Utilidades utilidades = new Utilidades();
                                    utilidades.mensajeAlertDialog(getActivity(), getActivity().getResources().getString(R.string.sEventoContieneDenuncias));
                                }
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


                return evento.formatoIdEventoByidUsuario(getActivity(), idSesion);
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);


    }

    /**
     *
     */
    private void selectComentarios() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_selectComentario),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        evento.getLl_comentario().clear();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int success;
                            success = jsonObject.getInt(getString(R.string.success));

                            if (success == 1) {
                                JSONArray jsonArray = new JSONArray();
                                jsonArray = jsonObject.getJSONArray(getString(R.string.result));
                                for (int i = 0; i < jsonArray.length(); i++) {

                                    ComentarioPOJO comentarioPOJO = new ComentarioPOJO();
                                    comentarioPOJO.setNombreUsuario(jsonArray.getJSONObject(i).getString(getString(R.string.nombreUsuario)));
                                    comentarioPOJO.setIdUsuario(jsonArray.getJSONObject(i).getInt(getString(R.string.sIdUsuario)));
                                    comentarioPOJO.setIdComentario(jsonArray.getJSONObject(i).getInt(getString(R.string.sIdComentario)));
                                    comentarioPOJO.setModo(jsonArray.getJSONObject(i).getInt(getString(R.string.modo)));
                                    comentarioPOJO.setComentario(jsonArray.getJSONObject(i).getString(getString(R.string.comentario)));
                                    evento.getLl_comentario().add(comentarioPOJO);
                                }
                                showNoticeDialog(evento, idSesion);
                            } else {
                                Toast.makeText(getActivity(), getString(R.string.NoComentarios), Toast.LENGTH_SHORT).show();
                                evento.getLl_comentario().clear();
                                showNoticeDialog(evento, idSesion);
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

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return evento.formatoIdEvento(getActivity());
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }

    /**
     *
     */
    private void deleteComentario() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_deleteComentario),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Utilidades utilidades = new Utilidades();
                        utilidades.errorConsultaBBDD(getActivity(), getActivity().getResources().getString(R.string.sEliminarComentario));
                        Intent intent = new Intent(getActivity(), EventoSeleccionado.class);

                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

                        // getActivity().startActivity(intent);

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

                //Adding parameters to request


                return comentarioPOJO.toArray();
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }

    /**
     *
     */
    private void insertComentarios(final ComentarioPOJO comentarioPOJO) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_insertComentario),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Toast.makeText(getActivity(), getString(R.string.sInsertarComentario), Toast.LENGTH_SHORT).show();

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

                //Adding parameters to request


                return comentarioPOJO.toArray();
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }

    /**
     * @param activity
     * @param _evento
     * @param idSesion
     */
    private void showNoticeDialogPenalizaciones(FragmentActivity activity, EventoPOJO _evento, int idSesion) {
        activity.getIntent().putExtra("penalizacion", _evento);
        activity.getIntent().putExtra("idSesion", idSesion);
        fragmentManager.beginTransaction();

        FragmentDialogPenalizaciones dialog = FragmentDialogPenalizaciones.newInstance(evento, idSesion);
        dialog.show(fragmentManager, "DialogFragmentPenalizacion");
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    Log.e("onMenuOpened...", e.getMessage());
                }
            }
        }
        assert menu != null;
        for (int i = 0; i < menu.size(); i++) {
            MenuItem mi = menu.getItem(i);
            String title = mi.getTitle().toString();
            Spannable newTitle = new SpannableString(title);
            Typeface font = Typeface.createFromAsset(getActivity().getAssets(), getResources().getString(R.string.fontAmaticRegular));
            newTitle.setSpan(new CustomTypeFaceSpan("", font), 0, newTitle.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            mi.setTitle(newTitle);

        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        menu.clear();
        evento.setI_idEvento(sharedPreferences.getInt(getString(R.string.sIdEvento), 0));
        evento.setI_idUsuario(sharedPreferences.getInt(getString(R.string.sIdUsuario), 0));
        evento.setI_estado(sharedPreferences.getInt(getResources().getString(R.string.estado), 0));
        idSesion = sharedPreferences.getInt(getString(R.string.sIdSesion), 0);


        if (idUsuario == idSesion || idSesion == 1) {
            inflater.inflate(R.menu.menu_mi_evento_seleccionado, menu);

        } else {
            inflater.inflate(R.menu.menu_evento_seleccionado, menu);

        }

        MenuItem shareItem = menu.findItem(R.id.menu_item_share);
        ShareActionProvider mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);

        super.onCreateOptionsMenu(menu, inflater);
    }

    /**
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        //noinspection SimplifiableIfStatement
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DATOS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        switch (item.getItemId()) {
            case R.id.idMIComoLlegar:


                Intent i = new Intent(getActivity(), MapsActivity.class);

                editor = sharedPreferences.edit();
                editor.putInt(getString(R.string.sIdSesion), idSesion);
                editor.putInt(getString(R.string.sIdEvento), evento.getI_idEvento());
                editor.putInt(getString(R.string.sIdUsuario), evento.getI_idUsuario());
                editor.putString(getString(R.string.nombreEvento), evento.getS_nombreEvento());
                editor.putString(getString(R.string.direccion), getDireccionPOJO().getDireccion());
                editor.putString(getString(R.string.ciudad), getDireccionPOJO().getCiudad());

                editor.apply();

                startActivity(i);

                return true;

            case R.id.idMIDejaTuComentario:

                if (evento.getI_estado() == 0) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                    LayoutInflater inflater = getActivity().getLayoutInflater();
                    View dialogView = inflater.inflate(R.layout.fragment_comentario, null);

                    builder.setView(dialogView);


                    Button bEnviar = (Button) dialogView.findViewById(R.id.idBAddComentario);
                    Button bCancelar = (Button) dialogView.findViewById(R.id.idBCancelaAddComentario);
                    final EditText editText = (EditText) dialogView.findViewById(R.id.idEtAddComentario);


                    final AlertDialog dialog = builder.create();

                    bCancelar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
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


                            insertComentarios(comentarioPOJO);

                            CustomFontTextView customFontTextView = (CustomFontTextView) getActivity().findViewById(R.id.idTvNumDeComentario);

                            int res = Integer.parseInt(customFontTextView.getText().toString());

                            mListener.onDialogPositive(res + 1);


                            dialog.dismiss();

                        }
                    });


                    dialog.show();
                } else {
                    Utilidades utilidades = new Utilidades();
                    utilidades.mensajeAlertDialog(getActivity(), getResources().getString(R.string.sUsuarioBloqueado));
                }


                return true;
            case R.id.idMIModificarEvento:


                editor.putInt(getResources().getString(R.string.sIdSesion), idSesion);
                editor.putInt(getResources().getString(R.string.sEvento), evento.getI_idEvento());
                editor.putInt(getResources().getString(R.string.sIdUsuario), evento.getI_idUsuario());
                editor.putBoolean(getResources().getString(R.string.sFotoNueva), true);
                editor.apply();


                Intent intent = new Intent(getActivity(), EventoUpdate.class);
                startActivity(intent);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentManager.popBackStack();
                fragmentTransaction.add(new EventoUpdateFragment(), getString(R.string.fragment_evento_update));
                fragmentTransaction.addToBackStack(getString(R.string.fragment_evento_update));
                //fragmentTransaction.commit();


                //mListener.updateEvento(evento);

                return true;

            case R.id.idMIEliminarEvento:


                AlertDialog.Builder builderEliminar = new AlertDialog.Builder(getActivity());
                TextView textView = new TextView(getActivity().getApplicationContext());
                textView.setText(getActivity().getResources().getString(R.string.sWarning));
                textView.setTypeface(Typeface.createFromAsset(getActivity().getApplicationContext().getAssets(), getActivity().getApplicationContext().getResources().getString(R.string.fontAmaticRegular)));

                builderEliminar.setTitle(getResources().getString(R.string.sEliminarEvento));
                textView.setPadding(15, 10, 0, 0);
                textView.setTextSize(getActivity().getApplicationContext().getResources().getDimension(R.dimen.size_15));
                textView.setTextColor(getActivity().getApplicationContext().getResources().getColor(R.color.rojo));

                builderEliminar.setCustomTitle(textView);
                builderEliminar.setIcon(R.drawable.ic_warning);

                builderEliminar.setMessage(getActivity().getResources().getString(R.string.sDeseaEliminarEvento) + " " + evento.getS_nombreEvento());

                builderEliminar.setPositiveButton(getResources().getString(R.string.sAceptar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        deleteEvento();


                    }


                });
                builderEliminar.setNegativeButton(getResources().getString(R.string.sCancelar), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                });

                AlertDialog alert = builderEliminar.create();
                alert.show();
                alert.getWindow().getAttributes();

                Button button = new Button(activity);
                button = alert.getButton(DialogInterface.BUTTON_POSITIVE);
                button.setTextColor(activity.getResources().getColor(R.color.link));
                button.setBackgroundColor(activity.getResources().getColor(R.color.blanco));

                //Preparamos las fuentes personalizadas
                Typeface fontTextoBoton = Typeface.createFromAsset(activity.getAssets(), activity.getResources().getString(R.string.fontAmaticRegularBold));
                button.setTypeface(fontTextoBoton);


                Button buttonCancel = new Button(activity);
                buttonCancel = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
                buttonCancel.setTextColor(activity.getResources().getColor(R.color.blanco));
                buttonCancel.setBackgroundColor(activity.getResources().getColor(R.color.link));
                //Preparamos las fuentes personalizadas
                Typeface fontTextoCancel = Typeface.createFromAsset(activity.getAssets(), activity.getResources().getString(R.string.fontAmaticRegularBold));
                button.setTypeface(fontTextoCancel);

                return true;

            case R.id.idMIFotoEvento:


                Utilidades utilidades = new Utilidades();

                if (utilidades.isExternalStorageAvailable()) {

                    if (Build.VERSION.SDK_INT >= 23) {
                        requestPermission();
                    } else {
                        selectImage();
                    }
                } else {
                    Toast.makeText(getActivity(), getString(R.string.aceptarPermiso), Toast.LENGTH_SHORT).show();
                }

                return true;


            case R.id.action_settings:
                Log.d(getClass().getName(), "Configuracion" + String.valueOf(item.getItemId()));
                i = new Intent(getActivity(), Configuracion.class);
                startActivity(i);
                return true;

            case R.id.menu_item_share:


                showNoticeShare();

                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    /**
     *
     */

    private void requestPermission() {


        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                //Show Information about why you need the permission


                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_CONSTANT);

            } else if (permissionStatus.getBoolean(Manifest.permission.WRITE_EXTERNAL_STORAGE, false)) {
                //Previously Permission Request was cancelled with 'Dont Ask Again',
                // Redirect to Settings after showing Information about why you need the permission
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
                builder.setTitle(getString(R.string.permission_storage));
                builder.setMessage(getString(R.string.almacen));
                builder.setPositiveButton(getString(R.string.sSi), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts(getString(R.string.paquete), getActivity().getPackageName(), null);
                        intent.setData(uri);
                        startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
                        Toast.makeText(getActivity(), getString(R.string.menuPermisos), Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton(getString(R.string.sCancelar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            } else {
                //just request the permission
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_CONSTANT);


            }

            SharedPreferences.Editor editor = permissionStatus.edit();
            editor.putBoolean(Manifest.permission.WRITE_EXTERNAL_STORAGE, true);
            editor.apply();


        } else {
            //You already have the permission, just go ahead.
            selectImage();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        Log.e(getClass().getName(), "request " + requestCode);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case EXTERNAL_STORAGE_PERMISSION_CONSTANT: {
                if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(),
                            getResources().getString(R.string.permission_storage_success),
                            Toast.LENGTH_SHORT).show();
                    selectImage();

                } else {


                    Toast.makeText(getActivity(), getString(R.string.aceptarPermiso), Toast.LENGTH_SHORT).show();


                }

            }
        }
    }

    /**
     *
     */
    private void selectImage() {

        final CharSequence[] items = {getActivity().getResources().getString(R.string.sRealizarFoto),
                getActivity().getResources().getString(R.string.sSeleccionarGaleria),
                getActivity().getResources().getString(R.string.sCancelar)};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getActivity().getResources().getString(R.string.sAddFoto));
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals(getActivity().getResources().getString(R.string.sRealizarFoto))) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, getActivity().getResources().getInteger(R.integer.REQUEST_CAMERA));
                } else if (items[item].equals(getActivity().getResources().getString(R.string.sSeleccionarGaleria))) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, getActivity().getResources().getString(R.string.sSelecionarArchivo)),
                            getActivity().getResources().getInteger(R.integer.SELECT_FILE));
                } else if (items[item].equals(getActivity().getResources().getString(R.string.sCancelar))) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    /*
   * @param requestCode
   * @param resultCode
   * @param data
   */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        try {
            if (resultCode == -1) {
                imageview = (ImageView) getActivity().findViewById(R.id.toolbarImage);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                Bitmap thumbnail;
                if (requestCode == TAKE_FOTO) {

                    thumbnail = (Bitmap) data.getExtras().get("data");
                    assert thumbnail != null;
                    thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

                    File directorio = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/FindAndGo");

                    if (!directorio.exists()) {
                        File wallpaperDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/FindAndGo");
                        wallpaperDirectory.mkdirs();
                    }

                    //StringTokenizer tokenizer = new StringTokenizer()

                    File tmp = new File(Environment.getExternalStoragePublicDirectory((Environment.DIRECTORY_DCIM) + "/FindAndGo"),
                            evento.getI_idEvento() + getActivity().getResources().getString(R.string.sFormatoEvento)
                                    + getActivity().getResources().getString(R.string.punto) + formato);


                    FileOutputStream fo;


                    try {
                        if (tmp.exists())
                            tmp.delete();

                        destination = new File(Environment.getExternalStoragePublicDirectory((Environment.DIRECTORY_DCIM) + "/FindAndGo"),
                                evento.getI_idEvento() + getActivity().getResources().getString(R.string.sFormatoEvento)
                                        + getActivity().getResources().getString(R.string.punto) + formato);

                        destination.createNewFile();
                        fo = new FileOutputStream(destination);
                        fo.write(bytes.toByteArray());
                        fo.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    BitmapFactory.Options options = new BitmapFactory.Options();


                    int scale = 1;
                    while (options.outWidth / scale / 2 >= 150
                            && options.outHeight / scale / 2 >= 150)
                        scale *= 2;
                    options.inSampleSize = scale;


                } else if (requestCode == getActivity().getResources().getInteger(R.integer.SELECT_FILE)) {


                    Uri imageUri = data.getData();
                    String[] projection = {MediaStore.Images.Media.DATA};
                    String selectedImagePath;

                    CursorLoader cursorLoader = new CursorLoader(
                            getActivity(),
                            imageUri, projection, null, null, null);
                    Cursor cursor = cursorLoader.loadInBackground();

                    if (cursor != null) {
                        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        cursor.moveToFirst();
                        selectedImagePath = cursor.getString(column_index);
                        BitmapFactory.Options options = new BitmapFactory.Options();

                        int scale = 1;
                        while (options.outWidth / scale / 2 >= 150
                                && options.outHeight / scale / 2 >= 150)
                            scale *= 2;
                        options.inSampleSize = scale;

                        StringTokenizer stringTokenizer = new StringTokenizer(selectedImagePath, ".");

                        destination = new File(selectedImagePath);

                    }

                }
                try {
                    Utilidades utilidades = new Utilidades();
                    if (utilidades.isExternalStorageAvailable())
                        upload();
                    else {

                        utilidades.mensajeAlertDialog(getActivity().getApplicationContext(), getActivity().getResources().getString(R.string.sNoImagenDownload));
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     *
     */
    private void upload() {
        // Image location URL


        try {
            InputStream is = new FileInputStream(destination);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bao);

            BitmapFactory.Options options = new BitmapFactory.Options();

            int scale = 1;
            while (options.outWidth / scale / 2 >= SIZE_FOTO
                    && options.outHeight / scale / 2 >= SIZE_FOTO)
                scale *= 2;
            options.inSampleSize = scale;

            //Bitmap thumb = Bitmap.createScaledBitmap (BitmapFactory.decodeFile(selectedImagePath, options), 96, 96, false);

            UploadImage uploadImage = new UploadImage(Bitmap.createScaledBitmap(BitmapFactory.decodeFile(destination.getPath(), options), 96, 96, false));
            uploadImage.execute();


        } catch (FileNotFoundException e) {

        }
    }

    private void deleteEvento() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_deleteEventoSeleccionado),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        int success;
                        Utilidades utilidades = new Utilidades();
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            utilidades.errorConsultaBBDD(getActivity(), jsonObject.getString("mensaje"));

                            success = jsonObject.getInt("success");


                            if (success == 1) {


                                activity.finish();
                                activity.overridePendingTransition(0, 0);

                                Intent i;
                                if (idSesion == 1) {
                                    i = new Intent(activity, TabAdmin.class);
                                } else {
                                    i = new Intent(activity, MenuPrincipal.class);
                                }

                                SharedPreferences sharedPreferences = activity.getSharedPreferences(activity.getResources().getString(R.string.sDATOS), Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putInt(activity.getResources().getString(R.string.sIdSesion), idSesion);
                                editor.putInt(activity.getResources().getString(R.string.tab), 0);

                                editor.apply();
                                activity.startActivity(i);
                                activity.finish();

                            } else {
                                Toast.makeText(activity, activity.getResources().getString(R.string.sNoEliminaEvento), Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            Log.e("deleteEvento Catch", e.getMessage());
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        Log.e("deleteEvento Error", error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //Adding parameters to request


                return evento.formatoIdEventoidUsuario(getActivity());
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }

    private ImageView getIv_comentario() {
        return iv_comentario;
    }

    private void setIv_comentario(ImageView iv_comentario) {
        this.iv_comentario = iv_comentario;
    }

    private ImageView getIv_confirmar() {
        return Iv_confirmar;
    }

    private void setIv_confirmar(ImageView iv_confirmar) {
        Iv_confirmar = iv_confirmar;
    }

    private ImageView getIv_denunciar() {
        return Iv_denunciar;
    }

    private void setIv_denunciar(ImageView iv_denunciar) {
        Iv_denunciar = iv_denunciar;
    }

    private CustomFontTextView getTv_artista() {
        return tv_artista;
    }

    private void setTv_artista(CustomFontTextView tv_artista) {
        this.tv_artista = tv_artista;
    }

    private CustomFontTextView getTv_categoria() {
        return tv_categoria;
    }

    private void setTv_categoria(CustomFontTextView tv_categoria) {
        this.tv_categoria = tv_categoria;
    }

    private CustomFontTextView getTv_clasificacion() {
        return tv_clasificacion;
    }

    private void setTv_clasificacion(CustomFontTextView tv_clasificacion) {
        this.tv_clasificacion = tv_clasificacion;
    }

    private CustomFontTextView getTv_confirmacion() {
        return tv_confirmacion;
    }

    private void setTv_confirmacion(CustomFontTextView tv_confirmacion) {
        this.tv_confirmacion = tv_confirmacion;
    }

    private CustomFontTextView getTv_creador() {
        return tv_creador;
    }

    private void setTv_creador(CustomFontTextView tv_creador) {
        this.tv_creador = tv_creador;
    }

    private CustomFontTextView getTv_denuncia() {
        return tv_denuncia;
    }

    private void setTv_denuncia(CustomFontTextView tv_denuncia) {
        this.tv_denuncia = tv_denuncia;
    }

    private CustomFontTextView getTv_descripcion() {
        return tv_descripcion;
    }

    private void setTv_descripcion(CustomFontTextView tv_descripcion) {
        this.tv_descripcion = tv_descripcion;
    }

    private CustomFontTextView getTv_direccion() {
        return tv_direccion;
    }

    private void setTv_direccion(CustomFontTextView tv_direccion) {
        this.tv_direccion = tv_direccion;
    }

    private CustomFontTextView getTv_fecha() {
        return tv_fecha;
    }

    private void setTv_fecha(CustomFontTextView tv_fecha) {
        this.tv_fecha = tv_fecha;
    }

    private CustomFontTextView getTv_hora() {
        return tv_hora;
    }

    private void setTv_hora(CustomFontTextView tv_hora) {
        this.tv_hora = tv_hora;
    }

    private CustomFontTextView getTv_lugar() {
        return tv_lugar;
    }

    private void setTv_lugar(CustomFontTextView tv_lugar) {
        this.tv_lugar = tv_lugar;
    }

    private CustomFontTextView getTv_precio() {
        return tv_precio;
    }

    private void setTv_precio(CustomFontTextView tv_precio) {
        this.tv_precio = tv_precio;
    }

    private CustomFontTextView getTv_tipo() {
        return tv_tipo;
    }

    private void setTv_tipo(CustomFontTextView tv_tipo) {
        this.tv_tipo = tv_tipo;
    }

    private DireccionPOJO getDireccionPOJO() {
        return direccionPOJO;
    }

    private CustomFontTextView getTv_numComentario() {
        return tv_numComentario;
    }

    private void setTv_numComentario(CustomFontTextView tv_numComentario) {
        this.tv_numComentario = tv_numComentario;
    }

    private int getPosicion() {
        return posicion;
    }

    private void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

        void onDialogPositive(int res);

        void setTitle(String title);

    }

    public static class ImageDownloadTask extends AsyncTask<String, Void, Boolean> {

        private final String fileURL;
        private final ImageView imageView;
        private final Context _context;
        private String newFile;


        /**
         *
         */
        public ImageDownloadTask(final Context context, final String imageURL, final ImageView imageView) {
            this.fileURL = imageURL;
            this.imageView = imageView;
            this._context = context;


        }

        @Override
        protected Boolean doInBackground(final String... args) {

            HttpURLConnection con = null;
            Boolean estado = false;


            try {
                HttpURLConnection.setFollowRedirects(false);

                newFile = _context.getString(R.string.sRutaImagenes) + fileURL + _context.getString(R.string.sFormatoEvento);
                con = (HttpURLConnection) new URL(newFile).openConnection();
                con.setRequestMethod("HEAD");

                if ((con.getResponseCode() == HttpURLConnection.HTTP_OK)) {


                    if (con.getURL().getFile().equalsIgnoreCase(_context.getString(R.string.upload)
                            + fileURL
                            + _context.getString(R.string.sFormatoEvento))) {
                        {

                            urlShare = newFile;
                            estado = true;
                        }
                    }


                }

            } catch (Exception e) {
                e.printStackTrace();


            } finally {
                assert con != null;
                assert con != null;
                con.disconnect();
            }


            if (!newFile.contains(".jpeg"))
                newFile = newFile.concat(".jpeg");
            return estado;
        }


        @Override
        protected void onPostExecute(final Boolean result) {

            isImage = result;


            if (result) {
                {
                    launchPicasso(_context, result, this.imageView, newFile);
                    urlShare = newFile;
                }
            }


        }

    }

    /**
     *
     */
    public class UploadImage extends AsyncTask<Void, Void, Void> {


        private final Bitmap bitmaps;
        private ProgressDialog progressDialog;

        public UploadImage(Bitmap bitmaps) {
            this.bitmaps = bitmaps;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.show();

        }


        @Override
        protected Void doInBackground(Void... params) {


            Bitmap bitmap = bitmaps;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream); // convertir Bitmap to ByteArrayOutputStream
            InputStream in = new ByteArrayInputStream(stream.toByteArray()); // convertirr ByteArrayOutputStream to ByteArrayInputStream

            DefaultHttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(
                    getActivity().getResources().getString(R.string.sUrl_saveToFile));

            MultiPartEntity reqEntity = new MultiPartEntity();
            reqEntity.addPart(getActivity().getResources().getString(R.string.sImagenes), evento.getI_idEvento() + getActivity().getResources().getString(R.string.sFormatoEvento) + getActivity().getResources().getString(R.string.punto) + formato, in);

            httppost.setEntity(reqEntity);


            HttpResponse response = null;
            try {
                response = httpclient.execute(httppost);
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                if (response != null) {

                    InputStream is = response.getEntity().getContent();
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(is));
                    StringBuilder str = new StringBuilder();

                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        str.append(line).append("\n");
                    }


                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            progressDialog.dismiss();

            Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.uploaded), Toast.LENGTH_LONG).show();
        }
    }


}