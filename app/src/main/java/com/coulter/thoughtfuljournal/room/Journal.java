package com.coulter.thoughtfuljournal.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

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

    public Journal(String journal_name, String journal_content, Date creation_date) {
        this.primaryKey = primaryKey;
        this.journal_name = journal_name;
        this.journal_content = journal_content;
        this.creation_date = creation_date;
    }

    public boolean equals(Journal journal) {
        try {
            return journal_name.equals(journal.journal_name)
                    && journal_content.equals(journal.journal_content)
                    && creation_date == journal.creation_date;
        }catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public String toString() {
        return "Journal{" +
                "primaryKey=" + primaryKey +
                ", journal_name='" + journal_name + '\'' +
                ", creation_date=" + creation_date +
                '}';
    }
}
