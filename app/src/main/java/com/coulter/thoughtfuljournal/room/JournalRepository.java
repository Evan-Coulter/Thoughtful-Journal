package com.coulter.thoughtfuljournal.room;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Calendar;
import java.util.List;

public class JournalRepository {
    private final JournalDAO dao;
    private final LiveData<List<Journal>> journals;
    private static final String DEFAULT_NAME = "New Journal";

    public JournalRepository(Application application) {
        dao = JournalDatabase.getInstance(application).getJournalDao();
        journals = dao.getJournals();
    }

    public LiveData<List<Journal>> getJournals() {
        return journals;
    }

    public void insert(Journal journal) {
        JournalDatabase.writer.execute(() -> dao.insert(journal));
    }

    public void delete(Journal journal) {
        JournalDatabase.writer.execute(() -> dao.delete(journal));
    }

    public List<Journal> search(String journalName) {
        return dao.search(journalName);
    }


    public MutableLiveData<Journal> getNewJournal() {
        return new MutableLiveData<>(new Journal(DEFAULT_NAME, "", Calendar.getInstance().getTime(), false));
    }
    public MutableLiveData<Journal> getOldJournal(Journal journal) {
        MutableLiveData<Journal> liveData = getNewJournal();
        Journal newJournal = new Journal(journal.journal_name,
                journal.journal_content,
                journal.creation_date,
                journal.isDraft);
        newJournal.primaryKey = journal.primaryKey;
        liveData.setValue(newJournal);
        return liveData;
    }
}
