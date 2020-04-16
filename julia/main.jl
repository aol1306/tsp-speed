using ResumableFunctions
using BenchmarkTools

struct Point
    x::Float32
    y::Float32
end

function swap(points::Array{Point, 1}, a, b)::Nothing
    @inbounds temp= points[a]
    @inbounds points[a] = points[b]
    @inbounds points[b] = temp
    return nothing
end

@resumable function permutations(points::Array{Point, 1})::Array{Point, 1}
    n = length(points)
    indexes = ones(Int32, n)
    i = 1
    @yield points
    while i <= n
        if @inbounds indexes[i] < i
            swap(points, i % 2 == 0 ? indexes[i] : 1, i)
            @yield points
            @inbounds  indexes[i] += 1
            i = 1
        else
            @inbounds indexes[i] = 1
            i += 1
        end
    end
end

@inline euclideanDistance(a::Point, b::Point)::Float32 = @fastmath sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y))

function pathLength(start::Point, path::Array{Point,1})::Float32
    pathLen::Float32 = 0.0
    @inbounds pathLen += euclideanDistance(start, path[1])
    for i = 2:length(path)
        @inbounds pathLen += euclideanDistance(path[i - 1], path[i])
    end
    @inbounds pathLen += euclideanDistance(path[end], start)
    return pathLen
end

function bruteForceTSP(points::Array{Point,1}, startPoint::Point)::Float32
    shortestLength::Float32 = pathLength(startPoint, points)

    for path::Array{Point,1} in permutations(points)
        pathLen::Float32 = pathLength(startPoint, path)
        if pathLen < shortestLength
            shortestLength = pathLen
        end
    end
    return shortestLength
end

function main()
    startPoint::Point = Point(3,4)
    points = Point[ Point(1,2), Point(5,6), Point(9,2), Point(0,3), Point(7,3), Point(2,7), Point(5,5), Point(1,3), Point(0,-4), Point(2,2) ]
    shortest::Float32 = 0.0
    shortest = bruteForceTSP(points, startPoint)
    @btime bruteForceTSP($points, $startPoint)
    @btime bruteForceTSP($points, $startPoint)
    @btime bruteForceTSP($points, $startPoint)
    print(shortest)

    # @profile shortest = bruteForceTSP(points, startPoint)
    # Profile.print()
end

main()
