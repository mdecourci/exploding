# Functional programming approach to a Card Game player problem

## TODO: Command line run

## Iteration 1: Basic roulette ruse

* There are one player and 47 cards. One of the cards is explosive and the rest are blank.
* All the cards are shuffled and arranged face down in a draw pile.
* The player draws cards one after the other. If the card is blank, it has no effect, and can be discarded. If the card is explosive, the player loses.


## Iteration 2: Defuse cards

Enhance the game:

* We have three additional _Defuse_ cards, making a total of 50 in the deck.
* Game set up:
  1. Give one defuse card to the player.
  2. Put the remaining two defuse cards with the rest in the draw pile, shuffle and arrange face down.
* The player's turn consists of two steps:
   1. Draw one card
   2. There are four alternatives:
      * Blank card: the turn finishes.
      * Defuse card: add the defuse card to the player's hand and the turn finishes.
      * Explosive card, if the player has a defuse card: 
        1. Discard the defuse card onto the discard pile.
        2. Return the explosive card to the draw pile.
        3. Re-shuffle the draw pile.
      * Explosive card, if the player does not have a defuse card: The player loses.


## Extension ideas

As part of a face to face interview we like to seat together, discuss your submission and build up on it. We might pick from these examples below, or something else that we might find interesting in your code. _These are just examples in case you would like to think about them. They are not part of the unattended submission._

* Allowing the user to configure the number of cards of each type in the deck
* Allowing the user to configure the number of players
* Including _See the future_ cards that allow the player to reveal the next three cards and _Skip_ cards, which allow the player to skip one card from the drawing pile.


### Description
* There are one player and 47 cards. One of the cards is explosive and the rest are blank.
* All the cards are shuffled and arranged face down in a draw pile.
* The player draws cards one after the other. If the card is blank, it has no effect, and can be discarded. 
* If the card is explosive, the player loses.

### Functions
* `shuffle` - cards
* `arrange` - cards (face down?)
* `draws` card from pile of cards
* `one after the other` draw in sequence ?
* `discard`
* derived a players `turn`

### Candidates
* `shuffle` - input(cards), output(cards)
* `arrange` - input(cards), output(cards)
* `draws` - input(cards) or input(pile), output(card)
* `discard` - input(card), output(remaining cards) or output(new pile)
* `one after the other` draw next from head top/head of cards, not random draw ?

### States
* `Blank`
* `Explosive`
* `no effect` 
* `loses` (player)
* `discarded`

### Notes
* arrange() Pile
* shuffle() Pile, return Pile
* draw() from Pile, return card
* discard(card), return Pile

