use std::env::current_dir;
use std::fs::File;
use std::io::{self, BufRead};

pub fn get_input_iter(filename: &str) -> io::Result<io::Lines<io::BufReader<File>>> {
    let path_str = format!("../resources/{}", filename);
    let file_path = current_dir()?.join(path_str);
    let file = File::open(file_path)?;
    Ok(io::BufReader::new(file).lines())
}
