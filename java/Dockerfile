FROM debian
WORKDIR /tsp
SHELL ["/bin/bash", "-c"]
RUN apt update && \
  apt install -y default-jdk

COPY . .
RUN javac tsp.java
CMD ["java", "-XX:+UseParallelGC", "Tsp", "coords.txt"]
