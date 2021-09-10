package com.wang.rpc.server;

import com.wang.rpc.codec.Decoder;
import com.wang.rpc.codec.Encoder;
import com.wang.rpc.codec.JSONDecoder;
import com.wang.rpc.codec.JSONEncoder;
import com.wang.rpc.transport.HttpTransportServer;
import com.wang.rpc.transport.TransportServer;
import lombok.Data;
import org.eclipse.jetty.server.HttpTransport;

/**
 * @program: rpc
 * @description: server配置
 * @author: wangwancheng
 * @create: 2021-09-07 23:02
 */
@Data
public class RpcServerConfig {
    //网络协议
    private Class<? extends TransportServer> transportClass = HttpTransportServer.class;
    //序列化
    private Class<? extends Encoder>  encoderClass = JSONEncoder.class;
    //反序列化
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private int port = 3000;



}