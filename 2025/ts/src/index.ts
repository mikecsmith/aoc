import { partOne as day1PartOne, partTwo as day1PartTwo } from "./day1.ts";
import { partOne as day2PartOne, partTwo as day2PartTwo } from "./day2.ts";

console.log("Password for day one, part one:", await day1PartOne());
console.log("Password for day one, part two:", await day1PartTwo());
console.log("Sum of invalid product IDs for day two, part one:", day2PartOne());
console.log("Sum of invalid product IDs for day two, part two:", day2PartTwo());
