package com.rolandoamarillo.workportalinboxbizagi.injection.component;

import com.rolandoamarillo.workportalinboxbizagi.injection.module.ActivityModule;
import com.rolandoamarillo.workportalinboxbizagi.injection.scope.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
}
