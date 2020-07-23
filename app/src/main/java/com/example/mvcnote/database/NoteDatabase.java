package com.example.mvcnote.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

@Database(entities = {NoteEntity.class}, version = 2)
public abstract class NoteDatabase extends RoomDatabase  {
    public abstract NoteDao getDao();

    //Prepopulate test
    static RoomDatabase.Callback prepopulateCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }

        @Override
        public void onOpen(@NonNull final SupportSQLiteDatabase db) {
            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    Log.i("CALLED", "yes");
                    db.execSQL("INSERT INTO notes(title, body) VALUES(\'Hello World\', \'Hello\')");
                }
            });
        }
    };

    //Singleton
    private static volatile NoteDatabase noteDatabase;
    private static final String DB_NAME = "note_db";

    public static synchronized NoteDatabase getInstance(Context context) {
        if (noteDatabase == null) {
            noteDatabase = create(context);
        }
        return noteDatabase;
    }

    private static NoteDatabase create(final Context context) {

        return Room.databaseBuilder(
                context,
                NoteDatabase.class,
                DB_NAME
        )
                .fallbackToDestructiveMigration()
                .build();
    }
}
