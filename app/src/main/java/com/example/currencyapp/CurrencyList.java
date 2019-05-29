package com.example.currencyapp;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;


import java.util.ArrayList;

public class CurrencyList extends AppCompatActivity {
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    public static Boolean isEnable=false;
    Button backButton;
    ToggleButton toggleButton;




    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currency_list);

        initImageBitmaps();

        backButton = (Button) findViewById(R.id.button2);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                onBackPressed();

            }
        });


        final ToggleButton toggleButton = (ToggleButton) findViewById(R.id.myToggleButton);
        if(toggleButton != null) {
            toggleButton.setChecked(false);
            toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_star_black_24dp));
            toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked)
                        toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_star_yellow_24dp));
                    else
                        toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_star_black_24dp));
                }
            });
        }

    }



        private void initImageBitmaps(){

        mImageUrls.add("https://i.pinimg.com/originals/ac/b7/a8/acb7a839edbc8559e817db4219af39a8.jpg");
        mNames.add("Lee Taeyong");

        mImageUrls.add("https://i.pinimg.com/originals/83/c3/47/83c3473ae48bff78bc1485bc74d65a0d.jpg");
        mNames.add("Kim Jungkook");

        mImageUrls.add("https://i.pinimg.com/originals/60/d5/34/60d534500de9cb4c53c6547cf15643ea.jpg");
        mNames.add("Kim Jungwoo");

        mImageUrls.add("https://i2.wp.com/www.hellokpop.com/wp-content/uploads/2019/02/IMG_20190214_173440.jpg?resize=660%2C400&ssl=1");
        mNames.add("V");

        mImageUrls.add("https://img.kpopmap.com/2019/01/maxresdefault5.jpg");
        mNames.add("Ten");

        mImageUrls.add("https://pm1.narvii.com/7052/0a7a34ef022ea6f22c3c8f90d67faced435a35f5r1-853-1280v2_hq.jpg");
        mNames.add("Mark Lee");

        mImageUrls.add("https://6.viki.io/image/2a0d25527850433184085b2b03eab44d.jpeg?s=900x600&e=t");
        mNames.add("Suga");

        mImageUrls.add("https://i.pinimg.com/originals/83/c3/47/83c3473ae48bff78bc1485bc74d65a0d.jpg");
        mNames.add("Kim Jungkook");

        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageUrls);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }



}
