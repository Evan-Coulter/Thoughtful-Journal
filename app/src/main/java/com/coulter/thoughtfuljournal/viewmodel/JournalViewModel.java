package com.coulter.thoughtfuljournal.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.coulter.thoughtfuljournal.room.Journal;
import com.coulter.thoughtfuljournal.room.JournalRepository;

import java.util.List;

public class JournalViewModel extends AndroidViewModel {
    private final JournalRepository repository;
    private final LiveData<List<Journal>> journals;
    public MutableLiveData<Journal> currentJournal;

    public JournalViewModel(Application application){
        super(application);
        repository = new JournalRepository(application);
        journals = repository.getJournals();
    }

    public LiveData<List<Journal>> getJournals() {
        return journals;
    }

    public void insert(Journal journal) {
        repository.insert(journal);
    }

    public void delete(String journalName) {
        repository.delete(journalName);
    }

    public List<Journal> search(String journalName) {
        return repository.search(journalName);
    }

    public void postNewJournal() {
        currentJournal = repository.getNewJournal();
    }
    public void postOldJournal(Journal journal) {
        currentJournal = repository.getOldJournal(journal);
    }
}
