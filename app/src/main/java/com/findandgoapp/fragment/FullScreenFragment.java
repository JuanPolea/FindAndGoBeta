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
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.findandgoapp.activity.R;
import com.findandgoapp.custom.CustomTypeFaceSpan;
import com.findandgoapp.library.MultiPartEntity;
import com.findandgoapp.tools.BordesRedondos;
import com.findandgoapp.tools.Utilidades;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

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

import static android.content.Context.MODE_PRIVATE;

/**
 * A placeholder fragment containing a simple view.
 */
public class FullScreenFragment extends Fragment {

    private static final String TAG = "FullScreenFragment";
    private final int SIZE_FOTO = 600;
    private SharedPreferences permissionStatus;
    private static final int REQUEST_PERMISSION_SETTING = 101;
    private static final int EXTERNAL_STORAGE_PERMISSION_CONSTANT = 100;
    private int idSesion;
    private File destination;
    private ImageView imageView;
    private boolean leyenda;
    private int tipo;

    public FullScreenFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_full_screen, container, false);

        setHasOptionsMenu(true);

        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(getString(R.string.DATOS), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        imageView = (ImageView) view.findViewById(R.id.imgDisplay);

        idSesion = sharedPreferences.getInt(getActivity().getResources().getString(R.string.sIdSesion), 0);
        tipo = sharedPreferences.getInt(getString(R.string.tipoUsuario), 0);

        permissionStatus = getActivity().getSharedPreferences(getString(R.string.permissionStatus), MODE_PRIVATE);
        boolean flag = sharedPreferences.getBoolean(getActivity().getResources().getString(R.string.usuario), false);
        leyenda = sharedPreferences.getBoolean(getActivity().getResources().getString(R.string.leyenda), false);

        if (leyenda) {
            if (flag) {
                launchPicasso();

            } else {

                imageView.setBackground(ResourcesCompat.getDrawable(getActivity().getResources(), R.drawable.leyenda_full, null));
                imageView.setScaleType(ImageView.ScaleType.CENTER);
                getActivity().setTitle(R.string.Leyenda);
            }


        } else {
            launchPicasso();
        }
        editor.apply();


        return view;
    }

    /**
     *
     */
    private void launchPicasso() {


        try {

            Picasso.
                    with(getActivity()).
                    load(getActivity().getResources().getString(R.string.sRutaImagenes) +
                            String.valueOf(idSesion) +
                            getActivity().getResources().getString(R.string.sFormatoPerfil)).
                    memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).
                    transform(new BordesRedondos(SIZE_FOTO, 0)).
                    error(getResources().getDrawable(R.drawable.ic_imagen_por_insertar2)).
                    into(imageView);


        } catch (Exception e) {

            if (tipo == 2)
                imageView.setBackground(getActivity().getResources().getDrawable(R.drawable.icono_usuario));
            else if (tipo == 3)
                imageView.setBackground(getActivity().getResources().getDrawable(R.drawable.icono_artista));
            else if (tipo == 4)
                imageView.setBackground(getActivity().getResources().getDrawable(R.drawable.icono_asistente));
            else
                imageView.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_logo_tab));


            Log.e(getClass().getName(), "CATCH " + e.getMessage());
            e.printStackTrace();
        }
        Utilidades utilidades = new Utilidades();
        utilidades.clearImageDiskCache(getActivity());

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(getActivity().getResources().getString(R.string.sMessage),
                getActivity().getResources().getString(R.string.sRutaImagenes));

    }


    /**
     * @param menu
     */
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


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);


        if (!leyenda)
            inflater.inflate(R.menu.menu_full_screen, menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.e(TAG, "onOptionsItemSelected");

        switch (item.getItemId()) {
            case R.id.idMICamara:

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
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
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
        super.onActivityResult(requestCode, resultCode, data);
        ImageView imageview = (ImageView) getActivity().findViewById(R.id.imgDisplay);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        Bitmap thumbnail;

        if (resultCode == -1) {
            int h = 600;
            int w = 600;
            if (requestCode == 137) {
                try {
                    thumbnail = (Bitmap) data.getExtras().get("data");
                    assert thumbnail != null;
                    thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

                    File directorio = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/FindAndGo");

                    if (!directorio.exists()) {
                        File wallpaperDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/FindAndGo");
                        wallpaperDirectory.mkdirs();
                    }

                    destination = new File(Environment.getExternalStoragePublicDirectory((Environment.DIRECTORY_DCIM) + "/FindAndGo"),
                            idSesion + getActivity().getResources().getString(R.string.sFormatoPerfil));
                    FileOutputStream fo;


                    if (destination.exists())
                        destination.delete();

                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();


                    BitmapFactory.Options options = new BitmapFactory.Options();


                    int scale = 1;
                    while (options.outWidth / scale / 2 >= SIZE_FOTO
                            && options.outHeight / scale / 2 >= SIZE_FOTO)
                        scale *= 2;
                    options.inSampleSize = scale;


                    Bitmap thumb = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(destination.getPath(), options), w, h, false);

                    BordesRedondos bordesRedondos = new BordesRedondos(SIZE_FOTO, 0);
                    imageview.setImageBitmap(bordesRedondos.transform(thumb));


                } catch (IOException e) {
                    e.printStackTrace();
                }
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

                    Bitmap thumb = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(selectedImagePath, options), w, h, false);

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
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream); // convertir Bitmap to ByteArrayOutputStream
            InputStream in = new ByteArrayInputStream(stream.toByteArray()); // convertirr ByteArrayOutputStream to ByteArrayInputStream

            DefaultHttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(
                    getActivity().getResources().getString(R.string.sUrl_saveToFile));

            MultiPartEntity reqEntity = new MultiPartEntity();
            reqEntity.addPart(getActivity().getResources().getString(R.string.sImagenes), idSesion + getActivity().getResources().getString(R.string.sFormatoPerfil), in);
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
