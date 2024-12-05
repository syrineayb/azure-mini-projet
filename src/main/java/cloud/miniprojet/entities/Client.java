package cloud.miniprojet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Generates getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates a constructor with all arguments
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom; // Ensure this property exists in your Client entity
    private String prenom; // Ensure this property exists in your Client entity
    private Integer age;
    @ManyToOne
    @JoinColumn(name = "id_region")
    private Region region;
}
