package io.cadmia.aoc2022.day06

import scala.io.Source

def solve(input: String, size: Int): Int = {
  input.toList
    .sliding(size)
    .takeWhile((window) => window.distinct.length != size)
    .length + size
}

@main def solvePuzzle(): Unit = {
  val input = Source
    .fromResource("aoc/2022/day06/input")
    .mkString

  val partOneSolution = solve(input, 4)
  val partTwoSolution = solve(input, 14)

  println(s"Part one: $partOneSolution")
  println(s"Part two: $partTwoSolution")
}
