/*
 * Copyright (C) 2010 Moduad Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mina.connectmanage;

import android.util.Log;

import com.mina.MinaController;
import com.mina.codec.sms.HeartMessage;

/**
 *
 * <B style="color:#00f"> 重连线程</B>
 * <br>1、因为其它非主观的断连就要重连，
 * <br>2、电梯、隧道、长时间断网等各种情况的等待机制
 * <br>3、
 * @author zhanglin  2017年3月3日
 */
public class ReconnectionThread extends Thread {


    private final MinaController mMinaController;

    private int waiting;

    ReconnectionThread(MinaController minaController) {
        this.mMinaController = minaController;
        this.waiting = 0;
    }

    public void run() {
        try {
            while (!isInterrupted()) {
                Log.d("$$$$$$", "$$$$$$Trying to reconnect in " + waiting()
                        + " seconds");
                Thread.sleep((long) waiting() * 1000L);
                mMinaController.sendMessage(new HeartMessage());
                waiting++;
            }
        } catch (final InterruptedException e) {
            Log.e("$$$$$$", "$$$$$$ reconnect error [wait:" + waiting()
                    + ",errorMsg :"+e.getMessage());
          /*  mMinaController.getHandler().post(new Runnable() {
                public void run() {
                    mMinaController.getConnectionListener().reconnectionFailed(e);
                }
            });*/
        }
    }

    /**
     * @return
     */
    private int waiting() {
        if(waiting > 30){//110分钟过去
            return 30*60;
        }
        if (waiting > 20) {//40分钟过去
            return 10*60;
        }
        if (waiting > 13) {//7分钟过去
            return 5*60;
        }
        return waiting <= 7 ? 10 : 60;
    }
}
