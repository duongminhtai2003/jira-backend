package com.dut.dao.impl;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dut.dao.IssueDao;
import com.dut.entity.IssueEntity;

@Transactional
@Repository
public class IssueDaoImpl implements IssueDao {
    private static final Logger LOGGER = LogManager.getLogger(IssueDaoImpl.class);

    @Autowired
    private EntityManager entityManager;

    @Override
    public void createIssue(IssueEntity issueEntity) {
        LOGGER.info("------createIssue START--------------");
        entityManager.persist(issueEntity);
        LOGGER.info("------createIssue END--------------");
    }

    @Override
    public void editIssue(IssueEntity issueEntity) {
        LOGGER.info("------issueEntity START--------------");
        entityManager.merge(issueEntity);
        LOGGER.info("------issueEntity END--------------");
    }

}
