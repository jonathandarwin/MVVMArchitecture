package com.example.user.mvvmarchitecture.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.example.user.mvvmarchitecture.R;
import com.example.user.mvvmarchitecture.adapter.NoteAdapter;
import com.example.user.mvvmarchitecture.common.BaseActivity;
import com.example.user.mvvmarchitecture.common.NoteData;
import com.example.user.mvvmarchitecture.databinding.ActivityMainBinding;
import com.example.user.mvvmarchitecture.model.Note;
import com.example.user.mvvmarchitecture.viewmodel.MainViewModel;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel>
        implements View.OnClickListener{

    List<Note> listNote = new ArrayList<>();
    NoteAdapter adapter;

    public MainActivity(){
        super(MainViewModel.class, R.layout.activity_main);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new NoteAdapter(MainActivity.this, listNote);
        getBinding().recyclerview.setLayoutManager(new LinearLayoutManager(this));
        getBinding().recyclerview.setAdapter(adapter);
        NoteData.getInit();
        getBinding().fabAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(getBinding().fabAdd)){
            gotoIntent(InsertUpdateNote.class, null, false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getViewModel().getListNote().observe(this, new android.arch.lifecycle.Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                listNote.clear();
                listNote.addAll(notes);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
