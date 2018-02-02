package com.minasms.codec.sms;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import com.google.gson.Gson;

/**
 * 
 * <B style="color:#00f"> 短信编码器</B>
 * <br>将自定义对象{@link SmsObject}编为二进制流
 * <br>1、Object将对象强转成自定义对象
 * <br>2、根据字符集生成的编码工具
 * <br>3、将对象根据协议写入缓存
 * <br>4、再将缓存写入输出流
 * @author zhanglin  2016-2-26
 */
public class SmsEncoder extends ProtocolEncoderAdapter {
	
	private final Charset mCharset;
	
	public SmsEncoder(Charset charset) {
		this.mCharset = charset;
	}

	@Override
	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception {
		SmsObject smsObject=(SmsObject) message;
		CharsetEncoder ce=mCharset.newEncoder();
		IoBuffer ioBuffer = IoBuffer.allocate(100).setAutoExpand(true);
		ioBuffer.putString("T:"+smsObject.getType()+"\n", ce);
		ioBuffer.putString("S:"+smsObject.getSender()+"\n", ce);
		ioBuffer.putString("R:"+smsObject.getReceiver()+"\n", ce);
		ioBuffer.putString("V:"+smsObject.getValidate()+"\n", ce);
		ioBuffer.putString("L:"+smsObject.getBody().getBytes(mCharset).length+"\n", ce);
		ioBuffer.putString(smsObject.getBody(), ce);
		System.out.println("encode:"+new Gson().toJson(message));
		ioBuffer.flip();
		out.write(ioBuffer);
	}
	//T:01\n
	//S:18950478288\n
	//R:18950478888\n
	//V:md5value\n
	//L:10\n
	//1234567890
}
