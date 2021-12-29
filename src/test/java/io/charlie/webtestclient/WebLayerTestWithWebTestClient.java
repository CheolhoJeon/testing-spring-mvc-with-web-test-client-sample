package io.charlie.webtestclient;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;

import static org.springframework.http.HttpStatus.OK;

@DisplayName("MockMvc 인스턴스를 내부적으로 활용하는 WebTestClient 객체 생성")
@WebMvcTest(HomeController.class)
class UsingMockMvcWebTestClientBindingToMockMvc {

  @Autowired
  private MockMvc mockMvc;

  private WebTestClient webTestClient;

  @BeforeEach
  public void setup() {
    this.webTestClient = MockMvcWebTestClient
        .bindTo(mockMvc)
        .build();
  }

  @Test
  public void webClientTest() {
    this.webTestClient
        .get()
        .uri("/")
        .exchange()
        .expectStatus().isEqualTo(OK);
  }

}

@DisplayName("특정 컨트롤러에 바인딩된 MockMvc 인스턴스를 내부적으로 활용하는 WebTestClient 객체 생성")
class UsingMockMvcWebTestClientBindingToController {

  private WebTestClient webTestClient;

  @BeforeEach
  public void setup() {
    this.webTestClient = MockMvcWebTestClient
        .bindToController(HomeController.class)
        .build();
  }

  @Test
  public void webClientTest() {
    this.webTestClient
        .get()
        .uri("/")
        .exchange()
        .expectStatus().isEqualTo(OK);
  }

}

@DisplayName("SpringBootTest을 통해 등록한 WebTestClient 빈을 주입 받아 사용")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsingWebTestClientDirectly {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  public void webClientTest() {
    this.webTestClient
        .get()
        .uri("/")
        .exchange()
        .expectStatus().isEqualTo(OK);
  }

}