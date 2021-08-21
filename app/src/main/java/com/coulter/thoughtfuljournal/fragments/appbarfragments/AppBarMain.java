package com.coulter.thoughtfuljournal.fragments.appbarfragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.coulter.thoughtfuljournal.MainActivity;
import com.coulter.thoughtfuljournal.R;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicInteger;

public class AppBarMain extends AppBarFragment {
    @SuppressLint("NonConstantResourceId")
    @Override
    protected boolean handleOnOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                requireActivity().onBackPressed();
                return true;
            case R.id.sortButton:
                Toast.makeText(requireActivity(), "Show Sort Menu", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.searchButton:
                Toast.makeText(requireActivity(), "Show Search View", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
