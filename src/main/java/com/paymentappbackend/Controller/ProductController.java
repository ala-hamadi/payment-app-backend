package com.paymentappbackend.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymentappbackend.Entity.Products;
import com.paymentappbackend.Entity.Users;
import com.paymentappbackend.Service.ProductService;

@RestController
@RequestMapping("/public")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping("/getAllProducts")
  public List<Products> getAllProducts() {
    return productService.getAllProducts();
  }

  @GetMapping("/getProductById/{id}")
  public Optional<Products> getProductById(@PathVariable(value = "id") int id) {
    return productService.getProductById(id);
  }

  @PostMapping("/addProductToCart/{user_id}/{product_id}")
  public Users addProductToCart(@PathVariable(value = "user_id") int user_id, @PathVariable(value = "product_id") int product_id) {
    return productService.addProductToCart(user_id, product_id);
  }

  @PutMapping("/removeProductFromCart/{user_id}/{product_id}")
  public Users removeProductFromCart(@PathVariable(value = "user_id") int user_id, @PathVariable(value = "product_id") int product_id) {
    return productService.removeProductFromCart(user_id, product_id);
  }
}
