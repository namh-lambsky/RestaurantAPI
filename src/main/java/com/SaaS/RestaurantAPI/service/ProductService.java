package com.SaaS.RestaurantAPI.service;

import com.SaaS.RestaurantAPI.model.Product;
import com.SaaS.RestaurantAPI.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public ArrayList<Product> getProducts() {
        return (ArrayList<Product>) productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
    }

    public Product saveProduct(Product product) {
        Date currentDate = new Date(System.currentTimeMillis());
        product.setCreated_at(currentDate);
        product.setUpdated_at(currentDate);
        return productRepository.save(product);
    }

    public Product updateProductById(Product request, int id) {
        Product product = getProductById(id);
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setQuantity(request.getQuantity());
        product.setPackageType(request.getPackageType());
        product.setIVA(request.isIVA());
        product.setUnitary_price(request.getUnitary_price());
        product.setTotal(request.getTotal());

        Date currentDate = new Date(System.currentTimeMillis());
        product.setUpdated_at(currentDate);
        productRepository.save(product);

        return product;
    }

    public ResponseEntity<Map<String,Boolean>> deleteProductById(int id) {
        Product product = this.getProductById(id);
        productRepository.deleteById(id);
        Map<String,Boolean> response = new HashMap<>();
        response.put("eliminado",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}
