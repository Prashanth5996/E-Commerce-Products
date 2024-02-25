package com.products.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.products.model.Products;
@Service
class ProductServiceImp implements ProductService {

    @Autowired
    public JdbcTemplate jt;

    private final String SQL_FIND_PRODUCT = "SELECT * FROM products WHERE productId=?";
    private final String SQL_DELETE_PRODUCT = "DELETE FROM products WHERE productId=?";
    private final String SQL_UPDATE_PRODUCTS = "UPDATE products SET pname=?, description=?, price=? WHERE productId=?";
    private final String SQL_GET_ALL = "SELECT * FROM products";
    private final String SQL_INSERT_PRODUCTS = "INSERT INTO products(productId,pname,description,price) VALUES (?,?,?,?)";

    @Override
    public String saveProduct(Products products) {
        try {
            jt.update(SQL_INSERT_PRODUCTS, products.getProductId(), products.getPname(), products.getDescription(), products.getPrice());
            return "Products Data Saved Successfully..!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to Save products..!";
        }
    }

    @Override
    public String updateProduct(Products products, int productId) {
        try {
            jt.update(SQL_UPDATE_PRODUCTS, products.getPname(), products.getDescription(), products.getPrice(), productId);
            return "Products Data Updated Successfully..!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to Updated products..!";
        }
    }

    @Override
    public String deleteProduct(int productId) {
        try {
            jt.update(SQL_DELETE_PRODUCT, productId);
            return "Products Data Deleted Successfully..!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to Delete products..!";
        }
    }

    @Override
    public String getOneProduct(int productId) {
        try {
            Products product = jt.queryForObject(SQL_FIND_PRODUCT, new BeanPropertyRowMapper<>(Products.class), productId);
            return "" + product;
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to get products or ID Not Found..!";
        }
    }

    @Override
    public List<Products> getAllProducts() {
        try {
            return jt.query(SQL_GET_ALL, new BeanPropertyRowMapper<>(Products.class));
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
