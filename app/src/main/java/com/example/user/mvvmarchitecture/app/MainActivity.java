package com.example.user.mvvmarchitecture.app;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.user.mvvmarchitecture.R;
import com.example.user.mvvmarchitecture.adapter.NoteAdapter;
import com.example.user.mvvmarchitecture.common.NoteData;
import com.example.user.mvvmarchitecture.databinding.ActivityMainBinding;
import com.example.user.mvvmarchitecture.model.Note;
import com.example.user.mvvmarchitecture.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ActivityMainBinding binding;
    List<Note> listNote = new ArrayList<>();
    MainViewModel viewModel;
    NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        adapter = new NoteAdapter(MainActivity.this, listNote);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerview.setAdapter(adapter);

        NoteData.getInit();
        binding.fabAdd.setOnClickListener(this);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(binding.fabAdd)){
            Intent intent = new Intent(MainActivity.this, InsertUpdateNote.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getListNote().observe(this, new android.arch.lifecycle.Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                listNote.clear();
                listNote.addAll(notes);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
