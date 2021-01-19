using System;
using System.Collections.Generic;
using System.IO;

namespace tsp_speed
{
    class Point
    {
        public double x, y;

        public Point(double x, double y)
        {
            this.x = x;
            this.y = y;
        }
    }
    class Program
    {
        static double DistanceF(Point a, Point b)
        {
            return Math.Sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
            //return Math.Sqrt(Math.Pow(a.x - b.x, 2) + Math.Pow(a.y - b.y, 2)); // makes it 10 times slower
        }
        static double PathLength(Point start, List<Point> path)
        {
            double length = 0;
            length += DistanceF(start, path[0]);
            for (int i=1; i< path.Count; ++i)
            {
                length += DistanceF(path[i - 1], path[i]);
            }
            length += DistanceF(path[path.Count - 1], start);
            return length;
        }

        static List<Point> GetPoints(String filename)
        {
            var ret = new List<Point>();

            string[] lines = File.ReadAllLines(filename);
            foreach (var line in lines)
            {
                var point = new Point(Double.Parse(line.Split(',')[0]), Double.Parse(line.Split(',')[1]));
                ret.Add(point);
            }

            return ret;
        }

        static void BruteForceTSP(String filename)
        {
            var points = GetPoints(filename);

            var start = System.Diagnostics.Stopwatch.StartNew();

            var n = points.Count;
            var indexes = new List<int>();
            for (int j=0; j<n; j++)
            {
                indexes.Add(0);
            }

            var shortestPath = points;
            var shortestLength = PathLength(points[0], points);

            var i = 0;
            while (i<n)
            {
                if (indexes[i] < i)
                {
                    // swap
                    var temp = points[i % 2 == 0 ? 0 : indexes[i]];
                    points[i % 2 == 0 ? 0 : indexes[i]] = points[i];
                    points[i] = temp;

                    var length = PathLength(points[0], points);
                    if (length < shortestLength)
                    {
                        shortestLength = length;
                        shortestPath = points;
                    }
                    indexes[i]++;
                    i = 0;
                } else
                {
                    indexes[i] = 0;
                    i++;
                }
            }

            start.Stop();
            var elapsedMs = start.ElapsedMilliseconds;
            Console.WriteLine("{\"lang\" : \"csharp\", \"time\" : " + elapsedMs + ", \"distance\" : " + shortestLength + "}");
        }

        static void Main(string[] args)
        {
            if (args.Length < 1)
            {
                Console.WriteLine("Usage: ./main [filename]");
                return;
            }
            BruteForceTSP(args[0]);
        }
    }
}

/*

 * 
Point* fromFile(std::string line) {
	std::stringstream s(line);
	std::string x, y;
	std::getline(s, x, ',');
	std::getline(s, y, ',');
	return new Point(std::stod(x), std::stod(y));
}

std::vector< Point* > getPoints(Point & start, char *filename)
{
	std::vector<Point*> ret;
	std::fstream f;
	f.open(filename);
	if (f.is_open()) {
		std::string s;
		f >> s;
		start = *fromFile(s);
		while (!f.eof()) {
			std::string s;
			f >> s;
			if (s.empty()) break;
			ret.push_back(fromFile(s));
		}
	}
	return ret;
}
*/
