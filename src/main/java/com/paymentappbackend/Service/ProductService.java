package com.paymentappbackend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymentappbackend.Repository.ProductRepository;

@Service
public class ProductService {
  @Autowired
  private ProductRepository productRepository;
}
