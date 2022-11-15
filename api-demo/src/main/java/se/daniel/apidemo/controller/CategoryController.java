package se.daniel.apidemo.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import se.daniel.apidemo.model.Category;
import se.daniel.apidemo.model.Item;
import se.daniel.apidemo.repository.CategoryRepository;
import se.daniel.apidemo.repository.ItemRepository;

@RestController
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = List.of(new Category());

        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        categories = categoryRepository.findAll();

        return ResponseEntity.ok(categories);

    }

    @GetMapping("/categories/{categoryName}")
    public ResponseEntity getCategoryWithItems(@PathVariable("categoryName") String categoryName) {
       Category category = categoryRepository.findByName(categoryName).get(0);
       Set<Item> items = itemRepository.findByCategory(category.getId());

       return ResponseEntity.ok(items);
    } 




    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@RequestBody Category newCategory) {
        Category category = categoryRepository.save(new Category(newCategory.getName(), null));
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<HttpStatus> deleteItem(@PathVariable("id") long id) {
        try {
            categoryRepository.deleteItemsFromCategory(id);
            categoryRepository.deleteById(id);

            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    
}


