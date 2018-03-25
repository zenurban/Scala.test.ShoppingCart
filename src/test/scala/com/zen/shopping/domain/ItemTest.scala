package com.zen.shopping.domain

import org.scalatest.{FlatSpec, Matchers}

class ItemTest extends FlatSpec with Matchers {
  val defaultApple = new Apple
  val rottenApple = Apple("Rotten", "Apple", 333.3)

  defaultApple should not be null

  defaultApple should have(
    'itemType ("Fruit"),
    'desc ("Apple"),
    'price (0.6)
  )

  rottenApple should not be null

  rottenApple should have(
    'itemType ("Rotten"),
    'desc ("Apple"),
    'price (333.3)
  )

  Orange() should have(
    'itemType ("Fruit"),
    'desc ("Orange"),
    'price (0.25)
  )
}
