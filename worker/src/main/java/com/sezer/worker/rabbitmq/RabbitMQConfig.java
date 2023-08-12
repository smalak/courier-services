package com.sezer.worker.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${application.location-queue-config.name}")
    String locationQueue;

    @Value("${application.log-queue-config.name}")
    String logQueue;

    @Value("${application.location-queue-config.exchange}")
    String locationExchange;

    @Value("${application.log-queue-config.exchange}")
    String logExchange;

    @Value("${application.location-queue-config.routing-key}")
    String locationRoutingKey;

    @Value("${application.log-queue-config.routing-key}")
    String logRoutingKey;

    @Bean
    public Queue locationQueue() {
        return new Queue(locationQueue, true);
    }

    @Bean
    public Queue logQueue() {
        return new Queue(logQueue, true);
    }

    @Bean
    DirectExchange locationExchange() {
        return new DirectExchange(locationExchange);
    }

    @Bean
    DirectExchange logExchange() {
        return new DirectExchange(logExchange);
    }

    @Bean
    Binding locationBinding(Queue locationQueue, DirectExchange locationExchange) {
        return BindingBuilder.bind(locationQueue).to(locationExchange).with(locationRoutingKey);
    }

    @Bean
    Binding logBinding(Queue logQueue, DirectExchange logExchange) {
        return BindingBuilder.bind(logQueue).to(logExchange).with(logRoutingKey);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
