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

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.ShareOpenGraphObject;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.widget.ShareDialog;
import com.findandgoapp.activity.R;
import com.findandgoapp.custom.CustomFontTextView;
import com.findandgoapp.pojo.EventoPOJO;
import com.google.android.gms.plus.PlusShare;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;


/**
 * Created by Juan F. Mateos
 */
public class FragmentDialogShare extends DialogFragment {

    private static final java.lang.String ARG_PARAM1 = "evento";
    private static final java.lang.String ARG_PARAM2 = "url";

    private com.findandgoapp.custom.CustomFontTextView email;
    private com.findandgoapp.custom.CustomFontTextView gmail;
    private com.findandgoapp.custom.CustomFontTextView google;
    private com.findandgoapp.custom.CustomFontTextView facebook;
    private com.findandgoapp.custom.CustomFontTextView sms;
    private com.findandgoapp.custom.CustomFontTextView twitter;
    private com.findandgoapp.custom.CustomFontTextView whatsapp;
    private ImageView iv_email;
    private ImageView iv_gmail;
    private ImageView iv_google;
    private ImageView iv_facebook;
    private ImageView iv_sms;
    private ImageView iv_twitter;
    private ImageView iv_whatsapp;

    private String url;
    private Bitmap bitmap;

    private Target target;

    private EventoPOJO evento;

    public static FragmentDialogShare newInstance(String url, EventoPOJO eventoPojo) {


        FragmentDialogShare fragmentDialog = new FragmentDialogShare();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM2, url);
        args.putParcelable(ARG_PARAM1, eventoPojo);
        fragmentDialog.setArguments(args);

        return fragmentDialog;
    }


    private String getUrl() {
        return url;
    }

    private void setUrl(String url) {
        this.url = url;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();


        final Intent i = getActivity().getIntent();

        final View v = inflater.inflate(R.layout.fragment_dialog_share, null);
        builder.setView(v);

        setUrl(i.getStringExtra(ARG_PARAM2));
        if (getUrl().equalsIgnoreCase(getString(R.string.svacio)))
            setUrl(getString(R.string.sRutaImagenesDummy));
        evento = i.getParcelableExtra(ARG_PARAM1);

        Log.e(getClass().getName(), getUrl());

        ImageView buttonCancel = ((ImageView) v.findViewById(R.id.bCancelaShare));

        email = ((CustomFontTextView) v.findViewById(R.id.idShareMail));
        gmail = ((CustomFontTextView) v.findViewById(R.id.idShareGmail));
        google = ((CustomFontTextView) v.findViewById(R.id.idShareGoogle));
        facebook = ((CustomFontTextView) v.findViewById(R.id.idShareFacebook));
        sms = ((CustomFontTextView) v.findViewById(R.id.idShareText));
        twitter = ((CustomFontTextView) v.findViewById(R.id.idShareTwitter));
        whatsapp = ((CustomFontTextView) v.findViewById(R.id.idShareWhatsapp));

        iv_email = ((ImageView) v.findViewById(R.id.ivShareMail));
        iv_gmail = ((ImageView) v.findViewById(R.id.ivShareGMail));
        iv_google = ((ImageView) v.findViewById(R.id.ivShareGoogle));
        iv_facebook = ((ImageView) v.findViewById(R.id.ivShareFacebook));
        iv_sms = ((ImageView) v.findViewById(R.id.ivShareText));
        iv_twitter = ((ImageView) v.findViewById(R.id.ivShareTwitter));
        iv_whatsapp = ((ImageView) v.findViewById(R.id.ivShareWhatsapp));

        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, createDescription(evento));
        intent.putExtra(Intent.EXTRA_SUBJECT, evento.getS_nombreEvento());
        intent.setType("text/plain");

        final PackageManager pm = getActivity().getPackageManager();
        final List<ResolveInfo> matches = pm.queryIntentActivities(intent, 0);
        final ResolveInfo[] best = {null};

        loadImage(getActivity(), getUrl());


        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (final ResolveInfo info : matches) {
                    if (info.activityInfo.packageName.endsWith("android.email") || info.activityInfo.name.toLowerCase().contains("gmail")) {
                        best[0] = info;
                        if (best[0] != null) {

                            intent.setClassName(best[0].activityInfo.packageName, best[0].activityInfo.name);
                            startActivity(intent);
                        } else
                            Toast.makeText(getActivity(), getString(R.string.appNoinstalada), Toast.LENGTH_LONG).show();
                    }


                }
                dismiss();

            }
        });


        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (final ResolveInfo info : matches) {
                    if (info.activityInfo.packageName.contains("android.gm") || info.activityInfo.name.toLowerCase().contains("gmail")) {
                        best[0] = info;
                        if (best[0] != null) {
                            intent.setClassName(best[0].activityInfo.packageName, best[0].activityInfo.name);
                            startActivity(intent);
                        } else
                            Toast.makeText(getActivity(), getString(R.string.appNoinstalada), Toast.LENGTH_LONG).show();
                    }


                }
                dismiss();

            }
        });

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (final ResolveInfo info : matches) {
                    if (info.activityInfo.packageName.contains("google")) {
                        best[0] = info;
                        if (best[0] != null) {
                            Intent share = new PlusShare.Builder(getActivity())
                                    .setType("text/plain")
                                    .setText(createDescriptionGooglePlus(evento))
                                    .setContentUrl(Uri.parse(getUrl()))
                                    .getIntent();

                            startActivity(share);
                        } else
                            Toast.makeText(getActivity(), getString(R.string.appNoinstalada), Toast.LENGTH_LONG).show();
                    }


                }
                dismiss();

            }
        });


        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {


                    ShareDialog shareDialog = new ShareDialog(getActivity());

                    ShareOpenGraphObject object = new ShareOpenGraphObject.Builder()
                            .putString("og:type", "article")
                            .putString("og:title", evento.getS_nombreEvento())
                            .putString(
                                    "og:description",
                                    createDescription(evento))
                            .build();


                    SharePhoto photo = new SharePhoto.Builder().setBitmap(bitmap)
                            .setUserGenerated(true).build();

                    // Create an action
                    ShareOpenGraphAction action = new ShareOpenGraphAction.Builder()
                            .setActionType("news.reads")
                            .putObject("article", object)

                            .build();

                    // Create the content
                    ShareOpenGraphContent content = new ShareOpenGraphContent.Builder()
                            .setPreviewPropertyName("article").setAction(action).build();

                    shareDialog.show(content);


                    //ShareDialog.show(content);


                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("Shari ", e.getMessage());


                }
                dismiss();
            }

        });

        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                for (final ResolveInfo info : matches) {
                    if (info.activityInfo.name.toLowerCase().contains("mms")) {
                        best[0] = info;
                        if (best[0] != null) {
                            intent.putExtra(Intent.EXTRA_TEXT, createDescriptionText(evento));
                            intent.setClassName(best[0].activityInfo.packageName, best[0].activityInfo.name);
                            startActivity(intent);
                        } else
                            Toast.makeText(getActivity(), getString(R.string.appNoinstalada), Toast.LENGTH_LONG).show();
                    }


                }
                dismiss();
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                for (final ResolveInfo info : matches) {
                    if (info.activityInfo.name.toLowerCase().contains("twitter")) {
                        best[0] = info;
                        if (best[0] != null) {
                            intent.putExtra(Intent.EXTRA_TEXT, createDescriptionTwitter(evento));
                            intent.setClassName(best[0].activityInfo.packageName, best[0].activityInfo.name);
                            startActivity(intent);
                        } else
                            Toast.makeText(getActivity(), getString(R.string.appNoinstalada), Toast.LENGTH_LONG).show();
                    }


                }
                dismiss();
            }
        });
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                for (final ResolveInfo info : matches) {
                    if (info.activityInfo.name.toLowerCase().contains("whatsapp")) {
                        best[0] = info;
                        if (best[0] != null) {
                            intent.putExtra(Intent.EXTRA_TEXT, createDescriptionText(evento));
                            intent.setClassName(best[0].activityInfo.packageName, best[0].activityInfo.name);
                            startActivity(intent);
                        } else
                            Toast.makeText(getActivity(), getString(R.string.appNoinstalada), Toast.LENGTH_LONG).show();
                    }


                }
                dismiss();

            }
        });


        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return builder.create();
    }

    private String createDescriptionTwitter(EventoPOJO eventoSeleccionadoPOJO) {
        String space = "\r\n";

        return eventoSeleccionadoPOJO.getS_nombreEvento().concat(". ").
                concat(getString(R.string.sClasificacionCabecera)).concat(": ").concat(eventoSeleccionadoPOJO.getS_clasificacion()).concat("." + space).
                concat(getString(R.string.Lugar)).concat(": ").concat(eventoSeleccionadoPOJO.getS_nombreSala()).concat(", ").
                concat(eventoSeleccionadoPOJO.getS_localidad()).concat(",").
                concat(eventoSeleccionadoPOJO.getsCiudad()).concat(". " + getString(R.string.en)).
                concat(eventoSeleccionadoPOJO.getS_fecha()).concat(" " + getString(R.string.alas)).
                concat(String.valueOf(eventoSeleccionadoPOJO.getS_hora())).concat(". #findandgo");

    }

    private String createDescription(EventoPOJO eventoSeleccionadoPOJO) {

        String space = "\r\n";

        return getString(R.string.Categoria).concat(": ").concat(eventoSeleccionadoPOJO.getS_categoria()).concat("." + space).
                concat(getString(R.string.sClasificacionCabecera)).concat(": ").concat(eventoSeleccionadoPOJO.getS_clasificacion()).concat("." + space).
                concat(getString(R.string.sala)).concat(": ").concat(eventoSeleccionadoPOJO.getS_nombreSala()).concat(".\n").
                concat(getString(R.string.Provincia)).concat(": ").concat(eventoSeleccionadoPOJO.getsCiudad()).concat(".\n").
                concat(getString(R.string.pueblo)).concat(": ").concat(eventoSeleccionadoPOJO.getS_localidad()).concat(".\n").
                concat(getString(R.string.Fecha)).concat(": ").concat(eventoSeleccionadoPOJO.getS_fecha()).concat(".\n").
                concat(getString(R.string.Hora)).concat(": ").concat(String.valueOf(eventoSeleccionadoPOJO.getS_hora())).concat(". #findandgo").concat(".\n").
                concat(getString(R.string.PuedesDescargar)).concat(getString(R.string.enlacePlayStore));


    }

    private String createDescriptionText(EventoPOJO eventoSeleccionadoPOJO) {

        String space = "\r\n";

        return eventoSeleccionadoPOJO.getS_nombreEvento().concat(". ").
                concat(getString(R.string.Categoria)).concat(": ").concat(eventoSeleccionadoPOJO.getS_categoria()).concat("." + space).
                concat(getString(R.string.sClasificacionCabecera)).concat(": ").concat(eventoSeleccionadoPOJO.getS_clasificacion()).concat("." + space).
                concat(getString(R.string.sala)).concat(": ").concat(eventoSeleccionadoPOJO.getS_nombreSala()).concat(".\n").
                concat(getString(R.string.Provincia)).concat(": ").concat(eventoSeleccionadoPOJO.getsCiudad()).concat(".\n").
                concat(getString(R.string.pueblo)).concat(": ").concat(eventoSeleccionadoPOJO.getS_localidad()).concat(".\n").
                concat(getString(R.string.Fecha)).concat(": ").concat(eventoSeleccionadoPOJO.getS_fecha()).concat(".\n").
                concat(getString(R.string.Hora)).concat(": ").concat(String.valueOf(eventoSeleccionadoPOJO.getS_hora()));


    }

    private String createDescriptionGooglePlus(EventoPOJO eventoSeleccionadoPOJO) {


        return getString(R.string.nombreDelEvento).concat(": ").concat(eventoSeleccionadoPOJO.getS_nombreEvento()).concat(".\n").
                concat(getString(R.string.Categoria)).concat(": ").concat(eventoSeleccionadoPOJO.getS_categoria()).concat(".\n").
                concat(getString(R.string.sClasificacionCabecera)).concat(": ").concat(eventoSeleccionadoPOJO.getS_clasificacion()).concat(".\n").
                concat(getString(R.string.sala)).concat(": ").concat(eventoSeleccionadoPOJO.getS_nombreSala()).concat(".\n").
                concat(getString(R.string.Provincia)).concat(": ").concat(eventoSeleccionadoPOJO.getsCiudad()).concat(".\n").
                concat(getString(R.string.pueblo)).concat(": ").concat(eventoSeleccionadoPOJO.getS_localidad()).concat(".\n").
                concat(getString(R.string.Fecha)).concat(": ").concat(eventoSeleccionadoPOJO.getS_fecha()).concat(".\n").
                concat(getString(R.string.Hora)).concat(": ").concat(String.valueOf(eventoSeleccionadoPOJO.getS_hora())).concat(".\n").
                concat(getString(R.string.PuedesDescargar)).concat(getString(R.string.enlacePlayStore));

    }


    private void loadImage(Context context, String url) {

        final ImageView imageView = (ImageView) getActivity().findViewById(R.id.image);

        target = new Target() {
            @Override
            public void onBitmapLoaded(final Bitmap _bitmap, Picasso.LoadedFrom from) {

                bitmap = Bitmap.createBitmap(_bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

                bitmap = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.ic_logo);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };

        Picasso.with(context)
                .load(url)
                .into(target);
    }


    @Override
    public void onDestroy() {  // could be in onPause or onStop
        Picasso.with(getActivity()).cancelRequest(target);
        super.onDestroy();
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }


}




