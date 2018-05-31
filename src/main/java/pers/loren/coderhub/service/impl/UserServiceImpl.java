package pers.loren.coderhub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.loren.coderhub.domain.UserEntity;
import pers.loren.coderhub.mapper.UserMapper;
import pers.loren.coderhub.service.UserService;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public List<UserEntity> getAllUser() {
        return userMapper.getAll();
    }

    @Override
    public UserEntity selectByUserName(String username) {
        return userMapper.findByName(username);
    }

    @Override
    public int insertUser(UserEntity userEntity) {
        return userMapper.insertUser(userEntity);
    }

    @Override
    @Cacheable(value = "user", key = "#str", unless = "#result==null")
    public String testRedis(String str) {
        System.out.println("看到我时不是缓存>>>>>>>>>>" + str);
        String name = userMapper.getAll().get(0).getName();
        return name;
    }
}
