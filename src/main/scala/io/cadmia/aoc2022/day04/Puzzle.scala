package io.cadmia.aoc2022.day04

import scala.io.Source

type Assignments = Vector[Int]
type AssignmentRanges = (Range, Range)
type Score = 1 | 0

def stringToInt(string: String): Int = {
  string.toIntOption match
    case Some(number) => number
    case None => throw new Exception("Unable to convert input to number")
}
def calculatePartOneScore(assignmentRanges: AssignmentRanges): Score = if (
  assignmentRanges(0).containsSlice(assignmentRanges(1)) || assignmentRanges(1)
    .containsSlice(assignmentRanges(0))
) 1
else 0

def calculatePartTwoScore(assignmentRanges: AssignmentRanges): Score =
  if (assignmentRanges(0).intersect(assignmentRanges(1)).nonEmpty) 1 else 0

def calculatePartScores(assignmentRanges: AssignmentRanges): (Int, Int) = (
  calculatePartOneScore(assignmentRanges),
  calculatePartTwoScore(assignmentRanges)
)

def makeAssignmentRanges(assignments: Assignments): AssignmentRanges =
  (assignments(0) to assignments(1), assignments(2) to assignments(3))

def processLine(line: String): Assignments =
  line.split(",").flatMap(range => range.split("-").map(stringToInt)).toVector

lazy val getLineScores =
  processLine.andThen(makeAssignmentRanges).andThen(calculatePartScores)

@main def solvePuzzle(): Unit = {
  val scores = Source
    .fromResource("aoc/2022/day04/input")
    .getLines
    .foldLeft(0, 0)((scores, line) => {
      val (partOneLineScore, partTwoLineScore) = getLineScores(line)
      val (partOneScoreSum, partTwoScoreSum) = scores
      (partOneScoreSum + partOneLineScore, partTwoScoreSum + partTwoLineScore)
    })

  println(s"Part one score is: ${scores(0)}")
  println(s"Part two score is: ${scores(1)}")
}
