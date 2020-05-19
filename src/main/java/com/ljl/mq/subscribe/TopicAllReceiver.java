package com.ljl.mq.subscribe;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ：
 * @date ：Created in 2019/12/13 15:40
 * @description：
 * @modified By：
 * @version:
 */
//@Component
//@RabbitListener(queues = "topic.all")
//public class TopicAllReceiver {
//    @RabbitHandler
//    public void process(String testMessage) {
//        System.out.println("all  : " + testMessage);
//    }
//}
