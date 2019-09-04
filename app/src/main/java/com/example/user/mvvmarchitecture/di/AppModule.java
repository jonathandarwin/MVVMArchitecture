package com.example.user.mvvmarchitecture.di;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;

import com.example.user.mvvmarchitecture.app.main.MainViewModel;
import com.example.user.mvvmarchitecture.repository.NoteRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Context context;
    private Application application;

    public AppModule(Context context){
        this.context = context;
    }

    @Provides
    @Singleton
    public NoteRepository provideNoteRepository(){
        return new NoteRepository();
    }

    @Provides
    public MainViewModel provideViewModelFactory(){
//        return ViewModelProviders.of(this).get(MainViewModel.class);
        return new MainViewModel();
    }
}
