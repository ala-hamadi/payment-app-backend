package com.paymentappbackend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.paymentappbackend.Service.ProductService;

@RestController
public class ProductController {
  @Autowired
  private ProductService productService;
}
