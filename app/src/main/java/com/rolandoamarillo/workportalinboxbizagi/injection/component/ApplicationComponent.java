package com.rolandoamarillo.workportalinboxbizagi.injection.component;

import android.app.Application;
import android.content.Context;

import com.rolandoamarillo.workportalinboxbizagi.injection.module.ApplicationModule;
import com.rolandoamarillo.workportalinboxbizagi.injection.scope.ApplicationContext;
import com.rolandoamarillo.workportalinboxbizagi.managers.TasksManager;
import com.rolandoamarillo.workportalinboxbizagi.remote.ApiInterface;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    Application application();

    @ApplicationContext
    Context context();

    @Singleton
    Bus getBus();

    @Singleton
    Retrofit getRetrofit();

    @Singleton
    ApiInterface getApiInterface();

    @Singleton
    TasksManager getTasksManager();
}
