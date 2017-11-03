package com.github.kuntian.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.kuntian.constant.Constants;
import com.github.kuntian.entity.SResource;
import com.github.kuntian.entity.SRole;
import com.github.kuntian.entity.SUser;
import com.github.kuntian.entity.SUserRole;
import com.github.kuntian.security.utils.SecurityUserHolder;
import com.github.kuntian.service.SUserService;
import com.github.kuntian.utils.DateUtils;
import com.mysql.jdbc.StringUtils;

/**
 * 在 @PreAuthorize 中我们可以利用内建的 SPEL 表达式：比如 'hasRole()' 来决定哪些用户有权访问。 需注意的一点是
 * hasRole 表达式认为每个角色名字前都有一个前缀 'ROLE_'。所以这里的 'ADMIN' 其实在 数据库中存储的是 'ROLE_ADMIN'
 * 。这个 @PreAuthorize 可以修饰Controller也可修饰Controller中的方法。
 **/
@RestController
@RequestMapping("/users")
public class UserController {

	private static final Log logger = LogFactory.getLog(UserController.class);

	@Autowired
	private SUserService sUserServiceImpl;

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public List<SUser> getUsers() {
		return sUserServiceImpl.findAll();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	SUser addUser(@RequestBody SUser addedUser) {
		return sUserServiceImpl.create(addedUser);
	}

	@PostAuthorize("returnObject.username == principal.username or hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public SUser getUser(@PathVariable Integer id) {
		return sUserServiceImpl.findUserById(id);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	SUser updateUser(@PathVariable Integer id, @RequestBody SUser updatedUser) {
		updatedUser.setId(id);
		return sUserServiceImpl.update(updatedUser);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	SUser removeUser(@PathVariable Integer id) {
		SUser deletedUser = sUserServiceImpl.findUserById(id);
		sUserServiceImpl.deleteUser(id);
		return deletedUser;
	}

	@PostAuthorize("returnObject.username == principal.username or hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public SUser getUserByUsername(@RequestParam(value = "username") String username) {
		return sUserServiceImpl.findUserByUsername(username);
	}

	@RequestMapping(value = "getSelfUserProfile", method = RequestMethod.POST)
	public SUser getSelfUserProfile() {
		String username = SecurityUserHolder.getCurrentUser().getUsername();
		return sUserServiceImpl.findUserByUsername(username);
	}

	@RequestMapping(value = "getUserByRealName", method = RequestMethod.POST)
	public Map<String, Object> getUserByRealName(String realName) {
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("status", Constants.RET_SUC);
		try {
			List<SUser> list = sUserServiceImpl.getUserByRealName(realName);
			ret.put("data", list);
		} catch (Exception e) {
			logger.error("查询用户失败：" + e.getMessage());
			ret.put("status", Constants.RET_ERR);
			ret.put("errorMessage", "查询用户失败：" + e.getMessage());
			return ret;
		}
		return ret;
	}

	@RequestMapping(value = "createUser", method = RequestMethod.POST)
	public Map<String, Object> createUser(SUser user) {

		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("status", Constants.RET_SUC);
		try {
			SUser ou = sUserServiceImpl.findUserByUsername(user.getUsername());
			if (ou != null) {
				logger.error("创建用户失败：账户名重复");
				ret.put("status", Constants.RET_ERR);
				ret.put("errorMessage", "创建用户失败：账户名重复");
				return ret;
			}
			user.setCreateUser(SecurityUserHolder.getCurrentUser().getUsername());
			user.setUpdateUser(SecurityUserHolder.getCurrentUser().getUsername());
			user.setDob(DateUtils.getDate(user.getDobStr(), DateUtils.format));
			user = sUserServiceImpl.create(user);
			ret.put("data", user);
		} catch (Exception e) {
			logger.error(" 创建用户失败：" + e.getMessage());
			ret.put("status", Constants.RET_ERR);
			ret.put("errorMessage", "创建用户失败：" + e.getMessage());
			return ret;
		}
		return ret;
	}

	@RequestMapping(value = "updateUser", method = RequestMethod.POST)
	public Map<String, Object> updateUser(SUser user) {

		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("status", Constants.RET_SUC);
		try {
			SUser ou = sUserServiceImpl.findUserById(user.getId());
			ou.setUpdateUser(SecurityUserHolder.getCurrentUser().getUsername());
			ou.setDob(user.getDob());
			ou.setEmail(user.getEmail());
			ou.setRealname(user.getRealname());
			ou.setDob(DateUtils.getDate(user.getDobStr(), DateUtils.format));
			user = sUserServiceImpl.update(ou);
			ret.put("data", user);
		} catch (Exception e) {
			logger.error("更新用户失败：" + e.getMessage());
			ret.put("status", Constants.RET_ERR);
			ret.put("errorMessage", "更新用户失败：" + e.getMessage());
			return ret;
		}
		return ret;
	}

	@RequestMapping(value = "delUsers", method = RequestMethod.POST)
	public Map<String, Object> delRoles(String ids) {

		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("status", Constants.RET_SUC);
		try {
			if (!StringUtils.isNullOrEmpty(ids)) {
				sUserServiceImpl.delUsers(ids);
			}
		} catch (Exception e) {
			logger.error("删除用户失败：" + e.getMessage());
			ret.put("status", Constants.RET_ERR);
			ret.put("errorMessage", "删除用户失败：" + e.getMessage());
			return ret;
		}
		return ret;
	}

	@RequestMapping(value = "getSelfResource", method = RequestMethod.POST)
	public Map<String, Object> getSelfResource() {

		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("status", Constants.RET_SUC);
		try {
			List<SResource> list = sUserServiceImpl.getSelfResource(SecurityUserHolder.getCurrentUser().getId());
			ret.put("data", list);
		} catch (Exception e) {
			logger.error("获取用户菜单失败：" + e.getMessage());
			ret.put("status", Constants.RET_ERR);
			ret.put("errorMessage", "获取用户菜单失败：" + e.getMessage());
			return ret;
		}
		return ret;
	}

	@RequestMapping(value = "getRelateUserRole", method = RequestMethod.POST)
	public Map<String, Object> getRelateUserRole(Integer id) {

		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("status", Constants.RET_SUC);
		try {
			List<SRole> relatedList = sUserServiceImpl.getUserRelatedRole(id);
			List<SRole> unRelateList = sUserServiceImpl.getUserUnRelateRole(id);

			ret.put("relatedData", relatedList);
			ret.put("unRelateData", unRelateList);
		} catch (Exception e) {
			logger.error("获取用户角色关联关系失败：" + e.getMessage());
			ret.put("status", Constants.RET_ERR);
			ret.put("errorMessage", "获取用户角色关联关系失败：" + e.getMessage());
			return ret;
		}
		return ret;
	}

	@RequestMapping(value = "addUserRoles", method = RequestMethod.POST)
	public Map<String, Object> addUserRoles(Integer userId, String roleIds) {

		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("status", Constants.RET_SUC);
		try {
			List<SUserRole> list = new ArrayList<SUserRole>();
			if (!StringUtils.isNullOrEmpty(roleIds)) {
				String[] ris = roleIds.split(",");
				for (String roleId : ris) {
					SUserRole ur = new SUserRole();
					ur.setRoleId(Integer.valueOf(roleId));
					ur.setUserId(userId);
					ur.setCreateUser(SecurityUserHolder.getCurrentUser().getUsername());
					ur.setUpdateUser(SecurityUserHolder.getCurrentUser().getUsername());
					list.add(ur);
				}
			}
			sUserServiceImpl.addUserRoles(list, userId);
		} catch (Exception e) {
			logger.error("关联用户角色失败：" + e.getMessage());
			ret.put("status", Constants.RET_ERR);
			ret.put("errorMessage", "关联用户角色失败：" + e.getMessage());
			return ret;
		}
		return ret;
	}
}
