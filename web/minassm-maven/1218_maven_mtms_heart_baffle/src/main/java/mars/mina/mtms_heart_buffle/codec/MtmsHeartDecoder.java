package mars.mina.mtms_heart_buffle.codec;

import java.nio.charset.Charset;

import mars.util.BytesUtils;
import mars.util.HexUtils;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * <B style="color:#00f"> 短信息</B>
 * <br>1、将输入流数据写入缓存ioBuffer中
 *
 * @author zhanglin  2016-2-28
 */
public class MtmsHeartDecoder extends CumulativeProtocolDecoder {

    private final Charset mCharset;

    public MtmsHeartDecoder(Charset charset) {
        mCharset = charset;
    }


    @Override
    protected boolean doDecode(IoSession session, IoBuffer in,
                               ProtocolDecoderOutput out) throws Exception {
        byte[] lengthbytes =  new byte[2]; 
        if (in.remaining() < 2){ 
        	out.write(new MtmsHeartMessageObject("01"));
            return false;  
        } 
        //System.out.println("ck[position"+in.position()+"limit"+in.limit()+"remaining"+in.remaining()+"capacity"+in.capacity());
        in.get(lengthbytes);
        //System.out.println(BytesUtils.bytesToHex(lengthbytes));
        //System.out.println(Integer.parseInt(BytesUtils.bytesToHex(lengthbytes), 16));
        int length = Integer.parseInt(BytesUtils.bytesToHex(lengthbytes), 16);
        byte[] bodybytes =  new byte[length]; 
        //System.out.println("ck[position"+in.position()+"limit"+in.limit()+"remaining"+in.remaining()+"capacity"+in.capacity());
        if(in.remaining()<length){
        	out.write(new MtmsHeartMessageObject("01"));
            return false;
        }
        in.get(bodybytes);
        String bodyStr = BytesUtils.bytesToHex(bodybytes);
        String timestamp = bodyStr.substring(0, 14);
        String termStatus = bodyStr.substring(14, bodyStr.indexOf("DF"));
        System.out.println(HexUtils.hexStr2BinStr(termStatus));
        String tlvDate = bodyStr.substring(bodyStr.indexOf("DF"), bodyStr.length()-48);
        String sign = bodyStr.substring(bodyStr.length()-48, bodyStr.length());
        out.write(new MtmsHeartMessageObject("00", timestamp, termStatus, tlvDate, sign));
        return false;//false:表示本次读取完毕，不在调用doDecoder  true:
    }

}
