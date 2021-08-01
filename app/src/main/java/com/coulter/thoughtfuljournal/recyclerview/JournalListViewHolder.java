package com.coulter.thoughtfuljournal.recyclerview;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.coulter.thoughtfuljournal.R;
import com.google.android.material.card.MaterialCardView;

public class JournalListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final View layout;
    private final TextView title;
    private final TextView date;
    private final TextView journal;
    private JournalListClickListener listener;

    public JournalListViewHolder(View view) {
        super(view);
        layout = view;
        layout.setOnClickListener(this);
        title = layout.findViewById(R.id.card_title);
        date = layout.findViewById(R.id.card_date);
        journal = layout.findViewById(R.id.journal_text);
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

    public void setOnClickListener(JournalListClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onItemClick(v, getBindingAdapterPosition());
    }

    public void setOnMoreButtonClickListener(MoreButtonClickListener listener) {
        ImageView moreButton = layout.findViewById(R.id.moreButton);
        moreButton.setOnClickListener(view -> listener.onMoreButtonClicked(moreButton, getBindingAdapterPosition()));
    }

    public void setBackground(int color) {
        ((MaterialCardView)layout).setCardBackgroundColor(color);
    }

    public void setDateColor(int color) {
        date.setTextColor(color);
    }

    public void setTypeFace(Typeface font) {
        date.setTypeface(font);
    }
}
