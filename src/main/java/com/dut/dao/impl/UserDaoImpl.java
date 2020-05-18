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

import com.dut.dao.UserDao;
import com.dut.entity.UserEntity;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

    @Autowired
    private EntityManager entityManager;

    @Override
    public UserEntity getUserById(Integer id) {
        LOGGER.info("------getUserById START--------------");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT u");
        sql.append(" FROM ");
        sql.append("    UserEntity u ");
        sql.append(" WHERE ");
        sql.append("    u.id = :id ");
        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("id", id);
        UserEntity userEntity = null;
        try {
            userEntity = (UserEntity) query.getSingleResult();
        } catch (NoResultException e) {
            LOGGER.error("------getUserById No Result--------------");
        }
        LOGGER.info("------getUserById END--------------");
        return userEntity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserEntity> getAll() {
        LOGGER.info("------getAll START--------------");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT u");
        sql.append(" FROM ");
        sql.append("    UserEntity u ");
        sql.append(" INNER JOIN UserRoleEntity ur ON ur.userId = u.id ");
        sql.append(" INNER JOIN RoleEntity r ON r.id = ur.roleId ");
        sql.append(" WHERE ");
        sql.append("    r.name <> 'ADMIN' ");
        Query query = this.entityManager.createQuery(sql.toString());
        List<UserEntity> listUsers = null;
        try {
            listUsers = query.getResultList();
        } catch (NoResultException e) {
            LOGGER.error("------getAll No Result--------------");
        }
        LOGGER.info("------getAll END--------------");
        return listUsers;
    }

    @Override
    public void createUser(UserEntity userEntity) {
        LOGGER.info("------createUser START--------------");
        entityManager.persist(userEntity);
        LOGGER.info("------createUser END--------------");
    }

    @Override
    public void updateUser(UserEntity userEntity) {
        LOGGER.info("------updateUser START--------------");
        entityManager.merge(userEntity);
        LOGGER.info("------updateUser END--------------");
    }

    @Override
    public UserEntity getUserByUserName(String username) {
        LOGGER.info("------getUserByUserName START--------------");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT u");
        sql.append(" FROM ");
        sql.append("    UserEntity u ");
        sql.append(" WHERE ");
        sql.append("    u.username = :username ");
        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("username", username);
        UserEntity userEntity = null;
        try {
            userEntity = (UserEntity) query.getSingleResult();
        } catch (NoResultException e) {
            LOGGER.error("------getUserByUserName No Result--------------");
        }
        LOGGER.info("------getUserByUserName END--------------");
        return userEntity;
    }

    @Override
    public boolean checkUsername(String username) {
        LOGGER.info("------checkUsername START--------------");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT u");
        sql.append(" FROM ");
        sql.append("    UserEntity u ");
        sql.append(" WHERE ");
        sql.append("    u.username = :username ");
        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("username", username);
        if (query.getResultList().isEmpty()) {
            return true;
        }
        LOGGER.info("------checkUsername END--------------");
        return false;
    }

}