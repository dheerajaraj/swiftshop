package com.inventory.inventorybiz.valueObj;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Rating {

  private float rating;

  public Rating calculateAverageRating(Rating oldAverageRating, int totalUnRatedCount) {
    return new Rating(
        ((oldAverageRating.getRating() * totalUnRatedCount) + this.rating) / (totalUnRatedCount
            + 1));
  }
}
