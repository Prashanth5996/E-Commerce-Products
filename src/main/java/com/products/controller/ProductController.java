package com.products.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.products.model.Products;
import com.products.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public String saveProducts(@RequestBody Products products) {
        if (products != null) {
            return productService.saveProduct(products);
        } else {
            return "Invalid input for saving products...!!";
        }
    }

    @PatchMapping("/update/{productId}")
    public String updateProducts(@RequestBody Products products, @PathVariable Integer productId) {
        if (products != null) {
            return productService.updateProduct(products, productId);
        } else {
            return "Invalid input for updating products...!!";
        }
    }

    @DeleteMapping("/delete/{productId}")
    public String deleteProducts(@PathVariable Integer productId) {
        if (productId != null) {
            return productService.deleteProduct(productId);
        } else {
            return "No Id Found for deletion...!!";
        }
    }

    @GetMapping("/get/{productId}")
    public String getOneProduct(@PathVariable Integer productId) {
        if (productId != null) {
            return productService.getOneProduct(productId);
        } else {
            return "Invalid productId No Record Found...!!";
        }
    }

    @GetMapping("/getAll")
    public List<Products> getAllProducts() {
        return productService.getAllProducts();
    }
}