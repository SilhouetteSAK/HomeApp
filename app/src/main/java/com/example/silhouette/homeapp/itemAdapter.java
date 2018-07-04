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
 * Created by Silhouette on 2018/4/2.
 */

public class itemAdapter extends BaseAdapter {

    private LinkedList<item> mData;
    private Context mContext;

    public itemAdapter(LinkedList<item> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
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
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item,parent,false);
        TextView txt_aContent = (TextView) convertView.findViewById(R.id.textView2);
        TextView txt_aResult = (TextView) convertView.findViewById(R.id.textView3);
        TextView txt_aAc = (TextView) convertView.findViewById(R.id.textView);
        txt_aContent.setText(mData.get(position).getContent());
        txt_aResult.setText(mData.get(position).getResult());
        txt_aAc.setText(mData.get(position).getAc());
        return convertView;
    }

    public void add(int position,item i){
        if (mData == null) {
            mData = new LinkedList<>();
        }
        mData.add(position,i);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        if(mData != null) {
            mData.remove(position);
        }
        notifyDataSetChanged();
    }
}
