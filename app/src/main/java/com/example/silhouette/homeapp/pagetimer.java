package com.example.silhouette.homeapp;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Silhouette on 2018/4/27.
 */



public class pagetimer extends Fragment implements AdapterView.OnItemClickListener,AdapterView.OnItemSelectedListener{

    private ListView listView;
    private SimpleAdapter simpleAdapter;
    private List<Map<String,Object>> dataList;

    private String[] str_title ={"定时1","定时2","定时3","定时4"} ;
    private String[] str_start = {"16:00","18:00", "13:00","09:00"};
    private String[] str_end = {"20:00","00:00","17:00","12:00"};
    private String[] str_temperature = {"24","26","20","17"};
    private ArrayList<String> title = new ArrayList<String>();
    private ArrayList<String> start = new ArrayList<String>();
    private ArrayList<String> end = new ArrayList<String>();
    private ArrayList<String> temperature = new ArrayList<String>();


    private Button new_btn;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timerpage, container, false);



        listView =  view.findViewById(R.id.timingList);
        dataList = new ArrayList<Map<String, Object>>();
        initArrayList();
        initData();

        simpleAdapter = new SimpleAdapter(getContext(),dataList,R.layout.list_item,new String[]{"title","start","end","temperature"},
                new int[]{R.id.item_title,R.id.item_start,R.id.item_end,R.id.temperature});
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemSelectedListener(this);

        new_btn = view.findViewById(R.id.new_btn);
        new_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //String ti = "定时"+ (title.length+1);
                String ti = "定时"+ (title.size()+1);
                String start_time = "00:00";
                String stop_time = "00:00";
                String tem = "20";

                Intent intent = new Intent();
                intent.setClass(getActivity(),timing_detail.class);
                intent.putExtra("title",ti);
                intent.putExtra("start",start_time);
                intent.putExtra("stop",stop_time);
                intent.putExtra("temperature",tem);
//                startActivityForResult(intent,99);
                startActivity(intent);
            }
        });

        return view;
    }

    private void initArrayList()
    {
        title.clear();
        start.clear();
        end.clear();
        temperature.clear();
        for (int i=0;i<str_title.length;i++)
        {
            title.add(str_title[i]);
            start.add(str_start[i]);
            end.add(str_end[i]);
            temperature.add(str_temperature[i]);
        }
    }

    private List<Map<String,Object>> initData()
    {


        for (int i=0;i<(title.size());i++)
        {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("title",title.get(i));
            map.put("start",start.get(i));
            map.put("end",start.get(i));
            map.put("temperature",temperature.get(i));
            dataList.add(map);
        }
        return dataList;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        String ti = title.get(position);
        String start_time = start.get(position);
        String stop_time = end.get(position);
        String tem = temperature.get(position);

        Intent intent = new Intent();
        intent.setClass(getActivity(),timing_detail.class);
        intent.putExtra("title",ti);
        intent.putExtra("start",start_time);
        intent.putExtra("stop",stop_time);
        intent.putExtra("temperature",tem);
//        startActivityForResult(intent,position);
        startActivity(intent);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}
