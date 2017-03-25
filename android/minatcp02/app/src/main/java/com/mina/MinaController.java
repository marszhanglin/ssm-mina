package com.mina;

import android.util.Log;

import com.mina.codec.sms.SmsCodecFactory;
import com.mina.codec.sms.SmsObject;
import com.mina.connectmanage.ConnectConfig;
import com.mina.connectmanage.ConnectType;
import com.mina.connectmanage.MinaMessageInterface;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import mina.marszhang.minatcp02.common.Const;

/**
 * <B style="color:#00f"> mina控制类</B>
 * <br>1.对于客户端来说 开机后就与服务端生产连接
 * <br>2.监听不需要所有端ioHandler 由控制类处理部分监听
 * <br>3.对于客户端只要一个连接就够
 * <br>4.心跳也由控制层处理好  不用调用者操心
 * <br>5.目前就做到心跳与接收推送   后期添加点对点及 点对多的发送消息
 *
 * <br>原则：就是mina源码相关的东西不能最多只能到这一层
 * @author zhanglin 
 */
public class MinaController {

    private MinaMessageInterface mMinaMessageInterface;
    private InetSocketAddress mInetSocketAddress;
    private static MinaController INSTANCE =null;
    private MinaController(){
        init();
    }


    private void init(){
        if (nioSocketConnector == null) {
            nioSocketConnector = new NioSocketConnector();
        }
        nioSocketConnector.setConnectTimeoutMillis(Const.MINA_TIMEOUT*1000);
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
            	Log.d("$$$$$$", "$$$$$$3");
            }

            @Override
            public void inputClosed(IoSession session) throws Exception {

            }
        });
        nioSocketConnector.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new SmsCodecFactory(Charset.forName("UTF-8"))));//往最后面加一个过滤器
        Log.d("$$$$$$", "$$$$$$mina初始化完毕");
    }
    public static MinaController getINSTANCE(){
        if (INSTANCE == null) {
            INSTANCE = new MinaController();
        }
        return INSTANCE;
    }

    public void setConfig(ConnectConfig connectConfig)  {
        if (connectConfig == null) {
            throw new NullPointerException("connectConfig  is  null");
        }
        if (connectConfig.getMinaMessageInterface() == null) {
            throw new NullPointerException("MinaMessageInterface is null");
        }
        if (connectConfig.getIp() == null) {
            throw new NullPointerException("host ip is  null");
        }
        if (connectConfig == null) {
            throw new NullPointerException("connectConfig  is  null");
        }
        mInetSocketAddress = new InetSocketAddress(connectConfig.getIp(),connectConfig.getPort());
        mMinaMessageInterface = connectConfig.getMinaMessageInterface();
    }

    private NioSocketConnector nioSocketConnector =new NioSocketConnector();
    //注意ioSeeion的生命周期
    private IoSession ioSession ;
    /**mina连接状态*/
    private ConnectFuture connectFuture;
    public long connect(){
    	if(null==connectFuture||!connectFuture.isConnected()){
    		connectFuture=nioSocketConnector.connect(mInetSocketAddress);
            connectFuture.awaitUninterruptibly();//awaitUninterruptibly 是阻塞方法，等待write写完
            ioSession = connectFuture.getSession(); 
            ioSession.write(new SmsObject(ConnectType.DATA,"270504808", "780965203","no","connect"));
            Log.d("$$$$$$", "$$$$$$连接服务端：当前Id"+ioSession.getId());
    	}else{
    		Log.d("$$$$$$", "$$$$$$服务端已连接：当前Id"+ioSession.getId());
    	}
        // TODO 连接之后的心跳处理
        return ioSession.getId();
    }

    public boolean disconnect(){
        if(null==connectFuture||!connectFuture.isCanceled()){
            Log.d("$$$$$$", "$$$$$$已经与服务端断开连接");
            return true;
        }else{
            Log.d("$$$$$$", "$$$$$$未与服务端断开连接，正在断连");
            ioSession.closeOnFlush();
        }
        return true;
    }

    /**
     *
     *
     * @return
     */
    public boolean sendMessage(SmsObject smsObject){
        connect();
        connectFuture.awaitUninterruptibly();
        ioSession.write(smsObject);
        return true;
    }
}
