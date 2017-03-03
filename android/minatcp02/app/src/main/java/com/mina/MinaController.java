package com.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.mina.codec.sms.SmsCodecFactory;
import com.mina.codec.sms.SmsObject;

/**
 * <B style="color:#00f"> mina控制类</B>
 * <br>1.对于客户端来说 开机后就与服务端生产连接
 * <br>2.监听不需要所有端ioHandler 由控制类处理部分监听
 * <br>3.对于客户端只要一个连接就够
 * <br>4.心跳也由控制层处理好  不用调用者操心
 * <br>5.目前就做到心跳与接收推送   后期添加点对点及 点对多的发送消息
 * @author zhanglin 
 */
public class MinaController {

    private MinaMessageInterface mMinaMessageInterface;
    private static MinaController INSTANCE =null;
    private MinaController(MinaMessageInterface minaMessageInterface){
        mMinaMessageInterface = minaMessageInterface;
        init();
    }

    private void init(){
        if (nioSocketConnector == null) {
            nioSocketConnector = new NioSocketConnector();
        }
        nioSocketConnector.setConnectTimeoutMillis(5000);
        nioSocketConnector.setHandler(new IoHandler() {
            @Override
            public void sessionCreated(IoSession session) throws Exception {

            }

            @Override
            public void sessionOpened(IoSession session) throws Exception {

            }

            @Override
            public void sessionClosed(IoSession session) throws Exception {

            }

            @Override
            public void sessionIdle(IoSession session, IdleStatus status) throws Exception {

            }

            @Override
            public void exceptionCaught(IoSession session, Throwable cause) throws Exception {

            }

            @Override
            public void messageReceived(IoSession session, Object message) throws Exception {
                mMinaMessageInterface.messageReceived(message);
            }

            @Override
            public void messageSent(IoSession session, Object message) throws Exception {
            	System.out.println("3"); 
            }

            @Override
            public void inputClosed(IoSession session) throws Exception {

            }
        });
        nioSocketConnector.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new SmsCodecFactory(Charset.forName("UTF-8"))));//往最后面加一个过滤器
        System.out.println("mina初始化完毕");
    }
    public static MinaController getINSTANCE(MinaMessageInterface minaMessageInterface){
        if (INSTANCE == null) {
            INSTANCE = new MinaController(minaMessageInterface);
        }
        return INSTANCE;
    }

    private NioSocketConnector nioSocketConnector =new NioSocketConnector();
    private IoSession ioSession ;
    private ConnectFuture connectFuture;
    public boolean connect(){
    	if(null==connectFuture||!connectFuture.isConnected()){
    		connectFuture=nioSocketConnector.connect(new InetSocketAddress("192.168.1.26",10000));
            connectFuture.awaitUninterruptibly();
            ioSession = connectFuture.getSession(); 
            ioSession.write(new SmsObject("270504808", "780965203","你好，我是客户端"));
            System.out.println("连接服务端：当前Id"+ioSession.getId());
    	}else{
    		System.out.println("服务端已连接：当前Id"+ioSession.getId());
    	}
        // TODO 连接之后的心跳处理
        return true;
    }


    public interface MinaMessageInterface{
        public void messageReceived(Object object);
    }

}
