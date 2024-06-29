package com.greengroc.v1api.services;

import com.greengroc.v1api.models.DeliveryPartner;
import com.greengroc.v1api.repositories.DeliveryPartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryPartnerService {

    @Autowired
    private DeliveryPartnerRepository deliveryPartnerRepository;

    public DeliveryPartner registerDeliveryPartner(DeliveryPartner deliveryPartner) {
        return deliveryPartnerRepository.save(deliveryPartner);
    }

    public DeliveryPartner getDeliveryPartnerDetails(Long deliveryPartnerId) {
        return deliveryPartnerRepository.findById(deliveryPartnerId).orElseThrow(() -> new RuntimeException("Delivery Partner not found"));
    }
}
