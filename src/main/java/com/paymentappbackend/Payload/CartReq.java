package com.paymentappbackend.Payload;

public class CartReq {
  private Integer id;
  private String name;
  private float price;
  private Integer inventory;
  private Integer quantity;

  public CartReq(Integer id, String name, float price, Integer inventory, Integer quantity) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.inventory = inventory;
    this.quantity = quantity;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public void setInventory(Integer inventory) {
    this.inventory = inventory;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public float getPrice() {
    return price;
  }

  public Integer getInventory() {
    return inventory;
  }

  public Integer getQuantity() {
    return quantity;
  }
}
