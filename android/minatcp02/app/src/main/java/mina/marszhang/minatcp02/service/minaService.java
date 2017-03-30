package mina.marszhang.minatcp02.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import com.mina.MinaController;
import com.mina.codec.sms.SmsObject;
import com.mina.connectmanage.ConnectConfig;
import com.mina.connectmanage.ConnectivityReceiver;
import com.mina.connectmanage.MinaMessageInterface;

import java.util.Locale;


public class MinaService extends Service {

    private ConnectivityReceiver mConnectivityReceiver;
    private MinaController mMinaController;
    private TextToSpeech mTextToSpeech;
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
        if(null!=mTextToSpeech){
            return super.onStartCommand(intent, flags, startId);
        }
        initTTS();
        initMina();
        registerConnectListener();
        return super.onStartCommand(intent, flags, startId);
    }

    private void initMina() {
        mMinaController =  MinaController.getINSTANCE();
        mMinaController.setConfig(new ConnectConfig(new MinaMessageInterface() {
            @Override
            public void messageReceived(Object object) {
                //语言播报
                String input= ((SmsObject)object).getBody();
                Log.d("$$$$$$","语音播报内容："+input);
                //mTextToSpeech.speak(input, TextToSpeech.QUEUE_ADD, null);

            }

            @Override
            public void systemMsg(String object) {

            }
        }));
        new Thread(new Runnable() {
            @Override
            public void run() {
                mMinaController.connect();
            }
        }).start();
    }


    private void initTTS(){
        mTextToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                mTextToSpeech.setLanguage(Locale.US);//英文
//                mTextToSpeech.setLanguage(Locale.CHINA);

//                if (status == TextToSpeech.SUCCESS) {
//                    int result = mTextToSpeech.setLanguage(Locale.CHINA);
//                    if (result == TextToSpeech.LANG_MISSING_DATA
//                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
//                        Log.d("$$$$$$","语音播报：数据丢失或不支持");
//                    }
//                }
            }
        });
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
