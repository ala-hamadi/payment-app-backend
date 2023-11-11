package com.paymentappbackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymentappbackend.Entity.Payments;

@Repository
public interface PaymentRepository extends JpaRepository<Payments, Integer> {

}
