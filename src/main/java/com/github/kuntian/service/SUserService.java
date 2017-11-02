package com.github.kuntian.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.kuntian.entity.SResource;
import com.github.kuntian.entity.SRole;
import com.github.kuntian.entity.SUser;
import com.github.kuntian.entity.SUserRole;

@Service
public interface SUserService {

	public List<SUser> findAll();

	public SUser create(SUser user);

	public SUser findUserById(int id);

	public SUser login(String email, String password);

	public SUser update(SUser user);

	public void deleteUser(int id);

	public SUser findUserByEmail(String email);

	public SUser findUserByUsername(String username);

	List<SUser> getUserByRealName(String name);

	void delUsers(String ids);

	List<SResource> getSelfResource(Integer id);

	List<SRole> getUserRelatedRole(Integer id);

	List<SRole> getUserUnRelateRole(Integer id);

	void addUserRoles(List<SUserRole> sroles, Integer userId);
}
