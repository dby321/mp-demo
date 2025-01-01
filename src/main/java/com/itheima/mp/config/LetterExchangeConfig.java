package com.itheima.mp.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dongbinyu
 * @version 1.0
 * @project mp-demo
 * @description
 * @date 2025/1/1 15:47:59
 */
@Configuration
public class LetterExchangeConfig {

        //===========================普通===========================
        //定义队列的名称常量
        public static final String DIRECT_QUEUE = "directQueue";
        public static final String DIRECT_QUEUE2 = "directQueue2";
        //定义直接交换机的名称常量
        public static final String DIRECT_EXCHANGE = "directExchange";
        //定义路由键常量，用于交换机和队列之间的绑定
        public static final String DIRECT_ROUTING_KEY = "direct";
        //定义路由键常量，用于交换机和队列之间的绑定
        public static final String DIRECT_ROUTING_KEY_2= "direct2";





        //定义队列，名称为DIRECT_QUEUE
        //为普通队列 绑设置 死信参数
        @Bean
        public Queue directQueue() {
            //return new Queue(DIRECT_QUEUE, true);
            Map<String, Object> args = new HashMap<>();
            // 设置死信交换机
            args.put("x-dead-letter-exchange", DLX_EXCHANGE);
            // 设置发送到死信交换机的路由键
            args.put("x-dead-letter-routing-key", DLX_ROUTING_KEY);
            // 创建队列，设置为持久化、非排他、非自动删除，并附带死信参数
            return new Queue(DIRECT_QUEUE, true, false, false, args);
        }


        //定义直接交换机
        @Bean
        public DirectExchange directExchange() {
            return new DirectExchange(DIRECT_EXCHANGE, true, false);
        }

        //定义队列，名称为DIRECT_QUEUE2
        @Bean
        public Queue directQueue2() {
            return new Queue(DIRECT_QUEUE2, true);
        }


        //定义一个绑定，将directQueue队列绑定到directExchange交换机上，
        //使用direct作为路由键
        @Bean
        public Binding bindingDirectExchange(Queue directQueue, DirectExchange directExchange) {
            return BindingBuilder.bind(directQueue).to(directExchange).with(DIRECT_ROUTING_KEY);
        }

        // 定义一个绑定Bean，将directQueue2队列也绑定到directExchange交换机上，
        @Bean
        public Binding bindingDirectExchange2(Queue directQueue2, DirectExchange directExchange) {
            return BindingBuilder.bind(directQueue2).to(directExchange).with(DIRECT_ROUTING_KEY_2);
        }

        //===========================死信===========================
        // 定义死信交换机的名称
        public static final String DLX_EXCHANGE = "dlx_exchange";
        // 定义发送到死信交换机的路由键
        public static final String DLX_ROUTING_KEY = "dlx.routing.key";
        // 定义死信队列的名称
        public static final String DLX_QUEUE = "dlx_queue";

        /**
         * 声明死信交换机，这里使用Direct类型。
         * @return 返回一个配置好的DirectExchange对象。
         */
        @Bean
        DirectExchange dlxExchange() {
            // 创建并返回Direct类型的交换机
            return new DirectExchange(DLX_EXCHANGE,true, false);
        }
        /**
         * 声明死信队列。
         * @return 返回一个配置好的Queue对象，用作死信队列。
         */
        @Bean
        Queue dlxQueue() {
            // 创建并返回死信队列，设置为持久化
            return new Queue(DLX_QUEUE, true);
        }

        /**
         * 绑定死信队列到死信交换机，使用指定的路由键。
         */
        @Bean
        Binding binding(Queue dlxQueue,DirectExchange dlxExchange) {
            return BindingBuilder.bind(dlxQueue).to(dlxExchange).with(DLX_ROUTING_KEY);
        }


}
