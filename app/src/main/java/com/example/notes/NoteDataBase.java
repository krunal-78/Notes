package com.example.notes;

import android.content.Context;
import android.provider.ContactsContract;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Notes.class},version = 1,exportSchema = false)
public abstract class NoteDataBase extends RoomDatabase {
    public abstract NotesDAO notesDAO();

    private static volatile NoteDataBase INSTANCE;
    private static final int NUMBERS_OF_THREADS = 4;
    //ExecutorService with fixed threads to run database operations on a background thread
    static final ExecutorService databaseWriterExecutor = Executors.newFixedThreadPool(NUMBERS_OF_THREADS);
    // singleton for Notesdatabase to prevent multiple instances at a time;
    static NoteDataBase getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (NoteDataBase.class){
                if(INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NoteDataBase.class,"note_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
