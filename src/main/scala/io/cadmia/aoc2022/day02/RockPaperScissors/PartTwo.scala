package io.cadmia.aoc2022.day02.RockPaperScissors

import scala.collection.immutable.HashMap
import scala.io.Source

object PartTwo extends RockPaperScissors {
  private def getPlayerMove(outcome: String, opponentMove: String): String = {
    outcome match
      case "draw" => opponentMove
      case "lose" => getLosesAgainstMove(opponentMove)
      case "win" => getWinsAgainstMove(opponentMove)
      case _ => throw new Exception("Invalid input to getPlayerMove")
  }

  def decryptCheatsheet(code: String): String  = {
    code match
      case "A" => "rock"
      case "B" => "paper"
      case "C" => "scissors"
      case "X" => "lose"
      case "Y" => "draw"
      case "Z" => "win"
      case _ => throw new Exception("Invalid input to PartTwo.decryptCheatsheet")
  }

  def calculateScore(game: Array[String]): Int = {
    val Array(opponentMove, outcome) = game.map(decryptCheatsheet)
    val playerMove = getPlayerMove(outcome, opponentMove)

    getScore(playerMove, outcome)
  }
}