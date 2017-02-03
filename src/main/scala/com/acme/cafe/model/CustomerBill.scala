package com.acme.cafe.model

import java.math.{BigDecimal, RoundingMode}

import org.apache.commons.lang3.Validate

import scala.collection.JavaConversions._

class CustomerBill {

  var total: BigDecimal = BigDecimal.ZERO
  var subTotal: BigDecimal = BigDecimal.ZERO
  var totalDiscount: BigDecimal = BigDecimal.ZERO
  var serviceCharge: BigDecimal = BigDecimal.ZERO
  var cartProducts: java.util.List[CartProduct] = new java.util.ArrayList[CartProduct]

  def addCartProduct(cartProduct: CartProduct) {
    Validate.notNull(cartProduct, "cartProduct must not be null")
    cartProducts.add(cartProduct)
    doTotals()
    total = total.setScale(2, RoundingMode.CEILING)
    subTotal = subTotal.setScale(2, RoundingMode.CEILING)
    serviceCharge = serviceCharge.setScale(2, RoundingMode.CEILING)
  }

  /**
    * @param cartProducts
    */
  def addCartProducts(cartProducts: java.util.List[CartProduct]) {
    for (cartProduct <- cartProducts)
      addCartProduct(cartProduct)
  }

  def doTotals() {

    total = BigDecimal.ZERO
    var hasHotFoodItems: Boolean = false
    var hasFoodItems: Boolean = false

    for (cartProduct: CartProduct <- cartProducts) {
      total = total.add(cartProduct.total)
      if (cartProduct.product.productType eq "FOOD") {
        hasFoodItems = true
        if (cartProduct.temperatureType eq "HOT") hasHotFoodItems = true
      }
    }

    subTotal = total
    if (!hasFoodItems) {
      //When all purchased items are drinks no service charge is applied
      return
    }
    var servicePercentage: BigDecimal = null
    val maxServiceCharge: BigDecimal = new BigDecimal("20.00")

    if (checkForHotFoodItems) return
    if (checkFoodItems) return

    def checkForHotFoodItems: Boolean = {
      if (hasHotFoodItems) {
        //When purchased items include any hot food apply a service charge of 20% to the total bill with a maximum £20 service charge
        servicePercentage = new BigDecimal("0.20")
        serviceCharge = total.multiply(servicePercentage)
        if (serviceCharge.compareTo(maxServiceCharge) > 0) serviceCharge = maxServiceCharge
        total = total.add(serviceCharge)
        return true
      }
      false
    }
    def checkFoodItems: Boolean = {
      if (hasFoodItems) {
        //When purchased items include any food apply a service charge of 10%
        servicePercentage = new BigDecimal("0.10")
        serviceCharge = total.multiply(servicePercentage)
        total = total.add(serviceCharge)
        return true
      }
      false
    }
  }

  //The following are convenience methods for velocity
  def getServiceCharge: BigDecimal = {
    return serviceCharge
  }

  def getTotal: BigDecimal = {
    return total
  }
  def getSubTotal: BigDecimal = {
    return subTotal
  }
}