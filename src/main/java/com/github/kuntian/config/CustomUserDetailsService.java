package com.github.kuntian.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.github.kuntian.entity.SUser;
import com.github.kuntian.entity.SecurityUser;
import com.github.kuntian.service.SUserService;

public class CustomUserDetailsService implements UserDetailsService {
	@Autowired // 数据库服务类
	private SUserService suserService;// code7

	// @Autowired
	// private SUserRepository sUserRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// SUser对应数据库中的用户表，是最终存储用户和密码的表，可自定义
		SUser user = suserService.findUserByUsername(userName); // code8

		if (user == null) {

			throw new UsernameNotFoundException("UserName " + userName + " not found");

		}

		// SecurityUser实现UserDetails
		SecurityUser securityUser = new SecurityUser(user);

		// Collection<SimpleGrantedAuthority> authorities = new
		// ArrayList<SimpleGrantedAuthority>();
		// authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		return securityUser; // code9

	}

}
