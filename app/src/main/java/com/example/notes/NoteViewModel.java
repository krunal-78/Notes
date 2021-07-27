package com.example.notes;

import android.app.Application;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private final NoteRepository noteRepository;
    private final LiveData<List<Notes>> AllNotes;


    public NoteViewModel( Application application) {
        super(application);
        NotesDAO notesDAO = NoteDataBase.getDatabase(application).notesDAO();
        noteRepository = new NoteRepository(notesDAO);
        AllNotes = noteRepository.getAllNotes();
    }

    public LiveData<List<Notes>> getAllNotes() {
        return AllNotes;
    }
    // wrapper insert method that will call repository insert;
    public void Insert(Notes notes){
            noteRepository.Insert(notes);
    }
    public void Delete(Notes notes){

            noteRepository.Delete(notes);

    }
}
