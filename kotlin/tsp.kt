import kotlin.collections.*
import kotlin.system.*
import java.io.File

data class Point(val x: Double, val y: Double)

fun getPoints(filename: String): ArrayList<Point> {
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

fun pathLength(start: Point, path: Array<Point>): Double {
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
	var points: Array<Point> = getPoints(filename).toTypedArray()
    var startPoint = points[0]

    val start = System.currentTimeMillis()
    val n = points.size

    var indexes = Array<Int>(n) { 0 }

    var shortestPath: Array<Point> = points
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
    println("{\"lang\" : \"kotlin\", \"time\" : $execTime, \"distance\" :  $shortestLength}")
}

fun main(args: Array<String>)
{
    if (args.size < 1) {
        println("Please provide filename as argument")
        return
    }

	bruteForceTSP(args[0]);
}
