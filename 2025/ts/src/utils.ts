import { createReadStream } from "node:fs";
import { createInterface } from "node:readline";

export function getInputStream(input: string) {
  const stream = createReadStream(`${import.meta.dirname}/../../resources/${input}`);
  const iterator = createInterface({ input: stream, crlfDelay: Infinity });

  return iterator;
}
