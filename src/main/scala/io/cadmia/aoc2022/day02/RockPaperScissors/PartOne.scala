package io.cadmia.aoc2022.day02.RockPaperScissors

import io.cadmia.aoc2022.day02

import scala.collection.immutable.HashMap
import scala.io.Source

object PartOne extends RockPaperScissors {
  private def getOutcome(youPlayed: String, opponentPlayed: String): String = {
    if youPlayed == opponentPlayed then
      "draw"
    else if youPlayed == loses(opponentPlayed) then
      "win"
    else if youPlayed == wins(opponentPlayed) then
      "lose"
    else
      throw new Exception("getOutcome unable to determine outcome")
  }

  def decryptCheatsheet(letter: String): String = {
    letter match
      case "A" | "X" => "rock"
      case "B" | "Y" => "paper"
      case "C" | "Z" => "scissors"
      case _ => throw new Exception("Invalid input to PartOne.decryptCheatsheet")
  }

  def calculateScore(game: Array[String]): Int = {
    val Array(opponentPlayed, youPlayed) = game.map(decryptCheatsheet)
    val outcome = getOutcome(youPlayed, opponentPlayed)
    getTotalScore(youPlayed, outcome)
  }
}