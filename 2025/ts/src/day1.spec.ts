import { describe, it, expect } from "vitest";
import { getEndPosition, getTimesAtZero, partOne, partTwo } from "./day1.ts";

describe("Day 1 - Integration", () => {
  it("Part One solves sample", async () => expect(await partOne("test1")).toBe(3));
  it("Part Two solves sample", async () => expect(await partTwo("test1")).toBe(6));
});

describe("getEndPosition", () => {
  it("Positive in bounds", () => {
    expect(getEndPosition(10, 20)).toBe(30);
  });
  it("Positive wrapping", () => {
    expect(getEndPosition(95, 10)).toBe(5); // 105 % 100 = 5
  });
  it("Positive multi-wrapping", () => {
    expect(getEndPosition(10, 250)).toBe(60); // 260 % 100 = 60
  });

  it("Negative in bounds", () => {
    expect(getEndPosition(50, -20)).toBe(30);
  });
  it("Negative wrapping", () => {
    expect(getEndPosition(10, -20)).toBe(90);
  });
  it("Negative multi-wrapping", () => {
    expect(getEndPosition(0, -150)).toBe(50);
  });
  it("Zero steps", () => {
    expect(getEndPosition(75, 0)).toBe(75);
  });
});

describe("getTimesAtZero", () => {
  it("Positive in bounds", () => {
    expect(getTimesAtZero(0, 14)).toBe(0);
  });
  it("Positive wrapping", () => {
    expect(getTimesAtZero(95, 60)).toBe(1);
  });
  it("Positive exact landing", () => {
    expect(getTimesAtZero(52, 48)).toBe(1);
  });
  it("Positive multi-wrapping", () => {
    expect(getTimesAtZero(50, 150)).toBe(2);
  });
  it("Positive full loop starting at 0", () => {
    expect(getTimesAtZero(0, 100)).toBe(1);
  });

  it("Negative in bounds", () => {
    expect(getTimesAtZero(82, -30)).toBe(0);
  });
  it("Negative wrapping", () => {
    expect(getTimesAtZero(50, -68)).toBe(1);
  });
  it("Negative exact landing", () => {
    expect(getTimesAtZero(55, -55)).toBe(1);
  });
  it("Negative multi-wrapping", () => {
    expect(getTimesAtZero(50, -250)).toBe(3);
  });
  it("Negative full loop starting at 0", () => {
    expect(getTimesAtZero(0, -100)).toBe(1);
  });
  it("Negative starting at 0 with less than 100 steps", () => {
    expect(getTimesAtZero(0, -5)).toBe(0);
  });
});
