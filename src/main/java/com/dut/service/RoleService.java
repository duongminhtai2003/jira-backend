package com.dut.service;

import com.dut.bean.ResultBean;
import com.dut.utils.ApiValidateException;

public interface RoleService {
    public ResultBean getAll() throws ApiValidateException;
}
