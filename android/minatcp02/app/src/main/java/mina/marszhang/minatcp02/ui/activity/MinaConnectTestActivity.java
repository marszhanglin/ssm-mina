package mina.marszhang.minatcp02.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mina.MinaController;
import com.mina.codec.sms.SmsObject;

import mina.marszhang.minatcp02.R;

/**
 * <B style="color:#00f"> </B>
 * <br>
 *
 * @author zhanglin  ${date}
 */
public class MinaConnectTestActivity extends Activity {

    private TextView tvMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_minat);
        tvMsg = (TextView) findViewById(R.id.msg);
    }

    public void connect(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {

                MinaController.getINSTANCE(new MinaController.MinaMessageInterface(){
                    @Override
                    public void messageReceived(final Object object) {
                        tvMsg.post(new Runnable() {
                            @Override
                            public void run() {
                                tvMsg.append(((SmsObject)object).getBody());
                            }
                        });
                    }
                }).connect();
            }
        }).start();
    }
}
