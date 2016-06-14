package com.rolandoamarillo.workportalinboxbizagi;

import android.app.Application;
import android.content.Context;

import com.rolandoamarillo.workportalinboxbizagi.injection.component.ApplicationComponent;
import com.rolandoamarillo.workportalinboxbizagi.injection.component.DaggerApplicationComponent;
import com.rolandoamarillo.workportalinboxbizagi.injection.module.ApplicationModule;

public class WorkportalInboxApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    public static WorkportalInboxApplication get(Context context) {
        return (WorkportalInboxApplication) context.getApplicationContext();
    }

    /**
     * To get ApplicationComponent
     *
     * @return ApplicationComponent
     */
    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    /**
     * Need to replace the component with a test specific one
     *
     * @param applicationComponent
     */
    public void setComponent(ApplicationComponent applicationComponent) {
        this.mApplicationComponent = applicationComponent;
    }

}
