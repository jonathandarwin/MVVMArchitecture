package com.example.base;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

public abstract class BaseActivity<DataBinding extends ViewDataBinding, VM extends ViewModel> extends AppCompatActivity {

    protected static int REQUEST_CODE = 1000;

    VM viewModel;
    DataBinding binding;
    Class<VM> vm;
    int layout;
    ViewGroup root;
    View loader;

    public BaseActivity(){

    }

    public BaseActivity(Class<VM> vm, int layout){
        this.vm = vm;
        this.layout = layout;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        viewModel = ViewModelProviders.of(this).get(vm);
        binding = DataBindingUtil.setContentView(this, layout);

        // loader
        root = findViewById(android.R.id.content);
        loader = LayoutInflater.from(this).inflate(R.layout.loader, null);

        setListener();
        setObserver();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(getCurrentFocus() != null){
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    protected void setListener(){

    }

    protected void setObserver(){

    }

    protected VM getViewModel(){
        return viewModel;
    }

    protected DataBinding getBinding(){
        return binding;
    }

    protected void gotoIntent(Class classIntent, Bundle bundle, boolean isFinish){
        Intent intent = new Intent(this, classIntent);
        if(bundle != null){
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, REQUEST_CODE);
        if(isFinish){
            finish();
        }
    }

    protected AlertDialog.Builder createDialogConfirmation(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        return builder;
    }

    protected void showLoading(){
        root.addView(loader);
    }

    protected void removeLoading(){
        root.removeView(loader);
    }
}