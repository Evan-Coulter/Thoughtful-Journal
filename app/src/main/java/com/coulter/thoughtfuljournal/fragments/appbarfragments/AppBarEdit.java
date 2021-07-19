package com.coulter.thoughtfuljournal.fragments.appbarfragments;

import android.view.MenuItem;
import android.widget.Toast;


import androidx.lifecycle.ViewModelProvider;

import com.coulter.thoughtfuljournal.MainActivity;
import com.coulter.thoughtfuljournal.R;
import com.coulter.thoughtfuljournal.viewmodel.JournalViewModel;

public class AppBarEdit extends AppBarFragment {
    @Override
    protected boolean handleOnOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.saveButton) {
            JournalViewModel viewModel = new ViewModelProvider(requireActivity()).get(JournalViewModel.class);
            viewModel.insert(viewModel.currentJournal.getValue());
            ((MainActivity) requireActivity()).onClick(null);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getMenuID() {
        return R.menu.edit_app_bar_menu;
    }
}
