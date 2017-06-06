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

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.findandgoapp.activity.Configuracion;
import com.findandgoapp.activity.PerfilUsuario;
import com.findandgoapp.activity.R;
import com.findandgoapp.custom.CustomFontRadioButton;
import com.findandgoapp.custom.CustomFontTextView;
import com.findandgoapp.custom.CustomTypeFaceSpan;
import com.findandgoapp.pojo.UsuarioPOJO;

public class UsuarioNuevoFragment extends Fragment {

    private static final String TAG = "UsuarioNuevoFrg";

    private static SharedPreferences sharedPreferences;
    private UsuarioPOJO usuarioPOJO;

    private RadioGroup rg_usuarioTipo;
    private RadioButton rb_usuarioAsistente;
    private RadioButton rb_usuarioArtista;
    private RadioButton rb_usuarioEmpresa;
    private ImageView iv_usuarioConfirmacion;
    private TextView tv_usuarioInformacion;


    public UsuarioNuevoFragment() {
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        Log.e(TAG, "onOptionsItemSelected");

        Intent i;
        switch (item.getItemId()) {
            case R.id.action_settings:
                Log.d(TAG, "Configuracion" + String.valueOf(item.getItemId()));
                i = new Intent(getActivity(), Configuracion.class);
                startActivity(i);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "OnCreateView");
        View rootView = inflater.inflate(R.layout.fragment_usuario_nuevo, container, false);

        usuarioPOJO = new UsuarioPOJO();


        sharedPreferences = getActivity().getSharedPreferences(getResources().getString(R.string.sDATOS), Context.MODE_PRIVATE);

        usuarioPOJO.setS_email(sharedPreferences.getString(getResources().getString(R.string.sEmail), null));
        usuarioPOJO.setS_nombre(sharedPreferences.getString(getResources().getString(R.string.sNombre), null));


        setElements(rootView);


        return rootView;
    }

    /**
     * @param rootView
     * @desc Inicializar los elementos del fragment
     */
    private void setElements(View rootView) {

        Log.i(TAG, "setElements");
        final View v = rootView;

        setTv_usuarioInformacion((CustomFontTextView) rootView.findViewById(R.id.idTvUsuarioInformacion));
        getTv_usuarioInformacion().setText(R.string.sAsistenteInformacion);
        setRg_usuarioTipo((RadioGroup) rootView.findViewById(R.id.idRgUsuarioTipo));

        setRg_usuarioTipo((RadioGroup) v.findViewById(R.id.idRgUsuarioTipo));
        setIv_usuarioConfirmacion((ImageView) v.findViewById(R.id.idBUsuarioConfirmacion));

        setRb_usuarioAsistente((CustomFontRadioButton) v.findViewById(R.id.idRbUsuarioAsistente));
        setRb_usuarioArtista((CustomFontRadioButton) v.findViewById(R.id.idRbUsuarioArtista));
        setRb_usuarioEmpresa((CustomFontRadioButton) v.findViewById(R.id.idRbUsuarioEmpresa));

        getRg_usuarioTipo().setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {// TODO Auto-generated method stub
                if (checkedId == R.id.idRbUsuarioAsistente) {

                    getRb_usuarioAsistente().setTextColor(v.getContext().getResources().getColor(R.color.wallet_link_text_light));
                    getRb_usuarioEmpresa().setTextColor(v.getContext().getResources().getColor(R.color.negro));
                    getRb_usuarioArtista().setTextColor(v.getContext().getResources().getColor(R.color.negro));
                    getTv_usuarioInformacion().setText(R.string.sAsistenteInformacion);

                } else if (checkedId == R.id.idRbUsuarioArtista) {
                    getRb_usuarioArtista().setTextColor(v.getContext().getResources().getColor(R.color.wallet_link_text_light));
                    getRb_usuarioEmpresa().setTextColor(v.getContext().getResources().getColor(R.color.negro));
                    getRb_usuarioAsistente().setTextColor(v.getContext().getResources().getColor(R.color.negro));
                    getTv_usuarioInformacion().setText(R.string.sArtistaInformacion);

                } else if (checkedId == R.id.idRbUsuarioEmpresa) {
                    getRb_usuarioEmpresa().setTextColor(v.getContext().getResources().getColor(R.color.wallet_link_text_light));
                    getRb_usuarioArtista().setTextColor(v.getContext().getResources().getColor(R.color.negro));
                    getRb_usuarioAsistente().setTextColor(v.getContext().getResources().getColor(R.color.negro));
                    getTv_usuarioInformacion().setText(R.string.sEmpresaInformacion);
                }

            }

        });

        getIv_usuarioConfirmacion().setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                //Se crea un objeto Intent que nos permitirá comenzar una actividad nueva
                //referente a un asistente, artista o empresa en función del valor introducido en el radiogroup anterior.
                Intent i;
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(v.getResources().getString(R.string.sNombre), usuarioPOJO.getS_nombre());
                editor.putString(v.getResources().getString(R.string.sEmail), usuarioPOJO.getS_email());
                int tipo = 0;

                //Se comprueba si el radio button ha sido seleccionado y se mostrará un mensaje usando
                //un objeto del tipo Toast.
                if (getRb_usuarioAsistente().isChecked()) {


                    Toast.makeText(v.getContext(), R.string.sSeleccionaAsistente, Toast.LENGTH_SHORT).show();
                    tipo = 4;


                } else if (getRb_usuarioArtista().isChecked()) {
                    Toast.makeText(v.getContext(), R.string.sSeleccionaArtista, Toast.LENGTH_SHORT).show();


                    tipo = 3;

                } else if (getRb_usuarioEmpresa().isChecked()) {
                    Toast.makeText(v.getContext(), R.string.sSeleccionaEmpresa, Toast.LENGTH_SHORT).show();
                    tipo = 2;


                }
                editor.putInt(getString(R.string.tipo), tipo);
                editor.putString(getString(R.string.modo), getString(R.string.crear));

                editor.apply();
                i = new Intent(v.getContext(), PerfilUsuario.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

            }
        });
    }

    private ImageView getIv_usuarioConfirmacion() {
        return iv_usuarioConfirmacion;
    }

    private void setIv_usuarioConfirmacion(ImageView iv_usuarioConfirmacion) {
        this.iv_usuarioConfirmacion = iv_usuarioConfirmacion;
    }

    private RadioButton getRb_usuarioArtista() {
        return rb_usuarioArtista;
    }

    private void setRb_usuarioArtista(RadioButton rb_usuarioArtista) {
        this.rb_usuarioArtista = rb_usuarioArtista;
    }

    private RadioButton getRb_usuarioAsistente() {
        return rb_usuarioAsistente;
    }

    private void setRb_usuarioAsistente(RadioButton rb_usuarioAsistente) {
        this.rb_usuarioAsistente = rb_usuarioAsistente;
    }

    private RadioButton getRb_usuarioEmpresa() {
        return rb_usuarioEmpresa;
    }

    private void setRb_usuarioEmpresa(RadioButton rb_usuarioEmpresa) {
        this.rb_usuarioEmpresa = rb_usuarioEmpresa;
    }

    private RadioGroup getRg_usuarioTipo() {
        return rg_usuarioTipo;
    }

    private void setRg_usuarioTipo(RadioGroup rg_usuarioTipo) {
        this.rg_usuarioTipo = rg_usuarioTipo;
    }

    private TextView getTv_usuarioInformacion() {
        return tv_usuarioInformacion;
    }

    private void setTv_usuarioInformacion(TextView tv_usuarioInformacion) {
        this.tv_usuarioInformacion = tv_usuarioInformacion;
    }

}
