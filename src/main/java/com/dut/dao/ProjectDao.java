package com.dut.dao;

import java.util.List;

import com.dut.entity.ProjectEntity;

public interface ProjectDao {

    public void createProject(ProjectEntity projectEntity);

    public void updateProject(ProjectEntity projectEntity);

    public List<ProjectEntity> getProjectByUserId(Integer id);
}
