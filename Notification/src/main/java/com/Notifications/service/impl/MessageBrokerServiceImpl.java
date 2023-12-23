package com.Notifications.service.impl;

import com.Notifications.service.MessageBrokerService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Queue;

@Service
public class MessageBrokerServiceImpl implements MessageBrokerService {
    private RabbitTemplate rabbitTemplate;

    private Queue notificationQueue;

    public MessageBrokerServiceImpl(RabbitTemplate rabbitTemplate, Queue notificationQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.notificationQueue = notificationQueue;
    }

    @Override
    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(notificationQueue.toString(), message);
    }
}
