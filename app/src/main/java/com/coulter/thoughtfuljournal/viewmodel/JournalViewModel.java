package com.coulter.thoughtfuljournal.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.coulter.thoughtfuljournal.room.Journal;
import com.coulter.thoughtfuljournal.room.JournalRepository;

import java.util.List;

public class JournalViewModel extends AndroidViewModel {
    private final JournalRepository repository;
    private final List<Journal> journals;

    public JournalViewModel(Application application){
        super(application);
        repository = new JournalRepository(application);
        journals = repository.getJournals();
    }

    public List<Journal> getJournals() {
        return journals;
    }

    public void insert(Journal journal) {
        repository.insert(journal);
    }
}
