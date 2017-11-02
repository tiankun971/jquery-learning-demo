package com.github.kuntian.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.kuntian.entity.SUserRole;

@Repository("rSURRepository")
public interface SUserRoleRepository extends JpaRepository<SUserRole, Integer> {

	@Modifying
	@Query(value = "delete from s_user_role_r where s_role_id in ?1", nativeQuery = true)
	void delUserRoles(List<Integer> roleIds);

	@Modifying
	@Query(value = "delete from s_user_role_r where s_user_id = ?1", nativeQuery = true)
	void delUserRolesByUser(Integer userId);
}
