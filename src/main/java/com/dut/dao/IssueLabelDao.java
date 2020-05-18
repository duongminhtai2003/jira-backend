package com.dut.dao;

import com.dut.entity.IssueLabelEntity;
import com.dut.entity.UserProjectEntity;

public interface IssueLabelDao {
    public void createIssueLabel(IssueLabelEntity issueLabelEntity);

    public void updateIssueLabel(UserProjectEntity issueLabelEntity);
}
