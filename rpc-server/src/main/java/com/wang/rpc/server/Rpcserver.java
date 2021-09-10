package com.wang.rpc.server;

import com.wang.rpc.Request;
import com.wang.rpc.Response;
import com.wang.rpc.codec.Decoder;
import com.wang.rpc.codec.Encoder;
import com.wang.rpc.common.utils.ReflectionUtils;
import com.wang.rpc.transport.RequestHandler;
import com.wang.rpc.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @program: rpc
 * @description:
 * @author: wangwancheng
 * @create: 2021-09-08 23:02
 */
@Slf4j
public class Rpcserver {
    private RpcServerConfig config;
    private TransportServer net;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;

    private RequestHandler handler = new RequestHandler() {
        Response resp = new Response();
        @Override
        public void onRequest(InputStream recive, OutputStream toResp) {
            try {
                byte[] inBytes = IOUtils.readFully(recive,recive.available());
                Request request = decoder.decode(inBytes,Request.class);
                log.info("get request: {}",request);
                ServiceInstance sis = serviceManager.lookup(request);
                Object ret = serviceInvoker.invoke(sis, request);
                resp.setData(ret);

            } catch (IOException e) {
                log.warn(e.getMessage(),e);
                resp.setCode(1);
                resp.setMessage("RpcServer get error:"+e.getClass().getName()+":"+e.getMessage());
            }finally {
                byte[] outBytes = encoder.encode(resp);
                try {
                    toResp.write(outBytes);
                    log.info("response client");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    };
    public Rpcserver(RpcServerConfig config){
        this.config = config;

        //net
        this.net = ReflectionUtils.newInstance(config.getTransportClass());
        this.net.init(config.getPort(), this.handler);
        //codec
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());

        // service
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();

    }
    public void  start() throws Exception {
        this.net.start();
    }
    public void stop(){
        this.net.stop();
    }

    public <T> void register(Class<T> interfaceClass, T bean){
        serviceManager.register(interfaceClass, bean);
    }
    public Rpcserver(){
        this(new RpcServerConfig());
    }
}