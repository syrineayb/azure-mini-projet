package cloud.miniprojet.controllers;

import cloud.miniprojet.entities.Region;
import cloud.miniprojet.services.RegionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regions")
public class RegionController {

    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping
    public List<Region> getAllRegions() {
        return regionService.getAllRegions();
    }

    @PostMapping
    public Region addRegion(@RequestBody Region region) {
        return regionService.saveRegion(region);
    }
}
