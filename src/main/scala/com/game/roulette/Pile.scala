package com.game.roulette

import scala.util.Random

class Pile(val cards: List[Card]) {

  def shuffle = new Pile(Random.shuffle(cards))
  def isEmpty = cards.isEmpty
  def draw : Tuple2[Card, Pile]  = Tuple2(cards.head, new Pile(cards.tail))
}