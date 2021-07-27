package com.coulter.thoughtfuljournal.room;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DateConverterTest {
    @Test
    public void test() {
        Date date = Calendar.getInstance().getTime();
        Long timestamp = DateConverter.dateToTimestamp(date);
        assertEquals(DateConverter.dateToTimestamp(date), timestamp);
        assertEquals(DateConverter.fromTimestamp(timestamp), date);
    }
}