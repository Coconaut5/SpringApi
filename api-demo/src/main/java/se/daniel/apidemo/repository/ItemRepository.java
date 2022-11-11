package se.daniel.apidemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import se.daniel.apidemo.model.Item;


public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByName(String name);

    List<Item> findByPrice(Integer price);
    
}
