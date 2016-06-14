package com.rolandoamarillo.workportalinboxbizagi.injection.module;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.rolandoamarillo.workportalinboxbizagi.injection.scope.FragmentContext;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        this.mFragment = fragment;
    }

    @Provides
    Fragment provideFragment() {
        return mFragment;
    }

    @Provides
    @FragmentContext
    Context provideContext() {
        return mFragment.getContext();
    }

}
