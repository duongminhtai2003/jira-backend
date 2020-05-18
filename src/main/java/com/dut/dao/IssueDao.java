package com.dut.dao;

import com.dut.entity.IssueEntity;

public interface IssueDao {
    public void createIssue(IssueEntity issueEntity);

    public void editIssue(IssueEntity issueEntity);
}
