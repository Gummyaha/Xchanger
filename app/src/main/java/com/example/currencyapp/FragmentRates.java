package com.example.currencyapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Spinner;
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


public class FragmentRates extends Fragment {
    String rate_url ="https://api.exchangeratesapi.io/latest?base=",url,from_currency,currency_result_round;
    TextView itemRate,text1,currency_display,test;
    Double input, currency_result;
    View view;
    private Spinner dropdown1;
    RecyclerView.Adapter mAdapter;
    RecyclerView mRecyclerView;

    public FragmentRates() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rates_fragment,container,false);
        //super.onCreate(savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        RecyclerViewAdapter2 listAdapter = new RecyclerViewAdapter2(getContext());
        recyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

//        //------ Dropdown
//        dropdown1 = (Spinner) view.findViewById(R.id.baseSpinner);
//        ArrayAdapter<CharSequence> mSortAdapter = new ArrayAdapter<CharSequence>(getActivity(), android.R.layout.simple_spinner_item, getResources()
//                .getStringArray(R.array.currency));
//        mSortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        dropdown1.setAdapter(mSortAdapter);
//
//        dropdown1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parentView,
//                                       View selectedItemView, int position, long id) {
//                try {
//
//                    String select_base = parentView.getItemAtPosition(position).toString();
//                    url = select_base;
//                    test = (TextView)getView().findViewById(R.id.textView);
//                    test.setText(String.valueOf(url));
// //                       url = select_base;
////
////                    Intent intent = new Intent(getActivity(), RecyclerViewAdapter2.class);
////                    intent.putExtra("Base",url);
////                    getActivity().startActivity(intent);
//
//                }
//                catch (Exception e) {
//
//                }
//
//
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parentView) {
//
//            }
//
//        });



        return view;

    }



}
