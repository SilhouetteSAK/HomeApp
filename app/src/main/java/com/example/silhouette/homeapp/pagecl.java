package com.example.silhouette.homeapp;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.silhouette.homeapp.MainActivity.token;

/**
 * Created by Silhouette on 2018/4/2.
 */

public class pagecl extends Fragment{

    private ListView listView;
    private List<item> mData = null;
    private Context mContext;
    private itemAdapter mAdapter = null;
    private ImageButton cold;
    private ImageButton hot;
    private ImageButton night;
    private ImageButton up;
    private ImageButton down;
    private ImageButton wind;
    private ImageButton on;
    private Handler mHandler;
    private TextView textView;
    private int windspeed = 1;
    private pagehis ph;
    private boolean isOn = false;
    private boolean isCold = false;
    private boolean isHot = false;
    private int temperature = 26;
    private String t;
    private String w;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.clpage, container, false);
        textView = (TextView)view.findViewById(R.id.textView16);
        ph = (pagehis) getActivity().getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.vp + ":3");
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        //String r5 = msg.getData().getString("value");
                        String r6 = msg.getData().getString("code");
                        if(r6.equals("200")){
                            textView.setText(t + "℃");
                            //Toast.makeText(getActivity(),"27",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getActivity(),"操作失败",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 1:
                        //String r7 = msg.getData().getString("value");
                        String r8 = msg.getData().getString("code");
                        if(r8.equals("200")){
                            textView.setText(t + "℃");
                        }
                        else {
                            Toast.makeText(getActivity(),"操作失败",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2:
                        //String r3 = msg.getData().getString("value");
                        String r4 = msg.getData().getString("code");
                        if(r4.equals("200")){
                            mAdapter.remove(0);
                            mAdapter.add(0,new item("制冷模式", "开","4月18日"));
                            //Toast.makeText(getActivity(),"制冷成功",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getActivity(),"操作失败",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 3:
                        //在这里得到数据，并且可以直接更新UI
                        String data3 = (String)msg.obj;
                        //updateWeather();  //刷新数据
                        textView.setText(data3);
                    case 4:
                        //在这里得到数据，并且可以直接更新UI
                        String data4 = (String)msg.obj;
                        //updateWeather();  //刷新数据
                        textView.setText(data4);
                        break;
                    case 5:
                        //String r9 = msg.getData().getString("value");
                        String r0 = msg.getData().getString("code");
                        if(windspeed == 1 && r0.equals("200")){
                            mAdapter.remove(2);
                            mAdapter.add(2,new item("风速", "低","4月18日"));
                        }
                        else if(windspeed == 2 && r0.equals("200")){
                            mAdapter.remove(2);
                            mAdapter.add(2,new item("风速", "中","4月18日"));
                        }
                        else if(windspeed == 3 && r0.equals("200")){
                            mAdapter.remove(2);
                            mAdapter.add(2,new item("风速", "高","4月18日"));
                        }
                        else {
                            Toast.makeText(getActivity(),"操作失败",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 6:
                        //String r1 = msg.getData().getString("value");
                        String r2 = msg.getData().getString("code");
                        if(r2.equals("200")){
                            Toast.makeText(getActivity(),"开机成功",Toast.LENGTH_SHORT).show();
                        }
//                        else if(r1 == "0" && r2 == "200"){
//                            Toast.makeText(getActivity(),"关机成功",Toast.LENGTH_SHORT).show();
//                        }
                        else {
                            Toast.makeText(getActivity(),"操作失败",Toast.LENGTH_SHORT).show();
                        }
//                        String test = msg.getData().getString("value");
                        //Toast.makeText(getActivity(),r2,Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }

        };

        on=(ImageButton) view.findViewById(R.id.on);
        on.setBackgroundColor(Color.TRANSPARENT);
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FragmentActivity fragmentActivity = (FragmentActivity) getActivity();
                fragmentActivity.setFlag(0);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
                            FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
                            formBody.add("ordertype","1");//传递键值对参数
                            Request request = new Request.Builder().url("http://www.lacrazy.cn:8080/firstjsp2.0/HelloWorld").post(formBody.build()).build();
                            Response response = client.newCall(request).execute();
                            final String responseData = response.body().string();
                            //removeBOM(responseData);

                            //JSONObject jsonObject = new JSONObject(responseData);
                            //final String value = jsonObject.getString("value");
                            //final String code = jsonObject.getString("code");

                            Message msg =new Message();
                            msg.what = 6;
                            Bundle bundle = new Bundle();
                            //bundle.putString("value",responseData);
                            bundle.putString("code",responseData);
                            msg.setData(bundle);
                            //msg.obj = "数据";//可以是基本类型，可以是对象，可以是List、map等；
                            //完成之后发送消息给Handler
                            mHandler.sendMessage(msg);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        cold=(ImageButton) view.findViewById(R.id.cold);
        cold.setBackgroundColor(Color.TRANSPARENT);
        cold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(!isCold) {
//                    ph = (pagehis) getActivity().getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.vp + ":3");
//                    ph.setData(5);
//                    isCold = true;
//                }
//                if(isCold) {
//                    ph = (pagehis) getActivity().getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.vp + ":3");
//                    ph.setData(6);
//                    isCold = false;
//                }
                final FragmentActivity fragmentActivity = (FragmentActivity) getActivity();
                fragmentActivity.setFlag(5);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
                            FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
                            formBody.add("ordertype","1").add("pattern","3");//传递键值对参数
                            Request request = new Request.Builder().url("http://www.lacrazy.cn:8080/firstjsp2.0/HelloWorld").post(formBody.build()).build();
                            Response response = client.newCall(request).execute();
                            final String responseData = response.body().string();

//                            JSONObject jsonObject = new JSONObject(responseData);
//                            final String value = jsonObject.getString("value");
//                            final String code = jsonObject.getString("code");

                            Message msg =new Message();
                            msg.what = 2;
                            Bundle bundle = new Bundle();
                            //bundle.putString("value",value);
                            bundle.putString("code",responseData);
                            msg.setData(bundle);
                            //msg.obj = "数据";//可以是基本类型，可以是对象，可以是List、map等；
                            //完成之后发送消息给Handler
                            mHandler.sendMessage(msg);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        hot=(ImageButton) view.findViewById(R.id.hot);
        hot.setBackgroundColor(Color.TRANSPARENT);
        hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
                            FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
                            formBody.add("username","zhangsan");//传递键值对参数
                            Request request = new Request.Builder().url("http://www.baidu.com").post(formBody.build()).build();
                            Response response = client.newCall(request).execute();
                            final String responseData = response.body().string();
                            Message msg =new Message();
                            msg.what = 3;
                            msg.obj = "数据";//可以是基本类型，可以是对象，可以是List、map等；
                            //完成之后发送消息给Handler
                            mHandler.sendMessage(msg);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        night=(ImageButton) view.findViewById(R.id.night);
        night.setBackgroundColor(Color.TRANSPARENT);
        night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
                            FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
                            formBody.add("username","zhangsan");//传递键值对参数
                            Request request = new Request.Builder().url("http://www.baidu.com").post(formBody.build()).build();
                            Response response = client.newCall(request).execute();
                            final String responseData = response.body().string();
                            Message msg =new Message();
                            msg.what = 4;
                            msg.obj = "数据";//可以是基本类型，可以是对象，可以是List、map等；
                            //完成之后发送消息给Handler
                            mHandler.sendMessage(msg);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        up=(ImageButton) view.findViewById(R.id.up);
        up.setBackgroundColor(Color.TRANSPARENT);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ph = (pagehis) getActivity().getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.vp + ":3");
//                ph.setData(3);
                final FragmentActivity fragmentActivity = (FragmentActivity) getActivity();
                fragmentActivity.setFlag(3);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
                            temperature++;
                            t = String.valueOf(temperature);
                            FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
                            formBody.add("ordertype","1").add("temperature",t);//传递键值对参数
                            Request request = new Request.Builder().url("http://www.lacrazy.cn:8080/firstjsp2.0/HelloWorld").post(formBody.build()).build();
                            Response response = client.newCall(request).execute();
                            final String responseData = response.body().string();

                            //JSONObject jsonObject = new JSONObject(responseData);
                            //final String value = jsonObject.getString("value");
                            //final String code = jsonObject.getString("code");

                            Message msg =new Message();
                            msg.what = 0;
                            Bundle bundle = new Bundle();
                            //bundle.putString("value",value);
                            bundle.putString("code",responseData);
                            msg.setData(bundle);
                            //msg.obj = "数据";//可以是基本类型，可以是对象，可以是List、map等；
                            //完成之后发送消息给Handler
                            mHandler.sendMessage(msg);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        down=(ImageButton) view.findViewById(R.id.down);
        down.setBackgroundColor(Color.TRANSPARENT);
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ph = (pagehis) getActivity().getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.vp + ":3");
//                ph.setData(4);
                final FragmentActivity fragmentActivity = (FragmentActivity) getActivity();
                fragmentActivity.setFlag(4);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
                            temperature--;
                            t = String.valueOf(temperature);
                            FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
                            formBody.add("ordertype","1").add("temperature",t);//传递键值对参数
                            Request request = new Request.Builder().url("http://www.lacrazy.cn:8080/firstjsp2.0/HelloWorld").post(formBody.build()).build();
                            Response response = client.newCall(request).execute();
                            final String responseData = response.body().string();

//                            JSONObject jsonObject = new JSONObject(responseData);
//                            final String value = jsonObject.getString("value");
//                            final String code = jsonObject.getString("code");

                            Message msg =new Message();
                            msg.what = 1;
                            Bundle bundle = new Bundle();
                            //bundle.putString("value",value);
                            bundle.putString("code",responseData);
                            msg.setData(bundle);
                            //msg.obj = "数据";//可以是基本类型，可以是对象，可以是List、map等；
                            //完成之后发送消息给Handler
                            mHandler.sendMessage(msg);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        wind=(ImageButton) view.findViewById(R.id.wind);
        wind.setBackgroundColor(Color.TRANSPARENT);
        wind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ph = (pagehis) getActivity().getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.vp + ":3");
//                ph.setData(2);
                final FragmentActivity fragmentActivity = (FragmentActivity) getActivity();
                fragmentActivity.setFlag(2);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
                            if(windspeed != 3){
                                windspeed++;
                            }
                            else{
                                windspeed = 1;
                            }
                            w = String.valueOf(windspeed);
                            FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
                            formBody.add("ordertype","1").add("windspeed",w);//传递键值对参数
                            Request request = new Request.Builder().url("http://www.lacrazy.cn:8080/firstjsp2.0/HelloWorld").post(formBody.build()).build();
                            Response response = client.newCall(request).execute();
                            final String responseData = response.body().string();

//                            JSONObject jsonObject = new JSONObject(responseData);
//                            final String value = jsonObject.getString("value");
//                            final String code = jsonObject.getString("code");

                            Message msg =new Message();
                            msg.what = 5;
                            Bundle bundle = new Bundle();
                            //bundle.putString("value",value);
                            bundle.putString("code",responseData);
                            msg.setData(bundle);
                            //msg.obj = "数据";//可以是基本类型，可以是对象，可以是List、map等；
                            //完成之后发送消息给Handler
                            mHandler.sendMessage(msg);


//                            JSONObject jsonObject = new JSONObject(responseData);
//                            final String title = jsonObject.getString("title");

//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    try {
//                                        if (title.equals(tn)) {
//                                            Toast.makeText(addTas.this, "创建成功", Toast.LENGTH_SHORT).show();
//                                            Thread.sleep(2000);
//                                            finish();
//                                        } else {
//                                            Toast.makeText(addTas.this, "创建失败", Toast.LENGTH_SHORT).show();
//                                        }
//                                    }catch (Exception e){
//                                        e.printStackTrace();
//                                    }
//                                }
//                            });
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        listView = (ListView) view.findViewById(R.id.lv1);
        mData = new LinkedList<item>();
        mData.add(new item("制冷模式", "关","4月18日"));
        mData.add(new item("制热模式", "关","4月18日"));
        mData.add(new item("风速", "低","4月18日"));
        mData.add(new item("夜间模式", "关","4月18日"));
        mAdapter = new itemAdapter((LinkedList<item>) mData, getContext());
        listView.setAdapter(mAdapter);
        return view;
    }

    public static final String removeBOM(String data) {
        if (TextUtils.isEmpty(data)) {
            return data;
        }
        if (data.startsWith("\ufeff")) {
            return data.substring(1);
        } else {
            return data;
        }
    }
}
