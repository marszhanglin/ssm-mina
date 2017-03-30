package mina.marszhang.minatcp02.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <B style="color:#00f"> </B>
 * <br>
 *
 * @author zhanglin  2017/3/30
 */
public class RegexUtil {


    public static boolean isIp(String ipValue){
        if (null==ipValue||ipValue.length()<5||ipValue.length()>15){
            return false;
        }

        String rexp= "([1-9]|[1-9]\\\\d|1\\\\d{2}|2[0-4]\\\\d|25[0-5])(\\\\.(\\\\d|[1-9]\\\\d|1\\\\d{2}|2[0-4]\\\\d|25[0-5])){3}";

        Pattern pattern = Pattern.compile(rexp);

        Matcher mat = pattern.matcher(ipValue);

        return mat.find();
    }

}
