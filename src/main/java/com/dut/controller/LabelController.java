package com.dut.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dut.bean.ResultBean;
import com.dut.constant.Constant;
import com.dut.service.LabelService;
import com.dut.utils.ApiValidateException;

@RestController
@RequestMapping(value = "/api")
public class LabelController {
    @Autowired
    private LabelService labelService;

    private static final Logger LOGGER = LogManager.getLogger(LabelController.class);

    @GetMapping(value = "/label", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getAll() {
        LOGGER.info("------getAll START--------------");
        ResultBean resultBean = null;
        try {
            resultBean = labelService.getAll();
        } catch (ApiValidateException e) {
            LOGGER.error("------getAll FAIL--------------");
            return new ResponseEntity<ResultBean>(new ResultBean(e.getCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.error("------getAll FAIL--------------");
            return new ResponseEntity<ResultBean>(new ResultBean(Constant.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        LOGGER.info("------getAll END--------------");
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

}
