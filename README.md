# TSP speed

TSP brute force algorithm implemented in Python, Cython, Go, Rust, C++, C# and Kotlin.

Algorithm generates all possible permutations of given coordinate set and calculates path length. Finds best path in n! time.

For supplied coordinates shortest path should be 35.1805411

Please note there might be some optimizations I didn't know about / didn't care to implement.

## Results

Tests run using i7-8700 @ 3.20GHz, Win 10.

|                 | Python | Cython | Go 1.13 |  C#  |Kotlin| C++ | Rust 1.41 |
|-----------------|--------|--------|---------|------|------|-----|-----------|
| run 1           | 20.47s | 14.52s | 1874ms  |1297ms|1170ms|116ms|    55ms   |
| run 2           | 20.81s | 14.59s | 1872ms  |1280ms|1145ms|115ms|    55ms   |
| run 3           | 20.24s | 14.52s | 1876ms  |1294ms|1155ms|117ms|    55ms   |
| average         | 20.50s | 14.54s | 1874ms  |1290ms|1157ms|116ms|    55ms   |

I had one case where Cython implementation ran for 26.93s with the same data, no idea what happened.

C++ built in VS2019 - Release mode.

C# built in VS2019 - Release mode.

Kotlin used Java(TM) SE Runtime Environment (build 1.8.0_241-b07)

## Running

* Python: `python main.py ../coords.txt`
* Cython: `python main.py ../coords.txt`
* Go: `go run main.go ../coords.txt`
* Rust: `cargo run --release -- ../coords.txt`
* C++: `TSP.exe ../coords.txt`
* C#: `dotnet tsp-speed.dll ../coords.txt`
* Kotlin: `java -jar tsp.jar ../coords.txt`

You can also run all programs using run.bat script.

## Cython notes

Repo includes Cython extention built for Windows.

To build for a different system, run `python setup.py build_ext --inplace`

## Credits
* Marcel Mikołajko for C++ implementation
