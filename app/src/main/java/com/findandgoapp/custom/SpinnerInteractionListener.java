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

package com.findandgoapp.custom;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.findandgoapp.activity.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by juanpolea on 23/03/17.
 * <p>
 * This file is part of FindAndGoApp.
 * <p>
 * FindAndGoApp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * FindAndGoApp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

class SpinnerInteractionListener implements AdapterView.OnItemSelectedListener, View.OnTouchListener {

    private boolean userSelect = false;
    private Context c;


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        userSelect = true;
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        if (userSelect) {
            // Your selection handling code here

            userSelect = false;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * A partir de la provincia, devuelve las localidades de ésta.
     *
     * @param s
     */
    private void selectLocalidad(final Context context, final AutoCompleteTextView autoCompleteTextView, final int s) {


        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, context.getResources().getString(R.string.sUrl_selectLocalidad),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Log.e(getClass().getName(), "selectLocalidad " + response);
                        try {
                            JSONArray jsonArray = new JSONArray();
                            JSONObject json = new JSONObject(response);
                            ArrayList<String> array = new ArrayList<>();
                            jsonArray = json.getJSONArray(context.getString(R.string.result));

                            int success = json.getInt(context.getString(R.string.success));
                            if (success == 1) {
                                if (jsonArray.length() > 0) {

                                    for (int i = 0; i < jsonArray.length(); i++) {

                                        JSONObject jsonObject = new JSONObject();
                                        jsonObject = jsonArray.getJSONObject(i);

                                        array.add(jsonObject.getString(context.getString(R.string.localidad)));

                                    }

                                    if (array.size() > 0) {

                                        String[] string = new String[array.size()];
                                        string = array.toArray(string);

                                        ArrayAdapter<String> adapter = new ArrayAdapter<>
                                                (context, android.R.layout.simple_list_item_1, string);
                                        autoCompleteTextView.setAdapter(adapter);


                                    }
                                }
                            }


                            Log.e(getClass().getName(), array.toString());
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

                return formatoIdProvincia(s);
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);


    }


    private Map<String, String> formatoIdProvincia(int idProvincia) {
        Map<String, String> params = new HashMap<>();


        params.put(c.getString(R.string.sidProvincia), String.valueOf(idProvincia));

        return params;

    }


}
