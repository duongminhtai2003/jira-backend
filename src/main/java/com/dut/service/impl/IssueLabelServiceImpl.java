package com.dut.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dut.bean.ResultBean;
import com.dut.constant.Constant;
import com.dut.constant.MessageUtils;
import com.dut.dao.IssueLabelDao;
import com.dut.entity.IssueLabelEntity;
import com.dut.service.IssueLabelService;
import com.dut.utils.ApiValidateException;

@Service
public class IssueLabelServiceImpl implements IssueLabelService {

    private static final Logger LOGGER = LogManager.getLogger(IssueLabelServiceImpl.class);

    @Autowired
    private IssueLabelDao issueLabelDao;

    @Override
    public ResultBean addLabelToIssue(String json) {
        LOGGER.info("------addLabelToIssue START--------------");
        IssueLabelEntity issueLabelEntity = new IssueLabelEntity();
        JSONObject jsonObject = new JSONObject(json);

        // check label_id
        if (jsonObject.has("label_id")) {
            Integer labelId = jsonObject.getInt("label_id");
            issueLabelEntity.setLabelId(labelId);
        }

        // check issue_id 
        if (jsonObject.has("issue_id")) {
            Integer issueId = jsonObject.getInt("issue_id");
            issueLabelEntity.setIssueId(issueId);
        }
        issueLabelDao.createIssueLabel(issueLabelEntity);

        LOGGER.info("------addLabelToIssue END--------------");
        return new ResultBean(issueLabelEntity, Constant.OK, MessageUtils.getMessage("MSG01", new Object[] { "Create issue label" }));
    }

    @Override
    public ResultBean updateLabelInIssue(String json) throws ApiValidateException {
        // TODO Auto-generated method stub
        return null;
    }

}
