package com.dut.dao.impl;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dut.dao.SprintDao;
import com.dut.entity.SprintEntity;

@Transactional
@Repository
public class SprintDaoImpl implements SprintDao {
    private static final Logger LOGGER = LogManager.getLogger(SprintDaoImpl.class);

    @Autowired
    private EntityManager entityManager;

    @Override
    public void createSprint(SprintEntity sprintEntity) {
        LOGGER.info("------createSprint START--------------");
        entityManager.persist(sprintEntity);
        LOGGER.info("------createSprint END--------------");

    }

    @Override
    public void updateSprint(SprintEntity sprintEntity) {
        LOGGER.info("------updateSprint START--------------");
        entityManager.merge(sprintEntity);
        LOGGER.info("------updateSprint END--------------");

    }

}
