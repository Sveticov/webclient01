package org.svetikov.webclient03

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import reactor.kotlin.test.test

@SpringBootTest
@WebMvcTest(Webclient03Application::class)
class Webclient03ApplicationTests {
    val client = WebClient.create()



    @Test
    fun contextLoads() {
        client
            .get()
            .uri("http://localhost:8900/person3")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(Person::class.java)
            .test()
            .expectFusion()
            .verifyComplete()

    }

}


