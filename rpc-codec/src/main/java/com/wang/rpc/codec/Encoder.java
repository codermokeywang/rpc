package com.wang.rpc.codec;

/**
 * @program: rpc
 * @description: 序列化
 * @author: wangwancheng
 * @create: 2021-09-07 00:29
 */
public interface Encoder {
    byte[] encode(Object obj);
}