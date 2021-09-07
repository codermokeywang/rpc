package com.wang.rpc.transport;

/**
 * @program: rpc
 * @description: 1.启动、监听
 * 2.接受请求
 * 3.关闭监听
 * @author: wangwancheng
 * @create: 2021-09-07 21:15
 */
public interface TransportServer {
    void init(int port, RequestHandler handler);
    void start() throws Exception;
    void stop();
}