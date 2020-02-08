import kotlin.collections.*
import kotlin.system.*
import java.io.File

data class Point(val x: Double, val y: Double)

fun getPoints(filename: String): ArrayList<Point> {
    println("Reading from $filename")
    var ret = ArrayList<Point>()
    File(filename).forEachLine {
        val s = it.split(",")
        val p = Point(s[0].toDouble(), s[1].toDouble())
        ret.add(p)
    }
    return ret
}

fun euclideanDistance(a: Point, b: Point): Double {
	return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y))
}

fun pathLength(start: Point, path: ArrayList<Point>): Double {
	var length = 0.0;
	length += euclideanDistance(start, path.get(0));
	for (i in 1..path.size-1)
	{
		length += euclideanDistance(path.get(i - 1), path.get(i));
	}
	length += euclideanDistance(path.get(path.size - 1), start);
	return length;
}

fun bruteForceTSP(filename: String)
{
	var points = getPoints(filename)
    var startPoint = points[0]

    val start = System.currentTimeMillis()
    val n = points.size

    var indexes = ArrayList<Int>()
    for (i in 0..n) {
        indexes.add(0)
    }

    var shortestPath: ArrayList<Point> = points
    var shortestLength = pathLength(startPoint, points)

    var i = 0
    while (i < n)
    {
        if (indexes[i] < i)
        {
            val toSwapIndex = if (i % 2 == 0) 0 else indexes[i]
            val tmp = points[toSwapIndex]
            points[toSwapIndex] = points[i]
            points[i] = tmp

            val length = pathLength(startPoint, points)
            if (length < shortestLength)
            {
                shortestLength = length
                shortestPath = points
            }
            indexes[i]++
            i = 0
        }
        else
        {
            indexes[i] = 0
            ++i
        }
    }
    val execTime = System.currentTimeMillis() - start
    println("Shortest length: $shortestLength")
    println("Shortest path: $shortestPath")
    println("Time: $execTime ms")
}

fun main(args: Array<String>)
{
    println("Kotlin (JVM)")

    if (args.size < 1) {
        println("Please provide filename as argument")
        return
    }

	bruteForceTSP(args[0]);
}
