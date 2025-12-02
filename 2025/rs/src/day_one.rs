use anyhow::{Result, anyhow};
use regex::Regex;
use std::sync::LazyLock;

use crate::aoc_helpers;

const DIALSIZE: i32 = 100;

static LINE_REGEX: LazyLock<Regex> =
    LazyLock::new(|| Regex::new(r"^([LR])(\d+)$").expect("Valid regular expression"));

fn parse_line_to_steps(line: &str) -> Result<i32> {
    let invalid_input_error = "Invalid Input";
    let captures = LINE_REGEX
        .captures(line)
        .ok_or_else(|| anyhow!(invalid_input_error))?;

    let steps = captures[2].parse::<i32>()?;

    match &captures[1] {
        "L" => Ok(-steps),
        "R" => Ok(steps),
        _ => Err(anyhow!(invalid_input_error)),
    }
}

pub fn part_one() -> Result<i32> {
    let lines_iter = aoc_helpers::get_input_iter("day1")?;

    let mut position = 50;
    let mut password = 0;

    for line_result in lines_iter {
        let line = line_result?;
        let steps = parse_line_to_steps(&line)?;
        let end_position = (position + steps).rem_euclid(DIALSIZE);
        if end_position == 0 {
            password += 1
        }
        position = end_position;
    }
    Ok(password)
}

pub fn part_two() -> Result<i32> {
    let lines_iter = aoc_helpers::get_input_iter("day1")?;

    let mut position = 50;
    let mut password = 0;

    for line_result in lines_iter {
        let line = line_result?;
        let steps = parse_line_to_steps(&line)?;
        let raw_position = position + steps;

        password += if raw_position >= DIALSIZE {
            raw_position / DIALSIZE
        } else if raw_position < 0 {
            let crossings = (raw_position.abs() / DIALSIZE) + 1;
            if position == 0 {
                crossings - 1
            } else {
                crossings
            }
        } else if raw_position == 0 {
            1
        } else {
            0
        };

        position = raw_position.rem_euclid(DIALSIZE);
    }
    Ok(password)
}
