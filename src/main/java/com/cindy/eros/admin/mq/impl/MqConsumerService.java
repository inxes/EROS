package com.cindy.eros.admin.mq.impl;

import com.cindy.eros.admin.mq.IMqConsumerService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * @program: eros
 * @description:
 * @author: cindy
 * @create: 2018-08-24 15:16
 **/

@Service
public class MqConsumerService implements IMqConsumerService {

    @Override
    @JmsListener(destination = "mytest.queue")
    public void receiveQueue(String text) {
        System.out.println("Consumer接收报文为:"+text);
    }
}
