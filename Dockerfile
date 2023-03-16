FROM openjdk:11
COPY ./target/calculator-1.0-SNAPSHOT.jar ./
COPY ./src/main/resources/log4j2.xml /logs/log4j2.xml
WORKDIR ./
RUN java -jar calculator-1.0-SNAPSHOT.jar
CMD ["java", "-cp", ".:/logs", "-Dlog4j.configurationFile=/logs/log4j2.xml", "-jar", "calculator-1.0-SNAPSHOT.jar"]
