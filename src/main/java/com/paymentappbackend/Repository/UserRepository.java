package com.paymentappbackend.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.paymentappbackend.Entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

  @Query("SELECT u FROM Users u WHERE u.email = ?1")
  public Optional<Users> findByEmail(String email);

  boolean existsByEmail(String email);
}
