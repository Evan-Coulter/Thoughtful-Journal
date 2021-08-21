package com.coulter.thoughtfuljournal.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.coulter.thoughtfuljournal.R;
import com.coulter.thoughtfuljournal.databinding.ResizeDialogFragmentBinding;
import com.coulter.thoughtfuljournal.room.Journal;
import com.coulter.thoughtfuljournal.viewmodel.JournalViewModel;
import com.google.android.material.slider.Slider;

import org.jetbrains.annotations.NotNull;

public class ResizeDialogFragment extends DialogFragment {
    public ResizeDialogFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ResizeDialogFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.resize_dialog_fragment, container, false);
        setupSlider(binding.sizeSlider);
        setupDoneButton(binding.doneButton);
        return binding.getRoot();
    }

    private void setupSlider(Slider sizeSlider) {
        sizeSlider.addOnChangeListener((slider, value, fromUser) -> {
            if(requireActivity().findViewById(R.id.journalText)!=null) {
                ((TextView)requireActivity().findViewById(R.id.journalText)).setTextSize(value);
            }
        });
        Journal journal = new ViewModelProvider(requireActivity())
                .get(JournalViewModel.class)
                .currentJournal.getValue();
        if(journal!=null) {
            sizeSlider.setValue(journal.fontSize);
        }
    }

    private void setupDoneButton(Button doneButton) {
        doneButton.setOnClickListener(v->dismiss());
    }
}
