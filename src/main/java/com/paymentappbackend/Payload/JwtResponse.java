package com.paymentappbackend.Payload;


public class JwtResponse {

  private String token;
  private String type = "Bearer";
  private Integer id;
  private String email;
  private String username;


  public JwtResponse(String accessToken, Integer id, String email,String username) {
    this.token = accessToken;
    this.id = id;
    this.email = email;
    this.username=username;
  }

  public String getAccessToken() {
    return token;
  }

  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }

  public String getTokenType() {
    return type;
  }

  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}