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

C++ built in VS2019 - Release mode.

C# built in VS2019 - Release mode.

Kotlin used Java(TM) SE Runtime Environment (build 1.8.0_241-b07)

Tests run using Apple M1 on MacBook Air, using Docker (run-docker.sh).

|           | Python | Rust  | Go   | Javascript | Julia | Kotlin | Cython | C++ | C#   |
|-----------|--------|-------|------|------------|-------|--------|--------|-----|------|
| run 1     | 9066ms |26.47ms|1309ms| 1814ms     |40.15ms|1077ms  |6076ms  |42ms |1238ms|
| run 2     | 9212ms |26.41ms|1316ms| 1822ms     |40.13ms|1075ms  |6372ms  |44ms |1240ms|
| run 3     | 9224ms |26.53ms|1311ms| 1819ms     |40.14ms|1102ms  |6168ms  |44ms |1249ms|
| average   | 9167ms |26.47ms|1312ms| 1818ms     |40.14ms|1085ms  |6205ms  |43ms |1242ms|

## Running

### Docker

Use run-docker.sh script to run all benchmarks in docker.

### Manually

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
* Marcel Mikołajko for C++ and Julia implementation
