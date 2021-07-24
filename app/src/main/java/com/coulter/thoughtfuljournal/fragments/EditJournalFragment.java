package com.coulter.thoughtfuljournal.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.ParcelableSpan;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.coulter.thoughtfuljournal.R;
import com.coulter.thoughtfuljournal.databinding.EditJournalFragmentBinding;
import com.coulter.thoughtfuljournal.viewmodel.JournalViewModel;

import org.jetbrains.annotations.NotNull;

public class EditJournalFragment extends Fragment {
    public JournalViewModel viewModel;
    private EditJournalFragmentBinding binding;

    public EditJournalFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.edit_journal_fragment,container,false);
        viewModel = new ViewModelProvider(requireActivity()).get(JournalViewModel.class);
        binding.setViewmodel(viewModel);
        setupButtons(binding);
        return binding.getRoot();
    }

    private void setupButtons(EditJournalFragmentBinding binding) {
        setupFormatButton(new StyleSpan(Typeface.BOLD), binding.boldButton, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        setupFormatButton(new StyleSpan(Typeface.ITALIC), binding.italicButton, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        setupFormatButton(new UnderlineSpan(), binding.underlineButton, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        setupFormatButton(new AbsoluteSizeSpan(22, true), binding.sizeButton, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
    }

    private void setupFormatButton(ParcelableSpan span, Button button, int spanBounds) {
        button.setOnClickListener(view->{
            int selectionIndex = binding.editText.getSelectionEnd();
            Spannable string = new SpannableStringBuilder(binding.editText.getText());
            string.setSpan(span,
                    binding.editText.getSelectionStart(),
                    binding.editText.getSelectionEnd(),
                    spanBounds);
            binding.editText.setText(string);
            binding.editText.setSelection(selectionIndex);
        });
    }
}
