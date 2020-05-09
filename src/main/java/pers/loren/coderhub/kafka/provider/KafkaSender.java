package pers.loren.coderhub.kafka.provider;


import com.gexin.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import pers.loren.coderhub.kafka.beans.KMessage;

import java.util.UUID;

//@Component
public class KafkaSender {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //发送消息方法
    public void send(String msg) {
        KMessage message = new KMessage();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString() + "-" + msg);
        message.setTime(System.currentTimeMillis());
        kafkaTemplate.send("k_message", JSON.toJSONString(message));
    }
}
