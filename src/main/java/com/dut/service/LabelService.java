package com.dut.service;

import com.dut.bean.ResultBean;

public interface LabelService {
    public ResultBean createLabel(String json);

    public ResultBean getLabel(String json);

    public ResultBean updateLabel(String json);

    public ResultBean deleteLabel(String json);
}
