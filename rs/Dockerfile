FROM rust
WORKDIR /tsp
COPY . .
RUN cargo build --release
CMD ["cargo", "run", "--release", "--", "coords.txt"]
