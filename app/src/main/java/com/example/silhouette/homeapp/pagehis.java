package com.example.silhouette.homeapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Silhouette on 2018/4/8.
 */

public class pagehis extends Fragment {

    private ListView lv;
    private List<historyItem> hData = null;
    private Context hContext;
    private historyItemAdapter hAdapter = null;
    private SwipeRefreshLayout SL;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hispage, container, false);
        lv=(ListView)view.findViewById(R.id.his_lv);
        SL = (SwipeRefreshLayout)view.findViewById(R.id.sl);
        hData = new LinkedList<historyItem>();
        SL.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                int flag = ((FragmentActivity) getActivity()).getFlag();
                switch (flag) {
                    case 0:
                        hData.add(new historyItem("开机", "21:08",R.mipmap.kai));
                        break;
                    case 1:
                        hData.add(new historyItem("关机", "8:03",R.mipmap.guan));
                        break;
                    case 2:
                        hData.add(new historyItem("改变风速", "21:14",R.mipmap.feng));
                        break;
                    case 3:
                        hData.add(new historyItem("升高温度", "13:15",R.mipmap.sheng));
                        break;
                    case 4:
                        hData.add(new historyItem("降低温度", "13:15",R.mipmap.jiang));
                        break;
                    case 5:
                        hData.add(new historyItem("开启制冷模式", "13:12",R.mipmap.leng));
                        break;
                    case 6:
                        hData.add(new historyItem("关闭制冷模式", "13:12",R.mipmap.leng));
                        break;
                    case 7:
                        hData.add(new historyItem("开启制热模式", "13:12",R.mipmap.re));
                        break;
                    case 8:
                        hData.add(new historyItem("关闭制热模式", "13:12",R.mipmap.re));
                        break;
                }
                SL.setRefreshing(false);
            }
        });
        hAdapter = new historyItemAdapter((LinkedList<historyItem>) hData, getContext());
        lv.setAdapter(hAdapter);
        return view;
    }
}
