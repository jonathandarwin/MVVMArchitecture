package com.example.user.mvvmarchitecture.di;

import com.example.user.mvvmarchitecture.app.main.MainActivity;

import dagger.Component;

@Component(
        modules = {
            AppModule.class
        }
)
public interface AppComponent {
    void inject(MainActivity target);
}
