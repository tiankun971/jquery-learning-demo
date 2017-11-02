package com.github.kuntian.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.kuntian.entity.SUser;

@Repository
public interface SUserRepository extends JpaRepository<SUser, Integer> {

	@Query("select u from SUser u where u.email=?1 and u.password=?2")
	SUser login(String email, String password);

	SUser findByEmailAndPassword(String email, String password);

	SUser findUserByUsername(String username);

	SUser findUserByEmail(String email);

	@Query(value = "select * from s_user where realname like concat('%',?1,'%') and enabled = 1 and username <> 'admin'", nativeQuery = true)
	List<SUser> getUserByRealName(String name);

	@Modifying
	@Query(value = "update s_user set enabled = 0 where user_id in ?1", nativeQuery = true)
	void disableUser(List<Integer> ids);
}
