package com.dut.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dut.bean.ResultBean;
import com.dut.constant.Constant;
import com.dut.constant.MessageUtils;
import com.dut.dao.ProjectDao;
import com.dut.dao.UserDao;
import com.dut.dao.UserProjectDao;
import com.dut.entity.ProjectEntity;
import com.dut.entity.UserEntity;
import com.dut.entity.UserProjectEntity;
import com.dut.service.ProjectService;
import com.dut.utils.ApiValidateException;
import com.dut.utils.DataUtils;

@Service
public class ProjectServiceImpl implements ProjectService {

    private static final Logger LOGGER = LogManager.getLogger(ProjectServiceImpl.class);

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserProjectDao userProjectDao;

    @Override
    public ResultBean createProject(String json) throws ApiValidateException {
        LOGGER.info("------createProject START--------------");

        ProjectEntity projectEntity = new ProjectEntity();
        JSONObject jsonObject = new JSONObject(json);

        // check & set name
        if (jsonObject.has("name")) {
            String name = jsonObject.getString("name");
            projectEntity.setName(name);
        }

        // check & set description
        if (jsonObject.has("description")) {
            String description = jsonObject.getString("description");
            projectEntity.setDescription(description);
        }

        // set status
        projectEntity.setStatus(0);

        // set entityType
        projectEntity.setEntityType(0);

        projectDao.createProject(projectEntity);

        // get PM
        UserEntity userEntity = new UserEntity();
        userEntity = userDao.getUserByUserName(DataUtils.getUsernameByToken());

        // set PM and Project
        UserProjectEntity userProjectEntity = new UserProjectEntity();
        userProjectEntity.setUserId(userEntity.getId());
        userProjectEntity.setProjectId(projectEntity.getId());

        // add PM to Project
        userProjectDao.createUserProject(userProjectEntity);

        LOGGER.info("------createProject END--------------");
        return new ResultBean(projectEntity, Constant.OK, MessageUtils.getMessage("MSG01", new Object[] { "Create project" }));
    }

    @Override
    public ResultBean getProject(String json) throws ApiValidateException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResultBean updateProject(String json) throws ApiValidateException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResultBean deleteProject(String json) throws ApiValidateException {
        // TODO Auto-generated method stub
        return null;
    }

}
