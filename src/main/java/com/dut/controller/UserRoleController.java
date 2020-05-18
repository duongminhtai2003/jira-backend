package com.dut.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dut.bean.ResultBean;
import com.dut.constant.Constant;
import com.dut.service.UserRoleService;
import com.dut.utils.ApiValidateException;

@RestController
@RequestMapping(value = "/api")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    private static final Logger LOGGER = LogManager.getLogger(UserRoleController.class);

    @PostMapping(value = "/userrole", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> createUserRole(@RequestBody String json) {
        LOGGER.info("------createUserRole START--------------");
        ResultBean resultBean = null;
        try {
            resultBean = userRoleService.createUserRole(json);
        } catch (ApiValidateException e) {
            LOGGER.error("------createUserRole FAIL--------------");
            return new ResponseEntity<ResultBean>(new ResultBean(e.getCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.error("------createUserRole FAIL--------------");
            return new ResponseEntity<ResultBean>(new ResultBean(Constant.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        LOGGER.info("------createUserRole END--------------");
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @PutMapping(value = "/userrole", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> editUserRole(@RequestBody String json) {
        LOGGER.info("------createUserRole START--------------");
        ResultBean resultBean = null;
        try {
            resultBean = userRoleService.updateUserRole(json);
        } catch (ApiValidateException e) {
            LOGGER.error("------createUserRole FAIL--------------");
            return new ResponseEntity<ResultBean>(new ResultBean(e.getCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.error("------createUserRole FAIL--------------");
            return new ResponseEntity<ResultBean>(new ResultBean(Constant.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        LOGGER.info("------createUserRole END--------------");
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

}
