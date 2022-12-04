package io.cadmia.aoc2022.day01

import scala.io.Source

lazy val LeaderboardSize = 3
case class SnackStash(
    caloriesSum: Int = 0,
    leaderboard: Vector[Int] = Vector.fill(LeaderboardSize)(0)
) {
  private def updatedLeaderboard =
    (caloriesSum +: leaderboard).sorted.takeRight(LeaderboardSize)

  def addSnack(snackCalories: String): SnackStash = {
    snackCalories.toIntOption match
      case None           => SnackStash(0, updatedLeaderboard)
      case Some(calories) => SnackStash(caloriesSum + calories, leaderboard)
  }

  def finalLeaderboard = updatedLeaderboard
}

@main def solvePuzzle(): Unit = {
  val leaderboard = Source
    .fromResource("aoc/2022/day01/input")
    .getLines
    .foldLeft(SnackStash())({ case (snackStash, snackCalories) =>
      snackStash.addSnack(snackCalories)
    })

  println("Calorie Count:")
  println(s"Largest Elf Snack Stash: ${leaderboard.finalLeaderboard.last}")
  printf(
    s"Top ${LeaderboardSize} Elf Snack Stash calories: ${leaderboard.finalLeaderboard.sum}"
  )
}
