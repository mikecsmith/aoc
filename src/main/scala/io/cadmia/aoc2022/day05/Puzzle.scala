package io.cadmia.aoc2022.day05

import scala.io.Source
import scala.util.matching.Regex

/** Note for future me - solved part one and part two required one small tweak.
  * I was late finishing so instead of a full refactor to lazily process the
  * lines once for both parts I just wrapped it in a case class and passed in
  * the part to solve.
  */

case class Puzzle(part: 1 | 2) {
  private type Stack = List[String]
  private type StackRow = List[String]
  private type Stacks = List[Stack]

  private val GridPattern: Regex =
    """(?:(?:\s|\[)(\s|[A-Z])(?:\s|\])(?:\s|$))""".r.unanchored
  private val GridIndexPattern: Regex = """^(?:\s(\d)\s{1,2})+$""".r
  private val NewLinePattern: Regex = """^$""".r
  private val MovePattern: Regex = """move (\d+) from (\d+) to (\d+)""".r

  private def addValueToStack(
      stack: Stack,
      stackRow: StackRow,
      index: Int
  ): Stack = {
    val value = stackRow(index)
    if value == " " then stack else stack :+ value
  }

  private def processGridPattern(line: String, rawStacks: Stacks): Stacks = {
    val stackRow: StackRow =
      GridPattern.findAllMatchIn(line).map(_.group(1)).toList

    val stacks: Stacks = rawStacks.length match
      case 0 => (0 until stackRow.length).map(_ => List[String]()).toList
      case x if stackRow.length == x => rawStacks
      case _ => throw new Exception("Number of stacks in grid does not match")

    stacks.zipWithIndex.map { case (stack, index) =>
      addValueToStack(stack, stackRow, index)
    }
  }

  private def processMove(line: String, stacks: Stacks): Stacks = {
    val movePattern =
      MovePattern.findAllMatchIn(line).flatMap(_.subgroups).map(_.toInt).toList

    val amount = movePattern(0)
    val fromStackIndex = movePattern(1) - 1
    val toStackIndex = movePattern(2) - 1

    val cratesToMove =
      if this.part == 1 then stacks(fromStackIndex).take(amount).reverse
      else stacks(fromStackIndex).take(amount)

    stacks
      .updated(fromStackIndex, stacks(fromStackIndex).drop(amount))
      .updated(
        toStackIndex,
        cratesToMove ++ stacks(toStackIndex)
      )
  }

  private def processLine(line: String, stacks: Stacks): Stacks = {
    line match
      case GridPattern(_*)      => processGridPattern(line, stacks)
      case GridIndexPattern(_*) => stacks
      case NewLinePattern(_*)   => stacks
      case MovePattern(_*)      => processMove(line, stacks)
      case _ => throw new Exception("Invalid line in input file")
  }

  def solvePuzzle(): String = {
    val stacks: Stacks = List()
    val finalStacks = Source
      .fromResource("aoc/2022/day05/input")
      .getLines
      .foldLeft(stacks)((stacks, line) => {
        processLine(line, stacks)
      })

    finalStacks.map(_(0)).mkString
  }
}

@main def main(): Unit = {
  val partOneSolution = Puzzle(1).solvePuzzle()
  val partTwoSolution = Puzzle(2).solvePuzzle()

  println(s"Part 1 solution: $partOneSolution")
  println(s"Part 2 solution: $partTwoSolution")
}
