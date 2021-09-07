package com.wang.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: rpc
 * @description: 表示服务
 * @author: wangwancheng
 * @create: 2021-09-06 23:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SerivceDescriptor {
    private String claszz;
    private String method;
    private String returnType;
    private String[] parameterTypes;
}