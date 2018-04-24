package pers.loren.coderhub.service.impl;

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
}
