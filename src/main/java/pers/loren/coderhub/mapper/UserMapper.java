package pers.loren.coderhub.mapper;


import pers.loren.coderhub.domain.UserEntity;

import java.util.List;

//@Repository
public interface UserMapper {
    List<UserEntity> getAll();

    UserEntity findByName(String username);

    int insertUser(UserEntity userEntity);
}
