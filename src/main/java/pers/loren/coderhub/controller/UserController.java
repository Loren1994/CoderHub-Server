package pers.loren.coderhub.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pers.loren.coderhub.constant.Constants;
import pers.loren.coderhub.domain.OperationByMapDTO;
import pers.loren.coderhub.domain.UserEntity;
import pers.loren.coderhub.jwt.Audience;
import pers.loren.coderhub.jwt.JWTHelper;
import pers.loren.coderhub.service.UserService;
import pers.loren.coderhub.util.Result;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private Audience audience;

    @GetMapping("/getAll")
    public Result getAll(@RequestParam int pageNum, @RequestParam int pageSize) {
        PageInfo pageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> userService.getAllUser());
        return new Result(pageInfo);
    }

    @PostMapping("/login")
    public Result login(@RequestBody HashMap<String, Object> hashMap, OperationByMapDTO operationByMapDTO, HttpServletResponse response) {
        operationByMapDTO.setHashMap(hashMap);
        String username = hashMap.get("username").toString();
        UserEntity userEntity = userService.selectByUserName(username);
        if (null == userEntity) {
            return new Result(HttpStatus.BAD_REQUEST.value(), Constants.INVALID_USERNAME);
        }
        if (!userEntity.getPassword().equals(hashMap.get("password").toString())) {
            return new Result(HttpStatus.BAD_REQUEST.value(), Constants.INVALID_PASSWORD);
        }
        String jwtToken = Constants.AUTHOR_HEADER_PREFIX +
                JWTHelper.createJWT(username,
                        userEntity.getId(),
                        userEntity.getName(),
                        audience.getClientId(),
                        audience.getName(),
                        audience.getExpiresSecond() * 1000,
                        audience.getBase64Secret());
        response.addHeader(Constants.AUTHOR_HEADER, jwtToken);
        return new Result(HttpStatus.OK.value(), "登录成功", jwtToken);
    }

    @PostMapping("/register")
    public Result register(@RequestBody HashMap<String, Object> hashMap, OperationByMapDTO operationByMapDTO) {
        operationByMapDTO.setHashMap(hashMap);
        UserEntity userEntity = new UserEntity();
        userEntity.setName(hashMap.get("username").toString());
        userEntity.setPassword(hashMap.get("password").toString());
        userEntity.setAddress(hashMap.get("address").toString());
        userEntity.setAge((int) hashMap.get("age"));
        userService.insertUser(userEntity);
        return new Result("注册成功");
    }

}
