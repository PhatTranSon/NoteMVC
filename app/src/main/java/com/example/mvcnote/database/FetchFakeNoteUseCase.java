package com.example.mvcnote.database;

import android.util.Log;

import com.example.mvcnote.common.BaseObservable;
import com.example.mvcnote.database.mapper.NoteMapper;
import com.example.mvcnote.notes.Note;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.FlowableSubscriber;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FetchFakeNoteUseCase extends BaseObservable<FetchFakeNoteUseCase.Listener> {
    public interface Listener {
        void onFetchNotesSuccess(List<Note> notes);
        void onFetchNotesError(Throwable t);
    }

    private final NoteDao mNoteDao;

    public FetchFakeNoteUseCase(NoteDao noteDao) {
        this.mNoteDao = noteDao;
    }

    public void fetchNotesAndNotify() {
        //Fake notes
        mNoteDao.getAllNotes()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<NoteEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("STATE", "Subscribed");
                    }

                    @Override
                    public void onNext(List<NoteEntity> noteEntities) {
                        List<Note> notes = new ArrayList<>();
                        for (NoteEntity entity : noteEntities) {
                            notes.add(NoteMapper.fromEntity(entity));
                        }
                        onFetchSuccess(notes);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e("ERROR", t.getMessage());
                        onFetchError(t);
                    }

                    @Override
                    public void onComplete() {
                        Log.i("STATE", "Complete");
                    }
                });
    }

    private void onFetchSuccess(List<Note> notes) {
        for (Listener listener : getListeners()) {
            listener.onFetchNotesSuccess(notes);
        }
    }

    private void onFetchError(Throwable throwable) {
        for (Listener listener : getListeners()) {
            listener.onFetchNotesError(throwable);
        }
    }
}
