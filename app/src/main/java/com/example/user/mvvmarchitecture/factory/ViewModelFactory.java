package com.example.user.mvvmarchitecture.factory;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.user.mvvmarchitecture.repository.NoteRepository;

import javax.inject.Inject;

public class ViewModelFactory implements ViewModelProvider.Factory {

    @Inject
    NoteRepository noteRepository;

    public ViewModelFactory(Context context, NoteRepository noteRepository){
        this.noteRepository = noteRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return null;
    }
}
