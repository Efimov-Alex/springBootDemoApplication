package ru.netology.springbootdemoapplication;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;


    private static GenericContainer<?> devapp = new GenericContainer<>("devapp")
            .withExposedPorts(8080);

    private static GenericContainer<?> prodapp = new GenericContainer<>("prodapp")
            .withExposedPorts(8081);


    @BeforeAll
    public static void setUp() {
        devapp.start();
        prodapp.start();
    }

    @Test
    void returnDevapp() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://192.168.99.100:"
                + devapp.getMappedPort(8080) + "/profile", String.class);
        System.out.println(forEntity.getBody());

        Assertions.assertEquals(forEntity.getBody(), "Current profile is dev");

    }

    @Test
    void returnProdapp() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://192.168.99.100:"
                + prodapp.getMappedPort(8081) + "/profile", String.class);
        System.out.println(forEntity.getBody());

        Assertions.assertEquals(forEntity.getBody(), "Current profile is production");
    }



}