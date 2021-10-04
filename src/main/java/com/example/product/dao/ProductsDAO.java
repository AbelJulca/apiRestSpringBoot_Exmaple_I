package com.example.product.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.product.entitys.Product;

public interface ProductsDAO extends JpaRepository<Product, Long>{

}
