package com.example.notes;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes_table")
public class Notes {
    @PrimaryKey(autoGenerate = true)
    private int id=0;

    @NonNull
    @ColumnInfo(name = "text")
    private final String text;


    public Notes(@NonNull String text){
        this.text = text;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getText() {
        return this.text;
    }

    public void setId(int id) {
        this.id = id;
    }
}
