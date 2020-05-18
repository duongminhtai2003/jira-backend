package com.dut.service;

import com.dut.bean.ResultBean;
import com.dut.utils.ApiValidateException;

public interface SprintService {
    public ResultBean createSprint(String json) throws ApiValidateException;

    public ResultBean updateSprint(String json) throws ApiValidateException;

}
