package com.dut.service;

import com.dut.bean.ResultBean;
import com.dut.utils.ApiValidateException;

public interface IssueService {
    public ResultBean createIssue(String json) throws ApiValidateException;

    public ResultBean getIssue(String json) throws ApiValidateException;

    public ResultBean updateIssue(String json) throws ApiValidateException;

    public ResultBean deleteIssue(String json) throws ApiValidateException;
}
