package com.coulter.thoughtfuljournal.recyclerview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.coulter.thoughtfuljournal.R;
import com.coulter.thoughtfuljournal.room.Journal;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.List;


public class JournalListAdapter extends RecyclerView.Adapter<JournalListViewHolder> {
    private final List<Journal> journals;

    public JournalListAdapter(List<Journal> journals) {
        this.journals = journals;
    }

    @NotNull
    @Override
    public JournalListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recycler_view_fragment_card, parent, false);
        return new JournalListViewHolder(view);
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onBindViewHolder(JournalListViewHolder holder, int position) {
        holder.getTitle().setText(journals.get(position).journal_name);
        holder.getDate().setText(
            new SimpleDateFormat("dd/MM/yyyy")
            .format(journals.get(position)
            .creation_date)
        );
        holder.getJournal().setText(journals.get(position).journal_name);
    }

    @Override
    public int getItemCount() {
        return journals.size();
    }
}
