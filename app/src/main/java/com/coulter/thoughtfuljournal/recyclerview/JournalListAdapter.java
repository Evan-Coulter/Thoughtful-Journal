package com.coulter.thoughtfuljournal.recyclerview;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.coulter.thoughtfuljournal.R;
import com.coulter.thoughtfuljournal.room.Journal;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;


public class JournalListAdapter extends RecyclerView.Adapter<JournalListViewHolder> {
    private final List<Journal> journals;
    private JournalListClickListener clickListener;

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

    @Override
    public void onBindViewHolder(JournalListViewHolder holder, int position) {
        Journal currentJournal = journals.get(position);
        holder.getTitle().setText(currentJournal.journal_name);
        setDate(holder, currentJournal);
        holder.getJournal().setText(Html.fromHtml(currentJournal.journal_content));
        holder.setOnClickListener(clickListener);
    }

    private void setDate(JournalListViewHolder holder, Journal currentJournal) {
        if(!currentJournal.isDraft) {
            holder.getDate().setText(
                new SimpleDateFormat("dd/MM/yyyy", Locale.US)
                .format(currentJournal.creation_date)
            );
        }else{
            holder.getDate().setText(R.string.draft_text);
        }
    }

    @Override
    public int getItemCount() {
        return journals.size();
    }

    public Journal getJournal(int position){
        return journals.get(position);
    }

    public void setOnClickListener(JournalListClickListener listener){
        clickListener = listener;
    }
}
