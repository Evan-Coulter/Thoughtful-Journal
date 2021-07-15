package com.coulter.thoughtfuljournal.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.coulter.thoughtfuljournal.R;
import com.coulter.thoughtfuljournal.recyclerview.JournalListAdapter;
import com.coulter.thoughtfuljournal.viewmodel.JournalViewModel;


public class RecyclerViewFragment extends Fragment {
    private RecyclerView recyclerView;

    public RecyclerViewFragment(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view_fragment, container, false);
        setupRecyclerView(view);
        return view;
    }

    private void setupRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        new ViewModelProvider(requireActivity())
                .get(JournalViewModel.class)
                .getJournals()
                .observe(requireActivity(), journals -> {
                    recyclerView.setAdapter(
                        new JournalListAdapter(journals)
                    );
                });
    }
}
