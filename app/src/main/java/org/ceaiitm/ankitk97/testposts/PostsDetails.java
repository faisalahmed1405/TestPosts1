package org.ceaiitm.ankitk97.testposts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class PostsDetails extends AppCompatActivity {

    public static final String JSON_URL = "http://ceaiitm.org/2016/main/androidapp/postdetails.php";
    public static final String EXTRA_POSTID = "event_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_details);

        Intent intent = getIntent();
        String id = intent.getStringExtra(EXTRA_POSTID);

        sendRequest(id);

    }

    public void sendRequest(final String postID){

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
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                Log.d("postID", postID);
                params.put("ID",postID);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        ParseJSONPostDetails pj = new ParseJSONPostDetails(json);
        pj.parseJSON();
        //final CustomListPostDetails cl = new CustomListPostDetails(PostsDetails.this, ParseJSONPostDetails.names, ParseJSONPostDetails.date, ParseJSONPostDetails.textData, ParseJSONPostDetails.imageFiles);

        TextView name = (TextView) findViewById(R.id.postDetails_name);
        TextView date = (TextView) findViewById(R.id.postDetails_date);
        TextView textData = (TextView) findViewById(R.id.postDetails_text_data);
        TextView images = (TextView) findViewById(R.id.postDetails_images);

        name.setText(ParseJSONPostDetails.names[0]);
        date.setText(ParseJSONPostDetails.date[0]);
        textData.setText(ParseJSONPostDetails.textData[0]);
        images.setText(ParseJSONPostDetails.imageFiles[0]);

    }
}
