package ru.netology.jdspringboot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JdSpringBootApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private static final GenericContainer<?> devapp = new GenericContainer<>("devapp").withExposedPorts(8080);
    private static final GenericContainer<?> prodapp = new GenericContainer<>("prodapp").withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        devapp.start();
        prodapp.start();
    }

    @Test
    void contextLoadsDev() {
        ResponseEntity<String> devEntity = testRestTemplate.getForEntity("http://localhost:" + devapp.getMappedPort(8080) + "/profile", String.class);
        Assertions.assertEquals(devEntity.getBody(), "Current profile is dev");
        System.out.println(devEntity.getBody());
    }

    @Test
    void contextLoadsProd() {
        ResponseEntity<String> prodEntity = testRestTemplate.getForEntity("http://localhost:" + prodapp.getMappedPort(8081) + "/profile", String.class);
        Assertions.assertEquals(prodEntity.getBody(), "Current profile is production");
        System.out.println(prodEntity.getBody());
    }

}
