package com.example.silhouette.homeapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.picker.NumberPicker;
import cn.addapp.pickers.picker.SinglePicker;

/**
 * Created by Administrator on 2018/5/5.
 */

public class timing_detail extends AppCompatActivity implements View.OnClickListener{

    private String[] mode = new String[] {"自动","制冷","抽湿","送风"};
    private String[] speed = new String[] {"一级","二级","三级"};
    private String[] repeat = new String[] {"星期一","星期二","星期三","星期四","星期五","星期六","星期日"};
    private boolean[] repeat_chosen = new boolean[] {true,true,true,true,true,true,true};

    private Button del_btn;

    private Button btn_start_time;
    private Button btn_stop_time;
    private Button mode_btn;
    private Button speed_btn;
    private Button repeat_btn;
    private Button temperature_btn;
    private TextView tv_start_time;
    private TextView tv_stop_time;
    private TextView tv_temperature;
    private TextView mode_tv;
    private TextView speed_tv;


    private String title;
    private String start_time;
    private String stop_time;
    private String temperature;

    private  int hour;
    private  int minute;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timing_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv_temperature = (TextView) findViewById(R.id.temperature_tv);


        tv_start_time = (TextView) findViewById(R.id.start_time_tv);
        btn_start_time =(Button) findViewById(R.id.start_time_btn);
        btn_start_time.setOnClickListener(this);

        tv_stop_time = (TextView)findViewById(R.id.stop_time_tv);
        btn_stop_time = (Button)findViewById(R.id.stop_time_btn);
        btn_stop_time.setOnClickListener(this);

        mode_tv = (TextView)findViewById(R.id.mode_tv);
        speed_tv = (TextView)findViewById(R.id.speed_tv);


        final Intent intent = getIntent();
        title = intent.getStringExtra("title");
        start_time = intent.getStringExtra("start");
        stop_time = intent.getStringExtra("stop");
        temperature = intent.getStringExtra("temperature");

        setTitle(title);
        tv_start_time.setText(start_time);
        tv_stop_time.setText(stop_time);
        tv_temperature.setText(temperature);

        mode_btn =(Button) findViewById(R.id.mode_btn);
        speed_btn = (Button)findViewById(R.id.speed_btn);
        repeat_btn =(Button)findViewById(R.id.repeat_btn);
        temperature_btn = (Button)findViewById(R.id.temperature_btn);
        mode_btn.setOnClickListener(this);
        speed_btn.setOnClickListener(this);
        repeat_btn.setOnClickListener(this);
        temperature_btn.setOnClickListener(this);

        del_btn = (Button)findViewById(R.id.delete_timing_btn);
        del_btn.setOnClickListener(this);

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                Intent intent1 = new Intent();
//                setResult(2,intent1);
                finish();
            case R.id.save:
                start_time = tv_start_time.getText().toString();
                stop_time = tv_stop_time.getText().toString();
                temperature = tv_temperature.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("start",start_time);
                intent.putExtra("stop",stop_time);
                intent.putExtra("temperature",temperature);
//                setResult(1,intent);
                finish();
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {

            case R.id.delete_timing_btn:
//                Intent intent = new Intent();
//                setResult(3,intent);
                finish();
                break;
            case R.id.start_time_btn:

                onTimePicker("开始时间",tv_start_time);

                break;
            case R.id.stop_time_btn:

                onTimePicker("关闭时间",tv_stop_time);
                break;
            case R.id.temperature_btn:
                onNumberPicker("温度",tv_temperature);
                break;

            case R.id.mode_btn:
                onSinglePicker("模式",mode_tv,mode);
                break;
            case R.id.speed_btn:
                onSinglePicker("风速",speed_tv,speed);
                break;
            case R.id.repeat_btn:

                AlertDialog ad = new AlertDialog.Builder(this).setTitle("重复").setMultiChoiceItems(repeat,repeat_chosen, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked)
                        {
                            repeat_chosen[which] = true;
                        }
                        else
                        {
                            repeat_chosen[which] = false;
                        }

                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String s = "";
                        for(int i = 0;i<repeat_chosen.length;i++)
                        {
                            switch(i)
                            {
                                case 0:
                                    if (repeat_chosen[i])
                                        s+="一 ";
                                    break;
                                case 1:
                                    if (repeat_chosen[i])
                                        s+="二 ";
                                    break;
                                case 2:
                                    if (repeat_chosen[i])
                                        s+="三 ";
                                    break;
                                case 3:
                                    if (repeat_chosen[i])
                                        s+="四 ";
                                    break;
                                case 4:
                                    if (repeat_chosen[i])
                                        s+="五 ";
                                    break;
                                case 5:
                                    if (repeat_chosen[i])
                                        s+="六 ";
                                    break;
                                case 6:
                                    if (repeat_chosen[i])
                                        s+="日 ";
                                    break;
                            }
                        }
                        TextView textView = (TextView) findViewById(R.id.repeat_tv);
                        textView.setText(s);

                    }
                }).create();
                ad.show();
        }

    }

    public void onSinglePicker(String title, final TextView textView, String[] items)
    {
        cn.addapp.pickers.picker.SinglePicker picker = new SinglePicker(this,items);
        picker.setTextSize(30);
        picker.setTitleText(title);
        picker.setCanLoop(false);
        picker.setTopBackgroundColor(getResources().getColor(R.color.colorBackground));
        picker.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        picker.setTitleTextSize(20);
        picker.setSubmitTextColor(getResources().getColor(R.color.colorWhite));
        picker.setSubmitTextSize(20);
        picker.setCancelTextColor(getResources().getColor(R.color.colorWhite));
        picker.setCancelTextSize(20);
        picker.setWheelModeEnable(true);
        picker.setBackgroundColor(getResources().getColor(R.color.colorBackground));
        picker.setSelectedItem(textView.getText().toString());
        picker.setOnItemPickListener(new OnItemPickListener() {
            @Override
            public void onItemPicked(int i, Object o) {
                textView.setText(o.toString());
            }
        });
        picker.show();
    }

    public void onTimePicker(String title, final TextView textView)
    {
        cn.addapp.pickers.picker.TimePicker timePicker = new cn.addapp.pickers.picker.TimePicker(this);
        timePicker.setRangeStart(0,0);
        timePicker.setRangeEnd(23,59);
        timePicker.setCanLoop(false);
        timePicker.setBackgroundColor(getResources().getColor(R.color.colorBackground));
        timePicker.setTitleText(title);
        timePicker.setTitleTextSize(20);
        timePicker.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        timePicker.setTopBackgroundColor(getResources().getColor(R.color.colorBackground));
        timePicker.setSubmitTextColor(getResources().getColor(R.color.colorWhite));
        timePicker.setSubmitTextSize(20);
        timePicker.setCancelTextColor(getResources().getColor(R.color.colorWhite));
        timePicker.setCancelTextSize(20);
        timePicker.setTextSize(30);
        timePicker.setWheelModeEnable(true);
        timePicker.setOnTimePickListener(new cn.addapp.pickers.picker.TimePicker.OnTimePickListener() {
            @Override
            public void onTimePicked(String hour, String minute) {
                textView.setText(hour+":"+minute);
            }
        });
        timePicker.show();
    }

    public void onNumberPicker(String title, final TextView textView)
    {
        cn.addapp.pickers.picker.NumberPicker numberPicker = new cn.addapp.pickers.picker.NumberPicker(this);
        numberPicker.setRange(16,30,1);
        numberPicker.setWheelModeEnable(true);
        numberPicker.setCanLoop(false);
        numberPicker.setBackgroundColor(getResources().getColor(R.color.colorBackground));
        numberPicker.setTitleText(title);
        numberPicker.setTitleTextSize(20);
        numberPicker.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        numberPicker.setTopBackgroundColor(getResources().getColor(R.color.colorBackground));
        numberPicker.setSubmitTextColor(getResources().getColor(R.color.colorWhite));
        numberPicker.setSubmitTextSize(20);
        numberPicker.setCancelTextColor(getResources().getColor(R.color.colorWhite));
        numberPicker.setCancelTextSize(20);
        numberPicker.setTitleText(title);
        numberPicker.setSelectedItem(Integer.parseInt(textView.getText().toString()));
        numberPicker.setTextSize(30);
        numberPicker.setLabel("℃");
        numberPicker.setOnNumberPickListener(new NumberPicker.OnNumberPickListener() {
            @Override
            public void onNumberPicked(int i, Number number) {
                textView.setText(number.toString());
            }
        });
        numberPicker.show();
    }


}
