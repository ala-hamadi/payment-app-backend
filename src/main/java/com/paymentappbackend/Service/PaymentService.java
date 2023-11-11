package com.paymentappbackend.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.paymentappbackend.Entity.Payments;
import com.paymentappbackend.Entity.Users;
import com.paymentappbackend.Repository.PaymentRepository;
import com.paymentappbackend.Repository.UserRepository;

@Service
public class PaymentService {

  @Autowired
  private PaymentRepository paymentRepository;
  @Autowired
  private UserRepository userRepository;

  public ResponseEntity<Payments> addPayment(Payments payment) {
    if (payment == null || !userRepository.existsByEmail(payment.getUser().getEmail())) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    paymentRepository.save(payment);
    return ResponseEntity.ok(payment);
  }
}
