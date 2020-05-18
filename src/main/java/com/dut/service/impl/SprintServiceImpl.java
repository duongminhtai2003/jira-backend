package com.dut.service.impl;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dut.bean.ResultBean;
import com.dut.constant.Constant;
import com.dut.constant.MessageUtils;
import com.dut.dao.SprintDao;
import com.dut.entity.SprintEntity;
import com.dut.service.SprintService;

@Service
public class SprintServiceImpl implements SprintService {

    private static final Logger LOGGER = LogManager.getLogger(SprintServiceImpl.class);

    @Autowired
    private SprintDao sprintDao;

    @Override
    public ResultBean createSprint(String json) {
        LOGGER.info("------createSprint START--------------");

        SprintEntity sprintEntity = new SprintEntity();
        JSONObject jsonObject = new JSONObject(json);

        // check & set name
        if (jsonObject.has("name")) {
            String name = jsonObject.getString("name");
            sprintEntity.setName(name);
        }

        // check & set description
        if (jsonObject.has("description")) {
            String description = jsonObject.getString("description");
            sprintEntity.setDescription(description);
        }

        // check & set totalStoryPoint
        if (jsonObject.has("total_story_point")) {
            Integer totalStoryPoint = jsonObject.getInt("total_story_point");
            sprintEntity.setTotalStoryPoint(totalStoryPoint);
        }

        // check & set epicId
        if (jsonObject.has("epic_id")) {
            Integer epicId = jsonObject.getInt("epic_id");
            sprintEntity.setEpicId(epicId);
        }

        // set startTime
        sprintEntity.setStartTime(new Date());

        // set finishTime
        sprintEntity.setFinishTime(null);

        // set isSuccess
        sprintEntity.setStatus(0);

        // set entityType
        sprintEntity.setEntityType(2);

        sprintDao.createSprint(sprintEntity);

        LOGGER.info("------createSprint END--------------");
        return new ResultBean(sprintEntity, Constant.OK, MessageUtils.getMessage("MSG01", new Object[] { "Create sprint" }));
    }

    @Override
    public ResultBean updateSprint(String json) {
        // TODO Auto-generated method stub
        return null;
    }

}
