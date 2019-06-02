python .\py\main.py .\coords.txt
cd rs
cargo run --release -- ..\coords.txt
cd ..
go run .\go\main.go .\coords.txt