package org.example.delivery.service.saga;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.delivery.dto.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProducerWarehouseSagaService {

  @Value("${topic.saga.warehouse}")
  private String sendClientTopic;

  private final KafkaTemplate<String , Object> kafkaTemplate;

  public void sendOrder(Order order) {
    kafkaTemplate.send(sendClientTopic, order.getId(), order);
    log.info("Send order from producer {}", order);
  }
}
