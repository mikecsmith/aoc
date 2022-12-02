package io.cadmia.aoc2022.day02.RockPaperScissors

import scala.collection.immutable.HashMap

trait RockPaperScissors {
  private val rock = ("scissors", "paper")
  private val scissors = ("paper", "rock")
  private val paper = ("rock", "scissors")

  def loses(gesture: String): String = {
    gesture match
      case "rock" => rock(1)
      case "paper" => paper(1)
      case "scissors" => scissors(1)
      case _ => throw new Exception("Invalid input to losesAgainst")
  }

  def wins(gesture: String): String = {
    gesture match
      case "rock" => rock(0)
      case "paper" => paper(0)
      case "scissors" => scissors(0)
      case _ => throw new Exception("Invalid input to winsAgainst")
  }

  private def getChoiceScore(youPlayed: String): Int = {
    youPlayed match
      case "rock" => 1
      case "paper" => 2
      case "scissors" => 3
      case _ => throw new Exception("Invalid input to getChoiceScore")
  }

  private def getGameScore(outcome: String): Int = {
    outcome match
      case "win" => 6
      case "lose" => 0
      case "draw" => 3
      case _ => throw new Exception("Invalid input to getGameScore")
  }

  def getTotalScore(youPlayed: String, outcome: String): Int = getChoiceScore(youPlayed) + getGameScore(outcome)
}