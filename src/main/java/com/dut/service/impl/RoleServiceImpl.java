package com.dut.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dut.bean.ResultBean;
import com.dut.constant.Constant;
import com.dut.constant.MessageUtils;
import com.dut.dao.RoleDao;
import com.dut.entity.RoleEntity;
import com.dut.service.RoleService;
import com.dut.utils.ApiValidateException;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    private static final Logger LOGGER = LogManager.getLogger(RoleServiceImpl.class);

    @Override
    public ResultBean getAll() throws ApiValidateException {
        LOGGER.info("------getAll START--------------");
        List<RoleEntity> roles = roleDao.getAll();
        LOGGER.info("------getAll START--------------");
        return new ResultBean(roles, Constant.OK, MessageUtils.getMessage("MSG01", "Get roles"));
    }

}
