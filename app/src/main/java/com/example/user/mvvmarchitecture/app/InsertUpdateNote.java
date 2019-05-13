package com.example.user.mvvmarchitecture.app;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.user.mvvmarchitecture.R;
import com.example.user.mvvmarchitecture.common.BaseActivity;
import com.example.user.mvvmarchitecture.databinding.ActivityInsertUpdateNoteBinding;
import com.example.user.mvvmarchitecture.model.Note;
import com.example.user.mvvmarchitecture.viewmodel.InsertUpdateNoteViewModel;
import com.example.user.mvvmarchitecture.viewmodel.MainViewModel;

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
            getViewModel().insertNote(note).observe(InsertUpdateNote.this, new Observer<Boolean>() {
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
