package ru.gb;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.Data;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.*;
import reactor.core.publisher.Mono;
import ru.gb.api.Book;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class BookProvider {

  private final WebClient webClient;

  public BookProvider(ReactorLoadBalancerExchangeFilterFunction loadBalancerExchangeFilterFunction) {
    webClient = WebClient.builder()
      .filter(loadBalancerExchangeFilterFunction)
      .build();
  }

  public UUID getRandomBookId() {
    Book randomBook = webClient.get()
      .uri("http://book-service/api/book/random")
      .retrieve()
      .bodyToMono(Book.class)
      .block();

    return randomBook.getId();
  }

}
