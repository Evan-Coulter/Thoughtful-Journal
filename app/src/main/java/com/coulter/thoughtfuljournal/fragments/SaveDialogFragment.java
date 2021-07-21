package com.coulter.thoughtfuljournal.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.coulter.thoughtfuljournal.MainActivity;
import com.coulter.thoughtfuljournal.R;
import com.coulter.thoughtfuljournal.databinding.SaveDialogFragmentBinding;
import com.coulter.thoughtfuljournal.viewmodel.JournalViewModel;

import java.util.Objects;

public class SaveDialogFragment extends DialogFragment {
    public SaveDialogFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SaveDialogFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.save_dialog_fragment, container, false);
        binding.setViewModel(new ViewModelProvider(requireActivity()).get(JournalViewModel.class));
        binding.dialogCancelButton.setOnClickListener(getCancelButtonListener(binding));
        binding.dialogSaveButton.setOnClickListener(getSaveButtonListener(binding));
        return binding.getRoot();
    }

    //Override this.
    public View.OnClickListener getCancelButtonListener(SaveDialogFragmentBinding binding) {
        return v -> dismiss();
    }

    //Override this.
    public View.OnClickListener getSaveButtonListener(SaveDialogFragmentBinding binding) {
        if(Objects.requireNonNull(binding.textField.getEditText()).getText().toString().equals("")){
            binding.textField.getEditText().setText(R.string.new_journal_title);
        }
        return v -> {
            JournalViewModel viewModel = new ViewModelProvider(requireActivity()).get(JournalViewModel.class);
            viewModel.insert(viewModel.currentJournal.getValue());
            //Refresh current layout.
            ((MainActivity)requireActivity()).onClick(null);
            ((MainActivity)requireActivity()).onClick(null);
            dismiss();
        };
    }
}