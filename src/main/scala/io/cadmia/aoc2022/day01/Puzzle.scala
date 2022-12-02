package io.cadmia.aoc2022.day01

import scala.io.Source

@main def solvePuzzle(): Unit =
  var highestCalories = 0
  var currentCalories = 0

  Source.fromResource("aoc/2022/day01/input").getLines.foreach(line => {
    if (line.nonEmpty)
      currentCalories += line.toInt
    else
      if (currentCalories > highestCalories)
        highestCalories = currentCalories
      currentCalories = 0
  })
  println(highestCalories)

