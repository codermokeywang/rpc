package com.wang.rpc;

import lombok.Data;

/**
 * @program: rpc
 * @description: 请求服务
 * @author: wangwancheng
 * @create: 2021-09-06 23:33
 */
@Data
public class Request {
    private ServiceDescriptor serviceDescriptor;
    private Object[] parameters;

}