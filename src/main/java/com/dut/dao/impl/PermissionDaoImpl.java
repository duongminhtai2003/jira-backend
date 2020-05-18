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

import com.dut.dao.PermissionDao;
import com.dut.entity.PermissionEntity;

@Transactional
@Repository
public class PermissionDaoImpl implements PermissionDao {

    private static final Logger LOGGER = LogManager.getLogger(PermissionDaoImpl.class);

    @Autowired
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<PermissionEntity> getPermissionByUserId(Integer id) {
        LOGGER.info("------getPermissionByUsername START--------------");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT p ");
        sql.append(" FROM ");
        sql.append("    PermissionEntity p ");
        sql.append(" INNER JOIN RolePermissionEntity rp ON rp.permissionId = p.id ");
        sql.append(" INNER JOIN RoleEntity r ON r.id = rp.roleId ");
        sql.append(" INNER JOIN UserRoleEntity AS ur ON ur.roleId = r.id ");
        sql.append(" INNER JOIN UserEntity AS u ON u.id = ur.userId ");
        sql.append(" WHERE ");
        sql.append("    u.id = :id");
        sql.append(" GROUP BY p.scope ");
        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("id", id);
        List<PermissionEntity> permissions = null;
        try {
            permissions = query.getResultList();
        } catch (NoResultException e) {
            LOGGER.error("------getPermissionByUsername No Result--------------");
            return null;
        }
        LOGGER.info("------getPermissionByUsername END--------------");
        return permissions;
    }

}
