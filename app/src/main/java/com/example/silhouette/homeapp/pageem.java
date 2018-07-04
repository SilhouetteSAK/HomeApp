package com.example.silhouette.homeapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Silhouette on 2018/4/6.
 */

public class pageem extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.empage, container, false);
        LineChart chart = (LineChart) view.findViewById(R.id.echart);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴位置
        xAxis.setDrawGridLines(true);//不绘制网格线
        xAxis.setTextColor(Color.WHITE);// 设置轴标签的颜色。
        xAxis.setTextSize(11f);//设置轴标签的文字大小。
        xAxis.setAxisMinimum(0f);//设置最小值
        xAxis.setAxisMaximum(30f);//设置最大值
        ArrayList<Entry> poitList= new ArrayList<Entry>();
        for (int i= 0;i<30;i++){
            Random rand = new Random();
            int val = rand.nextInt(3) + 3;
            poitList.add(new Entry(i,val));
        }
        YAxis yAxis = chart.getAxisLeft();//获取左轴
        // YAxis rightAxis = chart.getAxisRight();//获取右轴
        chart.getAxisRight().setEnabled(false);//隐藏右轴  默认显示

        //设置Y轴最大最小值，不设置chart会自己计算
        yAxis.setAxisMinimum(0f);//设置最小值
        yAxis.setAxisMaximum(6f);//设置最大值
        yAxis.setDrawGridLines(true);//绘制网格线 默认为true
        yAxis.setTextColor(Color.WHITE);// 设置轴标签的颜色。
        yAxis.setTextSize(11f);//设置轴标签的文字大小。
        LineDataSet dataSet= new LineDataSet(poitList, "用电量");
        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        dataSet.setHighLightColor(Color.RED); // 设置点击某个点时，横竖两条线的颜色
        dataSet.setDrawValues(true);//在点上显示数值 默认true
        dataSet.setValueTextSize(12f);//数值字体大小，同样可以设置字体颜色、自定义字体等
        dataSet.setValueTextColor(Color.WHITE);
        LineData data = new LineData(dataSet);
        chart.setData(data);

        return view;
    }
}
