package com.dut.service;

import com.dut.bean.ResultBean;
import com.dut.utils.ApiValidateException;

public interface IssueLabelService {
    public ResultBean addLabelToIssue(String json) throws ApiValidateException;

    public ResultBean updateLabelInIssue(String json) throws ApiValidateException;
}
