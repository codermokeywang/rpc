package com.wang.rpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * @program: rpc
 * @description: fastjson实现反序列化
 * @author: wangwancheng
 * @create: 2021-09-07 00:37
 */
public class JSONDecoder implements Decoder{
    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes,clazz);
    }
}