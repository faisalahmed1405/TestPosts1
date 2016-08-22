package org.ceaiitm.ankitk97.testposts;

/**
 * Created by Ankitk97 on 17-08-2016.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Belal on 9/22/2015.
 */
public class ParseJSONPosts {
    public static String[] date;
    public static String[] names;
    public static String[] textData;

    public static final String JSON_ARRAY = "result";
    public static final String KEY_DATE = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_TEXT_DATA = "email";

    private JSONArray users = null;

    private String json;

    public ParseJSONPosts(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            date = new String[users.length()];
            names = new String[users.length()];
            textData = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                date[i] = jo.getString(KEY_DATE);
                names[i] = jo.getString(KEY_NAME);
                textData[i] = jo.getString(KEY_TEXT_DATA);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}