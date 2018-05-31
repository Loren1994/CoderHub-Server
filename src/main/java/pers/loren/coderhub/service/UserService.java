package pers.loren.coderhub.service;

import pers.loren.coderhub.domain.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> getAllUser();

    UserEntity selectByUserName(String username);

    int insertUser(UserEntity userEntity);

    String testRedis(String id);
}
