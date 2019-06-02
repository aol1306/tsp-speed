use permutohedron::heap_recursive;
use std::env;
use std::fs::File;
use std::io::BufRead;
use std::io::BufReader;
use std::process;
use std::time::Instant;
use std::vec::Vec;

#[derive(Debug, Clone, Copy)]
struct Coordinate {
    x: f64,
    y: f64,
}

fn read_coordinates() -> Vec<Coordinate> {
    let args: Vec<String> = env::args().collect();
    let mut ret: Vec<Coordinate> = vec![];
    if args.len() < 2 {
        println!("Add coords filename as argument");
        process::exit(-1);
    }
    let f = File::open(&args[1]).expect("Can't open file");
    let file = BufReader::new(&f);
    for line in file.lines() {
        let value = line.unwrap();
        let split = value.split(",").collect::<Vec<&str>>();
        ret.push(Coordinate {
            x: split[0].parse::<f64>().expect("Invalid file format"),
            y: split[1].parse::<f64>().expect("Invalid file format"),
        });
    }
    return ret;
}

fn distance_f(a: &Coordinate, b: &Coordinate) -> f64 {
    ((a.x - b.x).powf(2.0) + (a.y - b.y).powf(2.0)).sqrt()
}

fn calculate_distance(start: &Coordinate, coords: &[Coordinate]) -> f64 {
    let mut distance = 0.0;
    distance += distance_f(start, &coords[0]);
    for i in 1..coords.len() {
        distance += distance_f(&coords[i - 1], &coords[i]);
    }
    distance += distance_f(&coords[coords.len() - 1], start);
    return distance;
}

fn solve_brute_force() {
    let mut coords = read_coordinates();

    let start_time = Instant::now();

    let start = coords.remove(0);
    let mut shortest_path = coords.clone();
    let mut shortest_distance = calculate_distance(&start, &coords);

    heap_recursive(&mut coords, |permutation| {
        let current_distance = calculate_distance(&start, &permutation);
        if current_distance < shortest_distance {
            shortest_distance = current_distance;
            shortest_path = permutation.to_vec();
        }
    });

    println!(
        "Brute-force took {} s",
        start_time.elapsed().as_micros() as f64 / 1000000.0
    );

    println!("Path: len = {}; {:?}", shortest_distance, shortest_path);
}

fn main() {
	println!("rust");
    solve_brute_force();
}
