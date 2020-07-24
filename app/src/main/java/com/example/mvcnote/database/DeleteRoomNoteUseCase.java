package com.example.mvcnote.database;

import com.example.mvcnote.common.BaseObservable;
import com.example.mvcnote.database.mapper.NoteMapper;
import com.example.mvcnote.notes.Note;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DeleteRoomNoteUseCase extends BaseObservable<DeleteRoomNoteUseCase.Listener> {
    public interface Listener {
        void onNoteDeleteSuccess();
        void onNoteDeleteError(Throwable error);
    }
    private final NoteDao mNoteDao;

    public DeleteRoomNoteUseCase(NoteDao noteDao) {
        mNoteDao = noteDao;
    }

    public void deleteNoteAndNotify(Note note) {
        //Convert note to note entity
        NoteEntity noteEntity = NoteMapper.toEntity(note);
        //Delete
        mNoteDao.delete(noteEntity)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //Nothing
                    }

                    @Override
                    public void onComplete() {
                        onDeleteSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        onDeleteError(e);
                    }
                });
    }

    private void onDeleteSuccess() {
        for (Listener listener : getListeners()) {
            listener.onNoteDeleteSuccess();
        }
    }

    private void onDeleteError(Throwable error) {
        for (Listener listener : getListeners()) {
            listener.onNoteDeleteError(error);
        }
    }
}
