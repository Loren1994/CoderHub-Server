package pers.loren.coderhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.loren.coderhub.domain.UserEntity;
import pers.loren.coderhub.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String test() {
        return "hello loren";
    }

    @GetMapping("/getAll")
    public int getAll() {
        List<UserEntity> list = userService.getAllUser();
        return list.size();
    }

}
