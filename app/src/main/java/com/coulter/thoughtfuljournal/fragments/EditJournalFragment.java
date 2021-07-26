package com.coulter.thoughtfuljournal.fragments;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.ParcelableSpan;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;

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

public class EditJournalFragment extends Fragment implements PopupMenu.OnMenuItemClickListener {
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
        setupFormatButton(binding.boldButton, ()-> applyFormatting(new StyleSpan(Typeface.BOLD), binding.editText.getSelectionStart(), binding.editText.getSelectionEnd()));
        setupFormatButton(binding.italicButton, ()-> applyFormatting(new StyleSpan(Typeface.ITALIC), binding.editText.getSelectionStart(), binding.editText.getSelectionEnd()));
        setupFormatButton(binding.underlineButton, ()-> applyFormatting(new UnderlineSpan(), binding.editText.getSelectionStart(), binding.editText.getSelectionEnd()));
        binding.sizeButton.setOnClickListener(view->{
            PopupMenu popup = new PopupMenu(requireActivity(), view);
            popup.setOnMenuItemClickListener(this);
            popup.inflate(R.menu.size_popup_menu);
            popup.show();
            binding.sizeButton.setChecked(false);
        });
    }

    private void setupFormatButton(MaterialButton button, Runnable applyFormat) {
        button.setOnClickListener(view-> {
            if (binding.editText.getSelectionStart() - binding.editText.getSelectionEnd() != 0) {
                //Weird lambda is here because we need to create a new span for each one we want to apply.
                applyFormat.run();
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

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId()){
            case R.id.small:
                binding.editText.setTextSize(14);
                return true;
            case R.id.medium:
                binding.editText.setTextSize(18);
                return true;
            case R.id.large:
                binding.editText.setTextSize(22);
                return true;
            default:
                return false;
        }
    }
}