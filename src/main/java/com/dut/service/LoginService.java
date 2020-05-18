package com.dut.service;

import com.dut.bean.ResultBean;
import com.dut.utils.ApiValidateException;

public interface LoginService {
    public ResultBean login(String json) throws ApiValidateException;
}
