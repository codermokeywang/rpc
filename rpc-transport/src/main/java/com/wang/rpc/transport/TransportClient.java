package com.wang.rpc.transport;

import com.wang.rpc.Peer;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

/**
 * @program: rpc
 * @description: 1.创建连接
 * 2.发送数据，并且等待响应
 * 3.关闭连接
 * @author: wangwancheng
 * @create: 2021-09-07 21:13
 */
public interface TransportClient {
    void connect(Peer peer);
    InputStream write(InputStream data) throws IOException;
    void close();

}