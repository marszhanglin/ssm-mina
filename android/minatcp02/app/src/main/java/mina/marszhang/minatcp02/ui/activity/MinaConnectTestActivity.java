package mina.marszhang.minatcp02.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mina.MinaController;
import com.mina.codec.sms.HeartMessage;
import com.mina.codec.sms.SmsObject;
import com.mina.connectmanage.ConnectConfig;
import com.mina.connectmanage.MinaMessageInterface;

import mina.marszhang.minatcp02.R;

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
    }

    public void connect(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //
                MinaController minaController = MinaController.getINSTANCE();

                minaController.setConfig(new ConnectConfig(new MinaMessageInterface() {
                    @Override
                    public void messageReceived(final Object object) {
                        tvMsg.post(new Runnable() {
                            @Override
                            public void run() {
                                msgSb.append(((SmsObject) object).getBody()+"\n");
                                tvMsg.setText(msgSb.toString());
                            }
                        });
                    }

                    @Override
                    public void systemMsg(final String object) {
                        tvSysMsg.post(new Runnable() {
                            @Override
                            public void run() {
                                sysMsgSb.append(object+"\n");
                                tvSysMsg.setText(sysMsgSb.toString());
                            }
                        });
                    }
                }));

                minaController.connect();
            }
        }).start();
    }


    public void disconnect(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                MinaController minaController = MinaController.getINSTANCE();

                minaController.disconnect();
            }
        }).start();
    }

    public void heart(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                MinaController minaController = MinaController.getINSTANCE();

                minaController.sendMessage(new HeartMessage());
            }
        }).start();
    }


    public void sendmessage(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                MinaController minaController = MinaController.getINSTANCE();

                minaController.sendMessage(new SmsObject(edMsg.getText().toString()));
            }
        }).start();
    }

    public void setting(View view){
        //MinaController.getINSTANCE().changeAddr(edMsg.getText().toString(),10000);
    }

    public void web(){
        //跳转webView 放在fragment中吧

    }


}
