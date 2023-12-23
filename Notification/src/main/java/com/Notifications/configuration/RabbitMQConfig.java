package com.Notifications.configuration;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static String NOTIFICATION_QUEUE = "notification_queue";

    @Bean
    public Queue notificationQueue() {
        return new Queue(NOTIFICATION_QUEUE);
    }
}
