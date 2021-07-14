package com.coulter.thoughtfuljournal.room;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class JournalDAOTest {
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
    public void testInsert() {
        Journal journal = new Journal("Name", "Content", Calendar.getInstance().getTime());
        JournalDAO dao = database.getJournalDao();
        dao.insert(journal);
        Journal returnedJournal = dao.getJournals().get(0);
        if(journal.equals(returnedJournal)) {
            assertTrue(true);
        } else {
            fail();
        }
    }

    @Test
    public void testDelete() {
        Journal journal = new Journal("Name", "Content", Calendar.getInstance().getTime());
        JournalDAO dao = database.getJournalDao();
        dao.insert(journal);
        dao.delete(journal.journal_name);
        assertEquals(dao.getJournals().size(), 0);
    }

    @Test
    public void testSearch() {
        List<Journal> journals = new ArrayList<>();
        for(int i = 0; i<3; i++) {
            journals.add(new Journal(Integer.toString(i),"", new Date()));
        }
        for (Journal j:journals) {
            dao.insert(j);
        }
        List<Journal> foundJournals = database.getJournalDao().search("2");
        assertTrue(journals.get(2).equals(foundJournals.get(0)));
    }
}
