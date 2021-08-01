package com.coulter.thoughtfuljournal.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

//TODO: getJournals now returns live data. Need to retest it.
@Dao
public interface JournalDAO {
    @Query("SELECT * FROM journal_table")
    LiveData<List<Journal>> getJournals();

    @Query("SELECT * FROM journal_table WHERE journal_name LIKE '%' || :query || '%'")
    List<Journal> search(String query);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Journal journal);

    @Delete
    void delete(Journal journal);

    @Query("SELECT * FROM journal_table WHERE primaryKey = :id")
    LiveData<Journal> get(int id);
}
