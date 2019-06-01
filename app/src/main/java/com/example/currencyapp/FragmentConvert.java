package com.example.currencyapp;

import android.content.Intent;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

//import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static android.support.constraint.Constraints.TAG;


public class FragmentConvert extends Fragment {
    String currency_url ="https://api.exchangeratesapi.io/latest?base=",url,from_currency,currency_result_round,to_currency;
    View view;
    TextView from_currencyText,to_currencyText,test,rateDisplay,test3,currency_display;
    Double currency_result,input;
    EditText editText;
    int to_position,from_position;
    public static Spinner dropdown1,dropdown2 ;
    protected  AppDatabase db;
    private FavoriteViewModel favoriteViewModel;

    public FragmentConvert() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        view = inflater.inflate(R.layout.convert_fragment, container, false);

        favoriteViewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);

        Button b = (Button) view.findViewById(R.id.button2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //make your toast here
                Get_Currency();
            }
        });

        Button b2 = (Button) view.findViewById(R.id.button);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Swap_Currency();
            }
        });

        Button b3 = (Button) view.findViewById(R.id.button5);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFavorite();
                Toast.makeText(getActivity(), "Add in Favorite!", Toast.LENGTH_SHORT).show();
            }
        });

        favoriteViewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);


        //---1st Dropdown
        dropdown1 = (Spinner) view.findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> mSortAdapter = new ArrayAdapter<CharSequence>(getActivity(), android.R.layout.simple_spinner_item, getResources()
                .getStringArray(R.array.currency));
        mSortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown1.setAdapter(mSortAdapter);

        dropdown1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView,
                                       View selectedItemView, int position, long id) {
                try {
                    from_position=position;
                    from_currency=parentView.getItemAtPosition(position).toString();
                    from_currencyText = (TextView)getView().findViewById(R.id.textView3);
                    from_currencyText.setText(String.valueOf(from_currency));
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
                    to_currencyText = (TextView)getView().findViewById(R.id.textView4);
                    to_currencyText.setText(String.valueOf(to_currency));


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

    void Get_Currency(){

        JsonObjectRequest jur = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject main_object = response.getJSONObject("rates");
                    Double temp = Double.valueOf(main_object.getDouble(to_currency));
                    //Rate display
                    rateDisplay = (TextView)getView().findViewById(R.id.textView7);
                    rateDisplay.setText(String.valueOf(temp));
                    editText = (EditText)getView().findViewById(R.id.editText);
                    if (editText.getText().toString().trim().length() == 0) {
                        input=0.0;
                    }else{
                        input = Double.parseDouble(editText.getText().toString());
                    }
                    currency_result = input*temp;
                    currency_result_round = String.format("%.2f", currency_result);
                    currency_display = (TextView)getView().findViewById(R.id.textView9);
                    currency_display.setText(String.valueOf(currency_result_round));

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

    void Swap_Currency(){
        from_currencyText = (TextView)getView().findViewById(R.id.textView3);
        from_currencyText.setText(String.valueOf(to_currency));
        dropdown1.setSelection(to_position);

        to_currencyText = (TextView)getView().findViewById(R.id.textView4);
        to_currencyText.setText(String.valueOf(from_currency));
        dropdown2.setSelection(from_position);
    }

    public static void set_currency(int from,int to){
        dropdown1.setSelection(from);
        dropdown2.setSelection(to);
    }


    private void AddFavorite() {
        FavoriteCurrencyDao favoriteCurrencyDao = AppDatabase.getDatabase(getActivity()).favoriteCurrencyDao();
        FavoriteCurrency favorite = new FavoriteCurrency();
        favorite.from_currency = from_currency;
        favorite.to_currency = to_currency;
        favorite.from_currency_position = from_position;
        favorite.to_currency_position = to_position;

        FavoriteCurrency newFavorite = favoriteCurrencyDao.findFavorite(from_currency,to_currency);
        if (newFavorite == null) {
            favoriteViewModel.insert(favorite);
        }
    }


}
