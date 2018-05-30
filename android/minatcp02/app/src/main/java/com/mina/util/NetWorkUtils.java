package com.mina.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import static com.mina.connectmanage.ConnectivityReceiver.isNetworkAvailable;

/**
 * Created by Administrator on 2018/2/3.
 */

public class NetWorkUtils {
    public static boolean isNetWorkAvailable(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null) {
            Log.d("$$$$$$", "Network Type  = " + networkInfo.getTypeName());
            Log.d("$$$$$$", "Network State = " + networkInfo.getState());
            if (networkInfo.isConnected()) {
                Log.i("$$$$$$", "Network connected");
                return true;
            }else{
            }
        } else {
            Log.e("$$$$$$", "Network unavailable");
        }
        return false;
    }

}
