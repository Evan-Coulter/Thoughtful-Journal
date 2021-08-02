package com.coulter.thoughtfuljournal.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.coulter.thoughtfuljournal.MainActivity;
import com.coulter.thoughtfuljournal.R;
import com.coulter.thoughtfuljournal.recyclerview.JournalListAdapter;
import com.coulter.thoughtfuljournal.recyclerview.JournalListClickListener;
import com.coulter.thoughtfuljournal.recyclerview.MoreButtonClickListener;
import com.coulter.thoughtfuljournal.recyclerview.ResourceProvider;
import com.coulter.thoughtfuljournal.room.Journal;
import com.coulter.thoughtfuljournal.viewmodel.JournalViewModel;


public class RecyclerViewFragment extends Fragment implements JournalListClickListener, MoreButtonClickListener, ResourceProvider {
    private RecyclerView recyclerView;
    private JournalListAdapter adapter;

    public RecyclerViewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view_fragment, container, false);
        setupRecyclerView(view);
        return view;
    }

    private void setupRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.scheduleLayoutAnimation();
        new ViewModelProvider(requireActivity())
                .get(JournalViewModel.class)
                .getJournals()
                .observe(requireActivity(), journals -> {
                    adapter = new JournalListAdapter(journals);
                    adapter.setOnClickListener(this);
                    adapter.setOnMoreButtonClickListener(this);
                    adapter.setResourceProvider(this);
                    recyclerView.setAdapter(adapter);
                });
    }

    @Override
    public void onItemClick(View view, int position) {
        Journal clickedJournal = adapter.getJournal(position);
        JournalViewModel viewModel = new ViewModelProvider(requireActivity()).get(JournalViewModel.class);
        viewModel.postOldJournal(clickedJournal);
        ((MainActivity) requireActivity()).onClick(null);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onMoreButtonClicked(View view, int position) {
        PopupMenu popup = new PopupMenu(requireActivity(), view);
        Journal journal = adapter.getJournal(position);
        popup.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.deleteButton:
                    new ViewModelProvider(requireActivity()).get(JournalViewModel.class).delete(journal);
                    return true;
                case R.id.editorButton:
                    Toast.makeText(requireActivity(), "Editor", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.readerButton:
                    Toast.makeText(requireActivity(), "Reader", Toast.LENGTH_SHORT).show();
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
}
