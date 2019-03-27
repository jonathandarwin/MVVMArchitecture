package com.example.user.mvvmarchitecture;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.user.mvvmarchitecture.databinding.ActivityInsertUpdateNoteBinding;
import com.example.user.mvvmarchitecture.model.Note;
import com.example.user.mvvmarchitecture.viewmodel.NoteViewModel;

public class InsertUpdateNote extends AppCompatActivity implements View.OnClickListener{

    ActivityInsertUpdateNoteBinding binding;
    NoteViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_insert_update_note);
        binding.setModel(new Note());
        viewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        binding.btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(binding.btnSave)){
            Note note = binding.getModel();
            viewModel.insertNote(note).observe(InsertUpdateNote.this, new Observer<Boolean>() {
                @Override
                public void onChanged(@Nullable Boolean aBoolean) {
                    if(aBoolean){
                        Toast.makeText(InsertUpdateNote.this, "Insert Success", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else{
                        Toast.makeText(InsertUpdateNote.this, "Insert Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
