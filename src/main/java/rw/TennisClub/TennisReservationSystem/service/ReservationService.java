package rw.TennisClub.TennisReservationSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.TennisClub.TennisReservationSystem.model.Reservation;
import rw.TennisClub.TennisReservationSystem.userRepository.ReservationRepository;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    // Save a new reservation
    public void saveReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    // Update an existing reservation
    public void updateReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    // Delete a reservation by ID
    public void deleteReservation(Integer id) {
        reservationRepository.deleteById(id);
    }

    // Get a reservation by ID
    public Reservation getReservationById(Integer id) {
        return reservationRepository.findById(id).orElse(null);
    }

    // Get all reservations
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    // Search for reservations based on court number and date
    public List<Reservation> searchReservations(String courtnumber, String date) {
        // Implement your search logic here, for example:
        if (courtnumber != null && !courtnumber.isEmpty() && date != null && !date.isEmpty()) {
            return reservationRepository.findByCourtnumberAndDate(courtnumber, java.sql.Date.valueOf(date));
        } else if (courtnumber != null && !courtnumber.isEmpty()) {
            return reservationRepository.findByCourtnumber(courtnumber);
        } else if (date != null && !date.isEmpty()) {
            return reservationRepository.findByDate(java.sql.Date.valueOf(date));
        } else {
            return getAllReservations(); // Return all if no filter is applied
        }
    }

    // Save a list of reservations (for bulk upload)
    public void saveAll(List<Reservation> reservations) {
        reservationRepository.saveAll(reservations);
    }
}