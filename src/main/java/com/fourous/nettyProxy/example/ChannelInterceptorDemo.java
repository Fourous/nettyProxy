package com.fourous.nettyProxy.example;

import com.fourous.nettyProxy.protocol.protobuf.ProtocolMessage;
import com.fourous.nettyProxy.sever.factory.CooserComponent;
import com.fourous.nettyProxy.sever.interceptor.ChannelInterceptor;
import com.fourous.nettyProxy.sever.interceptor.InterceptorConfigurer;
import com.fourous.nettyProxy.sever.interceptor.InterceptorRegistry;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wonderzh
 * @Date: 2020/9/11
 * @Version: 1.0
 */

@CooserComponent
@Slf4j
public class ChannelInterceptorDemo implements InterceptorConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new InterceptorDemoImpl())
                .addPathPattern("/dw/**");
    }

    public class InterceptorDemoImpl implements ChannelInterceptor {

        private Map<Long, Long> timeMap = new HashMap<>();

        @Override
        public boolean preHandle(ProtocolMessage msg, Map<String, Object> attributes) {
            timeMap.put(msg.getUuid(), System.currentTimeMillis());
            System.out.println("前置拦截");
            return true;
        }

        @Override
        public void afterHandle(ProtocolMessage response, Map<String, Object> attributes) {
            System.out.println("后置拦截");
            long start = timeMap.get(response.getUuid());
            log.info("业务{}耗时: {}",response.getProtocol(),(System.currentTimeMillis()-start));
        }

    }



}
