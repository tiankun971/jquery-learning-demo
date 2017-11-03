package com.github.kuntian.security.utils;

import org.springframework.security.core.context.SecurityContextHolder;

import com.github.kuntian.entity.SecurityUser;

public class SecurityUserHolder {

	public static SecurityUser getCurrentUser() {
		if (SecurityContextHolder.getContext() != null
				&& SecurityContextHolder.getContext().getAuthentication() != null) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof SecurityUser) {
				return (SecurityUser) principal;
			}
		}
		return null;
	}

}
