package io.cadmia.aoc2022.day03

import scala.io.Source

// Types
type Rucksack = Vector[Set[Char]]

// Constants
lazy val ElfGroupSize = 3
lazy val LastElf = ElfGroupSize - 1
lazy val UncheckedRucksack: Rucksack = Vector.fill(ElfGroupSize)(Set.empty)
lazy val LookupTable = (('a' to 'z') ++ ('A' to 'Z')).zipWithIndex.map(t => (t(0), t(1) + 1)).toMap

// Data Structures
case class BadgeState(prioritySum: Int = 0, currentElf: Int = 0, checkedRucksacks: Rucksack = UncheckedRucksack)

// Shared utils
def getPriorityValue(char: Char): Int = {
  val GetPriorityError = () => throw new Exception("Unable to retrieve priority")
  LookupTable.getOrElse(char, GetPriorityError())
}

def getDuplicate(rucksack: Rucksack): Char = {
  val duplicates = rucksack.reduceLeft((a, b) => a.intersect(b))
  duplicates.size match
    case 1 => duplicates.head
    case _ => throw new Exception("More than one duplicate in input")
}

def makeCharSetFromString(s: String): Set[Char] = {
  s.toCharArray.toSet
}

// Curried Functions
lazy val getPriority = getDuplicate.andThen(getPriorityValue)
lazy val getDuplicatePriorityForLine = makeRucksackCompartments.andThen(getPriority)

// Part One utils
def makeRucksackCompartments(line: String): Rucksack = {
  val midPoint = line.length / 2
  val s1 = makeCharSetFromString(line.substring(0, midPoint))
  val s2 = makeCharSetFromString(line.substring(midPoint))
  Vector(s1, s2)
}

// Part Two utils
def increment = (x: Int) => x + 1

def processBadgeLine(badgeState: BadgeState, line: String): BadgeState = {
  val newRucksackContents = makeCharSetFromString(line)
  val updatedRucksacks = badgeState.checkedRucksacks.updated(badgeState.currentElf, newRucksackContents)
  if badgeState.currentElf == LastElf then
    val updatedPriority = badgeState.prioritySum + getPriority(updatedRucksacks)
    BadgeState(updatedPriority, 0, UncheckedRucksack)
  else
    BadgeState(badgeState.prioritySum, increment(badgeState.currentElf), updatedRucksacks)
}

// Main

@main def solvePuzzle(): Unit = {
  val (duplicatePrioritySum, badgeState) = Source.fromResource("aoc/2022/day03/input").getLines.foldLeft(0, BadgeState())((acc, line) => {
    val (duplicatePrioritySum, badgeState) = acc
    val newDuplicatePrioritySum = duplicatePrioritySum + getDuplicatePriorityForLine(line)
    val newBadgeState = processBadgeLine(badgeState, line)
    (newDuplicatePrioritySum, newBadgeState)
  })

  println(s"Part one priority score: $duplicatePrioritySum")
  println(s"Part two priority score: ${badgeState.prioritySum}")
}
