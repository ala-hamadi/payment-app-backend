package com.paymentappbackend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymentappbackend.Entity.Payments;

@Repository
public interface PaymentRepository extends JpaRepository<Payments, Integer> {

  List<Payments> findPaymentsByUserId(Integer userId);

}
