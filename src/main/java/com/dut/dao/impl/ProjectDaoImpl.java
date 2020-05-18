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

import com.dut.dao.ProjectDao;
import com.dut.entity.ProjectEntity;

@Transactional
@Repository
public class ProjectDaoImpl implements ProjectDao {

    private static final Logger LOGGER = LogManager.getLogger(ProjectDaoImpl.class);

    @Autowired
    private EntityManager entityManager;

    @Override
    public void createProject(ProjectEntity projectEntity) {
        LOGGER.info("------createProject START--------------");
        entityManager.persist(projectEntity);
        LOGGER.info("------createProject END--------------");
    }

    @Override
    public void updateProject(ProjectEntity projectEntity) {
        LOGGER.info("------updateProject START--------------");
        entityManager.merge(projectEntity);
        LOGGER.info("------updateProject END--------------");

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ProjectEntity> getProjectByUserId(Integer id) {
        LOGGER.info("------getProjectByUserId START--------------");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT p");
        sql.append(" FROM ");
        sql.append("    ProjectEntity p ");
        sql.append(" INNER JOIN UserProjectEntity AS up ON up.projectId = p.id ");
        sql.append(" INNER JOIN UserEntity AS u ON u.id = up.userId ");
        sql.append(" WHERE ");
        sql.append("    u.id = :id ");
        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("id", id);
        List<ProjectEntity> projects = null;
        try {
            projects = query.getResultList();
        } catch (NoResultException e) {
            LOGGER.error("------getProjectByUserId No Result--------------");
        }
        LOGGER.info("------getProjectByUserId END--------------");
        return projects;
    }

}
