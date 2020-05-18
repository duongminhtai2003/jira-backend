package com.dut.service;

import com.dut.bean.ResultBean;
import com.dut.utils.ApiValidateException;

public interface UserProjectService {
    public ResultBean addUserToProject(String json) throws ApiValidateException;

}
