package com.fourous.nettyProxy.example;

import com.fourous.nettyProxy.annotation.MessageHandler;
import com.fourous.nettyProxy.disruptor.EventContext;
import com.fourous.nettyProxy.tool.JacksonUtil;
import com.fourous.nettyProxy.tool.ProtoFactory;
import com.google.protobuf.Message;
import com.fourous.nettyProxy.protocol.protobuf.GenericProto;
import com.fourous.nettyProxy.protocol.protobuf.ProtocolMessage;
import com.fourous.nettyProxy.protocol.protobuf.RequestProto;
import com.fourous.nettyProxy.protocol.protobuf.ResponseProto;
import com.fourous.nettyProxy.annotation.ProtocolMapping;

import java.io.IOException;

/**
 * @Author: wonderzh
 * @Date: 2020/7/8
 * @Version: 1.0
 */
@MessageHandler
public class EventHandlerDemo {


    @ProtocolMapping("/dw/lf")
    public Object uploadLf(RequestProto.LfData lfData, EventContext context) throws InterruptedException {
        System.out.println(lfData.getData(0).getItemId());
        return ProtocolMessage.DEFAULT_PROTOCOL.newResponseInstance().getAck();
    }

    @ProtocolMapping("test/hf")
    public Object uploadHf(RequestProto.HfData hfData) {
        System.out.println(hfData.getEventType());
        return null;
    }

    @ProtocolMapping("/dw/json")
    public Message json(EventContext context, GenericProto.JsonObj jsonObj) {
        try {
            JsonBodyTest bodyTest= JacksonUtil.jsonToObj(jsonObj.getBody(),JsonBodyTest.class);
            System.out.println(context.getChannelId());
            System.out.println(bodyTest.getName());

            ProtocolMessage message= ProtoFactory.generateJsonRequestProtocol(ProtocolMessage.DEFAULT_PROTOCOL,"/dw/json",JacksonUtil.objectToJson(bodyTest));
            context.getCtx().channel().writeAndFlush(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ResponseProto.Response createResponse() {
        ResponseProto.Response.Builder responseBuilder = ResponseProto.Response.newBuilder();
        responseBuilder.setStatus(5000);
        responseBuilder.setMessage("错误响应测试");
        return responseBuilder.build();
    }


}
