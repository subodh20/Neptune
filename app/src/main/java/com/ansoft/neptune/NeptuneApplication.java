package com.ansoft.neptune;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by subodh on 05/06/2016.
 */
public class NeptuneApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(getApplicationContext());
        Parse.initialize(this);
    }
}
