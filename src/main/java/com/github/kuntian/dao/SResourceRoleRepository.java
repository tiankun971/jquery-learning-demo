package com.github.kuntian.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.kuntian.entity.SResourceRole;

@Repository("rSRRRepository")
public interface SResourceRoleRepository extends JpaRepository<SResourceRole, Integer> {

	@Modifying
	@Query(value = "delete from s_resource_role_r where s_role_id in ?1", nativeQuery = true)
	void delResourceRoles(List<Integer> roleIds);

}
