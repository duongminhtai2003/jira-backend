package com.dut.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dut.bean.ResultBean;
import com.dut.constant.Constant;
import com.dut.constant.MessageUtils;
import com.dut.dao.IssueDao;
import com.dut.dao.UserDao;
import com.dut.entity.IssueEntity;
import com.dut.entity.UserEntity;
import com.dut.service.IssueService;
import com.dut.utils.ApiValidateException;
import com.dut.utils.DataUtils;

@Service
public class IssueServiceImpl implements IssueService {

    private static final Logger LOGGER = LogManager.getLogger(IssueServiceImpl.class);

    @Autowired
    private IssueDao issueDao;

    @Autowired
    private UserDao userDao;

    @Override
    public ResultBean createIssue(String json) throws ApiValidateException {
        LOGGER.info("------createIssue START--------------");

        IssueEntity issueEntity = new IssueEntity();
        JSONObject jsonObject = new JSONObject(json);

        // check & set name
        if (jsonObject.has("name")) {
            String name = jsonObject.getString("name");
            issueEntity.setName(name);
        }

        // check & set description
        if (jsonObject.has("description")) {
            String description = jsonObject.getString("description");
            issueEntity.setDescription(description);
        }

        // check & set assigneeId
        if (jsonObject.has("assignee_id")) {
            Integer assigneeId = jsonObject.getInt("assignee_id");
            issueEntity.setAssigneeId(assigneeId);
        }

        // check & set sprintId
        if (jsonObject.has("sprint_id")) {
            Integer sprintId = jsonObject.getInt("sprint_id");
            issueEntity.setSprintId(sprintId);
        }

        // check & set storyPoint
        if (jsonObject.has("story_point")) {
            Integer storyPoint = jsonObject.getInt("storyPoint");
            issueEntity.setReporterId(storyPoint);
        }

        // check & set type
        if (jsonObject.has("type")) {
            Integer type = jsonObject.getInt("type");
            issueEntity.setType(type);
        }

        // check & set reporterId
        UserEntity userEntity = userDao.getUserByUserName(DataUtils.getUsernameByToken());
        Integer reporterId = userEntity.getId();
        issueEntity.setReporterId(reporterId);

        // set priority
        issueEntity.setPriority(1);

        // set type
        issueEntity.setEntityType(0);

        // set entityType
        issueEntity.setEntityType(3);

        issueDao.createIssue(issueEntity);

        LOGGER.info("------createIssue END--------------");
        return new ResultBean(issueEntity, Constant.OK, MessageUtils.getMessage("MSG01", new Object[] { "Create issue" }));
    }

    @Override
    public ResultBean getIssue(String json) throws ApiValidateException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResultBean updateIssue(String json) throws ApiValidateException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResultBean deleteIssue(String json) throws ApiValidateException {
        // TODO Auto-generated method stub
        return null;
    }

}
