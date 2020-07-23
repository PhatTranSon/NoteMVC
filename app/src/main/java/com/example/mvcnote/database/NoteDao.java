package com.example.mvcnote.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM notes")
    Observable<NoteEntity> getAllNotes();

    @Insert
    Single<NoteEntity> add(NoteEntity noteEntity);

    @Delete
    Single<Integer> delete(NoteEntity noteEntity);
}
