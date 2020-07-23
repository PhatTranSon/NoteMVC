package com.example.mvcnote.database.mapper;

import com.example.mvcnote.database.NoteEntity;
import com.example.mvcnote.notes.Note;

public class NoteMapper {
    public static Note fromEntity(NoteEntity entity) {
        return new Note(
                entity.getId(),
                entity.getTitle(),
                entity.getBody()
        );
    }
}
