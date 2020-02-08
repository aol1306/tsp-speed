python .\py\main.py .\coords.txt
python .\cython\main.py .\coords.txt
dotnet .\csharp\tsp-speed.dll .\coords.txt
cd rs
cargo run --release -- ..\coords.txt
cd ..
cd go
go run main.go ..\coords.txt
cd ..
cpp\TSP.exe .\coords.txt
java -jar kotlin\tsp.jar .\coords.txt
pause
