package mina.marszhang.minatcp02.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Toast;

import com.mina.SmsEventMessage;
import com.mina.connectmanage.MessageType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Locale;

import mina.marszhang.minatcp02.model.Event;
import mina.marszhang.minatcp02.model.ToastMessage;
import mina.marszhang.minatcp02.util.CmdUtils;

/**
 * mima服务类
 */
public class SmsMessageMonitorService extends Service {

    private TextToSpeech mTextToSpeech;
    @Override
    public void onCreate() {
        Log.d("$$$$$$","SmsMessageMonitorService--------onCreate");
        Log.d("$$$$$$","消息监听启动");
        super.onCreate();
        EventBus.getDefault().register(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("$$$$$$","SmsMessageMonitorService--------onBind");
        throw null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("$$$$$$","SmsMessageMonitorService--------onStartCommand");
        if(null!=mTextToSpeech){
            return super.onStartCommand(intent, flags, startId);
        }




        initTTS();
        //initMina();
        return super.onStartCommand(intent, flags, startId);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(SmsEventMessage smsEventMessage){
        if(null==smsEventMessage) return;
        if(null==smsEventMessage.getSmsObject())return;
        String body = smsEventMessage.getSmsObject().getBody();
        if(MessageType.validateType(MessageType.CMD,body)){  //指令
            Event event=  MessageType.messageConvert(Event.class,body);
            Log.d("$$$$$$",event.toString());
            String action = event.getAction();
            String pkName = event.getPkgName();
            if(action.equals("reboot")){
                CmdUtils.reboot(SmsMessageMonitorService.this);
            }else if(action.equals("uninstall")){
                CmdUtils.uninstallApk(SmsMessageMonitorService.this,pkName);
            }
        }else if(MessageType.validateType(MessageType.TOAST,body)){  //指令
            final ToastMessage toastMessage=  MessageType.messageConvert(ToastMessage.class,body);
            if(null!=toastMessage){
                Toast.makeText(getApplicationContext(),"推送消息："+toastMessage.getToastBodyString(),Toast.LENGTH_SHORT).show();

            }
        }
    }



    private void initMina() {
        /*mMinaController.setConfig(new ConnectConfig(new MinaMessageInterface() {
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
        }));*/
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




    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
