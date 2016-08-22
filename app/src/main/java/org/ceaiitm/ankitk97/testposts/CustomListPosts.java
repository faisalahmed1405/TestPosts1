package org.ceaiitm.ankitk97.testposts;

/**
 * Created by Ankitk97 on 17-08-2016.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomListPosts extends ArrayAdapter<String> {
    public String[] text_data;
    public String[] id;
    public String[] name;
    public String[] date;
    private Activity context;

    public CustomListPosts(Activity context, String[] id, String[] name, String[] date, String[] text_data) {
        super(context, R.layout.posts_view_layout, name);
        this.context = context;
        this.id = id;
        this.name = name;
        this.date = date;
        this.text_data = text_data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.posts_view_layout, null);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.event_name);
        TextView textViewId = (TextView) listViewItem.findViewById(R.id.event_date);
        TextView textViewEmail = (TextView) listViewItem.findViewById(R.id.event_text);

        textViewId.setText(date[position]);
        textViewName.setText(name[position]);
        textViewEmail.setText(text_data[position]);

        return listViewItem;
    }
}