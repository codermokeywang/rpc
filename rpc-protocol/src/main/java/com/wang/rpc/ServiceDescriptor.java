package com.wang.rpc;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @program: rpc
 * @description: 表示服务
 * @author: wangwancheng
 * @create: 2021-09-06 23:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ServiceDescriptor {
    private String claszz;
    private String method;
    private String returnType;
    private String[] parameterTypes;

    public static ServiceDescriptor from(Class clazz, Method method){
        ServiceDescriptor sdp = new ServiceDescriptor() ;
        sdp.setClaszz(clazz.getName());
        sdp.setMethod(method.getName());
        sdp.setReturnType(method.getReturnType().getName());

        Class[] parameterClasses =  method.getParameterTypes();
        String[] parameterTypes = new String[parameterClasses.length];
        for (int i = 0; i<parameterClasses.length; i++){
            parameterTypes[i] = parameterClasses[i].getName();
        }
        sdp.setParameterTypes(parameterTypes);
        return  sdp;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o==null || getClass() != o.getClass()) return false;
        ServiceDescriptor that = (ServiceDescriptor)o;
        return this.toString().equals(that.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
    public String toString(){
        return "clazz="+claszz+",method=" + method+ ",returnType=" + returnType + ",parameterTypes="+ Arrays.toString(parameterTypes);
    }


}