package com.github.kuntian.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.kuntian.utils.DateUtils;

@Entity
@Table(name = "s_user") // code11
public class SUser extends BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String username;

	private String realname;

	private String email;

	private String password = "098f6bcd4621d373cade4e832627b4f6";

	private Date dob;

	private String imgUrl = "assets/avatars/profile-pic.jpg";

	private Date lastPasswordResetDate;

	private boolean enabled = true;

	private boolean accountNonLocked = true;

	private boolean accountNonExpired = true;

	private boolean credentialsNonExpired = true;

	private Set<SRole> SRoleSet = new HashSet<SRole>(0);// Code12

	private String roleNames;

	private String dobStr;

	public SUser() {

	}

	public SUser(String username, String realname, String email, String password, Date dob, Date lastPasswordResetDate,
			boolean enabled, boolean accountNonLocked, boolean accountNonExpired, boolean credentialsNonExpired,
			Set<SRole> SRoleSet) {

		this.username = username;

		this.realname = realname;

		this.email = email;

		this.password = password;

		this.dob = dob;

		this.lastPasswordResetDate = lastPasswordResetDate;

		this.enabled = enabled;

		this.accountNonLocked = accountNonLocked;

		this.accountNonExpired = accountNonExpired;

		this.credentialsNonExpired = credentialsNonExpired;

		this.SRoleSet = SRoleSet;

	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "USER_ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;

	}

	public void setId(Integer id) {

		this.id = id;

	}

	@Column(name = "username", length = 50)
	public String getUsername() {

		return this.username;

	}

	public void setUsername(String username) {

		this.username = username;

	}

	@Column(name = "realname", length = 50)
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	@Column(name = "email", length = 50)
	public String getEmail() {

		return this.email;

	}

	public void setEmail(String email) {

		this.email = email;

	}

	@Column(name = "password", length = 200)
	public String getPassword() {

		return this.password;

	}

	public void setPassword(String password) {

		this.password = password;

	}

	@JsonIgnore
	@Temporal(TemporalType.DATE)
	@Column(name = "dob", length = 10)
	public Date getDob() {

		return this.dob;

	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@JsonIgnore
	@Column(name = "LAST_PASSWORD_RESET_DATE")
	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	public void setLastPasswordResetDate(Date lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	@JsonIgnore
	@Column(name = "enabled")
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@JsonIgnore
	@Column(name = "account_Non_Locked")
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	@JsonIgnore
	@Column(name = "account_Non_Expired")
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	@JsonIgnore
	@Column(name = "credentials_Non_Expired")
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	@JsonIgnore
	@JoinTable(name = "s_user_role_r", joinColumns = {
			@JoinColumn(name = "S_USER_ID", referencedColumnName = "USER_ID") }, inverseJoinColumns = {
					@JoinColumn(name = "S_ROLE_ID", referencedColumnName = "ROLE_ID") })
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	public Set<SRole> getSRoleSet() {
		return SRoleSet;
	}

	public void setSRoleSet(Set<SRole> sRoleSet) {
		SRoleSet = sRoleSet;
	}

	/**
	 * 添加角色
	 * 
	 * @param SRole
	 */
	public void addSRole(SRole sRole) {
		if (!this.SRoleSet.contains(sRole)) {
			this.SRoleSet.add(sRole);
			sRole.getSUserSet().add(this);
		}
	}

	/**
	 * 删除角色
	 * 
	 * @param SRole
	 */
	public void removeSRole(SRole sRole) {
		if (this.SRoleSet.contains(sRole)) {
			sRole.getSUserSet().remove(this);
			this.SRoleSet.remove(sRole);
		}
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	@Column(name = "img_url")
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@Transient
	public String getDobStr() {
		if (this.dob != null)
			return DateUtils.formatDate(this.dob);
		return dobStr;
	}

	public void setDobStr(String dobStr) {
		this.dobStr = dobStr;
	}

}
