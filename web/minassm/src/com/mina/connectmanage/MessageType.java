package com.mina.connectmanage;


import com.google.gson.Gson;
import com.mvc.model.Event;


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
    private static boolean validateType(String type,String message){
    	 if(null!=message&&message.length()>=2&&message.substring(0, 2).equals(type)){
    		 return true;
    	 }
    	 return false;
     } 
	
	public static String packMessAge(String type,Object object){
		if(null==object){
			return null;
		}
		Gson gson =new Gson();
		return type+gson.toJson(object);
	}
    
    
}
