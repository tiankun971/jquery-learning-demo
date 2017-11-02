package com.github.kuntian.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.github.kuntian.entity.SRole;

@Repository
public interface SRoleRepository extends JpaRepository<SRole, Integer> {

	@Query("select role from SRole role where role.name = :name")
	SRole findSRoleByRoleName(@Param("name") String name);

	@Query(value = "select * from s_role role where role.display_name like concat('%',:name,'%')", nativeQuery = true)
	List<SRole> getSRoleByName(@Param("name") String name);

	@Modifying
	@Query(value = "delete from s_role where role_id in ?1", nativeQuery = true)
	void delRoles(List<Integer> ids);

	@Query(value = "select count(1) from s_role role where role.name = ?1 and role.role_id <> ?2", nativeQuery = true)
	Long getExistsRoleName(String name, Integer id);

	@Query(value = "select count(1) from s_role where name = ?1", nativeQuery = true)
	Long getExistsRoleName(String name);

	@Query(value = "select * from s_role sr where exists (select 1 from s_user_role_r srr where srr.s_role_id = sr.role_id and srr.s_user_id = ?1)", nativeQuery = true)
	List<SRole> getUserRelatedRole(Integer id);

	@Query(value = "select * from s_role sr where not exists (select 1 from s_user_role_r srr where srr.s_role_id = sr.role_id and srr.s_user_id = ?1)", nativeQuery = true)
	List<SRole> getUserUnRelateRole(Integer id);
}