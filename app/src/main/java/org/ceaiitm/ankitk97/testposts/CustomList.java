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

public class CustomList extends ArrayAdapter<String> {
    private String[] text_data;
    private String[] name;
    private String[] date;
    private Activity context;

    public CustomList(Activity context, String[] name, String[] date, String[] text_data) {
        super(context, R.layout.posts_view_layout, name);
        this.context = context;
        this.name = name;
        this.date = date;
        this.text_data = text_data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.posts_view_layout, null);
        TextView textViewId = (TextView) listViewItem.findViewById(R.id.event_name);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.event_date);
        TextView textViewEmail = (TextView) listViewItem.findViewById(R.id.event_text);

        textViewId.setText(name[position]);
        textViewName.setText(date[position]);
        textViewEmail.setText(text_data[position]);

        return listViewItem;
    }
}