package io.cadmia.aoc2022.day04

import scala.io.Source

type Assignments = Vector[Int]

type Score = 1 | 0

def stringToInt(string: String): Int = {
  string.toIntOption match
    case Some(number) => number
    case None => throw new Exception("Unable to convert input to number")
}

def processLine(line: String): Assignments = line.split(",").flatMap(range => range.split("-").map(stringToInt)).toVector

def isSubOrSuperset(assignments: Assignments): Score = {
  val assignmentOneRange = assignments(0) to assignments(1)
  val assignmentTwoRange = assignments(2) to assignments(3)
  if ((assignmentOneRange containsSlice assignmentTwoRange) || (assignmentTwoRange containsSlice assignmentOneRange)) 1 else 0
}

lazy val getLineScore = processLine.andThen(isSubOrSuperset)

@main def solvePuzzle(): Unit = {
  val score = Source.fromResource("aoc/2022/day04/input").getLines.foldLeft(0)((score, line) => {
    score + getLineScore(line)
  })

  println(s"Part one score is: $score")
}