package com.rolandoamarillo.workportalinboxbizagi.activities.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.rolandoamarillo.workportalinboxbizagi.WorkportalInboxApplication;
import com.rolandoamarillo.workportalinboxbizagi.injection.component.ActivityComponent;
import com.rolandoamarillo.workportalinboxbizagi.injection.component.DaggerActivityComponent;
import com.rolandoamarillo.workportalinboxbizagi.injection.module.ActivityModule;
import com.rolandoamarillo.workportalinboxbizagi.managers.TasksManager;
import com.rolandoamarillo.workportalinboxbizagi.remote.ApiInterface;
import com.squareup.otto.Bus;

/**
 * Created by rolando.amarillo on 13/06/2016.
 */
public class BaseActivity extends AppCompatActivity {

    protected TasksManager tasksManager;
    protected Bus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tasksManager = WorkportalInboxApplication.get(this).getComponent().getTasksManager();
        bus = WorkportalInboxApplication.get(this).getComponent().getBus();
    }

    private ActivityComponent mActivityComponent;

    public ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder().activityModule(new ActivityModule(this))
                    .applicationComponent(WorkportalInboxApplication.get(this).getComponent()).build();
        }
        return mActivityComponent;
    }
}
