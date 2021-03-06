package com.mina;

import android.util.Log;

import com.mina.codec.sms.SmsCodecFactory;
import com.mina.codec.sms.SmsObject;
import com.mina.connectmanage.ConnectConfig;
import com.mina.connectmanage.ConnectType;
import com.mina.util.NetWorkUtils;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.greenrobot.eventbus.EventBus;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import mina.marszhang.minatcp02.App;
import mina.marszhang.minatcp02.common.Const;
import mina.marszhang.minatcp02.util.RegexUtil;

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
        nioSocketConnector.setHandler(new IoHandlerAdapter() {
            @Override
            public void sessionCreated(IoSession session) throws Exception {
                EventBus.getDefault().post(new SmsEventMessage(null,"sessionCreated"+session.getId()));
            }

            @Override
            public void sessionOpened(IoSession session) throws Exception {
                EventBus.getDefault().post(new SmsEventMessage(null,"sessionOpened"+session.getId()));
            }

            @Override
            public void sessionClosed(IoSession session) throws Exception {
                EventBus.getDefault().post(new SmsEventMessage(null,"sessionClosed"+session.getId()));
            }

            @Override
            public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
                EventBus.getDefault().post(new SmsEventMessage(null,"sessionIdle"+session.getId()+",status:"+status.toString()));
            }

            @Override
            public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
                EventBus.getDefault().post(new SmsEventMessage(null,"exceptionCaught"+session.getId()));
            }

            @Override
            public void messageReceived(IoSession session, Object message) throws Exception {
                EventBus.getDefault().post(new SmsEventMessage((SmsObject) message,"messageReceived"+session.getId()));
            }

            @Override
            public void messageSent(IoSession session, Object message) throws Exception {
                EventBus.getDefault().post(new SmsEventMessage((SmsObject) message,"messageSent"+session.getId()));
            }

            @Override
            public void inputClosed(IoSession session) throws Exception {
                EventBus.getDefault().post(new SmsEventMessage(null,"inputClosed"+session.getId()));
                session.closeOnFlush();
            }
        });
        nioSocketConnector.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new SmsCodecFactory(Charset.forName("UTF-8"))));//往最后面加一个过滤器
        Log.d("$$$$$$", "$$$$$$:mina初始化完毕");
    }
    public static MinaController getINSTANCE(){
        if (INSTANCE == null) {
            INSTANCE = new MinaController();
        }
        return INSTANCE;
    }

    public void setConfig(ConnectConfig connectConfig)  {
        if (connectConfig == null) {
            EventBus.getDefault().post(new SmsEventMessage(null,"connectConfig  is  null"));
            throw new NullPointerException("connectConfig  is  null");
        }
        if (connectConfig.getIp() == null) {
            EventBus.getDefault().post(new SmsEventMessage(null,"host ip is  null"));
            throw new NullPointerException("host ip is  null");
        }
        if (connectConfig == null) {
            EventBus.getDefault().post(new SmsEventMessage(null,"connectConfig  is  null"));
            throw new NullPointerException("connectConfig  is  null");
        }
        mInetSocketAddress = new InetSocketAddress(connectConfig.getIp(),connectConfig.getPort());
    }


    /** 连接其他地址*/
    public void changeAddr(String ip,int port){
        if(RegexUtil.isIp(ip)){
            Log.e("$$$$$$","$$$$$$:not Ip value");
            return ;
        }
        mInetSocketAddress = new InetSocketAddress(ip,port);
        disconnect();
        connect();
    }

    private NioSocketConnector nioSocketConnector =new NioSocketConnector();
    //注意ioSeeion的生命周期
    private IoSession ioSession ;
    /**mina连接状态 表示曾经*/
    private ConnectFuture connectFuture;
    public long connect(){
        if (!NetWorkUtils.isNetWorkAvailable(App.getContext())){
            Log.d("$$$$$$","网络未连接,无法连接后台");
            return -1;
        }

        try {
            switch (getConnectState()) {
                case future_false:
                    connectFuture=nioSocketConnector.connect(mInetSocketAddress);
                    connectFuture.awaitUninterruptibly();//awaitUninterruptibly 是阻塞方法，等待write写完
                    ioSession = connectFuture.getSession();
                    ioSession.write(new SmsObject(ConnectType.CONNECT,"12345", "no","no","connect"));
                    break;
                case session_false:
                    connectFuture=nioSocketConnector.connect(mInetSocketAddress);
                    connectFuture.awaitUninterruptibly();//awaitUninterruptibly 是阻塞方法，等待write写完
                    ioSession = connectFuture.getSession();
                    ioSession.write(new SmsObject(ConnectType.CONNECT,"12345", "no","no","connect"));
                    break;
                case session_true:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("$$$$$$", "$$$$$$connect:"+(null!=ioSession?ioSession.getId():ioSession));
        // TODO 连接之后的心跳处理
        return (null!=ioSession?ioSession.getId():-1);
    }

    public boolean disconnect(){
        switch (getConnectState()) {
            case future_false:
                break;
            case session_false:
                break;
            case session_true:
                ioSession.closeOnFlush();
                break;
        }
        Log.d("$$$$$$", "$$$$$$disconnect:"+ioSession.getId());
        return true;
    }

    private ConnectState getConnectState(){
        if(null!=connectFuture&&connectFuture.isConnected()){
            if(null!=ioSession&&ioSession.isActive()){
                Log.d("$$$$$$", "$$$$$$ioSession--true");
                return ConnectState.session_true;
            }else{
                Log.d("$$$$$$", "$$$$$$ioSession--false");
                return ConnectState.session_false;
            }
        }else{
            Log.d("$$$$$$", "$$$$$$connectFuture--false");
            return ConnectState.future_false;
        }
    }

    /**
     *
     *
     * @return
     */
    public boolean sendMessage(SmsObject smsObject){
        connect();
        if(null==connectFuture||null==ioSession){
            Log.d("$$$$$$", "未连接无法发送消息");
            return false;
        }
        connectFuture.awaitUninterruptibly();
        ioSession.write(smsObject);
        return true;
    }



    public enum ConnectState{
        future_false,
        session_false,
        session_true
    }
}
