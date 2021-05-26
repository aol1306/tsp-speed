#!/usr/bin/python3
import itertools
import math
import sys
import time
import json

# euclidean
def distance_f(a, b):
    return math.sqrt((a[0]-b[0])**2 + (a[1]-b[1])**2)

# read coordinates from file (one line - one coordinate pair)
def read_coordinates():
    if len(sys.argv) != 2:
        print("Usage: main.py filename")
        sys.exit(-1)
    with open(sys.argv[1], 'r') as f:
        ret = []
        for line in f:
            ret.append([float(n) for n in line.strip().split(",")])
    return ret

# calculate TSP path -> includes distance from starting point and returns back to it (full cycle)
def calculate_distance(start, coords):
    distance = 0
    distance += distance_f(start, coords[0])
    for i in range(1, len(coords)):
        distance += distance_f(coords[i-1], coords[i])
    distance += distance_f(coords[-1], start)
    return distance

def solve_brute_force():
    coords = read_coordinates()
	
    start_t = time.time()
    # starting position is first entry
    start = coords.pop(0)
    # assume first is shortest
    shortest_path = coords
    shortest_distance = calculate_distance(start, coords)

    # heavy calculations - brute force
    for path in itertools.permutations(coords):
        current_distance = calculate_distance(start, path)
        if current_distance < shortest_distance:
            shortest_distance = current_distance
            shortest_path = path

    end_t = time.time()
    #print("Brute-force took " + str((end_t - start_t))+ " seconds")

    # add starting point to the front and end (aesthetics)
    # print results
    #print("Shortest path is", (start,)+shortest_path+(start,))
    #print("Distance:", shortest_distance)
    result = {'lang': 'pypy', 'time': int((end_t-start_t)*1000), 'distance': shortest_distance}
    print(json.dumps(result))


solve_brute_force()
