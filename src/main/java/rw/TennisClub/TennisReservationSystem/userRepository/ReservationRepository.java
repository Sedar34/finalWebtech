package rw.TennisClub.TennisReservationSystem.userRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.TennisClub.TennisReservationSystem.model.Reservation;

import java.sql.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByCourtnumber(String courtnumber);
    List<Reservation> findByDate(Date date);
    List<Reservation> findByCourtnumberAndDate(String courtnumber, Date date);
}