
package com.dut.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dut.jwt.JwtAuthenticationFilter;
import com.dut.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userService;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // Get AuthenticationManager bean
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Password encoder, để Spring Security sử dụng mã hóa mật khẩu người dùng
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // Ngăn chặn request từ một domain khác
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/login").permitAll()
                .antMatchers(HttpMethod.POST, "/api/user").hasAnyAuthority("SCOPE_WRITE_USER")
                .antMatchers(HttpMethod.PUT, "/api/user").hasAnyAuthority("SCOPE_WRITE_USER")
                .antMatchers(HttpMethod.POST, "/api/userrole").hasAnyAuthority("SCOPE_WRITE_ROLE")
                .antMatchers(HttpMethod.PUT, "/api/userrole/").hasAnyAuthority("SCOPE_WRITE_ROLE")
                .antMatchers(HttpMethod.POST, "/api/project").hasAnyAuthority("SCOPE_WRITE_PROJECT")
                .antMatchers(HttpMethod.POST, "/api/userproject").hasAnyAuthority("SCOPE_WRITE_PROJECT")
                .antMatchers(HttpMethod.POST, "/api/epic").hasAnyAuthority("SCOPE_WRITE_EPIC")
                .antMatchers(HttpMethod.POST, "/api/sprint").hasAnyAuthority("SCOPE_WRITE_SPRINT")
                .antMatchers(HttpMethod.POST, "/api/issue").hasAnyAuthority("SCOPE_WRITE_ISSUE")
                .anyRequest().authenticated();
        // Tất cả các request khác đều cần phải xác thực mới được truy cập
        // Thêm một lớp Filter kiểm tra jwt
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    // truy voo db,  get roleName = dev -> url
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
}
