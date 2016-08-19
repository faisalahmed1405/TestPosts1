package org.ceaiitm.ankitk97.testposts;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


/**
 * Created by Edwin on 15/02/2015.
 */
public class Tab1 extends Fragment {

    public static final String JSON_URL = "http://ceaiitm.org/2016/main/androidapp/testPosts.php";
    private Button buttonGet;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // RecyclerView rv = (RecyclerView) inflater.inflate(
       //        R.layout.tab_1, container, false);
        View v =inflater.inflate(R.layout.tab_1,container,false);

        listView = (ListView) v.findViewById(R.id.listView);
        //buttonGet = (Button) v.findViewById(R.id.buttonGet);
        sendRequest();
        /*buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }
        });*/
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
                        Toast.makeText(getActivity().getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();
        CustomList cl = new CustomList(getActivity(), ParseJSON.ids,ParseJSON.names,ParseJSON.emails);
        listView.setAdapter(cl);
    }
}
