package com.zen.shopping.domain

trait Item {
  def itemType: String

  def desc: String

  def price: BigDecimal
}

case class Apple(itemType: String = "Fruit", desc: String = "Apple", price: BigDecimal = 0.6) extends Item

case class Orange(itemType: String = "Fruit", desc: String = "Orange", price: BigDecimal = 0.25) extends Item

case class Rotten(itemType: String = "Rotten fruit", desc: String = "Orange", price: BigDecimal = -9999.99) extends Item


