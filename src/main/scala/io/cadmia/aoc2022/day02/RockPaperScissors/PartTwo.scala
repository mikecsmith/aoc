package io.cadmia.aoc2022.day02.RockPaperScissors

import scala.collection.immutable.HashMap
import scala.io.Source

object PartTwo extends RockPaperScissors {
  private def getYouPlayed(outcome: String, opponent: String): String = {
    outcome match
      case "draw" => opponent
      case "lose" => wins(opponent)
      case "win" => loses(opponent)
      case _ => throw new Exception("Invalid input to getYouPlayed")
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
    val Array(colOne, colTwo) = game
    val opponent = decryptCheatsheet(colOne)
    val outcome = decryptCheatsheet(colTwo)
    val you = getYouPlayed(outcome, opponent)

    getTotalScore(you, outcome)
  }
}