package com.inventory.inventorybiz.inventory.model.entity.valueobj.reservation;

import java.security.InvalidParameterException;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Stock {

  private Integer quantity;

  public void validateAndUpdateStocksToRemove(Stock stock) {
    if (this.quantity < stock.getQuantity()) {
      throw new InvalidParameterException(
          "The stock in order cannot be more than the stocks in the inventory");
    }
    this.quantity = this.quantity - stock.getQuantity();
  }
}
