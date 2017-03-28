package mina.marszhang.minatcp02.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * 服务启动入口
 */
public class BootStartService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("$$$$$$","$$$$$$BootStartService--------onBind");
        return null;
    }

    @Override
    public void onCreate() {
        Log.d("$$$$$$","$$$$$$BootStartService--------onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("$$$$$$","$$$$$$BootStartService--------onStartCommand");
        Intent i1=new Intent(this,MinaService.class);
        startService(i1);



        return super.onStartCommand(intent, flags, startId);
    }
}
