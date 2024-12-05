package rw.TennisClub.TennisReservationSystem.userRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.TennisClub.TennisReservationSystem.model.ResetToken;
import rw.TennisClub.TennisReservationSystem.model.User;

import java.util.Optional;

public interface ResetTokenRepository extends JpaRepository<ResetToken, Long> {
    void deleteByToken(String token);
    Optional<ResetToken> findByUser(User user);
    Optional<ResetToken> findByToken(String token);
}
