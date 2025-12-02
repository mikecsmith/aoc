import { getInputStream } from "./utils.ts";

const DialSize = 100;
const InvalidInputError = new Error("Invalid input");

const getSteps = (line: string): number => {
  const regex = /([LR])(\d+)/;
  const matches = regex.exec(line);

  if (!matches) throw InvalidInputError;

  const direction = matches[1];
  const stepsStr = matches[2];

  if (!direction || !stepsStr) throw InvalidInputError;

  const steps = parseInt(stepsStr);

  if (direction === "L") return -steps;

  return steps;
};

export const getEndPosition = (startPosition: number, steps: number): number => {
  const endPosition = (startPosition + steps) % DialSize;
  if (endPosition < 0) return endPosition + DialSize;
  return endPosition;
};

export const getTimesAtZero = (startPosition: number, steps: number) => {
  const rawPosition = startPosition + steps;

  if (rawPosition >= DialSize) {
    return Math.floor(rawPosition / DialSize);
  }

  if (rawPosition < 0) {
    const crossings = Math.floor(Math.abs(rawPosition) / DialSize) + 1;
    if (startPosition === 0) return crossings - 1;
    return crossings;
  }

  if (rawPosition === 0) {
    return 1;
  }

  return 0;
};

export async function partOne(input = "day1") {
  let position = 50;
  let password = 0;

  for await (const line of getInputStream(input)) {
    const steps = getSteps(line);
    const endPosition = getEndPosition(position, steps);
    if (endPosition === 0) password += 1;
    position = endPosition;
  }
  return password;
}

export async function partTwo(input = "day1") {
  let position = 50;
  let password = 0;

  for await (const line of getInputStream(input)) {
    const steps = getSteps(line);
    password += getTimesAtZero(position, steps);
    position = getEndPosition(position, steps);
  }
  return password;
}
