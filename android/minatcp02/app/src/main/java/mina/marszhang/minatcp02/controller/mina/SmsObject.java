package mina.marszhang.minatcp02.controller.mina;

/**
 * 
 * <B style="color:#00f"> 短信协调对象</B>
 * <br>
 * @author zhanglin  2017-2-26
 */
public class SmsObject {
	/** 接收者id（对比QQ号） 这个只用来提供给接收端来显示或记录谁收的短信  与sessionid的关系及作用要理清楚  非常重要*/
	private String receiver;
	/** 发送者id（对比QQ号） 这个只用来提供给接收端来显示或记录谁收的短信  与sessionid的关系及作用要理清楚  非常重要*/
	private String sender;
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

	public SmsObject(String body, String receiver, String sender) {
		this.body = body;
		this.receiver = receiver;
		this.sender = sender;
	}
}
