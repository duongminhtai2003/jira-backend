package com.dut.service;

import com.dut.bean.ResultBean;
import com.dut.utils.ApiValidateException;

public interface ProjectService {
    public ResultBean createProject(String json) throws ApiValidateException;

    public ResultBean getProject(String json) throws ApiValidateException;

    public ResultBean updateProject(String json) throws ApiValidateException;

    public ResultBean deleteProject(String json) throws ApiValidateException;
}