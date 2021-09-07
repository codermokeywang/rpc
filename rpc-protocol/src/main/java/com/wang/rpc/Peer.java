package com.wang.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @program: rpc
 * @description: 表示网络传输的一个端点
 * @author: wangwancheng
 * @create: 2021-09-06 23:16
 */
@Data
@AllArgsConstructor
public class Peer {
    private String host;
    private int port;
}