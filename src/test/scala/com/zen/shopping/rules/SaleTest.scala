package com.zen.shopping.rules

import com.zen.shopping.domain.{Apple, Orange}
import org.scalatest.{FlatSpec, Matchers}

class SaleTest extends FlatSpec with Matchers {

  "An empty shopping cart " should "have 0 items" in {
    assert(RawSale.getAdjustedIemCount(List()) == 0)
    assert(OneForTwoSale.getAdjustedIemCount(List()) == 0)
    assert(ThreeForTwoSale.getAdjustedIemCount(List()) == 0)
  }

  "Shopping cart with 1 item " should "have 1 item" in {
    assert(RawSale.getAdjustedIemCount(List(new Orange)) == 1)
    assert(OneForTwoSale.getAdjustedIemCount(List(new Apple)) == 1)
    assert(ThreeForTwoSale.getAdjustedIemCount(List(new Orange)) == 1)
  }

  "Shopping cart with 5 apples and OneForTwoSale " should "have 3 adjusted items" in {
    val offer: Sale = OneForTwoSale
    assert(offer.getAdjustedIemCount(List.fill(5)(Apple())) == 3)
    println(offer.desc(List.concat(List.fill(5)(Apple()), List.fill(6)(Orange()))))
  }

  "Shopping cart with 6 apples and ThreeForTwoSale " should "have 4 adjusted items" in {
    assert(ThreeForTwoSale.getAdjustedIemCount(List.fill(6)(Apple())) == 4)
  }
  
}
