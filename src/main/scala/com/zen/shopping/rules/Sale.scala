package com.zen.shopping.rules

import com.zen.shopping.domain.Item

trait Sale {
  def getAdjustedIemCount(items: List[Item]): Int
  def desc(items: List[Item]) : String  = s"adusted size: ${getAdjustedIemCount(items)}"
}

object RawSale extends Sale {
  def getAdjustedIemCount(items: List[Item]): Int = items.size
//  def desc(items: List[Item]) : String = ""
}

object OneForTwoSale extends Sale {
  def getAdjustedIemCount(items: List[Item]): Int = items.size / 2 + items.size % 2
}

object ThreeForTwoSale extends Sale {
  def getAdjustedIemCount(items: List[Item]): Int = (2 * (items.size / 3)) + items.size % 3
}

object NoSale extends Sale {
  def getAdjustedIemCount(items: List[Item]): Int = 0
}
