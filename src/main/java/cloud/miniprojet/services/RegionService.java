package cloud.miniprojet.services;

import cloud.miniprojet.entities.Region;
import cloud.miniprojet.repositories.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public Region saveRegion(Region region) {
        return regionRepository.save(region);
    }
}