package com.game.roulette

trait PlayerState
case object Lose extends PlayerState
case object NoEffect extends PlayerState
case object Discard extends PlayerState

case object Start extends PlayerState
case object Play extends PlayerState
case object Finish extends PlayerState
