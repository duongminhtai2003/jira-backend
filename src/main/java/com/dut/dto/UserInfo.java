package com.dut.dto;

import java.util.List;

import com.dut.entity.PermissionEntity;
import com.dut.entity.RoleEntity;
import com.dut.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private UserEntity userEntity;
    private List<RoleEntity> roles;
    private List<PermissionEntity> permissions;

}
