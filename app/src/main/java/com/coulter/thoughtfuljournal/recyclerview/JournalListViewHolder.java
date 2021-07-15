package com.coulter.thoughtfuljournal.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.coulter.thoughtfuljournal.R;

public class JournalListViewHolder extends RecyclerView.ViewHolder {
    private final TextView title;
    private final TextView date;
    private final TextView journal;

    public JournalListViewHolder(View view) {
        super(view);
        title = view.findViewById(R.id.card_title);
        date = view.findViewById(R.id.card_date);
        journal = view.findViewById(R.id.journal_text);
    }

    public TextView getTitle() {
        return title;
    }

    public TextView getDate() {
        return date;
    }

    public TextView getJournal() {
        return journal;
    }
}
