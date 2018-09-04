package com.cindy.eros.admin.mq;


import javax.jms.Destination;

/**
 * @program: eros
 * @description: 生产者
 * @author: cindy
 * @create: 2018-08-24 15:16
 **/

public interface IMqProducerService {

    public void sendMessage(Destination destination, final String message);
}
