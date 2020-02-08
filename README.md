# TSP speed

TSP brute force algorithm implemented in Python, Cython, Go, Rust, C++ and C#.

Algorithm generates all possible permutations of given coordinate set and calculates path length. Finds best path in n! time.

Please note there might be some optimizations I didn't know about / didn't care to implement.

## Results

Tests run using i7-8700 @ 3.20GHz, Win 10.

|                 | Python | Cython | Golang | Rust | C++ |  C#  |
|-----------------|--------|--------|--------|------|-----|------|
| run 1           | 20.47s | 14.52s | 1.88s  | 55ms |116ms|1297ms|
| run 2           | 20.81s | 14.59s | 1.88s  | 55ms |115ms|1280ms|
| run 3           | 20.24s | 14.52s | 1.88s  | 55ms |117ms|1294ms|
| average         | 20.50s | 14.54s | 1.88s  | 55ms |116ms|1290ms|

I had one case where Cython implementation ran for 26.93s with the same data, no idea what happened.

C++ built in VS2019 - Release mode.
C# built in VS2019 - Release mode.

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
