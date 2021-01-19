FROM debian
WORKDIR /tsp
SHELL ["/bin/bash", "-c"]
RUN apt update && \
  apt install -y zip unzip curl default-jdk

COPY . .
RUN curl -s https://get.sdkman.io | bash && \
  source "/root/.sdkman/bin/sdkman-init.sh" && \
  sdk install kotlin && \
  kotlinc tsp.kt -include-runtime -d tsp.jar
CMD ["java", "-jar", "tsp.jar", "coords.txt"]
