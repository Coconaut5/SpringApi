package se.daniel.apidemo.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import se.daniel.apidemo.model.Phonebook;
import se.daniel.apidemo.model.UpdatePhonebook;
import se.daniel.apidemo.repository.PhonebookRepository;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class PhonebookController {

    @Autowired
    PhonebookRepository phonebookRepository;
    
	@GetMapping("/phonebook")
	public ResponseEntity<List<Phonebook>> getAllPhonebooks(@RequestParam(required = false) String firstName) {
        try {
            List<Phonebook> phonebook = List.of(new Phonebook());

            if (firstName == null)
                phonebook = phonebookRepository.findAll();
            else 
                phonebook = phonebookRepository.findByFirstName(firstName);

            if (phonebook.isEmpty()) 
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            
            
            return ResponseEntity.ok(phonebook);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	} 

    @GetMapping("/phonebook/{id}")
    public ResponseEntity<Phonebook> getPhonebookById(@PathVariable("id") long id) {
        Optional<Phonebook> phonebookData = phonebookRepository.findById(id);

        if (phonebookData.isPresent()) {
            return ResponseEntity.ok(phonebookData.get());
        } 
        
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/phonebook")
    public ResponseEntity<Phonebook> createPhonebook(@RequestBody Phonebook newPhonebook) {
        Phonebook phonebook = phonebookRepository.save(new Phonebook(newPhonebook.getFirstName(), newPhonebook.getLastName(), newPhonebook.getPhoneNumber(), new Date()));
        return new ResponseEntity<>(phonebook, HttpStatus.CREATED);
    }

    @DeleteMapping("/phonebook/{id}")
    public ResponseEntity<HttpStatus> deletePhonebook(@PathVariable("id") long id) {
        try {
            phonebookRepository.deleteById(id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/phonebook")
    public ResponseEntity<Phonebook> updatePhonebook(@RequestBody UpdatePhonebook updatedPhonebook) {
        Optional<Phonebook> phonebookData = phonebookRepository.findById(updatedPhonebook.getId());

        if (!phonebookData.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Phonebook phonebook = phonebookData.get();

        if (Objects.nonNull(updatedPhonebook.getFirstName())) {
            phonebook.setFirstName(updatedPhonebook.getFirstName());
        }

        if (updatedPhonebook.getLastName() != null) {
            phonebook.setLastName(updatedPhonebook.getLastName());            
        }

        if (updatedPhonebook.getPhoneNumber() != null) {
            phonebook.setPhoneNumber(updatedPhonebook.getPhoneNumber());
        }

        return ResponseEntity.ok(phonebookRepository.save(phonebook));
    }
}

