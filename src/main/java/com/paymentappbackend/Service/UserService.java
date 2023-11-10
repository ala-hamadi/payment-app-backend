package com.paymentappbackend.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.paymentappbackend.Entity.Users;
import com.paymentappbackend.Repository.UserRepository;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;


  public ResponseEntity<Users> getUserById(Integer id) {

    Optional<Users> user = userRepository.findById(id);
    if (user.isPresent()) {
      return ResponseEntity.ok(user.get());
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  public ResponseEntity<Users> addUser(Users user) {

    if (user == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    Optional<Users> existingUser = userRepository.findByEmail(user.getEmail());
    if (existingUser.isPresent()) {
      return new ResponseEntity<>(HttpStatus.FOUND);
    }
    if (user.getEmail() == null || user.getPassword() == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } else {
      String encodedPassword = passwordEncoder.encode(user.getPassword());
      user.setPassword(encodedPassword);
    }
    userRepository.save(user);
    return ResponseEntity.ok(user);
  }

  public boolean isUserExistsByEmail(String email) {

    return userRepository.existsByEmail(email);
  }
}
