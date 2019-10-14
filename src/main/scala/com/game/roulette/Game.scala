package com.game.roulette

import com.typesafe.config.ConfigFactory

object Game {
  def main(args: Array[String]): Unit = {
    val config = ConfigFactory.load().getConfig("cards")
    println(config.getString("numberOfPlayers"))
  }

}
