package rw.TennisClub.TennisReservationSystem.userRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.TennisClub.TennisReservationSystem.model.Furniture;

@Repository
public interface FurnitureRepository extends JpaRepository<Furniture, Long> {
}
