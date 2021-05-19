#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <stdint.h>

#include <sys/time.h>

typedef struct {
    double x, y;
} Point;

Point point_new(double x, double y) {
    Point ret;
    ret.x = x;
    ret.y = y;
    return ret;
}

void point_print(const Point* p) {
    printf("Point { x: %lf y: %lf }\n", p->x, p->y);
}

void swap(Point* a, int i, int j) {
    Point tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
}

inline double euclidean_distance(const Point* a, const Point* b) {
    double ret = sqrt((a->x - b->x) * (a->x - b->x) + (a->y - b->y) * (a->y - b->y));
    return ret;
}

double path_length(const Point* start, const Point* path, const size_t path_size) {
    double length = 0;
    length += euclidean_distance(start, &path[0]);
    for (int i=1; i<path_size; i++) {
        length += euclidean_distance(&path[i-1], &path[i]);
    }
    length += euclidean_distance(&path[path_size-1], start);
    return length;
}

// https://github.com/aol1306/tsp-speed/blob/master/cpp/TSP.cpp
void tsp(const Point* points_arg, const size_t size_arg) {
    struct timeval start, stop;

    gettimeofday(&start, NULL);

    int size = size_arg-1;

    Point start_point = point_new(points_arg[0].x, points_arg[0].y);
    Point* points = malloc(sizeof(Point) * size);
    memcpy(points, points_arg + 1, sizeof(Point) * size);

    int i = 0;
    int *indexes = calloc(size, sizeof(int));

    // first permutation
    Point* best_path = malloc(sizeof(Point) * size);
    memcpy(best_path, points, sizeof(Point) * size);

    double best_length = path_length(&start_point, points, size);

    while (i < size) {
        if (indexes[i] < i) {
            swap(points, i % 2 == 0 ? 0 : indexes[i], i);

            // other permutations
            double length = path_length(&start_point, points, size);
            if (length < best_length) {
                best_length = length;
                memcpy(best_path, points, sizeof(Point) * size);
            }

            indexes[i]++;
            i = 0;
        } else {
            indexes[i] = 0;
            ++i;
        }
    }

    gettimeofday(&stop, NULL);
    uint64_t delta = (stop.tv_sec - start.tv_sec) * 1000 + (stop.tv_usec - start.tv_usec) / 1000;
    printf("{\"lang\": \"c\", \"time\": %llu, \"distance\": %.14lf}\n", delta, best_length);

    free(points);
    free(best_path);
    free(indexes);
}

int main(int argc, char** argv) {
    double x, y;
    size_t points_n = 0;
    Point* points = NULL;

    if (argc < 2) {
        puts("Usage: ./main [filename]");
        exit(-1);
    }

    FILE *f = fopen(argv[1], "r");
    if (f == NULL) {
        puts("Error opening file");
        exit(-1);
    }
    while(fscanf(f, "%lf,%lf", &x, &y) == 2) {
        if (points == NULL) {
            points = malloc(sizeof(Point) * ++points_n);
        } else {
            points = realloc(points, sizeof(Point) * ++points_n);
        }
        points[points_n-1] = point_new(x, y);
    }
    fclose(f);

    tsp(points, points_n);
    
    if (points != NULL) free(points);
}
