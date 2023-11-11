package com.paymentappbackend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.paymentappbackend.Entity.Payments;
import com.paymentappbackend.Service.PaymentService;

@RestController
public class PaymentController {

  public final static String FOUND = "FOUND";
  public final static String BAD_REQUEST = "BAD_REQUEST";
  public final static String NOT_FOUND = "NOT_FOUND";
  public final static String NULL = "ID NULL DETECTED";

  @Autowired
  private PaymentService paymentService;

  @PostMapping("/addPayment")
  public ResponseEntity<Object> addPayment(@RequestBody Payments paymentReq) {
    ResponseEntity<Payments> paymentRes = paymentService.addPayment(paymentReq);
    if (paymentRes.getStatusCodeValue() == 200) {
      return new ResponseEntity<>(paymentRes.getBody(), HttpStatus.OK);
    } else if (paymentRes.getStatusCodeValue() == 400) {
      return new ResponseEntity<>(BAD_REQUEST, HttpStatus.BAD_REQUEST);
    } else {
      return new ResponseEntity<>(FOUND, HttpStatus.FOUND);
    }
  }
}
