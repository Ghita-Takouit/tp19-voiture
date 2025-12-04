package com.example.service_voiture;

import com.example.service_voiture.entities.Client;
import com.example.service_voiture.entities.Voiture;
import com.example.service_voiture.repositories.VoitureRepository;
import com.example.service_voiture.services.ClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class ServiceVoitureApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceVoitureApplication.class, args);
	}

	@Bean
	CommandLineRunner initialiserBaseH2(VoitureRepository voitureRepository, ClientService clientService){
		return args -> {
			// Insertion des voitures dans la base de données
			System.out.println("=== Initialisation de la base de données ===");

			voitureRepository.save(new Voiture(null, "Toyota", "A 25 333", "Corolla", 1L, null));
			voitureRepository.save(new Voiture(null, "Renault", "B 6 3456", "Megane", 1L, null));
			voitureRepository.save(new Voiture(null, "Peugeot", "A 55 4444", "301", 2L, null));

			System.out.println("✓ 3 voitures insérées avec succès!");

			// Test de connexion au service CLIENT (optionnel)
			try {
				System.out.println("\n=== Test de connexion au service CLIENT ===");
				Client c1 = clientService.clientById(2L);
				Client c2 = clientService.clientById(1L);

				System.out.println("✓ Service CLIENT accessible!");
				System.out.println("  Client 1 - Id: " + c2.getId() + ", Nom: " + c2.getNom());
				System.out.println("  Client 2 - Id: " + c1.getId() + ", Nom: " + c1.getNom() + ", Age: " + c1.getAge());
			} catch (Exception e) {
				System.err.println("⚠ Service CLIENT non accessible (les voitures seront retournées sans infos client)");
				System.err.println("  Erreur: " + e.getMessage());
			}

			System.out.println("=== Initialisation terminée ===\n");
		};
	}

}
