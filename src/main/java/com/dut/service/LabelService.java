package com.dut.service;

import com.dut.bean.ResultBean;
import com.dut.utils.ApiValidateException;

public interface LabelService {
    public ResultBean getAll() throws ApiValidateException;
}
