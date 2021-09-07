package com.wang.rpc;

import lombok.Data;

/**
 * @program: rpc
 * @description: 表示RPC的返回
 * @author: wangwancheng
 * @create: 2021-09-06 23:34
 */
@Data
public class Response {
    /*
    *   服务返回编码，0-成功，非0失败
    * */
    private int code = 0;
    /*
    * 具体的错误信息
    * */
    private String message = "ok";
    /**
     * 返回的数据
     */
    private Object data;
}