package com.vitaliyhtc.autoelectric;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by VitaliyHTC on 01.07.2016.
 */
public class MainTab1ListAdapter extends ArrayAdapter<MainTab1ListItem> {
    private final Context context;
    private final ArrayList<MainTab1ListItem> modelsArrayList;

    public MainTab1ListAdapter(Context context, ArrayList<MainTab1ListItem> modelsArrayList) {
        super(context, R.layout.main_tab_list_item, modelsArrayList);
        this.context = context;
        this.modelsArrayList = modelsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.main_tab_list_item, parent, false);

        ImageView imgView = (ImageView) rowView.findViewById(R.id.item_icon);
        TextView titleView = (TextView) rowView.findViewById(R.id.item_title);

        imgView.setImageResource(modelsArrayList.get(position).getIcon());
        titleView.setText(modelsArrayList.get(position).getTitle());

        return rowView;
    }
}
