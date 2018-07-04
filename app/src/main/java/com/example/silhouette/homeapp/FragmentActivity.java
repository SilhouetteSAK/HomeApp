package com.example.silhouette.homeapp;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FragmentActivity extends AppCompatActivity {

    private MainFragment mf;
    static String CURRENT_FRAGMENT;
    private int f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        CURRENT_FRAGMENT = MainFragment.class.getName();
        if(mf==null){
            mf = new MainFragment();
            fm.beginTransaction().add(R.id.fmcontent,mf,mf.getClass().getName()).commit();
        }else {
            fm.beginTransaction().show(mf).commit();
        }
        fm.beginTransaction().show(mf).commit();
        mf = new MainFragment();
        CURRENT_FRAGMENT = MainFragment.class.getName();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fmcontent, mf, MainFragment.class.getName()).commit();
    }

    public void setFlag(int f){
        this.f = f;
    }
    public int getFlag(){
        return f;
    }
}
