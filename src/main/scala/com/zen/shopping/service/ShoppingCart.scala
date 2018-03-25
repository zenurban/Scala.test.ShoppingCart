package com.zen.shopping.service

import com.zen.shopping.domain.Item
import com.zen.shopping.rules.Sale

import scala.math.BigDecimal

trait ShoppingCart {
  def totalCost(items: List[Item], itemsOffer: Map[Item, Sale]): BigDecimal
  def desc(items: List[Item], sale: Map[Item, Sale]): String
}

class ShoppingCartCampaignService extends ShoppingCart {
  def totalCost(items: List[Item], sale: Map[Item, Sale]): BigDecimal = {
    items.groupBy(identity)
      .map({ case (item, listOfItems) => item.price * sale(item).getAdjustedIemCount(listOfItems) })
      .sum
  }

  def desc(items: List[Item], sale: Map[Item, Sale]): String = {
    items.groupBy(identity)
      .map({ case (item, listOfItems) =>
        s"\n-->desc: ${item.itemType}: ${item.desc}(£${item.price}),total size: ${listOfItems.size}, sale size:  ${sale(item).getAdjustedIemCount(listOfItems)}"
      case (_) =>"\nintruder"
      }).mkString
  }
}

