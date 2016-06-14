package com.rolandoamarillo.workportalinboxbizagi.injection.module;

import android.app.Activity;
import android.content.Context;

import com.rolandoamarillo.workportalinboxbizagi.injection.scope.ActivityContext;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }
}
