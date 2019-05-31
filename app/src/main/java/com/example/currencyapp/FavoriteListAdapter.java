package com.example.currencyapp;


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

import java.util.List;

import static com.example.currencyapp.FavoriteSaveDialogFragment.TAG_DIALOG_DIRECTOR_SAVE;

public class FavoriteListAdapter extends RecyclerView.Adapter<FavoriteListAdapter.FavoriteViewHolder> {
    private LayoutInflater layoutInflater;
    private List<FavoriteCurrency> favoriteList;
    private Context context;

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
                    //FragmentConvert cbeta = new FragmentConvert();
                    FragmentConvert.set_curreny(favorite.from_currency_position,favorite.to_currency_position);  //?

               //     FragmentConvert optionsFrag = new FragmentConvert ();
               //     ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.linearLayout, optionsFrag,"OptionsFragment").addToBackStack(null).commit();

                  //  AppCompatActivity activity = (AppCompatActivity) v.getContext();
                  //  activity.getSupportFragmentManager().beginTransaction().replace(R.id.flContent, FragmentConvert).addToBackStack(null).commit();

                    /*
                    FragmentConvert fragment = new FragmentConvert();
                    FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.linearLayout, fragment);
                    transaction.commit();

*/
                    //   DialogFragment dialogFragment = FavoriteSaveDialogFragment.newInstance(favorite.from_currency);
                 //   dialogFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), TAG_DIALOG_DIRECTOR_SAVE);
                }
            });

            holder.delete.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //FragmentConvert.set_curreny(2,2);
                    FavoriteCurrency theRemovedItem = favoriteList.get(position);
                    // remove your item from data base
                    favoriteList.remove(position);

                    // remove the item from list
                    notifyItemRemoved(position); // notify the adapter about the removed item
                  /*  ListsDatabaseList theRemovedItem = list.get(position);
                    // remove your item from data base
                    list.remove(position);  // remove the item from list
                    notifyItemRemoved(position); // notify the adapter about the removed item
                    */
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
