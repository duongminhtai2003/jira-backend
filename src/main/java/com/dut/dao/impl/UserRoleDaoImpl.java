package com.dut.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dut.dao.UserRoleDao;
import com.dut.entity.UserRoleEntity;

@Transactional
@Repository
public class UserRoleDaoImpl implements UserRoleDao {

    private static final Logger LOGGER = LogManager.getLogger(UserRoleDaoImpl.class);

    @Autowired
    private EntityManager entityManager;

    @Override
    public void updateUserRole(UserRoleEntity userRoleEntity) {
        LOGGER.info("------updateUser START--------------");
        entityManager.merge(userRoleEntity);
        LOGGER.info("------updateUser END--------------");
    }

    @Override
    public void createUserRole(UserRoleEntity userRoleEntity) {
        LOGGER.info("------createUser START--------------");
        entityManager.persist(userRoleEntity);
        LOGGER.info("------createUser END--------------");
    }

    @Override
    public UserRoleEntity getUserRoleByUserIdAndRoleId(Integer userId, Integer roleId) {
        LOGGER.info("------getUserRoleByUserIdAndRoleId START--------------");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ur");
        sql.append(" FROM ");
        sql.append("    UserRoleEntity ur ");
        sql.append(" WHERE ");
        sql.append("    ur.userId = :userId ");
        sql.append(" AND ");
        sql.append("    ur.roleId = :roleId ");
        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("userId", userId);
        query.setParameter("roleId", roleId);
        UserRoleEntity userRoleEntity = null;
        try {
            userRoleEntity = (UserRoleEntity) query.getSingleResult();
        } catch (NoResultException e) {
            LOGGER.error("------getUserRoleByUserIdAndRoleId No Result--------------");
            return null;
        }
        LOGGER.info("------getUserRoleByUserIdAndRoleId END--------------");
        return userRoleEntity;
    }
}
