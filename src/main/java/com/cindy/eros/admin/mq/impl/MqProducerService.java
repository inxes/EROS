package com.cindy.eros.admin.mq.impl;

import com.cindy.eros.admin.mq.IMqProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;


/**
 * @program: eros
 * @description:
 * @author: cindy
 * @create: 2018-08-24 15:17
 **/

@Service("producer")
public class MqProducerService implements IMqProducerService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Override
    public void sendMessage(Destination destination, String message) {
        jmsMessagingTemplate.convertAndSend(destination,message);
    }
}
