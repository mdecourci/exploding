package com.game.roulette

import scala.util.Random

class Player {

  def play(startPlayerState: PlayerState, startPlayersHand: List[Card], startDrawPile: Pile, startDiscardPile: Pile) : Tuple4[PlayerState, List[Card], Pile, Pile] = {

    def turn(playerState: PlayerState, playersHand: List[Card], drawPile: Pile, discardPile: Pile) : Tuple4[PlayerState, List[Card], Pile, Pile] = {
      if (playerState != Lose && playerState != Finish) {

        val currentPlay = drawPile.draw
        val drawnCard = currentPlay._1
        val currentPile = currentPlay._2

        val currentHand = playersHand ++ List(drawnCard)

        val nextDiscardPile = addToDiscardPile(drawnCard, currentHand, discardPile)
        val nextPlayersHand = addToPlayersHand(drawnCard, currentHand)
        val nextDrawPile = addToDrawPile(drawnCard, currentHand, currentPile)

        val nextPlayerState = updatePlayerState(playerState, drawnCard, currentHand)
        turn(nextPlayerState, nextPlayersHand, nextDrawPile, nextDiscardPile)
      }
      else Tuple4(playerState, playersHand, drawPile, discardPile)
    }

    turn(startPlayerState, startPlayersHand, startDrawPile, startDiscardPile)
  }

  private def updatePlayerState(playerState: PlayerState, card: Card, playersHand: List[Card]) : PlayerState = {
    if (card == Blank || card == Defuse || (card == Explosive && playersHand.contains(Defuse))) Finish else if (card == Explosive && !playersHand.contains(Defuse)) Lose else playerState
  }

  private def addToDrawPile(card: Card, playersHand: List[Card], drawPile: Pile) : Pile = {
    if (card == Explosive && playersHand.contains(Defuse)) return new Pile(drawPile.cards ++ List(card)).shuffle else drawPile
  }

  private def addToPlayersHand(card: Card, playersHand: List[Card]) : List[Card]  = {
    if (card == Blank) playersHand.filterNot(_ == Blank)  else if (card == Explosive && playersHand.contains(Defuse)) playersHand.filterNot(c => (c == Defuse ||  c == Explosive)) else playersHand
  }

  private def addToDiscardPile(card: Card, playersHand: List[Card], discardPile: Pile) : Pile  = {
    if (card == Explosive && playersHand.contains(Defuse)) new Pile(discardPile.cards ++ List(Defuse)) else discardPile
  }
}
