use anyhow::Result;

pub mod aoc_helpers;
pub mod day_one;

fn main() -> Result<()> {
    let day_one_part_one = day_one::part_one()?;
    println!("Day one, part one: {}", day_one_part_one);

    let day_one_part_two = day_one::part_two()?;
    println!("Day one, part two: {}", day_one_part_two);
    Ok(())
}
