package com.wang.rpc.server;

import com.wang.rpc.Request;
import com.wang.rpc.common.utils.ReflectionUtils;

/**
 * @program: rpc
 * @description:调用具体的服务
 * @author: wangwancheng
 * @create: 2021-09-08 22:30
 */
public class ServiceInvoker {
    public Object invoke(ServiceInstance service, Request request){
        return ReflectionUtils.invoke(
                service.getTarget(),
                service.getMethod(),
                request.getParameters()
        );
    }
}