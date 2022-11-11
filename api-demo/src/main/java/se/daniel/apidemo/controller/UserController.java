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

import se.daniel.apidemo.model.User;
import se.daniel.apidemo.model.UpdateUser;
import se.daniel.apidemo.repository.UserRepository;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;
    
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String firstName) {
        try {
            List<User> userList = List.of(new User());

            if (firstName == null)
                userList = userRepository.findAll();
            else 
                userList = userRepository.findByFirstName(firstName);

            if (userList.isEmpty()) 
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            
            
            return ResponseEntity.ok(userList);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	} 

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        Optional<User> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            return ResponseEntity.ok(userData.get());
        } 
        
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        User user = userRepository.save(new User(newUser.getFirstName(), newUser.getLastName(), newUser.getPhoneNumber(), new Date(), null));
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        try {
            userRepository.deleteById(id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody UpdateUser updatedUser) {
        Optional<User> userData = userRepository.findById(updatedUser.getId());

        if (!userData.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User user = userData.get();

        if (Objects.nonNull(updatedUser.getFirstName())) {
            user.setFirstName(updatedUser.getFirstName());
        }

        if (Objects.nonNull(updatedUser.getLastName())) {
            user.setLastName(updatedUser.getLastName());            
        }

        if (Objects.nonNull(updatedUser.getPhoneNumber())) {
            user.setPhoneNumber(updatedUser.getPhoneNumber());
        }

        return ResponseEntity.ok(userRepository.save(user));
    }
}



// database normalization rules 1-3
