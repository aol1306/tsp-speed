python3 py/main.py coords.txt
python3 cython/main.py coords.txt
dotnet csharp/tsp-speed.dll coords.txt
cd rs
cargo run --release -- ../coords.txt
cd ..
cd go
go run main.go ../coords.txt
cd ..
g++ -O3 cpp/TSP.cpp -o cpp/TSP # build
./cpp/TSP coords.txt
java -jar kotlin/tsp.jar coords.txt
