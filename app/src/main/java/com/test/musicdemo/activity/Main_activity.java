package com.test.musicdemo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.test.musicdemo.R;
import com.test.musicdemo.adapter.FragmentViewpagerAdapter;
import com.test.musicdemo.fragment.Fragment01;
import com.test.musicdemo.fragment.Fragment02;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/14.
 */
public class Main_activity extends AppCompatActivity{

    public static final int NOTIFY_1 = 0x1001;

    private ViewPager viewPager;
    private List<Fragment> list = null;
    private FragmentManager fm = null;

    public static Handler mHandler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        fm = getSupportFragmentManager();
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        list = new ArrayList<Fragment>();
        list.add(new Fragment01());
        list.add(new Fragment02());
        viewPager.setAdapter(new FragmentViewpagerAdapter(fm, list));
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

            }
        };
    }
}
