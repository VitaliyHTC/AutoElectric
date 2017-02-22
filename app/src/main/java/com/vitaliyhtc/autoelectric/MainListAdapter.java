package com.vitaliyhtc.autoelectric;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainListAdapter extends ArrayAdapter<MainListItem> {
    private final Context context;
    private final ArrayList<MainListItem> models;

    public MainListAdapter(Context context, ArrayList<MainListItem> models) {
        super(context, R.layout.main_tab_list_item, models);
        this.context = context;
        this.models = models;
    }

    @Override
    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.main_tab_list_item, parent, false);

        ImageView imgView = (ImageView) rowView.findViewById(R.id.item_icon);
        TextView titleView = (TextView) rowView.findViewById(R.id.item_title);

        MainListItem mainListItem = models.get(position);
        imgView.setImageResource(mainListItem.getIcon());
        titleView.setText(mainListItem.getTitle());

        View colorLabelView = rowView.findViewById(R.id.ColorLabelView);
        if(mainListItem.getMainListItemType().equals(MainListItemType.ItemsList)){
            colorLabelView.setBackgroundColor(context.getResources().getColor(R.color.ListItemToItemsListLabelBG));
        }

        return rowView;
    }
}
