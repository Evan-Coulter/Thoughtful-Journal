package com.coulter.thoughtfuljournal.fragments.appbarfragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;

import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.SearchView;

import com.coulter.thoughtfuljournal.MainActivity;
import com.coulter.thoughtfuljournal.R;
import com.coulter.thoughtfuljournal.recyclerview.SortSubject;
import com.coulter.thoughtfuljournal.room.Journal;

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
            case R.id.searchButton:
                SearchView searchView = (SearchView)item.getActionView();
                searchView.setOnQueryTextListener((MainActivity)requireActivity());
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
