package com.wang.rpc.codec;

/**
 * @program: rpc
 * @description: εεΊεε
 * @author: wangwancheng
 * @create: 2021-09-07 00:31
 */
public interface Decoder {
    <T> T decode(byte[] bytes,Class<T> clazz);
}