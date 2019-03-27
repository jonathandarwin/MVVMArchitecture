package com.example.user.mvvmarchitecture.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.user.mvvmarchitecture.BR;

/**
 * Created by User on 3/27/2019.
 */

public class Note extends BaseObservable {
    protected int id;
    protected String title;
    protected String description;

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }
}
