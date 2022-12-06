package io.cadmia.aoc2022.day02.RockPaperScissors

//Enums
enum Move:
  case Rock, Paper, Scissors

enum Outcome:
  case Win, Lose, Draw

object RockPaperScissors {
  // Constants
  private val rock = (Move.Scissors, Move.Paper)
  private val scissors = (Move.Paper, Move.Rock)
  private val paper = (Move.Rock, Move.Scissors)

  // Private methods
  private def getWinsAgainstMove(move: Move): Move = {
    move match
      case Move.Rock     => rock(1)
      case Move.Paper    => paper(1)
      case Move.Scissors => scissors(1)
  }

  private def getLosesAgainstMove(move: Move): Move = {
    move match
      case Move.Rock     => rock(0)
      case Move.Paper    => paper(0)
      case Move.Scissors => scissors(0)
  }

  private def getPlayerMoveScore(move: Move): Int = {
    move match
      case Move.Rock     => 1
      case Move.Paper    => 2
      case Move.Scissors => 3
  }

  private def getGameScore(outcome: Outcome): Int = {
    outcome match
      case Outcome.Win  => 6
      case Outcome.Lose => 0
      case Outcome.Draw => 3
  }

  private def decryptOpponentMove(code: String): Move = {
    code match
      case "A" => Move.Rock
      case "B" => Move.Paper
      case "C" => Move.Scissors
  }

  private def decryptPlayerMove(code: String): Move = {
    code match
      case "X" => Move.Rock
      case "Y" => Move.Paper
      case "Z" => Move.Scissors
  }

  private def decryptOutcome(code: String): Outcome = {
    code match
      case "X" => Outcome.Lose
      case "Y" => Outcome.Draw
      case "Z" => Outcome.Win
  }

  private def getGameOutcome(playerMove: Move, opponentMove: Move): Outcome = {
    playerMove match
      case draw if opponentMove == draw                      => Outcome.Draw
      case win if getWinsAgainstMove(opponentMove) == win    => Outcome.Win
      case lose if getLosesAgainstMove(opponentMove) == lose => Outcome.Lose
      case _ =>
        throw new Exception("getOutcome unable to determine getGameOutcome")
  }
  private def getPlayerMove(outcome: Outcome, opponentMove: Move): Move = {
    outcome match
      case Outcome.Draw => opponentMove
      case Outcome.Lose => getLosesAgainstMove(opponentMove)
      case Outcome.Win  => getWinsAgainstMove(opponentMove)
  }

  private def getScore(playerMove: Move, outcome: Outcome): Int =
    getPlayerMoveScore(playerMove) + getGameScore(outcome)

  // Solutions
  def calculatePartOneScore(game: Array[String]): Int = {
    val opponentMove = decryptOpponentMove(game(0))
    val playerMove = decryptPlayerMove(game(1))
    val outcome = getGameOutcome(playerMove, opponentMove)
    getScore(playerMove, outcome)
  }

  def calculatePartTwoScore(game: Array[String]): Int = {
    val opponentMove = decryptOpponentMove(game(0))
    val outcome = decryptOutcome(game(1))
    val playerMove = getPlayerMove(outcome, opponentMove)
    getScore(playerMove, outcome)
  }
}
