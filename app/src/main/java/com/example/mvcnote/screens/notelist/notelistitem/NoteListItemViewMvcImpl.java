package com.example.mvcnote.screens.notelist.notelistitem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.mvcnote.R;
import com.example.mvcnote.notes.Note;
import com.example.mvcnote.screens.common.BaseObservableViewMvc;

public class NoteListItemViewMvcImpl extends BaseObservableViewMvc<NoteListItemViewMvc.Listener> implements NoteListItemViewMvc {
    private final TextView titleText, idText;

    public NoteListItemViewMvcImpl(LayoutInflater inflater, @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_note_list_item, parent, false));

        titleText = findViewById(R.id.note_title_edit_text);
        idText = findViewById(R.id.note_id_text);
    }

    @Override
    public void bindNote(final Note note) {
        titleText.setText(note.getTitle());
        idText.setText("Id: " + note.getId());
        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Listener listener : getListeners()) {
                    listener.onNoteClick(note.getId());
                }
            }
        });
    }
}
