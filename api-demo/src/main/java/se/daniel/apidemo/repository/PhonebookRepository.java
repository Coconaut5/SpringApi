package se.daniel.apidemo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import se.daniel.apidemo.model.Phonebook;


public interface PhonebookRepository extends JpaRepository<Phonebook, Long> {

    List<Phonebook> findByCreatedAt(Date createdAt);

    List<Phonebook> findByPhoneNumber(String phoneNumber);

    List<Phonebook> findByFirstName(String firstName);
    
}
