package com.coulter.thoughtfuljournal.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class JournalRepository {
    private JournalDAO dao;
    private final List<Journal> journals;

    public JournalRepository(Application application) {
        dao = JournalDatabase.getInstance(application).getJournalDao();
        journals = dao.getJournals();
    }

    public List<Journal> getJournals() {
        return journals;
    }

    public void insert(Journal journal) {
        JournalDatabase.writer.execute(()->{
            dao.insert(journal);
        });
    }
}
