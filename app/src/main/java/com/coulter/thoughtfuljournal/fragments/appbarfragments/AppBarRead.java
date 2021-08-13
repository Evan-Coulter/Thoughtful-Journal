package com.coulter.thoughtfuljournal.fragments.appbarfragments;

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

    @Override
    protected boolean handleOnOptionsItemSelected(MenuItem item) {
        Toast.makeText(requireActivity(), "Dynamicly Resize", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    protected int getMenuID() {
        return R.menu.read_app_bar_menu;
    }
}
