package com.coulter.thoughtfuljournal.fragments.spanhandler;

import android.content.Context;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.widget.EditText;


public class SpanHandler {
    private final EditText editText;
    private final StyleSpan newSpan;
    private final int start;
    private final int end;
    private Spannable text;
    private StyleSpan[] spans;

    public SpanHandler(EditText editText, StyleSpan newSpan, int start, int end) {
        this.editText = editText;
        this.newSpan = newSpan;
        this.start = start;
        this.end = end;
    }

    public void start() {
        text = editText.getText();
        spans = text.getSpans(start, end, StyleSpan.class);
        if(containsSameStyleSpan()) {
            if(containsTextWithoutSameStyleSpan()) {
                applySpans();
            } else {
                removeSpans();
            }
        } else {
            applySpans();
        }
        editText.setText(text);
        editText.setSelection(start, end);
    }

    private boolean containsSameStyleSpan() {
        for(StyleSpan span:spans) {
            if(span.getStyle() == newSpan.getStyle()) return true;
        }
        return false;
    }

    private boolean containsTextWithoutSameStyleSpan() {
        for(int i=start; i<end; i++) {
            if(indexDoesNotContainSameStyleSpan(i)) return true;
        }
        return false;
    }

    private void applySpans() {
        for (int i=start; i<end; i++) {
            if(indexDoesNotContainSameStyleSpan(i)) {
                text.setSpan(new StyleSpan(newSpan.getStyle()), i, i+1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
    }

    private void removeSpans() {
        for(StyleSpan span:spans) {
            if(span.getStyle() == newSpan.getStyle()){
                text.removeSpan(span);
            }
        }
    }

    private boolean indexDoesNotContainSameStyleSpan(int i) {
        for (StyleSpan span:text.getSpans(i, i+1, StyleSpan.class)) {
            if(span.getStyle() == newSpan.getStyle()) return false;
        }
        return true;
    }

}