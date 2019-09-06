package com.example.repository;

import android.os.Handler;
import android.util.Log;

import com.example.model.Note;

import java.util.List;

import rx.Observable;
import rx.subjects.PublishSubject;

public class NoteRepository {
    public List<Note> getListNote()
    {
        return NoteData.listNote;
    }

    public Observable<Boolean> insertNote(Note note){
        PublishSubject<Boolean> result = PublishSubject.create();
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            try{
                note.setId(NoteData.listNote.size()-1);
                NoteData.listNote.add(note);
                result.onNext(true);
            }
            catch (Exception e){
                e.printStackTrace();
                result.onError(e);
            }
            result.onCompleted();
        }, 1000);
        return result;
    }
}



