package com.dut.dao.impl;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dut.dao.EpicDao;
import com.dut.entity.EpicEntity;

@Transactional
@Repository
public class EpicDaoImpl implements EpicDao {
    private static final Logger LOGGER = LogManager.getLogger(EpicDaoImpl.class);

    @Autowired
    private EntityManager entityManager;

    @Override
    public void createEpic(EpicEntity epicEntity) {
        LOGGER.info("------createEpic START--------------");
        entityManager.persist(epicEntity);
        LOGGER.info("------createEpic END--------------");

    }

    @Override
    public void updateEpic(EpicEntity epicEntity) {
        LOGGER.info("------updateEpic START--------------");
        entityManager.merge(epicEntity);
        LOGGER.info("------updateEpic END--------------");

    }

}
