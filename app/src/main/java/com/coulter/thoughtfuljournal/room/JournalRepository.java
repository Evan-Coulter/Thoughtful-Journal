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
        JournalDatabase.writer.execute(()->{
            dao.insert(journal);
        });
    }

    public void delete(String journalName) {
        dao.delete(journalName);
    }

    public List<Journal> search(String journalName) {
        return dao.search(journalName);
    }

    public MutableLiveData<Journal> getNewJournal(){
        return new MutableLiveData<>(new Journal(DEFAULT_NAME,"",Calendar.getInstance().getTime()));
    }
}
