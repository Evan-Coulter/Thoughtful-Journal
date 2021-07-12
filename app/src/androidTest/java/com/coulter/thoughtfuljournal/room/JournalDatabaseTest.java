package com.coulter.thoughtfuljournal.room;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class JournalDatabaseTest {
    private JournalDatabase database;
    private JournalDAO dao;

    @Before
    public void createDB() {
        Context context = ApplicationProvider.getApplicationContext();
        database = Room.inMemoryDatabaseBuilder(context, JournalDatabase.class)
                .build();
        dao = database.getJournalDao();
    }

    @After
    public void closeDB() {
        database.close();
    }

    @Test
    public void testAddAUser() throws InterruptedException {
        Date date = Calendar.getInstance().getTime();
        String name = "Hi";
        String journalEntry = "Hello World this is my journal!";
        Journal journal = new Journal(name, journalEntry, date);
        Thread addThread = new Thread(){
            @Override
            public synchronized void start() {
                dao.insert(journal);
            }
        };
        addThread.start();
        addThread.join();
        List<Journal> journalList = dao.getJournals();
        Assert.assertTrue(journal.equals(journalList.get(0)));
    }
}