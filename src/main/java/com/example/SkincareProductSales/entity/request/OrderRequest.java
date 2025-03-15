package com.example.SkincareProductSales.entity.request;

import lombok.Data;

import java.util.List;

public class OrderRequest {
  private List<OrderDetailRequest> details;

  public String voucherCode;

 public OrderRequest() {
 }

 public OrderRequest(List<OrderDetailRequest> details, String voucherCode) {
  this.details = details;
  this.voucherCode = voucherCode;
 }

 public List<OrderDetailRequest> getDetails() {
  return details;
 }

 public void setDetails(List<OrderDetailRequest> details) {
  this.details = details;
 }

 public String getVoucherCode() {
  return voucherCode;
 }

 public void setVoucherCode(String voucherCode) {
  this.voucherCode = voucherCode;
 }
}
