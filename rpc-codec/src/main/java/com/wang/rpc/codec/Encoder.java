package com.wang.rpc.codec;

/**
 * @program: rpc
 * @description: εΊεε
 * @author: wangwancheng
 * @create: 2021-09-07 00:29
 */
public interface Encoder {
    byte[] encode(Object obj);

}