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

import com.dut.dao.RoleDao;
import com.dut.entity.RoleEntity;

@Transactional
@Repository
public class RoleDaoImpl implements RoleDao {

    private static final Logger LOGGER = LogManager.getLogger(RoleDaoImpl.class);

    @Autowired
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<RoleEntity> getAll() {
        LOGGER.info("------getRole START--------------");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT r");
        sql.append(" FROM ");
        sql.append("    RoleEntity r ");
        Query query = this.entityManager.createQuery(sql.toString());
        List<RoleEntity> roles = null;
        try {
            roles = query.getResultList();
        } catch (NoResultException e) {
            LOGGER.error("------getRole No Result--------------");
            return null;
        }
        LOGGER.info("------getRole END--------------");
        return roles;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<RoleEntity> getUserRole(Integer id) {
        LOGGER.info("------getUserRole START--------------");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT r");
        sql.append(" FROM ");
        sql.append("    RoleEntity r ");
        sql.append(" INNER JOIN UserRoleEntity ur ON ur.roleId = r.id ");
        sql.append(" INNER JOIN UserEntity u ON u.id = ur.userId ");
        sql.append(" WHERE ");
        sql.append("    u.id = :id");
        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("id", id);
        List<RoleEntity> roles = null;
        try {
            roles = query.getResultList();
        } catch (NoResultException e) {
            LOGGER.error("------getUserRole No Result--------------");
            return null;
        }
        LOGGER.info("------getUserRole END--------------");
        return roles;
    }
}
