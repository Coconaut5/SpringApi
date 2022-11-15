package se.daniel.apidemo.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import se.daniel.apidemo.model.Item;


public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByName(String name);

    List<Item> findByPrice(Integer price);

    // Item <-> ItemToCategory <-> Category 
    @Query(value = "SELECT items.* FROM items LEFT JOIN items_categories ON items.id = items_categories.item_id LEFT JOIN categories C ON C.id = items_categories.categories_id WHERE categories_id = :id", nativeQuery = true)
    Set<Item> findByCategory(@Param("id") Long categoriesId);

    
}
