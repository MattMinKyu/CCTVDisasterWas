package com.ytn.cctvdisaster.project.configuration;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitConfiguration {
	
	/*
    private static final String CHAT_QUEUE_NAME = "cctv_disaster";
    private static final String CHAT_EXCHANGE_NAME = "CCTVDisaster";
    private static final String ROUTING_KEY = "ytn.cctv.disaster";
	*/
	
	@Value("${spring.rabbitmq.host}")
    private String host;
    
    @Value("${spring.rabbitmq.username}")
    private String username;
    
    @Value("${spring.rabbitmq.password}")
    private String password;
    
	 /*
    @Value("${rabbitmq.exchange.name}")
    private String exchange_name;
    
    @Value("${rabbitmq.queue.name}")
    private String queue_name;
    
    @Value("${rabbitmq.routingkey.name}")
    private String routingKey;
    
   
    @Bean
    public Queue queue() {
        return new Queue(queue_name, true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange_name);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(routingKey);
    }


    @Bean
    public  com.fasterxml.jackson.databind.Module dateTimeModule() {
        return new JavaTimeModule();
    }
    */
    
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost(host);
        factory.setUsername(username);
        factory.setPassword(password);
        return factory;
    }
    
    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        //rabbitTemplate.setRoutingKey(routingKey);
        return rabbitTemplate;
    }
    
    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        /*
    	ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        objectMapper.registerModule(dateTimeModule());
        return new Jackson2JsonMessageConverter(objectMapper);
        */
    	return new Jackson2JsonMessageConverter();
    }

}

