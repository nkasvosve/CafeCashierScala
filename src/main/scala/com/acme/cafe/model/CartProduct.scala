package com.acme.cafe.model

import java.math.BigDecimal

import org.apache.commons.lang3.Validate

class CartProduct(productV: Product, countV: Int, temperatureTypeV: String) {

  var product: Product = productV
  var count: Int = countV
  var temperatureType: String = temperatureTypeV
  var total: BigDecimal = BigDecimal.ZERO
  Validate.notNull(product, "product must not be null")
  Validate.notNull(temperatureType, "temperatureType must not be null")
  Validate.isTrue(count > 0, "count must be > 0")

  doTotal()

  def doTotal() {
    total = product.price.multiply(new BigDecimal(count))
  }
}