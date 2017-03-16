package com.mina.codec.sms;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * 
 * <B style="color:#00f"> 短消息编解码工厂</B>
 * <br>
 * @author zhanglin  2017-2-28
 */
public class SmsCodecFactory implements ProtocolCodecFactory {

	private SmsDecoder decoder;
	private SmsEncoder encoder;
	
	public SmsCodecFactory() {
		this(Charset.forName("UTF-8"));
	}

	public SmsCodecFactory(Charset charset) {
		this.decoder = new SmsDecoder(charset);
		this.encoder = new SmsEncoder(charset);
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return encoder;
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return decoder;
	}

}
