package com.coulter.thoughtfuljournal.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.coulter.thoughtfuljournal.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;


public class FABFragment extends Fragment {
    public FABFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fab_fragment, container, false);
        setupFAB(view.findViewById(R.id.fab));
        return view;
    }

    private void setupFAB(ExtendedFloatingActionButton fab) {
        fab.setOnClickListener(clickedView->{
            Toast.makeText(requireContext(), "Navigate to 'Edit Journal'", Toast.LENGTH_SHORT).show();
        });
    }
}
