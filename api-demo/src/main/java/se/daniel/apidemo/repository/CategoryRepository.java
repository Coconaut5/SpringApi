package se.daniel.apidemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import se.daniel.apidemo.model.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByName(String name);


    // delete category, and remove its relations to all items. WIP
    // Transactional tag made it work?
    @Transactional
    @Modifying(flushAutomatically  = true)
    @Query(value = "DELETE FROM items_categories WHERE categories_id = :id", nativeQuery = true)
    void deleteItemsFromCategory(@Param("id") Long id);
    
}
