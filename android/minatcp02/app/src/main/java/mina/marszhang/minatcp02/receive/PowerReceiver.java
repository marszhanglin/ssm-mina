package mina.marszhang.minatcp02.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import mina.marszhang.minatcp02.App;

/**
 * 休眠唤醒广播接收器   电源键  熄屏
 */
public class PowerReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		if ("android.intent.action.SLEEP".equals(intent.getAction())) {
			//休眠
			Log.d("$$$$$$", "$$$$$$进入休眠状态");
			App.WAKEUP = false;
		} else if ("android.intent.action.WAKEUP".equals(intent.getAction())) {
			//唤醒
			Log.d("$$$$$$", "$$$$$$进入唤醒状态");
			App.WAKEUP = true;
		}
	}

}
