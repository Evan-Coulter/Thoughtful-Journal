package com.coulter.thoughtfuljournal.room;

import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class JournalTest {
    static final String name = "Name";
    static final String content = "Content";
    static Date date;
    static {
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse("25/12/2021");
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
    }

    public void testEquals() {
        Journal journal1 = new Journal(name, content, date);
        Journal journal2 = new Journal(name, content, date);
        assertEquals(journal1, journal2);
    }

    public void testNotEquals() {
        Journal journal1 = new Journal(name, content, date);
        Journal journal2 = new Journal(content, name, date);
        assertNotEquals(journal1, journal2);
    }

    @Test
    public void testConstructor() {
        int size = 2;
        String[] names = {"", "Name"};
        String[] content = {"", "Lorem ipsum, or lipsum as it is sometimes known, " +
                "is dummy text used in laying out print, graphic or web designs."};
        String[] stringDates = {"00/00/0000", "25/12/2021"};
        Date[] dates = {new Date(),new Date()};
        for(int i=0; i<size; i++) {
            try {
                dates[i] = new SimpleDateFormat("dd/MM/yyyy").parse(stringDates[i]);
            } catch(ParseException exception) {
                dates[i] = null;
            }
        }
        for(int i=0; i<size; i++) {
            try {
                Journal journal = new Journal(names[i], content[i], dates[i]);
                if (!journal.journal_name.equals(names[i]) ||
                        !journal.journal_content.equals(content[i]) ||
                        !journal.creation_date.equals(dates[i])){
                    fail();
                }
            } catch (Exception exception) {
                Log.e("Constructor Test Failed", exception.toString());
                fail();
            }
        }
        assertTrue(true);
    }
}