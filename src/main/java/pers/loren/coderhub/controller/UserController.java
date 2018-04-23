package pers.loren.coderhub.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.loren.coderhub.domain.UserEntity;
import pers.loren.coderhub.service.UserService;
import pers.loren.coderhub.util.Result;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public Result getAll() {
        List<UserEntity> list = userService.getAllUser();
        return new Result(JSON.toJSONString(list));
    }

}
