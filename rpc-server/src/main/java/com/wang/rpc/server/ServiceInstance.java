package com.wang.rpc.server;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * @program: rpc
 * @description: 表示一个具体服务
 * @author: wangwancheng
 * @create: 2021-09-08 00:07
 */
@Data
@AllArgsConstructor
public class ServiceInstance {
    private Object target;
    private Method method;

}