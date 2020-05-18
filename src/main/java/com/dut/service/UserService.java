package com.dut.service;

import com.dut.bean.ResultBean;
import com.dut.utils.ApiValidateException;

public interface UserService {
    public ResultBean createUser(String json) throws ApiValidateException;

    public ResultBean getUserById(Integer id) throws ApiValidateException;

    public ResultBean getAll() throws ApiValidateException;

    public ResultBean updateUser(String json) throws ApiValidateException;

    public ResultBean deleteUser(String json) throws ApiValidateException;

}
