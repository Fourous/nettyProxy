package com.fourous.nettyProxy.example;

import com.fourous.nettyProxy.client.ClientHolder;
import com.fourous.nettyProxy.client.CooFuture;
import com.fourous.nettyProxy.client.DefaultClientEventAdapter;
import com.fourous.nettyProxy.client.FutureCallback;
import com.fourous.nettyProxy.protocol.protobuf.GenericProto;
import com.fourous.nettyProxy.protocol.protobuf.ProtocolMessage;
import com.fourous.nettyProxy.protocol.protobuf.ResponseProto;
import com.fourous.nettyProxy.tool.ProtoFactory;
import lombok.extern.slf4j.Slf4j;


/**
 * @Author: wonderzh
 * @Date: 2020/11/5
 * @Version: 1.0
 */
@Slf4j
public class ClientEventListenerDemo extends DefaultClientEventAdapter {

    /**
     * 建立连接后，立即发送身份验证
     * @param clientHolder
     */
    @Override
    public void channelActive(ClientHolder clientHolder) {
        super.channelActive(clientHolder);
        System.out.println("active："+Thread.currentThread().getName());
        //身份认证
        ProtocolMessage identity = ProtoFactory.createIdentityProof(ProtocolMessage.DEFAULT_PROTOCOL,"probe");
        CooFuture<ResponseProto.Ack> idFuture =clientHolder.getClient().send(identity, ResponseProto.Ack.class);

        idFuture.addListener(new FutureCallback<ResponseProto.Ack>() {
            @Override
            public void onSuccess(ResponseProto.Ack ack) throws Exception {
                System.out.println("身份验证成功");
            }

            @Override
            public void onError(Throwable error) throws Exception {
                error.printStackTrace();
            }
        });

    }


    @Override
    public void channelRead(ClientHolder clientHolder, Object msg) {
        System.out.println("read："+Thread.currentThread().getName());
        GenericProto.JsonObj jsonObj = (GenericProto.JsonObj) msg;
        System.out.println(jsonObj.getBody());
    }

    @Override
    public void channelInactive(ClientHolder clientHolder) {
        System.out.println("inactive："+Thread.currentThread().getName());
        super.channelInactive(clientHolder);
    }

    @Override
    public void channelException(ClientHolder clientHolder, Throwable cause) {
        System.out.println("listener exception："+Thread.currentThread().getName());
        super.channelException(clientHolder, cause);
    }
}
