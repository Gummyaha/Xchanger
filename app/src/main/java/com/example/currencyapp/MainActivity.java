package com.example.currencyapp;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
//    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.tablayout_id);
//        appBarLayout = (AppBarLayout) findViewById(R.id.appbarid);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new FragmentConvert(),"Converter");
        adapter.AddFragment(new FragmentRates(),"Exchange Rates");
        //adapter.AddFragment(new FragmentSettings(),"Settings");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

//        initImageBitmaps();

    }
//
//    private void initImageBitmaps(){
//
//        mImageUrls.add("https://i.pinimg.com/originals/ac/b7/a8/acb7a839edbc8559e817db4219af39a8.jpg");
//        mNames.add("Lee Taeyong");
//
//        mImageUrls.add("https://i.pinimg.com/originals/83/c3/47/83c3473ae48bff78bc1485bc74d65a0d.jpg");
//        mNames.add("Kim Jungkook");
//
//        mImageUrls.add("https://i.pinimg.com/originals/60/d5/34/60d534500de9cb4c53c6547cf15643ea.jpg");
//        mNames.add("Kim Jungwoo");
//
//        mImageUrls.add("http://images6.fanpop.com/image/photos/41300000/Naver-X-Starcast-v-bts-41369878-640-960.jpg");
//        mNames.add("V");
//
//        mImageUrls.add("https://img.kpopmap.com/2019/01/maxresdefault5.jpg");
//        mNames.add("Ten");
//
//        mImageUrls.add("https://pm1.narvii.com/7052/0a7a34ef022ea6f22c3c8f90d67faced435a35f5r1-853-1280v2_hq.jpg");
//        mNames.add("Mark Lee");
//
//        initRecyclerView();
//    }
//
//    private void initRecyclerView(){
//        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
//        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageUrls);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//    }
}
