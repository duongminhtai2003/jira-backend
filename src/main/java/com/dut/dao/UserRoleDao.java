package com.dut.dao;

import com.dut.entity.UserRoleEntity;

public interface UserRoleDao {
    public void updateUserRole(UserRoleEntity userRoleEntity);

    public void createUserRole(UserRoleEntity userRoleEntity);

    public UserRoleEntity getUserRoleByUserIdAndRoleId(Integer userId, Integer roleId);
}
