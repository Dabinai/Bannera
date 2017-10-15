package app;

import android.app.Application;

/**
 * Created by Dabin on 2017/10/13.
 */

public class MyApp extends Application{
    public static MyApp mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

    }
    public static MyApp getInstance() {
        return mInstance;
    }
}
