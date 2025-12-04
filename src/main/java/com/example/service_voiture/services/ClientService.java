package com.example.service_voiture.services;

import com.example.service_voiture.entities.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Utilise Eureka pour trouver le service Client
@FeignClient(name="Client")
public interface ClientService{
    @GetMapping(path="/clients/{id}")
    Client clientById(@PathVariable Long id);
}
