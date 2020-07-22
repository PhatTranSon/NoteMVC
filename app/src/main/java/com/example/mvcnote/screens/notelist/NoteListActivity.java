package com.example.mvcnote.screens.notelist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mvcnote.R;

public class NoteListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_note_list);
    }
}