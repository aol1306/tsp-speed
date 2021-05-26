FROM gcc
WORKDIR /tsp
COPY . .
RUN gcc -lm TSP.c -o a.out -O3 -Wall
CMD ["./a.out", "coords.txt"]
