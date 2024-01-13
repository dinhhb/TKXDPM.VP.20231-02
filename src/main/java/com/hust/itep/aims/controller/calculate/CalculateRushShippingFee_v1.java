package com.hust.itep.aims.controller.calculate;


import com.hust.itep.aims.entity.order.OrderMedia;

public class CalculateRushShippingFee_v1 extends CalculateShippingFee_v1{
  /**
   * This method calculate shipping fees for each media supported by rush delivery.
   * 
   * @param orderMedia orderMedia
   * @return int
   */
  public int calculateRushOrderShippingFee(OrderMedia orderMedia) {
    if (orderMedia == null || orderMedia.getQuantity() == 0) {
      System.out.print("The order must not be empty!");
      return 0;
    }
    int rushOrderFees = orderMedia.getQuantity() * 10000;
    return rushOrderFees;
  }
}
