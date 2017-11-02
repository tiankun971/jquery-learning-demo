package com.github.kuntian.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SecurityUser extends SUser implements UserDetails {

	private static final long serialVersionUID = 1L;

	public SecurityUser(SUser suser) {

		if (suser != null)

		{

			this.setId(suser.getId());

			this.setUsername(suser.getUsername());

			this.setRealname(suser.getUsername());

			this.setEmail(suser.getEmail());

			this.setPassword(suser.getPassword());

			this.setDob(suser.getDob());

			this.setLastPasswordResetDate(suser.getLastPasswordResetDate());

			this.setEnabled(suser.isEnabled());

			this.setAccountNonLocked(suser.isAccountNonLocked());

			this.setAccountNonExpired(suser.isAccountNonExpired());

			this.setCredentialsNonExpired(suser.isCredentialsNonExpired());

			this.setSRoleSet(suser.getSRoleSet());

		}

	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		Set<SRole> userRoles = this.getSRoleSet();

		if (userRoles != null)

		{

			for (SRole role : userRoles) {

				SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());

				authorities.add(authority);

			}

		}

		return authorities;

	}

	@JsonIgnore
	@Override
	public String getPassword() {

		return super.getPassword();

	}

	@Override
	public String getUsername() {

		return super.getUsername();

	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {

		return super.isAccountNonExpired();

	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {

		return super.isAccountNonLocked();

	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {

		return super.isCredentialsNonExpired();

	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {

		return super.isEnabled();

	}
}