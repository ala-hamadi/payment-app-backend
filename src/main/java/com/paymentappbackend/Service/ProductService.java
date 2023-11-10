package com.paymentappbackend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymentappbackend.Entity.Products;
import com.paymentappbackend.Repository.ProductRepository;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  public List<Products> getAllProducts() {
    return productRepository.findAll();
  }

  public Optional<Products> getProductById(int id) {
    if (productRepository.findById(id).isPresent()) {
      return productRepository.findById(id);
    } else {
      return null;
    }
  }
}
