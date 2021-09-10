package com.wang.rpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * @program: rpc
 * @description: fastjson实现序列化
 * @author: wangwancheng
 * @create: 2021-09-07 00:35
 */
public class JSONEncoder implements Encoder{
    @Override
    public byte[] encode(Object obj) {
        return JSON.toJSONBytes(obj);
    }
}