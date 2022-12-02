package io.cadmia.aoc2022.day02.RockPaperScissors

import scala.collection.immutable.HashMap
import scala.io.Source

object PartOne extends RockPaperScissors {
  private def getGameOutcome(playerMove: String, opponentMove: String): String = {
    if playerMove == opponentMove then
      "draw"
    else if playerMove == getWinsAgainstMove(opponentMove) then
      "win"
    else if playerMove == getLosesAgainstMove(opponentMove) then
      "lose"
    else
      throw new Exception("getOutcome unable to determine getGameOutcome")
  }

  protected def decryptCheatsheet(code: String): String = {
    code match
      case "A" | "X" => "rock"
      case "B" | "Y" => "paper"
      case "C" | "Z" => "scissors"
      case _ => throw new Exception("Invalid input to PartOne.decryptCheatsheet")
  }

  def calculateScore(game: Array[String]): Int = {
    val Array(opponetMove, playerMove) = game.map(decryptCheatsheet)
    val outcome = getGameOutcome(playerMove, opponetMove)
    getScore(playerMove, outcome)
  }
}