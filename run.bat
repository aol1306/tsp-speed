python .\py\main.py .\coords.txt
python .\cython\main.py .\coords.txt
dotnet .\csharp\tsp-speed.dll .\coords.txt
cd rs
cargo run --release -- ..\coords.txt
cd ..
go run .\go\main.go .\coords.txt
cpp\TSP.exe .\coords.txt
pause
