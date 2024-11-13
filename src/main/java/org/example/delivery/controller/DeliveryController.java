package org.example.delivery.controller;

import lombok.RequiredArgsConstructor;
import org.example.delivery.dto.Delivery;
import org.example.delivery.service.DeliveryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/delivery")
public class DeliveryController {

  private final DeliveryService deliveryService;

  @PostMapping("/save")
  public ResponseEntity createDelivery(@RequestBody Delivery delivery) {
    deliveryService.save(delivery);
    return ResponseEntity.ok("Delivery with name " + delivery.getName() + " was created");
  }

  @GetMapping("/status/{deliveryId}")
  public ResponseEntity getCurrentStatus(@PathVariable("deliveryId") String deliveryId) {
    var delivery = deliveryService.findById(deliveryId);
    return ResponseEntity.ok("Warehouse status is " + delivery.getIsExist());
  }
}
