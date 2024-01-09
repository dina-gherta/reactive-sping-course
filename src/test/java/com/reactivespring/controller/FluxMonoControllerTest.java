package com.reactivespring.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

import java.util.Objects;

@WebFluxTest(controllers = FluxMonoController.class)
@AutoConfigureWebTestClient
class FluxMonoControllerTest {
    @Autowired
    WebTestClient webTestClient;

    @Test
    void flux(){
        webTestClient.
                get()
                .uri("/test")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(Integer.class)
                .hasSize(4);

    }
    @Test
    void flux_2(){
       var flux = webTestClient.
                get()
                .uri("/test")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .returnResult(Integer.class)
                .getResponseBody();

        StepVerifier.create(flux)
                .expectNext(1,2,3, 4)
                .verifyComplete();

    }

    @Test
    void flux_3(){
        var flux = webTestClient.
                get()
                .uri("/test")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(Integer.class)
                .consumeWith(listEntityExchangeResult -> {
                 var responseBody =  listEntityExchangeResult.getResponseBody();
                 assert Objects.requireNonNull(responseBody).size() == 4;
                });

    }
}