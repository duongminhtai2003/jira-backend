package com.dut.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dut.bean.ResultBean;
import com.dut.constant.Constant;
import com.dut.constant.MessageUtils;
import com.dut.dao.UserProjectDao;
import com.dut.entity.UserProjectEntity;
import com.dut.service.UserProjectService;

@Service
public class UserProjectServiceImpl implements UserProjectService {

    private static final Logger LOGGER = LogManager.getLogger(UserProjectServiceImpl.class);

    @Autowired
    private UserProjectDao userProjectDao;

    @Override
    public ResultBean addUserToProject(String json) {
        LOGGER.info("------addUserToProject START--------------");
        UserProjectEntity userProjectEntity = new UserProjectEntity();
        JSONObject jsonObject = new JSONObject(json);

        // check userId
        if (jsonObject.has("user_id")) {
            Integer userId = jsonObject.getInt("user_id");
            userProjectEntity.setUserId(userId);
        }

        // check projectId 
        if (jsonObject.has("project_id")) {
            Integer projectId = jsonObject.getInt("project_id");
            userProjectEntity.setProjectId(projectId);
        }

        userProjectDao.createUserProject(userProjectEntity);

        LOGGER.info("------addUserToProject END--------------");
        return new ResultBean(userProjectEntity, Constant.OK, MessageUtils.getMessage("MSG01", new Object[] { "Create user project" }));
    }

}
