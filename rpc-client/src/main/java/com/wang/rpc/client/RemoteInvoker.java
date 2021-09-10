package com.wang.rpc.client;

import com.wang.rpc.Request;
import com.wang.rpc.Response;
import com.wang.rpc.ServiceDescriptor;
import com.wang.rpc.codec.Decoder;
import com.wang.rpc.codec.Encoder;
import com.wang.rpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @program: rpc
 * @description: 调用远程服务的代理类
 * @author: wangwancheng
 * @create: 2021-09-09 11:20
 */
@Slf4j
public class RemoteInvoker implements InvocationHandler {
    private Class clazz;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RemoteInvoker(Class clazz, Encoder encoder, Decoder decoder, TransportSelector selector) {
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder = decoder;
        this.selector = selector;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = new Request();
        request.setServiceDescriptor(ServiceDescriptor.from(clazz,method));
        request.setParameters(args);
        Response resp = invokeRemote(request);
        if(resp == null ||resp.getCode()!=0)
            throw new IllegalStateException("fail to invoke remote:" +resp);
        return resp.getData();
    }

    private Response invokeRemote(Request request) {
        Response response = null;
        TransportClient client = null;



        try {
            client = selector.select();
            byte[] encode = encoder.encode(request);
            InputStream write = client.write(new ByteArrayInputStream(encode));
            byte[] bytes = IOUtils.readFully(write,write.available());
            response = decoder.decode(bytes,Response.class);
        } catch (IOException e) {
            log.warn("[invokeRemote] e={},{}",e.getMessage(),e);
            response = new Response();
            response.setCode(1);
            response.setMessage("Rpc error"+e.getClass()+":"+e.getMessage());

        }finally {
            if(client!=null)
                selector.release(client);
        }
        return response;

    }
}