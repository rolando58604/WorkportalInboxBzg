package com.rolandoamarillo.workportalinboxbizagi.injection.component;

import com.rolandoamarillo.workportalinboxbizagi.injection.module.FragmentModule;
import com.rolandoamarillo.workportalinboxbizagi.injection.scope.PerFragment;

import dagger.Component;

@PerFragment
@Component(dependencies = ActivityComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
}
