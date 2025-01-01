package com.itheima.mp.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dongbinyu
 * @version 1.0
 * @project mp-demo
 * @description
 * @date 2025/1/1 15:27:22
 */
@Configuration
public class LazyQueueConfig {
    @Bean
    public Queue lazyQueue(){
        return QueueBuilder
                .durable("lazy.queue")
                .lazy() // 开启Lazy模式
                .build();
    }
}
