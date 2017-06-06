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
import android.widget.ListView;
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
import com.findandgoapp.pojo.UsuarioPOJO;
import com.findandgoapp.tools.Utilidades;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Juan F. Mateos
 */
public class FragmentDialogAdminPenalizacion extends DialogFragment {


    private static final java.lang.String ARG_PARAM1 = "penalizacion";
    private static final java.lang.String ARG_PARAM2 = "idSesion";
    private static final String TAG = "DgAdFragPena";
    private Button buttonOK, buttonCancel;
    private ListView list;
    private static UsuarioPOJO _usp;
    private NoticeDialogListener mCallback;

    public static FragmentDialogAdminPenalizacion newInstance(int idUsuario) {
        FragmentDialogAdminPenalizacion fragmentDialog = new FragmentDialogAdminPenalizacion();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, idUsuario);
        args.putSerializable(ARG_PARAM2, 1);
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

    public interface NoticeDialogListener {
        void onDialogPositiveClick();

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
        CustomFontTextView textView = ((CustomFontTextView) v.findViewById(R.id.TvTituloMenuComentarios));
        textView.setText(getActivity().getResources().getString(R.string.sElijaPenalizacion));
        list = ((ListView) v.findViewById(R.id.listaMenuPenalizaciones));


        _usp = i.getParcelableExtra(ARG_PARAM1);


        _usp.setItems(getResources().getStringArray(R.array.adminPenaliza));

        ListAdapterDialogo listAdapter = new ListAdapterDialogo(v.getContext(), _usp);
        getList().setAdapter(listAdapter);
        addListenerToListView(v.getContext(), list);


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


    /**
     * @param context
     * @param listView
     */
    private void addListenerToListView(final Context context, final ListView listView) {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                if (position == 0)

                {
                    _usp.setI_motivo(10);
                    _usp.setI_tipoPenalizacion(1);
                    Log.e(TAG, "Motivo " + String.valueOf(_usp.getI_motivo()));


                } else if (position == 1)

                {

                    _usp.setI_motivo(7);
                    _usp.setI_tipoPenalizacion(1);
                    Log.e(TAG, "Motivo " + String.valueOf(_usp.getI_motivo()));


                } else if (position == 2)

                {

                    _usp.setI_motivo(9);
                    _usp.setI_tipoPenalizacion(1);
                    Log.e(TAG, "Motivo " + String.valueOf(_usp.getI_motivo()));


                } else {
                    _usp.setI_motivo(11);
                    _usp.setI_tipoPenalizacion(1);
                }
                penalizarUsuario();

            }

        });
    }

    /**
     *
     */
    private class ListAdapterDialogo extends BaseAdapter {

        final Context _c;
        final LinkedList<String> linkedList = new LinkedList<>();

        public ListAdapterDialogo(Context c, UsuarioPOJO usuario) {

            Collections.addAll(linkedList, usuario.getItems());

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

                Log.e(TAG, "if == null");
                viewHolder = new ViewHolder();
                LayoutInflater vi = (LayoutInflater) _c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.fragment_admin_penaliza, null);
                convertView.setTag(viewHolder);
            } else {

                viewHolder = (ViewHolder) convertView.getTag();

            }


            viewHolder.tvTexto = (CustomFontTextView) convertView.findViewById(R.id.tvAdminPenaliza);
            viewHolder.tvTexto.setText(linkedList.get(position));
            viewHolder.tvTexto.setBackgroundColor(_c.getResources().getColor(R.color.transparente));

            return convertView;
        }


        private class ViewHolder {
            TextView tvTexto;

        }
    }

    private void penalizarUsuario() {

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getActivity().getResources().getString(R.string.sUrl_insertUsuarioPenalizacion),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Utilidades utilidades = new Utilidades();
                        Log.e(getClass().getName(), "penalizarUsuario " + response);

                        dismiss();
                        try {

                            JSONObject json = new JSONObject(response);

                            int success;
                            success = json.getInt(getString(R.string.success));
                            if (success != 1) {
                                utilidades.errorConsultaBBDD(getActivity(), getActivity().getResources().getString(R.string.sErrorBBDD));
                            }
                            if (success == 1) {

                                mCallback.onDialogPositiveClick();
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

                return _usp.formatoPenalizacion();
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

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

}
