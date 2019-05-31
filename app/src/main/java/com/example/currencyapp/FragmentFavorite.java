package com.example.currencyapp;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FragmentFavorite extends Fragment {
    View view;
    protected  AppDatabase db;
    private List<FavoriteCurrency> listPlayer;
    private FavoriteViewModel noteViewModel;
    private FavoriteListAdapter noteListAdapter;
    //protected User currentUser;

    TextView test10;

    public FragmentFavorite() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.favorite_fragment,container,false);

/*

        RecyclerView recyclerView = (RecyclerView)getView().findViewById(R.id.recyclerview);
        noteListAdapter = new FavoriteListAdapter(getActivity(), (FavoriteListAdapter.OnDeleteClickListener) this);
        recyclerView.setAdapter(noteListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
*/
//----------------------------------------------------------------------------------------------------------------
/*
        noteListAdapter = new FavoriteListAdapter(getActivity(), (FavoriteListAdapter.OnDeleteClickListener) this);

        noteViewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);
        noteViewModel.getAll().observe(this, posts -> noteListAdapter.setNotes(posts));


        RecyclerView recyclerView = (RecyclerView)getView().findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(noteListAdapter);

*/

        /*
        RecyclerView recyclerView = (RecyclerView)getView().findViewById(R.id.recyclerview);
        noteListAdapter = new FavoriteListAdapter(this,this);
        recyclerView.setAdapter(noteListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        */

/*
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewNoteActivity.class);
                startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE);
            }
        });
*/
        //noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
/*
        noteViewModel.getAll().observe(this, new Observer<List<FavoriteCurrency>>() {
            @Override
            public void onChanged(@Nullable List<FavoriteCurrency> notes) {
                noteListAdapter.setNotes(notes);
            }
        });
*/

        Button b3 = (Button) view.findViewById(R.id.button4);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test_Curreny();
            }
        });

        db = AppDatabase.getDatabase(getActivity());


       /* List<String> listPlayerName = new ArrayList<>();
        for (FavoriteCurrency player : listPlayer) {
            listPlayerName.add(player.fromCurrency);
        }
        */

        //db.favoriteCurrencyDAO().getAll();


        return view;
    }

    void test_Curreny(){

        /*
        List<String> listPlayerName = new ArrayList<>();
        listPlayerName.add();
                db.favoriteCurrencyDAO().getAlls() ;
                */

      //  test10 = (TextView)getView().findViewById(R.id.textView6);
        //test10.setText(String.valueOf(db.favoriteCurrencyDAO().countFavorites()));

       // test10.setText(String.valueOf(db.favoriteCurrencyDAO().getFromCurrency().toCurrency));



    }


}


