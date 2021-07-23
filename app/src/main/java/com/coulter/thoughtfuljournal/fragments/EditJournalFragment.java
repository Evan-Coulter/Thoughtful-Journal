package com.coulter.thoughtfuljournal.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.coulter.thoughtfuljournal.R;
import com.coulter.thoughtfuljournal.databinding.EditJournalFragmentBinding;
import com.coulter.thoughtfuljournal.viewmodel.JournalViewModel;

import org.jetbrains.annotations.NotNull;

public class EditJournalFragment extends Fragment {
    public JournalViewModel viewModel;

    public EditJournalFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        EditJournalFragmentBinding binding = DataBindingUtil.inflate(inflater,R.layout.edit_journal_fragment,container,false);
        viewModel = new ViewModelProvider(requireActivity()).get(JournalViewModel.class);
        binding.setViewmodel(viewModel);
        setupButtons(binding);
        return binding.getRoot();
    }

    private void setupButtons(EditJournalFragmentBinding binding) {
        setupBoldButton(binding);
    }

    private void setupBoldButton(EditJournalFragmentBinding binding) {
        binding.boldButton.setOnClickListener(view->{
            Spannable string = new SpannableStringBuilder(binding.editText.getText());
            string.setSpan(new StyleSpan(Typeface.BOLD),
                    binding.editText.getSelectionStart(),
                    binding.editText.getSelectionEnd(),
                    0);
            binding.editText.setText(string);
        });
    }
}
