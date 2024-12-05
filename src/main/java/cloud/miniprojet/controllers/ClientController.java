package cloud.miniprojet.controllers;

import cloud.miniprojet.entities.Client;
import cloud.miniprojet.entities.Region;
import cloud.miniprojet.services.ClientService;
import cloud.miniprojet.services.RegionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;
    private final RegionService regionService; // Assuming you have a RegionService to handle region data

    // Constructor injection for both services
    public ClientController(ClientService clientService, RegionService regionService) {
        this.clientService = clientService;
        this.regionService = regionService;
    }

    // Display a list of all clients
    // Display a list of all clients or search results
    @GetMapping
    public String getAllClients(@RequestParam(value = "searchQuery", required = false) String searchQuery, Model model) {
        List<Client> clients;

        if (searchQuery != null && !searchQuery.isEmpty()) {
            // Search for clients based on the search query
            clients = clientService.searchClients(searchQuery);
            model.addAttribute("searchQuery", searchQuery); // Pass the search query to the view
        } else {
            // Get all clients if no search query is provided
            clients = clientService.getAllClients();
        }

        model.addAttribute("clients", clients);
        return "liste_client"; // client-list.html view
    }

    // Display the form for creating a new client
    @GetMapping("/create")
    public String createClientForm(Model model) {
        List<Region> regions = regionService.getAllRegions(); // Fetch regions from the service
        model.addAttribute("regions", regions); // Add the regions to the model
        model.addAttribute("client", new Client()); // Initialize an empty client object and add it to the model
        return "ajout_client"; // Return the name of the HTML view for creating a new client
    }


    // Handle the form submission for creating a new client
    @PostMapping
    public String addClient(@ModelAttribute Client client) {
        clientService.saveClient(client); // Save the client to the database
        return "redirect:/clients"; // Redirect to the client list page
    }

    // Handle the deletion of a client by ID
    @PostMapping("/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return "redirect:/clients"; // Redirect to the client list page after deletion
    }

    // Display the form for updating an existing client
    @GetMapping("/update/{id}")
    public String updateClientForm(@PathVariable Long id, Model model) {
        Optional<Client> client = clientService.getClientById(id); // Fetch the client by ID
        List<Region> regions = regionService.getAllRegions(); // Fetch regions for the dropdown

        if (client.isPresent()) {
            model.addAttribute("client", client.get()); // Add the client object to the model
            model.addAttribute("regions", regions); // Add the list of regions to the model
            return "modifier"; // Return the name of the HTML view for updating a client
        } else {
            // If client not found, return to the client list with an error message
            model.addAttribute("error", "Client not found");
            return "liste_client"; // Redirect to the client list page as fallback
        }
    }


    // Handle the form submission for updating an existing client
    @PostMapping("/update/{id}")
    public String updateClient(@PathVariable Long id, @ModelAttribute Client client) {
        client.setId(id); // Ensure the ID is set for the updated client
        clientService.saveClient(client); // Save the updated client details
        return "redirect:/clients"; // Redirect to the client list page after update
    }
}
