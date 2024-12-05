package cloud.miniprojet.services;

import cloud.miniprojet.entities.Client;
import cloud.miniprojet.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
    // Method to search clients based on a query
    public List<Client> searchClients(String searchQuery) {
        // You can customize this search logic based on your needs (e.g., search by name, surname, etc.)
        return clientRepository.findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(searchQuery, searchQuery);
    }
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }


}
