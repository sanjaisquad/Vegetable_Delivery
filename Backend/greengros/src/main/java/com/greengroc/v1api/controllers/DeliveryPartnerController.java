package com.greengroc.v1api.controllers;

import com.greengroc.v1api.models.DeliveryPartner;
import com.greengroc.v1api.services.DeliveryPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/deliveryPartners")
public class DeliveryPartnerController {

    @Autowired
    private DeliveryPartnerService deliveryPartnerService;

    @PostMapping("/register")
    public ResponseEntity<DeliveryPartner> registerDeliveryPartner(@RequestBody DeliveryPartner deliveryPartner) {
        return ResponseEntity.ok(deliveryPartnerService.registerDeliveryPartner(deliveryPartner));
    }

    @GetMapping("/{deliveryPartnerId}")
    public ResponseEntity<DeliveryPartner> getDeliveryPartnerDetails(@PathVariable Long deliveryPartnerId) {
        return ResponseEntity.ok(deliveryPartnerService.getDeliveryPartnerDetails(deliveryPartnerId));
    }
}
