FROM openjdk:17 as build
EXPOSE 8081
ADD target/springBootDemoApplication-0.0.1-SNAPSHOT.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]