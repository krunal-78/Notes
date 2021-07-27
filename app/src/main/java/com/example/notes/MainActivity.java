package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity implements InotesRVClicked{
    private NoteViewModel noteViewModel;
    private EditText inputNote;
    private Button addNote;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputNote = findViewById(R.id.inputNote);
        addNote = findViewById(R.id.addNote);
        recyclerView = findViewById(R.id.recyclerView);
        // making adapter and setting layout manager as Linear layout manager and settting adapter;

        NotesAdapter notesAdapter = new NotesAdapter(this);
        recyclerView.setAdapter(notesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.d("krunal","adapter set!");
        // important;
        noteViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(NoteViewModel.class);
        Log.d("krunal","got viewmodel!");
        noteViewModel.getAllNotes().observe(this,notes-> {
            if(notes!=null) {
                notesAdapter.updateList(notes);
                Log.d("krunal", "updated list!");
            }
        });
        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noteText = inputNote.getText().toString();
                if(noteText.isEmpty()){
                    Toast.makeText(MainActivity.this, "Can't insert empty text!", Toast.LENGTH_SHORT).show();
                }
                else{
                    noteViewModel.Insert(new Notes(noteText));
                    Log.d("krunal","Inserted "+noteText);
//                    Toast.makeText(MainActivity.this, noteText+" Inserted!", Toast.LENGTH_SHORT).show();

                }
//                noteViewModel.Insert(new Notes("krunal"));
            }
        });
    }

    @Override
    public void OnItemClicked(View v, int position, Notes notes) {
//            Toast.makeText(this, notes.getText().toString()+" Deleted!", Toast.LENGTH_SHORT).show();
            noteViewModel.Delete(notes);
             Log.d("krunal","Deleted "+ notes.getText().toString());

    }

}