package com.coulter.thoughtfuljournal.fragments.dialogs;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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
    private SaveDialogFragmentBinding binding;
    public SaveDialogFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.save_dialog_fragment, container, false);
        binding.setViewModel(new ViewModelProvider(requireActivity()).get(JournalViewModel.class));
        binding.dialogCancelButton.setOnClickListener(v->dismiss());
        binding.dialogSaveButton.setOnClickListener(getSaveButtonListener(null));
        return binding.getRoot();
    }

    public View.OnClickListener getSaveButtonListener(Runnable callback) {
        if(Objects.requireNonNull(binding.textField.getEditText()).getText().toString().equals("")){
            binding.textField.getEditText().setText(R.string.new_journal_title);
        }
        return v -> {
            JournalViewModel viewModel = new ViewModelProvider(requireActivity()).get(JournalViewModel.class);
            Objects.requireNonNull(viewModel.currentJournal.getValue()).journal_content = Html.toHtml(((EditText)requireActivity().findViewById(R.id.editText)).getText());
            viewModel.insert(viewModel.currentJournal.getValue());
            //Refresh current layout.
            (requireActivity()).onBackPressed();
            ((MainActivity)requireActivity()).navigate(R.id.listToEdit);
            if(callback!=null) callback.run();
            dismiss();
        };
    }

    public void addSaveCallback(Runnable callback) {
        binding.dialogSaveButton.setOnClickListener(getSaveButtonListener(callback));
    }
}
