package com.dut.dao.impl;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dut.dao.UserProjectDao;
import com.dut.entity.UserProjectEntity;

@Transactional
@Repository
public class UserProjectDaoImpl implements UserProjectDao {

    private static final Logger LOGGER = LogManager.getLogger(UserProjectDaoImpl.class);

    @Autowired
    private EntityManager entityManager;

    @Override
    public void createUserProject(UserProjectEntity userProjectEntity) {
        LOGGER.info("------createUserProject START--------------");
        entityManager.persist(userProjectEntity);
        LOGGER.info("------createUserProject END--------------");

    }

    @Override
    public void updateUserProject(UserProjectEntity userProjectEntity) {
        LOGGER.info("------updateUserProject START--------------");
        entityManager.merge(userProjectEntity);
        LOGGER.info("------updateUserProject END--------------");

    }

}
