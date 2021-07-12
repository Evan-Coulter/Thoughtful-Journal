package com.coulter.thoughtfuljournal.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Journal.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class JournalDatabase extends RoomDatabase {
    private static volatile JournalDatabase instance;
    static final ExecutorService writer = Executors.newFixedThreadPool(4);

    public static synchronized JournalDatabase getInstance(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    JournalDatabase.class,
                    "journal_database"
            )
            .build();
        }
        return instance;
    }

    public abstract JournalDAO getJournalDao();
}
