FROM julia
WORKDIR /tsp
COPY . .
RUN julia -e "import Pkg; Pkg.add(\"ResumableFunctions\"); Pkg.add(\"BenchmarkTools\")"
CMD ["julia", "main.jl", "coords.txt"]
