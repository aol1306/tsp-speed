# TSP speed

TSP brute force algorithm implemented in Python, Cython, Go, Rust, C++, C# and Kotlin.

Algorithm generates all possible permutations of given coordinate set and calculates path length. Finds best path in n! time.

For supplied coordinates shortest path should be 35.1805411

Please note there might be some optimizations I didn't know about / didn't care to implement.

## Results

Tests run using Apple M1 on MacBook Air, using Docker (run-docker.sh).

```json
{"lang": "py", "time": 9308, "distance": 35.18054110918435}
{"lang" : "rs", "time": 26.236, "distance": 35.18054110918435}
{"lang" : "go", "time": 1302, "distance": 35.18054110918435}
{"lang":"js","time":1815,"distance":35.18054110918435}
{"lang" : "julia", "time" : 41, "distance" : 35.180542}
{"lang" : "kotlin", "time" : 612, "distance" :  35.18054110918435}
{"lang": "cython", "time": 5413, "distance": 35.18054110918435}
{"lang" : "cpp", "time" : 43, "distance" : 35.1805}
{"lang" : "csharp", "time" : 1251, "distance" : 35.18054110918435}
{"lang" : "ruby", "time": 3598.095294, "distance": 35.18054110918435}
{"lang" : "scala", "time" : 591, "distance" :  35.18054110918435}
```

## Running

Use run-docker.sh script to run all benchmarks in docker.

## Credits
* Marcel Mikołajko for C++ and Julia implementation
