package com.ljl.mq.rabbitMqConf;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

/**
 * topic 模式配置 即 发布/订阅 模式
 * @author ：
 * @date ：Created in 2019/12/13 14:41
 * @description：
 * @modified By：
 * @version:
 */
@Configuration
public class TopicRabbitMqConfig {

    //绑定键
    public final static String save = "topic.save";
    public final static String edit = "topic.edit";
    public final static String all = "topic.all";


    @Bean
    TopicExchange exchange() {
        return new TopicExchange("ljl",true,false);
    }

    @Bean
    public Queue saveQueue() {
        return new Queue(TopicRabbitMqConfig.save);
    }

    @Bean
    public Queue editQueue() {
        return new Queue(TopicRabbitMqConfig.edit);
    }

    @Bean
    public Queue allQueue() {
        return new Queue(TopicRabbitMqConfig.all);
    }




    //将 saveQueue  和 topicExchange 绑定,而且绑定的键值为topic.save
    //这样只要是消息携带的路由键是topic.save,才会分发到该队列
    @Bean
    Binding bindingExchangeMessageSave() {
        return BindingBuilder.bind(saveQueue()).to(exchange()).with(save);
    }


    @Bean
    Binding bindingExchangeMessageEdit() {
        return BindingBuilder.bind(editQueue()).to(exchange()).with(edit);
    }

    //将allQueue 和topicExchange 绑定,而且绑定的键值为用上通配路由键规则topic.#
    // 这样只要是消息携带的路由键是以topic.开头,都会分发到该队列
    @Bean
    Binding bindingExchangeMessageAll() {
        return BindingBuilder.bind(allQueue()).to(exchange()).with("topic.#");
    }


}
