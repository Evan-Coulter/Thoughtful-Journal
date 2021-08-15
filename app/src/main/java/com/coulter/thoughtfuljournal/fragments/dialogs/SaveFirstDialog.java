package com.coulter.thoughtfuljournal.fragments.dialogs;

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
import com.coulter.thoughtfuljournal.databinding.SaveFirstDialogFragmentBinding;
import com.coulter.thoughtfuljournal.viewmodel.JournalViewModel;

/**
 * Prompts to user to save before leaving this fragment.
 */
public class SaveFirstDialog extends DialogFragment {
    public SaveFirstDialog(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SaveFirstDialogFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.save_first_dialog_fragment, container, false);
        binding.setViewModel(new ViewModelProvider(requireActivity()).get(JournalViewModel.class));
        binding.dialogOkButton.setOnClickListener(getOkButtonListener());
        binding.dialogCancelButton.setOnClickListener(v->dismiss());
        return binding.getRoot();
    }

    //Override this to add callback.
    public View.OnClickListener getOkButtonListener() {
        return v -> {
            SaveDialogFragment saveDialog = new SaveDialogFragment();
            saveDialog.show(requireActivity().getSupportFragmentManager(), "Save Dialog");
            dismiss();
        };
    }
}
