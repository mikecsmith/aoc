package io.cadmia.aoc2022.day04

import org.scalatest.funsuite.AnyFunSuite

class PuzzleTests extends AnyFunSuite {
  // Identical ranges
  test(
    "calculatePartOneScore & calculatePartTwoScore should return 1 if ranges are identical"
  ) {
    val oneIsTwoSingleValue: AssignmentRanges = (1 to 1, 1 to 1)
    assert(calculatePartOneScore(oneIsTwoSingleValue) == 1)
    assert(calculatePartTwoScore(oneIsTwoSingleValue) == 1)

    val oneIsTwoMultipleValues: AssignmentRanges = (1 to 5, 1 to 5)
    assert(calculatePartOneScore(oneIsTwoMultipleValues) == 1)
    assert(calculatePartTwoScore(oneIsTwoMultipleValues) == 1)
  }

  // Contains
  test(
    "calculatePartOneScore & calculatePartTwoScore should return 1 if range one contains range two"
  ) {
    val oneContainsTwo: AssignmentRanges = (1 to 5, 3 to 4)
    assert(calculatePartOneScore(oneContainsTwo) == 1)
    assert(calculatePartTwoScore(oneContainsTwo) == 1)

    val oneContainsTwoSameEnd: AssignmentRanges = (1 to 5, 3 to 5)
    assert(calculatePartOneScore(oneContainsTwoSameEnd) == 1)
    assert(calculatePartTwoScore(oneContainsTwoSameEnd) == 1)

    val oneContainsTwoSameStart: AssignmentRanges = (3 to 4, 3 to 5)
    assert(calculatePartOneScore(oneContainsTwoSameStart) == 1)
    assert(calculatePartTwoScore(oneContainsTwoSameStart) == 1)
  }

  test(
    "calculatePartOneScore & calculatePartTwoScore should return 1 if range two contains range one"
  ) {
    val oneInsideTwo: AssignmentRanges = (3 to 4, 1 to 5)
    assert(calculatePartOneScore(oneInsideTwo) == 1)
    assert(calculatePartTwoScore(oneInsideTwo) == 1)

    val oneInsideTwoSameEnd: AssignmentRanges = (3 to 5, 1 to 5)
    assert(calculatePartOneScore(oneInsideTwoSameEnd) == 1)
    assert(calculatePartTwoScore(oneInsideTwoSameEnd) == 1)

    val oneInsideTwoSameStart: AssignmentRanges = (3 to 4, 3 to 5)
    assert(calculatePartOneScore(oneInsideTwoSameStart) == 1)
    assert(calculatePartTwoScore(oneInsideTwoSameStart) == 1)
  }

  // Intersection
  test(
    "calculatePartOneScore && calculatePartTwoScore should return 0 if ranges do not intersect"
  ) {
    val oneDifferentToTwo: AssignmentRanges = (1 to 5, 6 to 7)
    assert(calculatePartOneScore(oneDifferentToTwo) == 0)
    assert(calculatePartTwoScore(oneDifferentToTwo) == 0)

    val twoDifferentToOne: AssignmentRanges = (99 to 100, 1000 to 10006)
    assert(calculatePartOneScore(twoDifferentToOne) == 0)
    assert(calculatePartTwoScore(twoDifferentToOne) == 0)
  }

  test("calculatePartOneScore should return 0 if ranges intersect") {
    val oneOverlapsTwo: AssignmentRanges = (1 to 5, 3 to 6)
    assert(calculatePartOneScore(oneOverlapsTwo) == 0)

    val twoOverlapsOne: AssignmentRanges = (2 to 7, 1 to 6)
    assert(calculatePartOneScore(twoOverlapsOne) == 0)
  }

  test("calculatePartTwoScore should return 1 if ranges intersect") {
    val oneOverlapsTwo: AssignmentRanges = (1 to 5, 3 to 6)
    assert(calculatePartTwoScore(oneOverlapsTwo) == 1)

    val twoOverlapsOne: AssignmentRanges = (2 to 7, 1 to 6)
    assert(calculatePartTwoScore(twoOverlapsOne) == 1)
  }
}
