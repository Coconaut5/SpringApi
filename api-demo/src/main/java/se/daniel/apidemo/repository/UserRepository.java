package se.daniel.apidemo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import se.daniel.apidemo.model.User;


public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByCreatedAt(Date createdAt);

    List<User> findByPhoneNumber(String phoneNumber);

    List<User> findByFirstName(String firstName);

    
}
