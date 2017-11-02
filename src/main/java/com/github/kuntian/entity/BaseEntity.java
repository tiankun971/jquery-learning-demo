package com.github.kuntian.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public class BaseEntity implements Serializable{

	
	    /**
	    * @Fields serialVersionUID : (用一句话描述这个变量表示什么)
	    */
	    
	private static final long serialVersionUID = 3286758416065774270L;
	
	private String createUser;
	private String updateUser;
	private Timestamp  createTime;
	private Timestamp  updateTime;
	
	
	@Column(name="CREATE_USER")
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	@Column(name="UPDATE_USER")
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
	@JsonIgnore
	@Column(name="CREATE_TIME",updatable=false)
	@CreationTimestamp
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	@JsonIgnore
	@Column(name="UPDATE_TIME")
	@UpdateTimestamp
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}
