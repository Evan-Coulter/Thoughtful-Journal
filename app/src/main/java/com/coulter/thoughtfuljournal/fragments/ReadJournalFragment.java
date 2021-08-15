package com.coulter.thoughtfuljournal.fragments;

import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.coulter.thoughtfuljournal.R;
import com.coulter.thoughtfuljournal.databinding.ReadJournalFragmentBinding;
import com.coulter.thoughtfuljournal.room.Journal;
import com.coulter.thoughtfuljournal.viewmodel.JournalViewModel;


public class ReadJournalFragment extends Fragment {
    private ReadJournalFragmentBinding binding;
    private JournalViewModel viewModel;

    public ReadJournalFragment(){}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.read_journal_fragment, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(JournalViewModel.class);
        binding.setViewmodel(viewModel);
        setupTextView();
        return binding.getRoot();
    }

    private void setupTextView() {
        Journal journal = viewModel.currentJournal.getValue();
        if(journal!=null) {
            binding.journalText.setText(Html.fromHtml(journal.journal_content));
            binding.journalText.setTextSize(viewModel.currentJournal.getValue().fontSize);
        }
    }
}
