package com.example.currencyapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

//import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class FragmentConvert extends Fragment {
    String currency_url ="https://api.exchangeratesapi.io/latest?base=",url,from_currency,currency_result_round;
    View view;
    TextView test,test2,test3,currency_display;
    Double currency_result,input;
    Button button;
    private Spinner dropdown1,dropdown2 ;
    public FragmentConvert() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        view = inflater.inflate(R.layout.convert_fragment, container, false);

        button = (Button) view.findViewById(R.id.button4);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), CurrencyList.class);
                startActivity(intent);
            }
        });


//        view = inflater.inflate(R.layout.convert_fragment,container,false);
//
//        Button b = (Button) view.findViewById(R.id.button2);
//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //make your toast here
//                Get_Currency();
//            }
//        });
//
//        //---1st Dropdown
//        dropdown1 = (Spinner) view.findViewById(R.id.spinner1);
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
//                    String select_item =parentView.getItemAtPosition(position).toString();
//                    test = (TextView)getView().findViewById(R.id.textView3);
//                    test.setText(String.valueOf(select_item));
//                    url = currency_url+select_item;
//
//                }
//                catch (Exception e) {
//
//                }
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parentView) {
//
//            }
//
//        });
//
//        //---2nd Dropdown
//        dropdown2 = (Spinner) view.findViewById(R.id.spinner2);
//        ArrayAdapter<CharSequence> mSortAdapter2 = new ArrayAdapter<CharSequence>(getActivity(), android.R.layout.simple_spinner_item, getResources()
//                .getStringArray(R.array.currency));
//        mSortAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        dropdown2.setAdapter(mSortAdapter2);
//
//        dropdown2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parentView,
//                                       View selectedItemView, int position, long id) {
//                try {
//
//                    from_currency =parentView.getItemAtPosition(position).toString();
//                    test3 = (TextView)getView().findViewById(R.id.textView4);
//                    test3.setText(String.valueOf(from_currency));
//
//
//                }
//                catch (Exception e) {
//
//                }
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parentView) {
//
//            }
//
//        });
//
        return view;
//    }
//    public void sendMessage(View view) {
//        // Do something in response to button
//    }
//
//    void Get_Currency(){
//
//        //test.setText(String.valueOf(url));
//        JsonObjectRequest jur = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    JSONObject main_object = response.getJSONObject("rates");
//                    //JSONArray array = response.getJSONArray("weather");
//                    //JSONObject object = array.getJSONObject(0);
//                    Double temp = Double.valueOf(main_object.getDouble(from_currency));
//                    test2 = (TextView)getView().findViewById(R.id.textView7);
//                    test2.setText(String.valueOf(temp));
//                    EditText editText = (EditText)getView().findViewById(R.id.editText);
//                    if (editText.getText().toString().trim().length() == 0) {
//                        input=0.0;
//                    }else{
//                        input = Double.parseDouble(editText.getText().toString());
//                    }
//                    currency_result = input*temp;
//                    currency_result_round = String.format("%.2f", currency_result);
//                    currency_display = (TextView)getView().findViewById(R.id.textView9);
//                    currency_display.setText(String.valueOf(currency_result_round));
//
//                }
//                catch (JSONException e){
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }
//        );
//        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
//        queue.add(jur);
    }
//


}
