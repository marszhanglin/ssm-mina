package mina.marszhang.minatcp02;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import mina.marszhang.minatcp02.exception.CrashHandler;
import mina.marszhang.minatcp02.service.MinaService;

/**
 * Created by marszhang on 2017/2/25.
 */

public class App extends Application {

    public static boolean WAKEUP =false;

    private static Context instance;
    @Override
    public void onCreate() {
        super.onCreate();

        Intent i1=new Intent(this,MinaService.class);
        startService(i1);

        CrashHandler.getInstance().init(this);
        instance = this;
    }

    public static Context getContext(){
        return instance;
    }
}
