package com.acme.cafe.model

import java.math.BigDecimal

import org.apache.commons.lang3.Validate

class Product(nameVar: String, priceV: BigDecimal, descriptionV: String, productTypeV: String) {

  var name: String = nameVar
  var price: BigDecimal = priceV
  var description: String = descriptionV
  var productType: String = productTypeV

  Validate.notBlank(name, "name must not be null");
  Validate.notNull(price, "price must not be null");
  Validate.notBlank(description, "description must not be null");
  Validate.notNull(productType, "productType must not be null");
}