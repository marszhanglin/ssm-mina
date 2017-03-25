package mina.marszhang.minatcp02.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.util.Log;

import com.mina.connectmanage.ConnectivityReceiver;


public class MinaService extends Service {

    private ConnectivityReceiver mConnectivityReceiver;
    @Override
    public void onCreate() {
        Log.d("$$$$$$","MinaService--------onCreate");
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("$$$$$$","MinaService--------onBind");
        throw null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("$$$$$$","MinaService--------onStartCommand");

        registerConnectListener();
        return super.onStartCommand(intent, flags, startId);
    }


    /**
     * 注册网络监听
     */
    private void registerConnectListener(){
        Log.d("$$$$$$", "注册广播："+ ConnectivityManager.CONNECTIVITY_ACTION);
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        if (mConnectivityReceiver == null) {
            mConnectivityReceiver = new ConnectivityReceiver();
        }
        registerReceiver(mConnectivityReceiver,filter);

    }

    /**
     * 解绑网络监听
     */
    private void unreigisterConnectListener(){
        Log.d("$$$$$$", "解绑广播："+ ConnectivityManager.CONNECTIVITY_ACTION);
        if(null!=mConnectivityReceiver){
            unregisterReceiver(mConnectivityReceiver);
        }
    }




    @Override
    public void onDestroy() {
        unreigisterConnectListener();
        super.onDestroy();
    }
}
