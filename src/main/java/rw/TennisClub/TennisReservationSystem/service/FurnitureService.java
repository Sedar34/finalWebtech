package rw.TennisClub.TennisReservationSystem.service;

import org.springframework.stereotype.Service;
import rw.TennisClub.TennisReservationSystem.model.Furniture;
import rw.TennisClub.TennisReservationSystem.userRepository.FurnitureRepository;

import java.util.List;

@Service
public class FurnitureService {

    private final FurnitureRepository furnitureRepository;

    public FurnitureService(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    public void saveAll(List<Furniture> furnitureList) {
        furnitureRepository.saveAll(furnitureList);
    }

    public List<Furniture> getAllFurniture() {
        return furnitureRepository.findAll();
    }
}
