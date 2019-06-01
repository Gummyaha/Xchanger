package com.example.currencyapp;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class FavoriteListAdapter extends RecyclerView.Adapter<FavoriteListAdapter.FavoriteViewHolder> {
    private LayoutInflater layoutInflater;
    private List<FavoriteCurrency> favoriteList;
    private Context context;
    protected  AppDatabase db;

    public FavoriteListAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setFavoriteList(List<FavoriteCurrency> favoriteList) {
        this.favoriteList = favoriteList;
        notifyDataSetChanged();
    }

    @Override
    public FavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = layoutInflater.inflate(R.layout.item_list_favorite, parent, false);
        db = AppDatabase.getDatabase(context);
        return new FavoriteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FavoriteViewHolder holder, int position) {
        if (favoriteList == null) {
            return;
        }

        final FavoriteCurrency favorite = favoriteList.get(position);
        if (favorite != null) {
            holder.fromCurrenctText.setText(favorite.from_currency);
            holder.toCurrenctText.setText(favorite.to_currency);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentConvert.set_currency(favorite.from_currency_position,favorite.to_currency_position);
                    Toast.makeText(v.getContext(), "Ready to convert!", Toast.LENGTH_SHORT).show();
                }
            });

            holder.delete.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    db.favoriteCurrencyDao().delete(favorite);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        if (favoriteList == null) {
            return 0;
        } else {
            return favoriteList.size();
        }
    }

    static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        private TextView fromCurrenctText;
        private TextView toCurrenctText;
        private Button delete;


        public FavoriteViewHolder(View itemView) {
            super(itemView);
            fromCurrenctText = itemView.findViewById(R.id.from_currency);
            toCurrenctText = itemView.findViewById(R.id.to_currency);
            delete = itemView.findViewById(R.id.button7);
        }


    }
}
