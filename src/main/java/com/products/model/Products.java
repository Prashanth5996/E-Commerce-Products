package com.products.model;

public class Products {
	private Integer productId;
	private String pname;
	private String description;
	private Double price;
	public Products(Integer productId, String pname, String description, Double price) {
		this.productId = productId;
		this.pname = pname;
		this.description = description;
		this.price = price;
	}
	public Products() {
		
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Products [productId=" + productId + ", pname=" + pname + ", description=" + description + ", price="
				+ price + "]";
	}
}
