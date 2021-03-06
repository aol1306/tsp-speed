package main

import (
	"bufio"
	"fmt"
	"log"
	"math"
	"os"
	"strconv"
	"strings"
	"time"

	prmt "github.com/gitchander/permutation"
)

type coord struct {
	x float64
	y float64
}

type coordSlice []coord

func (ps coordSlice) Len() int      { return len(ps) }
func (ps coordSlice) Swap(i, j int) { ps[i], ps[j] = ps[j], ps[i] }

func distanceF(a *coord, b *coord) float64 {
	return math.Sqrt(math.Pow(a.x-b.x, 2) + math.Pow(a.y-b.y, 2))
}

func calculateDistance(start *coord, coords coordSlice) float64 {
	distance := 0.0
	distance += distanceF(start, &coords[0])
	for i := 1; i < len(coords); i++ {
		distance += distanceF(&coords[i-1], &coords[i])
	}
	distance += distanceF(&coords[len(coords)-1], start)
	return distance
}

func readCoordinates() coordSlice {
	if len(os.Args) < 2 {
		log.Fatal("Add filename as argument")
		os.Exit(-1)
	}

	file, err := os.Open(os.Args[1])
	if err != nil {
		log.Fatal(err)
		os.Exit(-2)
	}
	defer file.Close()

	var ret []coord

	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		s := strings.Split(scanner.Text(), ",")
		x, err := strconv.ParseFloat(s[0], 64)
		if err != nil {
			log.Fatal("Cannot parse file")
			os.Exit(-3)
		}
		y, err := strconv.ParseFloat(s[1], 64)
		if err != nil {
			log.Fatal("Cannot parse file")
			os.Exit(-3)
		}
		ret = append(ret, coord{x, y})
	}
	return ret
}

func solveBruteForce() {
	coords := readCoordinates()
	startTime := time.Now()

	start, coords := coords[0], coords[1:]

	shortestPath := coords
	shortestDistance := calculateDistance(&start, coords)

	p := prmt.New(coords)
	for p.Next() {
		currentDistance := calculateDistance(&start, coords)
		if currentDistance < shortestDistance {
			shortestDistance = currentDistance
			shortestPath = coords
		}
	}

	_ = shortestPath
	elapsed := time.Since(startTime)
	fmt.Printf("{\"lang\" : \"go\", \"time\": %v, \"distance\": %v}\n", elapsed.Milliseconds(), shortestDistance)
}

func main() {
	solveBruteForce()
}
