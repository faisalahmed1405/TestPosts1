package org.ceaiitm.ankitk97.testposts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by Edwin on 15/02/2015.
 */
public class Tab1 extends Fragment {

    public static final String JSON_URL = "http://ceaiitm.org/2016/main/androidapp/testPosts2.php";
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // RecyclerView rv = (RecyclerView) inflater.inflate(
       //        R.layout.tab_1, container, false);
        View v =inflater.inflate(R.layout.tab_1,container,false);
        listView = (ListView) v.findViewById(R.id.listView);
        sendRequest();

        return v;
    }

    public void sendRequest(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL,

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
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("name","Ankit");
                params.put("id","1233");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        ParseJSONPosts pj = new ParseJSONPosts(json);
        pj.parseJSON();
        final CustomListPosts cl = new CustomListPosts(getActivity(), ParseJSONPosts.names, ParseJSONPosts.date, ParseJSONPosts.textData);
        listView.setAdapter(cl);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String names = cl.name[position];

                Context context = getContext();
                Intent intent = new Intent(context, PostsDetails.class);
                intent.putExtra(PostsDetails.EXTRA_POSTID, names);

                context.startActivity(intent);

            }
        });
    }
}
