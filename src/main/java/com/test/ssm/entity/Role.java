package com.test.ssm.entity;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	/**
	 * 权限名称
	 */
	private String name;
	/**
	 * 权限描述
	 */
	private String description;
	
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
