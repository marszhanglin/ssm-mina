package mina.marszhang.minatcp02.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mina.MinaController;
import com.mina.SmsEventMessage;
import com.mina.codec.sms.HeartMessage;
import com.mina.codec.sms.SmsObject;
import com.mina.connectmanage.ConnectConfig;
import com.mina.connectmanage.ConnectType;
import com.mina.connectmanage.MessageType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import mina.marszhang.minatcp02.R;
import mina.marszhang.minatcp02.common.Const;
import mina.marszhang.minatcp02.model.Event;
import mina.marszhang.minatcp02.model.ToastMessage;
import mina.marszhang.minatcp02.service.SmsMessageMonitorService;
import mina.marszhang.minatcp02.util.AndroidUtils;
import mina.marszhang.minatcp02.util.CmdUtils;

import static mina.marszhang.minatcp02.util.AndroidUtils.isServiceRunning;

/**
 * <B style="color:#00f"> </B>
 * <br>
 *
 * @author zhanglin  2017/3/25
 */
public class MinaConnectTestActivity extends Activity {

    StringBuilder msgSb=new StringBuilder();
    StringBuilder sysMsgSb=new StringBuilder();
    private TextView tvMsg;
    private TextView tvSysMsg;
    private EditText edMsg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_minat);
        tvMsg = (TextView) findViewById(R.id.msg);
        tvSysMsg = (TextView) findViewById(R.id.sys_msg);
        edMsg=(EditText) findViewById(R.id.ed_msg);
        EventBus.getDefault().register(this);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(SmsEventMessage smsEventMessage){
        if(null==smsEventMessage) return;
        sysMsgSb.append(smsEventMessage.getMsg());
        tvSysMsg.setText(sysMsgSb.toString());
        if(null==smsEventMessage.getSmsObject())return;
        msgSb.append(smsEventMessage.getSmsObject().toString());
        tvMsg.setText(msgSb.toString());
        String body = smsEventMessage.getSmsObject().getBody();
//        if(MessageType.validateType(MessageType.TOAST,body)){  //指令
//            ToastMessage toastMessage=  MessageType.messageConvert(ToastMessage.class,body);
//            if(null!=toastMessage){
//                Toast.makeText(this,"推送消息："+toastMessage.getToastBodyString(),Toast.LENGTH_SHORT).show();
//            }
//        }

    }



    public void connect(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //
                MinaController minaController = MinaController.getINSTANCE();
                minaController.setConfig(new ConnectConfig(Const.MINA_IP,Const.MINA_PORT));
                minaController.connect();
            }
        }).start();
    }


    public void disconnect(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                MinaController minaController = MinaController.getINSTANCE();
                minaController.sendMessage(new SmsObject(ConnectType.DISCONNECT,"12345","1","1","1"));
                minaController.disconnect();
            }
        }).start();
    }

    public void heart(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                MinaController minaController = MinaController.getINSTANCE();
                minaController.sendMessage(new SmsObject(ConnectType.HEART_BEATER,"12345","1","1","1"));
                minaController.sendMessage(new HeartMessage());
            }
        }).start();
    }


    public void sendmessage(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                MinaController minaController = MinaController.getINSTANCE();
                minaController.sendMessage(new SmsObject(ConnectType.DATA,"12345","1","1",edMsg.getText().toString()));
            }
        }).start();
    }

    public void setting(View view){
        //MinaController.getINSTANCE().changeAddr(edMsg.getText().toString(),10000);
    }

    public void monitor(View view){
        //跳转webView 放在fragment中吧
       boolean  isMonitorStart= AndroidUtils.isServiceRunning(this, "mina.marszhang.minatcp02.service.SmsMessageMonitorService");
       Toast.makeText(this,"监听是否启动"+isMonitorStart,Toast.LENGTH_SHORT).show();

        if(!isMonitorStart){
            Intent msgMS = new Intent(this, SmsMessageMonitorService.class);
            this.startService(msgMS);
        }
    }






}
