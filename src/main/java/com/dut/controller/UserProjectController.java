package com.dut.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dut.bean.ResultBean;
import com.dut.constant.Constant;
import com.dut.service.UserProjectService;
import com.dut.utils.ApiValidateException;

@RestController
@RequestMapping(value = "/api")
public class UserProjectController {

    @Autowired
    private UserProjectService userProjectService;

    private static final Logger LOGGER = LogManager.getLogger(UserProjectController.class);

    @PostMapping(value = "/userprojet", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> addUserToProject(@RequestBody String json) {
        LOGGER.info("------addUserToProject START--------------");
        ResultBean resultBean = null;
        try {
            resultBean = userProjectService.addUserToProject(json);
        } catch (ApiValidateException e) {
            LOGGER.error("------addUserToProject FAIL--------------");
            return new ResponseEntity<ResultBean>(new ResultBean(e.getCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.error("------addUserToProject FAIL--------------");
            return new ResponseEntity<ResultBean>(new ResultBean(Constant.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        LOGGER.info("------addUserToProject END--------------");
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

}