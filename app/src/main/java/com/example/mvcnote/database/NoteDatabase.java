package com.example.mvcnote.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {NoteEntity.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase  {
    public abstract NoteDao getDao();

    //Singleton
    private static volatile NoteDatabase noteDatabase;
    private static final String DB_NAME = "note_db";

    static synchronized NoteDatabase getInstance(Context context) {
        if (noteDatabase == null) {
            noteDatabase = create(context);
        }
        return noteDatabase;
    }

    private static NoteDatabase create(Context context) {
        return Room.databaseBuilder(
                context,
                NoteDatabase.class,
                DB_NAME
        ).build();
    }
}
