fs = require('fs')

if (process.argv.length != 3) {
    console.log("Usage: node tsp.js [filename]")
    process.exit()
}

function getPoints(data) {
    var coordsRaw = data.split("\n")
    coordsRaw.pop() // newline
    var coords = []
    for (var i in coordsRaw) {
        var split = coordsRaw[i].split(',')
        var c = []
        c.x = parseInt(split[0])
        c.y = parseInt(split[1])
        coords.push(c)
    }
    return coords
}

function distanceF(a, b) {
    return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y))
}

function pathLength(start, path) {
    var length = 0
    length += distanceF(start, path[0])
    for (var i=1; i<path.length; ++i) {
        length += distanceF(path[i-1], path[i])
    }
    length += distanceF(path[path.length-1], start)
    return length
}


fs.readFile(process.argv[2], 'utf-8', (err, data) => {
    var points = getPoints(data)
    var start = Date.now()

    var n = points.length
    var indexes = []
    for (var j=0; j<n; j++) {
        indexes.push(0)
    }
    var shortestPath = points
    var shortestLength = pathLength(points[0], points)

    var i = 0
    while (i<n) {
        if (indexes[i] < i) {
            // swap
            var tmp = points[i % 2 == 0 ? 0 : indexes[i]]
            points[i % 2 == 0 ? 0 : indexes[i]] = points[i]
            points[i] = tmp

            var length = pathLength(points[0], points)
            if (length < shortestLength) {
                shortestLength = length
                shortestPath = points
            }
            indexes[i]++
            i = 0
        } else {
            indexes[i] = 0
            i++
        }
    }

    console.log("Elapsed: " + (Date.now() - start) + "ms")
    console.log("Shortest length: " + shortestLength)
    console.log("Shortest path: ")
    console.log(shortestPath)
})