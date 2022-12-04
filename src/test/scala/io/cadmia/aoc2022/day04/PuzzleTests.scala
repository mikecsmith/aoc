package io.cadmia.aoc2022.day04

import org.scalatest.funsuite.AnyFunSuite

class PuzzleTests extends AnyFunSuite {
  test("isSubOrSuperset should return 1 if range one is inside range two") {
    val oneInsideTwo: Assignments = Vector(3,4,1,5)
    assert(isSubOrSuperset(oneInsideTwo) == 1)

    val oneInsideTwoSameEnd: Assignments = Vector(3,5,1,5)
    assert(isSubOrSuperset(oneInsideTwoSameEnd) == 1)

    val oneInsideTwoSameStart: Assignments = Vector(3,4,3,5)
    assert(isSubOrSuperset(oneInsideTwoSameStart) == 1)
  }
  test("isSubOrSuperset should return 1 if range one contains range two") {
    val oneContainsTwo: Assignments = Vector(1,5,3,4)
    assert(isSubOrSuperset(oneContainsTwo) == 1)

    val oneContainsTwoSameEnd: Assignments = Vector(1,5,3,5)
    assert(isSubOrSuperset(oneContainsTwoSameEnd) == 1)

    val oneContainsTwoSameStart: Assignments = Vector(3,4,3,5)
    assert(isSubOrSuperset(oneContainsTwoSameStart) == 1)
  }

  test("isSubOrSuperset should return 1 if ranges are identical") {
    val oneIsTwoSingleValue: Assignments = Vector(1, 1, 1, 1)
    assert(isSubOrSuperset(oneIsTwoSingleValue) == 1)

    val oneIsTwoMultipleValues: Assignments = Vector(1, 5, 1, 5)
    assert(isSubOrSuperset(oneIsTwoMultipleValues) == 1)
  }

  test("isSubOrSuperset should return 0 if ranges overlap") {
    val oneOverlapsTwo: Assignments = Vector(1,5,3,6)
    assert(isSubOrSuperset(oneOverlapsTwo) == 0)

    val twoOverlapsOne: Assignments = Vector(2,7,1,6)
    assert(isSubOrSuperset(twoOverlapsOne) == 0)
  }
}