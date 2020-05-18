package com.dut.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dut.bean.ResultBean;
import com.dut.constant.Constant;
import com.dut.service.UserService;
import com.dut.utils.ApiValidateException;

@RestController
@RequestMapping(value = "/api")
public class UserController {
    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    @PostMapping(value = "/user", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> createUser(@RequestBody String json) {
        LOGGER.info("------createUser START--------------");
        ResultBean resultBean = null;
        try {
            resultBean = userService.createUser(json);
        } catch (ApiValidateException e) {
            LOGGER.error("------createUser FAIL--------------");
            return new ResponseEntity<ResultBean>(new ResultBean(e.getCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.error("------createUser FAIL--------------");
            return new ResponseEntity<ResultBean>(new ResultBean(Constant.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        LOGGER.info("------createUser END--------------");
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getUserById(@PathVariable Integer id) {
        LOGGER.info("------getUser START--------------");
        ResultBean resultBean = null;
        try {
            resultBean = userService.getUserById(id);
        } catch (ApiValidateException e) {
            LOGGER.error("------getUser FAIL--------------");
            return new ResponseEntity<ResultBean>(new ResultBean(e.getCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.error("------getUser FAIL--------------");
            return new ResponseEntity<ResultBean>(new ResultBean(Constant.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        LOGGER.info("------getUser END--------------");
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @GetMapping(value = "/user", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getAllUser() {
        LOGGER.info("------getAllUser START--------------");
        ResultBean resultBean = null;
        try {
            resultBean = userService.getAll();
        } catch (ApiValidateException e) {
            LOGGER.error("------getAllUser FAIL--------------");
            return new ResponseEntity<ResultBean>(new ResultBean(e.getCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.error("------getAllUser FAIL--------------");
            return new ResponseEntity<ResultBean>(new ResultBean(Constant.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        LOGGER.info("------getAllUser END--------------");
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @PutMapping(value = "/user", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> updateUser(@RequestBody String json) {
        LOGGER.info("------updateUser START--------------");
        ResultBean resultBean = null;
        try {
            resultBean = userService.updateUser(json);
        } catch (ApiValidateException e) {
            LOGGER.error("------updateUser FAIL--------------");
            return new ResponseEntity<ResultBean>(new ResultBean(e.getCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.error("------updateUser FAIL--------------");
            return new ResponseEntity<ResultBean>(new ResultBean(Constant.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        LOGGER.info("------updateUser END--------------");
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }
}
