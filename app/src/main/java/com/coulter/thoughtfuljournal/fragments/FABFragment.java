package com.coulter.thoughtfuljournal.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.coulter.thoughtfuljournal.MainActivity;
import com.coulter.thoughtfuljournal.R;
import com.coulter.thoughtfuljournal.viewmodel.JournalViewModel;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import org.jetbrains.annotations.NotNull;

public class FABFragment extends Fragment {
    private ExtendedFloatingActionButton fab;

    public FABFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fab_fragment, container, false);
        fab = view.findViewById(R.id.fab);
        fab.setAlpha(0);
        return view;
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab.setOnClickListener(clickedView->{
            JournalViewModel viewModel = new ViewModelProvider(requireActivity()).get(JournalViewModel.class);
            viewModel.postNewJournal();
            ((MainActivity)requireActivity()).navigate(R.id.listToEdit);
        });
        fab.animate().alpha(1.0f).setDuration(700).start();
    }
}
