package io.cadmia.aoc2022.day01

import scala.collection.mutable.ListBuffer
import scala.io.Source
@main def solvePuzzle(): Unit =
  var currentCalories = 0
  val calories = ListBuffer.empty[Int]

  Source.fromResource("aoc/2022/day01/input").getLines.foreach(line => {
    if (line.nonEmpty)
      currentCalories += line.toInt
    else
      calories.append(currentCalories)
      currentCalories = 0
  })

  val sortedCalories = calories.sorted

  println("Calorie Count:")
  printf("Largest Elf Snack Stash: %s\n", sortedCalories.takeRight(1).sum)
  printf("Top 3 Elf Snack Stash Calories: %s\n", sortedCalories.takeRight(3).sum)


