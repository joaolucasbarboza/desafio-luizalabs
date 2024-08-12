FROM openjdk:22-jdk

COPY target/wishlist-0.0.1-SNAPSHOT.jar /app/app.jar

CMD ["java", "-jar", "/app/app.jar"]