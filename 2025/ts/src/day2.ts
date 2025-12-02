import { readFileSync } from "node:fs";

const isTuple = (arr: string[]): arr is [string, string] => {
  return arr.length === 2;
};

const getIds = (input: string): number[] => {
  const content = readFileSync(`${import.meta.dirname}/../../resources/${input}`, "utf-8");

  return content.split(",").reduce((array, range) => {
    const ids = range.split("-");
    if (!isTuple(ids)) throw new Error("Invalid input format");
    const start = parseInt(ids[0]);
    const end = parseInt(ids[1]);

    for (let id = start; id <= end; id++) {
      array.push(id);
    }
    return array;
  }, [] as number[]);
};

export const isRepeatedSequence = (sequence: string): boolean => {
  if (sequence.length % 2 !== 0) return false;
  const middle = sequence.length / 2;
  return sequence.slice(0, middle) === sequence.slice(middle);
};

export const isInvalidId = (id: string): boolean => {
  if (id.length === 1) return false;
  return (id + id).slice(1, -1).includes(id);
};

export function partOne(input = "day2") {
  const ids = getIds(input);
  let total = 0;
  for (const id of ids) {
    if (isRepeatedSequence(id.toString())) {
      total += id;
    }
  }
  return total;
}

export function partTwo(input = "day2") {
  const ids = getIds(input);
  let total = 0;
  for (const id of ids) {
    if (isInvalidId(id.toString())) {
      total += id;
    }
  }
  return total;
}
