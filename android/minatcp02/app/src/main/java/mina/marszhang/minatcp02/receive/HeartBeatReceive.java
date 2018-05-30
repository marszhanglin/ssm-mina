package mina.marszhang.minatcp02.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.mina.MinaController;
import com.mina.codec.sms.HeartMessage;
import com.mina.connectmanage.ConnectConfig;
import com.mina.util.NetWorkUtils;

import mina.marszhang.minatcp02.common.Const;

/**
 * <B style="color:#00f"> 收到广播，触发心跳</B>
 * <br>
 *
 * @author zhanglin  2017/4/30
 */
public class HeartBeatReceive extends BroadcastReceiver {
    /** 网络不行次数*/
    private static int waiting;
    /** 已经多少次连不上*/
    private static int unavailableTimes;
    @Override
    public void onReceive(Context context, Intent intent) {

        if (!NetWorkUtils.isNetWorkAvailable(context)){
            Log.d("$$$$$$","网络未连接,无法发送心跳");
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                MinaController minaController = MinaController.getINSTANCE();
                minaController.setConfig(new ConnectConfig(Const.MINA_IP,Const.MINA_PORT));
                minaController.sendMessage(new HeartMessage());
            }
        }).start();

    }

}
