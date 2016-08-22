package org.ceaiitm.ankitk97.testposts;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by Ankitk97 on 11-09-2015.
 */
public class Tab3 extends Fragment {


    public static final String JSON_URL = "http://ceaiitm.org/2016/main/androidapp/contacts.php";
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // RecyclerView rv = (RecyclerView) inflater.inflate(
        //        R.layout.tab_1, container, false);
        View v =inflater.inflate(R.layout.tab_3,container,false);

        listView = (ListView) v.findViewById(R.id.listViewContacts);
        sendRequest();

        return v;
    }


    public void sendRequest(){

        StringRequest stringRequest = new StringRequest(JSON_URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity().getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }){

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        ParseJSONContacts pj = new ParseJSONContacts(json);
        pj.parseJSON();
        final CustomListContacts cl = new CustomListContacts(getActivity(), ParseJSONContacts.fullName, ParseJSONContacts.dept1, ParseJSONContacts.dept2, ParseJSONContacts.position, ParseJSONContacts.phone);
        listView.setAdapter(cl);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Call")
                        .setMessage("Are you sure you want to Call?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                Intent callIntent = new Intent(Intent.ACTION_CALL);
                                String number = ParseJSONContacts.phone[position];
                                callIntent.setData(Uri.parse("tel:" + number));
                                getContext().startActivity(callIntent);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.sym_call_outgoing)
                        .show();
            }
        });
    }

}
