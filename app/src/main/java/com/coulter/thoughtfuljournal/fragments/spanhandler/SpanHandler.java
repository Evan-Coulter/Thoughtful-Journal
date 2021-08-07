package com.coulter.thoughtfuljournal.fragments.spanhandler;

import android.content.Context;
import android.os.Parcelable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class SpanHandler {
    private final EditText editText;
    private Parcelable span;
    private final int start;
    private final int end;
    private Context context;

    public SpanHandler(Context context, EditText editText, Parcelable span, int start, int end) {
        this.editText = editText;
        this.span = span;
        this.start = start;
        this.end = end;
        this.context = context;
    }

    public void start() {
        Spannable text = editText.getText();
        StyleSpan[] spans = text.getSpans(start, end, StyleSpan.class);
        if(spans.length == 0) {
            applyFormatting();
        }
        else {
            Toast.makeText(context, Integer.valueOf(spans.length).toString() + " Spans Detected", Toast.LENGTH_SHORT).show();
        }
    }

    private void applyFormatting() {
        Spannable string = new SpannableStringBuilder(editText.getText());
        string.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        editText.setText(string);
        editText.setSelection(start, end);
    }
}