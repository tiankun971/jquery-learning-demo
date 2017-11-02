package com.github.kuntian.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.kuntian.entity.SResource;

@Repository("rSRRepository")
public interface SResourceRepository extends JpaRepository<SResource, Integer> {

	@Query(value = "select * from s_resource sr where not exists (select 1 from s_resource_role_r rr where rr.s_resource_code = sr.resource_code and rr.s_role_id = ?1)", nativeQuery = true)
	List<SResource> getUnRelateRoleResource(Integer roleId);

	@Query(value = "select * from s_resource sr where exists (select 1 from s_resource_role_r rr where rr.s_resource_code = sr.resource_code and rr.s_role_id = ?1)", nativeQuery = true)
	List<SResource> getRelatedRoleResource(Integer roleId);

	@Query(value = "select * from s_resource sr "
			+ " where exists (select 1 from s_resource_role_r srr ,s_user_role_r sur  where srr.s_role_id = sur.s_role_id and srr.s_resource_code = sr.resource_code and sur.s_user_id = ?1)", nativeQuery = true)
	List<SResource> getSelfResource(Integer id);
}
