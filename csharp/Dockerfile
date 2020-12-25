FROM mcr.microsoft.com/dotnet/sdk
WORKDIR /tsp
COPY . .
WORKDIR /tsp-build
RUN dotnet new console && \
rm -fv Program.cs && \
cp /tsp/Program.cs /tsp-build/Program.cs
CMD ["dotnet", "run", "-c", "Release", "/tsp/coords.txt"]
