package io.cadmia.aoc2022.day02

import scala.io.Source

import io.cadmia.aoc2022.day02.RockPaperScissors.RockPaperScissors

@main def solvePuzzle(): Unit = {
  val scores = Source.fromResource("aoc/2022/day02/input").getLines.foldLeft(0,0)((currentScores, line) => {
    val (currentPartOneScore, currentPartTwoScore) = currentScores
    val game = line.split(" ")
    (
      RockPaperScissors.calculatePartOneScore(game) + currentPartOneScore,
      RockPaperScissors.calculatePartTwoScore(game) + currentPartTwoScore
    )
  })

  val (partOneScore, partTwoScore) = scores
  println(s"Part one - score: $partOneScore")
  println(s"Part two - score: $partTwoScore")
}
