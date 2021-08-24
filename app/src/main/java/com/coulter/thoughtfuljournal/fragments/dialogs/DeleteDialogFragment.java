package com.coulter.thoughtfuljournal.fragments.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.coulter.thoughtfuljournal.R;
import com.coulter.thoughtfuljournal.databinding.DeleteDialogFragmentBinding;
import com.coulter.thoughtfuljournal.room.Journal;
import com.coulter.thoughtfuljournal.viewmodel.JournalViewModel;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class DeleteDialogFragment extends DialogFragment {
    private Journal journal;

    public DeleteDialogFragment(){}

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DeleteDialogFragmentBinding binding = DeleteDialogFragmentBinding.inflate(inflater, container, false);
        binding.deleteButton.setOnClickListener(v->{
            if(journal!=null) {
                new ViewModelProvider(requireActivity()).get(JournalViewModel.class).delete(journal);
                Snackbar.make(requireActivity().findViewById(R.id.fragmentContainerView), "Journal Deleted", Snackbar.LENGTH_SHORT).show();
            }
            dismiss();
        });
        binding.cancelButton.setOnClickListener(v->dismiss());
        if(Objects.requireNonNull(getDialog()).getWindow()!=null) {
            getDialog().getWindow().setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.dialog_background,null));
        }
        return binding.getRoot();
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }
}
