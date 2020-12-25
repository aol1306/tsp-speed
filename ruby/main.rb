#!/usr/bin/ruby

# euclidean
def distance_f(a, b)
    return Math.sqrt((a[0]-b[0])**2 + (a[1]-b[1])**2)
end

# read coordinates from file (one line - one coordinate pair)
def read_coordinates()
    if ARGV.length != 1
        puts "Usage: main.rb filename"
        exit
    end
    ret = []
    File.open(ARGV[0]).each do |line|
        line.strip!
        ret.append([line.split(",")[0].to_f, line.split(",")[1].to_f])
    end
    return ret
end

# calculate TSP path -> includes distance from starting point and returns back to it (full cycle)
def calculate_distance(start, coords)
    distance = 0
    distance += distance_f(start, coords[0])
    for i in 1..(coords.length-1)
        distance += distance_f(coords[i-1], coords[i])
    end
    distance += distance_f(coords[-1], start)
    return distance
end

def solve_brute_force()
    coords = read_coordinates()
	
    start_t = Time.now
    # starting position is first entry
    start = coords.pop
    # assume first is shortest
    shortest_path = coords
    shortest_distance = calculate_distance(start, coords)

    # heavy calculations - brute force
    for path in coords.permutation()
        current_distance = calculate_distance(start, path)
        if (current_distance < shortest_distance)
            shortest_distance = current_distance
            shortest_path = path
        end
    end

    end_t = Time.now
    puts "Brute-force took #{end_t - start_t} seconds"

    # print results
    puts "Shortest path is #{shortest_path}"
    puts "Distance: #{shortest_distance}"
end


puts "ruby"
solve_brute_force