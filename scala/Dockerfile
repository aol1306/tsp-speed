FROM debian
WORKDIR /tsp
SHELL ["/bin/bash", "-c"]
RUN apt update && \
  apt install -y zip unzip curl default-jdk clang libgc-dev && \
  curl -s https://get.sdkman.io | bash && \
  source "/root/.sdkman/bin/sdkman-init.sh" && \
  sdk install sbt && \
  sbt initialize
COPY . .
# sbt nativeLink for native builds
RUN source "/root/.sdkman/bin/sdkman-init.sh" && \
  sbt assembly
CMD ["java", "-jar", "/tsp/target/scala-2.13/tsp-speed-assembly-1.0.jar"]