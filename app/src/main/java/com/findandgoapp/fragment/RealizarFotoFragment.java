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

/**
 * Created by juanpolea on 15/08/16.
 */


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.findandgoapp.activity.MenuPrincipal;
import com.findandgoapp.activity.R;
import com.findandgoapp.library.MultiPartEntity;
import com.findandgoapp.pojo.EventoPOJO;
import com.findandgoapp.tools.BordesRedondos;
import com.findandgoapp.tools.Utilidades;

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

/**
 * A placeholder fragment containing a simple view.
 */
public class RealizarFotoFragment extends Fragment {

    private static final String TAG = "Realizar foto";
    private final EventoPOJO eventoConsultaPOJO = new EventoPOJO();
    private File destination;

    private SharedPreferences sharedPreferences;
    private int idSesion;
    private final int REQUIRED_SIZE = 30;
    private int inserta;
    private ImageView imageview;


    public RealizarFotoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_realizar_foto2, container, false);
        sharedPreferences = getActivity().getApplicationContext().getSharedPreferences("DATOS", Context.MODE_PRIVATE);

        eventoConsultaPOJO.setI_idEvento(sharedPreferences.getInt(getResources().getString(R.string.sIdEvento), 0));
        //eventoSeleccionadoPOJO.setiIdUsuario(sharedPreferences.getInt(getResources().getString(R.string.sIdUsuario), 0));


        idSesion = sharedPreferences.getInt(getActivity().getResources().getString(R.string.sIdSesion), 0);
        inserta = sharedPreferences.getInt(getActivity().getResources().getString(R.string.inserta), 0);
        if (inserta == 0)
            imageview = (ImageView) getActivity().findViewById(R.id.ivFoto);

        selectImage(getActivity());

        return view;
    }

    /**
     * @param context
     */
    private void selectImage(final Activity context) {

        final CharSequence[] items = {context.getResources().getString(R.string.sRealizarFoto),
                context.getResources().getString(R.string.sSeleccionarGaleria),
                context.getResources().getString(R.string.sCancelar)};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getResources().getString(R.string.sAddFoto));
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals(context.getResources().getString(R.string.sRealizarFoto))) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, context.getResources().getInteger(R.integer.REQUEST_CAMERA));
                } else if (items[item].equals(context.getResources().getString(R.string.sSeleccionarGaleria))) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, context.getResources().getString(R.string.sSelecionarArchivo)),
                            context.getResources().getInteger(R.integer.SELECT_FILE));
                } else if (items[item].equals(context.getResources().getString(R.string.sCancelar))) {
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

        ImageView imageview = (ImageView) getActivity().findViewById(R.id.ivFoto);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        Bitmap thumbnail;


        if (resultCode == -1) {
            boolean fotoNueva = false;

            if (requestCode == 137) {

                thumbnail = (Bitmap) data.getExtras().get("data");
                assert thumbnail != null;

                thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);


                if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                    //RUNTIME PERMISSION Android M

                    destination = new File(getActivity().getExternalFilesDir(android.os.Environment.DIRECTORY_DCIM), eventoConsultaPOJO.getI_idEvento() + getActivity().getResources().getString(R.string.sFormatoEvento) + getActivity().getResources().getString(R.string.sFormatoPerfil));
                    try {

                        destination.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }


                FileOutputStream fo;


                try {
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

                Bitmap thumb = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(destination.getPath(), options), 96, 96, false);

                BordesRedondos bordesRedondos = new BordesRedondos(100, 0);


                if (inserta == 0)
                    imageview.setImageBitmap(bordesRedondos.transform(thumb));


            } else if (requestCode == getActivity().getResources().getInteger(R.integer.SELECT_FILE)) {


                String selectedImagePath;

                if (Build.MANUFACTURER.equalsIgnoreCase("Xiaomi") && Build.MODEL.equalsIgnoreCase("Redmi 3S")) {
                    selectedImagePath = data.getData().getPath();
                } else {
                    String[] projection = {MediaStore.MediaColumns.DATA};
                    Cursor cursor = getActivity().managedQuery(data.getData(), projection, null, null,
                            null);
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                    cursor.moveToFirst();
                    selectedImagePath = cursor.getString(column_index);
                }


                BitmapFactory.Options options = new BitmapFactory.Options();

                int scale = 1;
                while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                        && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                    scale *= 2;
                options.inSampleSize = scale;
                Bitmap thumb = null;
                try {
                    thumb = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(selectedImagePath, options), 96, 96, false);
                } catch (Exception e) {
                    e.printStackTrace();
                }


                thumbnail = BitmapFactory.decodeFile(selectedImagePath, options);
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

                BordesRedondos bordesRedondos = new BordesRedondos(100, 0);

                Log.e(TAG, "fotoNueva " + fotoNueva);
                if (fotoNueva) {
                    if (inserta == 0) {
                        imageview = (ImageView) getActivity().findViewById(R.id.ivFoto);
                        imageview.setImageBitmap(bordesRedondos.transform(thumb));
                    }

                }
                destination = new File(selectedImagePath);
            }
            try {
                Utilidades utilidades = new Utilidades();
                if (utilidades.isExternalStorageAvailable())
                    upload();
                else {
                    Log.e(TAG, "Error al cargar la imagen");
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
        Log.e(TAG, "path " + destination.getPath());

        try {
            InputStream is = new FileInputStream(destination);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bao);

            BitmapFactory.Options options = new BitmapFactory.Options();


            int scale = 1;
            while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                    && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                scale *= 2;
            options.inSampleSize = scale;

            //Bitmap thumb = Bitmap.createScaledBitmap (BitmapFactory.decodeFile(selectedImagePath, options), 96, 96, false);

            UploadImage uploadImage = new UploadImage(Bitmap.createScaledBitmap(BitmapFactory.decodeFile(destination.getPath(), options), 96, 96, false));
            uploadImage.execute();


        } catch (FileNotFoundException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(getActivity().getResources().getString(R.string.sMessage),
                getActivity().getResources().getString(R.string.sRutaImagenes));

    }

    /**
     *
     */
    public class UploadImage extends AsyncTask<Void, Void, Void> {


        private final Bitmap bitmaps;
        ProgressDialog pDialog;

        public UploadImage(Bitmap bitmaps) {
            this.bitmaps = bitmaps;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            if (bitmaps == null)
                return null;

            Bitmap bitmap = bitmaps;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream); // convertir Bitmap to ByteArrayOutputStream
            InputStream in = new ByteArrayInputStream(stream.toByteArray()); // convertirr ByteArrayOutputStream to ByteArrayInputStream

            DefaultHttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(
                    getActivity().getResources().getString(R.string.sUrl_saveToFile));

            MultiPartEntity reqEntity = new MultiPartEntity();
            String formato = "jpeg";
            reqEntity.addPart(getActivity().getResources().getString(R.string.sImagenes), eventoConsultaPOJO.getI_idEvento() + getActivity().getResources().getString(R.string.sFormatoEvento) + getActivity().getResources().getString(R.string.punto) + formato, in);
            Log.e(TAG, reqEntity.toString());
            httppost.setEntity(reqEntity);

            Log.i(TAG, "request " + httppost.getRequestLine());

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
                Log.i(TAG, "response " + (response != null ? response.getStatusLine().toString() : null));
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
            pDialog.dismiss();
            Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.uploaded), Toast.LENGTH_LONG).show();

            /* Probar a eliminar el fragment en vez de iniciar la actividad

            if(fotoNueva){
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("idSesion", idSesion);
                editor.putInt("idEvento", idEvento);
                Intent intent = new Intent(getActivity(),EventoSeleccionado.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
            }
            getActivity().finish();*/

            int inserta = sharedPreferences.getInt(getString(R.string.inserta), 0);

            if (inserta == 1) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                FragmentTransaction frTransaction = fragmentManager.beginTransaction();

                frTransaction.addToBackStack(getString(R.string.fragment_evento_consulta));

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(getString(R.string.sIdUsuario), idSesion);
                editor.putString(getString(R.string.modo), getString(R.string.consultar));
                editor.putInt(getString(R.string.sIdSesion), idSesion);
                editor.putString(getString(R.string.fromFragment), getString(R.string.fragment_evento_consulta));
                editor.putInt(getString(R.string.seleccion), 0);
                editor.putInt(getString(R.string.tab), 0);
                editor.apply();
                frTransaction.commit();

            }
            Intent intent = new Intent(getActivity(), MenuPrincipal.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);


        }
    }


}

