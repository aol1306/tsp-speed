FROM gcc
WORKDIR /tsp
COPY . .
RUN g++ TSP.cpp -o a.out -O3 -Wall
CMD ["./a.out", "coords.txt"]
