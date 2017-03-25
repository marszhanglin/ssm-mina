package mina.marszhang.minatcp02;

import android.app.Application;

import mina.marszhang.minatcp02.exception.CrashHandler;

/**
 * Created by marszhang on 2017/2/25.
 */

public class App extends Application {

    public static boolean WAKEUP =false;

    @Override
    public void onCreate() {
        super.onCreate();

        CrashHandler.getInstance().init(this);
    }
}
