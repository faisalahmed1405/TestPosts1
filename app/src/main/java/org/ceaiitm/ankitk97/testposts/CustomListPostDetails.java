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

public class CustomListPostDetails extends ArrayAdapter<String> {
    public String[] text_data;
    public String[] name;
    public String[] date;
    public String[] files;
    private Activity context;

    public CustomListPostDetails(Activity context, String[] name, String[] date, String[] text_data,  String[] files) {
        super(context, R.layout.posts_view_layout, name);
        this.context = context;
        this.name = name;
        this.date = date;
        this.text_data = text_data;
        this.files = files;
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