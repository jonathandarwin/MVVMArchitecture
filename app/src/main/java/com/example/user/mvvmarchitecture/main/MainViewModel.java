package com.example.user.mvvmarchitecture.main;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;

import com.example.model.Note;
import com.example.repository.NoteRepository;

import java.util.List;

/**
 * Created by User on 3/27/2019.
 */

public class MainViewModel extends ViewModel {
    public final static int SHOW_LOADING = 1;
    public final static int REMOVE_LOADING = 2;
    public final static int ERROR = 3;

    protected MutableLiveData<List<Note>> listNote = new MutableLiveData<>();
    protected MutableLiveData<Integer> status = new MutableLiveData<>();

    public void getListNote(){
        status.postValue(SHOW_LOADING);
        // add delay
        new Handler().postDelayed(() -> {
            NoteRepository repo = new NoteRepository();
            listNote.setValue(repo.getListNote());
            status.postValue(REMOVE_LOADING);
        }, 1500);
    }
}
