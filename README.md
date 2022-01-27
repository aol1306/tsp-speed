# TSP speed

TSP brute force algorithm implemented in Python, Cython, Go, Rust, C++, C# and Kotlin.

Algorithm generates all possible permutations of given coordinate set and calculates path length. Finds best path in n! time.

For supplied coordinates shortest path should be 35.1805411

Please note there might be some optimizations I didn't know about / didn't care to implement.

## Results

Tests run using Apple M1 on MacBook Air, using Docker (run-docker.sh).

```json
{"lang": "py", "time": 8447, "distance": 35.18054110918435}
{"lang": "pypy", "time": 324, "distance": 35.18054110918435}
{"lang" : "rs", "time": 25.975, "distance": 35.18054110918435}
{"lang" : "go", "time": 43, "distance": 35.18054110918435}
{"lang":"js","time":1399,"distance":35.18054110918435}
{"lang" : "julia", "time" : 40, "distance" : 35.180542}
{"lang" : "kotlin", "time" : 606, "distance" :  35.18054110918435}
{"lang": "cython", "time": 4820, "distance": 35.18054110918435}
{"lang" : "cpp", "time" : 46, "distance" : 35.1805}
{"lang": "c", "time": 51, "distance": 35.18054110918435}
{"lang" : "csharp", "time" : 1208, "distance" : 35.18054110918435}
{"lang" : "ruby", "time": 3615.699335, "distance": 35.18054110918435}
{"lang" : "scala", "time" : 581, "distance" :  35.18054110918435}
{"lang" : "java", "time" : 463, "distance" :  35.18054110918435}
```

## Running

Use run-docker.sh script to run all benchmarks in docker.

## Credits
* Marcel Mikołajko for C++ and Julia implementation
