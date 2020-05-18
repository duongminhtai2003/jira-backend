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
import com.dut.dao.EpicDao;
import com.dut.entity.EpicEntity;
import com.dut.service.EpicService;
import com.dut.utils.ApiValidateException;

@Service
public class EpicServiceImpl implements EpicService {

    private static final Logger LOGGER = LogManager.getLogger(EpicServiceImpl.class);

    @Autowired
    private EpicDao epicDao;

    @Override
    public ResultBean createEpic(String json) throws ApiValidateException {
        LOGGER.info("------createEpic START--------------");

        EpicEntity epicEntity = new EpicEntity();
        JSONObject jsonObject = new JSONObject(json);

        // check & set name
        if (jsonObject.has("name")) {
            String name = jsonObject.getString("name");
            epicEntity.setName(name);
        }

        // check & set description
        if (jsonObject.has("description")) {
            String description = jsonObject.getString("description");
            epicEntity.setDescription(description);
        }

        // check & set projectId
        if (jsonObject.has("project_id")) {
            Integer projectId = jsonObject.getInt("project_id");
            epicEntity.setProjectId(projectId);
        }

        // set startDate
        epicEntity.setStartDate(new Date());

        // set endDate
        epicEntity.setEndDate(null);

        // set isSuccess
        epicEntity.setIsSuccess(false);

        // set entityType
        epicEntity.setEntityType(1);

        epicDao.createEpic(epicEntity);

        LOGGER.info("------createEpic END--------------");
        return new ResultBean(epicEntity, Constant.OK, MessageUtils.getMessage("MSG01", new Object[] { "Create epic" }));
    }

    @Override
    public ResultBean getEpic(String json) throws ApiValidateException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResultBean updateEpic(String json) throws ApiValidateException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResultBean deleteEpic(String json) throws ApiValidateException {
        // TODO Auto-generated method stub
        return null;
    }

}
