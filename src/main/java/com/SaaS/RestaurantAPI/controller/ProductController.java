package com.SaaS.RestaurantAPI.controller;


import com.SaaS.RestaurantAPI.model.Product;
import com.SaaS.RestaurantAPI.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping
    public ArrayList<Product> getProducts(){
        return this.productService.getProducts();
    }

    @GetMapping(path = "/{id}")
    public Product getProductById(@PathVariable int id) {
        return this.productService.getProductById(id);
    }

    
    @PostMapping(path = "/new")
    public Product saveProduct(@RequestBody Product product) {
        return this.productService.saveProduct(product);
    }

    @PutMapping(path = "/update-{id}")
    public Product updateProduct(@RequestBody Product request, @PathVariable int id) {
        return this.productService.updateProductById(request, id);
    }

    @DeleteMapping(path = "/delete-{id}")
    public String deleteProduct(@PathVariable int id) {
        boolean isDeleted = this.productService.deleteProductById(id);
        if (isDeleted) {
            return "Product with id: " + id + " was deleted";
        } else {
            return "Unexpected error: Product with id:" + id + " was not deleted";
        }
    }
}
