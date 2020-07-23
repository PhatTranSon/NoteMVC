package com.example.mvcnote.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM notes")
    Observable<List<NoteEntity>> getAllNotes();

    @Insert
    Completable add(NoteEntity noteEntity);

    @Delete
    Completable delete(NoteEntity noteEntity);
}
