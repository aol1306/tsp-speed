#!/bin/sh
set -eux

docker build py -t tsp-speed-py
docker build pypy -t tsp-speed-pypy
docker build rs -t tsp-speed-rs
docker build go -t tsp-speed-go
docker build js -t tsp-speed-js
docker build julia -t tsp-speed-julia
docker build kotlin -t tsp-speed-kotlin
docker build cython -t tsp-speed-cython
docker build cpp -t tsp-speed-cpp
docker build csharp -t tsp-speed-csharp
docker build ruby -t tsp-speed-ruby
docker build scala -t tsp-speed-scala
docker build java -t tsp-speed-java

set +x

docker run --rm -it tsp-speed-py
docker run --rm -it tsp-speed-pypy
docker run --rm -it tsp-speed-rs
docker run --rm -it tsp-speed-go
docker run --rm -it tsp-speed-js
docker run --rm -it tsp-speed-julia
docker run --rm -it tsp-speed-kotlin
docker run --rm -it tsp-speed-cython
docker run --rm -it tsp-speed-cpp
docker run --rm -it tsp-speed-csharp
docker run --rm -it tsp-speed-ruby
docker run --rm -it tsp-speed-scala
docker run --rm -it tsp-speed-java
