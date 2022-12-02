package io.cadmia.aoc2022.day02.RockPaperScissors

import scala.collection.immutable.HashMap

trait RockPaperScissors {
  def decryptCheatsheet(code: String): String
  def calculateScore(game: Array[String]): Int

  private val rock = ("scissors", "paper")
  private val scissors = ("paper", "rock")
  private val paper = ("rock", "scissors")

  def getWinsAgainstMove(move: String): String = {
    move match
      case "rock" => rock(1)
      case "paper" => paper(1)
      case "scissors" => scissors(1)
      case _ => throw new Exception("Invalid input to getWinsAgainstMove")
  }

  def getLosesAgainstMove(move: String): String = {
    move match
      case "rock" => rock(0)
      case "paper" => paper(0)
      case "scissors" => scissors(0)
      case _ => throw new Exception("Invalid input to getLosesAgainstMove")
  }

  private def getPlayerMoveScore(move: String): Int = {
    move match
      case "rock" => 1
      case "paper" => 2
      case "scissors" => 3
      case _ => throw new Exception("Invalid input to getPlayerMoveScore")
  }

  private def getGameScore(outcome: String): Int = {
    outcome match
      case "win" => 6
      case "lose" => 0
      case "draw" => 3
      case _ => throw new Exception("Invalid input to getGameScore")
  }

  def getScore(playerMove: String, outcome: String): Int = getPlayerMoveScore(playerMove) + getGameScore(outcome)
}