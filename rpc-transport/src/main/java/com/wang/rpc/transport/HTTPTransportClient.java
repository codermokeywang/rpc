package com.wang.rpc.transport;

import com.wang.rpc.Peer;
import org.apache.commons.io.IOUtils;
import sun.nio.ch.IOUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @program: rpc
 * @description: 基于http实现的客户端
 * @author: wangwancheng
 * @create: 2021-09-07 21:20
 */
public class HTTPTransportClient implements TransportClinet {
    private String url;
    @Override
    public void connect(Peer peer) {
        this.url = "http://" + peer.getHost() + ":" + peer.getPort();
    }

    @Override
    public InputStream write(InputStream data) throws IOException {
        HttpURLConnection httpConn = (HttpURLConnection) new URL(url).openConnection();
        httpConn.setDoOutput(true);
        httpConn.setDoInput(true);
        httpConn.setUseCaches(false);
        httpConn.setRequestMethod("POST");
        httpConn.connect();
        IOUtils.copy(data,httpConn.getOutputStream());
        int resultCode = httpConn.getResponseCode();
        if(resultCode == HttpURLConnection.HTTP_OK){
            return httpConn.getInputStream();
        } else {
            return httpConn.getErrorStream();
        }

    }

    @Override
    public void close() {

    }
}