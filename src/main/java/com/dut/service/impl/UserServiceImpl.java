package com.dut.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dut.bean.ResultBean;
import com.dut.constant.Constant;
import com.dut.constant.MessageUtils;
import com.dut.controller.UserController;
import com.dut.dao.PermissionDao;
import com.dut.dao.RoleDao;
import com.dut.dao.UserDao;
import com.dut.dto.UserInfo;
import com.dut.entity.PermissionEntity;
import com.dut.entity.RoleEntity;
import com.dut.entity.UserEntity;
import com.dut.service.UserService;
import com.dut.utils.ApiValidateException;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public ResultBean createUser(String json) throws ApiValidateException {
        LOGGER.info("------createUser START--------------");
        JSONObject jsonObject = new JSONObject(json);
        String username = "";
        String fullname = "";
        String password = "";
        Integer status = 1;
        String skill = "";
        String level = "";
        String email = "";
        Integer age = 0;
        Integer gender = 0;

        // check username
        if (jsonObject.has("username")) {
            username = jsonObject.getString("username");
        }

        // check fullname
        if (jsonObject.has("fullname")) {
            fullname = jsonObject.getString("fullname");
        }

        // check password
        if (jsonObject.has("password")) {
            password = jsonObject.getString("password");
        }

        // check skill
        if (jsonObject.has("skill")) {
            skill = jsonObject.getString("skill");
        }

        // check level
        if (jsonObject.has("level")) {
            level = jsonObject.getString("level");
        }

        // check email
        if (jsonObject.has("email")) {
            email = jsonObject.getString("email");
        }

        // check age
        if (jsonObject.has("age")) {
            age = jsonObject.getInt("age");
        }

        // check gender
        if (jsonObject.has("gender")) {
            age = jsonObject.getInt("gender");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setFullname(fullname);
        userEntity.setStatus(status);
        userEntity.setUsername(username);
        userEntity.setPassword(new BCryptPasswordEncoder().encode(password));
        userEntity.setAge(age);
        userEntity.setEmail(email);
        userEntity.setGender(gender);
        userEntity.setLevel(level);
        userEntity.setSkill(skill);
        userDao.createUser(userEntity);
        LOGGER.info("------createUser END--------------");
        return new ResultBean(userEntity, Constant.OK, MessageUtils.getMessage("MSG01", "Create user"));
    }

    @Override
    public ResultBean getUserById(Integer id) throws ApiValidateException {
        LOGGER.info("------getUserById START--------------");
        UserInfo userInfo = new UserInfo();

        UserEntity userEntity = userDao.getUserById(id);
        List<PermissionEntity> permissions = permissionDao.getPermissionByUserId(id);
        List<RoleEntity> roles = roleDao.getUserRole(id);

        userInfo.setUserEntity(userEntity);
        userInfo.setRoles(roles);
        userInfo.setPermissions(permissions);

        LOGGER.info("------getUserById END--------------");
        return new ResultBean(userInfo, Constant.OK, MessageUtils.getMessage("MSG01", "Get user"));
    }

    @Override
    public ResultBean updateUser(String json) throws ApiValidateException {
        LOGGER.info("------updateUser START--------------");
        UserEntity userEntity = new UserEntity();
        JSONObject jsonObject = new JSONObject(json);

        // check id
        if (jsonObject.has("id")) {
            Integer id = jsonObject.getInt("id");
            userEntity = userDao.getUserById(id);
        }

        // check username
        if (jsonObject.has("username")) {
            String username = jsonObject.getString("username");
            userEntity.setUsername(username);
        }

        // check password
        if (jsonObject.has("password")) {
            String password = jsonObject.getString("password");
            userEntity.setPassword(new BCryptPasswordEncoder().encode(password));
        }

        // check fullname
        if (jsonObject.has("fullname")) {
            String fullname = jsonObject.getString("fullname");
            userEntity.setFullname(fullname);
        }

        userDao.updateUser(userEntity);
        LOGGER.info("------updateUser END--------------");
        return new ResultBean(userEntity, Constant.OK, MessageUtils.getMessage("MSG01", "Update user"));
    }

    @Override
    public ResultBean deleteUser(String json) throws ApiValidateException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResultBean getAll() throws ApiValidateException {
        LOGGER.info("------getAll START--------------");
        List<UserEntity> listUsers = userDao.getAll();
        LOGGER.info("------getAll END--------------");
        return new ResultBean(listUsers, Constant.OK, MessageUtils.getMessage("MSG01", "Get all user"));
    }

}
