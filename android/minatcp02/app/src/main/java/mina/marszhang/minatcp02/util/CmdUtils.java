package mina.marszhang.minatcp02.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.PowerManager;

/**
 * Created by Administrator on 2018/5/14.
 */
public class CmdUtils {

    public static void reboot(Context ctx){

        // 权限要求：  android.permission.MANAGE_NEWLAND
        PowerManager pManager=(PowerManager) ctx.getSystemService(Context.POWER_SERVICE);
        pManager.reboot("");
    }


    /**
     * 卸载应用
     *
     * @param pkgName
     *            应用包名
     * @return
     */
    public static void uninstallApk(Context context, String pkgName) {
        if (null!=pkgName) {
            // 新大陆 // 权限要求：  android.permission.MANAGE_NEWLAND
            Uri uri=Uri.parse("package:"+pkgName);
            Intent intentdel4 = new Intent("android.intent.action.DELETE.HIDE");
            intentdel4.setData(uri);
            intentdel4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentdel4);

            // 通用
//            Intent uninstall_intent = new Intent();
//            uninstall_intent.setAction(Intent.ACTION_DELETE);
//            uninstall_intent.setData(Uri.parse("package:"+pkgName));
//            uninstall_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(uninstall_intent);
        }
    }

}
