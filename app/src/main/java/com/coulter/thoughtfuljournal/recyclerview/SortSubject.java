package com.coulter.thoughtfuljournal.recyclerview;

import android.view.MenuItem;

public interface SortSubject {
    void attachSortObserver(SortObserver observer);
    boolean notifySortButtonClicked(MenuItem clickedMenuItem);
}
