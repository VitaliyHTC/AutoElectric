package com.vitaliyhtc.autoelectric;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by VitaliyHTC on 01.07.2016.
 */
public class MainTabListAdapter extends ArrayAdapter<MainTabListItem> {
    private final Context context;
    private final ArrayList<MainTabListItem> modelsArrayList;

    public MainTabListAdapter(Context context, ArrayList<MainTabListItem> modelsArrayList) {
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

        MainTabListItem mainTabListItem = modelsArrayList.get(position);
        imgView.setImageResource(mainTabListItem.getIcon());
        titleView.setText(mainTabListItem.getTitle());

        View colorLabelView = rowView.findViewById(R.id.ColorLabelView);
        if(mainTabListItem.getMainListItemType().equals(MainListItemType.ItemsList)){
            colorLabelView.setBackgroundColor(context.getResources().getColor(R.color.ListItemToItemsListLabelBG));
        }

        return rowView;
    }
}
