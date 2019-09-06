package com.example.user.mvvmarchitecture.insertupdate;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.example.model.Note;
import com.example.repository.NoteRepository;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class InsertUpdateNoteViewModel extends ViewModel {

    public LiveData<Boolean> insertNote(Note note){
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        NoteRepository repo = new NoteRepository();
        repo.insertNote(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(res -> result.setValue(true),
                           error -> result.setValue(false));
        return result;
    }

    public boolean validateInput(Note note){
        if(note.getTitle() != null && !note.getTitle().trim().equals("") &&
                note.getDescription() != null && !note.getDescription().trim().equals(""))
            return true;
        return false;
    }
}
