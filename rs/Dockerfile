FROM rust
WORKDIR /tsp
COPY . .
RUN cargo build --release
CMD ["cargo", "run", "-q", "--release", "--", "coords.txt"]
