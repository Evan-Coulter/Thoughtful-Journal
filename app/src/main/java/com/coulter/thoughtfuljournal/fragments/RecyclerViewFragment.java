package com.coulter.thoughtfuljournal.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.coulter.thoughtfuljournal.MainActivity;
import com.coulter.thoughtfuljournal.R;
import com.coulter.thoughtfuljournal.recyclerview.FilterObserver;
import com.coulter.thoughtfuljournal.recyclerview.FilterSubject;
import com.coulter.thoughtfuljournal.recyclerview.JournalListAdapter;
import com.coulter.thoughtfuljournal.recyclerview.JournalListClickListener;
import com.coulter.thoughtfuljournal.recyclerview.MoreButtonClickListener;
import com.coulter.thoughtfuljournal.recyclerview.ResourceProvider;
import com.coulter.thoughtfuljournal.recyclerview.SortObserver;
import com.coulter.thoughtfuljournal.recyclerview.SortSubject;
import com.coulter.thoughtfuljournal.room.Journal;
import com.coulter.thoughtfuljournal.viewmodel.JournalViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class RecyclerViewFragment extends Fragment implements JournalListClickListener, MoreButtonClickListener, ResourceProvider, SortObserver, SearchView.OnQueryTextListener, FilterObserver {
    private RecyclerView recyclerView;
    private JournalListAdapter adapter;
    private JournalViewModel viewModel;
    private List<Journal> filteredJournals;
    private List<Journal> unfilteredJournals;

    public RecyclerViewFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view_fragment, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(JournalViewModel.class);
        setupRecyclerView(view);
        ((SortSubject)requireActivity()).attachSortObserver(this);
        ((FilterSubject)requireActivity()).attachFilterObserver(this);
        return view;
    }

    private void setupRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        viewModel.getJournals().observe(requireActivity(), journals->{
            unfilteredJournals = new ArrayList<>(journals);
            updateRecyclerView(unfilteredJournals);
            recyclerView.scheduleLayoutAnimation();
        });
    }

    private void updateRecyclerView(List<Journal> journals) {
        filteredJournals = journals;
        adapter = new JournalListAdapter(journals);
        adapter.setOnClickListener(this);
        adapter.setOnMoreButtonClickListener(this);
        adapter.setResourceProvider(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Journal clickedJournal = adapter.getJournal(position);
        viewModel.postOldJournal(clickedJournal);
        if(clickedJournal.isDraft) {
            ((MainActivity) requireActivity()).navigate(R.id.listToEdit);
        } else {
            ((MainActivity) requireActivity()).navigate(R.id.listToRead);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onMoreButtonClicked(View view, int position) {
        PopupMenu popup = new PopupMenu(requireActivity(), view);
        Journal journal = adapter.getJournal(position);
        popup.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.deleteButton:
                    viewModel.delete(journal);
                    return true;
                case R.id.editorButton:
                    viewModel.postOldJournal(journal);
                    ((MainActivity) requireActivity()).navigate(R.id.listToEdit);
                    return true;
                case R.id.readerButton:
                    viewModel.postOldJournal(journal);
                    ((MainActivity) requireActivity()).navigate(R.id.listToRead);
                    return true;
                default:
                    return false;
            }
        });
        popup.inflate(R.menu.more_popup_menu);
        popup.show();
    }

    @Override
    public Context getResourceProvider() {
        return requireActivity();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onSortButtonClicked(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.sortByName:
                Collections.sort(filteredJournals, (journal1, journal2) ->
                        journal1.journal_name.compareToIgnoreCase(journal2.journal_name));
                updateRecyclerView(filteredJournals);
                break;
            case  R.id.sortByDate:
                Collections.sort(filteredJournals, (journal1, journal2) ->
                        journal1.creation_date.compareTo(journal2.creation_date));
                updateRecyclerView(filteredJournals);
                break;
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        List<Journal> filteredList = new ArrayList<>();
        if(newText.isEmpty()) {
            filteredList.addAll(unfilteredJournals);
        } else {
            for (Journal journal: unfilteredJournals) {
                if(journal.journal_name.toLowerCase().contains(newText.toLowerCase())
                || journal.journal_content.toLowerCase().contains(newText.toLowerCase())) {
                    filteredList.add(journal);
                }
            }
        }
        updateRecyclerView(filteredList);
        return false;
    }

    @Override
    public SearchView.OnQueryTextListener provideOnQueryTextListener() {
        return this;
    }
}
