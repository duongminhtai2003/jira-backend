package com.dut.dao;

import java.util.List;

import com.dut.entity.PermissionEntity;

public interface PermissionDao {
    public List<PermissionEntity> getPermissionByUserId(Integer id);
}
