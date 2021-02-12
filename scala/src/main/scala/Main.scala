import scala.math.sqrt
import scala.collection.mutable.ListBuffer
import scala.io.Source

case class Point(val x: Double, val y: Double)

object Main {

  def getPoints(filename: String): ListBuffer[Point] = {
    var ret = new ListBuffer[Point]

    val source = Source.fromFile(filename)
    for (line <- source.getLines()) {
      val s = line.split(",")
      val p = Point(s(0).toDouble, s(1).toDouble)
      ret += p
    }
    source.close()

    ret
  }

  def euclideanDistance(a: Point, b: Point): Double = {
    sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y))
  }

  def pathLength(start: Point, path: Array[Point]): Double = {
    var len: Double = 0
    len += euclideanDistance(start, path(0))
    for (i <- 1 to path.size - 1) {
      len += euclideanDistance(path(i - 1), path(i))
    }
    len += euclideanDistance(path(path.size - 1), start)
    len
  }

  def bruteForceTSP() = {
    val points = getPoints("coords.txt").toArray
    val startPoint = points(0)

    val start = System.currentTimeMillis
    val n = points.size

    var indexes = new Array[Int](n);

    var shortestPath = points
    var shortestLength = pathLength(startPoint, points)

    var i = 0
    while (i < n) {
      if (indexes(i) < i) {
        val toSwapIndex = if (i % 2 == 0) 0 else indexes(i)
        val tmp = points(toSwapIndex)
        points(toSwapIndex) = points(i)
        points(i) = tmp

        val length = pathLength(startPoint, points)
        if (length < shortestLength) {
          shortestLength = length
          shortestPath = points
        }
        indexes(i) += 1
        i = 0
      } else {
        indexes(i) = 0
        i += 1
      }
    }

    val execTime = System.currentTimeMillis - start
    println(
      s"""{"lang" : "scala", "time" : $execTime, "distance" :  $shortestLength}"""
    )
  }

  def main(args: Array[String]): Unit = {
    bruteForceTSP
  }
}
