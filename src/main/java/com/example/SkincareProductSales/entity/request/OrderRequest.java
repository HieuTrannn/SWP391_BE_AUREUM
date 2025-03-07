package com.example.SkincareProductSales.entity.request;

import lombok.Data;

import java.util.List;

public class OrderRequest {
 private List<OrderDetailRequest> details;

 public OrderRequest(List<OrderDetailRequest> details) {
  this.details = details;
 }

 public OrderRequest() {
 }

 public List<OrderDetailRequest> getDetails() {
  return details;
 }

 public void setDetails(List<OrderDetailRequest> details) {
  this.details = details;
 }
}
