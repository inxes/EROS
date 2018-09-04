package com.cindy.eros.admin.mq;

/**
 * @program: eros
 * @description: 消费者
 * @author: cindy
 * @create: 2018-08-24 15:15
 **/

public interface IMqConsumerService {

    public void receiveQueue(String text);
}
