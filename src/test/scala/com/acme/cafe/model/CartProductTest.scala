package com.acme.cafe.model

import org.junit.Test
import java.math.BigDecimal

class CartProductTest {

  @Test(expected = classOf[NullPointerException])
  def showNullProductIsRejected() {
    new CartProduct(null, 1, "COLD")
  }

  @Test(expected = classOf[IllegalArgumentException])
  def showZeroCountIsRejected() {
    val price: BigDecimal = new BigDecimal("1.20")
    new CartProduct(buildProduct(price), -1, "COLD")
  }

  @Test(expected = classOf[NullPointerException])
  def showNullTemperatureTypeIsRejected() {
    val price: BigDecimal = new BigDecimal("1.20")
    new CartProduct(buildProduct(price), -1, null)
  }

  @Test
  def showValidProductIsAddedTocart() {
    val price: BigDecimal = new BigDecimal("1.20")
    val currentTotal: BigDecimal = price.multiply(new BigDecimal(2))
    val cartProduct: CartProduct = new CartProduct(buildProduct(price), 2, "HOT")
    org.junit.Assert.assertNotNull(cartProduct)
    org.junit.Assert.assertTrue(cartProduct.total == currentTotal)
    org.junit.Assert.assertTrue(cartProduct.product.price == price)
  }

  def buildProduct(price: BigDecimal): Product = new Product("Coffee", price, "Hot Coffee", "RINK")
}