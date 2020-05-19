package com.ljl.mq.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：
 * @date ：Created in 2019/12/13 14:49
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("mq/")
public class SendMessage {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public String send(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("test","哈哈");
        rabbitTemplate.convertAndSend("ljl","topic.save", jsonObject.toJSONString());
//        System.out.println(object);
        return "r";
    }

    @GetMapping("/send2")
    public String send2(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("test","哈哈");
        rabbitTemplate.convertAndSend("ljl", "topic.*", jsonObject.toJSONString());
        return "r";
    }

    @GetMapping("/send3")
    public String send3(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("test","哈哈");
        rabbitTemplate.convertAndSend("ljl","topic.edit", jsonObject.toJSONString());
//        System.out.println(object);
        return "r";
    }
}
