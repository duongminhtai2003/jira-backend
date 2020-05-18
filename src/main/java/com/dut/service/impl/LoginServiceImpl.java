package com.dut.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.dut.bean.ResultBean;
import com.dut.constant.Constant;
import com.dut.constant.MessageUtils;
import com.dut.dao.PermissionDao;
import com.dut.dao.RoleDao;
import com.dut.dao.UserDao;
import com.dut.dto.JwtResponseDto;
import com.dut.dto.UserInfo;
import com.dut.entity.PermissionEntity;
import com.dut.entity.RoleEntity;
import com.dut.entity.UserEntity;
import com.dut.jwt.JwtTokenProvider;
import com.dut.service.LoginService;
import com.dut.utils.ApiValidateException;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserDao userDao;

    @Override
    public ResultBean login(String json) throws ApiValidateException {
        JSONObject jsonObject = new JSONObject(json);
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String tmp = "";
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            // Nếu không xảy ra exception tức là thông tin hợp lệ
            // Set thông tin authentication vào Security Context
            SecurityContextHolder.getContext().setAuthentication(authentication);
            tmp = tokenProvider.generateToken((UserDetails) authentication.getPrincipal());
        } catch (Exception e) {
            throw new ApiValidateException(Constant.INTERNAL_SERVER_ERROR, MessageUtils.getMessage("username or password not valid"));
        }

        JwtResponseDto jwtResponseDto = new JwtResponseDto();
        UserInfo userInfo = new UserInfo();
        Map<String, String> token = new HashMap<String, String>();
        
        UserEntity userEntity = userDao.getUserByUserName(username);
        List<PermissionEntity> permissions = permissionDao.getPermissionByUserId(userEntity.getId());
        List<RoleEntity> roles = roleDao.getUserRole(userEntity.getId());

        token.put("type", "Bearer");
        token.put("token", tmp);

        userInfo.setPermissions(permissions);
        userInfo.setUserEntity(userEntity);
        userInfo.setRoles(roles);

        jwtResponseDto.setToken(token);
        jwtResponseDto.setUserInfo(userInfo);

        return new ResultBean(jwtResponseDto, Constant.OK, MessageUtils.getMessage("Login success"));
    }
}
