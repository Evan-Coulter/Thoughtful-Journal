package com.coulter.thoughtfuljournal.fragments.spanhandler;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Parcelable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.widget.EditText;
import android.widget.Toast;


public class SpanHandler {
    private final EditText editText;
    private final StyleSpan newSpan;
    private final int start;
    private final int end;
    private final Context context;

    public SpanHandler(Context context, EditText editText, StyleSpan newSpan, int start, int end) {
        this.editText = editText;
        this.newSpan = newSpan;
        this.start = start;
        this.end = end;
        this.context = context;
    }

    public void start() {
        Spannable text = editText.getText();
        StyleSpan[] spans = text.getSpans(start, end, StyleSpan.class);
        if(hasBothSpans(text, spans)) {
            handleBothSpansCase();
            return;
        }
        if(spans.length == 0) {
            /*If selected text has no formatting
                then apply new formatting.*/
            text.setSpan(newSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        else {
            for (StyleSpan span : spans) {
                int spanStart = text.getSpanStart(span);
                int spanEnd = text.getSpanEnd(span);
                if (newSpan.getStyle() == span.getStyle()) {
                    /*Will either need to remove or replace span here
                        so its safe to remove*/
                    text.removeSpan(span);
                    Toast.makeText(context, "Removing " + Integer.valueOf(span.getStyle()).toString(), Toast.LENGTH_SHORT).show();
                }
                /*If new span is encapsulated by previous span then remove previous span (already
                    accomplished by last if statement. It's placed there to save typing it twice.)*/
                if (spanStart <= start && spanEnd >= end) {
                    /*Unless it's of a different type. Then we can apply it regardless of it the old span
                     * was larger.*/
                    if (newSpan.getStyle() != span.getStyle()) {
                        Toast.makeText(context, "1)Adding " + Integer.valueOf(span.getStyle()).toString(), Toast.LENGTH_SHORT).show();
                        text.setSpan(newSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                } else {
                    /*If new span goes past the bounds of the previous span then its safe to apply
                     * regardless of any other conditions.*/
                    Toast.makeText(context, "2)Adding " + Integer.valueOf(span.getStyle()).toString(), Toast.LENGTH_SHORT).show();
                    text.setSpan(newSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }
        editText.setText(text);
        editText.setSelection(start, end);
    }

    private boolean hasBothSpans(Spannable text, StyleSpan[] spans) {
        return false;
    }

    private void handleBothSpansCase() {
    }
}