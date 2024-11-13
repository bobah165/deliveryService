package org.example.delivery.service.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.delivery.dto.Order;
import org.example.delivery.service.DeliveryService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerWarehouseService {

  private static final String topicCreateOrder = "${topic.consumer.warehouse}";
  private static final String kafkaConsumerGroupId = "${spring.kafka.consumer.group-id}";
  private final DeliveryService deliveryService;

  @Transactional
  @KafkaListener(topics = topicCreateOrder, groupId = kafkaConsumerGroupId, properties = {"spring.json.value.default.type=org.example.delivery.dto.Order"})
  public Order checkOrder(Order orderEvent) {
    log.info("Message consumed {}", orderEvent);
    deliveryService.checkOrder(orderEvent);
    return orderEvent;
  }
}
