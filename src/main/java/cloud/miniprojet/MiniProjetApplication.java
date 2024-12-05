package cloud.miniprojet;

import cloud.miniprojet.entities.Region;
import cloud.miniprojet.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MiniProjetApplication implements CommandLineRunner {

    @Autowired
    private RegionRepository regionRepository;

    public static void main(String[] args) {
        SpringApplication.run(MiniProjetApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Insert default regions if the table is empty
        if (regionRepository.count() == 0) {
            regionRepository.save(new Region(1L, "Mahdia"));
            regionRepository.save(new Region(2L, "Tunis"));
            regionRepository.save(new Region(3L, "Gafsa"));
            regionRepository.save(new Region(4L, "Kef"));
            regionRepository.save(new Region(5L, "Tataouine"));
        }
    }
}
