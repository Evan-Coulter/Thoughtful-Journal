package com.coulter.thoughtfuljournal.fragments.appbarfragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.FileObserver;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.SearchView;

import com.coulter.thoughtfuljournal.MainActivity;
import com.coulter.thoughtfuljournal.R;
import com.coulter.thoughtfuljournal.recyclerview.FilterSubject;
import com.coulter.thoughtfuljournal.recyclerview.SortSubject;

import org.jetbrains.annotations.NotNull;


public class AppBarMain extends AppBarFragment {
    @SuppressLint("NonConstantResourceId")
    @Override
    protected boolean handleOnOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                requireActivity().onBackPressed();
                return true;
            case R.id.sortButton:
                showSortPopup();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showSortPopup() {
        PopupMenu popup = new PopupMenu(requireActivity(), requireActivity().findViewById(R.id.app_bar));
        popup.setGravity(Gravity.END);
        popup.setOnMenuItemClickListener(item -> ((SortSubject)requireActivity()).notifySortButtonClicked(item));
        popup.inflate(R.menu.sort_popup_menu);
        popup.show();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        SearchView searchView = (SearchView)menu.findItem(R.id.searchButton).getActionView();
        searchView.setOnQueryTextListener(((FilterSubject)requireActivity()).getOnQueryTextListener());
    }

    @Override
    protected int getMenuID() {
        return R.menu.main_app_bar_menu;
    }

    @Override
    protected View setAndGetLayout(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Don't use data binding here.
        return inflater.inflate(R.layout.app_bar_fragment, container, false);
    }

    @Override
    protected boolean shouldDisplayBackButton() {
        return false;
    }
}
