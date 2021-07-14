package com.coulter.thoughtfuljournal.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface JournalDAO {
    @Query("SELECT * FROM journal_table")
    List<Journal> getJournals();

    @Query("SELECT * FROM journal_table WHERE journal_name LIKE '%' || :query || '%'")
    List<Journal> search(String query);

    @Insert
    void insert(Journal journal);

    @Query("DELETE FROM journal_table WHERE journal_name = :journalName ")
    void delete(String journalName);
}
