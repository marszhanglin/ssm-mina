package mina.marszhang.minatcp02.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.mina.MinaController;
import com.mina.codec.sms.HeartMessage;

/**
 * <B style="color:#00f"> 收到广播，触发心跳</B>
 * <br>
 *
 * @author zhanglin  2017/4/30
 */
public class HeartBeatReceive extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MinaController minaController = MinaController.getINSTANCE();

                minaController.sendMessage(new HeartMessage());
            }
        }).start();

    }
}
