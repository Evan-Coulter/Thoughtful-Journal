package com.coulter.thoughtfuljournal.room;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class JournalTest {
    @Test
    public void testGoodConstructor() {
        Date date = Calendar.getInstance().getTime();
        Journal journal = new Journal("John", "", date, false);
        assertEquals(journal.journal_content, "");
        assertEquals(journal.journal_name,"John");
        assertEquals(journal.creation_date, date);
        assertEquals(journal.primaryKey, 0);
        assertEquals(journal.isDraft, false);
    }

    @Test
    public void testBadConstructor() {
        Date date = Calendar.getInstance().getTime();
        //shouldn't throw exception.
        Journal journal = new Journal(null, null, null, false);
    }

    @Test
    public void testEquals() {
        Date date = Calendar.getInstance().getTime();
        Journal journal = new Journal("","",date, false);
        Journal journal2 = new Journal("","",date, false);
        assertTrue(journal.equals(journal2));
    }
}