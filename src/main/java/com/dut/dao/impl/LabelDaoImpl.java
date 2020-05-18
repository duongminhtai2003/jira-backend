package com.dut.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dut.dao.LabelDao;
import com.dut.entity.LabelEntity;

@Transactional
@Repository
public class LabelDaoImpl implements LabelDao {

    private static final Logger LOGGER = LogManager.getLogger(LabelDaoImpl.class);

    @Autowired
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<LabelEntity> getAll() {
        LOGGER.info("------getAll START--------------");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT l ");
        sql.append(" FROM ");
        sql.append("    LabelEntity l ");
        Query query = this.entityManager.createQuery(sql.toString());
        List<LabelEntity> labels = null;
        try {
            labels = query.getResultList();
        } catch (NoResultException e) {
            LOGGER.error("------getAll No Result--------------");
            return null;
        }
        LOGGER.info("------getAll END--------------");
        return labels;
    }
}
