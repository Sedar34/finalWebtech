package rw.TennisClub.TennisReservationSystem.userRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.TennisClub.TennisReservationSystem.model.User;

import java.util.List;

@Repository


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findByUsernameContainingOrEmailContaining(String username, String email);
    List<User> findTop5ByOrderByIdDesc();
}

//import org.springframework.data.repository.PagingAndSortingRepository;
//
//import java.util.List;
//
//@Repository
//public interface UserRepository extends PagingAndSortingRepository<User, Long> {
//    User findByUsername(String username);
//    User findByEmail(String email);
//    List<User> findByUsernameContainingOrEmailContaining(String username, String email);
//}