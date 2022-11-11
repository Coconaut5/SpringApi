package se.daniel.apidemo.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;


import se.daniel.apidemo.model.Item;
import se.daniel.apidemo.model.Phonebook;
import se.daniel.apidemo.model.UpdateItem;
import se.daniel.apidemo.repository.ItemRepository;
import se.daniel.apidemo.repository.PhonebookRepository;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    PhonebookRepository phonebookRepository;
    

    
	@GetMapping("/items")
	public ResponseEntity<List<Item>> getAllItems(@RequestParam(required = false) Integer price) {
        try {
            List<Item> items = List.of(new Item());

            if (price == null)
                items = itemRepository.findAll();
            else 
                items = itemRepository.findByPrice(price);

            if (items.isEmpty()) 
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            
            
            return ResponseEntity.ok(items);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	} 

    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable("id") long id) {
        Optional<Item> itemData = itemRepository.findById(id);

        if (itemData.isPresent()) {
            return ResponseEntity.ok(itemData.get());
        } 
        
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/items")
    public ResponseEntity<Item> createItem(@RequestBody Item newItem) {
        Item item = itemRepository.save(new Item(newItem.getName(), newItem.getDescription(), newItem.getPrice(), null));
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<HttpStatus> deleteItem(@PathVariable("id") long id) {
        try {
            itemRepository.deleteById(id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/items")
    public ResponseEntity<Item> updateItem(@RequestBody UpdateItem updatedItem) {
        Optional<Item> itemData = itemRepository.findById(updatedItem.getId());

        if (!itemData.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Item item = itemData.get();

        if (Objects.nonNull(updatedItem.getName())) {
            item.setName(updatedItem.getName());
        }

        if (updatedItem.getDescription() != null) {
            item.setDescription(updatedItem.getDescription());            
        }

        if (updatedItem.getPrice() != null) {
            item.setPrice(updatedItem.getPrice());
        }

        return ResponseEntity.ok(itemRepository.save(item));
    }

    // add user to item. and add item to the user's list shoppingCart
    @PutMapping("/items/{id}")
    public ResponseEntity<Item> addUser(@PathVariable("id") long id, @RequestParam long uid) {
  
        Optional<Item> itemData = itemRepository.findById(id);
        Optional<Phonebook> userData = phonebookRepository.findById(uid);

        if (!itemData.isPresent() && !userData.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } 

        Item item = itemData.get();

        if(Objects.nonNull(itemData)) {
            item.setUser(userData.get());
        }

        return ResponseEntity.ok(itemRepository.save(item));

    }
    // only need id of user from the put request body, that will be added to the Item (user)
}
