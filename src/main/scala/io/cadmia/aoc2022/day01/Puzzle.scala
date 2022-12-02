package io.cadmia.aoc2022.day01

import scala.io.Source
import scala.util.Try

def processCalorieLines = (tuple: (Int, Vector[Int]), line: String) => {
  val (currentCaloriesSum, leaderboard) = tuple
  val currentLineCalories = Try(line.toInt).getOrElse(0)
  if line.nonEmpty then
    (currentCaloriesSum + currentLineCalories, leaderboard)
  else
    val (lowestCalorieCount, lowestCalorieCountPos) = leaderboard.zipWithIndex.min
    (0, if currentCaloriesSum > lowestCalorieCount then leaderboard.updated(lowestCalorieCountPos, currentCaloriesSum) else leaderboard)
  end if
}

@main def solvePuzzle(): Unit = {
  val leaderboardSize = 3
  val initLeaderboard = Vector.fill(leaderboardSize)(0)
  val caloriesLeaderboard = Source.fromResource("aoc/2022/day01/input").getLines
    .foldLeft(0, initLeaderboard)(processCalorieLines)._2.sorted

  println("Calorie Count:")
  printf("Largest Elf Snack Stash: %s\n", caloriesLeaderboard.last)
  printf("Top %s Elf Snack Stash Calories: %s\n", leaderboardSize, caloriesLeaderboard.takeRight(leaderboardSize).sum)
}

