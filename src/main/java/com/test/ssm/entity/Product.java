package com.test.ssm.entity;

public class Product {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	/**
	 * 商品类型
	 */
	private String category;
	/**
	 * 商品名称
	 */
	private String name;
	private String price;
	private Boolean stocked;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Boolean getStocked() {
		return stocked;
	}
	public void setStocked(Boolean stocked) {
		this.stocked = stocked;
	}

}
