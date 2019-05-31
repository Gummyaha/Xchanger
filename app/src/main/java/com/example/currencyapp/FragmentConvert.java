package com.example.currencyapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class FragmentConvert extends Fragment {
    String currency_url ="https://api.exchangeratesapi.io/latest?base=",url,from_currency,to_currency;
    View view;
    TextView test,test2,test3,currency_display;
    Double currency_result,input;
    EditText editText;
    int to_position,from_position;
    public static Spinner dropdown1,dropdown2 ;
    protected  AppDatabase db;



    public FragmentConvert() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.convert_fragment,container,false);

        Button b = (Button) view.findViewById(R.id.button2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Get_Curreny();
            }
        });

        Button b2 = (Button) view.findViewById(R.id.button);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Swap_Curreny();
            }
        });

        Button b3 = (Button) view.findViewById(R.id.button5);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFavorite();
            }
        });

        db = AppDatabase.getDatabase(getActivity());

/*----------------------------TEST-------------------------------

        ArrayList<String> places = new ArrayList<>(Arrays.asList("Buenos Aires", "Córdoba", "La Plata"));
        String[] countryNames={"India","China","Australia","Portugle","America","New Zealand"};
        places.add("list51");
        String[] str = GetStringArray(places);

        ArrayList<String> currency = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.currency)));
        currency.add("list51");
        String[] str_currency = GetStringArray(currency);

----------------------------------------------------------------*/

        //---1st Dropdown
        dropdown1 = (Spinner) view.findViewById(R.id.spinner1);

        ArrayAdapter<CharSequence> mSortAdapter = new ArrayAdapter<CharSequence>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.currency));
        mSortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown1.setAdapter(mSortAdapter);




        dropdown1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView,
                                       View selectedItemView, int position, long id) {
                try {

                    from_position=position;
                    from_currency =parentView.getItemAtPosition(position).toString();
                    test = (TextView)getView().findViewById(R.id.textView3);
                    test.setText(String.valueOf(from_currency));
                    url = currency_url+from_currency;

                }
                catch (Exception e) {

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });

        //---2nd Dropdown
        dropdown2 = (Spinner) view.findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> mSortAdapter2 = new ArrayAdapter<CharSequence>(getActivity(), android.R.layout.simple_spinner_item, getResources()
                .getStringArray(R.array.currency));
        mSortAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown2.setAdapter(mSortAdapter2);


        dropdown2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView,
                                       View selectedItemView, int position, long id) {
                try {

                    to_position = position;
                    to_currency =parentView.getItemAtPosition(position).toString();
                    test3 = (TextView)getView().findViewById(R.id.textView4);
                    test3.setText(String.valueOf(to_currency));


                }
                catch (Exception e) {

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });

        return view;
    }
    public void sendMessage(View view) {
        // Do something in response to button
    }

    void Get_Curreny(){

        //test.setText(String.valueOf(url));
        JsonObjectRequest jur = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject main_object = response.getJSONObject("rates");

                    //delete
                    //JSONArray array = response.getJSONArray("rate");
                    //JSONObject object = array.getJSONObject(0);
                    Double temp = Double.valueOf(main_object.getDouble(to_currency));

                    //delete
                   // test2 = (TextView)getView().findViewById(R.id.textView7);
                    //test2.setText(String.valueOf(temp));

                    editText = (EditText)getView().findViewById(R.id.editText);
                    if (editText.getText().toString().trim().length() == 0) {
                        input=0.0;
                    }else{
                        input = Double.parseDouble(editText.getText().toString());
                    }
                    currency_result = input*temp;
                    currency_display = (TextView)getView().findViewById(R.id.textView9);
                    currency_display.setText(String.valueOf(currency_result));

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
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(jur);
    }
    void Swap_Curreny(){

        test = (TextView)getView().findViewById(R.id.textView3);
        test.setText(String.valueOf(to_currency));
        dropdown1.setSelection(to_position);

        test3 = (TextView)getView().findViewById(R.id.textView4);
        test3.setText(String.valueOf(from_currency));
        dropdown2.setSelection(from_position);

        //editText.setText("0");


    }

    public static void set_curreny(int from,int to){

       // test = (TextView)getView().findViewById(R.id.textView3);
       // test.setText(String.valueOf(to_currency));
        dropdown1.setSelection(from);

        //test3 = (TextView)getView().findViewById(R.id.textView4);
        //test3.setText(String.valueOf(from_currency));
        dropdown2.setSelection(to);



        //editText.setText("0");


    }


    private void AddFavorite() {
        FavoriteCurrency favorite = new FavoriteCurrency();
        //favorite.setFromCurrency(fromCurrency);
       // favorite.setToCurrency(toCurrency);


        favorite.from_currency = from_currency;
        favorite.to_currency = to_currency;
        favorite.from_currency_position = from_position;
        favorite.to_currency_position = to_position;
        db.favoriteCurrencyDao().insert(favorite);

        //InsertData.onInsertProfile(getContext(), favorite, this);
    }







}
