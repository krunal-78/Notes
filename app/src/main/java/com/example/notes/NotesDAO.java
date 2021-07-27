package com.example.notes;

import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotesDAO {
    // for inserting a Note ;
    // resolving conflict also;

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void Insert(Notes notes);

    // for deleting a Note;
    @Delete
    void Delete(Notes notes);

    // for getting all Notes in order of their ids (auto generated);
    // have to provide SQL Query;
    // for observing updated data we make this LiveData; track changes via an observer in main activity;

    @Query("SELECT * FROM notes_table ORDER BY id ASC")
    LiveData<List<Notes>> getAllNotes();
}
