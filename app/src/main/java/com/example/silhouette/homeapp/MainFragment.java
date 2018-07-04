package com.example.silhouette.homeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Silhouette on 2018/4/2.
 */

public class MainFragment extends Fragment {

    private ViewPager vp;
    private RadioGroup rg;
    private List<Fragment> pagerList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.mainfragment, container, false);
        Intent intent = getActivity().getIntent();
        vp = (ViewPager) view.findViewById(R.id.vp);
        rg = (RadioGroup) view.findViewById(R.id.RG);
        initView();
        SectionsPagerAdapter mPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
        vp.setAdapter(mPagerAdapter);
        rg.check(R.id.btn1);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.btn1:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.btn2:
                        vp.setCurrentItem(1);
                        break;
                    case R.id.btn3:
                        vp.setCurrentItem(2);
                        break;
                    case R.id.btn4:
                        vp.setCurrentItem(3);
                        break;
                }
            }
        });

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rg.check(R.id.btn1);
                        break;
                    case 1:
                        rg.check(R.id.btn2);
                        break;
                    case 2:
                        rg.check(R.id.btn3);
                        break;
                    case 3:
                        rg.check(R.id.btn4);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }

        public class SectionsPagerAdapter extends FragmentPagerAdapter {

            public SectionsPagerAdapter(FragmentManager fm) {
                super(fm);
            }

            @Override
            public Fragment getItem(int position) {
                return pagerList.get(position);
            }

            @Override
            public int getCount() {
                return pagerList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "SECTION 1";
                    case 1:
                        return "SECTION 2";
                    case 2:
                        return "SECTION 3";
                    case 3:
                        return "SECTION 4";
                }
                return null;
            }
        }

    public void initView() {
        pagerList = new ArrayList<>();
        pagerList.add(new pagecl());
        pagerList.add(new pageem());
        pagerList.add(new pagetimer());
        pagerList.add(new pagehis());
    }


}

