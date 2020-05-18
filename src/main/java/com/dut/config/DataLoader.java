package com.dut.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.dut.dao.UserDao;
import com.dut.entity.UserEntity;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private UserDao userDao;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("admin");
        userEntity.setPassword(new BCryptPasswordEncoder().encode("admin"));
        userEntity.setAge(26);
        userEntity.setFullname("Duong Minh Tai");
        userEntity.setEmail("duongminhtai2003@gmail.com");
        userEntity.setGender(1);
        userEntity.setLevel("MAX");
        userEntity.setSkill("MAX");
        userEntity.setStatus(1);
        if (userDao.checkUsername("admin")) {
            userDao.createUser(userEntity);
        }
    }
}