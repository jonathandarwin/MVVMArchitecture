package com.example.user.mvvmarchitecture.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.user.mvvmarchitecture.model.Note;
import com.example.user.mvvmarchitecture.repository.NoteRepository;

import java.util.ArrayList;
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
