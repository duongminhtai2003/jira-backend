package com.dut.service;

import com.dut.bean.ResultBean;
import com.dut.utils.ApiValidateException;

public interface UserRoleService {
    public ResultBean createUserRole(String json) throws ApiValidateException;

    public ResultBean updateUserRole(String json) throws ApiValidateException;

}
