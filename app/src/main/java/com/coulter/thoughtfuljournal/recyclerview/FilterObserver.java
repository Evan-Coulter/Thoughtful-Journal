package com.coulter.thoughtfuljournal.recyclerview;

import androidx.appcompat.widget.SearchView;

public interface FilterObserver {
    SearchView.OnQueryTextListener provideOnQueryTextListener();
}
