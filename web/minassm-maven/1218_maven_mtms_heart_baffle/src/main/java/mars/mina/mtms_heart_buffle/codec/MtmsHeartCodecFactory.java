package mars.mina.mtms_heart_buffle.codec;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * 
 * <B style="color:#00f"> 心跳信息编解码工厂</B>
 * <br>
 * @author zhanglin  2017-12-19
 */
public class MtmsHeartCodecFactory implements ProtocolCodecFactory {

	private MtmsHeartDecoder decoder;
	private MtmsHeartEncoder encoder;
	
	public MtmsHeartCodecFactory() {
		this(Charset.forName("UTF-8"));
	}

	public MtmsHeartCodecFactory(Charset charset) {
		this.decoder = new MtmsHeartDecoder(charset);
		this.encoder = new MtmsHeartEncoder(charset);
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
