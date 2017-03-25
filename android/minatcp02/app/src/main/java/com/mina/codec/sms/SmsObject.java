package com.mina.codec.sms;

import com.mina.connectmanage.ConnectType;

/**
 * 
 * <B style="color:#00f"> 短信协调对象</B>
 * <br>
 * @author zhanglin  2017-2-26
 */
public class SmsObject {
	/** 请求类型 比如 心跳 数据  这里有个问题 这个字段是不希望暴露出去的  */
	public String type;
	/** 接收者id（对比QQ号） 这个只用来提供给接收端来显示或记录谁收的短信  与sessionid的关系及作用要理清楚  非常重要*/
	private String receiver;
	/** 发送者id（对比QQ号） 这个只用来提供给接收端来显示或记录谁收的短信  与sessionid的关系及作用要理清楚  非常重要*/
	private String sender;
	/** 校验值  用不用不影响编解码 验证规则由调用者决定  框架本身不管*/
	private String validate;
	/** 内容 */
	private String body;
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public SmsObject(String type, String receiver, String sender,  String validate,String body) {
		this.body = body;
		this.receiver = receiver;
		this.sender = sender;
		this.type = type;
		this.validate = validate;
	}
	public SmsObject(String body) {
		this.body = body;
		this.receiver = "service";
		this.sender = "client";
		this.type = ConnectType.DATA;
		this.validate = "no";
	}

}
