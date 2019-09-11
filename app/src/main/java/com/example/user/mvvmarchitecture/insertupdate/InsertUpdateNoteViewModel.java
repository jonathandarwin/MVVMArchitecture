package com.example.user.mvvmarchitecture.insertupdate;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.example.model.Note;
import com.example.repository.NoteRepository;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class InsertUpdateNoteViewModel extends ViewModel {

    public final static int SHOW_LOADING = 1;
    public final static int REMOVE_LOADING = 2;
    public final static int ERROR = 3;
    public final static int SUCCESS = 4;

    protected MutableLiveData<Integer> status = new MutableLiveData<>();

    public void insertNote(Note note){
        status.setValue(SHOW_LOADING);
        NoteRepository repo = new NoteRepository();
        repo.insertNote(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(res -> {
                    status.setValue(REMOVE_LOADING);
                    status.setValue(SUCCESS);
                },
                error -> {
                    status.setValue(REMOVE_LOADING);
                    status.setValue(ERROR);
               });
    }

    public boolean validateInput(Note note){
        if(note.getTitle() != null && !note.getTitle().trim().equals("") &&
                note.getDescription() != null && !note.getDescription().trim().equals(""))
            return true;
        return false;
    }
}
