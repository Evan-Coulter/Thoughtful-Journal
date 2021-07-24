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
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

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
        setupButtons();
        return binding.getRoot();
    }

    private void setupButtons() {
        setupFormatButton(new StyleSpan(Typeface.BOLD), binding.boldButton);
        setupFormatButton(new StyleSpan(Typeface.ITALIC), binding.italicButton);
        setupFormatButton(new UnderlineSpan(), binding.underlineButton);
        setupFormatButton(new AbsoluteSizeSpan(22, true), binding.sizeButton);
    }

    private void setupFormatButton(ParcelableSpan span, Button button) {
        button.setOnClickListener(view-> {
            //if there is text selected
            if (binding.editText.getSelectionStart() - binding.editText.getSelectionEnd() != 0) {
                //only apply formatting
                applyFormatting(span, button, binding.editText.getSelectionStart(), binding.editText.getSelectionEnd());
            } else {
                displayError();
            }
        });
    }

    private void applyFormatting(ParcelableSpan span, Button button, int start, int end){
        int selectionIndex = binding.editText.getSelectionEnd();
        Spannable string = new SpannableStringBuilder(binding.editText.getText());
        string.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.editText.setText(string);
        binding.editText.setSelection(selectionIndex);
        ((MaterialButton)button).setChecked(false);
    }

    private void displayError() {
        //show snack bar error telling user to highlight text first.
    }
}
