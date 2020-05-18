/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.dut.service.impl;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dut.dao.PermissionDao;
import com.dut.dao.UserDao;
import com.dut.entity.PermissionEntity;
import com.dut.entity.UserEntity;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userLogin = userDao.getUserByUserName(username);
        if (Objects.isNull(userLogin)) {
            throw new UsernameNotFoundException(username);
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (PermissionEntity permission : permissionDao.getPermissionByUserId(userLogin.getId())) {
            authorities.add(new SimpleGrantedAuthority("SCOPE_" + permission.getScope()));
        }
        return new User(userLogin.getUsername(), userLogin.getPassword(), authorities);
    }
}
