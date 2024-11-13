package org.example.delivery.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.delivery.dto.Delivery;
import org.example.delivery.dto.Order;
import org.example.delivery.repository.DeliveryRepository;
import org.example.delivery.service.saga.ProducerPaySagaService;
import org.example.delivery.service.producer.ProducerPayService;
import org.example.delivery.service.saga.ProducerWarehouseSagaService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryService {

  private final DeliveryRepository repository;
  private final ProducerPayService producerPayService;
  private final ProducerWarehouseSagaService producerWarehouseSagaService;
  private final ProducerPaySagaService producerPaySagaService;

  @Transactional
  public void save(Delivery delivery) {
    repository.save(delivery);
  }

  @Transactional
  public Delivery findById(String id){
    return repository.findById(id)
      .orElse(Delivery.builder().build());
  }

  @Async
  public void checkOrder(Order order) {
    var delivery = repository.findById(order.getId())
      .orElse(Delivery.builder().build());

    if (delivery.getIsExist().equals(Boolean.TRUE)) {
      order.setStatus("DELIVERED");
      producerPayService.sendOrder(order);
    } else {
      order.setStatus("CANCELED");
      producerWarehouseSagaService.sendOrder(order);
      producerPaySagaService.sendOrder(order);
    }
  }
}
