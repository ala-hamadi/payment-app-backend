package com.paymentappbackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymentappbackend.Entity.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products,Long> {

}
