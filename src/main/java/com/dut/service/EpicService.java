package com.dut.service;

import com.dut.bean.ResultBean;
import com.dut.utils.ApiValidateException;

public interface EpicService {
    public ResultBean createEpic(String json) throws ApiValidateException;

    public ResultBean getEpic(String json) throws ApiValidateException;

    public ResultBean updateEpic(String json) throws ApiValidateException;

    public ResultBean deleteEpic(String json) throws ApiValidateException;
}
