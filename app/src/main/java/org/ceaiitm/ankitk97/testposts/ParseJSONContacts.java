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
public class ParseJSONContacts {
    public static String[] fullName;
    public static String[] dept1;
    public static String[] dept2;
    public static String[] position;
    public static String[] phone;

    public static final String JSON_ARRAY = "result";
    public static final String KEY_NAME = "fullname";
    public static final String KEY_DEPT1 = "department1";
    public static final String KEY_DEPT2 = "department2";
    public static final String KEY_POSITION = "position";
    public static final String KEY_PHONE = "phone";

    private JSONArray users = null;

    private String json;

    public ParseJSONContacts(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            fullName = new String[users.length()];
            dept1 = new String[users.length()];
            dept2 = new String[users.length()];
            position = new String[users.length()];
            phone = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                fullName[i] = jo.getString(KEY_NAME);
                dept1[i] = jo.getString(KEY_DEPT1);
                dept2[i] = jo.getString(KEY_DEPT2);
                position[i] = jo.getString(KEY_POSITION);
                phone[i] = jo.getString(KEY_PHONE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}