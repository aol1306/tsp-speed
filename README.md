# TSP speed

TSP brute force algorithm implemented in Python, Cython, Go and Rust.

Algorithm generates all possible permutations of given coordinate set and calculates path length. Finds best path in n! time.

Please note there might be some optimizations I didn't know about / didn't care to implement.

## Results

Tests run using i7-8700 @ 3.20GHz, Win 10.

|                 | Python | Cython | Golang | Rust |
|-----------------|--------|--------|--------|------|
| run 1           | 20.47s | 14.52s | 1.88s  | 0.05s|
| run 2           | 20.81s | 14.59s | 1.88s  | 0.05s|
| run 3           | 20.24s | 14.52s | 1.88s  | 0.05s|
| average         | 20.50s | 14.54s | 1.88s  | 0.05s|

I had one case where Cython implementation ran for 26.93s with the same data, no idea what happened.

## Running

* Python: `python main.py ../coords.txt`
* Cython: `python main.py ../coords.txt`
* Go: `go run main.go ../coords.txt`
* Rust: `cargo run --release -- ../coords.txt`

You can also run all programs using run.bat script.

## Cython notes

Repo includes Cython extention built for Windows.

To build for a different system, run `python setup.py build_ext --inplace`
