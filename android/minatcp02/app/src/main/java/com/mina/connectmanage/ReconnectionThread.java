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

/**
 *
 * <B style="color:#00f"> 重连线程</B>
 * <br>
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
                mMinaController.connect();
                waiting++;
            }
        } catch (final InterruptedException e) {
          /*  mMinaController.getHandler().post(new Runnable() {
                public void run() {
                    mMinaController.getConnectionListener().reconnectionFailed(e);
                }
            });*/
        }
    }

    private int waiting() {
        if (waiting > 20) {
            return 600;
        }
        if (waiting > 13) {
            return 300;
        }
        return waiting <= 7 ? 10 : 60;
    }
}
