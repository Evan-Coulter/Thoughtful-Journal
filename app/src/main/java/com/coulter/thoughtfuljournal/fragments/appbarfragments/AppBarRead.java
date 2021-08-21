package com.coulter.thoughtfuljournal.fragments.appbarfragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.coulter.thoughtfuljournal.R;
import com.coulter.thoughtfuljournal.databinding.AppBarFragmentBinding;
import com.coulter.thoughtfuljournal.viewmodel.JournalViewModel;

import org.jetbrains.annotations.NotNull;

public class AppBarRead extends AppBarFragment {
    @Override
    protected View setAndGetLayout(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AppBarFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.app_bar_fragment, container, false);
        binding.setViewModel(new ViewModelProvider(requireActivity()).get(JournalViewModel.class));
        return binding.getRoot();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    protected boolean handleOnOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                requireActivity().onBackPressed();
                return true;
            case R.id.dynamicSizeButton:
                Toast.makeText(requireActivity(), "Dynamicly Resize", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected int getMenuID() {
        return R.menu.read_app_bar_menu;
    }

    @Override
    protected boolean shouldDisplayBackButton() {
        return true;
    }
}
