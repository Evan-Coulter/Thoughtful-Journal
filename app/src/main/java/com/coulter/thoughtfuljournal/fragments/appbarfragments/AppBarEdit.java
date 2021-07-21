package com.coulter.thoughtfuljournal.fragments.appbarfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.coulter.thoughtfuljournal.R;
import com.coulter.thoughtfuljournal.databinding.AppBarFragmentBinding;
import com.coulter.thoughtfuljournal.fragments.SaveDialogFragment;
import com.coulter.thoughtfuljournal.viewmodel.JournalViewModel;

import org.jetbrains.annotations.NotNull;

public class AppBarEdit extends AppBarFragment {

    @Override
    protected boolean handleOnOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.saveButton) {
            JournalViewModel viewModel = new ViewModelProvider(requireActivity()).get(JournalViewModel.class);
            viewModel.insert(viewModel.currentJournal.getValue());
            SaveDialogFragment dialog = new SaveDialogFragment();
            dialog.show(requireActivity().getSupportFragmentManager(), "Save Dialog");
            return true;
        }
        return super.onOptionsItemSelected(item);
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
