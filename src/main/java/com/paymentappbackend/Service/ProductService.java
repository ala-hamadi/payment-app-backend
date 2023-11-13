package com.paymentappbackend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymentappbackend.Entity.Products;
import com.paymentappbackend.Entity.Users;
import com.paymentappbackend.Payload.CartReq;
import com.paymentappbackend.Repository.ProductRepository;
import com.paymentappbackend.Repository.UserRepository;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private UserRepository userRepository;

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

  public Users addProductToCart(Integer user_id, Integer product_id) {
    Optional<Products> product = productRepository.findById(product_id);
    Optional<Users> user = userRepository.findById(user_id);
    if (!product.isPresent() || !user.isPresent() || user.get().getCart().contains(product.get())) {
      return null;
    }
    user.get().getCart().add(product.get());
    userRepository.save(user.get());
    return user.get();
  }

  public Users removeProductFromCart(Integer user_id, Integer product_id) {
    Optional<Products> product = productRepository.findById(product_id);
    Optional<Users> user = userRepository.findById(user_id);
    if (!product.isPresent() || !user.isPresent() || !user.get().getCart().contains(product.get())) {
      return null;
    }
    user.get().getCart().remove(product.get());
    userRepository.save(user.get());
    return user.get();
  }


  public String decreaseQuantityInProducts(List<CartReq> cart) {
    for (CartReq product : cart) {
      Integer productId = product.getId();
      Optional<Products> dbProduct = productRepository.findById(productId);
      dbProduct.get().setInventory(dbProduct.get().getInventory() - product.getQuantity());
      productRepository.save(dbProduct.get());
    }
    return "UPDATED";
  }

  public Users removeAllProductsFromCart(Integer userId) {
    Optional<Users> userOptional = userRepository.findById(userId);
    if (userOptional.isPresent()) {
      Users user = userOptional.get();
      user.getCart().clear();

      userRepository.save(user);
      return user;
    } else {
      return null;
    }
  }

}
