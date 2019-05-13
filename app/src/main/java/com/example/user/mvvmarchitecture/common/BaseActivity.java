package com.example.user.mvvmarchitecture.common;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity<ViewBinding extends ViewDataBinding, VM extends ViewModel> extends AppCompatActivity {
    private VM viewModel;
    private ViewBinding binding;

    private Class<VM> vm;
    private int layout;

    public BaseActivity(Class<VM> vm, int layout){
        this.vm = vm;
        this.layout = layout;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // SET VIEW MODEL
        this.viewModel = ViewModelProviders.of(this).get(vm);
        // SET BINDING LAYOUT
        this.binding = DataBindingUtil.setContentView(this, layout);
    }

    protected VM getViewModel(){
        return viewModel;
    }

    protected ViewBinding getBinding(){
        return binding;
    }
}