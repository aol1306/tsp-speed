# TSP speed

TSP brute force algorithm implemented in Python, Go and Rust.

Algorithm generates all possible permutations of given coordinate set and calculates path length. Finds best path in n! time.

Please note there might be some optimizations I didn't know about / didn't care to implement.

## Results

Tests run using i7-8700 @ 3.20GHz, Win 10.

|                 | Python | Golang | Rust |
|-----------------|--------|--------|------|
| run 1           | 20.47s | 1.88s  | 0.05s|
| run 2           | 20.81s | 1.88s  | 0.05s|
| run 3           | 20.24s | 1.88s  | 0.05s|
| average         | 20.50s | 1.88s  | 0.05s|

## Running

* Python: `python main.py ../coords.txt`
* Go: `go run main.go ../coords.txt`
* Rust: `cargo run --release -- ../coords.txt`

You can also run all programs using run.bat script.
