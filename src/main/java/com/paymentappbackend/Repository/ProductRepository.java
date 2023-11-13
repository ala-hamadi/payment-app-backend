package com.paymentappbackend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.paymentappbackend.Entity.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {
  @Query("SELECT p FROM Products p WHERE p.inventory > 0")
  List<Products> findAllAvailableProducts();
}
