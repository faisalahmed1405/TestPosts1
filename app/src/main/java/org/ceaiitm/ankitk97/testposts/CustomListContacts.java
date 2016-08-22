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

public class CustomListContacts extends ArrayAdapter<String> {

    public String[] name;
    public String[] dept1;
    public String[] dept2;
    public String[] position;
    public String[] phone;

    private Activity context;

    public CustomListContacts(Activity context, String[] name, String[] dept1, String[] dept2, String[] position, String[] phone) {
        super(context, R.layout.contacts_view_layout, name);
        this.context = context;
        this.name = name;
        this.dept1 = dept1;
        this.dept2 = dept2;
        this.position = position;
        this.phone = phone;
    }

    @Override
    public View getView(int viewPosition, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.contacts_view_layout, null);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.contacts_name);
        TextView textViewDept1 = (TextView) listViewItem.findViewById(R.id.contacts_dept1);
        TextView textViewDept2 = (TextView) listViewItem.findViewById(R.id.contacts_dept2);
        TextView textViewPos = (TextView) listViewItem.findViewById(R.id.contacts_position);
        TextView textViewPhone = (TextView) listViewItem.findViewById(R.id.contacts_phone);

        textViewName.setText(name[viewPosition]);
        textViewDept1.setText(dept1[viewPosition]);
        textViewDept2.setText(dept2[viewPosition]);
        textViewPos.setText(position[viewPosition]);
        textViewPhone.setText(phone[viewPosition]);

        return listViewItem;
    }
}