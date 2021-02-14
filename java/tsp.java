import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("Point (%f, %f)", x, y);
    }
}

class Tsp {

    private static double pathLength(Point start, Point[] path) {
        var length = 0.0;
        length += euclideanDistance(start, path[0]);
        for (int i=1; i<path.length; i++)
        {
            length += euclideanDistance(path[i - 1], path[i]);
        }
        length += euclideanDistance(path[path.length - 1], start);
        return length;
    }

    private static double euclideanDistance(Point a, Point b) {
        return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }

    private static Point[] getPoints(String filename) throws IOException {
        return Files.lines(Paths.get(filename)).map(x -> {
            var s = x.split(",");
            var p = new Point(Double.parseDouble(s[0]), Double.parseDouble(s[1]));
            return p;
        }).toArray(Point[]::new);
    }

    private static void bruteForceTSP(String filename) throws IOException {
        var points = getPoints(filename);
        var startPoint = points[0];

        var start = System.currentTimeMillis();
        var n = points.length;

        var indexes = new int[n];

        var shortestPath = points;
        var shortestLength = pathLength(startPoint, points);

        var i = 0;
        while (i < n)
        {
            if (indexes[i] < i)
            {
                var toSwapIndex = 0;
                if (!(i % 2 == 0)) {
                    toSwapIndex = indexes[i];
                }
                var tmp = points[toSwapIndex];
                points[toSwapIndex] = points[i];
                points[i] = tmp;

                var length = pathLength(startPoint, points);
                if (length < shortestLength)
                {
                    shortestLength = length;
                    shortestPath = points;
                }
                indexes[i]++;
                i = 0;
            }
            else
            {
                indexes[i] = 0;
                i++;
            }
        }
        var execTime = System.currentTimeMillis() - start;
        System.out.printf("{\"lang\" : \"java\", \"time\" : %d, \"distance\" :  %.14f}\n", execTime, shortestLength);
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Please provide filename as argument");
            return;
        }
        
        bruteForceTSP(args[0]);
    }
}