package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity implements InotesRVClicked{
    private RecyclerView recyclerView;
    private NoteViewModel noteViewModel;
    private FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        // making adapter and setting layout manager as Linear layout manager and settting adapter;
        floatingActionButton = findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,addNoteActivity.class);
                startActivity(intent);
            }
        });
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

    }

    @Override
    public void OnItemClicked(View v, int position, Notes notes) {
//            Toast.makeText(this, notes.getText().toString()+" Deleted!", Toast.LENGTH_SHORT).show();
            noteViewModel.Delete(notes);
             Log.d("krunal","Deleted "+ notes.getText().toString());

    }

}