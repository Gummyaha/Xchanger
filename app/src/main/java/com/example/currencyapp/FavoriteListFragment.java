package com.example.currencyapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class FavoriteListFragment extends Fragment {
    private FavoriteListAdapter favoriteListAdapter;
    private FavoriteViewModel favoriteViewModel;
    private Context context;

    public static FavoriteListFragment newInstance() {
        return new FavoriteListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        favoriteListAdapter = new FavoriteListAdapter(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_favorite);
        recyclerView.setAdapter(favoriteListAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        return view;
    }

    private void initData() {
        favoriteViewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);
        favoriteViewModel.getFavoriteList().observe(this, new Observer<List<FavoriteCurrency>>() {
            @Override
            public void onChanged(@Nullable List<FavoriteCurrency> favorites) {
                favoriteListAdapter.setFavoriteList(favorites);
            }
        });
    }

    public void removeData() {
        if (favoriteViewModel != null) {
            favoriteViewModel.deleteAll();
        }
    }
}
