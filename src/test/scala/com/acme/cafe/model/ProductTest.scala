package com.acme.cafe.model

import java.math.BigDecimal

import org.junit.Test

//import org.scalatest.junit.AssertionsForJUnit

class ProductTest {

  @Test(expected = classOf[NullPointerException])
  def showNullProductTypeIsRejected() {
    new Product("Coffer", new BigDecimal("1.00"), "Coffee", null)
  }

  @Test(expected = classOf[IllegalArgumentException])
  def showEmptyDescriptionIsRejected() {
    new Product("Coffer", new BigDecimal("1.00"), "", "DRINK")
  }

  @Test(expected = classOf[NullPointerException])
  def showNullPriceIsRejected() {
    new Product("Coffer", null, "Coffee", "DRINK")
  }


  @Test(expected = classOf[IllegalArgumentException])
  def testAndShowEmptyNameIsRejected() {
    new Product("", new BigDecimal("1.00"), "Coffee", "DRINK")
  }

  @Test def showValidProducthasValidFields() {
    val price: BigDecimal = new BigDecimal("1.20")
    val product: Product = buildProduct(price)
    org.junit.Assert.assertTrue(product.price eq (price))
    org.junit.Assert.assertEquals(product.name, "Coffee")
    org.junit.Assert.assertEquals(product.description, "Hot Coffee")
    org.junit.Assert.assertEquals(product.productType,"DRINK")
  }

  def buildProduct(price: BigDecimal): Product = new Product("Coffee", price, "Hot Coffee", "DRINK")
}