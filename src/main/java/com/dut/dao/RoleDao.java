package com.dut.dao;

import java.util.List;

import com.dut.entity.RoleEntity;

public interface RoleDao {
    public List<RoleEntity> getAll();

    public List<RoleEntity> getUserRole(Integer id);
}
