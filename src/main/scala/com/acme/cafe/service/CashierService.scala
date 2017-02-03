package com.acme.cafe.service

import java.io.StringWriter
import java.math.BigDecimal
import java.util.{Properties, Random}

import com.acme.cafe.model.{CartProduct, _}
import org.apache.velocity.app.VelocityEngine
import org.apache.velocity.{Template, VelocityContext}


object CashierService {

  /**
    *
    * @param name
    * @param price
    * @param description
    * @param productType
    * @return
    */
  def buildProduct(name: String, price: java.math.BigDecimal, description: String, productType: String): Product = {
    return new Product(name, price, description, productType)
  }

  //program launcher
  def main(args: Array[String]) {
    val customerBill = new CustomerBill()

    var price: BigDecimal = new BigDecimal("1.20")
    var product = buildProduct("Coffee", price, "Hot Coffee","DRINK")
    var cartProduct = new CartProduct(product, 2, "HOT");
    customerBill.addCartProduct(cartProduct)

    price = new BigDecimal("2.30")
    new CartProduct(new Product("Sandwich", price, "Hot Sandwich", "FOOD"), 2, "COLD")
    cartProduct = new CartProduct(product, 2, "HOT");
    customerBill.addCartProduct(cartProduct)

    val writer: StringWriter = printReceipt(customerBill)

    println(writer)
  }

  def printReceipt(customerBill: CustomerBill): StringWriter = {
    val random: Random = new Random(1)
    val ve: VelocityEngine = new VelocityEngine()
    val props: Properties = new Properties()
    props.put("file.resource.loader.path", "src/test/resources")
    ve.init(props)
    val t: Template = ve.getTemplate("receipt-template.vtl")

    val context: VelocityContext
    = new VelocityContext
    context.put("date", new java.util.Date)
    context.put("server", "John")
    context.put("table", 1 + random.nextInt(10))
    context.put("checkNumber", 1 + random.nextInt(10))
    context.put("customerBill", customerBill)

    val writer: StringWriter = new StringWriter()
    t.merge(context, writer)
    writer
  }
}