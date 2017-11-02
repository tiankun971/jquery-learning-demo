package com.github.kuntian.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.kuntian.constant.Constants;

@Entity
@Table(name = "s_role")
public class SRole extends BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private String displayName;

	private String showName;

	/**
	 * many to many 一个角色对应多个用户
	 */
	private Set<SUser> SUserSet = new HashSet<SUser>();

	public SRole() {

	}

	public SRole(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ROLE_ID", unique = true, nullable = false)
	public Integer getId() {

		return this.id;

	}

	public void setId(Integer id) {

		this.id = id;

	}

	@Column(name = "name", length = 20)
	public String getName() {
		return this.name;

	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	@ManyToMany(mappedBy = "SRoleSet")
	public Set<SUser> getSUserSet() {
		return SUserSet;
	}

	public void setSUserSet(Set<SUser> sUserSet) {
		SUserSet = sUserSet;
	}

	/**
	 * 添加用户
	 * 
	 * @param SUser
	 */
	public void addSUser(SUser sUser) {
		if (!this.SUserSet.contains(sUser)) {
			this.SUserSet.add(sUser);
			sUser.getSRoleSet().add(this);
		}
	}

	/**
	 * 删除用户
	 * 
	 * @param SUser
	 */
	public void removeSUser(SUser sUser) {
		if (this.SUserSet.contains(sUser)) {
			sUser.getSRoleSet().remove(this);
			this.SUserSet.remove(sUser);
		}
	}

	@Column(name = "display_name", nullable = false)
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Transient
	public String getShowName() {
		Integer index = this.name.indexOf(Constants.ROLE_PREFIX);
		if (index == 0)
			return this.name.substring(Constants.ROLE_PREFIX.length());
		return name;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}
}