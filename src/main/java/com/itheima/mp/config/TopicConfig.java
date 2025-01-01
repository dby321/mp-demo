package com.itheima.mp.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfig {

    /**
     * 声明交换机
     * @return Direct类型交换机
     */
    @Bean
    public TopicExchange topicExchange(){
        return ExchangeBuilder.topicExchange("hmall.topic").build();
    }

    /**
     * 第1个队列
     */
    @Bean
    public Queue topicQueue1(){
        return new Queue("topic.queue1");
    }
    /**
     * 第2个队列
     */
    @Bean
    public Queue topicQueue2(){
        return new Queue("topic.queue2");
    }

    /**
     * 绑定队列和交换机
     */
    @Bean
    public Binding bindingQueue1WithNews(Queue topicQueue1, TopicExchange topicExchange){
        return BindingBuilder.bind(topicQueue1).to(topicExchange).with("#.news");
    }
    /**
     * 绑定队列和交换机
     */
    @Bean
    public Binding bindingQueue2WithChina(Queue topicQueue1, TopicExchange topicExchange){
        return BindingBuilder.bind(topicQueue1).to(topicExchange).with("china.#");
    }


}