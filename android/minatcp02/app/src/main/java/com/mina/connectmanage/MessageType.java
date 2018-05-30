package com.mina.connectmanage;


import com.google.gson.Gson;

import mina.marszhang.minatcp02.model.Event;
import mina.marszhang.minatcp02.model.ToastMessage;


/**
 *
 * <B style="color:#00f"> 消息类型</B>
 * <br>
 * @author zhanglin  2018-5-14
 */
public class MessageType {

	public static String CMD = "00";
	public static String TOAST = "01";

	/**
	 * 交易消息是否该类型
	 * @param type
	 * @param message
	 * @return
	 */
	public static boolean validateType(String type,String message){
		if(null!=message&&message.length()>=2&&message.substring(0, 2).equals(type)){
			return true;
		}
		return false;
	}



	public static <T> T messageConvert(Class c,String message){
		if(c.isAssignableFrom(Event.class)&&validateType(CMD, message)
				||c.isAssignableFrom(ToastMessage.class)&&validateType(TOAST, message)){
			Gson gson =new Gson();
			T t=(T) gson.fromJson(message.substring(2), c);
			return t;
		}
		return null;
	}


}
