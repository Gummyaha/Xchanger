package com.example.currencyapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;




public class RecyclerViewAdapter2 extends RecyclerView.Adapter {
    Context context;
    String currency_url ="https://api.exchangeratesapi.io/latest?symbols=",url,base;


    public RecyclerViewAdapter2(Context context) {
        this.context = context;
    }

//    public RecyclerViewAdapter2(ArrayList<String> mUrl) {
//        this.mUrl = mUrl;
//        //this.item = item;
//    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exchangerate_list,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return OurData.title.length;
    }


    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mItemText,mItemRate,mTest;
        private ImageView mItemImage;

        public ListViewHolder(View itemView){

            super(itemView);
            mItemText = (TextView) itemView.findViewById(R.id.itemText);
            mItemImage = (ImageView) itemView.findViewById(R.id.itemImage);
            mItemRate = (TextView) itemView.findViewById(R.id.itemRate);
            //mTest = (TextView) itemView.findViewById(R.id.test);
            itemView.setOnClickListener(this);
        }

        //get data from OurData.java
        public void bindView(final int position){
            //get currency name
            mItemText.setText(OurData.title[position]);
            //get flag image
            mItemImage.setImageResource(OurData.picturePath[position]);


            //get exchange rate
//            Intent intent = ((Activity) context).getIntent();
//            final String base = intent.getStringExtra("Base");
//
//            mTest = (TextView)itemView.findViewById(R.id.test);
//            mTest.setText(String.valueOf(base));

            url = currency_url+OurData.ratePath[position];
            JsonObjectRequest jur = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONObject main_object = response.getJSONObject("rates");
                        Double temp = Double.valueOf(main_object.getDouble(OurData.ratePath[position]));
                        mItemRate = (TextView)itemView.findViewById(R.id.itemRate);
                        mItemRate.setText(String.valueOf(temp));
                    }
                    catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }
            );
            RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
            queue.add(jur);

            //history button
//            mUrlButton.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View view) {
//                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
//                    intent.setData(Uri.parse(OurData.urlPath[position]));
//                    context.startActivity(intent);
//                }
//            });

        }

        public void onClick(View view){

        }

    }
}
