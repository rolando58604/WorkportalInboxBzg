package com.rolandoamarillo.workportalinboxbizagi.injection.module;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rolandoamarillo.workportalinboxbizagi.BuildConfig;
import com.rolandoamarillo.workportalinboxbizagi.injection.scope.ApplicationContext;
import com.rolandoamarillo.workportalinboxbizagi.managers.TasksManager;
import com.rolandoamarillo.workportalinboxbizagi.remote.ApiInterface;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    protected final Application mApplication;

    public ApplicationModule(Application application) {
        this.mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    Bus provideBus() {
        return new Bus();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @Singleton
    ApiInterface provideApiInterface(Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }

    @Provides
    @Singleton
    TasksManager provideTasksManager(Bus bus, ApiInterface apiInterface) {
        return new TasksManager(bus, apiInterface);
    }
}
