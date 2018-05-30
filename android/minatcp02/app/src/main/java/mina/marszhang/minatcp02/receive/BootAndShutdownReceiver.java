package mina.marszhang.minatcp02.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

import mina.marszhang.minatcp02.service.BootStartService;
import mina.marszhang.minatcp02.service.SmsMessageMonitorService;

/**
 * 开关机广播
 */
public class BootAndShutdownReceiver extends BroadcastReceiver {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if(action.equals(Intent.ACTION_BOOT_COMPLETED)){

			SimpleDateFormat simpleDateFormat=new SimpleDateFormat( "yyyy年MM月dd日 HH:mm:ss");
			// 打印开机时间
			String currentDate = simpleDateFormat.format(new Date());
			Log.d("$$$$$$", "$$$$$$开机时间："+currentDate);


			Intent msgMS = new Intent(context, SmsMessageMonitorService.class);
			context.startService(msgMS);

			Intent btS = new Intent(context, BootStartService.class);
			context.startService(btS);


			
		}else if(action.equals(Intent.ACTION_SHUTDOWN)){
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat( "yyyy年MM月dd日 HH:mm:ss");
			// 打印开机时间
			String currentDate = simpleDateFormat.format(new Date());
			Log.d("$$$$$$", "$$$$$$关机时间："+currentDate);
		}
	}
	
	
}
