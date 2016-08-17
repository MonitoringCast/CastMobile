package com.mega.robert.chromecastmoneymaker;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by RTingirica on 17.08.2016.
 */
public class LayoutAdapter extends ArrayAdapter<ChartLayout> {

    public LayoutAdapter(Context context, ArrayList<ChartLayout> chartLayoutArrayList) {
        super(context, 0, chartLayoutArrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_layout, parent, false);
        }
        ChartLayout chartLayout = getItem(position);
        TextView name = (TextView) convertView.findViewById(R.id.tvLayoutName);
        TextView rows = (TextView) convertView.findViewById(R.id.tvLayoutRows);
        TextView columns = (TextView) convertView.findViewById(R.id.tvLayoutColumns);

        name.setText(chartLayout.name);
        rows.setText(chartLayout.rows);
        columns.setText(chartLayout.columns);

        return convertView;
    }
}
