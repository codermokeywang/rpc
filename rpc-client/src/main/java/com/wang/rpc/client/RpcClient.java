package com.wang.rpc.client;

import com.wang.rpc.codec.Decoder;
import com.wang.rpc.codec.Encoder;
import com.wang.rpc.common.utils.ReflectionUtils;


import java.lang.reflect.Proxy;

/**
 * @program: rpc
 * @description:
 * @author: wangwancheng
 * @create: 2021-09-09 11:02
 */
public class RpcClient {
    private RpcClientConfig config;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;
    public RpcClient(){
        this(new RpcClientConfig());
    }
    public RpcClient(RpcClientConfig config){
        this.config = config;
        this.encoder = ReflectionUtils.newInstance(this.config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(this.config.getDecoderClass());
        this.selector = ReflectionUtils.newInstance(this.config.getTransportSelector());

        this.selector.init(this.config.getServers(),this.config.getConnectCount(),this.config.getTranportClass());



    }

    public <T> T getProxy(Class<T> clazz){
        return (T)Proxy.newProxyInstance(getClass().getClassLoader(),
                                      new Class[]{clazz},
                                      new RemoteInvoker(clazz,encoder,decoder,selector));
    }




}