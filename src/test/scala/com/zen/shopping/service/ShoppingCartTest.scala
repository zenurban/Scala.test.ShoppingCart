package com.zen.shopping.service

import com.zen.shopping.domain.{Apple, Item, Orange, Rotten}
import com.zen.shopping.rules._
import org.scalatest.{FlatSpec, Matchers}


class ShoppingCartTest extends FlatSpec with Matchers {
  val shoppingCartService: ShoppingCart = new ShoppingCartCampaignService

  val rawSale: Map[Item, Sale] = Map(new Apple -> RawSale, new Orange -> RawSale)
  val campaignSale: Map[Item, Sale] = Map(new Apple -> OneForTwoSale, new Orange -> ThreeForTwoSale,  Rotten() -> NoSale, Rotten( desc=  "Pear") -> NoSale)

  "Shopping cart during raw sale " should "calculate the price of items without campaign" in {
    val qApples = 3 + 9 + 2
    val qOranges = 3 + 6 + 1111
    val totalPrice = qApples * 0.6 + qOranges * 0.25
    val items = List.concat(List.fill(3)(Apple()), List.fill(3)(Orange()), List.fill(9)(Apple()), List.fill(6)(Orange()), List.fill(2)(Apple()), List.fill(1111)(Orange()))

    //println("total price(raw sale) = " + totalPrice)
    assert(shoppingCartService.totalCost(items, rawSale) == totalPrice)

    val items2 = List.concat(List.fill(qApples)(Apple()), List.fill(qOranges)(Orange()))
    assert(shoppingCartService.totalCost(items2, campaignSale) < totalPrice)
    assert(shoppingCartService.totalCost(items2, rawSale) == totalPrice)
  }


  "Shopping cart during campaign sale " should "reflect the campaign" in {
    val qApples = 3 + 9 + 2  +9
    val qOranges = 3 + 6 + 1111
    val totalPrice = (qApples / 2 + qApples % 2) * 0.6 + (2 * qOranges / 3 + qOranges % 3) * 0.25

    val items = List.concat(List.fill(3)(Apple()), List.fill(3)(Orange())
      , List.fill(9)(Apple()), List.fill(6)(Orange()), List.fill(2)(Apple()), List.fill(1111)(Orange())
      , List.fill(11)(Rotten( desc=  "Pear"))
      , List.fill(9)(Apple())
      , List.fill(99)(Rotten())
      , List.fill(88)(Rotten( desc=  "Pear"))
    )

    println("total price(campaign sale) = " + totalPrice)
    println(shoppingCartService.desc(items, campaignSale))

    assert(shoppingCartService.totalCost(items, campaignSale) == totalPrice)

    val items2 = List.concat(List.fill(qApples)(Apple()), List.fill(qOranges)(Orange()))
    assert(shoppingCartService.totalCost(items2, campaignSale) == totalPrice)
  }

}
