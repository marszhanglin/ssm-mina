package mina.marszhang.minatcp02.controller.mina;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * 
 * <B style="color:#00f"> 短信息</B>
 * <br>1、将输入流数据写入缓存ioBuffer中
 * 
 * @author zhanglin  2016-2-28
 */
public class SmsDecoder extends CumulativeProtocolDecoder {

	private final Charset mCharset;
	
	public SmsDecoder(Charset charset){
		mCharset = charset;
	}
	
	
	@Override
	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		IoBuffer ioBuffer =IoBuffer.allocate(100).setAutoExpand(true);
		CharsetDecoder cd=mCharset.newDecoder();
		int matchCount=0;
		String top ="";
		String sender = "";
		String receive = "";
		String length = "";
		String body = "";
		int i = 0;//表示行数
		while (in.hasRemaining()) {//把输入流一个一个字节的往缓存中传  当碰到"\n"时输出
			byte b=in.get();//从输入流中获取当前位置的字节
			ioBuffer.put(b);//写入缓存
			matchCount++;//记录读取缓存的位置
			if(i<5&&b==10){//10为\n
				switch (i) {
				case 1://第1行
					ioBuffer.flip();//校验0位置或者说是调整0的位置
					top = ioBuffer.getString(matchCount, cd);//从缓存中读取改行
					top = top.substring(0, top.length()-1);//去掉\n
					matchCount = 0;
					ioBuffer.clear();//清缓存 为了读下一行
					break;
				case 2://第2行
					ioBuffer.flip();//校验0位置或者说是调整0的位置
					sender = ioBuffer.getString(matchCount, cd);//从缓存中读取改行
					sender = sender.substring(0, sender.length()-1);//去掉\n
					matchCount = 0;
					ioBuffer.clear();//清缓存 为了读下一行
					break;
				case 3://第3行
					ioBuffer.flip();//校验0位置或者说是调整0的位置
					receive = ioBuffer.getString(matchCount, cd);//从缓存中读取改行
					receive = receive.substring(0, receive.length()-1);//去掉\n
					matchCount = 0;
					ioBuffer.clear();//清缓存 为了读下一行
					break;
				case 4://第4行
					ioBuffer.flip();//校验0位置或者说是调整0的位置
					length = ioBuffer.getString(matchCount, cd);//从缓存中读取改行
					length = length.substring(0, length.length()-1);//去掉\n
					matchCount = 0;
					ioBuffer.clear();//清缓存 为了读下一行
					break; 
				default:
					break;
				}
				i++;
			}else if(i==5){//第5行
				if(matchCount==Integer.parseInt(length.split(":")[1])){//当长度为第四行获取的长度时读取
					ioBuffer.flip();
					body=ioBuffer.getString(matchCount, cd);
					matchCount=0;
					i++;
					//退出来后面就算有数据也留给下一次读
					break;
				}
			}
		}
		return false;//false:表示本次读取完毕，不在调用doDecoder  true:  
	}
	//T:aaaa\n
	//S:18950478288\n
	//R:18950478888\n
	//L:10\n
	//1234567890

}
