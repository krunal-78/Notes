package com.example.notes;

import android.app.Application;
import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;

import java.util.List;

class NoteRepository {
//    private LiveData<List<Notes>> AllNotes;
    private final NotesDAO notesDAO;

    NoteRepository(NotesDAO notesDAO){
//        NoteDataBase db = NoteDataBase.getDatabase(application);
//        notesDAO = db.notesDAO();
//        AllNotes = notesDAO.getAllNotes();
        this.notesDAO = notesDAO;
    }
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Notes>> getAllNotes(){
        return notesDAO.getAllNotes();
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.Better ui experience;
    public void Insert(Notes notes) {
        NoteDataBase.databaseWriterExecutor.execute(()->{
             notesDAO.Insert(notes);
        });
    }
    //Executor Service is to perform task in background thread instead of main thread to give better ui experience;

    public void Delete(Notes notes){
        NoteDataBase.databaseWriterExecutor.execute(()->{
            notesDAO.Delete(notes);
        });
    }

}

