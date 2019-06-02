#include <cmath>
#include <vector>
#include <algorithm>
#include <iostream>
#include <chrono>
#include <functional>
#include <array>
#include <fstream>
#include <sstream>

class Point
{
public:
	double x, y;

	Point(double x, double y)
	{
		this->x = x;
		this->y = y;
	}
};

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

inline double euclideanDistance(Point& a, Point& b)
{
	return sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
}

double pathLength(Point& start, std::vector< Point*> & path)
{
	double length = 0;
	length += euclideanDistance(start, *path[0]);
	for (int i = 1; i < path.size(); ++i)
	{
		length += euclideanDistance(*path[i - 1], *path[i]);
	}
	length += euclideanDistance(*path[path.size() - 1], start);
	return length;
}

void bruteForceTSP(char* filename)
{
	Point startPoint = Point(0, 0);

	auto points = getPoints(startPoint, filename);

	auto start = std::chrono::steady_clock::now();
	const int n = points.size();

	int* indexes = new int[n];
	for (int i = 0; i < n; ++i)
	{
		indexes[i] = 0;
	}

	std::vector<Point*> shortestPath = points;
	double shortestLength = pathLength(startPoint, points);

	int i = 0;
	while (i < n)
	{
		if (indexes[i] < i)
		{
			std::swap(points[i % 2 == 0 ? 0 : indexes[i]], points[i]);
			double length = pathLength(startPoint, points);
			if (length < shortestLength)
			{
				shortestLength = length;
				std::copy(points.begin(), points.end(), shortestPath.begin());
			}
			indexes[i]++;
			i = 0;
		}
		else
		{
			indexes[i] = 0;
			++i;
		}
	}
	auto duration = std::chrono::steady_clock::now() - start;
	std::cout << std::chrono::duration_cast<std::chrono::milliseconds>(duration).count() << "ms" << std::endl;
	std::cout << "Shortest length: " << shortestLength << std::endl;
}

int main(int argc, char** argv)
{
	std::cout << "C++" << std::endl;

	if (argc < 2) {
		std::cout << "Usage: ./main [filename]" << std::endl;
		exit(-1);
	}

	bruteForceTSP(argv[1]);

	system("pause");
	return 0;
}
