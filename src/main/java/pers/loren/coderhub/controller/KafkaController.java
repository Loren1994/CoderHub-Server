package pers.loren.coderhub.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.loren.coderhub.kafka.provider.KafkaSender;
import pers.loren.coderhub.util.Result;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private KafkaSender kafkaSender;

    @GetMapping("send")
    public Result send(@RequestParam String msg) {
        kafkaSender.send(msg);
        return new Result(HttpStatus.OK.value(), "Send Success !!!");
    }
}
