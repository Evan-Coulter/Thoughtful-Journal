package com.coulter.thoughtfuljournal.recyclerview;

import androidx.appcompat.widget.SearchView;

public interface FilterSubject {
    void attachFilterObserver(FilterObserver observer);
    SearchView.OnQueryTextListener getOnQueryTextListener();
}
