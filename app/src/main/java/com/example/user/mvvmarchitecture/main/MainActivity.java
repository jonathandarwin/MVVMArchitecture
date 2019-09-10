package com.example.user.mvvmarchitecture.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.example.base.BaseActivity;
import com.example.model.Note;
import com.example.repository.NoteData;
import com.example.user.mvvmarchitecture.R;
import com.example.user.mvvmarchitecture.databinding.ActivityMainBinding;
import com.example.user.mvvmarchitecture.insertupdate.InsertUpdateNoteActivity;
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
            gotoIntent(InsertUpdateNoteActivity.class, null, false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        clearData();
        showLoading();

        getViewModel().getListNote().observe(this, notes -> {
            // add delay
            new Handler().postDelayed(() -> {
                removeLoading();

                listNote.addAll(notes);
                adapter.notifyDataSetChanged();
            },1500);
        });
    }

    private void clearData(){
        listNote.clear();
        adapter.notifyDataSetChanged();
    }
}
