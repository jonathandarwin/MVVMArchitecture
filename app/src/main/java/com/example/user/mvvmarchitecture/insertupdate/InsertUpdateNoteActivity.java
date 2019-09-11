package com.example.user.mvvmarchitecture.insertupdate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.base.BaseActivity;
import com.example.model.Note;
import com.example.user.mvvmarchitecture.R;
import com.example.user.mvvmarchitecture.databinding.ActivityInsertUpdateNoteBinding;
import com.example.user.mvvmarchitecture.main.MainActivity;

public class InsertUpdateNoteActivity extends BaseActivity<ActivityInsertUpdateNoteBinding, InsertUpdateNoteViewModel>
        implements View.OnClickListener{

    public InsertUpdateNoteActivity(){
        super(InsertUpdateNoteViewModel.class, R.layout.activity_insert_update_note);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBinding().setModel(new Note());
        getBinding().btnSave.setOnClickListener(this);
    }

    @Override
    protected void setObserver() {
        super.setObserver();
        getViewModel().status.observe(this, status -> {
            switch(status){
                case InsertUpdateNoteViewModel.SHOW_LOADING:
                    showLoading();
                    break;
                case InsertUpdateNoteViewModel.REMOVE_LOADING:
                    removeLoading();
                    break;
                case InsertUpdateNoteViewModel.SUCCESS:
                    Toast.makeText(InsertUpdateNoteActivity.this, getResources().getString(R.string.message_insert_success), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.putExtra(MainActivity.EXTRA_KEY_LOAD_DATA, MainActivity.LOAD_DATA);
                    setResult(MainActivity.CODE_LOAD_DATA, intent);
                    finish();
                    break;
                case InsertUpdateNoteViewModel.ERROR:
                    Toast.makeText(this, getResources().getString(R.string.message_error), Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.equals(getBinding().btnSave)){
            Note note = getBinding().getModel();
            if(!getViewModel().validateInput(note)){
                Toast.makeText(this, getResources().getString(R.string.message_please_input_all_the_field), Toast.LENGTH_SHORT).show();
                return;
            }
            getViewModel().insertNote(note);
        }
    }
}
