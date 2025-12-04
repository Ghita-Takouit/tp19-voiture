package com.example.service_voiture.services;

import com.example.service_voiture.entities.Client;
import com.example.service_voiture.entities.Voiture;
import com.example.service_voiture.repositories.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoitureService {

    @Autowired
    private VoitureRepository voitureRepository;

    @Autowired
    private ClientService clientService;

    public List<Voiture> findAll() {
        List<Voiture> voitures = voitureRepository.findAll();
        voitures.forEach(voiture -> {
            try {
                Client client = clientService.clientById(voiture.getId_client());
                voiture.setClient(client);
            } catch (Exception e) {
                System.err.println("Impossible de récupérer le client avec l'ID: " + voiture.getId_client());
                e.printStackTrace();
            }
        });
        return voitures;
    }

    public List<Voiture> findAllSimple() {
        return voitureRepository.findAll();
    }

    public Voiture findById(Long id) throws Exception {
        Voiture voiture = voitureRepository.findById(id)
                .orElseThrow(() -> new Exception("Voiture introuvable"));
        try {
            Client client = clientService.clientById(voiture.getId_client());
            voiture.setClient(client);
        } catch (Exception e) {
            System.err.println("Impossible de récupérer le client avec l'ID: " + voiture.getId_client());
            e.printStackTrace();
        }
        return voiture;
    }

    public Voiture save(Voiture voiture) {
        return voitureRepository.save(voiture);
    }

    public Voiture update(Long id, Voiture voiture) throws Exception {
        Voiture existingVoiture = voitureRepository.findById(id)
                .orElseThrow(() -> new Exception("Voiture introuvable"));

        existingVoiture.setMarque(voiture.getMarque());
        existingVoiture.setMatricule(voiture.getMatricule());
        existingVoiture.setModel(voiture.getModel());
        existingVoiture.setId_client(voiture.getId_client());

        return voitureRepository.save(existingVoiture);
    }

    public void delete(Long id) {
        voitureRepository.deleteById(id);
    }
}

