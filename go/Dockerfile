FROM golang
WORKDIR /tsp
COPY . .
RUN go build .
CMD ["go", "run", ".", "coords.txt"]
