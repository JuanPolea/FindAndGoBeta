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
import android.os.Build;
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
import android.widget.ImageView;
import android.widget.ListView;

import com.findandgoapp.activity.R;
import com.findandgoapp.custom.CustomFontTextView;
import com.findandgoapp.pojo.AlertaPOJO;

import java.util.Arrays;


/**
 * Created by Juan F. Mateos
 */
public class DialogSemana extends DialogFragment {


    private static final java.lang.String ARG_PARAM1 = "diasSemana";
    private static final java.lang.String ARG_PARAM2 = "idSesion";

    private ImageView buttonOK;
    private ListView list;


    private NoticeDialogListener mCallback;
    private AlertaPOJO alertaPojo;


    public static DialogSemana newInstance(AlertaPOJO alertaPojo, int idSesion) {

        DialogSemana fragmentDialog = new DialogSemana();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, alertaPojo);
        args.putSerializable(ARG_PARAM2, idSesion);
        fragmentDialog.setArguments(args);
        return fragmentDialog;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (NoticeDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement NoticeDialogListener");
        }
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

        final View v = inflater.inflate(R.layout.fragment_dialog_semana_list, null);
        builder.setView(v);

        setButtonOK((ImageView) v.findViewById(R.id.idBokSemana));
        ImageView buttonCancel = ((ImageView) v.findViewById(R.id.bCancelarDias));

        list = ((ListView) v.findViewById(R.id.listaRepetirSemana));


        alertaPojo = i.getParcelableExtra(ARG_PARAM1);

        Log.e(getClass().getName(), alertaPojo.getll_diaSeleccionado().toString());

        ListAdapterDialogo listAdapter = new ListAdapterDialogo(v.getContext(), alertaPojo);
        getList().setAdapter(listAdapter);


        addListenerToListView(v.getContext(), list);

        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                CustomFontTextView textView = (CustomFontTextView) getActivity().findViewById(R.id.idTvAlertaRepetir);
                boolean nunca = false;
                boolean siempre = true;

                for (boolean b : alertaPojo.getIsSelectedArray()) {

                    if (b)
                        nunca = true;
                    else
                        siempre = false;
                }


                mCallback.setTextInTextView(alertaPojo.getIsSelectedArray());
                if (!nunca) {
                    textView.setText(getString(R.string.nunca));
                    textView.setTextColor(getActivity().getResources().getColor(R.color.grisMuyOscuro));
                }

                if (siempre) {
                    textView.setText(getString(R.string.sRepetir));
                    textView.setTextColor(getActivity().getResources().getColor(R.color.verdeAgua));
                }

                Log.e(getClass().getName(), "setOnClickListener " + alertaPojo.getll_diaSeleccionado().toString() + "\n--> " + Arrays.toString(alertaPojo.getI_idiaSeleccionado()) + "---getIsSelectedArray--" + Arrays.toString(alertaPojo.getIsSelectedArray()));


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

    /**
     * @param context
     * @param listView
     */
    private void addListenerToListView(final Context context, final ListView listView) {


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                ImageView checkBox = ((ImageView) view.findViewById(R.id.idChexbox));
                CustomFontTextView textView = ((CustomFontTextView) view.findViewById(R.id.idTVDia));


                if (alertaPojo.getIsSelectedArray()[position]) {

                    alertaPojo.getIsSelectedArray()[position] = false;
                    alertaPojo.getI_idiaSeleccionado()[position] = 1;


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        checkBox.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_check_false));


                    } else
                        checkBox.setBackgroundDrawable(getActivity().getResources().getDrawable(R.drawable.ic_check_false));

                    textView.setTextColor(getResources().getColor(R.color.negro));


                } else {

                    alertaPojo.getIsSelectedArray()[position] = true;
                    alertaPojo.getI_idiaSeleccionado()[position] = 0;


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        checkBox.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_check_true));
                    } else
                        checkBox.setBackgroundDrawable(getActivity().getResources().getDrawable(R.drawable.ic_check_true));

                    textView.setTextColor(getResources().getColor(R.color.azulOscuro));

                }
                Log.e(getClass().getName(), "getIsSelectedArray " + alertaPojo.getIsSelectedArray()[position] + "\n\"getI_idiaSeleccionado \"+--> " + alertaPojo.getI_idiaSeleccionado()[position]);

                mCallback.setTextInTextView(alertaPojo.getIsSelectedArray());
                Log.e(getClass().getName(), "getIsSelectedArray " + Arrays.toString(alertaPojo.getIsSelectedArray()) + "\n\"getI_idiaSeleccionado \"+--> " + Arrays.toString(alertaPojo.getI_idiaSeleccionado()));

            }

        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void setButtonOK(ImageView buttonOK) {
        this.buttonOK = buttonOK;
    }

    private ListView getList() {
        return list;
    }

    public interface NoticeDialogListener {

        void setTextInTextView(boolean[] linkedlist);
    }

    /**
     * /**
     */
    private class ListAdapterDialogo extends BaseAdapter {

        private final AlertaPOJO alertaPOJO = new AlertaPOJO();
        private final Context _c;
        private final boolean[] booleans;
        private final String[] _semana = {
                getActivity().getResources().getString(R.string.sLunes),
                getActivity().getResources().getString(R.string.sMartes),
                getActivity().getResources().getString(R.string.sMiercoles),
                getActivity().getResources().getString(R.string.sJueves),
                getActivity().getResources().getString(R.string.sViernes),
                getActivity().getResources().getString(R.string.sSabado),
                getActivity().getResources().getString(R.string.sDomingo),
        };


        public ListAdapterDialogo(Context c, AlertaPOJO alerta) {

            booleans = alerta.getIsSelectedArray();
            alertaPOJO.setIsSelectedArray(alerta.getIsSelectedArray());
            _c = c;
        }

        public int getCount() {
            // TODO Auto-generated method stub
            return booleans.length;
        }

        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return booleans[position];
        }

        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            final ViewHolder viewHolder;

            if (convertView == null) {

                viewHolder = new ViewHolder();
                LayoutInflater vi = (LayoutInflater) _c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.fragment_dialog_semana_item, null);
                convertView.setTag(viewHolder);
            } else {

                viewHolder = (ViewHolder) convertView.getTag();

            }

            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.idChexbox);


            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.idChexbox);


            //Log.e(getClass().getName(),"Listadapter "+ " " +position+ " "+alertaPOJO.getIsSelectedArray()[position]);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                viewHolder.imageView.setBackground(alertaPOJO.getIsSelectedArray()[position] ?
                        getResources().getDrawable(R.drawable.ic_check_true) :
                        getResources().getDrawable(R.drawable.ic_check_false))
                ;
            } else {
                viewHolder.imageView.setBackgroundDrawable(alertaPOJO.getIsSelectedArray()[position] ?
                        getResources().getDrawable(R.drawable.ic_check_true) :
                        getResources().getDrawable(R.drawable.ic_check_false));
            }
            viewHolder.imageView.setSelected(alertaPOJO.getIsSelectedArray()[position]);


            viewHolder.tvDia = (CustomFontTextView) convertView.findViewById(R.id.idTVDia);
            viewHolder.tvDia.setText(_semana[position]);
            viewHolder.tvDia.setTextColor(
                    alertaPOJO.getIsSelectedArray()[position] ?
                            getResources().getColor(R.color.azulOscuro) :
                            getResources().getColor(R.color.negro)
            );


            return convertView;
        }


        private class ViewHolder {
            private ImageView imageView;

            private CustomFontTextView tvDia;


        }
    }

}




