import { describe, it, expect } from "vitest";
import { isInvalidId, isRepeatedSequence, partOne, partTwo } from "./day2.ts";

describe("isRepeatedSequence", () => {
  it("rejects any IDs with an odd length", () => {
    expect(isRepeatedSequence("5")).toBe(false);
    expect(isRepeatedSequence("121")).toBe(false);
    expect(isRepeatedSequence("12312")).toBe(false);
    expect(isRepeatedSequence("1234123")).toBe(false);
  });

  it("identifies valid repeated sequence IDs", () => {
    expect(isRepeatedSequence("11")).toBe(true);
    expect(isRepeatedSequence("1212")).toBe(true);
    expect(isRepeatedSequence("123123")).toBe(true);
    expect(isRepeatedSequence("12341234")).toBe(true);
  });
});

describe("isInvalidId", () => {
  it("rejects IDs without any repeated sequences", () => {
    expect(isInvalidId("12")).toBe(false);
    expect(isInvalidId("1234")).toBe(false);
    expect(isInvalidId("123456")).toBe(false);
    expect(isInvalidId("12345678")).toBe(false);
  });

  it("identifies IDs with any repeated sequences", () => {
    expect(isInvalidId("1111")).toBe(true); // "1" repeated
    expect(isInvalidId("121212")).toBe(true); // "12" repeated
    expect(isInvalidId("123123123")).toBe(true); // "123" repeated
    expect(isInvalidId("123412341234")).toBe(true); // "1234" repeated
  });
});

describe("Day 2 - Integration", () => {
  it("Part One solves sample", () => {
    expect(partOne("test2")).toBe(1227775554);
  });
  it("Part Two solves sample", () => {
    expect(partTwo("test2")).toBe(4174379265);
  });
});
