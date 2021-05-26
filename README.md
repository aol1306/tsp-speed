# TSP speed

TSP brute force algorithm implemented in Python, Cython, Go, Rust, C++, C# and Kotlin.

Algorithm generates all possible permutations of given coordinate set and calculates path length. Finds best path in n! time.

For supplied coordinates shortest path should be 35.1805411

Please note there might be some optimizations I didn't know about / didn't care to implement.

## Results

Tests run using Apple M1 on MacBook Air, using Docker (run-docker.sh).

```json
{"lang": "py", "time": 9193, "distance": 35.18054110918435}
{"lang": "pypy", "time": 327, "distance": 35.18054110918435}
{"lang" : "rs", "time": 26.667, "distance": 35.18054110918435}
{"lang" : "go", "time": 1276, "distance": 35.18054110918435}
{"lang":"js","time":1841,"distance":35.18054110918435}
{"lang" : "julia", "time" : 40, "distance" : 35.180542}
{"lang" : "kotlin", "time" : 610, "distance" :  35.18054110918435}
{"lang": "cython", "time": 5360, "distance": 35.18054110918435}
{"lang" : "cpp", "time" : 39, "distance" : 35.1805}
{"lang" : "csharp", "time" : 1151, "distance" : 35.18054110918435}
{"lang" : "ruby", "time": 3648.0811270000004, "distance": 35.18054110918435}
{"lang" : "scala", "time" : 616, "distance" :  35.18054110918435}
{"lang" : "java", "time" : 472, "distance" :  35.18054110918435}
```

## Running

Use run-docker.sh script to run all benchmarks in docker.

## Credits
* Marcel Mikołajko for C++ and Julia implementation
