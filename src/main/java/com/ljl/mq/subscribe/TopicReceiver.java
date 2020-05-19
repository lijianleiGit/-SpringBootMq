package com.ljl.mq.subscribe;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.edit")
public class TopicReceiver implements ChannelAwareMessageListener  {

//    @RabbitListener(queues = "topic.save")
//    @RabbitHandler
//    public void saveReceiver(String msg){
//        System.out.println("Topic:"+msg);
//    }

//    @RabbitHandler
//    public void process(String testMessage) {
//        System.out.println("edit  : " + testMessage);
//    }


    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            String msg = message.toString();
            String[] msgArray = msg.split("'");//可以点进Message里面看源码,单引号直接的数据就是我们的map消息数据
            System.out.println(":"+msg);
            System.out.println(msgArray[1]);
            channel.basicAck(deliveryTag, true);
//			channel.basicReject(deliveryTag, true);//为true会重新放回队列
        } catch (Exception e) {
            channel.basicReject(deliveryTag, false);
            e.printStackTrace();
        }
    }

}
