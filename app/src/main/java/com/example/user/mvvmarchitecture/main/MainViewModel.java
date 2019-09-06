package com.example.user.mvvmarchitecture.main;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.model.Note;
import com.example.repository.NoteRepository;

import java.util.List;

/**
 * Created by User on 3/27/2019.
 */

public class MainViewModel extends ViewModel {
    protected MutableLiveData<List<Note>> listNote;

    public LiveData<List<Note>> getListNote(){
        listNote = new MutableLiveData<>();
        NoteRepository repo = new NoteRepository();
        listNote.setValue(repo.getListNote());
        return listNote;
    }
}
