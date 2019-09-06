package com.example.user.mvvmarchitecture.insertupdate;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.base.BaseActivity;
import com.example.model.Note;
import com.example.user.mvvmarchitecture.R;
import com.example.user.mvvmarchitecture.databinding.ActivityInsertUpdateNoteBinding;

public class InsertUpdateNote extends BaseActivity<ActivityInsertUpdateNoteBinding, InsertUpdateNoteViewModel>
        implements View.OnClickListener{

    public InsertUpdateNote(){
        super(InsertUpdateNoteViewModel.class, R.layout.activity_insert_update_note);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBinding().setModel(new Note());
        getBinding().btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(getBinding().btnSave)){
            Note note = getBinding().getModel();
            if(!getViewModel().validateInput(note)){
                Toast.makeText(this, "Please input all the field", Toast.LENGTH_SHORT).show();
                return;
            }

            getBinding().loader.setVisibility(View.VISIBLE);
            getViewModel().insertNote(note).observe(InsertUpdateNote.this, result -> {
                getBinding().loader.setVisibility(View.GONE);
                if(result){
                    Toast.makeText(InsertUpdateNote.this, "Insert Success", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(InsertUpdateNote.this, "Insert Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
