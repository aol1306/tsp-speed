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
        static double PathLength(Point start, Point[] path)
        {
            double length = 0;
            length += DistanceF(start, path[0]);
            for (int i=1; i< path.Length; ++i)
            {
                length += DistanceF(path[i - 1], path[i]);
            }
            length += DistanceF(path[path.Length - 1], start);
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
            var points = GetPoints(filename).ToArray();

            var start = System.Diagnostics.Stopwatch.StartNew();

            var n = points.Length;
            var indexes = new int[n];
            for (int j=0; j<n; j++)
            {
                indexes[j] = 0;
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
