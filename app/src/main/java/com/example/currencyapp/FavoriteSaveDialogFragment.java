package com.example.currencyapp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class FavoriteSaveDialogFragment extends DialogFragment {
    private Context context;
    private String favoriteExtra;

    private static final String EXTRA_DIRECTOR_FULL_NAME = "director_full_name";
    public static final String TAG_DIALOG_DIRECTOR_SAVE = "dialog_director_save";

    public static FavoriteSaveDialogFragment newInstance(String directorFullName) {
        FavoriteSaveDialogFragment fragment = new FavoriteSaveDialogFragment();

        Bundle args = new Bundle();
        args.putString(EXTRA_DIRECTOR_FULL_NAME, directorFullName);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        favoriteExtra = args.getString(EXTRA_DIRECTOR_FULL_NAME);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_favorite, null);
        final EditText directorEditText = view.findViewById(R.id.etFavorite);
        if (favoriteExtra != null) {
            directorEditText.setText(favoriteExtra);
            directorEditText.setSelection(favoriteExtra.length());
        }

        alertDialogBuilder.setView(view)
                .setTitle(getString(R.string.dialog_director_title))
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        saveFavorite(directorEditText.getText().toString());
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        return alertDialogBuilder.create();
    }

    private void saveFavorite(String fullName) {
        if (TextUtils.isEmpty(fullName)) {
            return;
        }

        FavoriteCurrencyDao favoriteCurrencyDao = AppDatabase.getDatabase(context).favoriteCurrencyDao();

        if (favoriteExtra != null) {
            // clicked on item row -> update
            FavoriteCurrency favotireToUpdate = favoriteCurrencyDao.findfromByName(favoriteExtra);
            if (favotireToUpdate != null) {
                if (!favotireToUpdate.from_currency.equals(fullName)) {
                    favotireToUpdate.from_currency = fullName;
                    favoriteCurrencyDao.update(favotireToUpdate);
                }
            }
        } else {
            favoriteCurrencyDao.insert(new FavoriteCurrency(fullName,fullName,1,1));
        }
    }
}
