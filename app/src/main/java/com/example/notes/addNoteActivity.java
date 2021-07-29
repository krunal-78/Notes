package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addNoteActivity extends AppCompatActivity {
    private Button addNote;
    private EditText inputNote;
    private NoteViewModel noteViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        inputNote = findViewById(R.id.inputNote);
        addNote = findViewById(R.id.addNote);

        noteViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(NoteViewModel.class);
        Log.d("krunal","got viewmodel!");
        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noteText = inputNote.getText().toString();
                if(noteText.isEmpty()){
                    Toast.makeText(addNoteActivity.this, "Can't insert empty text!", Toast.LENGTH_SHORT).show();
                }
                else{
                    noteViewModel.Insert(new Notes(noteText));
                    Log.d("krunal","Inserted "+noteText);
//                    Toast.makeText(MainActivity.this, noteText+" Inserted!", Toast.LENGTH_SHORT).show();
                    finish();
                }

//                noteViewModel.Insert(new Notes("krunal"));
            }
        });
    }
}