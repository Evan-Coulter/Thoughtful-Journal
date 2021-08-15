package com.coulter.thoughtfuljournal.fragments.appbarfragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.coulter.thoughtfuljournal.R;
import com.coulter.thoughtfuljournal.databinding.AppBarFragmentBinding;
import com.coulter.thoughtfuljournal.fragments.dialogs.SaveDialogFragment;
import com.coulter.thoughtfuljournal.fragments.dialogs.SaveFirstDialog;
import com.coulter.thoughtfuljournal.viewmodel.JournalViewModel;

import org.jetbrains.annotations.NotNull;

public class AppBarEdit extends AppBarFragment {
    @SuppressLint("NonConstantResourceId")
    @Override
    protected boolean handleOnOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.saveButton:
                new SaveDialogFragment().show(requireActivity().getSupportFragmentManager(), "Save Dialog");
                return true;
            case R.id.openReaderButton:
                new SaveFirstDialog().show(requireActivity().getSupportFragmentManager(), "Save First Dialog");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected int getMenuID() {
        return R.menu.edit_app_bar_menu;
    }

    @Override
    protected View setAndGetLayout(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Use data binding here.
        AppBarFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.app_bar_fragment, container, false);
        binding.setViewModel(new ViewModelProvider(requireActivity()).get(JournalViewModel.class));
        return binding.getRoot();
    }
}
