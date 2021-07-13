package com.coulter.thoughtfuljournal.room;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


@Entity(tableName = "journal_table")
public class Journal {
    @PrimaryKey(autoGenerate = true)
    public int primaryKey;

    @ColumnInfo(name = "journal_name")
    public String journal_name;

    @ColumnInfo(name = "journal_content")
    public String journal_content;

    @ColumnInfo(name="creation_date")
    public Date creation_date;

    public Journal(@NonNull String journal_name, @NonNull String journal_content, Date creation_date) {
        this.journal_name = journal_name;
        this.journal_content = journal_content;
        if(creation_date == null) {
            this.creation_date = Calendar.getInstance().getTime();
        } else{
            this.creation_date = creation_date;
        }
    }

    public boolean equals(Journal journal) {
        try {
            if(journal_name.equals(journal.journal_name) &&
                journal_content.equals(journal.journal_content) &&
                creation_date.equals(journal.creation_date)) {
                return true;
            }
        }catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
        return false;
    }

    @NotNull
    @Override
    public String toString() {
        return "Journal{" +
                "primaryKey=" + primaryKey +
                ", journal_name='" + journal_name + '\'' +
                ", journal_content='" + journal_content + '\'' +
                ", creation_date=" + creation_date +
                '}';
    }
}
