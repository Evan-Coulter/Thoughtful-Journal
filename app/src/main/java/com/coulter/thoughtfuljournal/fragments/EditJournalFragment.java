package com.coulter.thoughtfuljournal.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.ParcelableSpan;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
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
import com.coulter.thoughtfuljournal.room.Journal;
import com.coulter.thoughtfuljournal.viewmodel.JournalViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

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
        setupEditText();
        setupButtons();
        return binding.getRoot();
    }

    private void setupEditText() {
        Journal journal = viewModel.currentJournal.getValue();
        if(journal != null){
            binding.editText.setText(Html.fromHtml(journal.journal_content));
        }
    }

    private void setupButtons() {
        setupFormatButton(new StyleSpan(Typeface.BOLD), binding.boldButton);
        setupFormatButton(new StyleSpan(Typeface.ITALIC), binding.italicButton);
        setupFormatButton(new UnderlineSpan(), binding.underlineButton);
    }

    private void setupFormatButton(ParcelableSpan span, MaterialButton button) {
        button.setOnClickListener(view-> {
            if (binding.editText.getSelectionStart() - binding.editText.getSelectionEnd() != 0) {
                applyFormatting(span, binding.editText.getSelectionStart(), binding.editText.getSelectionEnd());
            } else {
                displayError(button);
            }
            button.setChecked(false);
        });
    }

    private void applyFormatting(ParcelableSpan span, int start, int end){
        Spannable string = new SpannableStringBuilder(binding.editText.getText());
        string.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.editText.setText(string);
        binding.editText.setSelection(start, end);
    }

    private void displayError(Button button) {
        Snackbar.make(button, R.string.format_string_error, Snackbar.LENGTH_SHORT).show();
    }
}
