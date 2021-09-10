package com.wang.rpc.client;

import com.wang.rpc.Peer;
import com.wang.rpc.common.utils.ReflectionUtils;
import com.wang.rpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @program: rpc
 * @description:
 * @author: wangwancheng
 * @create: 2021-09-09 00:17
 */
@Slf4j
public class RandomTransportSelector implements TransportSelector{
    /**
     *  以及连接好的client
     */
    private List<TransportClient> clients;

    public RandomTransportSelector() {
        this.clients = new ArrayList<>();
    }

    @Override
    public void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz) {
        count = Math.max(count, 1);
        for (Peer peer:peers){
            for (int i = 0; i<count; i++){
                TransportClient client = ReflectionUtils.newInstance(clazz);
                client.connect(peer);
                clients.add(client);
            }
            log.info("connect server:{}",peer);
        }

    }

    @Override
    public synchronized TransportClient select() {
        int i = new Random().nextInt(clients.size());
        return clients.remove(i);
    }

    @Override
    public synchronized void release(TransportClient clinet) {
        clients.add(clinet);

    }

    @Override
    public synchronized void close() {
        for(TransportClient client:clients){
            client.close();
        }
        clients.clear();

    }
}