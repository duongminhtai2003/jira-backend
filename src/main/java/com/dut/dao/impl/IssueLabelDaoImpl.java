package com.dut.dao.impl;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dut.dao.IssueLabelDao;
import com.dut.entity.IssueLabelEntity;
import com.dut.entity.UserProjectEntity;

@Transactional
@Repository
public class IssueLabelDaoImpl implements IssueLabelDao {

    private static final Logger LOGGER = LogManager.getLogger(IssueLabelDaoImpl.class);

    @Autowired
    private EntityManager entityManager;

    @Override
    public void createIssueLabel(IssueLabelEntity issueLabelEntity) {
        LOGGER.info("------createIssueLabel START--------------");
        entityManager.persist(issueLabelEntity);
        LOGGER.info("------createIssueLabel END--------------");

    }

    @Override
    public void updateIssueLabel(UserProjectEntity issueLabelEntity) {
        LOGGER.info("------updateIssueLabel START--------------");
        entityManager.merge(issueLabelEntity);
        LOGGER.info("------updateIssueLabel END--------------");

    }

}
