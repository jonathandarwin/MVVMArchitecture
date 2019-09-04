package com.example.user.mvvmarchitecture.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.user.mvvmarchitecture.model.Note;

import java.util.List;

public class NoteRepository {
    public List<Note> getListNote()
    {
        return NoteData.listNote;
    }

    public LiveData<Boolean> insertNote(Note note){
        MutableLiveData<Boolean> bool = new MutableLiveData<>();
        note.setId(NoteData.listNote.size()-1);
        NoteData.listNote.add(note);
        bool.setValue(true);
        return bool;
    }
}
