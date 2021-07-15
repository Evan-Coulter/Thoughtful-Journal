package com.coulter.thoughtfuljournal.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

//TODO: getJournals now returns live data. Need to retest it.
@Dao
public interface JournalDAO {
    @Query("SELECT * FROM journal_table")
    LiveData<List<Journal>> getJournals();

    @Query("SELECT * FROM journal_table WHERE journal_name LIKE '%' || :query || '%'")
    List<Journal> search(String query);

    @Insert
    void insert(Journal journal);

    @Query("DELETE FROM journal_table WHERE journal_name = :journalName ")
    void delete(String journalName);
}
