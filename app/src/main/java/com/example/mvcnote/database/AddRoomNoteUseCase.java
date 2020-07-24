package com.example.mvcnote.database;

import android.util.Log;

import com.example.mvcnote.common.BaseObservable;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddRoomNoteUseCase extends BaseObservable<AddRoomNoteUseCase.Listener> {
    private final NoteDao mNoteDao;

    public interface Listener {
        void onAddNoteSuccess();
        void onAddNoteError(Throwable error);
    }

    public AddRoomNoteUseCase(NoteDao noteDao) {
        this.mNoteDao = noteDao;
    }

    public void addNoteAndNotify(String title, String body) {
        mNoteDao.add(new NoteEntity(title,body))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //Nothing
                    }

                    @Override
                    public void onComplete() {
                        onAddSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        //Log.i("ERROR", e.getMessage());
                        onAddError(e);
                    }
                });
    }

    private void onAddSuccess() {
        for (Listener listener : getListeners()) {
            listener.onAddNoteSuccess();
        }
    }

    private void onAddError(Throwable error) {
        for (Listener listener : getListeners()) {
            listener.onAddNoteError(error);
        }
    }
}
