package com.dut.dao;

import java.util.List;

import com.dut.entity.UserEntity;

public interface UserDao {

    public UserEntity getUserById(Integer id);

    public UserEntity getUserByUserName(String username);

    public List<UserEntity> getAll();

    public void createUser(UserEntity userEntity);

    public void updateUser(UserEntity userEntity);

}
