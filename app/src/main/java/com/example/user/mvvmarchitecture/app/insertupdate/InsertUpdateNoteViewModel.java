package com.example.user.mvvmarchitecture.app.insertupdate;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.user.mvvmarchitecture.model.Note;
import com.example.user.mvvmarchitecture.repository.NoteRepository;

public class InsertUpdateNoteViewModel extends ViewModel {

    public LiveData<Boolean> insertNote(Note note){
        NoteRepository repo = new NoteRepository();
        return repo.insertNote(note);
    }
}
