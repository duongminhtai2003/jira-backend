package com.dut.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dut.bean.ResultBean;
import com.dut.constant.Constant;
import com.dut.constant.MessageUtils;
import com.dut.dao.UserRoleDao;
import com.dut.entity.UserRoleEntity;
import com.dut.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private static final Logger LOGGER = LogManager.getLogger(UserRoleServiceImpl.class);

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public ResultBean createUserRole(String json) {
        LOGGER.info("------createUserRole START--------------");

        UserRoleEntity userRoleEntity = new UserRoleEntity();
        JSONObject jsonObject = new JSONObject(json);

        // check userId
        if (jsonObject.has("user_id")) {
            Integer userId = jsonObject.getInt("user_id");
            userRoleEntity.setUserId(userId);
        }

        // check roleId 
        if (jsonObject.has("role_id")) {
            Integer roleId = jsonObject.getInt("role_id");
            userRoleEntity.setRoleId(roleId);
        }

        userRoleDao.createUserRole(userRoleEntity);

        LOGGER.info("------createUserRole END--------------");
        return new ResultBean(userRoleEntity, Constant.OK, MessageUtils.getMessage("MSG01", new Object[] { "Create user role" }));
    }

    @Override
    public ResultBean updateUserRole(String json) {
        LOGGER.info("------updateUserRole START--------------");
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        JSONObject jsonObject = new JSONObject(json);

        // check userId 
        if (jsonObject.has("user_id")) {
            Integer userId = jsonObject.getInt("user_id");
            userRoleEntity.setUserId(userId);
        }

        // check roleId 
        if (jsonObject.has("role_id")) {
            Integer roleId = jsonObject.getInt("role_id");
            userRoleEntity.setRoleId(roleId);
        }

        // get user-role
        userRoleEntity = userRoleDao.getUserRoleByUserIdAndRoleId(userRoleEntity.getUserId(), userRoleEntity.getRoleId());

        // check newRoleId 
        if (jsonObject.has("new_role_id")) {
            Integer newRoleId = jsonObject.getInt("new_role_id");
            userRoleEntity.setRoleId(newRoleId);
        }

        userRoleDao.updateUserRole(userRoleEntity);

        LOGGER.info("------updateUserRole END--------------");
        return new ResultBean(userRoleEntity, Constant.OK, MessageUtils.getMessage("MSG01", new Object[] { "Update user role" }));
    }

}
