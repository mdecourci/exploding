package com.game.roulette

import org.scalatest.{BeforeAndAfter, FeatureSpec, GivenWhenThen, Matchers}

class PlayerCardGameSpec extends FeatureSpec with GivenWhenThen with BeforeAndAfter with Matchers {
  info("As a player with 47 cards")
  info("And only one Explosive card")
  info("I want to draw a card one after the other")
  info("So that a Blank card has no effect and can be discarded")
  info("And an Explosive card means I lose")

  val discardedPile = new Pile(List())

  before()

  feature("Player draws explosive cards or blank cards") {
    val player = new Player
    val playersHand = List()

    scenario("Player draws from a pile with a Single Blank card it is ignored") {
      Given("pile with a single Blank card")
      val pile = new Pile(List(Blank)).shuffle

      When("playing players hand")
      val endOfPlay = player.play(Play, playersHand, pile, discardedPile)

      val finalState = endOfPlay._1
      val finalHand = endOfPlay._2
      val finalDeckPile = endOfPlay._3

      Then("it is ignored")

      finalState should be (Finish)

      assert(finalHand.isEmpty)
      assert(finalDeckPile.isEmpty)
    }

    scenario("Player draws from a pile with Single explosive card and loses") {
      Given("pile with a single Explosive card")
      val pile = new Pile(List(Explosive))

      When("playing players hand")
      val endOfPlay = player.play(Play, playersHand, pile, discardedPile)

      val finalState = endOfPlay._1
      val finalHand = endOfPlay._2
      val finalDeckPile = endOfPlay._3

      Then("the player loses")

      finalState should be (Lose)
      assert(finalDeckPile.isEmpty)

      finalHand should have length 1
      finalHand contains Explosive
    }

    scenario("Player draws from a pile with many Blank cards") {
      Given("pile with many Blank cards")
      val pile = new Pile(List(Blank, Blank))

      When("playing players hand")
      val endOfPlay = player.play(Play, playersHand, pile, discardedPile)

      val finalState = endOfPlay._1
      val finalHand = endOfPlay._2
      val finalDeckPile = endOfPlay._3

      Then("there is no effect")

      finalState should be (Finish)

      assert(finalHand.isEmpty)

      finalDeckPile.cards should have length 1
      finalDeckPile.cards contains Blank
    }

    scenario("Player draws from a pile with one explosive card and other cards and loses") {

      Given("pile with an explosive card many other cards")
      val pile = new Pile(List(Other, Other, Explosive, Other, Other)).shuffle

      When("playing players hand")
      val endOfPlay = player.play(Play, playersHand, pile, discardedPile)

      val finalState = endOfPlay._1
      val finalHand = endOfPlay._2
      val finalDeckPile = endOfPlay._3

      Then("the player loses")

      finalState should be (Lose)

      assert((finalHand.length >=  1) || (finalHand.length <=  4))
      finalHand should contain atLeastOneOf (Other, Explosive)

      finalDeckPile.cards should contain atMostOneOf (Other, Explosive)
      finalDeckPile.cards shouldNot contain (Explosive)

      assert((finalDeckPile.cards.length >  1) || (finalDeckPile.cards.length <=  4))
    }
  }

  feature("Player has 1 Defuse card, and there are 2 defuse cards in the pile making a total of 50 in the deck") {
    val player = new Player
    val playersHand = List(Defuse)

    scenario("Player draws one Blank card, the turn finishes") {
      Given("pile with a single Blank card")
      val pile = new Pile(List(Blank)).shuffle

      When("playing players hand")
      val endOfPlay = player.play(Play, playersHand, pile, discardedPile)

      val finalState = endOfPlay._1
      val finalHand = endOfPlay._2
      val finalDeckPile = endOfPlay._3

      Then("it is ignored")

      finalState should be (Finish)

      finalHand should have length 1
      finalHand contains Defuse

      assert(finalDeckPile.isEmpty)
    }

    scenario("Player draws one Defuse card, the turn finishes") {
      Given("pile with a single Defuse card")
      val pile = new Pile(List(Defuse)).shuffle

      When("playing players hand")
      val endOfPlay = player.play(Play, playersHand, pile, discardedPile)

      val finalState = endOfPlay._1
      val finalHand = endOfPlay._2
      val finalDeckPile = endOfPlay._3

      Then("the player has 2 Defuse cards and finish")

      finalState should be (Finish)

      finalHand should contain (Defuse)
      finalHand should have length 2

      assert(finalDeckPile.isEmpty)
    }

    scenario("Player draws one Explosive card, the turn finishes") {
      Given("pile with a single Blank card")
      val pile = new Pile(List(Explosive)).shuffle

      When("playing players hand")
      val endOfPlay = player.play(Play, playersHand, pile, discardedPile)

      val finalState = endOfPlay._1
      val finalHand = endOfPlay._2
      val finalDeckPile = endOfPlay._3
      val finalDiscardedPile = endOfPlay._4

      Then("the player discards defuse card and returns the Explosive card to pile and re-shuffle cards")

      finalState should be (Finish)

      finalDiscardedPile.cards should contain (Defuse)
      finalDeckPile.cards should contain (Explosive)
      assert(finalHand.isEmpty)
    }
  }

}
