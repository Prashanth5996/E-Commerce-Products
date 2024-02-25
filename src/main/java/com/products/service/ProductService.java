package com.products.service;

import java.util.List;

import com.products.model.Products;

public interface ProductService {
	public String saveProduct(Products products);
	
	public String updateProduct(Products products,int productId);
	
	public String deleteProduct(int productId);
	
	public String getOneProduct(int productId);
	
	public List<Products> getAllProducts();
}
