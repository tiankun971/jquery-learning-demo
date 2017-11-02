package com.github.kuntian.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.kuntian.dao.SResourceRepository;
import com.github.kuntian.dao.SRoleRepository;
import com.github.kuntian.dao.SUserRepository;
import com.github.kuntian.dao.SUserRoleRepository;
import com.github.kuntian.entity.SResource;
import com.github.kuntian.entity.SRole;
import com.github.kuntian.entity.SUser;
import com.github.kuntian.entity.SUserRole;
import com.github.kuntian.service.SUserService;
import com.mysql.jdbc.StringUtils;

@Service
public class SUserServiceImpl implements SUserService {

	@Autowired
	private SUserRepository sUserRepository;// code10
	@Autowired
	private SResourceRepository rSRRepository;

	@Autowired
	private SRoleRepository sRoleRepository;
	@Autowired
	private SUserRoleRepository rSURRepository;

	public List<SUser> findAll() {

		return sUserRepository.findAll();

	}

	public SUser create(SUser user) {
		return sUserRepository.save(user);

	}

	public SUser findUserById(int id) {
		return sUserRepository.findOne(id);

	}

	public SUser login(String email, String password) {
		return sUserRepository.findByEmailAndPassword(email, password);

	}

	public SUser update(SUser user) {
		return sUserRepository.save(user);

	}

	public void deleteUser(int id) {
		sUserRepository.delete(id);
	}

	public SUser findUserByEmail(String email) {
		return sUserRepository.findUserByEmail(email);

	}

	public SUser findUserByUsername(String username) {
		return sUserRepository.findUserByUsername(username);
	}

	@Override
	public List<SUser> getUserByRealName(String name) {
		return sUserRepository.getUserByRealName(name);
	}

	@Transactional
	@Override
	public void delUsers(String ids) {
		if (!StringUtils.isNullOrEmpty(ids)) {
			String[] userIds = ids.split(",");
			List<Integer> list = new ArrayList<Integer>();
			for (String id : userIds) {
				list.add(Integer.valueOf(id));
			}
			sUserRepository.disableUser(list);
		}

	}

	@Override
	public List<SResource> getSelfResource(Integer id) {
		return rSRRepository.getSelfResource(id);
	}

	@Override
	public List<SRole> getUserRelatedRole(Integer id) {
		return sRoleRepository.getUserRelatedRole(id);
	}

	@Override
	public List<SRole> getUserUnRelateRole(Integer id) {
		return sRoleRepository.getUserUnRelateRole(id);
	}

	@Transactional
	@Override
	public void addUserRoles(List<SUserRole> sroles, Integer userId) {
		rSURRepository.delUserRolesByUser(userId);
		rSURRepository.save(sroles);
	}
}
