package com.acme.cafe.model

import java.io.StringWriter
import java.math.BigDecimal
import java.util.Properties

import org.apache.velocity.app.VelocityEngine
import org.apache.velocity.{Template, VelocityContext}
import org.junit.Test

import scala.util.Random

class CustomerBillTest {

  @Test(expected = classOf[NullPointerException])
  def showNullCartProductCannotBeAddedToCart() {
    var customerBill = new CustomerBill
    customerBill.addCartProduct(null)
  }

  @Test(expected = classOf[NullPointerException])
  def showZeroCountProductCannotBeAddedToCart() {
    var customerBill = new CustomerBill
    customerBill.addCartProduct(new CartProduct(null, 1, "COLD"))
  }

  @Test(expected = classOf[NullPointerException])
  def showNullCartProductProductTypeCannotBeAddedToCart() {
    var customerBill = new CustomerBill
    customerBill.addCartProduct(new CartProduct(new Product("Coffer", new BigDecimal("1.00"), "Coffee", null), 1, "COLD"))
  }

  @Test def showAddToCartComputesTotals() {
    var customerBill = new CustomerBill
    var price: BigDecimal = new BigDecimal("1.20")
    customerBill.addCartProduct(new CartProduct(new Product("Coffer", price, "Cold Coffee", "DRINK"), 2, "COLD"))
    var currentTotal: BigDecimal = price.multiply(new BigDecimal(2))
    org.junit.Assert.assertTrue(currentTotal == new BigDecimal("2.40"))
    org.junit.Assert.assertTrue(customerBill.total == currentTotal)
    price = new BigDecimal("2.30")
    currentTotal = currentTotal.add(price.multiply(new BigDecimal(2)))
    org.junit.Assert.assertTrue(currentTotal == new BigDecimal("7.00")) //2.40 + 4.60 = 7.0
    customerBill.addCartProduct(new CartProduct(new Product("Sandwich", price, "Hot Sandwich", "FOOD"), 2, "HOT")) // 7.0 x .20 = 8.40
    org.junit.Assert.assertTrue(customerBill.total == new BigDecimal("8.40"))
    printReceipt(customerBill)
  }

  private[model] val random: Random = new Random(1)

  private def printReceipt(customerBill: CustomerBill) {
    val ve: VelocityEngine = new VelocityEngine
    val props: Properties = new Properties
    props.put("file.resource.loader.path", "src/test/resources")
    ve.init(props)
    val t: Template = ve.getTemplate("receipt-template.vtl")
    val context: VelocityContext = new VelocityContext
    context.put("date", new java.util.Date)
    context.put("server", "John")
    context.put("table", 1 + random.nextInt(10))
    context.put("checkNumber", 1 + random.nextInt(10))
    context.put("customerBill", customerBill)
    val writer: StringWriter = new StringWriter
    t.merge(context, writer)
    System.out.println(writer.toString)
  }

  def buildProduct(price: BigDecimal): Product = new Product("Coffee", price, "Hot Coffee", "DRINK")
}