package com.wang.rpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @program: rpc
 * @description: 处理网路请求的handler
 * @author: wangwancheng
 * @create: 2021-09-07 21:17
 */
public interface RequestHandler {
    void onRequest(InputStream recive, OutputStream toResp);

}