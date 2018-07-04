package com.example.silhouette.homeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by Silhouette on 2018/4/8.
 */

public class historyItemAdapter extends BaseAdapter {

    private LinkedList<historyItem> hData;
    private Context hContext;

    public historyItemAdapter(LinkedList<historyItem> hData, Context hContext) {
        this.hData = hData;
        this.hContext = hContext;
    }

    @Override
    public int getCount() {
        return hData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(hContext).inflate(R.layout.historyitem,parent,false);
        ImageView img_icon = (ImageView) convertView.findViewById(R.id.iv1);
        TextView txt_aOperation = (TextView) convertView.findViewById(R.id.textView13);
        TextView txt_aTime = (TextView) convertView.findViewById(R.id.textView15);
        //img_icon.setBackgroundResource(mData.get(position).getIcon());
        img_icon.setImageResource(hData.get(position).getIcon());
        txt_aOperation.setText(hData.get(position).getOperation());
        txt_aTime.setText(hData.get(position).getTime());
        return convertView;
    }
}
