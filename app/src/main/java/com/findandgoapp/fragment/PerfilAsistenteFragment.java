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
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.CursorLoader;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
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
import android.widget.Spinner;
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
import com.findandgoapp.activity.MainActivity;
import com.findandgoapp.activity.MenuPrincipal;
import com.findandgoapp.activity.R;
import com.findandgoapp.adapter.MyArrayAdapter;
import com.findandgoapp.custom.CustomFontEditText;
import com.findandgoapp.custom.CustomTypeFaceSpan;
import com.findandgoapp.library.MultiPartEntity;
import com.findandgoapp.pojo.UsuarioPOJO;
import com.findandgoapp.tools.BordesRedondos;
import com.findandgoapp.tools.Utilidades;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;


public class PerfilAsistenteFragment extends Fragment {


    private static final int SIZE_FOTO = 300;
    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    private static final int EXTERNAL_STORAGE_PERMISSION_CONSTANT = 100;
    private static final int REQUEST_PERMISSION_SETTING = 101;
    private static UsuarioPOJO usuario;
    private ImageView iv_fotoPerfil;
    private CustomFontEditText et_nombre;
    private EditText et_password;
    private CustomFontEditText et_email;
    private TextInputLayout tilNombre, tilEmail, tilPassword, tilDescripcion, tilWeb;
    private Spinner sp_ciudad;
    private ImageView iv_registrar;
    private Bitmap rotatedBMP;
    private String modo;
    private Activity activity;
    private SharedPreferences sharedPreferences;
    private File destination;
    private SharedPreferences permissionStatus;

    public PerfilAsistenteFragment() {

    }

    /**
     * @param context
     * @param isImage
     * @param image
     * @param file
     */
    private static void launchPicasso(Context context, Boolean isImage, ImageView image, String file) {


        if (isImage) {

            Picasso.
                    with(context).
                    load(file)
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .transform(new BordesRedondos(300, 0))
                    .resize((int) context.getResources().getDimension(R.dimen.fotoPerfil), (int) context.getResources().getDimension(R.dimen.fotoPerfil))
                    .error(R.drawable.icono_asistente)
                    .into(image);


            Utilidades utilidades = new Utilidades();
            utilidades.clearImageDiskCache(context.getApplicationContext());
        } else {
            if (usuario.getI_tipo() == 2) {

                image.setImageDrawable(context.getResources().getDrawable(R.drawable.icono_usuario));

            } else if (usuario.getI_tipo() == 3) {
                image.setImageDrawable(context.getResources().getDrawable(R.drawable.icono_artista));
            } else {
                image.setImageDrawable(context.getResources().getDrawable(R.drawable.icono_asistente));
            }
        }


    }

    @Override
    public void onAttach(Context _context) {
        super.onAttach(_context);

    }

    @Override
    public void onResume() {
        super.onResume();
        usuario = new UsuarioPOJO();
        sharedPreferences = getActivity().getSharedPreferences(getResources().getString(R.string.sDATOS), MODE_PRIVATE);
        usuario.setI_idUsuario(sharedPreferences.getInt(getResources().getString(R.string.sIdSesion), 0));
        modo = sharedPreferences.getString(getString(R.string.modo), getString(R.string.svacio));

    }

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getActivity().setTitle(getString(R.string.title_activity_perfil_asistente));
        activity = getActivity();
        usuario = new UsuarioPOJO();
        setHasOptionsMenu(true);

        sharedPreferences = getActivity().getSharedPreferences(getResources().getString(R.string.sDATOS), MODE_PRIVATE);
        usuario.setI_idUsuario(sharedPreferences.getInt(getResources().getString(R.string.sIdSesion), 0));
        usuario.setI_tipo(4);
        usuario.setI_estado(sharedPreferences.getInt(getResources().getString(R.string.estado), 0));
        modo = sharedPreferences.getString(getString(R.string.modo), getString(R.string.svacio));

        permissionStatus = getActivity().getSharedPreferences(getString(R.string.permissionStatus), MODE_PRIVATE);

        final View rootView = inflater.inflate(R.layout.fragment_usuario_asistente, container, false);
        final List<Spinner> aSpinner = new LinkedList<>();

        setTilNombre((TextInputLayout) rootView.findViewById(R.id.tilAsistenteNombre));
        setTilEmail((TextInputLayout) rootView.findViewById(R.id.tilAsistenteEmail));
        setTilPassword((TextInputLayout) rootView.findViewById(R.id.tilAsistentePassword));

        setEt_nombre((CustomFontEditText) rootView.findViewById(R.id.idEtAsistenteNombre));

        getEt_nombre().addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                getTilNombre().setError(getString(R.string.sCampoObligatorio));

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                getTilNombre().setError(null);
                getEt_nombre().setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

                getEt_nombre().setError(null);

            }

        });


        setEt_email((CustomFontEditText) rootView.findViewById(R.id.idEtAsistenteEmail));
        getEt_email().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                getTilEmail().setError(getString(R.string.sCampoObligatorio));
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                getTilEmail().setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

                Log.e(getClass().getName(), String.valueOf(s) + " - " + Utilidades.esCorreoValido(String.valueOf(s)));
                if (!Utilidades.esCorreoValido(String.valueOf(s))) {
                    getTilEmail().setError(getString(R.string.sErrorFormatoEmail));

                } else {

                    getTilEmail().setErrorEnabled(false);
                }

            }

        });
        setEt_password((EditText) rootView.findViewById(R.id.idEtAsistentePassword));

        getEt_password().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                getTilPassword().setError(getString(R.string.sCampoObligatorio));

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                getTilPassword().setError(getString(R.string.sCampoObligatorio));
            }

            @Override
            public void afterTextChanged(Editable s) {

                Log.e(getClass().getName(), String.valueOf(s) + " - " + Utilidades.esCorreoValido(String.valueOf(s)));

                getTilPassword().setError(null);
            }

        });

        setSp_ciudad((Spinner) rootView.findViewById(R.id.idSpAsistenteCiudad));

        setIv_registrar((ImageView) rootView.findViewById(R.id.idIvAsistenteRegistrar));

        setIv_fotoPerfil((ImageView) rootView.findViewById(R.id.ivAsistenteFoto));

        if (!modo.equals(getString(R.string.crear))) {

            usuario.setS_password(sharedPreferences.getString(getResources().getString(R.string.password), null));

            getIv_fotoPerfil().setVisibility(View.VISIBLE);

            Utilidades utilidades = new Utilidades(getActivity());

            ImageDownloadTask imageDownloadTask = new ImageDownloadTask(getActivity(), String.valueOf(usuario.getI_idUsuario())
                    , getIv_fotoPerfil());
            imageDownloadTask.execute();


            rellenarFichaAsistente();
            utilidades.desactivarEditText(getEt_email());

            getEt_email().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Utilidades utilidades = new Utilidades();
                    utilidades.mensajeAlertDialog(getActivity(), getActivity().getResources().getString(R.string.sCampoNoModificable) + "\n" +
                            getActivity().getResources().getString(R.string.sEmailAdminstrador));
                }
            });
            getIv_fotoPerfil().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                        //RUNTIME PERMISSION Android M
                        Utilidades utilidades = new Utilidades();

                        if (Build.VERSION.SDK_INT >= 23) {
                            requestPermission();
                            if (utilidades.isExternalStorageAvailable()) {
                                selectImage();
                            } else {
                                Toast.makeText(getActivity(), getString(R.string.noRealizaFoto), Toast.LENGTH_SHORT).show();
                            }


                        } else {

                            if (utilidades.isExternalStorageAvailable()) {
                                selectImage();
                            } else {
                                Toast.makeText(getActivity(), getString(R.string.noRealizaFoto), Toast.LENGTH_SHORT).show();
                            }

                        }

                    } else {
                        Log.e(getClass().getName(), "Error Mounted " + Environment.MEDIA_MOUNTED + Environment.getExternalStorageState());
                    }
                }
            });


        } else {
            getIv_fotoPerfil().setVisibility(View.INVISIBLE);
        }

        final MyArrayAdapter arrAdapterCiudad = new MyArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, getActivity().getResources().getStringArray(R.array.ciudades));
        getSp_ciudad().setAdapter(arrAdapterCiudad);

        aSpinner.add(getSp_ciudad());


        getIv_registrar().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utilidades ut = new Utilidades();
                boolean nombre = true;
                boolean email = true;
                boolean password = true;

                if (getEt_nombre().getText().toString().isEmpty()) {
                    getTilNombre().setError(getString(R.string.sCampoObligatorio));
                    nombre = false;
                }
                if (getEt_email().getText().toString().isEmpty()) {
                    getTilEmail().setError(getString(R.string.sCampoObligatorio));
                    email = false;
                } else if (!ut.compruebaEmail(getActivity().getApplicationContext(), getEt_email().getText().toString())) {
                    getTilEmail().setError(getString(R.string.sErrorFormatoEmail));
                    email = false;

                }


                if (getEt_password().getText().toString().isEmpty()) {
                    getTilPassword().setError(getString(R.string.sCampoObligatorio));
                    password = false;
                }


                if (nombre && email && password) {

                    if (!(ut.compruebaSpinner(getActivity().getApplicationContext(), aSpinner))) {

                        usuario.setS_nombre(getEt_nombre().getText().toString());
                        usuario.setS_password(getEt_password().getText().toString());
                        usuario.setS_email(getEt_email().getText().toString());
                        usuario.setS_ciudad(getSp_ciudad().getSelectedItem().toString());


                        if (!modo.equalsIgnoreCase(getString(R.string.crear))) {
                            updateAsistente();
                        } else {
                            crearAsistente();
                        }

                    }

                } else {

                    Toast.makeText(getActivity(), getString(R.string.compruebeCampos), Toast.LENGTH_SHORT).show();

                }
            }
        });

        return rootView;
    }

    /**
     * @param menu
     * @param inflater
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        if (!modo.equalsIgnoreCase(getString(R.string.crear))) {

            inflater.inflate(R.menu.menu_perfil_asistente, menu);
            Log.e(getClass().getName(), "onCreateOptionsMenu1\n");
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    /**
     * @param item
     * @return
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.e(getClass().getName(), "onOptionsItemSelected");

        Intent i;
        switch (item.getItemId()) {
            case R.id.miIdEliminarAsistente:

                TextView textView = new TextView(getActivity().getApplicationContext());
                textView.setText(getActivity().getApplicationContext().getResources().getString(R.string.sWarning));
                textView.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), getActivity().getResources().getString(R.string.fontAmaticRegular)));
                textView.setPadding(15, 10, 0, 0);
                textView.setTextSize(getActivity().getResources().getDimension(R.dimen.size_15));
                textView.setTextColor(getActivity().getResources().getColor(R.color.rojo));

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setCustomTitle(textView)
                        .setMessage(getActivity().getResources().getString(R.string.sEliminarUsuario))
                        .setCancelable(false)
                        .setIcon(getResources().getDrawable(R.drawable.ic_action_alarms))
                        .setNegativeButton(getActivity().getResources().getString(R.string.sNO), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton(getActivity().getResources().getString(R.string.sSi), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                deleteAsistente();

                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();
                alert.getWindow().getAttributes();

                Button button = new Button(getActivity());
                button = alert.getButton(DialogInterface.BUTTON_POSITIVE);
                button.setTextColor(getActivity().getResources().getColor(R.color.link));
                button.setBackgroundColor(getActivity().getResources().getColor(R.color.blanco));

                //Preparamos las fuentes personalizadas
                Typeface fontTextoBoton = Typeface.createFromAsset(getActivity().getAssets(), getActivity().getResources().getString(R.string.fontAmaticRegularBold));
                button.setTypeface(fontTextoBoton);


                Button buttonCancel = new Button(getActivity());
                buttonCancel = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
                buttonCancel.setTextColor(getActivity().getResources().getColor(R.color.blanco));
                buttonCancel.setBackgroundColor(getActivity().getResources().getColor(R.color.link));
                //Preparamos las fuentes personalizadas
                Typeface fontTextoCancel = Typeface.createFromAsset(getActivity().getAssets(), getActivity().getResources().getString(R.string.fontAmaticRegularBold));
                button.setTypeface(fontTextoCancel);

                TextView textView1 = (TextView) alert.findViewById(android.R.id.message);
                textView1.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), getActivity().getResources().getString(R.string.fontAmaticRegular)));
                textView1.setTextSize(getActivity().getResources().getDimension(R.dimen.size_10));


                return true;
            case R.id.miIdRealizarFotoAsistente:


                Utilidades utilidades = new Utilidades();

                if (utilidades.isExternalStorageAvailable()) {
                    if (Build.VERSION.SDK_INT >= 23) {
                        requestPermission();
                    } else
                        selectImage();
                } else {
                    Toast.makeText(getActivity(), getString(R.string.noRealizaFoto), Toast.LENGTH_SHORT).show();
                }


                return true;

            case R.id.action_settings:

                Log.d(getClass().getName(), "Configuracion" + String.valueOf(item.getItemId()));
                i = new Intent(getActivity(), Configuracion.class);
                startActivity(i);
                return true;

            default:

                return super.onOptionsItemSelected(item);
        }

    }

    /**
     *
     */

    private void requestPermission() {


        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                //Show Information about why you need the permission

                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_WRITE_EXTERNAL_STORAGE);


            } else if (permissionStatus.getBoolean(Manifest.permission.WRITE_EXTERNAL_STORAGE, false)) {
                //Previously Permission Request was cancelled with 'Dont Ask Again',
                // Redirect to Settings after showing Information about why you need the permission
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_CONSTANT);

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
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_WRITE_EXTERNAL_STORAGE: {
                if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    selectImage();


                } else {

                    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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

    /**
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView imageview = (ImageView) getActivity().findViewById(R.id.ivAsistenteFoto);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        Bitmap thumbnail;

        if (resultCode == -1) {
            if (requestCode == 137) {

                thumbnail = (Bitmap) data.getExtras().get("data");
                assert thumbnail != null;
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

                File directorio = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/FindAndGo");

                if (!directorio.exists()) {
                    File wallpaperDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/FindAndGo");
                    wallpaperDirectory.mkdirs();
                }

                destination = new File(Environment.getExternalStoragePublicDirectory((Environment.DIRECTORY_DCIM) + "/FindAndGo"),
                        usuario.getI_idUsuario() + getActivity().getResources().getString(R.string.sFormatoPerfil));
                FileOutputStream fo;


                try {
                    if (destination.exists())
                        destination.delete();

                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                BitmapFactory.Options options = new BitmapFactory.Options();


                int scale = 1;
                while (options.outWidth / scale / 2 >= SIZE_FOTO
                        && options.outHeight / scale / 2 >= SIZE_FOTO)
                    scale *= 2;
                options.inSampleSize = scale;

                Bitmap thumb = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(destination.getPath(), options), SIZE_FOTO, SIZE_FOTO, false);

                BordesRedondos bordesRedondos = new BordesRedondos(SIZE_FOTO, 0);


                imageview.setImageBitmap(bordesRedondos.transform(thumb));


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
                    while (options.outWidth / scale / 2 >= SIZE_FOTO
                            && options.outHeight / scale / 2 >= SIZE_FOTO)
                        scale *= 2;
                    options.inSampleSize = scale;

                    Bitmap thumb = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(selectedImagePath, options), SIZE_FOTO, SIZE_FOTO, false);

                    BordesRedondos bordesRedondos = new BordesRedondos(SIZE_FOTO, 0);
                    imageview.setImageBitmap(bordesRedondos.transform(thumb));
                    destination = new File(selectedImagePath);

                }
                try {
                    Utilidades utilidades = new Utilidades();
                    if (utilidades.isExternalStorageAvailable())
                        upload();
                    else {
                        Log.e(getClass().getName(), "Error al cargar la imagen");
                        utilidades.mensajeAlertDialog(getActivity().getApplicationContext(), getActivity().getResources().getString(R.string.sNoImagenDownload));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                Utilidades utilidades = new Utilidades();
                if (utilidades.isExternalStorageAvailable())
                    upload();
                else {
                    Log.e(getClass().getName(), "Error al cargar la imagen");
                    utilidades.mensajeAlertDialog(getActivity().getApplicationContext(), getActivity().getResources().getString(R.string.sNoImagenDownload));
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     */
    private void upload() {
        // Image location URL
        Log.e(getClass().getName(), "path " + destination.getPath());

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
            Log.e(getClass().getName(), e.getMessage());
        }
    }

    /**
     *
     *
     */
    private void deleteAsistente() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_deleteUsuario),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Log.e(getClass().getName(), "deleteAsistente " + response);


                        try {

                            JSONObject json = new JSONObject(response);
                            int success;
                            success = json.getInt(getActivity().getString(R.string.success));
                            Log.e(getClass().getName(), "deleteAsistente " + response);

                            if (success == 1) {

                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.clear();
                                editor.apply();

                                getActivity().finish();
                                startActivity(intent);
                            } else
                                Toast.makeText(getActivity(), getString(R.string.UsuarioEliminado), Toast.LENGTH_SHORT).show();
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

                return usuario.formatoIdUsuario(getActivity());
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

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
                    Log.e(getClass().getSimpleName(), "onMenuOpened...unable to set icons for overflow menu", e);
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

    /**
     *
     */
    private void rellenarFichaAsistente() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_selectAllAsistente),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Log.e(getClass().getName(), "rellenarFichaAsistente " + response);


                        try {
                            JSONObject json = new JSONObject(response);


                            rellenarCampos(json.getJSONObject(getString(R.string.result)));
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

                return usuario.formatoIdUsuario(getContext());
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }


    /**
     *
     *
     */
    private void crearAsistente() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_insertAsistente),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONObject json = new JSONObject(response);


                            Log.e(getClass().getName(), "Success " + response);

                            if (json.getInt(getString(R.string.success)) == 1) {


                                Log.e(getClass().getName(), " Entra  -> " + json.getInt(getString(R.string.sIdUsuario)));
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putInt(activity.getString(R.string.sIdSesion), json.getInt(activity.getString(R.string.sIdUsuario)));
                                editor.putInt(activity.getString(R.string.tipoUsuario), 4);
                                editor.putString(activity.getString(R.string.password), json.getString(activity.getString(R.string.password)));
                                editor.putInt(activity.getString(R.string.estado), usuario.getI_estado());
                                editor.putInt(activity.getString(R.string.tab), 0);

                                editor.putBoolean(activity.getString(R.string.bloqueado), false);

                                editor.apply();
                                Log.e(getClass().getName(), " apply -> " + json.getInt(activity.getString(R.string.sIdUsuario)));
                                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentManager.popBackStack();
                                fragmentTransaction.commit();
                                Log.e(getClass().getName(), " commit -> " + json.getInt(activity.getString(R.string.sIdUsuario)));

                                Intent i = new Intent(activity, MenuPrincipal.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                activity.startActivity(i);
                                activity.finish();
                                Log.e(getClass().getName(), " finish -> ");

                                Log.e(getClass().getName(), " Menu Ppal -> ");

                            } else if (json.getInt(getString(R.string.success)) == 6)
                                Toast.makeText(getActivity(), getString(R.string.usuarioYaExiste), Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(getActivity(), getString(R.string.usuarioNocreado), Toast.LENGTH_SHORT).show();
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

                return formatoAsistente();
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);


    }

    /**
     * @return Map<String, String>
     * <p>
     * Devuelve un Map del usuario con el que se está trabajando
     */
    private Map<String, String> formatoAsistente() {

        Map<String, String> params = new HashMap<>();
        params.put(getString(R.string.sIdUsuario), String.valueOf(usuario.getI_idUsuario()));
        params.put(getString(R.string.nombre), getEt_nombre().getText().toString());
        params.put(getString(R.string.password), getEt_password().getText().toString());
        params.put(getString(R.string.email), getEt_email().getText().toString());

        params.put(getString(R.string.ciudad), getSp_ciudad().getSelectedItem().toString());

        return params;
    }

    private void updateAsistente() {

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_updateAsistente),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        int success;


                        Log.e(getClass().getName(), "updateAsistente " + response);


                        try {
                            JSONObject json = new JSONObject(response);
                            success = json.getInt(getString(R.string.success));
                            if (success == 1) {
                                Toast.makeText(getActivity(), getString(R.string.usuarioModificado), Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(getActivity(), getString(R.string.usuarioNoModificado), Toast.LENGTH_SHORT).show();


                            rellenarCampos(json);


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

                return formatoAsistente();
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    /**
     * @param json
     * @throws JSONException
     */
    private void rellenarCampos(JSONObject json) throws JSONException {

        Log.e(getClass().getName(), "RellarCampos " + json.toString());
        int seleccionado;
        getEt_nombre().setText(json.getString(getResources().getString(R.string.sNombreUsuario)));
        getEt_password().setText(json.getString(getString(R.string.password)));
        getEt_email().setText(json.getString(getResources().getString(R.string.smail)));
        seleccionado = Integer.parseInt(json.getString(getResources().getString(R.string.sidCiudad)));
        getSp_ciudad().setSelection(seleccionado);

    }

    /**
     * Opciones de Menu
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(getActivity().getResources().getString(R.string.sMessage),
                getActivity().getResources().getString(R.string.sRutaImagenes));

    }

    /**
     * Getters and setters
     */


    private CustomFontEditText getEt_email() {
        return et_email;
    }

    private void setEt_email(CustomFontEditText et_email) {
        this.et_email = et_email;
    }

    private CustomFontEditText getEt_nombre() {
        return et_nombre;
    }

    private void setEt_nombre(CustomFontEditText et_nombre) {
        this.et_nombre = et_nombre;
    }

    private EditText getEt_password() {
        return et_password;
    }

    private void setEt_password(EditText et_password) {
        this.et_password = et_password;
    }

    private ImageView getIv_fotoPerfil() {
        return iv_fotoPerfil;
    }

    private void setIv_fotoPerfil(ImageView iv_fotoPerfil) {
        this.iv_fotoPerfil = iv_fotoPerfil;
    }

    private ImageView getIv_registrar() {
        return iv_registrar;
    }

    private void setIv_registrar(ImageView iv_registrar) {
        this.iv_registrar = iv_registrar;
    }

    private Spinner getSp_ciudad() {
        return sp_ciudad;
    }

    private void setSp_ciudad(Spinner sp_ciudad) {
        this.sp_ciudad = sp_ciudad;
    }

    private TextInputLayout getTilEmail() {
        return tilEmail;
    }

    private void setTilEmail(TextInputLayout tilEmail) {
        this.tilEmail = tilEmail;
    }

    private TextInputLayout getTilNombre() {
        return tilNombre;
    }

    private void setTilNombre(TextInputLayout tilNombre) {
        this.tilNombre = tilNombre;
    }

    private TextInputLayout getTilPassword() {
        return tilPassword;
    }

    private void setTilPassword(TextInputLayout tilPassword) {
        this.tilPassword = tilPassword;
    }

    /**
     *
     */
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
                newFile = _context.getString(R.string.sRutaImagenes) + fileURL + _context.getString(R.string.sFormatoPerfil);
                con = (HttpURLConnection) new URL(newFile).openConnection();
                con.setRequestMethod("HEAD");

                if ((con.getResponseCode() == HttpURLConnection.HTTP_OK)) {


                    if (con.getURL().getFile().equalsIgnoreCase(_context.getString(R.string.upload) + fileURL + _context.getString(R.string.sFormatoPerfil))) {
                        {


                            estado = true;
                        }
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();


                estado = false;
                Log.d(getClass().getName(), "CATCH" + e.getMessage());

            } finally {
                assert con != null;
                con.disconnect();
            }

            Log.e(getClass().getName(), "FILE_EXISTS" + estado);

            return estado;
        }


        @Override
        protected void onPostExecute(final Boolean result) {


            if (result) {
                launchPicasso(_context, result, this.imageView, newFile);
            } else {


                this.imageView.setImageDrawable(this._context.getResources().getDrawable(R.drawable.icono_asistente));
                this.imageView.getLayoutParams().height = (int) _context.getResources().getDimension(R.dimen.fotoAlto);
                this.imageView.getLayoutParams().width = (int) _context.getResources().getDimension(R.dimen.fotoAlto);

            }
            Log.e(getClass().getName(), "\nTermina Image: " + fileURL);


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

            if (bitmaps == null)
                return null;

            Bitmap bitmap = bitmaps;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream); // convertir Bitmap to ByteArrayOutputStream
            InputStream in = new ByteArrayInputStream(stream.toByteArray()); // convertirr ByteArrayOutputStream to ByteArrayInputStream

            DefaultHttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(
                    getActivity().getResources().getString(R.string.sUrl_saveToFile));

            MultiPartEntity reqEntity = new MultiPartEntity();
            reqEntity.addPart(getActivity().getResources().getString(R.string.sImagenes), usuario.getI_idUsuario() + getActivity().getResources().getString(R.string.sFormatoPerfil), in);
            Log.e(getClass().getName(), reqEntity.toString());
            httppost.setEntity(reqEntity);

            Log.e(getClass().getName(), "request " + httppost.getRequestLine());

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

                    Log.e(getClass().getName(), "uploadFile Path is : " + destination);

                }
                Log.e(getClass().getName(), "response " + (response != null ? response.getStatusLine().toString() : null));
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
            Log.e(getClass().getName(), "UploadImage");

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            progressDialog.dismiss();
            Log.e(getClass().getName(), "onPost");
            Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.uploaded), Toast.LENGTH_LONG).show();
        }
    }
}