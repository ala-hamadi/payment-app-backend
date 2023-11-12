package com.paymentappbackend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymentappbackend.Entity.Users;
import com.paymentappbackend.Payload.JwtResponse;
import com.paymentappbackend.Security.jwt.JwtUtils;
import com.paymentappbackend.Security.services.UserDetailsImpl;
import com.paymentappbackend.Service.UserService;

@RestController
@RequestMapping("/public")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {


  public final static String FOUND = "FOUND";
  public final static String BAD_REQUEST = "BAD_REQUEST";
  public final static String NOT_FOUND = "NOT_FOUND";
  public final static String NULL = "ID NULL DETECTED";

  @Autowired
  private UserService userService;
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  JwtUtils jwtUtils;


  @GetMapping(value = "/getUser/{id}")
  public ResponseEntity<Object> getUser(@PathVariable("id") Integer id) {
    ResponseEntity<Users> user = userService.getUserById(id);
    if (user.getStatusCodeValue() == 200) {
      return new ResponseEntity<>(user.getBody(), HttpStatus.OK);
    } else if (user.getStatusCodeValue() == 404) {
      return new ResponseEntity<>(NOT_FOUND, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(NULL, HttpStatus.OK);

    }
  }

  @PostMapping("/signup")
  public ResponseEntity<Object> signup(@RequestBody Users userReq) {
    ResponseEntity<Users> user = userService.addUser(userReq);
    if (user.getStatusCodeValue() == 200) {
      return new ResponseEntity<>(user.getBody(), HttpStatus.OK);
    } else if (user.getStatusCodeValue() == 400) {
      return new ResponseEntity<>(BAD_REQUEST, HttpStatus.BAD_REQUEST);
    } else {
      return new ResponseEntity<>(FOUND, HttpStatus.FOUND);
    }
  }


  @PostMapping("/signin")
  public ResponseEntity<Object> authenticateUser(@RequestBody Users userReq) {
    if (!userService.isUserExistsByEmail(userReq.getEmail())) {
      return new ResponseEntity<>(NOT_FOUND, HttpStatus.OK);
    }

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(userReq.getEmail(), userReq.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    return new ResponseEntity<>(new JwtResponse(jwt,
        userDetails.getId(),
        userDetails.getEmail(),
        userDetails.getUsername()
    ), HttpStatus.OK);
  }

  @GetMapping(value = "/getUserByEmail/{email}")
  public ResponseEntity<Object> getUserByEmail(@PathVariable("email") String email) {
    ResponseEntity<Users> user = userService.getUserByEmail(email);
    if (user.getStatusCodeValue() == 200) {
      return new ResponseEntity<>(user.getBody(), HttpStatus.OK);
    } else if (user.getStatusCodeValue() == 404) {
      return new ResponseEntity<>(NOT_FOUND, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(NULL, HttpStatus.OK);

    }
  }

}
