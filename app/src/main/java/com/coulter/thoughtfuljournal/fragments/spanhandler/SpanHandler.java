package com.coulter.thoughtfuljournal.fragments.spanhandler;

import android.content.Context;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.widget.EditText;
import android.widget.Toast;


public class SpanHandler {
    private final EditText editText;
    private final StyleSpan newSpan;
    private final int start;
    private final int end;
    private final Context context;
    private Spannable text;
    private StyleSpan[] spans;

    public SpanHandler(Context context, EditText editText, StyleSpan newSpan, int start, int end) {
        this.editText = editText;
        this.newSpan = newSpan;
        this.start = start;
        this.end = end;
        this.context = context;
    }

    public void start() {
        text = editText.getText();
        spans = text.getSpans(start, end, StyleSpan.class);
        if(containsSpan()) {
            if(containsTextWithoutSpan()) {
                Toast.makeText(context, "Apply Span 1", Toast.LENGTH_SHORT).show();
                applySpans();
            } else {
                Toast.makeText(context, "Remove Span", Toast.LENGTH_SHORT).show();
                removeSpans();
            }
        } else {
            Toast.makeText(context, "Apply Span 2", Toast.LENGTH_SHORT).show();
            applySpans();
        }
        editText.setText(text);
        editText.setSelection(start, end);
    }

    private boolean containsSpan() {
        return spans.length > 0;
    }

    private boolean containsTextWithoutSpan() {
        for(int i=start; i<end; i++) {
            if(text.getSpans(i, i+1, StyleSpan.class).length==0) return true;
        }
        return false;
    }

    private void applySpans() {
        for (int i=start; i<end; i++) {
            if(text.getSpans(i, i+1, StyleSpan.class).length==0) {
                text.setSpan(new StyleSpan(newSpan.getStyle()), i, i+1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
    }

    private void removeSpans() {
        for(StyleSpan span:spans) {
            text.removeSpan(span);
        }
    }
}