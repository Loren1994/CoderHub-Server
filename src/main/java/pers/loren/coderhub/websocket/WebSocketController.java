package pers.loren.coderhub.websocket;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {

    @RequestMapping(value = "/webop/{vmc}")
    public String remote(@PathVariable("vmc") String vmc) {
        //json
        return WebSocketServer.sendMessage("测试消息", vmc);
    }
}