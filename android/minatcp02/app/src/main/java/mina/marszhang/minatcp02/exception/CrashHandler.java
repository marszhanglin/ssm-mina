package mina.marszhang.minatcp02.exception;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.lang.Thread.UncaughtExceptionHandler;

import mina.marszhang.minatcp02.service.BootStartService;

public class CrashHandler implements UncaughtExceptionHandler {
	
	private static CrashHandler INSTANCE;
	private Context context;
	
	public static CrashHandler getInstance() {
        if (INSTANCE == null)
            INSTANCE = new CrashHandler();
        return INSTANCE;
    }
	
    public void init(Context context) {
    	this.context = context;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会回调该函数来处理
     */
	@Override
	public void uncaughtException(Thread arg0, Throwable arg1) {
		arg1.printStackTrace();// 输出到logCat
		
		// 获取异常堆栈信息
		String stackInfo = getStackTraceInfo(arg1); 
		
		// 重启应用
		Log.e("$$$$$$", "$$$$$$捕获到异常，自重启....堆栈信息："+stackInfo);
		Log.e("$$$$$$", "$$$$$$服务重新启动");
		Intent sev = new Intent(context, BootStartService.class);
		context.startService(sev);
	}
	
	/**
	 * 获取异常堆栈信息
	 * @param throwable
	 * @return
	 */
	private String getStackTraceInfo(Throwable throwable){
		return Log.getStackTraceString(throwable);
	}
}
